
import java.util.HashSet;

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
    
    
    MainMenu mainMenu;
    PresencasMenu presencasMenu;
    UserMenu userMenu;
    HealthOrganizationMenu healthOrganizationMenu;
    AdministrationMenu administrationMenu;
    Database basededados;
    
    public static void main(String[] args) {

        
        LEI_200221030_200221070 mainClass = new LEI_200221030_200221070();
        mainClass.basededados  = new Database();
        mainClass.mainMenu = new MainMenu(mainClass);
        mainClass.presencasMenu = new PresencasMenu(mainClass,mainClass.basededados);
        mainClass.userMenu = new UserMenu(mainClass,mainClass.basededados);
        mainClass.healthOrganizationMenu = new HealthOrganizationMenu(mainClass);
        mainClass.administrationMenu = new AdministrationMenu(mainClass);
        
        
        InputReader reader = new InputReader();
        int what = reader.getIntegerNumber("Quem");
        if (what == 1){
            mainClass.joao();
        }else{
            mainClass.idkeyz();
        }
    }

    public void joao(){
        this.mainMenu.enableMenu();
        System.out.println("Database Size: " + this.basededados.getNumberOfRegistredUsers());
    }


    
    public void idkeyz() {
        User user1 = new User(111111111, UserState.INFECTED);

        user1.addGenerateID();
        user1.addGenerateID();
        user1.addGenerateID();
        user1.addGenerateID();
        user1.addGenerateID();
        user1.addGenerateID();
        user1.addGenerateID();
        user1.addGenerateID();
        user1.addGenerateID();
        user1.addGenerateID();
        
        user1.deleteOldIDs();
    }    
    
    public void closeApplication(){
        System.out.println("+---------------------------------------+");
        System.out.println("                                         ");
        System.out.println("           Adeus, volte sempre           ");
        System.out.println("                                         ");
        System.out.println("+---------------------------------------+");
        
        System.out.println("Database Size: " + this.basededados.getNumberOfRegistredUsers());
        System.exit(0);
    }

    public void changeMenu(int newMenu){
        switch (newMenu){
            case 0:
                this.mainMenu.enableMenu();
                break;
            case 1:
                this.presencasMenu.enableMenu();
                break;
            case 2:
                this.userMenu.enableMenu();
                break;
            case 3:
                this.healthOrganizationMenu.enableMenu();
                break;
            case 4:
                this.administrationMenu.enableMenu();
                break;
        }
    }
    
}
