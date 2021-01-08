
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
public class AppStart {

    /**
     * @param args the command line arguments
     */
    
    
    /*Teste Push
     *k
     */
    MainMenu mainMenu;
    PresencasMenu presencasMenu;
    UserMenu userMenu;
    HealthOrganizationMenu healthOrganizationMenu;
    AdministrationMenu administrationMenu;
    Database basededados;
    Statistics stats;
    HealthOrganization healthOrganization;
    
    public static void main(String[] args) {

        
        AppStart mainClass = new AppStart();
        mainClass.basededados  = new Database();
        mainClass.stats = new Statistics(mainClass.basededados);
        mainClass.healthOrganization = new HealthOrganization(mainClass.basededados,mainClass.stats);
        mainClass.mainMenu = new MainMenu(mainClass,mainClass.basededados);
        mainClass.presencasMenu = new PresencasMenu(mainClass,mainClass.basededados);
        mainClass.userMenu = new UserMenu(mainClass,mainClass.basededados,mainClass.healthOrganization,mainClass.stats);
        mainClass.healthOrganizationMenu = new HealthOrganizationMenu(mainClass,mainClass.basededados,mainClass.stats,mainClass.healthOrganization);
        mainClass.administrationMenu = new AdministrationMenu(mainClass,mainClass.basededados);
        
        mainClass.joao();
    }

    public void joao(){
        
        
        Classroom classroom = new Classroom(3,3,this.basededados,"F251");
        Classroom classroom2 = new Classroom(4,4,this.basededados,"F252");
        Classroom classroom3 = new Classroom(5,5,this.basededados,"F253");
        Classroom classroom4 = new Classroom(6,6,this.basededados,"F254");
        Classroom classroom5 = new Classroom(7,7,this.basededados,"F256");
        
        this.basededados.registerClassroom(classroom);
        this.basededados.registerClassroom(classroom2);
        this.basededados.registerClassroom(classroom3);
        this.basededados.registerClassroom(classroom4);
        this.basededados.registerClassroom(classroom5);
        this.basededados.registerUser(new User(200221030,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221031,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221032,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221033,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221034,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221035,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221036,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(111111111,UserState.CONTINUOUS,Cargos.Professor));
        this.basededados.registerUser(new User(222222222,UserState.CONTINUOUS,Cargos.Professor));
        this.mainMenu.enableMenu();
        
        
    }


    
    public void idkeyz() {

        Classroom classroom = new Classroom(3,3,this.basededados,"F251");
        Classroom classroom2 = new Classroom(4,4,this.basededados,"F252");
        Classroom classroom3 = new Classroom(5,5,this.basededados,"F253");
        Classroom classroom4 = new Classroom(6,6,this.basededados,"F254");
        Classroom classroom5 = new Classroom(7,7,this.basededados,"F256");
        classroom.drawMap();
        
        this.basededados.registerClassroom(classroom);
        this.basededados.registerClassroom(classroom2);
        this.basededados.registerClassroom(classroom3);
        this.basededados.registerClassroom(classroom4);
        this.basededados.registerClassroom(classroom5);
        this.basededados.registerUser(new User(200221030,UserState.INFECTED,Cargos.Aluno));
        this.basededados.registerUser(new User(200221031,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221032,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221033,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221034,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221035,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221036,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(111111111,UserState.CONTINUOUS,Cargos.Professor));
        this.basededados.registerUser(new User(222222222,UserState.INFECTED,Cargos.Professor));
        this.basededados.registerUser(new User(333333333,UserState.INFECTED,Cargos.Professor));
        //this.healthOrganization

        //this.healthOrganization.addOnListOfInfecteds(this.basededados.getUser(333333333).getRecivedIDs());

        this.mainMenu.enableMenu();
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
