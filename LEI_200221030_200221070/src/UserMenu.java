public class UserMenu {
    
    private InputReader reader;
    private int userId = -1;
    public UserMenu(){
        this.reader = new InputReader();
        this.userId = reader.getIntegerNumber("Número de Utilizador");
    }

    /*
    *   Mostra o Menu Principal
    *   Esta função nao retorna nenhum valor
    */
    public void drawMenu(){
        if (this.userId == -1) this.userId = reader.getIntegerNumber("Número de Utilizador");
        System.out.println("***************************************************");
        System.out.println("*                                                 *");
        System.out.println("*       Bem-vindo á aplicação de restreio         *");
        System.out.println("*                 de sala de aula                 *");
        System.out.println("*                                                 *");
        System.out.println("*                                                 *");
        System.out.println("*  1 - Verificar indicações da entidade de Saúde  *");
        System.out.println("*  2 - Declarar-se como infetado                  *");
        System.out.println("*  3 - Entrar em isolamento                       *");
        System.out.println("*  4 - Terminar isolamento                        *");
        System.out.println("*  5 - Ver estatisticas                           *");
        System.out.println("*  6 - Sair                                       *");
        System.out.println("*                                                 *");
        System.out.println("***************************************************");
    }

    public int getResponse(){
        int returnValue =  this.reader.getIntegerNumber("Opção");
        return returnValue;
    }
}
