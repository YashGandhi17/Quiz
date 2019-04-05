/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Yash
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
    public static void main(String[] args) {
      try{
            
            Connection c=DriverManager.getConnection("jdbc:ucanaccess://D:\\yash\\Atom\\Quiz\\Quiz-master\\Quiz-master\\src\\quiz\\Quiz.accdb");
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("select * from questions order by rand() limit 10");
        }
        catch(SQLException ee){System.out.println(ee);}

       
    }
}
