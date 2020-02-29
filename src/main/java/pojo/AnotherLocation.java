package pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"latitude",
		"longitude"
})
public class AnotherLocation {

	@JsonProperty("latitude")
	private Double lat;
	@JsonProperty("longitude")
	private Double lng;
	/*@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();*/

	@JsonProperty("latitude")
	public Double getLat() {
		return lat;
	}

	@JsonProperty("latitude")
	public void setLat(Double lat) {
		this.lat = lat;
	}

	@JsonProperty("longitude")
	public Double getLng() {
		return lng;
	}

	@JsonProperty("longitude")
	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "AnotherLocation{" +
				"lat=" + lat +
				", lng=" + lng +
				'}';
	}

/*	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}*/

}
