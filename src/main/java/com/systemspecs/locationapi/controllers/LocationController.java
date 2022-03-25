package com.systemspecs.locationapi.controllers;


import com.systemspecs.locationapi.domain.LocationRequest;
import com.systemspecs.locationapi.exception.ResourceNotFound;
import com.systemspecs.locationapi.models.Location;
import com.systemspecs.locationapi.repositories.LocationRepository;
import com.systemspecs.locationapi.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static java.lang.Math.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    //GET ALL LOCATIONS API
    @GetMapping
    public List<Location> getAllLocations(){
        return (List<Location>)locationRepository.findAll();
    }

    //CREATE NEW LOCATION
    @PostMapping("/create")
    public ResponseEntity<?> createLocation(@Validated @RequestBody LocationRequest locationRequest){
        return locationService.createLocation(locationRequest);
    }

    //GET LOCATION BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getLocationById(@PathVariable(name = "id") long id) throws ResourceNotFound{
        Location location = locationRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Location with this ID : "+ id +" doesn't exist"));
        return ResponseEntity.ok().body(location);
    }

    //UPDATE LOCATIONS
    @PutMapping("/{id}")
    public ResponseEntity<Object> getLocationById(@PathVariable(name = "id") long id, @RequestBody Location locationDetails) throws ResourceNotFound {
        Location location = locationRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Location with this ID : " + id + " doesn't exist"));

        location.setLocationName(locationDetails.getLocationName());
        location.setDescription(locationDetails.getDescription());
        location.setWebsite(locationDetails.getWebsite());
        location.setContactPerson(locationDetails.getContactPerson());
        location.setPhone(locationDetails.getPhone());
        location.setLongitude(locationDetails.getLongitude());
        location.setLatitude(locationDetails.getLatitude());

        return ResponseEntity.ok().body(location);
    }

    //DELETE LOCATION
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLocation(@PathVariable(value = "id") long locationId) throws ResourceNotFound{
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new ResourceNotFound("Location with ID: " + locationId + " not found"));
        locationRepository.deleteById(locationId);
        return ResponseEntity.ok().build();
    }

    //CALCULATE DISTANCE
    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<Object> getLocationById(@PathVariable(name = "id1") long id1, @PathVariable(name = "id2") long id2) throws ResourceNotFound{


        //a
        Double location1Lat = locationRepository.findById(id1).get().getLatitude();
        //x
        Double location1Long = locationRepository.findById(id1).get().getLongitude();

        //b
        Double location2Lat = locationRepository.findById(id1).get().getLatitude();
        //y
        Double location2Long = locationRepository.findById(id2).get().getLongitude();

        //radius of the earth
        Double rad = 6378.0;
        Double location = rad * cos(1*(cos(location1Lat) * cos(location2Lat) * cos(location1Long - location2Long)) + (sin(location1Lat) * sin(location2Lat)));
        return ResponseEntity.ok().body("Distance between Location " + locationRepository.findById(id1).get().getLocationName() + " and " + locationRepository.findById(id2).get().getLocationName()+ " is: " + abs(location));
    }



}
