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
    
    private User[] userData = null;
    private Classroom[] classroomData = null;
    
    
    /**
     * Contrutor para a classe Database, esta não requere parametros
     */
    public Database(){
        if (this.userData == null){
            this.userData = new User[1];
            this.classroomData = new Classroom[1];
        }else{
            return;
        }
    }
    //<editor-fold defaultstate="collapsed" desc="User Section">
    
    /**
     * Este Método permite registar um novo utilizadar na base de dados
     * @param userToRegister Objeto relativo ao novo utilizador a registar
     * @return Eetorna um código de erro do tipo enum ErrorCode
     */
    public ErrorCode registerUser(User userToRegister){
        boolean duplicateFlag = false;
        if (this.userData.length > 1){
            for (User userBuffer : this.userData){
                if (userBuffer == null) continue;
                if (duplicateFlag){
                    continue;
                }
                if (userBuffer.getIndividualID() == userToRegister.getIndividualID()){
                    duplicateFlag = true;
                }
            }
            if (duplicateFlag){
                return ErrorCode.UserAlreadyRegistred;
            }
        }
        User[] buffer = Arrays.copyOf(this.userData,this.userData.length + 1);
        buffer[this.userData.length - 1] = userToRegister;
        this.userData = Arrays.copyOf(buffer, buffer.length);
        return ErrorCode.NoError;
    }
    
    /**
     * Este método permite obter um utilizador da base de dados
     * @param individualID Id de 9 digitos individual do utilizador
     * @return Retorna um Objeto do tipo User
     */
    public User getUser(int individualID){
        for (User userBuffer : this.userData){
            if (userBuffer == null) continue;
            if (userBuffer.getIndividualID() == individualID){
                return userBuffer;
            }
        }
        return null;
    }
    
    
    public User[] getAllUsers(){
        return this.userData;
    }
    
    /**
     *
     * Este método permite obter um utilizador da base de dados
     * @param generatedID String com Id anonimo representando o utilizador
     * @return Retorna um Objeto do tipo User
     */
    public User getUser(String generatedID){
        for (User userBuffer : this.userData){
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
            this.userData[getUserIndex(id)] = userObject;
            return ErrorCode.NoError;
        }
        else{
            return ErrorCode.UserNotFound;
        }
    }
    
    /**
     * Obtem um index no array da base de dados
     * @param id id de 9 digitos do utilizador
     * @return Retorna o index do utilizador
     */
    private int getUserIndex(int individualID){
        int c = 0;
        for (User userBuffer : this.userData){
            if (userBuffer == null) continue;
            if (userBuffer.getIndividualID() == individualID){
                return c;
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
            this.userData[index] = null;
            for (int a = index + 1; a < this.userData.length;a++){
                if (this.userData[a] == null) continue;
                this.userData[a -1] = this.userData[a];
            }
            User[] buffer = Arrays.copyOf(this.userData,this.userData.length - 2);
            this.userData = Arrays.copyOf(buffer, buffer.length);
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
            this.userData[index] = null;
            for (int a = index + 1; a < this.userData.length;a++){
                if (this.userData[a] == null) continue;
                this.userData[a -1] = this.userData[a];
            }
            User[] buffer = Arrays.copyOf(this.userData,this.userData.length - 1);
            this.userData = Arrays.copyOf(buffer, buffer.length);
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
        return this.userData.length - 1;
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
                
                if (buffer.getUUID() == newClassroomToRegister.getUUID()){
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
                if (classroomBuffer.getUUID() == classroomNameOrUUID){
                    return c;
                }
            }else{
                if (classroomBuffer.getName() == classroomNameOrUUID){
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
                if (buffer.getUUID() == classroomNameOrUUID){
                    return buffer;
                }
            }
            return null;
        }else{
            for (Classroom buffer : this.classroomData){
                if (buffer == null) continue;
                if (buffer.getName() == classroomNameOrUUID){
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
            return ErrorCode.UserNotFound;
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
