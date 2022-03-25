package com.systemspecs.locationapi.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "locations")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "location_name", length = 100)
    private String locationName;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "website", nullable = true)
    private String website;

    @Column(name = "phone")
    private String phone;

    @Column(name = "contact_person", length = 100)
    private String contactPerson;

    @Column(name = "longitude", length = 25)
    private Double longitude;

    @Column(name = "latitude", length = 25)
    private Double latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }


    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
