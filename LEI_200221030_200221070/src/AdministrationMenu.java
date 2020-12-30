/**
 * Class MainMenu representa o menu principal
 * @author João Gouveia e João Portas
 */
public class AdministrationMenu {
    
    private InputReader inputReader;
    private boolean isActive = false;
    private LEI_200221030_200221070 mainActivity;
    private int currentlyAvailableOptions;
    private Database database;
    
    
    public AdministrationMenu(LEI_200221030_200221070 mainActivity,Database database){
        if (this.isActive == false){
            if (this.mainActivity == null){
                this.mainActivity = mainActivity;
                this.inputReader = new InputReader();
                this.database = database;
            }
        }
    }

    public void enableMenu(){
        this.isActive = true;
        this.menuHandler();
    }

    public boolean isActive(){
        return this.isActive;
    }

    /**
     * 
     * @param title Titlo a exibir
     * @param options Opções a exibir
     */
    
    
    private void printMenu(String title,String[] options){
        System.out.print('+');
        for (int a = 0; a < title.length() + 10;a++){
            System.out.print('-');
        }
        System.out.print("+\n");
        System.out.println("     " + title);
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
            "Criar Utilizador",
            "Eliminar Utilizador",
            "Mostra lsta de utilizadores",
            "Criar sala de aula",
            "Eliminar sala de aula",
            "Mostrar lista de salas de aulas",
            "Sair"
        };
        printMenu("Bem Vindo ao Sistema de rastreio, Área de Adminitração",options);
        this.currentlyAvailableOptions = options.length;
        int response = getResponse();
        switch (response){
            case 1:
                registerUser();
                break;
            case 2:
                removeUser();
                break;
            case 3:
                String status = "";
                System.out.println("----------------Utilizadores na Base de dados:-------------------");
                for (User buffer : database.getAllUsers()){
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
                    System.out.println("Tipo de Utilizador: " + buffer.getCargo());
                    System.out.println("GeneratedIds: {");
                    for (String stringBuffer : buffer.getGeneraredIDs()){
                        System.out.println("'"  + stringBuffer + "'");
                    }
                    System.out.println("}");
                    System.out.println("RecivedIDs: {");
                    for (String stringBuffer : buffer.getRecivedIDs()){
                        System.out.println("'"  + stringBuffer + "'");
                    }
                    System.out.println("}");
                    System.out.println("-------------------------------------------------");
                }
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
            case 4:
                registerClassroom();
                break;
            case 5:
                removeClassroom();
                break;
            case 6:
                System.out.println("----------------Salas de aula na Base de dados:-------------------");
                for (Classroom buffer : database.getAllClassrooms()){
                    if (buffer == null) continue;
                    System.out.println("Nome: " + buffer.getName());
                    System.out.println("Capacidade da Sala: " + buffer.getClassroomCapacity());
                    System.out.println("Linhas: " + buffer.getLinhas());
                    System.out.println("Colunas: " + buffer.getColunas());
                    System.out.println("-------------------------------------------------");
                }
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
        }
        if (response == this.currentlyAvailableOptions){
            disableMenu();
            this.mainActivity.changeMenu(0);
        }
    }

    private void removeUser(){
        User newUser;
        String[] options = new String[]{"Cancelar"};
        printMenu("Insira Nº de identificação do Utilizador",options);
        int response = getResponse();
        switch (response){
            case 1:
                menuHandler();
                break;
            default:
                 while (String.valueOf(response).length() != 9 && response != -1){
                    options = new String[]{"Cancelar"};
                    printMenu("Nº de indentificação inválido, tente novamente, (-1 para cancelar)",options);
                    response = getResponse();
                }
                
                if (response == -1){
                    menuHandler();
                    return;
                }
                
                ErrorCode code = this.database.removeUserFromDB(response);
                switch (code){
                    case NoError:
                        options = new String[]{};
                        printMenu("Utilizador removido com sucesso",options);
                        System.out.println("Prime 'enter' para continuar");
                        this.inputReader.nextLine();
                        menuHandler();
                        break;
                    case UserNotFound:
                        options = new String[]{};
                        printMenu(" Erro ao remover utilizador, utilizador não encontrado",options);
                        System.out.println("Prime 'enter' para continuar");
                        this.inputReader.nextLine();
                        menuHandler();
                        break;
                }
        }
    }
    
    public void removeClassroom(){
        String[] options = new String[]{"Cancelar"};
        printMenu("Insira Nome da sala",options);
        String resposta = getResponseString();
        if (resposta.equals("1")){
            menuHandler();
            return;
        }
        
        ErrorCode code = this.database.removeClassroom(resposta, false);
        switch (code){
            case NoError:
                options = new String[]{};
                printMenu("Sala removida Com Sucesso",options);
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
            case ClassroomNotFound:
                options = new String[]{};
                printMenu("sala não removida, sala não encontrada",options);
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
                
        }
        
    }
    
    public void registerUser(){
        User newUser;
        String[] options = new String[]{"Cancelar"};
        printMenu("Insira Nº de identificação do Utilizador",options);
        
        int response = getResponse();
        switch (response){
            case 1:
                menuHandler();
                break;
            default:
                while (String.valueOf(response).length() != 9 && response != -1){
                    options = new String[]{"Cancelar"};
                    printMenu("Nº de indentificação inválido, tente novamente, (-1 para cancelar)",options);
                    response = getResponse();
                }
                
                if (response == -1){
                    menuHandler();
                    return;
                }
                
                int UID = response;
                
                options = new String[]{"Infectado","Isolamento","Continuo"};
                printMenu("Insira o estado do utilizador",options);
                response = getResponse();
                UserState status = null;
                switch(response){
                    case 1:
                        status = UserState.INFECTED;
                        break;
                    case 2:
                        status = UserState.ISOLATION;
                        break;
                    case 3:
                        status = UserState.CONTINUOUS;
                        break;
                }
                
                
                options = new String[]{"Aluno","Professor"};
                printMenu("Insira o estado do utilizador",options);
                response = getResponse();
                Cargos cargo = null;
                switch(response){
                    case 1:
                        cargo = Cargos.Aluno;
                        break;
                    case 2:
                        cargo = Cargos.Professor;
                        break;
                }
                
                newUser = new User(UID,status,cargo);
                ErrorCode code = database.registerUser(newUser);
                switch (code){
                    case NoError:
                        options = new String[]{};
                        printMenu("Utilizador Registado Com Sucesso",options);
                        System.out.println("Prima 'enter' para continuar");
                        this.inputReader.nextLine();
                        menuHandler();
                        break;
                    case UnknownErrorRegisteringUser:
                        options = new String[]{};
                        printMenu("Ocurreu um erro ao registar Utilizador, tente novamente",options);
                        System.out.println("Prima 'enter' para continuar");
                        this.inputReader.nextLine();
                        menuHandler();
                        break;
                    case UserAlreadyRegistred:
                        options = new String[]{};
                        printMenu("Utilizador já se encontra registado, tente novamente",options);
                        System.out.println("Prima 'enter' para continuar");
                        this.inputReader.nextLine();
                        menuHandler();
                        break;
                        
                }
        }
        
    }
    
    public void registerClassroom(){
        Classroom classroomToRegister;
        String[] options = new String[]{"Cancelar"};
        printMenu("Insira o nome da sala",options);
        String response = getResponseString();
        if (response.equals("1")){
            menuHandler();
            return;
        }
        String nomeDaSala = response;
        options = new String[]{"Cancelar"};
        printMenu("Insira o número de colunas na sala",options);
        int response2 = getResponse();
        while (response2 < 1){
            options = new String[]{"Cancelar"};
            printMenu("Insira o número de colunas na sala",options);
            response2 = getResponse();
        }
        int colunas = response2;
        
        
        options = new String[]{"Cancelar"};
        printMenu("Insira o número de linhas na sala",options);
        response2 = getResponse();
        while (response2 < 1){
            options = new String[]{"Cancelar"};
            printMenu("Insira o número de linhas na sala",options);
            response2 = getResponse();
        }
        int linhas = response2;
        
        classroomToRegister = new Classroom(colunas,linhas,this.database,nomeDaSala);
        ErrorCode registerResult = this.database.registerClassroom(classroomToRegister);
        switch (registerResult){
            case NoError:
                options = new String[]{};
                printMenu("Sala Registada Com Sucesso",options);
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
            case ClassroomAlreadyRegistred:
                options = new String[]{};
                printMenu("Sala não Registada, sala já se encontrava registada",options);
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
        }
    }
    
    private void disableMenu(){
        this.isActive = false;
    }


    private int getResponse(){
        return this.inputReader.getIntegerNumber("Opção");
    }

    private String getResponseString(){
        return this.inputReader.getText("Opção");
    }
}
