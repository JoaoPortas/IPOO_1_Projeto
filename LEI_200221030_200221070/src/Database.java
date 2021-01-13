import java.util.ArrayList;
import java.util.Arrays;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class Database, esta classe é a representação da base de dados usado durante a execução do programa
 * @author Joao2
 */
public class Database {

    private ArrayList<User> userCollection;
    private Classroom[] classroomData;
    private Classroom[] aulas = null;
    
    /**
     * Contrutor para a classe Database, esta não requere parametros
     */
    public Database(){
        this.classroomData = new Classroom[1];
        this.userCollection = new ArrayList<>(0);
    }
    //<editor-fold defaultstate="collapsed" desc="User Section">
    
    /**
     * Este Método permite registar um novo utilizadar na base de dados
     * @param userToRegister Objeto relativo ao novo utilizador a registar
     * @return Eetorna um código de erro do tipo enum ErrorCode
     */
    public ErrorCode registerUser(User userToRegister){
        if (this.userCollection.size() > 1){
            for (User userBuffer : this.userCollection){
                if (userBuffer == null) continue;
                if (userBuffer.getIndividualID() == userToRegister.getIndividualID()) return ErrorCode.UserAlreadyRegistred;
            }
        }
        this.userCollection.add(userToRegister);
        return ErrorCode.NoError;
    }
    
    /**
     * Este método permite obter um utilizador da base de dados
     * @param individualID Id de 9 digitos individual do utilizador
     * @return Retorna um Objeto do tipo User
     */
    public User getUser(int individualID){
        for (User userBuffer : this.userCollection){
            if (userBuffer == null) continue;
            if (userBuffer.getIndividualID() == individualID){
                return userBuffer;
            }
        }
        return null;
    }
    
    
    public ArrayList<User> getAllUsers(){
        return this.userCollection;
    }
    
    /**
     *
     * Este método permite obter um utilizador da base de dados
     * @param generatedID String com Id anonimo representando o utilizador
     * @return Retorna um Objeto do tipo User
     */
    public User getUser(String generatedID){
        for (User userBuffer : this.userCollection){
            if (userBuffer == null) continue;
            for (String idBuffer : userBuffer.getGeneraredIDs()){
                if (idBuffer.equals(generatedID)){
                    return userBuffer;
                }
            }
        }
        return null;
    }
    
    /**
     * Este metódo permite atualizar o utilizador
     * @param userObject objecto do utilizador
     * @param id id de 9 digitos do utilizador
     * @return Eetorna um código de erro do tipo enum ErrorCode
     */
    public ErrorCode updateUser(User userObject,int id){
        if (getUserIndex(id) != -1){
            this.userCollection.set(getUserIndex(id),userObject);
            return ErrorCode.NoError;
        }
        else{
            return ErrorCode.UserNotFound;
        }

    }
    
    /**
     * Obtem um index no array da base de dados
     * @param individualID id de 9 digitos do utilizador
     * @return Retorna o index do utilizador
     */
    private int getUserIndex(int individualID){
        int c = 0;
        for (User userBuffer : this.userCollection){
            if (userBuffer == null) continue;
            if (userBuffer.getIndividualID() == individualID){
                return c;
            }
            c++;
        }
        return -1;
    }

    private int getUserIndex(String generatedId){
        int c = 0;
        for (User userBuffer : this.userCollection){
            if (userBuffer == null) continue;
            for (String stringBuffer : userBuffer.getGeneraredIDs()){
                if (stringBuffer.equals(generatedId)){
                    return c;
                }
            }
            c++;
        }
        return -1;
    }

    /**
     * Remove um utilizador da base de dados
     * @param individualId id de 9 digitos do utilizador
     * @return Eetorna um código de erro do tipo enum ErrorCode
     */
    public ErrorCode removeUserFromDB(int individualId){
        int index = getUserIndex(individualId);
        if (index != -1){
            this.userCollection.remove(getUserIndex(individualId));
        }
        else{
            return ErrorCode.UserNotFound;
        }
        return ErrorCode.NoError;
    }
    
