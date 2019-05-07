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

}
