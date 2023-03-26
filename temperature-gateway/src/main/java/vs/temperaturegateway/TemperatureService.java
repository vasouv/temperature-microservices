package vs.temperaturegateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TemperatureService {
	
	@Value("${temperature.service.alpha}")
	private String alphaUrl;
	
	@Value("${temperature.service.beta}")
	private String betaUrl;
	
	private RestTemplate client = new RestTemplate();
	
	private final String RANGES_URL = "/temperatures";
	private final String ALPHA = "alpha";
	private final String BETA = "beta";
	
	public TemperatureRanges temperatureRanges() {
		TemperatureRange alpha = forService(ALPHA);
		TemperatureRange beta = forService(BETA);
		return new TemperatureRanges(alpha, beta);
	}
			
	public TemperatureRange forService(String serviceName) {
		String serviceUrl = UriComponentsBuilder
				.fromUriString(determineServiceUrl(serviceName))
				.path("/{serviceName}")
				.path("/{rangesUrl}")
				.buildAndExpand(serviceName, RANGES_URL)
				.toString();
		return client.getForObject(serviceUrl, TemperatureRange.class);
	}
	
	private String determineServiceUrl(String name) {
		return switch (name) {
		    case "alpha" -> alphaUrl; 
		    case "beta" -> betaUrl;
		    default -> throw new IllegalArgumentException("Unexpected value: " + name);
		};
	}

}
