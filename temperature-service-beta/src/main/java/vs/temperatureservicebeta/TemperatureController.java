package vs.temperatureservicebeta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("beta")
public class TemperatureController {

	@Value("${temperature.acceptable.min}")
	private double acceptableMin;

	@Value("${temperature.acceptable.max}")
	private double acceptableMax;

	@Value("${temperature.critical.min}")
	private double criticalMin;

	@Value("${temperature.critical.max}")
	private double criticalMax;

	@Value("${error.percentage}")
	private int errorPercentage;

	@GetMapping
	public BigDecimal temperature() {
		if (errorPercentage() <= errorPercentage) {
			return createTemperature(criticalMin, criticalMax);
		}
		return createTemperature(acceptableMin, acceptableMax);
	}
	
	@GetMapping("temperatures")
	public ObjectNode temperatures() {
		return new ObjectMapper().createObjectNode()
				.put("acceptableMin", acceptableMin)
				.put("acceptableMax", acceptableMax)
				.put("criticalMin", criticalMin)
				.put("criticalMax", criticalMax);
	}

	private BigDecimal createTemperature(double min, double max) {
		return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(min, max)).setScale(2, RoundingMode.HALF_EVEN);
	}

	private int errorPercentage() {
		return ThreadLocalRandom.current().nextInt(1, 11);
	}

}
