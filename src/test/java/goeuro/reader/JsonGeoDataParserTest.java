package goeuro.reader;

import goeuro.entities.GeoData;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class JsonGeoDataParserTest {

	@Test
	public void testMultipleResults(){
		GeoDataParser parser = new JsonGeoDataParser();
		String raw = "[\n" +
                "\n" +
                " {\n" +
                "\n" +
                " _id: 377078,\n" +
                " key: null,\n" +
                " name: \"Potsdam\",\n" +
                " fullName: \"Potsdam, Germany\",\n" +
                " iata_airport_code: null,\n" +
                " type: \"location\",\n" +
                " country: \"Germany\",\n" +
                "\n" +
                " geo_position: {\n" +
                " latitude: 52.39886,\n" +
                " longitude: 13.06566\n" +
                " },\n" +
                " location_id: 377078,\n" +
                " inEurope: true,\n" +
                " countryCode: \"DE\",\n" +
                " coreCountry: true,\n" +
                " distance: null\n" +
                " },\n" +
                "\n" +
                " {\n" +
                " _id: 410978,\n" +
                " key: null,\n" +
                " name: \"Potsdam\",\n" +
                " fullName: \"Potsdam, USA\",\n" +
                " iata_airport_code: null,\n" +
                " type: \"location\",\n" +
                " country: \"USA\",\n" +
                "\n" +
                " geo_position: {\n" +
                " latitude: 44.66978,\n" +
                " longitude: -74.98131\n" +
                " },\n" +
                "\n" +
                " location_id: 410978,\n" +
                " inEurope: false,\n" +
                " countryCode: \"US\",\n" +
                " coreCountry: false,\n" +
                " distance: null\n" +
                " }\n" +
                " ]";
		List<GeoData> positions = parser.createPositions(raw);
		List<GeoData> expected = new ArrayList<>();
		expected.add(new GeoData(377078, "Potsdam", "location", 52.39886, 13.06566));
		expected.add(new GeoData(410978, "Potsdam", "location", 44.66978, -74.98131));
		Assert.assertEquals(expected, positions);
		
	}
	
	@Test
	public void testSingleResult(){
		GeoDataParser parser = new JsonGeoDataParser();
		String raw = "[\n" +
                "\n" +
                " {\n" +
                "\n" +
                " _id: 377078,\n" +
                " key: null,\n" +
                " name: \"Potsdam\",\n" +
                " fullName: \"Potsdam, Germany\",\n" +
                " iata_airport_code: null,\n" +
                " type: \"location\",\n" +
                " country: \"Germany\",\n" +
                "\n" +
                " geo_position: {\n" +
                " latitude: 52.39886,\n" +
                " longitude: 13.06566\n" +
                " },\n" +
                " location_id: 377078,\n" +
                " inEurope: true,\n" +
                " countryCode: \"DE\",\n" +
                " coreCountry: true,\n" +
                " distance: null\n" +
                " }\n" +
                " ]";
		List<GeoData> positions = parser.createPositions(raw);
		List<GeoData> expected = new ArrayList<>();
		expected.add(new GeoData(377078, "Potsdam", "location", 52.39886, 13.06566));
		Assert.assertEquals(expected, positions);
		
	}
	
	@Test
	public void testNoResults(){
		GeoDataParser parser = new JsonGeoDataParser();
		String raw = "[]";
		List<GeoData> positions = parser.createPositions(raw);
		Assert.assertEquals(0, positions.size());
		
	}
	
	@Test
	public void testInvalidJson(){
		GeoDataParser parser = new JsonGeoDataParser();
		String raw = "{this:\"is not a jsonArray\"}";
		List<GeoData> positions = parser.createPositions(raw);
		Assert.assertEquals(0, positions.size());
		
	}

}
