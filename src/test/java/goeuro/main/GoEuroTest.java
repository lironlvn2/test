package goeuro.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

public class GoEuroTest {

	@Before
	public void before(){
		File file = new File(GoEuroRunner.OUTPUT_FILE_NAME);
		file.delete();
	}
	
	@After
	public void after(){
		File file = new File(GoEuroRunner.OUTPUT_FILE_NAME);
		file.delete();
	}
	
	@Test
	public void testE2E() throws IOException{
		GoEuro.main(new String[]{"berlin"});
		File file = new File(GoEuroRunner.OUTPUT_FILE_NAME);
		Assert.assertTrue(file.exists());
		CSVReader reader = new CSVReader(new FileReader(file));
		List<String[]> lines = reader.readAll();
		reader.close();
		Assert.assertTrue(lines.size() > 1);
	}
	
}
