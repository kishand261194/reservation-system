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
    public void markPresent() {
    	Appoinments.remove();
    }
    
    public Student firstInQueue(){
    	return Appoinments.peek().getStudent();
    }  

    public String firstQuestionInQueue(){
    	return Appoinments.peek().getQuestion();
    }
    
    public void markAbsent() {
		Appoinment top = Appoinments.remove();
		
		Calendar calendar = Calendar.getInstance();
		long diff = Math.abs(calendar.getTime().getMinutes()-top.getDate().getMinutes());
		if(diff < 10) {
			Appoinments.add(top);
		}else{
			BannedAppoinments.add(top);
		}
    }
}
