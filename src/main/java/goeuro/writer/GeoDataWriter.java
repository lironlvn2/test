package goeuro.writer;

import goeuro.entities.GeoData;

import java.io.IOException;
import java.util.List;

public interface GeoDataWriter {

	public void write(List<GeoData> data, String fileName) throws IOException;
}
