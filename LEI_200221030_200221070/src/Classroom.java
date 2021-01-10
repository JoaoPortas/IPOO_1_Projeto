/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;

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
    private int registredUsers;
    
    public Classroom(int colunas, int linhas, Database database, String classroomName){
        this.colunas = (colunas > 1)? colunas : 1;
        this.linhas = (linhas > 1)? linhas : 1;
        this.db = database;
        this.mapa = new User[this.colunas * this.linhas];
        this.classroomUUID = RandomCodeGenerator.generateUniqueCode();
        this.classroomName = classroomName;
        this.registredUsers = 0;
    }
    
    public String getUUID(){
        return this.classroomUUID;
    }
    
    public String getName(){
        return this.classroomName;
    }
    
    public void addUserToClass(User userToRegister){
        if (this.registredUsers == this.mapa.length) return;
        for (User buffer : this.mapa){
            if (buffer == null) continue;
            if (buffer.getIndividualID() == userToRegister.getIndividualID()) return;
        }
        this.mapa[registredUsers] = userToRegister;
        this.registredUsers++;
    }

    private int getIndexOnArray(User search){
        int c = 0;
        for (User buffer : this.mapa){
            if (buffer.getIndividualID() == search.getIndividualID()) return c;
            c++;
        }
        return -1;
    }

    private int[] getCoords(int index){
        int y = (int)(index / this.colunas);
        int x = (index-(y*this.colunas));
        return new int[]{x,y};
    }

    public String[] getContacts(User student,int distance){
        int disX = 0;
        int disY = distance;
        int userPos = getIndexOnArray(student);
        int[] coords = getCoords(userPos);
        String[] contacts = new String[0];
        int dirY = 1;
        int dirX = 0;
        //Vertical Checks
        int index;
        User buffer;
        if ((coords[1] + (disY/2) * dirY) > -1 && (coords[1] + (disY/2) * dirY) < this.linhas) {
            contacts = grow(contacts);
            index = ((coords[1] + (disY/2) * dirY) * this.colunas + coords[0]);
            buffer = mapa[index];
            contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
            contacts = grow(contacts);
        }
        
        
        dirY = -1;
            if ((coords[1] + (disY/2) * dirY) > -1  && (coords[1] + (disY/2) * dirY) < this.linhas) {
            index = ((coords[1] + (disY/2) * dirY) * this.colunas + coords[0]);
            buffer = mapa[index];
            contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
            contacts = grow(contacts);
        }
        dirY = 0;
        disY = 0;
        disX = distance;
        dirX = 1;
        
        if ((coords[0] + ((disX/2)  * dirX)) > -1 && (coords[0] + ((disX/2)  * dirX)) < this.colunas){
            index = (coords[1] * this.colunas + (coords[0] + ((disX/2)  * dirX)));
            buffer = mapa[index];
            contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
            contacts = grow(contacts);
        }
        
        
        dirX = -1;
                
        if ((coords[0] + ((disX/2)  * dirX)) > -1 && (coords[0] + ((disX/2)  * dirX)) < this.colunas){
            index = (coords[1] * this.colunas + (coords[0] + ((disX/2)  * dirX)));
            buffer = mapa[index];
            contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
            contacts = grow(contacts);
        }
        //Diagnal Checks
        if (distance == 4){
            int finalDDir = distance/2;
            //Upper Right Quarter
            dirX = 1;
            dirY = -1;
            if ((coords[1] + ((finalDDir / 2) * dirY)) > -1 && (coords[1] + ((finalDDir / 2) * dirY)) < this.linhas && (coords[0] + ((finalDDir / 2) * dirX)) > -1 && (coords[0] + ((finalDDir / 2) * dirX)) < this.colunas){
               
                index = (coords[1] + ((finalDDir / 2) * dirY)) * this.colunas + (coords[0] + ((finalDDir / 2) * dirX));
                buffer = mapa[index];
                contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
                contacts = grow(contacts);
            }
            dirX = 1;
            dirY = 1;
            if ((coords[1] + ((finalDDir / 2) * dirY)) > -1 && (coords[1] + ((finalDDir / 2) * dirY)) < this.linhas && (coords[0] + ((finalDDir / 2) * dirX)) > -1 && (coords[0] + ((finalDDir / 2) * dirX)) < this.colunas){
                index = (coords[1] + ((finalDDir / 2) * dirY)) * this.colunas + (coords[0] + ((finalDDir / 2) * dirX));
                buffer = mapa[index];
                contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
                contacts = grow(contacts);
            }
            dirX = -1;
            dirY = 1;
            if ((coords[1] + ((finalDDir / 2) * dirY)) > -1 && (coords[1] + ((finalDDir / 2) * dirY)) < this.linhas && (coords[0] + ((finalDDir / 2) * dirX)) > -1 && (coords[0] + ((finalDDir / 2) * dirX)) < this.colunas){
                index = (coords[1] + ((finalDDir / 2) * dirY)) * this.colunas + (coords[0] + ((finalDDir / 2) * dirX));
                buffer = mapa[index];
                contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
                contacts = grow(contacts);
            }
            dirX = -1;
            dirY = -1;
            if ((coords[1] + ((finalDDir / 2) * dirY)) > -1 && (coords[1] + ((finalDDir / 2) * dirY)) < this.linhas && (coords[0] + ((finalDDir / 2) * dirX)) > -1 && (coords[0] + ((finalDDir / 2) * dirX)) < this.colunas){
                index = (coords[1] + ((finalDDir / 2) * dirY)) * this.colunas + (coords[0] + ((finalDDir / 2) * dirX));
                buffer = mapa[index];
                contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
            }
        }
        if (contacts[contacts.length - 1] == null){
            contacts = removeSpaceFromArray(contacts);
        }
        
        return contacts;
    }

    
    private String[] grow(String[] target){
        return Arrays.copyOf(target,target.length + 1);
    }
    
    private String[] removeSpaceFromArray(String[] target){
        return Arrays.copyOf(target,target.length - 1);
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
                    System.out.print(this.mapa[(this.y * this.colunas) + this.x].getCurrentIDToCastString() + ",");
                }
            }
            System.out.println("");
        }
        System.out.println("]");
    }

}
