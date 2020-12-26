/**
 * Class MainMenu representa o menu principal
 * @author João Gouveia e João Portas
 */
public class MainMenu {
    
    InputReader inputReader;
    boolean isActive = false;
    LEI_200221030_200221070 mainActivity;
    int currentlyAvailableOptions;
    
    public MainMenu(LEI_200221030_200221070 mainActivity){
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
            "Registo de presenças",
            "Área de utilizador",
            "Área de Autoridade de Saúde",
            "Área de Administrador",
            "Sair"
        };
        printMenu("Bem Vindo ao Sistema de rastreio",options);
        int response = getResponse();
        switch(response){
            case 1:
                this.mainActivity.changeMenu(1);
                break;
            case 2:
                this.mainActivity.changeMenu(1);
                break;
            case 3:
                this.mainActivity.changeMenu(1);
                break;
            case 4:
                this.mainActivity.changeMenu(1);
                break;
            case 5:
                disableMenu();
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
        this.mainActivity.closeApplication();
    }


    private int getResponse(){
        return this.inputReader.getIntegerNumber("Opção");
    }

}
