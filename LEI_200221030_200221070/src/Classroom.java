/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
    private Database db;
    private String classroomUUID;
    private String classroomName;
    private int registredUsers;
    private String dateOfClass;
    private ArrayList<User> classroomUsers;


    public Classroom(int colunas, int linhas, Database database, String classroomName){
        this.colunas = Math.max(colunas, 1);
        this.linhas = Math.max(linhas, 1);
        this.db = database;
        this.classroomUUID = RandomCodeGenerator.generateUniqueCode();
        this.classroomName = classroomName;
        this.registredUsers = 0;
        this.classroomUsers = new ArrayList<>(this.colunas * this.linhas);
    }
    
    public String getUUID(){
        return this.classroomUUID;
    }
    
    public String getName(){
        return this.classroomName;
    }
    
    public void addUserToClass(User userToRegister){
        if (this.classroomUsers.size() == this.getClassroomCapacity()) return;
        for (User buffer : this.classroomUsers){
            if (buffer == null) continue;
            if (buffer.getIndividualID() == userToRegister.getIndividualID()) return;
        }
        this.classroomUsers.add(userToRegister);
    }

    private int getIndexOnArray(User search){
        int c = 0;
        for (User buffer : this.classroomUsers){
            if (buffer.getIndividualID() == search.getIndividualID()) return c;
            c++;
        }
        return -1;
    }

    private int[] getCoords(int index){
        int y = (index / this.colunas);
        int x = (index-(y*this.colunas));
        return new int[]{x,y};
    }

    private int getIndexAtDistance(int distance,int directionX,int directionY,int[] originalCoords){
        int newY = (originalCoords[1] + ((distance / 2) * directionY));
        int newX = (originalCoords[0] + ((distance / 2) * directionX));
        if (newY < 0 || newX < 0) return -1;
        if (newY > this.linhas || newX > this.colunas) return -1;
        return (newY * this.colunas) + (newX);
    }


    public String[] getContacts(User student,int distance){
        int userPos = getIndexOnArray(student);
        int[] cords = getCoords(userPos);
        String[] contacts = new String[0];
        int dirY = 1;
        int dirX = 0;
        int index;
        User buffer;
        index = getIndexAtDistance(distance,dirX,dirY,cords);
        if (index != -1){
            contacts = grow(contacts);
            buffer = this.classroomUsers.get(index);
            contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
        }
        dirY = -1;
        index = getIndexAtDistance(distance,dirX,dirY,cords);
        if (index != -1){
            contacts = grow(contacts);
            buffer = this.classroomUsers.get(index);
            contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
        }
        dirX = 1;
        dirY = 0;
        index = getIndexAtDistance(distance,dirX,dirY,cords);
        if (index != -1){
            contacts = grow(contacts);
            buffer = this.classroomUsers.get(index);
            contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
        }
        dirX = -1;
        index = getIndexAtDistance(distance,dirX,dirY,cords);
        if (index != -1){
            contacts = grow(contacts);
            buffer = this.classroomUsers.get(index);
            contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
        }

        if (distance == 4){
            distance = distance/2;
            //Upper Right Quarter
            dirX = 1;
            dirY = -1;
            index = getIndexAtDistance(distance,dirX,dirY,cords);
            if (index != -1){
                contacts = grow(contacts);
                buffer = this.classroomUsers.get(index);
                contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
            }
            dirY = 1;
            index = getIndexAtDistance(distance,dirX,dirY,cords);
            if (index != -1){
                contacts = grow(contacts);
                buffer = this.classroomUsers.get(index);
                contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
            }
            dirX = -1;
            index = getIndexAtDistance(distance,dirX,dirY,cords);
            if (index != -1){
                contacts = grow(contacts);
                buffer = this.classroomUsers.get(index);
                contacts[contacts.length - 1] = buffer.getCurrentIDToCastString();
            }
            dirY = 1;
            index = getIndexAtDistance(distance,dirX,dirY,cords);
            if (index != -1){
                contacts = grow(contacts);
                buffer = this.classroomUsers.get(index);
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
                if (this.classroomUsers.get((this.y * this.colunas) + this.x) == null){
                    System.out.print("Ninguem, ");
                }else{
                    System.out.print(this.classroomUsers.get((this.y * this.colunas) + this.x).getCurrentIDToCastString() + ",");
                }
            }
        }
        System.out.println("\n]");
    }

}
