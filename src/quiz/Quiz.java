package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Vraj,Yash
 */
public class Quiz extends Applet implements ItemListener,ActionListener{
    Checkbox op1, op2, op3,op4;
    CheckboxGroup cbg;
    Label Que,lab,Score;
    Button next;
    ArrayList<Questions> question=new ArrayList<Questions>();
    int score=0;
    int ans=0;
    int i=0;
    int answer = 0;
     public void init()
    {
    ActionEvent e = null;
    Scanner s=new Scanner(System.in);    
    cbg = new CheckboxGroup();
    op1 = new Checkbox("", cbg, false);
    op2 = new Checkbox("", cbg, false);
    op3 = new Checkbox("", cbg, false);
    op4 = new Checkbox("", cbg, false);
    Que = new Label("");
    next=new Button("Next");
    lab=new Label();
    Score=new Label("Score:"+score);
    
    try{
        Connection c;
        c = DriverManager.getConnection("jdbc:ucanaccess://D:\\yash\\Atom\\Quiz\\Quiz-master\\Quiz-master\\src\\quiz\\Quiz.accdb");
        Statement st=c.createStatement();
        ResultSet rs=st.executeQuery("select * from questions order by rand() limit 10");
        while(rs.next()){
            Questions Qs;
            Qs=new Questions(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
            question.add(Qs);
        }
        c.close();

    }
    catch(SQLException ee){
        System.out.println(ee);
    }
    
    setLayout(null);
    Que.setBounds(30,10,200,20);
    op1.setBounds(30,40,200,20);
    op2.setBounds(30,70,200,20);
    op3.setBounds(30,100,200,20);
    op4.setBounds(30,130,200,20);
    lab.setBounds(30,160,200,20);
    next.setBounds(125,160,100,20);
    Score.setBounds(275,180,100,20);
    
    
    op1.addItemListener(this);
    op2.addItemListener(this);
    op3.addItemListener(this);
    op4.addItemListener(this);
    
    
    add(Que);
    add(op1);
    add(op2);
    add(op3);
    add(op4);
    add(next);
    add(lab);
    add(Score);
    next.addActionListener(this);
  
        
    //System.out.println("Your Final Score Is:"+score);       
    Questions Q=question.get(i);
    Que.setText(Q.question);
    op1.setLabel(Q.option1);
    op2.setLabel(Q.option2);
    op3.setLabel(Q.option3);
    op4.setLabel(Q.option4);
    ans=Q.answer;
        
  
 }
     
  public void actionPerformed(ActionEvent e) {
     lab.setText("");
     i++;
    Questions Q=question.get(i);
    Que.setText(Q.question);
    op1.setLabel(Q.option1);
    op2.setLabel(Q.option2);
    op3.setLabel(Q.option3);
    op4.setLabel(Q.option4);
   
    ans=Q.answer;
    if(i==question.size())
     repaint();
    }
   @Override
   public void itemStateChanged(ItemEvent e)
  {
    if(op1.getState() == true)
      answer = 1;
    else if(op2.getState() == true)
      answer = 2;
    else if(op3.getState() == true)
      answer = 3;
    else if(op4.getState() == true)
      answer = 4;
    if(ans==answer)
    {
        lab.setText("Correct Answer");
        Score.setText("Score:"+(++score));
    }
    else{lab.setText("Ooops!Wrong Answer");}  
 }  
}
