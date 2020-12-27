/**
 * Class MainMenu representa o menu principal
 * @author João Gouveia e João Portas
 */
public class HealthOrganizationMenu {
    
    private InputReader inputReader;
    private boolean isActive = false;
    private LEI_200221030_200221070 mainActivity;
    private int currentlyAvailableOptions;
    
    public HealthOrganizationMenu(LEI_200221030_200221070 mainActivity){
        if (this.isActive == false){
            if (this.mainActivity == null){
                this.mainActivity = mainActivity;
                this.inputReader = new InputReader();
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
