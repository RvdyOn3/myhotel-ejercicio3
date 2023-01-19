package com.myhotel.service.impl;

import com.myhotel.config.ApplicationConfig;
import com.myhotel.dto.CountryDTO;
import com.myhotel.repository.CountryRepository;
import com.myhotel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CountryImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Override
    public Flux<CountryDTO> getInfoEmployeeWithCountry() {
        List<CountryDTO> countryDTOS = countryRepository.findInfoEmployeeWithCountry();


        return Flux.fromIterable(countryDTOS);
    }
}
