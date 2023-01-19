package com.myhotel.service;

import com.myhotel.dto.EmployeeDTO;
import com.myhotel.dto.SegmentosDTO;
import reactor.core.publisher.Flux;

public interface EmployeeService {

    Flux<SegmentosDTO> getEmployeexSegment();
    Flux<EmployeeDTO> getEmployeeOldYears();
}
