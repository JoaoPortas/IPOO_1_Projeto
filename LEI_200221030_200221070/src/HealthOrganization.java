import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Class da organização de saúde
 * @author João Gouveia e João Portas
 */
public class HealthOrganization {

    private String[] listOfCodesOfInfecteds;
    private Database baseDeDados;
    private Statistics stats;
    
    public HealthOrganization(Database basededados, Statistics stats) {
        this.listOfCodesOfInfecteds =  new String[0];
        this.baseDeDados = basededados;
        this.stats = stats;
    }

    /**
     * Lista de todos os IDs gerados por utilizadores infetados
     * @return retorna as lista de IDs de user infetados
     */
    public String[] getListOfCodesOfInfdStrings() {
        return this.listOfCodesOfInfecteds;
    }

    private void getContactsOfInfectedUser(String[] codes) {
        User[] twoWayBuffer = new User[0];
        
        for (String buffer : codes) {
            User userBuffer = this.baseDeDados.getUser(buffer);
            if (!userBuffer.getStatus().equals(UserState.ISOLATION) && !userBuffer.getStatus().equals(UserState.INFECTED)) {
                if (!Arrays.stream(twoWayBuffer).anyMatch(userBuffer::equals)) {
                    /**Apagar */
                    UserState j = userBuffer.getStatus();
                    /**APAGAR */
                    userBuffer.setStatus(UserState.ISOLATION);
                    userBuffer.setDateOfChangedStatus();
                    this.baseDeDados.updateUser(userBuffer,userBuffer.getIndividualID());
                    this.stats.addIsolation();
                    twoWayBuffer = Arrays.copyOf(twoWayBuffer, twoWayBuffer.length + 1);
                    twoWayBuffer[twoWayBuffer.length - 1] = userBuffer;
                    /**APAGAR */
                    System.out.println("TESTE:");
                    System.out.println(userBuffer.getIndividualID() + " foi alterado do estado " + j.toString() + " para " + userBuffer.getStatus().toString());
                    /**APAGAR */
                }
            }
        }
    }

    public void checkUsersContacts() {
        if (this.listOfCodesOfInfecteds.length == 0) {this.stats.zeroOutDaily();return;}
        User twoWayBuffer = null;
        String[] contacts;
        for (String buffer : this.listOfCodesOfInfecteds){
            if (twoWayBuffer == null){
                User userBuffer = this.baseDeDados.getUser(buffer);
                if (userBuffer == null) continue;
                getContactsOfInfectedUser(userBuffer.getRecivedIDs());
                twoWayBuffer = userBuffer;
            }else{
                if (twoWayBuffer.equals(this.baseDeDados.getUser(buffer))) continue;
                User userBuffer = this.baseDeDados.getUser(buffer);
                if (userBuffer == null) continue;
                for (String userBufferContact : userBuffer.getRecivedIDs()){
                getContactsOfInfectedUser(userBuffer.getRecivedIDs());
                }
                twoWayBuffer = userBuffer;
            }
        }
    }
    
    public void sendInfectedToDb(){
        if (this.listOfCodesOfInfecteds.length == 0){ this.stats.zeroOutDaily();return;}
        User[] array = new User[0];
        for (String buffer : this.listOfCodesOfInfecteds){
            User userBuffer = this.baseDeDados.getUser(buffer);
            if (!Arrays.stream(array).anyMatch(userBuffer::equals)){
                array = Arrays.copyOf(array, array.length + 1);
                array[array.length - 1] = userBuffer;
                userBuffer.setStatus(UserState.INFECTED);
                userBuffer.setDateOfChangedStatus();
                this.baseDeDados.updateUser(userBuffer, userBuffer.getIndividualID());
                this.stats.addInfection();
            }
        }
        this.listOfCodesOfInfecteds = new String[0];
    }

    /**
     * Adiciona os IDs gerados pelos utilizadores infetados á lista de IDs de utilizadores infetados da  organização de saúde
     */
    public void addOnListOfInfecteds(String[] codesOfInfectedUser) {
        int lenghtOfUserGeneratedCodes = codesOfInfectedUser.length;
        String[] concatOfArrays = new String[lenghtOfUserGeneratedCodes + this.listOfCodesOfInfecteds.length];
        System.arraycopy(this.listOfCodesOfInfecteds, 0, concatOfArrays, 0, this.listOfCodesOfInfecteds.length);
        System.arraycopy(codesOfInfectedUser, 0, concatOfArrays, this.listOfCodesOfInfecteds.length, codesOfInfectedUser.length);
        this.listOfCodesOfInfecteds = Arrays.copyOf(concatOfArrays, concatOfArrays.length);

        /*int lenghtOfUserGeneratedCodes;

        for (User cache : this.baseDeDados.getAllUsers()) {
            if (cache != null) {
                if (cache.getStatus() == UserState.INFECTED) {
                    lenghtOfUserGeneratedCodes = cache.getGeneraredIDs().length;
                    String[] concatOfArrays = new String[lenghtOfUserGeneratedCodes + this.listOfCodesOfInfecteds.length];
                    System.arraycopy(this.listOfCodesOfInfecteds, 0, concatOfArrays, 0, this.listOfCodesOfInfecteds.length);
                    System.arraycopy(cache.getGeneraredIDs(), 0, concatOfArrays, this.listOfCodesOfInfecteds.length, cache.getGeneraredIDs().length);

                    //for (int i = 0; i < concatOfArrays.length; i++) {
                      //  System.out.print( concatOfArrays[i] + ", " );
                    //}

                    this.listOfCodesOfInfecteds = Arrays.copyOf(concatOfArrays, concatOfArrays.length);
                }
            }    
        }*/
    }

    public void fun() {
        System.out.println("fun()");

            System.out.println(this.baseDeDados.getAllUsers().length);
            for (User cache : this.baseDeDados.getAllUsers()) {
                if (cache != null) {
                    if (cache.getStatus() == UserState.INFECTED) {
                        System.out.println("HealthOrganization.fun()");
                    }
                }    
            }

    }
}
