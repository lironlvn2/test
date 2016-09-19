package goeuro.main;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import goeuro.reader.GeoDataReader;
import goeuro.writer.GeoDataWriter;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

public class RunnerTest {

	@Test
	public void testValid() throws Exception{
		GeoDataReader reader = mockValidRedaer();
		GeoDataWriter writer = mockValidWriter();
		GoEuroRunner runner = new GoEuroRunner(reader, writer);
		Assert.assertTrue(runner.getResults("somewhere"));
	}
	
	@Test
	public void testErrorReader() throws Exception{
		GeoDataReader reader = Mockito.mock(GeoDataReader.class);
		Mockito.when(reader.readData(anyString())).thenThrow(new Exception("error reading"));
		GeoDataWriter writer = mockValidWriter();
		GoEuroRunner runner = new GoEuroRunner(reader, writer);
		Assert.assertFalse(runner.getResults("somewhere"));
	}
	
	@Test
	public void testErrorWriting() throws Exception{
		GeoDataReader reader = mockValidRedaer();
		GeoDataWriter writer = Mockito.mock(GeoDataWriter.class);
		Mockito.doThrow(new IOException("error writing")).when(writer).write(any(), anyString());
		GoEuroRunner runner = new GoEuroRunner(reader, writer);
		Assert.assertFalse(runner.getResults("somewhere"));
	}

	private GeoDataWriter mockValidWriter() throws IOException {
		GeoDataWriter writer = Mockito.mock(GeoDataWriter.class);
		Mockito.doNothing().when(writer).write(any(), anyString());
		return writer;
	}

	private GeoDataReader mockValidRedaer() throws Exception {
		GeoDataReader reader = Mockito.mock(GeoDataReader.class);
		Mockito.when(reader.readData(anyString())).thenReturn(new ArrayList<>());
		return reader;
	}
}
