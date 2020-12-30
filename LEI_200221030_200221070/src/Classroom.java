/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joao2
 */
public class Classroom {
    
    private int colunas;
    private int linhas;
    private int x;
    private int y;
    private User[] mapa;
    private Database db;
    private String classroomUUID;
    private String classroomName;
    
    
    public Classroom(int colunas,int linhas,Database database,String classroomName){
        this.colunas = (colunas > 1)? colunas : 1;
        this.linhas = (linhas > 1)? linhas : 1;
        this.db = database;
        this.mapa = new User[this.colunas * this.linhas];
        this.classroomUUID = RandomCodeGenerator.generateUniqueCode();
        this.classroomName = classroomName;
    }
    
    public String getUUID(){
        return this.classroomUUID;
    }
    
    public String getName(){
        return this.classroomName;
    }
    
    public void addUserToClass(){
        //TODO
    }
    
    public void updateCapacity(int linhas,int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        this.mapa = new User[this.linhas * this.colunas];
    }
    
    public int getClassroomCapacity(){
        return this.linhas * this.colunas;
    }
    
    public int getLinhas(){
        return this.linhas;
    }
    
    public int getColunas(){
        return this.colunas;
    }
    
    //For testing
    public void drawMap(){
        System.out.println("[");
        for(this.y = 0; this.y < this.linhas; this.y++){
            for(this.x = 0; this.x < this.colunas; this.x++){
                if (this.mapa[(this.y * this.colunas) + this.x] == null){
                    System.out.print("Ninguem, ");
                }else{
                    System.out.print("Alguem, ");
                }
            }
            System.out.println("");
        }
        System.out.println("]");
    }
    
    //TODO change return type?
    public String[] getContacts(){
        return null;
    }
}
