import java.util.ArrayList;
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
    private Cargos cargo;
    private String dateOfChangedStatus;



    /**
     * 
     * @param individualID ID do individuo com 9 digitos (número de aluno ou número mecanográfico para docentes)
     * @param status Estado do individuo (continuo, isolamento ou infetado)
     */
    public User(int individualID, UserState status,Cargos cargo) {
        if (status != null && String.valueOf(individualID).length() == 9) {
            this.individualID = individualID;
            this.status = status;
            this.dateOfChangedStatus = (LocalDate.now()).format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
            this.generatedIDs = new String[0];
            this.recivedIDs =  new String[0];
            this.cargo = cargo;
        }
    }

    /**
     * Obtem a data em que o estado do utilizador foi alterado (continuo, isolamento ou infetado)
     * @return data da alteração do estado
     */
    public String getDateOfChangedStatus() {
        return dateOfChangedStatus;
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

    public Cargos getCargo(){
        return this.cargo;
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
     * Define a data da troca do estado (continuo, isolamento ou infetado) para a data atual
     */
    public void setDateOfChangedStatus() {
        this.dateOfChangedStatus = (LocalDate.now()).format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
    }

    /**
     * Altera a data de troca de estado (continuo, isolamento ou infetado) para uma data introduzida
     * @param dateOfChangedStatus data para a qual vai ser alterada
     */
    public void setDateOfChangedStatus(LocalDate dateOfChangedStatus) {
        this.dateOfChangedStatus = (dateOfChangedStatus).format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
    }

    /**
     * 
     * @param individualID Recebe o número do inidividuo (numero de aluno ou mecanográfico para docentes) de 9 digitos
     */
    public void setIndividualID(int individualID) {
        if (String.valueOf(individualID).length() == 9) {
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
                    setDateOfChangedStatus();
                    break;
                case ISOLATION:
                    setStatus(UserState.ISOLATION);
                    setDateOfChangedStatus();
                    break;
                case INFECTED:
                    setStatus(UserState.INFECTED);
                    setDateOfChangedStatus();
                    break;
            }
        }
    }

    /**
     * Gera e adiciona um novo ID e adiciona á lista de IDs gerados
     * 
     */
    public void addGenerateID() {
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String formatedDate = nowDate.format(formatter);

        /*if (this.generatedIDs.length == 0) {
            formatedDate = (nowDate.minusDays(20)).format(formatter);
        } else {
            formatedDate = nowDate.format(formatter);
        }*/

        String[] newGeneratedIDs = Arrays.copyOf(this.generatedIDs, this.generatedIDs.length + 1);
        this.currentIDToCast = RandomCodeGenerator.generateUniqueCode();
        newGeneratedIDs[this.generatedIDs.length] = this.currentIDToCast + " - " + formatedDate;

        this.generatedIDs = Arrays.copyOf(newGeneratedIDs, newGeneratedIDs.length);
        
        
    }

    /**
     * Elimina os IDs gerados com mais de 28 dias
     */
    public void deleteOldGeneretedIDs() {
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        String[] newIDsList;
        int lengthOfNewArray = 0;

        for (int i = 0; i < this.generatedIDs.length; i++) {
            if (nowDate.isAfter((LocalDate.parse(this.generatedIDs[i].substring(39, 49), formatter)).plusDays(28))) {
                this.generatedIDs[i] = null;
            }else {
                lengthOfNewArray++;
            }
        }

        newIDsList = new String[lengthOfNewArray];

        for (int i = 0, j = 0; i < this.generatedIDs.length; i++) {
            if (this.generatedIDs[i] != null) {
                newIDsList[j] = this.generatedIDs[i];
                j++;
            }
        }

        this.generatedIDs = Arrays.copyOf(newIDsList, newIDsList.length);
    }

    public void deleteOldRecivedIDs() {
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        String[] newIDsList;
        int lengthOfNewArray = 0;

        for (int i = 0; i < this.recivedIDs.length; i++) {
            if (nowDate.isAfter((LocalDate.parse(this.recivedIDs[i].substring(39, 49), formatter)).plusDays(28))) {
                this.recivedIDs[i] = null;
            }else {
                lengthOfNewArray++;
            }
        }

        newIDsList = new String[lengthOfNewArray];

        for (int i = 0, j = 0; i < this.recivedIDs.length; i++) {
            if (this.recivedIDs[i] != null) {
                newIDsList[j] = this.recivedIDs[i];
                j++;
            }
        }

        this.recivedIDs = Arrays.copyOf(newIDsList, newIDsList.length);
    }

    /**
     * Elimina os IDs gerados e recebidos com mais de 28 dias
     */
    public void deleteOldIDs() {
        deleteOldGeneretedIDs();
        deleteOldRecivedIDs();
    }
    
    
    
    public void reciveID(String id,int distance){
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String formatedDate = nowDate.format(formatter);
        
        String[] buffer = Arrays.copyOf(this.recivedIDs, this.recivedIDs.length + 1);
        buffer[this.recivedIDs.length] = id + " - " + formatedDate;
        this.recivedIDs = Arrays.copyOf(buffer,buffer.length);
    }
}
