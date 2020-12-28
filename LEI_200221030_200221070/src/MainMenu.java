/**
 * Class MainMenu representa o menu principal
 * @author João Gouveia e João Portas
 */
public class MainMenu {
    
    private InputReader inputReader;
    private boolean isActive = false;
    private LEI_200221030_200221070 mainActivity;
    private int currentlyAvailableOptions;
    private Database database;
    
    public MainMenu(LEI_200221030_200221070 mainActivity,Database database){
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
            "Registo de presenças",
            "Área de utilizador",
            "Área de Autoridade de Saúde",
            "Área de Administrador",
            "Sair"
        };
        printMenu("Bem Vindo ao Sistema de rastreio",options);
        int response = getResponse();
        if (response > 0 && response < 5){
            this.mainActivity.changeMenu(response);
        }
        else if (response == 5){
            disableMenu();
        }else if (response == 6){
            String status = "";
            System.out.println("----------------Database Print:-------------------");
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
            System.out.println("prima 'enter' para continuar");
            this.inputReader.nextLine();
            menuHandler();
        }
        else{
            System.out.println("Opção Inválida, tente novamente (prima 'enter' para continuar)");
            this.inputReader.nextLine();
            menuHandler();
        }
    }

    private void disableMenu(){
        this.isActive = false;
        this.mainActivity.closeApplication();
    }


    private int getResponse(){
        return this.inputReader.getIntegerNumber("Opção");
    }

}
