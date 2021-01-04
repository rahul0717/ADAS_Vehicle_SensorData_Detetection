import java.util.ArrayList;

// The Processing module of the application
// Part of the Model of the MVC pattern
public class CanDataProcessing {
	private ArrayList<String> steeringAngleMetaData = new ArrayList<String>();
	private ArrayList<String> speedMetaData = new ArrayList<String>();
	private ArrayList<String> yawRateMetaData = new ArrayList<String>();
	private ArrayList<String> longitudinalAccMetaData = new ArrayList<String>();
	private ArrayList<String> lateralAccMetaData = new ArrayList<String>();

	// Wrapper function to process and then clean the CAN Frames data to useful
	// format
	public void CanDataprocessing(ArrayList<String[]> canData) {
		this.CanDataSegregation(canData);
		this.CanDataClean();
	}

	// Here we will be parsing the frame data and forming our data Structure
	// We are creating the Meta data data Structure for each frames which we need to process each of the data
	// WE have chosen ArrayList to be underlying the DS of the MEtaData
	private void CanDataSegregation(ArrayList<String[]> canData) {
		for (int i = 0; i < canData.size(); i++) {
			String[] data = (String[]) canData.get(i);
			if (Integer.parseInt(data[0], 16) == Integer.parseInt("0003", 16)) {
				for (int j = 0; j < data.length; j++) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 5 || j == 7)
						steeringAngleMetaData.add(data[j]);
				}
			} else if (Integer.parseInt(data[0], 16) == Integer.parseInt("019F", 16)) {
				for (int j = 0; j < data.length; j++) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 5 || j == 7)
						speedMetaData.add(data[j]);
				}
			} else if ((Integer.parseInt(data[0], 16) == Integer.parseInt("0245", 16)) && data[3].contains("yaw")) {
				for (int j = 0; j < data.length; j++) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 5 || j == 7)
						yawRateMetaData.add(data[j]);
				}
			} else if ((Integer.parseInt(data[0], 16) == Integer.parseInt("0245", 16))
					&& data[3].contains("longitudinal")) {
				for (int j = 0; j < data.length; j++) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 5 || j == 7)
						longitudinalAccMetaData.add(data[j]);
				}
			} else if ((Integer.parseInt(data[0], 16) == Integer.parseInt("0245", 16)) && data[3].contains("lateral")) {
				for (int j = 0; j < data.length; j++) {
					if (j == 0 || j == 1 || j == 2 || j == 3 || j == 5 || j == 7)
						lateralAccMetaData.add(data[j]);
				}
			}

		}

	}

	// Since the Frame data has one or more unwanted fields we will ignore them
	// and keep only the useful ones
	// Since for each data the requirement is different we will perform on each
	// Arraylist which we have formed
	private void CanDataClean() {

		// We will remove the white spaces and make the frame data more readable
		String ByteRange = "";
		String lowerBound;
		String step;

		for (int i = 0; i < this.steeringAngleMetaData.size(); i++) {

			// For the Steering wheel angle data
			if (i == 1) {

				String dump = this.steeringAngleMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				// making the bytes and bits info more readable
				ByteRange += dumpArr[0].substring(2, 3) + ",";
				ByteRange += dumpArr[0].substring(5, 6) + "-";
				ByteRange += dumpArr[1].substring(2, 3) + ",";
				ByteRange += dumpArr[1].substring(5, 6);
				this.steeringAngleMetaData.set(1, ByteRange);
				ByteRange = "";
			}
			if (i == 4) {
				String dump = this.steeringAngleMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				lowerBound = dumpArr[1];
				lowerBound = lowerBound.replace(",", ".");
				this.steeringAngleMetaData.set(4, lowerBound);
				lowerBound = "";

			}
			if (i == 5) {

				String dump = this.steeringAngleMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split(" ");
				step = dumpArr[0];
				step = step.replace(",", ".");
				this.steeringAngleMetaData.set(5, step);
				step = "";

			}

		}
		// For the Speed metadata
		for (int i = 0; i < this.speedMetaData.size(); i++) {

			if (i == 1) {
				String dump = this.speedMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				// making the bytes and bits info more readable
				ByteRange += dumpArr[0].substring(2, 3) + ",";
				ByteRange += dumpArr[0].substring(5, 6) + "-";
				ByteRange += dumpArr[1].substring(2, 3) + ",";
				ByteRange += dumpArr[1].substring(5, 6);
				this.speedMetaData.set(1, ByteRange);
				ByteRange = "";
			}

			if (i == 4) {
				String dump = this.speedMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				lowerBound = dumpArr[0];
				lowerBound = lowerBound.replace(",", ".");
				this.speedMetaData.set(4, lowerBound);
				lowerBound = "";

			}
			if (i == 5) {
				String dump = this.speedMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split(" ");
				step = dumpArr[0];
				step = step.replace(",", ".");
				this.speedMetaData.set(5, step);
				step = "";

			}

		}
		// For the Yaw Rate metadata
		for (int i = 0; i < this.yawRateMetaData.size(); i++) {

			if (i == 1) {
				String dump = this.yawRateMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				// making the bytes and bits info more readable
				ByteRange += dumpArr[0].substring(2, 3) + ",";
				ByteRange += dumpArr[0].substring(5, 6) + "-";
				ByteRange += dumpArr[1].substring(2, 3) + ",";
				ByteRange += dumpArr[1].substring(5, 6);
				this.yawRateMetaData.set(1, ByteRange);
				ByteRange = "";
			}
			if (i == 4) {
				String dump = this.yawRateMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				lowerBound = dumpArr[1];
				lowerBound = lowerBound.replace(",", ".");
				this.yawRateMetaData.set(4, lowerBound);
				lowerBound = "";

			}
			if (i == 5) {
				String dump = this.yawRateMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split(" ");
				step = dumpArr[0];
				step = step.replace(",", ".");
				this.yawRateMetaData.set(5, step);
				step = "";

			}

		}
		// For longitudinalAcceraltion MetaData

		for (int i = 0; i < this.longitudinalAccMetaData.size(); i++) {

			if (i == 1) {
				String dump = this.longitudinalAccMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				// making the bytes and bits info more readable
				ByteRange += dumpArr[0].substring(2, 3) + ",";
				ByteRange += dumpArr[0].substring(5, 6) + "-";
				ByteRange += dumpArr[1].substring(2, 3) + ",";
				ByteRange += dumpArr[1].substring(5, 6);
				this.longitudinalAccMetaData.set(1, ByteRange);
				ByteRange = "";
			}
			if (i == 4) {
				String dump = this.longitudinalAccMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				lowerBound = dumpArr[1];
				lowerBound = lowerBound.replace(",", ".");
				this.longitudinalAccMetaData.set(4, lowerBound);
				lowerBound = "";

			}
			if (i == 5) {
				String dump = this.longitudinalAccMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split(" ");
				step = dumpArr[0];
				step = step.replace(",", ".");
				this.longitudinalAccMetaData.set(5, step);
				step = "";

			}

		}
		// For lateralAcceleration MetaData
		for (int i = 0; i < this.lateralAccMetaData.size(); i++) {

			if (i == 1) {
				String dump = this.lateralAccMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				// making the bytes and bits info more readable
				ByteRange += dumpArr[0].substring(2, 3) + ",";
				ByteRange += dumpArr[0].substring(5, 6) + "-";
				ByteRange += dumpArr[1].substring(2, 3) + ",";
				ByteRange += dumpArr[1].substring(5, 6);
				this.lateralAccMetaData.set(1, ByteRange);
				ByteRange = "";
			}
			if (i == 4) {
				String dump = this.lateralAccMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split("-");
				lowerBound = dumpArr[1];
				lowerBound = lowerBound.replace(",", ".");
				this.lateralAccMetaData.set(4, lowerBound);
				lowerBound = "";

			}
			if (i == 5) {
				String dump = this.lateralAccMetaData.get(i);
				dump = dump.trim();
				String[] dumpArr = dump.split(" ");
				step = dumpArr[0];
				step = step.replace(",", ".");
				this.lateralAccMetaData.set(5, step);
				step = "";

			}

		}

	}

	// FUncion to process the Trace data using all the information from the
	// Frame files
	// Each type of information will be identified and each of them will be
	// decided in a different method
	// ProcessSteeringAngleData() , ProcessSpeedData(), ProcessYLLData() will be
	// called based on the type of the info we are dealing
	// We will also be forming the data format to be printed
	public ArrayList<String> ProcessTraceData(String traceDataStr) {
		String[] traceData = traceDataStr.split("%%%");
		ArrayList<String> processedCANData = new ArrayList<String>();
		String result = "";
		float computedVal;
		float[] computedvals;
		// WE use simple space to create the indentations
		// We form the string which is used to print te results
		for (int i = 0; i < traceData.length; i++) {
			String[] traceLinedata = traceData[i].split(",");
			if (Integer.parseInt(traceLinedata[1], 16) == Integer.parseInt("0003", 16)) {
				computedVal = this.ProcessSteeringAngleData(traceLinedata);
				result = traceLinedata[0] + "     |  " + "Steering wheel angle             " + " |  " + computedVal
						+ "                   |    degrees";
				processedCANData.add(result);
				result = "";

			} else if (Integer.parseInt(traceLinedata[1], 16) == Integer.parseInt("019F", 16)) {
				computedVal = this.ProcessSpeedData(traceLinedata);
				result = traceLinedata[0] + "     |  " + "Displayed vehicle speed          " + " | " + computedVal
						+ "                    |    km/h ";
				processedCANData.add(result);
				result = "";

			} else if (Integer.parseInt(traceLinedata[1], 16) == Integer.parseInt("0245", 16)) {
				computedvals = this.ProcessYLLData(traceLinedata);
				result = traceLinedata[0] + "     |  " + "Vehicle yaw rate                 " + " | " + computedvals[0]
						+ "                 |    degree/sec";
				processedCANData.add(result);
				result = "";
				result = traceLinedata[0] + "     |  " + "Vehicle longitudinal acceleration" + " | " + computedvals[1]
						+ "                 |    m/sec^2";
				processedCANData.add(result);
				result = "";
				result = traceLinedata[0] + "     |  " + "Vehicle lateral acceleration     " + " | " + computedvals[2]
						+ "                 |    m/sec^2";
				processedCANData.add(result);
				result = "";

			}

		}

		return processedCANData;
	}

	// Function to process the SteeringAngleData
	public float ProcessSteeringAngleData(String[] steeringData) {
		// First we compute what the data bits gives and then we use the Steps
		// and the Lower bound to find the value
		int calculatedBinaryVal = this.ProcessDataBytes(steeringData, "steering");
		float step = Float.parseFloat(this.steeringAngleMetaData.get(5));
		float lowerBound = Float.parseFloat(this.steeringAngleMetaData.get(4));
		float finalAngle = (calculatedBinaryVal * step) - lowerBound;

		return finalAngle;

	}

	// Function to process the SpeedData
	public float ProcessSpeedData(String[] speedData) {
		// First we compute what the data bits gives and then we use the Steps
		// and the Lower bound to find the value
		int calculatedBinaryVal = this.ProcessDataBytes(speedData, "speed");
		float step = Float.parseFloat(this.speedMetaData.get(5));
		float lowerBound = Float.parseFloat(this.speedMetaData.get(4));
		float finalSpeed = (calculatedBinaryVal * step) - lowerBound;
		return finalSpeed;

	}

	// Function to process the All the data from the Frame 0245
	// There are three types of info which will be handled: Yaw, longitudinal
	// Acceleration Data, lateral Acceleration Data

	public float[] ProcessYLLData(String[] yllData) {
		// First we compute what the data bits gives and then we use the Steps
		// and the Lower bound to find the value
		float[] computedVals = new float[3];
		int calculatedBinaryValYaw = this.ProcessDataBytes(yllData, "yaw");
		int calculatedBinaryValLongitudinal = this.ProcessDataBytes(yllData, "longitudinal");
		int calculatedBinaryValLateral = this.ProcessDataBytes(yllData, "lateral");

		// We get the value from the Array list which we used to store the
		// information
		float yawStep = Float.parseFloat(this.yawRateMetaData.get(5));
		float yawLowerBound = Float.parseFloat(this.yawRateMetaData.get(4));
		float finalYaw = (calculatedBinaryValYaw * yawStep) - yawLowerBound;

		float longitudinalStep = Float.parseFloat(this.longitudinalAccMetaData.get(5));
		float longitudinalLowerBound = Float.parseFloat(this.longitudinalAccMetaData.get(4));
		float finalLongitudinal = (calculatedBinaryValLongitudinal * longitudinalStep) - longitudinalLowerBound;

		float lateralStep = Float.parseFloat(this.lateralAccMetaData.get(5));
		float lateralLowerBound = Float.parseFloat(this.lateralAccMetaData.get(4));
		float finalLateral = (calculatedBinaryValLateral * lateralStep) - lateralLowerBound;

		computedVals[0] = finalYaw;
		computedVals[1] = finalLongitudinal;
		computedVals[2] = finalLateral;

		return computedVals;

	}

	// FUnction to decode the Bytes and Bits information in the frames

	public int ProcessDataBytes(String[] traceData, String frameType) {
		String[] dataBytesArr = traceData[3].split("-");
		int val = 0;
		// Logic to decode the steering angel data
		if (frameType == "steering") {
			int byteVal1 = Integer.parseInt(dataBytesArr[0], 16);
			int byteVal2 = Integer.parseInt(dataBytesArr[1], 16);
			// 1,5-2,0

			String byteVal1Str = Integer.toBinaryString(byteVal1);
			String byteVal2Str = Integer.toBinaryString(byteVal2);

			if (byteVal1Str.length() < 8) {
				int i = byteVal1Str.length();
				while (i < 8) {
					byteVal1Str = "0" + byteVal1Str;
					i++;
				}
			}
			if (byteVal2Str.length() < 8) {
				int i = byteVal2Str.length();
				while (i < 8) {
					byteVal2Str = "0" + byteVal2Str;
					i++;
				}
			}

			byteVal1Str = byteVal1Str.substring(2);
			byteVal2Str = byteVal1Str + byteVal2Str;
			val = Integer.parseInt(byteVal2Str, 2);
		}
		// Logic to decode the Speed data
		else if (frameType == "speed") {
			int byteVal1 = Integer.parseInt(dataBytesArr[0], 16);
			int byteVal2 = Integer.parseInt(dataBytesArr[1], 16);
			// 1,5-2,0

			String byteVal1Str = Integer.toBinaryString(byteVal1);
			String byteVal2Str = Integer.toBinaryString(byteVal2);

			if (byteVal1Str.length() < 8) {
				int i = byteVal1Str.length();
				while (i < 8) {
					byteVal1Str = "0" + byteVal1Str;
					i++;
				}
			}
			if (byteVal2Str.length() < 8) {
				int i = byteVal2Str.length();
				while (i < 8) {
					byteVal2Str = "0" + byteVal2Str;
					i++;
				}
			}

			byteVal1Str = byteVal1Str.substring(4);
			byteVal2Str = byteVal1Str + byteVal2Str;
			val = Integer.parseInt(byteVal2Str, 2);
		}
		// The Logic to decode the Yaw data bytes
		else if (frameType == "yaw") {
			int byteVal1 = Integer.parseInt(dataBytesArr[0], 16);
			int byteVal2 = Integer.parseInt(dataBytesArr[1], 16);

			String byteVal1Str = Integer.toBinaryString(byteVal1);
			String byteVal2Str = Integer.toBinaryString(byteVal2);

			if (byteVal1Str.length() < 8) {
				int i = byteVal1Str.length();
				while (i < 8) {
					byteVal1Str = "0" + byteVal1Str;
					i++;
				}
			}
			if (byteVal2Str.length() < 8) {
				int i = byteVal2Str.length();
				while (i < 8) {
					byteVal2Str = "0" + byteVal2Str;
					i++;
				}
			}

			byteVal2Str = byteVal1Str + byteVal2Str;
			val = Integer.parseInt(byteVal2Str, 2);
		}
		// The decoding for the longitudinal acceleration data bits
		else if (frameType == "longitudinal") {
			int byteVal1 = Integer.parseInt(dataBytesArr[4], 16);

			String byteVal1Str = Integer.toBinaryString(byteVal1);

			if (byteVal1Str.length() < 8) {
				int i = byteVal1Str.length();
				while (i < 8) {
					byteVal1Str = "0" + byteVal1Str;
					i++;
				}
			}

			val = Integer.parseInt(byteVal1Str, 2);
		}
		// The decoding for the Lateral acceleration data bits
		else if (frameType == "lateral") {
			int byteVal1 = Integer.parseInt(dataBytesArr[5], 16);

			String byteVal1Str = Integer.toBinaryString(byteVal1);

			if (byteVal1Str.length() < 8) {
				int i = byteVal1Str.length();
				while (i < 8) {
					byteVal1Str = "0" + byteVal1Str;
					i++;
				}
			}

			val = Integer.parseInt(byteVal1Str, 2);
		}

		return val;

	}

}
