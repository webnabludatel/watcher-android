package org.dvaletin.apps.nabludatel.utils;

public class Violation {
	double lat, lng;
	String key;
	String value;
	long district_id;
	String violation;
	long timestamp;
	
	public Violation(double lat, double lng, String key,
			long currentTimeMillis, String value,
			long mCurrentElectionsDistrict, String violation) {
		this.lat = lat;
		this.lng = lng;
		this.key = key;
		this.timestamp = currentTimeMillis;
		this.value = value;
		this.district_id = mCurrentElectionsDistrict;
		this.violation = violation;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	public void setCoordinates(double lat2, double lng2) {
		this.lat = lat2;
		this.lng = lng2;
		
	}

	public void setTimeStamp(long currentTimeMillis) {
		this.timestamp = currentTimeMillis;
		
	}

	public double getLat() {
		return lat;
	}

	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(double lng) {
		this.lng = lng;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the district
	 */
	public long getDistrict() {
		return district_id;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(long district) {
		this.district_id = district;
	}

	/**
	 * @return the violation
	 */
	public String getViolation() {
		return violation;
	}

	/**
	 * @param violation the violation to set
	 */
	public void setViolation(String violation) {
		this.violation = violation;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
}
