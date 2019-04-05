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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Vraj
 */
public class Quiz {
    
    public static void main(String[] args) {
        int limit=10;
        int index=0;
        Scanner s=new Scanner(System.in);
        int ans;
        int score=0;
        Questions []question= new Questions[limit];
        try{
            Connection c=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Vraj\\Documents\\NetBeansProjects\\Quiz\\src\\quiz\\Quiz.accdb");
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("select * from questions order by rand() limit 10");
            while(rs.next()){
                question[index]=new Questions(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
                index++;
            }

        }
        catch(SQLException ee){
            System.out.println(ee);
        }
        for(int i=0;i<=question.length;i++){
          System.out.println("Q"+i+". "+question[i].question);
          System.out.println("1. "+question[i].option1);
          System.out.println("2. "+question[i].option2);
          System.out.println("3. "+question[i].option3);
          System.out.println("4. "+question[i].option4);

          System.out.println("Enter your answer in 1/2/3/4");
          ans=s.nextInt();

          if(ans==question[i].answer)
          {
            System.out.println("correct answer");
            score++;
          }
          else {
            System.out.println("Ooops!Wrong answer");
          }

        }
        System.out.println("Your Final Score Is:"+score);
    }
    
}
