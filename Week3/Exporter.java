
/**
 * Write a description of Exporter here.
 * 
 * @author (Tarek) 
 * @version (0.1)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Exporter {
    /*
	* This method returns a string of information about 
	* the country or returns “NOT FOUND” if there is no 
	* information about the country.
	*/
	public String countryInfo(CSVParser parser, String country) {
		for(CSVRecord record : parser) {
			String myCountry = record.get("Country");
			if(myCountry.contains(country)) {
				String exports = record.get("Exports");
				String value = record.get("Value (dollars)");
				String info = myCountry + ": " + exports + ": " + value;
				return info;
			}
		}

		return "NOT FOUND";
	}

	/*
	* This method prints the names of all the countries that 
	* have both exportItem1 and exportItem2 as export items.
	*/
	public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for(CSVRecord record : parser) {
			String exports = record.get("Exports");
			String country = record.get("Country");
			if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
				System.out.println(country);
			}
		}
	}

	/*
	* This method returns the number of countries 
	* that export exportItem.
	*/
	public int numberOfExporters(CSVParser parser, String exportItem) {
		int numOfCountries = 0;
		for(CSVRecord record : parser) {
			String exports = record.get("Exports");
			if(exports.contains(exportItem)) {
				numOfCountries++;
			}
		}
		return numOfCountries;
	}

	/*
	* This method prints the names of countries and their
	* Value amount for all countries whose Value (dollars) 
	* string is longer than the amount string.
	*/
	public void bigExporters(CSVParser parser, String amount) {
		for(CSVRecord record : parser) {
			String value = record.get("Value (dollars)");
			String country = record.get("Country");

			if(value.length() > amount.length()) {
				System.out.println(country + ": " + value);
			}
		}
	}

	public void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		
		// String info = countryInfo(parser, "Nauru");
		// System.out.println(info);
		
		//listExportersTwoProducts(parser, "cotton", "flowers");

		// int numOfCountries = numberOfExporters(parser, "cocoa");
		// System.out.println(numOfCountries);

		bigExporters(parser, "$999,999,999,999");

		//System.out.println("Country".equals("Country"));
	}
}
