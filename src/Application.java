
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Random;
import java.lang.*;

public class Application {
	
	

	public static void main(String[] args) {
		
		
		Random rand = new Random();
		
		String[] names = new String[]{"kishan", "pras", "vicky", "vineeth"};
		String[] emails = new String[]{"harden@gmail.com", "jordan@gmail.com", 
									  "curry@gmail.com", "james@gmail.com"};
		String[] questions = new String[]{"How to code?", "How to test?", 
				  "How not to fail?", "How to pass?"};
		
		//Random selection of number of students
		int n = rand.nextInt(5);
		
		Student [] students = new Student[n];
		
		for(int i =0; i<n;i++) {
			//random selection name
			int randName = rand.nextInt(4);
			
			//random selection email
			int randEmail = rand.nextInt(4);
			
			//Create student
			students[i] = new Student(names[randName], emails[randEmail]);
		}
		
		Appoinment [] appoinments = new Appoinment[n];
		for(int i =0; i<n; i++) {
			
			Calendar calendar = Calendar.getInstance();
			//Random selection either 5 mins behind time or 11 mins behind time
			int[] mins = {5, 11};
			int rnd = new Random().nextInt(mins.length);
			calendar.add(Calendar.MINUTE, -mins[rnd]);
			
			//appointment creation
			appoinments[i] = new Appoinment(students[i], questions[i], calendar.getTime());
			
		}
		
		
		//Adding the appointments to the queue
		AppoinmentHandler appoinmentQueue =new AppoinmentHandler(appoinments);
		
		
		//Ta views this to mark if the student is absent or present
		JFrame f=new JFrame("Appoinment");
	    JButton present=new JButton("Present");
	    JButton absent=new JButton("Absent");
	    JPanel displayPanel = new JPanel();
	    displayPanel.setBounds(20,80,300,100);   
	    JLabel l1,l2,l3,l4,l5,l6;
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	    
	    l3 = new JLabel("Student Email: ");
	    l4 = new JLabel("Student Question: ");
	    l5 = new JLabel("Appoinment Time: ");
	    
	    
	    //Data for inital frame
	    if(appoinmentQueue.Appoinments.isEmpty()) {
	    	l1=new JLabel("No appoinments to display !!");
	    	l2=new JLabel("");
	    	l6=new JLabel("");
	    	l4.setText("");
	    	l3.setText("");
	    	l5.setText("");
	    	present.setEnabled(false);
	    	absent.setEnabled(false);
	    }
	    else {
		    l1=new JLabel(appoinmentQueue.Appoinments.peek().getStudent().getEmail());    
		    l2=new JLabel(appoinmentQueue.Appoinments.peek().getQuestion());
		    l6=new JLabel( dateFormat.format(appoinmentQueue.Appoinments.peek().getDate())); 
	    } 
	    
	    l1.setBounds(128, 28, 86, 20);
	    l2.setBounds(0,100, 100,30);
	    
	    displayPanel.add(l3);
	    displayPanel.add(l1);
	    displayPanel.add(l4);
	    displayPanel.add(l2);
	    displayPanel.add(l5);
	    displayPanel.add(l6);
	    
	    f.add(displayPanel);    
	    f.setSize(400,400);
	    
	    present.setBounds(50,200,95,30);
	    
	    
	    //Frame to display the current Queue
	    JFrame f2 = new JFrame("Appoinment Queue");
	    f2.setVisible(true);
	    ArrayList<String> data = new ArrayList<String>();
		for(Appoinment a : appoinmentQueue.Appoinments) { 
			data.add(a.getStudent().getEmail()+" : "+a.getQuestion()+" ");
		}
		
		JList list = new JList(data.toArray());
        JPanel innerPanel = new JPanel();
        innerPanel.add(list);
        innerPanel.setBounds(20,80,300,100); 
        f2.add(innerPanel);    
	    f2.setSize(400,400);
	    
	    
	    //Action on clicking present
	    present.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    			appoinmentQueue.Appoinments.remove();
	    			displayPanel.removeAll();
	    			
	    			//If queue is empty
	    		    if(appoinmentQueue.Appoinments.isEmpty()) {
	    		    	l1.setText("No appoinments to display !!");
	    		    	l2.setText("");
	    		    	l6.setText("");
	    		    	present.setEnabled(false);
	    		    	absent.setEnabled(false);
		    		    displayPanel.add(l1);
	    		    }
	    		  //If queue not empty
	    		    else {
	    			    l3.setText("Student Email: ");
	    			    l4.setText("Student Question: ");
	    			    l1.setText(appoinmentQueue.Appoinments.peek().getStudent().getEmail());    
	    			    l2.setText(appoinmentQueue.Appoinments.peek().getQuestion());
	    			    l5.setText("Appoinment Time: ");
	    			    l6.setText(dateFormat.format(appoinmentQueue.Appoinments.peek().getDate())); 
	    			    displayPanel.add(l3);
	    			    displayPanel.add(l1);
	    			    displayPanel.add(l4);
	    			    displayPanel.add(l2);
	    			    displayPanel.add(l5);
	    			    displayPanel.add(l6);
	    		    }
	    			displayPanel.revalidate();displayPanel.repaint();
	    			
	    		 	ArrayList<String> data = new ArrayList<String>();
	    			for(Appoinment a : appoinmentQueue.Appoinments) { 
	    				data.add(a.getStudent().getEmail()+" : "+a.getQuestion()+" ");
	    			}
	    			JList list = new JList(data.toArray());
	    			innerPanel.removeAll();
	    			innerPanel.add(list);
	    			innerPanel.revalidate();innerPanel.repaint();
	    			
	    			
	    	        }  
	    	    });  
	    
	  //Action on clicking absent
	    absent.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){  
	    			Appoinment top = appoinmentQueue.Appoinments.remove();
	    			
	    			Calendar calendar = Calendar.getInstance();
	    			long diff = Math.abs(calendar.getTime().getMinutes()-top.getDate().getMinutes());
	    			if(diff < 10) {
		    			appoinmentQueue.Appoinments.add(top);
	    			}else{
	    				appoinmentQueue.BannedAppoinments.add(top);
	    			}
	    		
	    	        displayPanel.removeAll();
	    		    if(appoinmentQueue.Appoinments.isEmpty()) {
	    		    	l1.setText("No appoinments to display !!");
	    		    	l2.setText("");
	    		    	present.setEnabled(false);
	    		    	absent.setEnabled(false);
	    		    }
	    		    else {
	    			    l3.setText("Student Email: ");
	    			    l4.setText("Student Question: ");
	    			    l1.setText(appoinmentQueue.Appoinments.peek().getStudent().getEmail());    
	    			    l2.setText(appoinmentQueue.Appoinments.peek().getQuestion());
	    			    l5.setText("Appoinment Time: ");
	    			    l6.setText(dateFormat.format(appoinmentQueue.Appoinments.peek().getDate())); 
	    			    displayPanel.add(l3);
	    			    displayPanel.add(l1);
	    			    displayPanel.add(l4);
	    			    displayPanel.add(l2);
	    			    displayPanel.add(l5);
	    			    displayPanel.add(l6);
	    		    } 
	    			displayPanel.revalidate();displayPanel.repaint();
	    			
	    			ArrayList<String> data = new ArrayList<String>();
	    			for(Appoinment a : appoinmentQueue.Appoinments) { 
	    				data.add(a.getStudent().getEmail()+" : "+a.getQuestion()+" ");
	    			}
	    			JList list = new JList(data.toArray());
	    			innerPanel.removeAll();
	    			innerPanel.add(list);
	    			innerPanel.revalidate();innerPanel.repaint();
	    	        }  
	    	    }); 
	    
	    absent.setBounds(250,200,95,30);
	    f.add(present);
	    f.add(absent);  
	    f.setSize(500,500);  
	    f.setLayout(null);  
	    f.setVisible(true);   
		
	}

}
