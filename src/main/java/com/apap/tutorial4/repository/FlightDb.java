package com.apap.tutorial4.repository;
import com.apap.tutorial4.model.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long> {
	List<FlightModel> findByPilotLicenseNumber(String licenseNumber);
	void deleteById(long id);
	FlightModel findById(long id);
}
