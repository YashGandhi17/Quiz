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
 * @author Vraj,Yash
 */
public class Quiz {
    
    public static void main(String[] args) {
        int limit=10;
        int index=0;
        Scanner s=new Scanner(System.in);
        int ans;
        int score=0;
        ArrayList<Questions> question=new ArrayList<Questions>();
        try{
            Connection c;
            c = DriverManager.getConnection("jdbc:ucanaccess://D:\\yash\\Atom\\Quiz\\Quiz-master\\Quiz-master\\src\\quiz\\Quiz.accdb");
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("select * from questions order by rand() limit 10");
            while(rs.next()){
                Questions Qs;
                Qs=new Questions(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
                question.add(Qs);
                index++;
            }
            c.close();
        }
        catch(SQLException ee){
            System.out.println(ee);
        }
        for(Questions Q:question){
          System.out.println("Q"+". "+Q.question);
          System.out.println("1. "+Q.option1);
          System.out.println("2. "+Q.option2);
          System.out.println("3. "+Q.option3);
          System.out.println("4. "+Q.option4);

          System.out.println("Enter your answer in 1/2/3/4");
          ans=s.nextInt();

          if(ans==Q.answer)
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