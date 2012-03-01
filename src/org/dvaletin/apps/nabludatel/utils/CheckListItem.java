package org.dvaletin.apps.nabludatel.utils;

public class CheckListItem {
	private long id;
	private double lat, lng;
	private String key;
	private String value;
	private long pollingPlaceId;
	private String violation;
	private long timestamp;
	private boolean changed;
	
	public CheckListItem(long id, long currentTimeMillis, double lat, double lng, String key,
						 String value,
						 long pollingPlaceId, String violation) {
		this.id = id;
		this.lat = lat;
		this.lng = lng;
		this.key = key;
		this.timestamp = currentTimeMillis;
		this.value = value;
		this.pollingPlaceId = pollingPlaceId;
		this.violation = violation;
		this.changed = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		updateChanged(id, this.id);
		this.id = id;
	}

	public long getPollingPlaceId() {
		return pollingPlaceId;
	}

	public void setPollingPlaceId(long pollingPlaceId) {
		updateChanged(pollingPlaceId, this.pollingPlaceId);
		this.pollingPlaceId = pollingPlaceId;
	}

	public void setValue(String value) {
		updateChanged(value, this.value);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setCoordinates(double lat, double lng) {
		setLat(lat);
		setLng(lng);
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLat(double lat) {
		updateChanged(lat, this.lat);
		this.lat = lat;
	}

	public void setLng(double lng) {
		updateChanged(lng, this.lng);
		this.lng = lng;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		updateChanged(key, this.key);
		this.key = key;
	}

	public String getViolation() {
		return violation;
	}

	public void setViolation(String violation) {
		updateChanged(violation, this.violation);
		this.violation = violation;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		updateChanged(timestamp, this.timestamp);
		this.timestamp = timestamp;
	}

	private void updateChanged(Object newValue, Object oldValue) {
		this.changed |= oldValue != null && !oldValue.equals(newValue) || oldValue == null && newValue != null;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}
}
