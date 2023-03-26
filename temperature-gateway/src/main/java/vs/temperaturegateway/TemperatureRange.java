package vs.temperaturegateway;

public record TemperatureRange(Double acceptableMin, Double acceptableMax, 
		Double criticalMin, Double criticalMax) {

}