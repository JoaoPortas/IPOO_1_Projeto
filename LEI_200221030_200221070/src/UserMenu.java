

public class UserMenu {
    
    InputReader inputReader;
    boolean isActive = false;
    LEI_200221030_200221070 mainActivity;
    int currentlyAvailableOptions;

    String userId = null;

    public UserMenu(LEI_200221030_200221070 mainActivity){
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
        String[] titles = {
            "Sistema de rastreio em sala de aula, Área do Utilizador",
            "Utilizador: " + this.userId,
            "Estado: Em Continuo desde 99/99/9999",
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
        
        if (response == 6){
            disableMenu();
            this.mainActivity.changeMenu(0);
        }
    }

    private void disableMenu(){
        this.isActive = false;
        this.userId = null;
    }

    private int getResponse(){
        return this.inputReader.getIntegerNumber("Opção");
    }

}
