package com.systemspecs.locationapi.services;

import com.systemspecs.locationapi.domain.LocationRequest;
import com.systemspecs.locationapi.models.Location;
import com.systemspecs.locationapi.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;


    public ResponseEntity<?> createLocation(LocationRequest locationRequest) {
        Location location = new Location();

        //location.setLocationName(locationRequest.getLocationName());
        location.setLocationName(locationRequest.getLocationName());
        location.setContactPerson(locationRequest.getContactPerson());
        location.setLongitude(locationRequest.getLongitude());
        location.setLatitude(locationRequest.getLatitude());
        location.setDescription(locationRequest.getDescription());
        location.setPhone(locationRequest.getPhone());
        location.setWebsite(locationRequest.getWebsite());

        locationRepository.save(location);
        return ResponseEntity.ok(location);

    }
}
