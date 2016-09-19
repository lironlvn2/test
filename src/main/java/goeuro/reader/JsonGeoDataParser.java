package goeuro.reader;

import goeuro.entities.GeoData;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonGeoDataParser implements GeoDataParser{

	private static final Logger logger = Logger.getLogger(JsonGeoDataParser.class.getName());
	
	public static final String ID_FIELD = "_id";
	public static final String NAME_FIELD = "name";
	public static final String TYPE_FIELD = "type";
	public static final String POSITION_FIELD = "geo_position";
	public static final String LAT_FIELD = "latitude";
	public static final String LON_FIELD = "longitude";
	
	public List<GeoData> createPositions(String raw){
		List<GeoData> positions = new ArrayList<>();
		JSONArray jsonArr;
		try {
			jsonArr = new JSONArray(raw);

			for (int i=0; i<jsonArr.length(); i++){
				try {
					positions.add(createGeoData(jsonArr.getJSONObject(i)));
				} catch (JSONException e) {
					logger.log(Level.INFO,"error in one position data. ignoring it");
				}
			}
		} catch (JSONException e1) {
			logger.log(Level.INFO,"error parsing result to json. no results");
		}
		return positions;
	}

	private GeoData createGeoData(JSONObject json) throws JSONException {
		long id = json.getLong(ID_FIELD);
		String name = json.getString(NAME_FIELD);
		String type = json.getString(TYPE_FIELD);
		JSONObject geo = json.getJSONObject(POSITION_FIELD);
		double lat = geo.getDouble(LAT_FIELD);
		double lon = geo.getDouble(LON_FIELD);
		return new GeoData(id, name, type, lat, lon);
	}
}
