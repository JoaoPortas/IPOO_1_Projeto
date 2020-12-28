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
    
    /**
     * Contrutor para a classe Database, esta não requere parametros
     */
    public Database(){
        if (this.userData == null){
            userData = new User[1];
        }else{
            return;
        }
    }
    
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
            
            User[] buffer = Arrays.copyOf(this.userData,this.userData.length - 1);
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
}
