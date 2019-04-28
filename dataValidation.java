/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

/**
 *
 * @author YishaiBasserabie
 */
public class dataValidation {// contains validating methods
    private String problems;

    public String getProblems() {
        return problems;
    }
    
    public boolean checkName(String input, String ForL) {//checks if a name string is valid
        boolean ok = true;//creates variable that holds value of valid/non-valid
        problems = "";
        if (!input.equals("")) {//checks if something is entered
            for (int i = 0; i < input.length(); i++) {//loops through inputted string
            if (!Character.isAlphabetic(input.charAt(i))) {//checks if character at i is alphabetic
                if (ForL.equals("f")) {
                    problems += "it seems you have an invalid character in your first Name\n";
                } else {
                    problems += "it seems you have an invalid character in your last name\n";
                }
                ok = !ok;//flips value of ok to false
            }
        }
      } else {
            if (ForL.equals("f")) {
                problems += "oh no you have left first name plank\n";
            } else {
                problems += "oh no you have last name plank\n";
            }
            ok = !ok;//flips ok to false
        }
        return ok;//returns ok
    }
    
    public boolean checkNum(String inputString, int start, int end) {//checks if a number is valid
        boolean ok = true;//creates variable that holds value of valid/non-valid
        if (!inputString.equals("")) {//checks if somethng is entered
            int input = Integer.parseInt(inputString);//converts string imputString to int input
            if (input >= start && input <= end) {// checks if input is between parametres
                ok = !ok;//flips value of ok to false
            }
        } else {
            ok = !ok;//flips ok to false
        }
        return ok;//returns ok
    }
    
    public boolean checkCell(String cell) {
        boolean ok = true;
        problems = "";
        if (cell.equals("")) {
            problems += "you have left cell blank!\n";
            ok = !ok;
        } else {
            if (cell.length() != 10) {
                problems += "the cell number you have entered is of invalid length!\n";
                ok = false;
            }
        }
        return ok;
    }
    
    public boolean checkEmail(String input) {//validates inputted email
        boolean ok = true;//creates variable that holds value of valid/non-valid
        problems = "";
        if (!input.equals("")) {//checks if something was entered
            if (!input.contains("@") || !input.contains(".")) {
                ok = false;//flips ok to false
                problems += "email invalid please try again dude!\n";
            }
        } else {
            ok = false;//flips ok to false
            problems += "you have left email blank\n";
        }
        return ok;//returns ok
    }
    
    public boolean checkPassword(String password, String confirmPassword) {
        boolean ok = true;
        problems = "";
        if (!password.equals("")) {
            if (password.length() < 8) {
                ok = false;
                problems += "password must be at least 8 charachters!\n";
            }
        } else {
            ok = false;
            problems += "oy vey! you have left the password field blank\n";
        }
        if (!password.equals(confirmPassword)) {
            ok = false;
            problems += "password do not match\n";
        }
        return ok;
    }
    
    public boolean checkBlank(String input) {
        boolean ok = true;
        if (input.equals("")) {
            ok = !ok;
        }
        return ok;
    }
    
}
