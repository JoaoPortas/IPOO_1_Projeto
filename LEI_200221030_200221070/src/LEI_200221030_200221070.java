/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author João Gouveia e João Portas
 */
public class LEI_200221030_200221070 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LEI_200221030_200221070 lol  = new LEI_200221030_200221070();
        InputReader reader = new InputReader();
        int what = reader.getIntegerNumber("Quem");
        if (what == 1){
            lol.joao();
        }else{
            lol.idkeyz();
        }
    }

    public void joao(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.drawMenu();
        int response = mainMenu.getResponse();
        switch(response){
            case 1:
                System.out.println("1");
                break;
                case 2:
                    System.out.println("2");
                    break;
                    case 3:
                        System.out.println("3");
                        break;
                        case 4:
                            System.out.println("4");
                            break;
                            case 5:
                                System.out.println("5");
                                break;
        }

    }


    
    public void idkeyz() {
        User user1 = new User(111111111, UserState.INFECTED);

        user1.addGenerateID("a");
        user1.addGenerateID("b");
        user1.addGenerateID("c");
        user1.addGenerateID("d");
        user1.addGenerateID("e");
        user1.addGenerateID("f");
        user1.addGenerateID("g");
        user1.addGenerateID("h");
        user1.addGenerateID("i");
        user1.addGenerateID("j");
    }
    
}
