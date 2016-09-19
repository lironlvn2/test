package goeuro.reader;

import goeuro.entities.GeoData;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

public class HttpDataReaderTest {

	@Test
	public void testStatusOK() throws Exception{
		GeoDataParser mockDataParser = getMockParser();
		
		HttpClient mockClient = Mockito.mock(HttpClient.class);
		Mockito.when(mockClient.executeMethod(any())).thenReturn(HttpStatus.SC_OK);
		
		GeoDataReader reader = new HttpDataReader("http://url", mockDataParser, mockClient, 50);
		List<GeoData> data = reader.readData("somewhere");
		Assert.assertEquals(getDummyData(), data);
	}
	
	@Test(expected = HttpException.class)
	public void testStatusError() throws Exception{
		GeoDataParser mockDataParser = getMockParser();
		
		HttpClient mockClient = Mockito.mock(HttpClient.class);
		Mockito.when(mockClient.executeMethod(any())).thenReturn(HttpStatus.SC_NOT_FOUND);
		
		GeoDataReader reader = new HttpDataReader("http://url", mockDataParser, mockClient, 50);
		List<GeoData> data = reader.readData("somewhere");
		Assert.assertEquals(0, data.size());
	}
	
	@Test(expected = HttpException.class)
	public void testException() throws Exception{
		GeoDataParser mockDataParser = getMockParser();
		
		HttpClient mockClient = Mockito.mock(HttpClient.class);
		Mockito.when(mockClient.executeMethod(any())).thenThrow(new HttpException());
		
		GeoDataReader reader = new HttpDataReader("http://url", mockDataParser, mockClient, 50);
		List<GeoData> data = reader.readData("somewhere");
		Assert.assertEquals(0, data.size());
	}

	private GeoDataParser getMockParser() {
		GeoDataParser mockDataParser = Mockito.mock(GeoDataParser.class);
		List<GeoData> dummyData = getDummyData();
		Mockito.when(mockDataParser.createPositions(anyString())).thenReturn(dummyData);
		return mockDataParser;
	}

	private List<GeoData> getDummyData() {
		List<GeoData> dummyData = new ArrayList<>();
		dummyData.add(new GeoData(123, "name", "type", 1., 1.));
		return dummyData;
	}
	
	
}
