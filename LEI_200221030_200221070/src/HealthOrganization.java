import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Class da organização de saúde
 * @author João Gouveia e João Portas
 */
public class HealthOrganization {

    private String[] listOfCodesOfInfecteds;
    private Database baseDeDados;

    public HealthOrganization(Database basededados) {
        this.listOfCodesOfInfecteds =  new String[0];
        this.baseDeDados = basededados;
    }

    /**
     * Lista de todos os IDs gerados por utilizadores infetados
     * @return retorna as lista de IDs de user infetados
     */
    public String[] getListOfCodesOfInfdStrings() {
        return this.listOfCodesOfInfecteds;
    }

    
    public void checkUsersContacts() {
        User twoWayBuffer = null;
        String[] contacts;
        for (String buffer : this.listOfCodesOfInfecteds){
            if (twoWayBuffer == null){
                User userBuffer = this.baseDeDados.getUser(buffer);
                if (userBuffer == null) continue;
                for (String userBufferContact : userBuffer.getRecivedIDs()){
                    
                }
                twoWayBuffer = userBuffer;
            }else{
                if (twoWayBuffer.equals(this.baseDeDados.getUser(buffer))) continue;
                User userBuffer = this.baseDeDados.getUser(buffer);
                if (userBuffer == null) continue;
                for (String userBufferContact : userBuffer.getRecivedIDs()){
                    
                }
                twoWayBuffer = userBuffer;
            }
        }
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
