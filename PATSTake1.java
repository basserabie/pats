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
public class PATSTake1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnectDB db = new ConnectDB();
        new loginSignup().setVisible(true);
    }
}
