public class UserMenu {
    
    private InputReader inputReader;
    private boolean isActive = false;
    private AppStart mainActivity;
    private int currentlyAvailableOptions;
    private User userObject;
    private Database database;
    int userId = -1;
    private Statistics stats;
    private HealthOrganization healthOrganization;
    
    public UserMenu(AppStart mainActivity,Database database,HealthOrganization healthOrganization,Statistics stats){
        if (this.isActive == false){
            if (this.mainActivity == null){
                this.mainActivity = mainActivity;
                this.inputReader = new InputReader();
                this.database = database;
                this.healthOrganization = healthOrganization;
                this.stats = stats;
            }
        }
    }

    public void enableMenu(){
        this.isActive = true;
        this.userId = inputReader.getIntegerNumber("Número de Utilizador");
        while(String.valueOf(this.userId).length() != 9){
            this.userId = inputReader.getIntegerNumber("Número de Utilizador inválido ou não existente, tente novamente");
        }
        
        this.userObject = database.getUser(this.userId);
        
        
         while (this.userObject == null){
            this.userId = inputReader.getIntegerNumber("Número de Utilizador inválido ou não existente, tente novamente(-1 para cancelar)");
            if (this.userId == -1){
                disableMenu();
                this.mainActivity.changeMenu(0);
            }
            this.userObject = this.database.getUser(this.userId);
        }
        this.menuHandler();
    }


    public boolean isActive(){
        return this.isActive;
    }

    private int getLongestInArray(String[] inputArray){
        int majorIndex = 0;
        int bufferStringSize = 0;
        int counter = 0;
        for (String buffer : inputArray){
            if (buffer.length() > bufferStringSize){
                bufferStringSize = buffer.length();
                majorIndex = counter;
            }
            counter++;
        }
        return majorIndex;
    }
    
   
    private void printMenu(String[] titles,String[] options){
        System.out.print('+');
        for (int a = 0; a < titles[getLongestInArray(titles)].length() + 10;a++){
            System.out.print('-');
        }
        System.out.print("+\n");
        for (String buffer : titles){
            
            System.out.println("     " + buffer);
            
        }
        System.out.println("                                          ");
        for(int a = 0; a < options.length;a++){
            System.out.println("  " + (a + 1) + " - " + options[a]);
        }
        System.out.println("                                          ");
        System.out.print('+');
        for (int a = 0; a < titles[getLongestInArray(titles)].length() + 10;a++){
            System.out.print('-');
        }
        System.out.print("+\n");
    }

    private void menuHandler(){
        String status = "";
        switch (this.userObject.getStatus()){
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
        String[] titles = {
            "Sistema de rastreio em sala de aula, Área do Utilizador",
            "Utilizador: " + this.userObject.getIndividualID(),
            "Estado: " + status + " desde " + this.userObject.getDateOfChangedStatus(),
        };
        String[] options = {
            "Verifiacar instruções da Entiade de saude",
            "Decalrar-se como infectado",
            "Entrar em isolamento profilático",
            "Terminar Isolamento",
            "Ver Estatisticas Diárias",
            "Sair"
        };
        printMenu(titles,options);
        int response = getResponse();
        switch (response){
            case 1:
                titles = this.healthOrganization.recomendations(this.userObject.getStatus());
                options = new String[]{"Voltar"};
                printMenu(titles,options);
                getResponse();
                menuHandler();
                break;
            case 2:
                //TODO 
                this.healthOrganization.addOnListOfInfecteds(userObject.getGeneraredIDs());
                options = new String[]{};
                titles = new String[]{"Defenido como infectado"};
                printMenu(titles,options);
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
            case 3:
                this.userObject.setStatus(UserState.ISOLATION);
                this.database.updateUser(this.userObject, this.userId);
                this.stats.addIsolation();
                options = new String[]{};
                titles = new String[]{"Entrou em isolado"};
                printMenu(titles,options);
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
            case 4:
                this.userObject.setStatus(UserState.CONTINUOUS);
                this.database.updateUser(this.userObject, this.userId);
                this.stats.removeIsolation();
                options = new String[]{};
                titles = new String[]{"Saiu do isolamento"};
                printMenu(titles,options);
                System.out.println("Prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
            case 5:
                this.stats.showStatistics();
                menuHandler();
                break;
            case 6:
                disableMenu();
                this.mainActivity.changeMenu(0);
                break;
        }
    }

    private void disableMenu(){
        this.isActive = false;
        this.userId = -1;
    }

    private int getResponse(){
        return this.inputReader.getIntegerNumber("Opção");
    }
}
