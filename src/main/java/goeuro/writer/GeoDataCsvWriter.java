package goeuro.writer;

import goeuro.entities.GeoData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

public class GeoDataCsvWriter implements GeoDataWriter{
	
	public void write(List<GeoData> data, String fileName) throws IOException {
		CSVWriter csvWriter = null;
		try {
			csvWriter = new CSVWriter(new FileWriter(fileName), ',');
			csvWriter.writeNext(new String[]{"id", "name", "type", "latitude", "longitude"});
			for (GeoData d : data){
				csvWriter.writeNext(d.toArr());	
			}
		} finally {
			try { if (csvWriter != null) csvWriter.close(); } catch (IOException e) {}	
		}
	}
}
