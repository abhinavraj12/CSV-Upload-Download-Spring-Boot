package com.csv.upload.download.helper;



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.csv.upload.download.model.User;

public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "id", "namePrefix", "firstName", "lastName" };

	// It is used to check the file format is CSV or not.
	public static boolean hasCSVFormat(MultipartFile file) {
		if (TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {
			return true;
		}

		return false;
	}

//  This method is completely used for reading the CSV File data.

	public static List<User> csvToTutorials(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<User> userList = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
//				User user = new User(
//						Long.parseLong(csvRecord.get("id")),
//						csvRecord.get("namePrefix"),
//						csvRecord.get("firstName"),
//						csvRecord.get("lastName"));
				
				User user = new User();
				user.setId(Integer.parseInt(csvRecord.get("id")));
				user.setNamePrefix(csvRecord.get("namePrefix"));
				user.setFirstName(csvRecord.get("firstName"));
				user.setLastName(csvRecord.get("lastName"));

				userList.add(user);
			}

			return userList;
			
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

//  This method is used to write the data in the CSV file from the MySQL database table.

	public static ByteArrayInputStream tutorialsToCSV(List<User> userList) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		//**
		try (java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (User user : userList) {
				List<String> data = Arrays.asList(String.valueOf(user.getId()), user.getNamePrefix(),
						user.getFirstName(), user.getLastName()

				);

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
			
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}
}
