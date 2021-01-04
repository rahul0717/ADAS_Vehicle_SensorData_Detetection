import java.util.ArrayList;
// The Main Driver class of the application
// I have used MVC pattern to implement the program
// The Main method invokes the controller
// Since we want a since instance of the Controller I have used Singleton pattern
// WE will be using the ArrayList as the UnderLying DataStructure since it is easily Modifiable and is dynamic
public class CanDriverMain {
	public static void main(String[] args) {
		// getInstance method can used to get the only instance of the controller.
		CanDataController controllerObj = CanDataController.getControllerInstance();
		String canFilePath = "/Users/rahul5111/Downloads/CANFramesInfo.txt";
		String canTraceFilePath = "/Users/rahul5111/Downloads/19CANmessages.trc";
		// First we read the Can frame files
		ArrayList<String[]> canData = controllerObj.ReadCanDataCtroller(canFilePath);
		// Then we process and clean the data
		controllerObj.ProcessCanData(canData);
		// Then we read the Trace file
		String traceFileDataStr = controllerObj.ReadCanTraceDataCtroller(canTraceFilePath);
		// THen the processing of the data begins
		ArrayList<String> proceedCanData = controllerObj.processTraceDataCtroller(traceFileDataStr);
		// Finally we write the data on to the console
		controllerObj.writeCanProcessedData(proceedCanData);
		
		

	}

}
