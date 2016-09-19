package goeuro.reader;

import goeuro.entities.GeoData;

import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class HttpDataReader implements GeoDataReader{

	public static final int NUM_RETRIES = 3;
	public static final int RETRY_SLEEP_MILLIS = 3000;

	private String basicUrl;
	private GeoDataParser dataParser;
	private HttpClient client = new HttpClient();
	private int retrySleepMillis;

	public HttpDataReader(String basicUrl, GeoDataParser dataParser){
		this(basicUrl, dataParser, new HttpClient(), RETRY_SLEEP_MILLIS);
	}
	
	public HttpDataReader(String basicUrl, GeoDataParser dataParser, HttpClient client, int retrySleepMillis){
		this.basicUrl = basicUrl;
		this.dataParser = dataParser;
		this.client = client;
		this.retrySleepMillis = retrySleepMillis;
	}

	public List<GeoData> readData(String positionName) throws Exception{
		String url = basicUrl+"/"+positionName;
		
		HttpMethod method = null;
		try {
			method = new GetMethod(url);
			int numRetries = 0;
			int status = client.executeMethod(method);
			while (numRetries<NUM_RETRIES && status != HttpStatus.SC_OK){
				method.releaseConnection();
				method = new GetMethod(url);
				status = client.executeMethod(method);
				numRetries++;
				Thread.sleep(retrySleepMillis);
			}
			if (status == HttpStatus.SC_OK){
				return dataParser.createPositions(IOUtils.toString(method.getResponseBodyAsStream(), "UTF-8"));
			} else {
				throw new HttpException("error calling api, status "+status);
			}
		} finally {
			if (method != null) method.releaseConnection();
		}
	}
}
