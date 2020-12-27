import java.util.Arrays;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joao2
 */
public class TestDB {
    
    private User[] userData = new User[1];
    
    
    public TestDB(){
         
    }
    
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
    
    
    public User getUser(int individualID){
        for (User userBuffer : this.userData){
            if (userBuffer.getIndividualID() == individualID){
                return userBuffer;
            }
        }
        return null;
    }
    
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
    
    
    public ErrorCode updateUser(User userObject,int id){
        if (getUserIndex(id) != -1){
            this.userData[getUserIndex(id)] = userObject;
            return ErrorCode.NoError;
        }
        else{
            return ErrorCode.UserNotFound;
        }
    }
    
    
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
    
    
    public int getNumberOfRegistredUsers(){
        return this.userData.length - 1;
    }
}
