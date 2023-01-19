package com.myhotel.service;

import com.myhotel.dto.CountryDTO;
import reactor.core.publisher.Flux;

public interface CountryService {

    Flux<CountryDTO> getInfoEmployeeWithCountry();
}
