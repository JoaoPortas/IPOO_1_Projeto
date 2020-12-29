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
            "Mostrar lista de sala",
            "Sair"
        };
        printMenu("Bem Vindo ao Sistema de rastreio, Área de Adminitração",options);
        this.currentlyAvailableOptions = options.length;
        int response = getResponse();
        switch (response){
            case 1:
                registerUser();
                break;
        }
        if (response == this.currentlyAvailableOptions){
            disableMenu();
            this.mainActivity.changeMenu(0);
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
                while (String.valueOf(response).length() != 9){
                    options = new String[]{"Cancelar"};
                    printMenu("Nº de indentificação inválido, tente novamente",options);
                    response = getResponse();
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
    
    
    private void disableMenu(){
        this.isActive = false;
    }


    private int getResponse(){
        return this.inputReader.getIntegerNumber("Opção");
    }

}
