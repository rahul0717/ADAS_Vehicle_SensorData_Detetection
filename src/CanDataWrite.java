import java.util.ArrayList;
// The Write module of the APPLICATION
// The View part of the MVC
public class CanDataWrite {

	// We will be storing the GPS data as a Nested Array of Strings
	String[][] gpsData = { { "1000", "52.721103", "13.223500" }, { "2000", "52.721170", "13.223778" },
			{ "3000", "52.721235", "13.224058" }, { "4000", "52.721302", "13.224340" },
			{ "5000", "52.721368", "13.224623" }, { "6000", "52.721437", "13.224910" },
			{ "7000", "52.721505", "13.225198" }, { "8000", "52.721573", "13.225478" },
			{ "9000", "52.721639", "13.225752" }, { "10000", "52.721705", "13.226025" },
			{ "11000", "52.721772", "13.226307" }, { "12000", "52.721842", "13.226593" },
			{ "13000", "52.721918", "13.226883" }, { "14000", "52.722007", "13.227160" },
			{ "15000", "52.722113", "13.227414" }, { "16000", "52.722242", "13.227628" },
			{ "17000", "52.722390", "13.227803" }, { "18000", "52.722552", "13.227933" },
			{ "19000", "52.722722", "13.228020" }, { "20000", "52.722900", "13.228053" },
			{ "21000", "52.723078", "13.228037" }, { "22000", "52.723252", "13.227980" },
			{ "23000", "52.723422", "13.227892" }, { "24000", "52.723585", "13.227787" },
			{ "25000", "52.723745", "13.227680" }, { "26000", "52.723895", "13.227580" },
			{ "27000", "52.724025", "13.227495" }, { "28000", "52.724135", "13.227423" },
			{ "29000", "52.724225", "13.227375" }, { "30000", "52.724298", "13.227362" },
			{ "31000", "52.724358", "13.227377" }, { "32000", "52.724417", "13.227387" },
			{ "33000", "52.724470", "13.227372" }, { "34000", "52.724517", "13.227328" },
			{ "35000", "52.724553", "13.227262" }, { "36000", "52.724570", "13.227171" },
			{ "37000", "52.724565", "13.227075" }, { "38000", "52.724532", "13.226988" },
			{ "39000", "52.724478", "13.226932" }, { "40000", "52.724415", "13.226918" },
			{ "41000", "52.724352", "13.226948" } };

	// FUnction to Write the processed information
	// We will be adding the GPS data for every 1000 millisecond
	public void writeProcessedCanData(ArrayList<String> processedData) {
		float offset = 0;
		float gpsOffset = 0;
		// Creating the Presentation
		// We will add the GPS data itially and then we will only add every 1000 ms
		System.out.println("=========================================================================");
		System.out.println("Time Offset |       Sensor Name              |  Value");
		System.out.println("=========================================================================");

		int gspDataIndex = 0;
		int j = 0;
		System.out.println((Float.parseFloat(gpsData[gspDataIndex][0]) - 1000.0)
				+ "      |            GPS-Lat                |  " + gpsData[gspDataIndex][1] + "          |  degrees ");
		System.out.println((Float.parseFloat(gpsData[gspDataIndex][0]) - 1000.0)
				+ "      |            GPS-Long                |  " + gpsData[gspDataIndex][2] + "          |  degrees ");
		gspDataIndex++;
		
		// The loop for printing the data
		for (int i = 0; i < processedData.size(); i++) {
			System.out.println(processedData.get(i));
			// Check if we need to add the GPS data
			if (i + 1 < processedData.size()) {
				offset = Float.parseFloat(processedData.get(i + 1).split(" ")[0]);
				gpsOffset = Float.parseFloat(gpsData[gspDataIndex][0]) - 1000;
				if (offset > gpsOffset && gspDataIndex < gpsData.length - 1) {
					System.out.println((Float.parseFloat(gpsData[gspDataIndex][0]) - 1000.0)
							+ "   |            GPS-Lat                 |  " + gpsData[gspDataIndex][1] + "          |  degrees ");
					System.out.println((Float.parseFloat(gpsData[gspDataIndex][0]) - 1000.0)
							+ "   |            GPS-Long                |  " + gpsData[gspDataIndex][2] + "          |  degrees ");
					gspDataIndex++;
				}
			}


		}
	}

}
