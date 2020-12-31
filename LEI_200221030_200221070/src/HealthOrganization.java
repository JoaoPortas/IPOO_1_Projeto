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

    public void addOnListOfInfecteds(String[] listOfGeneratedCodes) {
        String[] newListOfCodesOfInfecteds = Arrays.copyOf(this.listOfCodesOfInfecteds, this.listOfCodesOfInfecteds.length + 1);
        //newListOfCodesOfInfecteds[this.listOfCodesOfInfecteds.length] = this.currentIDToCast + " - " + formatedDate;

        //this.generatedIDs = Arrays.copyOf(newGeneratedIDs, newGeneratedIDs.length);
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
