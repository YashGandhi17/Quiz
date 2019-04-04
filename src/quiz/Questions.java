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
    public static void main(String[] args) {
       Scanner s=new Scanner(System.in);
       List<Questions> QuestionList=new ArrayList<Questions>();
       int ans;
       int Score;
      try{
            Connection c=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Vraj\\Documents\\NetBeansProjects\\Quiz\\src\\quiz\\Quiz.accdb");
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("select * from questions order by rand() limit 10");
        }
        catch(SQLException ee){System.out.println(ee);}

        while(rs.next()){
            QuestionList.add(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
        }
        for(Questions Q:QuestionList){
          System.out.println(Q.question);
          System.out.println(Q.option1);
          System.out.println(Q.option2);
          System.out.println(Q.option3);
          System.out.println(Q.option4);

          System.out.println("Enter your answer in 1/2/3/4");
          ans=s.nextInt();

          if(ans==Q.answer)
          {
            System.out.println("correct answer");
            Score++;
          }
          else {
            System.out.println("Ooops!Wrong answer");
          }

        }
        System.out.println("Your Final Score Is:"+Score);

    }
}
