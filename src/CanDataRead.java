import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// The Read module 
// Acting as the Model of the MVC
public class CanDataRead {
	// function to read the Can frame data
	public ArrayList<String[]> ReadCanFramefile(String filePath) {
		String[] data;
		ArrayList<String[]> canData = new ArrayList<String[]>();
		boolean dataStart = false;
		String currentLine;
		try {
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				currentLine = myReader.nextLine();
				if (currentLine.contains("0003")) {
					dataStart = true;
				}
				if (dataStart) {
					data = currentLine.split(";");
					canData.add(data);
				}

			}

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return canData;

	}

	// Function to read the trace file data
	public String ReadCanTracefile(String filePath) {
		String traceData = "";
		boolean dataStart = false;
		String currentLine;
		try {
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				currentLine = myReader.nextLine();
				if (currentLine.contains(" 1) ") && currentLine.contains("Rx")) {
					dataStart = true;
				}
				if (dataStart) {
					List<String> lineData = new ArrayList<String>(Arrays.asList(currentLine.split(" ")));
					for (int i = 0; i < lineData.size(); i++) {
						if (lineData.get(i).length() == 0) {
							String temp = lineData.remove(i);
							i--;
						}
					}
					if ((Integer.parseInt(lineData.get(3), 16) == Integer.parseInt("0003", 16))
							|| (Integer.parseInt(lineData.get(3), 16) == Integer.parseInt("019F", 16))
							|| (Integer.parseInt(lineData.get(3), 16) == Integer.parseInt("0245", 16))) {
						traceData += lineData.get(1) + "," + lineData.get(3) + "," + lineData.get(4) + ","
								+ lineData.get(5) + "-" + lineData.get(6) + "-" + lineData.get(7) + "-"
								+ lineData.get(8) + "-" + lineData.get(9) + "-" + lineData.get(10) + "-"
								+ lineData.get(11) + "-" + lineData.get(12) + "%%%";
					}

				}
			}

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return traceData;

	}

}
