/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;
import java.sql.*;
/**
 *
 * @author Vraj
 */    
public class Questions{
    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    int answer;
    Questions(String question,String option1,String option2,String option3,String option4,int answer){
        this.question=question;
        this.option1=option1;
        this.option2=option2;
        this.option3=option3;
        this.option4=option4;
        this.answer=answer;
    }
   /* public static void main(String[] args) {
       System.out.println("Hello"); 
        try{
            Connection c=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Vraj\\Documents\\NetBeansProjects\\Quiz\\src\\quiz\\Quiz.accdb");
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("select * from questions");
            while(rs.next()){
                System.out.println(rs.getString(1) + rs.getString(2)+"Hello");
            }

        }
        catch(SQLException ee){System.out.println(ee);}

    }*/
}
    
