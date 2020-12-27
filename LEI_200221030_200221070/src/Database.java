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
            if (userBuffer.getIndividualID() == individualID){
                return userBuffer;
            }
        }
        return null;
    }
    
    /**
     * 
     * Este método permite obter um utilizador da base de dados
     * @param generatedID String com Id anonimo representando o utilizador 
     * @return Retorna um Objeto do tipo User
     */
    public User getUser(String generatedID){
        for (User userBuffer : this.userData){
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
    
    public ErrorCode removeUserFromDB(int individualId){
        //TODO
        return ErrorCode.NoError;
    }
    
    public ErrorCode removeUserFromDB(String generatedId){
        //TODO
        return ErrorCode.NoError;
    }
    
    public int getNumberOfRegistredUsers(){
        return this.userData.length - 1;
    }
}
