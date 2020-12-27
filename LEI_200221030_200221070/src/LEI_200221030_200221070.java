
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
    
    
    public static void main(String[] args) {

        
        LEI_200221030_200221070 mainClass = new LEI_200221030_200221070();
        mainClass.mainMenu = new MainMenu(mainClass);
        mainClass.presencasMenu = new PresencasMenu(mainClass);
        mainClass.userMenu = new UserMenu(mainClass);
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
        String status = "";
        
        System.out.println("-----------Creating--------------");
        System.out.println("Registering User: " + 200221030);
        Database database = new Database();
        ErrorCode result = database.registerUser(new User(200221030,UserState.CONTINUOUS));
        switch (result){
            case NoError:
                status = "Utilizador Registado Com Sucesso";
                break;
            case UserAlreadyRegistred:
                status = "Erro, Utilizador já se encontra registado";
                break;
        }
        System.out.println("Operation return -> " + status);
        System.out.println("Getting User 200221030");
        User test = database.getUser(200221030);
        System.out.println("Result: ");
        System.out.println(test.getIndividualID());
        
        System.out.println("-----------Updating--------------");
        System.out.println("New Id -> " + 200221031);
        test.setIndividualID(200221031);
        database.updateUser(test,200221030);
        
        switch (result){
            case NoError:
                status = "Utilizador Atualizado Com Sucesso";
                break;
            case UnknownErrorUpdatingData:
                status = "Erro desconhecido ao atualizar o utilizdor";
                break;
            case UserNotFound:
                status = "Erro ao atualizar o utilizdor, não encontrado";
                break;
        }
        
        System.out.println("Operation return -> " + status);
        
        System.out.println("-----------Obter com novo id--------------");
        User teste2 = database.getUser(200221031);
        System.out.println("Result: ");
        System.out.println(teste2.getIndividualID());
        
        
        System.out.println("------------Numero de utilizadorres na Bd-------------");
        System.out.println("Nº -> " + database.getNumberOfRegistredUsers());
        System.out.println("-----------Criar com id antigo--------------");
        System.out.println("Registering User: " + 200221030);
        result = database.registerUser(new User(200221030,UserState.CONTINUOUS));
        switch (result){
            case NoError:
                status = "Utilizador Registado Com Sucesso";
                break;
            case UserAlreadyRegistred:
                status = "Erro, Utilizador já se encontra registado";
                break;
        }
        System.out.println("Operation return -> " + status);
        System.out.println("Getting User 200221030");
        test = database.getUser(200221030);
        System.out.println("Result: ");
        System.out.println(test.getIndividualID());
        
        
        System.out.println("------------Numero de utilizadorres na Bd-------------");
        System.out.println("Nº -> " + database.getNumberOfRegistredUsers());
        System.out.println("-----------Criar com id novo--------------");
        System.out.println("Registering User: " + 200221031);
        result = database.registerUser(new User(200221031,UserState.CONTINUOUS));
        switch (result){
            case NoError:
                status = "Utilizador Registado Com Sucesso";
                break;
            case UserAlreadyRegistred:
                status = "Erro, Utilizador já se encontra registado";
                break;
        }
        System.out.println("Operation return -> " + status);
        System.out.println("Getting User 200221030");
        test = database.getUser(200221030);
        System.out.println("Result: ");
        System.out.println(test.getIndividualID());
        
        System.out.println("------------Numero de utilizadorres na Bd-------------");
        System.out.println("Nº -> " + database.getNumberOfRegistredUsers());
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
