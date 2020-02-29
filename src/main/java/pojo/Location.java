package pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created Using : http://www.jsonschema2pojo.org/
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"post code",
		"country",
		"country abbreviation",
		"places"
})
public class Location {
	@JsonProperty("post code")
	private String postCode;
	@JsonProperty("country")
	private String country;
	@JsonProperty("country abbreviation")
	private String countryAbbreviation;
	@JsonProperty("places")
	private List<Place> places = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("post code")
	public String getPostCode() {
		return postCode;
	}

	@JsonProperty("post code")
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("country abbreviation")
	public String getCountryAbbreviation() {
		return countryAbbreviation;
	}

	@JsonProperty("country abbreviation")
	public void setCountryAbbreviation(String countryAbbreviation) {
		this.countryAbbreviation = countryAbbreviation;
	}

	@JsonProperty("places")
	public List<Place> getPlaces() {
		return places;
	}

	@JsonProperty("places")
	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@Override
	public String toString() {
		return "Location{" +
				"postCode='" + postCode + '\'' +
				", country='" + country + '\'' +
				", countryAbbreviation='" + countryAbbreviation + '\'' +
				", places=" + places +
				", additionalProperties=" + additionalProperties +
				'}';
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
