package vs.temperaturegateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("temperatures")
public class TemperatureController {
	
	@Autowired
	TemperatureService temperatureService;
	
	@GetMapping("ranges")
	public TemperatureRanges ranges() {
		return temperatureService.temperatureRanges();
	}

}
