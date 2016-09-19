package goeuro.writer;

import goeuro.entities.GeoData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

public class GeoDataCsvWriterTest {

	private String filename = "test.csv";
	
	@Before
	public void before(){
		File file = new File(filename);
		file.delete();
	}
	
	@After
	public void after(){
		File file = new File(filename);
		file.delete();
	}
	
	@Test
	public void testHasResults() throws IOException{
		File file = new File(filename);
		Assert.assertFalse(file.exists());
		GeoDataWriter writer = new GeoDataCsvWriter();
		List<GeoData> data = new ArrayList<>();
		data.add(new GeoData(111, "name", "type", 1.5, 1.));
		data.add(new GeoData(222, "name2", "type2", 2.5, 2.));
		writer.write(data, filename);
		Assert.assertTrue(file.exists());
		CSVReader reader = new CSVReader(new FileReader(file));
		List<String[]> lines = reader.readAll();
		reader.close();
		Assert.assertEquals(3, lines.size());
		Assert.assertEquals("id", lines.get(0)[0]);
		Assert.assertEquals("111", lines.get(1)[0]);
		Assert.assertEquals(2., Double.valueOf(lines.get(2)[4]),0.0001);
	}
	
	@Test
	public void testNoResults() throws IOException{
		File file = new File(filename);
		Assert.assertFalse(file.exists());
		GeoDataWriter writer = new GeoDataCsvWriter();
		writer.write(new ArrayList<>(), filename);
		Assert.assertTrue(file.exists());
		CSVReader reader = new CSVReader(new FileReader(file));
		List<String[]> lines = reader.readAll();
		reader.close();
		Assert.assertEquals(1, lines.size());
		Assert.assertEquals("id", lines.get(0)[0]);
	}
}
