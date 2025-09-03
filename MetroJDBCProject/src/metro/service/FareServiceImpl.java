package metro.service;

public class FareServiceImpl implements FareService {

	@Override
	public double calculateFare(int startStationId, int endStationId) {
		
		return Math.abs((endStationId-startStationId)*FareService.fareAmount);
	}

}
