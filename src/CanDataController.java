import java.util.ArrayList;

// The Controller of the MVC structure.
// Singleton design pattern have been implemented
public class CanDataController {
	CanDataRead readObj;
	CanDataProcessing processObj;
	CanDataWrite writeObj;
	static CanDataController contollerObj = null;
	private ArrayList<String> processedDataResults = new ArrayList<String>();
	private String traceFileDataStr;
	// Private Constructor

	private CanDataController() {
		readObj = new CanDataRead();
		processObj = new CanDataProcessing();
		writeObj = new CanDataWrite();
	}

	// Function to get the object instance
	public static CanDataController getControllerInstance() {
		if (contollerObj == null) {
			contollerObj = new CanDataController();
		}
		return contollerObj;
	}

	// Helper function to read the frame files
	public ArrayList<String[]> ReadCanDataCtroller(String filePath) {
		return readObj.ReadCanFramefile(filePath);

	}

	// Helper function to read the Trace files
	public String ReadCanTraceDataCtroller(String filePath) {
		return readObj.ReadCanTracefile(filePath);

	}

	// Helper function to Process the Trace files
	public ArrayList<String> processTraceDataCtroller(String traceDataStr) {
		processedDataResults = processObj.ProcessTraceData(traceDataStr);
		return processedDataResults;
	}

	// Helper function to Process the frame files
	public void ProcessCanData(ArrayList<String[]> canData) {
		processObj.CanDataprocessing(canData);
	}

	// Helper function to write the processed files
	public void writeCanProcessedData(ArrayList<String> processedData) {
		writeObj.writeProcessedCanData(processedData);

	}

}
