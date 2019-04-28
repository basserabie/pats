/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class paymentsArray {
    ConnectDB  db = new ConnectDB();
    dataValidation vd = new dataValidation();
    private ArrayList<fetchPayments> paymentArray = new ArrayList<>();

    public paymentsArray() {
        ResultSet r = db.getResults("SELECT * FROM sPayTable");
        try {
            while(r.next()) {
                fetchPayments fp = new fetchPayments(r.getInt("StudID"), r.getInt("lessonID"), 
                        r.getInt("cost"), r.getInt("amountPaid"), r.getInt("amountOwed"));
                paymentArray.add(fp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
    }

}
