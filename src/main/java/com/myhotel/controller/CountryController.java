package com.myhotel.controller;

import com.myhotel.dto.CountryDTO;
import com.myhotel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;
    @GetMapping("/employee-info")
    ResponseEntity<Flux<CountryDTO>> getAVGSalaryWithDepartment(){
        Flux<CountryDTO> countryDTOFlux = countryService.getInfoEmployeeWithCountry();
        return new ResponseEntity<Flux<CountryDTO>>(countryDTOFlux, HttpStatus.OK);
    }
}
