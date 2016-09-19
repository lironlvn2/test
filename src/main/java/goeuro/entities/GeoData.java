package goeuro.entities;


public class GeoData {

	private long id;
	private String name;
	private String type;
	private double lat;
	private double lon;
	
	public GeoData(long id, String name, String type, double lat, double lon) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.lat = lat;
		this.lon = lon;
	}
	
	public String[] toArr(){
		return new String[]{String.valueOf(id), name, type, String.valueOf(lat), String.valueOf(lon)};
	}

	@Override
	public String toString() {
		return "GeoData [id=" + id + ", name=" + name + ", type=" + type
				+ ", lat=" + lat + ", lon=" + lon + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoData other = (GeoData) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
		
	
	
	
}
