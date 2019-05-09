import java.util.Calendar;
import java.util.LinkedList; 
import java.util.Queue; 

public class AppoinmentHandler {
	Queue<Appoinment> Appoinments = new LinkedList<>();
	Queue<Appoinment> BannedAppoinments = new LinkedList<>();
    public AppoinmentHandler(Appoinment app[]) {
    	for(int i = 0 ; i< app.length; i++) {
    		Appoinments.add(app[i]);
    	}
    }
    public String markPresent() {
    	if(Appoinments.isEmpty()){
    		return "Queue is empty";
    	}
    	else{
    		Appoinments.remove();
    		return "Student marked present";
    	}
    }
    
    public Student firstInQueue(){
    	return Appoinments.peek().getStudent();
    }  

    public String firstQuestionInQueue(){
    	return Appoinments.peek().getQuestion();
    }
    
    public String markAbsent() {
    	
    	if(Appoinments.isEmpty()){
    		return "Queue is empty";
    	}
    	else{
    		Appoinment top = Appoinments.remove();
    		
    		Calendar calendar = Calendar.getInstance();
    		long diff = Math.abs(calendar.getTime().getMinutes()-top.getDate().getMinutes());
    		if(diff < 10) {
    			Appoinments.add(top);
    			return "Student added to end of queue";
    		}else{
    			top.getStudent().setBanned(true);
    			calendar.add(Calendar.DATE, 10);
    			top.getStudent().setBannedTill(calendar.getTime());
    			BannedAppoinments.add(top);
    			System.out.println(BannedAppoinments.peek().getStudent().getBannedTill());
    			return "Student banned";
    		}
    	}
    	
    
    }
}
