package goeuro.reader;

import goeuro.entities.GeoData;

import java.util.List;

public interface GeoDataParser {

	public List<GeoData> createPositions(String raw);
}
