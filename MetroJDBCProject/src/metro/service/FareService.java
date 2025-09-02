package metro.service;

public interface FareService {
	double fareAmount=5;
    double calculateFare(int startStationId, int endStationId);
}