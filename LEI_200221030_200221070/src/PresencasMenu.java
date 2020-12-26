

public class PresencasMenu {
    
    InputReader inputReader;
    boolean isActive = false;
    LEI_200221030_200221070 mainActivity;
    int currentlyAvailableOptions;

    String userId = null;

    public PresencasMenu(LEI_200221030_200221070 mainActivity){
        if (this.isActive == false){
            if (this.mainActivity == null){
                this.mainActivity = mainActivity;
                this.inputReader = new InputReader();
            }
        }
    }

    public void enableMenu(){
        this.isActive = true;
        this.userId = inputReader.getText("Número de Utilizador");
        while(this.userId.length() != 9){
            this.userId = inputReader.getText("Número de Utilizador inválido, tente novamente");
        }
        this.menuHandler();
    }


    public boolean isActive(){
        return this.isActive;
    }

    private void printMenu(String title,String[] options){
        System.out.println("+-----------------------------------------+");
        System.out.println("     " + title + "              ");
        System.out.println("                                          ");
        for(int a = 0; a < options.length;a++){
            System.out.println("  " + (a + 1) + " - " + options[a]);
        }
        System.out.println("                                          ");
        System.out.println("+-----------------------------------------+");
    }

    private void menuHandler(){
        String[] options = {
            "Registar presença na aula",
            "Terminar Aula",
            "Sair",
        };
        printMenu("Bem Vindo ao Sistema de rastreio",options);
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
            default:
                System.out.println("Opção Inválida, tente novamente (prima 'enter' para continuar)");
                this.inputReader.nextLine();
                menuHandler();
                break;
        }
    }

    private void disableMenu(){
        this.isActive = false;
        this.userId = null;
    }

    private boolean registerPresence(){
        String[] opcoes = {"Cancelar"};
        printMenu("Registo de Presença na sala de aula", opcoes);
        int response = getResponse();
        switch(response){
            case 1:
                menuHandler();
                break;
            default:
                System.out.println("Opção Inválida, prima 'enter' para continuar");
                this.inputReader.nextLine();
                menuHandler();
                break;
        }
        return true;
    }

    public void terminarAula(){
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
