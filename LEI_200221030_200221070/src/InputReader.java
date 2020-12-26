/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
/* InputReader reads typed number or text input from the standard text terminal. 
 * The text or number typed by a user is returned.
 * 
 * @author    IPOO
 * @version    06/11/2016
 */
public class InputReader {

    private Scanner reader;

    public InputReader() {
        reader = new Scanner(System.in);
    }
    
    public void nextLine(){
        this.reader.nextLine();
    }
    
    private void showFormattedQuestion(String question) {
        if(question == null) {
            question = "";
        }
        question += "> ";       
        System.out.print(question);
    }

    public double getRealNumber(String question) {
        showFormattedQuestion(question);

        while (!reader.hasNextDouble()) {
            reader.nextLine();
            showFormattedQuestion(question);
        }
        double number = reader.nextDouble();
        reader.nextLine();  
        return number;
    }

    public int getIntegerNumber(String question) {
        showFormattedQuestion(question);

        while (!reader.hasNextInt()) {
            reader.nextLine();
           showFormattedQuestion(question);
        }
        int number = reader.nextInt();
        reader.nextLine();  
        return number;
    }

    public String getText(String  question) {
        showFormattedQuestion(question);

        return reader.nextLine();
    }
}
