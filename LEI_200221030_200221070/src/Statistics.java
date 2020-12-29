/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joao2
 */
public class Statistics {
    
    private InputReader input;
    private int infectedNumber = 0;
    private int isolationNumber = 0;
    private int infectionsToday = 0;
    private int isolationsToday = 0;
    private int continuousNumber = 0;
    private Database database;
    
    /**
     * 
     */
    public Statistics(Database database){
        this.database = database;
        input = new InputReader();
    }
    
    
    public void updateStatistics(){
        this.infectionsToday = 0;
        this.isolationsToday = 0;
        for (User buffer : this.database.getAllUsers()){
            if (buffer == null) continue;
            switch (buffer.getStatus()){
                case INFECTED:
                    this.infectionsToday++;
                    this.infectedNumber++;
                    break;
                case ISOLATION:
                    this.isolationNumber++;
                    this.isolationsToday++;
                    break;
                case CONTINUOUS:
                    this.continuousNumber++;
                    break;
            }
        }
    }
    
    public void showStatistics(){
        printMenu("Bem Vindo ao Sistema de rastreio, Estatisticas (Atualizadas Diáriamente) ",new String[]{"Voltar"});
        this.input.getIntegerNumber("Opção");
    }
    
     private void printMenu(String title,String[] options){
        System.out.print('+');
        for (int a = 0; a < title.length() + 10;a++){
            System.out.print('-');
        }
        System.out.print("+\n");
        System.out.println("     " + title);
        System.out.println("                                          ");
        System.out.println("    Utilizadores Infectados:  " + this.infectedNumber);
        System.out.println("    Utilizadores Infectados Hoje:  " + this.infectionsToday);
        System.out.println("    Utilizadores Isolados:  " + this.isolationNumber);
        System.out.println("    Utilizadores Isolados Hoje:  " + this.isolationsToday);
        System.out.println("    Utilizadores Em estado Continuo:  " + this.continuousNumber);
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
    
}
