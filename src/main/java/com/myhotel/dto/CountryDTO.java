package com.myhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {

    private String countryId;
    private String countryName;
    private Long employee;
    private Double salaryAvg;
    private Double salaryMax;
    private Double salaryMin;
    private Double yearAvg;
}
