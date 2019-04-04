/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vraj
 */
public class Quiz {
    
    public static void main(String[] args) {
        int limit=10;
        int i=0;
        Questions []question= new Questions[limit];
        try{
            Connection c=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Vraj\\Documents\\NetBeansProjects\\Quiz\\src\\quiz\\Quiz.accdb");
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("select * from questions order by rand() limit 1");
            while(rs.next()){
                question[i]=new Questions(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
                System.out.println(rs.getString(1) + rs.getString(2));
                i++;
            }

        }
        catch(SQLException ee){
            System.out.println(ee);
        }
        System.out.println(question[0].question + question[0].option1 +question[0].option2+question[0].option3+question[0].option4+question[0].answer);
    }
    
}
