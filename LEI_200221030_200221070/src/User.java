import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.*;

//import java.Date
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author João Gouveia e João Portas
 */
public class User {
    
    private int individualID;
    private UserState status;
    private String[] generatedIDs;
    private String[] recivedIDs;
    private String currentIDToCast;

    /**
     * 
     * @param individualID ID do individuo com 9 digitos (número de aluno ou número mecanográfico para docentes)
     * @param status Estado do individuo (continuo, isolamento ou infetado)
     */
    public User(int individualID, UserState status) {
        if (individualID == (int)individualID && status != null && String.valueOf(individualID).length() == 9) {
            this.individualID = individualID;
            this.status = status;
            this.generatedIDs = new String[0];
        }
    }

    /**
     * 
     * @return Devolve o ID do individuo sendo ele o número de aluno ou o número mecanográfico para docentes
     */
    public int getIndividualID() {
        return individualID;
    }

    /**
     * 
     * @return Devolve o estado atual do utilizador (continuo, isolamento ou infetado)
     */
    public UserState getStatus() {
        return status;
    }

    /**
     * 
     * @return Devolve o array de IDs a transmitir gerados para os outros utilizadores
     */
    public String[] getGeneraredIDs() {
        return generatedIDs;
    }

    /**
     * 
     * @return Retorna os IDs recebidos de outros utilizadores
     */
    public String[] getRecivedIDs() {
        return recivedIDs;
    }

    /**
     * 
     * @return Retorna o ID atual que é enviado para outros utilizadores
     */
    public String getCurrentIDToCastString() {
        return currentIDToCast;
    }

    /**
     * 
     * @param individualID Recebe o número do inidividuo (numero de aluno ou mecanográfico para docentes) de 9 digitos
     */
    public void setIndividualID(int individualID) {
        if (individualID == (int)individualID && String.valueOf(individualID).length() == 9) {
            this.individualID = individualID;
        }
    }

    /**
     * 
     * @param status Recebe o estado do inividuo 
     */
    public void setStatus(UserState status) {
        if (status != null) {
            this.status = status;
        }
    }

    /**
     * Altera o estado do inividuo para continuo, isolamento ou infetado
     * @param status Recebe o estado do indivíduo para o qual vai ser alterado
     */
    public void changeUserStatus(UserState status) {
        if (status != null) {
            switch (status) {
                case CONTINUOUS:
                    setStatus(UserState.CONTINUOUS);
                    break;
                case ISOLATION:
                    setStatus(UserState.ISOLATION);
                    break;
                case INFECTED:
                    setStatus(UserState.INFECTED);
                    break;
            }
        }
    }

    public void addGenerateID(String generatedID) {
        if (generatedID != null) {
            LocalDate nowDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String formatedDate = nowDate.format(formatter);
            String[] newGeneratedIDs = Arrays.copyOf(this.generatedIDs, this.generatedIDs.length + 1);

            newGeneratedIDs[this.generatedIDs.length] = generatedID + " - " + formatedDate;

            this.generatedIDs = Arrays.copyOf(newGeneratedIDs, newGeneratedIDs.length);

            System.out.println(this.generatedIDs[this.generatedIDs.length - 1]);
        }
    }
    
}
