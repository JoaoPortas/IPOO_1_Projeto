
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
    Statistics stats;
    
    public static void main(String[] args) {

        
        LEI_200221030_200221070 mainClass = new LEI_200221030_200221070();
        mainClass.basededados  = new Database();
        mainClass.stats = new Statistics(mainClass.basededados);
        mainClass.mainMenu = new MainMenu(mainClass,mainClass.basededados);
        mainClass.presencasMenu = new PresencasMenu(mainClass,mainClass.basededados);
        mainClass.userMenu = new UserMenu(mainClass,mainClass.basededados);
        mainClass.healthOrganizationMenu = new HealthOrganizationMenu(mainClass,mainClass.basededados,mainClass.stats);
        mainClass.administrationMenu = new AdministrationMenu(mainClass,mainClass.basededados);
        
        
        InputReader reader = new InputReader();
        int what = reader.getIntegerNumber("Quem");
        if (what == 1){
            mainClass.joao();
        }else{
            mainClass.idkeyz();
        }
    }

    public void joao(){
        
        
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
        this.basededados.registerUser(new User(200221030,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221031,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221032,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221033,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221034,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221035,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(200221036,UserState.CONTINUOUS,Cargos.Aluno));
        this.basededados.registerUser(new User(111111111,UserState.CONTINUOUS,Cargos.Professor));
        this.basededados.registerUser(new User(222222222,UserState.CONTINUOUS,Cargos.Professor));
        this.basededados.registerUser(new User(333333333,UserState.INFECTED,Cargos.Professor));
        this.stats.updateStatistics();
        this.mainMenu.enableMenu();
        
        
    }


    
    public void idkeyz() {
        /*User user1 = new User(111111111, UserState.INFECTED,Cargos.Aluno);

        user1.addGenerateID();

        user1.addGenerateID();
        
        System.out.println(user1.getDateOfChangedStatus());*/
        //joao();
        //HealthOrganization ho1 = new HealthOrganization();
        //ho1.fun();

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
        this.stats.updateStatistics();
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