    /**
     * Remove um utilizador da base de dados
     * @param generatedId String com Id anonimo representando o utilizador
     * @return Eetorna um código de erro do tipo enum ErrorCode
     */
    public ErrorCode removeUserFromDB(String generatedId){
        
        User user = this.getUser(generatedId);
        int index = getUserIndex(user.getIndividualID());
        if (index != -1){
            this.userCollection.remove(getUserIndex(generatedId));
        }
        else{
            return ErrorCode.UserNotFound;
        }
        return ErrorCode.NoError;
    }
    
    /**
     * Obtem o numero de utilizadores na base de dados
     * @return Retorna o numero de utilizadores na base de dados
     */
    public int getNumberOfRegistredUsers(){
        return this.userCollection.size();
    }
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Classroom Section">
    
    public ErrorCode registerClassroom(Classroom newClassroomToRegister){
        boolean duplicateFlag = false;
        
        if (this.classroomData.length > 1){
            for (Classroom buffer : this.classroomData){
                if (buffer == null) continue;
                if (duplicateFlag){
                    return ErrorCode.ClassroomAlreadyRegistred;
                }
                
                if (buffer.getUUID().equals(newClassroomToRegister.getUUID())){
                    duplicateFlag = true;
                }
            }
        }
        Classroom[] bufferArray = Arrays.copyOf(this.classroomData, this.classroomData.length + 1);
        bufferArray[this.classroomData.length - 1] = newClassroomToRegister;
        this.classroomData = Arrays.copyOf(bufferArray,bufferArray.length);
        return ErrorCode.NoError;
        
    }
     private int getClassroomIndex(String classroomNameOrUUID,boolean searchByUUID){
         
         
        int c = 0;
        for (Classroom classroomBuffer : this.classroomData){
            if (classroomBuffer == null) continue;
            if (searchByUUID){
                if (classroomBuffer.getUUID().equals(classroomNameOrUUID)){
                    return c;
                }
            }else{
                if (classroomBuffer.getName().equals(classroomNameOrUUID)){
                    return c;
                }
            }
            c++;
        }
        return -1;
    }
     
    public Classroom[] getAllClassrooms(){
        return this.classroomData;
    }
    
    public Classroom getClassroom(String classroomNameOrUUID, boolean searchByUUID){
        if (searchByUUID){
            for (Classroom buffer : this.classroomData){
                if (buffer == null) continue;
                if (buffer.getUUID().equals(classroomNameOrUUID)){
                    return buffer;
                }
            }
            return null;
        }else{
            for (Classroom buffer : this.classroomData){
                if (buffer == null) continue;
                if (buffer.getName().equals(classroomNameOrUUID)){
                    return buffer;
                }
            }
            return null;
        }
    }
    
    public ErrorCode removeClassroom(String classroomNameOrUUID, boolean searchByUUID){
        int index = getClassroomIndex(classroomNameOrUUID,searchByUUID);
        if (index != -1){
            this.classroomData[index] = null;

            for (int a = index + 1; a < this.classroomData.length;a++){
                if (this.classroomData[a] == null) continue;
                this.classroomData[a -1] = this.classroomData[a];
            }
            Classroom[] buffer = Arrays.copyOf(this.classroomData,this.classroomData.length - 2);
            this.classroomData = Arrays.copyOf(buffer, buffer.length);
        }
        else{
            return ErrorCode.ClassroomNotFound;
        }
        return ErrorCode.NoError;
    }
    
    public ErrorCode updateClass(Classroom newClassroomObject,String classroomNameOrUUID, boolean searchByUUID){
        int index = getClassroomIndex(classroomNameOrUUID,searchByUUID);
        if (index == -1) return ErrorCode.ClassroomNotFound;
        else{
            this.classroomData[index] = newClassroomObject;
        }
        return ErrorCode.NoError;
    }
    
    
    //</editor-fold>
}
