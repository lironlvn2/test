package goeuro.main;

import goeuro.entities.GeoData;
import goeuro.reader.GeoDataReader;
import goeuro.reader.HttpDataReader;
import goeuro.reader.JsonGeoDataParser;
import goeuro.writer.GeoDataCsvWriter;
import goeuro.writer.GeoDataWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GoEuroRunner {
	
	private static final Logger logger = Logger.getLogger( GoEuroRunner.class.getName() );
	public static final String GOEURO_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
	public static final String OUTPUT_FILE_NAME = "goeuro-output.csv";
	private GeoDataReader reader;
	private GeoDataWriter writer;
	
	public GoEuroRunner(){
		reader = new HttpDataReader(GOEURO_URL, new JsonGeoDataParser());
		writer = new GeoDataCsvWriter();
	}
	
	public GoEuroRunner(GeoDataReader reader, GeoDataWriter writer){
		this.reader = reader;
		this.writer = writer;
	}
	
	public boolean getResults(String position){
		List<GeoData> data = new ArrayList<>();
		try {
			data = reader.readData(position);
		} catch (Exception e) {
			logger.log(Level.INFO, "error reading data. no results");
			return false;
		}
		try {
			writer.write(data, OUTPUT_FILE_NAME);
			return true;
		} catch (IOException e) {
			logger.log(Level.INFO, "error writing to file "+OUTPUT_FILE_NAME);
			return false;
		}
	}
}
