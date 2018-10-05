package com.apap.tutorial4.service;
import com.apap.tutorial4.model.FlightModel;
import java.util.List;

public interface FlightService {
	List<FlightModel> getFlightDetailByPilot(String licenseNumber);
	FlightModel getFlightDetailById(long id);
	void addFlight(FlightModel flight);
	void deleteById(long id);
	void updateFlight(long id, FlightModel newFlight);
}
