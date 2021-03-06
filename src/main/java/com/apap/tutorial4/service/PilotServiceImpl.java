package com.apap.tutorial4.service;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDb pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	@Override
	public void deleteByLicenseNumber(String licenseNumber) {
		pilotDb.deleteByLicenseNumber(licenseNumber);
	}

	@Override
	public void updatePilot(String licenseNumber, PilotModel newPilot) {
		PilotModel updatePilot = pilotDb.findByLicenseNumber(licenseNumber);
		updatePilot.setFlyHour(newPilot.getFlyHour());
		updatePilot.setLicenseNumber(newPilot.getLicenseNumber());
		updatePilot.setName(newPilot.getName());
		pilotDb.save(updatePilot);
	}
}
