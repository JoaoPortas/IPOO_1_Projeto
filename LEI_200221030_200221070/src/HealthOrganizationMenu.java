/**
 * Class MainMenu representa o menu principal
 * @author João Gouveia e João Portas
 */
public class HealthOrganizationMenu {
    
    private InputReader inputReader;
    private boolean isActive = false;
    private LEI_200221030_200221070 mainActivity;
    private int currentlyAvailableOptions;
    private Database database;
    private HealthOrganization healthOrganization;
    private Statistics stats;
    
    public HealthOrganizationMenu(LEI_200221030_200221070 mainActivity,Database database,Statistics stats,HealthOrganization healthOrganization){
        if (this.isActive == false){
            if (this.mainActivity == null){
                this.mainActivity = mainActivity;
                this.inputReader = new InputReader();
                this.database = database;
                this.stats = stats;
                this.healthOrganization = healthOrganization;
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
            "Enviar lista de alunos infetados hoje",
            "Ver Estatisticas",
            "Sair"
        };
        printMenu("Bem Vindo ao Sistema de rastreio,Área de Adminitração de Saúde",options);
        this.currentlyAvailableOptions = options.length;
        int response = getResponse();
        switch (response){
            case 1:
                //TODO enviar generatedIds para lá
                //TODO atualizar estatisticas
                /*Apagar*/
                HealthOrganization ho1 = new HealthOrganization(database);
                for (User temp : database.getAllUsers()) {
                    if (temp != null) {
                        System.out.println(temp.getStatus());
                        if (temp.getStatus().equals(UserState.INFECTED)) {
                            ho1.addOnListOfInfecteds(temp.getGeneraredIDs());
                        }
                    }
                    
                    /**/
                }

                ho1.checkUsersContacts();
                /*HealthOrganization ho1 = new HealthOrganization(database);
                //ho1.addOnListOfInfecteds();
                for (String temp : ho1.getListOfCodesOfInfdStrings()) {
                    System.out.println(temp);
                }*/
                /***** */
                break;
            case 2:
                this.stats.showStatistics();
                menuHandler();
                break;
        }
        if (response == this.currentlyAvailableOptions){
            disableMenu();
            this.mainActivity.changeMenu(0);
        }
    }

    private void disableMenu(){
        this.isActive = false;
    }


    private int getResponse(){
        return this.inputReader.getIntegerNumber("Opção");
    }

}
