
/**
 * Find the coldest hour in a file and in a year.
 * 
 * @author (Tarek) 
 * @version (0.1) 21-12-2017
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class LowestTemp {
	/*
	* This method returns the CSVRecord with the coldest temperature in the file and thus 
	* all the information about the coldest temperature, such as the hour of the coldest temperature.
	*/
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldestTemp = null;
		for(CSVRecord currRow : parser) {
			if(coldestTemp == null) {
				coldestTemp = currRow;
			} 
			else {
				double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
				double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
				if(currTemp < lowestTemp) {
					coldestTemp = currRow;
				}
			}
		}
		return coldestTemp;
	}

	public void testColdestHourInFile() {
		FileResource f = new FileResource();
		CSVParser parser = f.getCSVParser();

		CSVRecord lowestTemp = coldestHourInFile(parser);
		System.out.println(lowestTemp.get("TemperatureF") + ": " + lowestTemp.get("DateUTC"));
	}

	public String fileWithColdestTemperature() {
		File fileName = null;
		CSVRecord coldestTemp = null;

		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord currRow = coldestHourInFile(parser);
			
			if(coldestTemp == null) {
				coldestTemp = currRow;
				fileName = f;
			} 
			else {
				double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
				double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
				if(currTemp < lowestTemp && currTemp > -50) {
					coldestTemp = currRow;
					fileName = f;
				}
			}
		}
		return fileName.getAbsolutePath();
	}

	public void testFileWithColdestTemperature() {
		String fileWithColdestTemp = fileWithColdestTemperature();
		File f = new File(fileWithColdestTemp);
		String fileName = f.getName();

		System.out.println("Coldest day was in file " + fileName);

		
		FileResource fr = new FileResource(f);
		CSVParser parser = fr.getCSVParser();
		CSVRecord lowestTemp = coldestHourInFile(parser);

		System.out.println("Coldest Temperature is: " + lowestTemp.get("TemperatureF"));

		System.out.println("All the Temperatures on the coldest day were");
		CSVParser parser2 = fr.getCSVParser();
		for(CSVRecord record : parser2) {
			double temp = Double.parseDouble(record.get("TemperatureF"));
			System.out.println(temp);
		}
	}

	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestHumdity = null;
		int currHumd;
		int lowestHumd;
		for(CSVRecord currRow : parser) {
			if(lowestHumdity == null) {
				lowestHumdity = currRow;
			} 

			else {
				if(!currRow.get("Humidity").equals("N/A") && !lowestHumdity.get("Humidity").equals("N/A")) {
					currHumd = Integer.parseInt(currRow.get("Humidity"));
					lowestHumd = Integer.parseInt(lowestHumdity.get("Humidity"));
					
					if(currHumd < lowestHumd) {
						lowestHumdity = currRow;
					}
				}
			}
		}
		return lowestHumdity;
	}

	public void testLowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord lowestHumdity = lowestHumidityInFile(parser);

		System.out.println(lowestHumdity.get("Humidity") + " at " + lowestHumdity.get("DateUTC"));
	}

	public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord lowestHumdity = null;
		int currHumd;
		int lowestHumd;

		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord currRow = lowestHumidityInFile(parser);
			
			if(lowestHumdity == null) {
				lowestHumdity = currRow;
			} 
			else {
				int currTemp = Integer.parseInt(currRow.get("Humidity"));
				int lowestTemp = Integer.parseInt(lowestHumdity.get("Humidity"));
				if(currTemp < lowestTemp) {
					lowestHumdity = currRow;
				}

				else {
					if(currRow.get("Humidity") != "N/A" && lowestHumdity.get("Humidity") != "N/A") {
						currHumd = Integer.parseInt(currRow.get("Humidity"));
						lowestHumd = Integer.parseInt(lowestHumdity.get("Humidity"));
						
						if(currHumd < lowestHumd) {
							lowestHumdity = currRow;
						}
					}
				}
			}
		}
		return lowestHumdity;
	}

	public void testLowestHumidityInManyFiles() {
		CSVRecord record = lowestHumidityInManyFiles();
		System.out.println(record.get("Humidity") + " at " + record.get("DateUTC"));
	}

	public double averageTemperatureInFile(CSVParser parser) {
		double num = 0.0;
		double sum = 0.0;

		for(CSVRecord record : parser) {
			double temp = Double.parseDouble(record.get("TemperatureF"));
			sum += temp;
			num++;
		}

		double average = sum / num;
		return average;
	}

	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avg = averageTemperatureInFile(parser);

		System.out.println("average temperature is " + avg);
	}

	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double num = 0.0;
		double sum = 0.0;

		for(CSVRecord record : parser) {
			double temp = Double.parseDouble(record.get("TemperatureF"));
			int humidity = Integer.parseInt(record.get("Humidity"));
			if(humidity >= value) {
				sum += temp;
				num++;
			}
		}

		double average = sum / num;
		return average;
	}

	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avg = averageTemperatureWithHighHumidityInFile(parser, 80);

		if(!Double.isNaN(avg)) {
			System.out.println("average temperature is " + avg);
		} else {
			System.out.println("No Temperature was found");
		}
	}
}
