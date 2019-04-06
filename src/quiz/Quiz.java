package quiz;

import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Yash
 */
public class Quiz extends Applet implements ItemListener,ActionListener,Runnable{
    Checkbox op1, op2, op3,op4;
    CheckboxGroup cbg;
    Label Que,lab,lab1,Score,name;
    TextField Name;
    Button next;
    Thread t,t1;
    ArrayList<Questions> question=new ArrayList<Questions>();
    int score=0;
    int ans=0,time=30;
    int i=-1;
    int answer = 0;
     public void init()
    {
    Scanner s=new Scanner(System.in);    
    cbg = new CheckboxGroup();
    op1 = new Checkbox("", cbg, false);
    op2 = new Checkbox("", cbg, false);
    op3 = new Checkbox("", cbg, false);
    op4 = new Checkbox("", cbg, false);
    Que = new Label("");
    name=new Label("Name:");
    Name=new TextField();
    next=new Button("Start Quiz");
    lab=new Label();
    lab1=new Label("Time:"+time);
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
    name.setBounds(30,100,40,20);
    Name.setBounds(100,100,100,20);
    next.setBounds(125,160,100,20);
    
    add(name);
    add(Name);
    add(next);
    next.addActionListener(this);
    
    
 }
     
  public void actionPerformed(ActionEvent e) {
     lab.setText("");
     if(e.getActionCommand()=="Start Quiz")
    {fun2();repaint();}
    if(e.getActionCommand()=="Next")
    {repaint();}
    if(e.getActionCommand()=="Submit")
    {fun1();}
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
        if(i==question.size()+1)
            fun1();
        else
            repaint();
    }
    else{
        lab.setText("Ooops!Wrong Answer");
        if(i==question.size()+1)
            fun1();
        else
            repaint();
    }  
 }
   
  public void start(){
    t=new Thread(this);
    t.start();
}
  
  public void run(){
      t1=Thread.currentThread();
      while(t1==t){
          if(i==-1){}
          else if(time!=0 && i>=0){lab1.setText("Time:"+(--time));} 
          else if(time==0 && i==question.size()){fun1();}
          else {
              repaint();
          }
          try{
              t1.sleep(1000);
          }
          catch(InterruptedException e){}
      }
  }
   
  public void paint(Graphics g){
   if(i==0 || i==-1){
    Questions Q=question.get(question.size()-1);
    Que.setText(Q.question);
    op1.setLabel(Q.option1);
    op2.setLabel(Q.option2);
    op3.setLabel(Q.option3);
    op4.setLabel(Q.option4);
    ans=Q.answer;
    time=30;
    i++;
   }
   else{
    if(i==question.size())
        next.setLabel("Submit");
    Questions Q=question.get(i-1);
    Que.setText(Q.question);
    op1.setLabel(Q.option1);
    op2.setLabel(Q.option2);
    op3.setLabel(Q.option3);
    op4.setLabel(Q.option4);
    ans=Q.answer;
    time=30;
    i++;
   }
  }
  
  public void fun1(){
  setLayout(null);
  lab.setBounds(100,100,200,20);
  
  remove(Que);
  remove(op1);
  remove(op2);
  remove(op3);
  remove(op4);
  remove(next);
  remove(lab1);
  remove(Score);
  remove(name);
  
  lab.setText("Your Total Score is:"+score);
  try{
              Thread.sleep(5000);
              System.exit(0);
          }
          catch(InterruptedException e){}
  }
  
  public void fun2(){
    setLayout(null);
    Que.setBounds(30,30,500,20);
    op1.setBounds(30,55,200,20);
    op2.setBounds(30,80,200,20);
    op3.setBounds(30,105,200,20);
    op4.setBounds(30,130,200,20);
    lab.setBounds(30,155,200,20);
    next.setBounds(125,160,100,20);
    Score.setBounds(275,180,100,20);
    lab1.setBounds(275,10,100,20);
    name.setBounds(30,10,100,20);
    
    op1.addItemListener(this);
    op2.addItemListener(this);
    op3.addItemListener(this);
    op4.addItemListener(this);
    
    next.setLabel("Next");
    name.setText("Name:"+Name.getText());
    add(Que);
    add(op1);
    add(op2);
    add(op3);
    add(op4);
    add(next);
    add(lab);
    add(lab1);
    remove(Name);
    add(Score);
    next.addActionListener(this);
    add(name);
    i=0;
    
  }
}
