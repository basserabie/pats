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
public class fetchPayments {
    private int StudentID, lessonID, cost, amountPaid, amountOwed;

    public fetchPayments(int StudentID, int lessonID, int cost, int amountPaid, int amountOwed) {
        this.StudentID = StudentID;
        this.lessonID = lessonID;
        this.cost = cost;
        this.amountPaid = amountPaid;
        this.amountOwed = amountOwed;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(int amountOwed) {
        this.amountOwed = amountOwed;
    }
    
    
}
