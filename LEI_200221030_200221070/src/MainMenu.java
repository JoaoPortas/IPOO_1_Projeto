public class MainMenu {
    
    private InputReader reader;

    public MainMenu(){
        this.reader = new InputReader();
    }

    /*
    *   Mostra o Menu Principal
    *   Esta função nao retorna nenhum valor
    */
    public void drawMenu(){
        System.out.println("*************************************************");
        System.out.println("*                                               *");
        System.out.println("*       Bem-vindo á aplicação de restreio       *");
        System.out.println("*                 de sala de aula               *");
        System.out.println("*                                               *");
        System.out.println("*                                               *");
        System.out.println("*  1 - Registo de Presenças                     *");
        System.out.println("*  2 - Área de Utilizador                       *");
        System.out.println("*  3 - Área de Autoridade de Saúde              *");
        System.out.println("*  4 - Área de Administrador                    *");
        System.out.println("*  5 - Sair                                     *");
        System.out.println("*                                               *");
        System.out.println("*************************************************");
    }

    public int getResponse(){
        int returnValue =  this.reader.getIntegerNumber("Opção");
        return returnValue;
    }
}
