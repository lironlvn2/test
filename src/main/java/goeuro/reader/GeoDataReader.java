package goeuro.reader;

import goeuro.entities.GeoData;

import java.util.List;

public interface GeoDataReader {
	public List<GeoData> readData(String positionName) throws Exception;
}
