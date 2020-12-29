
import java.util.Arrays;



public class PresencasMenu {
    
    
    private InputReader inputReader;
    private boolean isActive = false;
    private LEI_200221030_200221070 mainActivity;
    private int currentlyAvailableOptions;
    private Database database;
    private User[] usersInClass;
    int userId = -1;

    public PresencasMenu(LEI_200221030_200221070 mainActivity,Database basededados){
        if (this.isActive == false){
            if (this.mainActivity == null){
                this.mainActivity = mainActivity;
                this.inputReader = new InputReader();
                this.database = basededados;
                this.usersInClass = new User[1];
            }
        }
    }

    public void enableMenu(){
        this.isActive = true;
        this.userId = inputReader.getIntegerNumber("Número de Utilizador");
        while(String.valueOf(this.userId).length() != 9){
            this.userId = inputReader.getIntegerNumber("Número de Utilizador inválido, tente novamente");
        }
        User gotUser = this.database.getUser(this.userId);
        
        while (gotUser == null){
            this.userId = inputReader.getIntegerNumber("Número de Utilizador inválido ou não existente, tente novamente(-1 para cancelar)");
            if (this.userId == -1){
                disableMenu();
                this.mainActivity.changeMenu(0);
            }
            gotUser = this.database.getUser(this.userId);
        }
        
        if (gotUser.getCargo() == Cargos.Professor){
            addUserToClassArray(gotUser);
            this.menuHandler();
        }else{
                System.out.println("Utilizador sem acesso a esta funcionalidade, tente noavmente");
                this.inputReader.nextLine();
                disableMenu();
                this.mainActivity.changeMenu(0);
        }
    }

    
    private void addUserToClassArray(User target){
        
        User[] buffer = Arrays.copyOf(this.usersInClass,this.usersInClass.length + 1);
        buffer[this.usersInClass.length - 1] = target;
        this.usersInClass = Arrays.copyOf(buffer, buffer.length);
        
    }

    public boolean isActive(){
        return this.isActive;
    }

   private void printMenu(String title,String[] options){
        System.out.print('+');
        for (int a = 0; a < title.length() + 10;a++){
            System.out.print('-');
        }
        System.out.print("+\n");
        System.out.println("     " + title + "              ");
        System.out.println("                                          ");
        for(int a = 0; a < options.length;a++){
            System.out.println("  " + (a + 1) + " - " + options[a]);
        }
        System.out.println("                                          ");
        System.out.print('+');
        for (int a = 0; a < title.length() + 10;a++){
            System.out.print('-');
        }
        System.out.print("+\n");
    }

    private void menuHandler(){
        String[] options = {
            "Registar presença na aula",
            "Terminar Aula",
            "Sair",
        };
        printMenu("Registo de presenças na aula",options);
        int response = getResponse();
        switch(response){
            case 1:
                this.registerPresence();
                break;
            case 2:
                this.terminarAula();
                break;
            case 3:
                disableMenu();
                this.mainActivity.changeMenu(0);
                break;
            case 4:
                String status = "";
                System.out.println("----------------Class Print:-------------------");
                for (User buffer : this.usersInClass){
                    if (buffer == null) continue;
                    System.out.println("Id: " + buffer.getIndividualID());

                    switch (buffer.getStatus()){
                        case CONTINUOUS:
                            status = "Em Continuo";
                            break;
                        case INFECTED:
                            status = "Em Infectado";
                            break;
                        case ISOLATION:
                             status = "Em Isolamento";
                             break;
                    }
                    System.out.println("Estado: " + status);
                    System.out.println("-------------------------------------------------");
                }
                System.out.println("prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
            default:
                System.out.println("Opção Inválida, tente novamente (prima 'enter' para continuar)");
                this.inputReader.nextLine();
                menuHandler();
                break;
        }
    }

    private void disableMenu(){
        this.isActive = false;
        this.userId = -1;
        this.usersInClass = new User[1];
    }

    
    private boolean userAlreadyInClass(int individualID){
        for (User buffer : this.usersInClass){
            if (buffer == null) continue;
            if (buffer.getIndividualID() == individualID){
                return true;
            }
        }
        return false;
    }
    
    private boolean registerPresence(){
        String[] opcoes = {"Cancelar"};
        printMenu("Insira o nº do Aluno ou uma das seguintes opções", opcoes);
        int response = getResponse();
        switch(response){
            case 1:
                menuHandler();
                break;
            default:
                if (String.valueOf(response).length() != 9){
                    System.out.println("Opção Inválida, prima 'enter' para continuar");
                    this.inputReader.nextLine();
                    menuHandler();
                }else{
                    
                    if (!userAlreadyInClass(response)){
                        ErrorCode status;
                        User tempBuffer = this.database.getUser(response);
                        
                        while (tempBuffer == null && response != -1){
                            response = inputReader.getIntegerNumber("Número de Utilizador inválido ou não existente, tente novamente(-1 para cancelar)");
                            if (this.userId == -1){
                                menuHandler();
                            }
                            tempBuffer = this.database.getUser(response);
                        }
                        
                        if (response == -1){
                            menuHandler();
                            return false;
                        }
                            tempBuffer.addGenerateID();
                            status = database.updateUser(tempBuffer, response);
                            switch(status){
                                case NoError:
                                    addUserToClassArray(tempBuffer);
                                    opcoes = new String[]{};
                                    printMenu("Utilizador Registado na aula Com Sucesso",opcoes);
                                    System.out.println("Prima 'enter' para continuar");
                                    this.inputReader.nextLine();
                                    menuHandler();
                                    break;
                            }
                    }else{
                        opcoes = new String[]{};
                        printMenu("Utilizador já se encontra registado na aula",opcoes);
                        System.out.println("Prima 'enter' para continuar");
                        this.inputReader.nextLine();
                        menuHandler();
                    }
                }
                break;
                
        }
        return true;
    }

    private void sendIds(){
        for (User buffer : this.usersInClass){
            if (buffer == null) continue;
            for (User secondBuffer : this.usersInClass){
                if (secondBuffer == null) continue;
                if (secondBuffer.getIndividualID() == buffer.getIndividualID()) continue;
                secondBuffer.reciveID(buffer.getCurrentIDToCastString(),0);
                this.database.updateUser(secondBuffer,secondBuffer.getIndividualID());
            }
        }
    }
    
    
    
    private void terminarAula(){
        for (User buffer : this.usersInClass){
            if (buffer == null) continue;
            buffer.addGenerateID();
            this.database.updateUser(buffer, buffer.getIndividualID());
        }
        sendIds();
        String[] opcoes = {};
        printMenu("Aula terminada com sucesso", opcoes);
        disableMenu();
        System.out.println("Prima 'enter' para continuar:");
        this.inputReader.nextLine();
        this.mainActivity.changeMenu(0);
    }

    private int getResponse(){
        return this.inputReader.getIntegerNumber("Opção");
    }

}
