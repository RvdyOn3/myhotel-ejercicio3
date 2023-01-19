package com.myhotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="locations")
public class Location {

    @Id
    @Column(name = "LOCATION_ID")
    private BigDecimal locationId;
    @Column(name = "STREET_ADDRESS")
    private String streetAddress;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE_PROVINCE")
    private String stateProvince;
    @Column(name = "COUNTRY_ID")
    private String countryId;
}
