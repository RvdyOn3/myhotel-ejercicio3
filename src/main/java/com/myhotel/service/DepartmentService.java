package com.myhotel.service;

import com.myhotel.dto.DepartmentDTO;
import com.myhotel.dto.DepartmentAVG;
import reactor.core.publisher.Flux;

public interface DepartmentService {

    Flux<DepartmentDTO> getDepartmentSalaryxSegment();
    Flux<DepartmentDTO> getEmployeeMaxSalaryWithDepartment();
    Flux<DepartmentAVG> getAVGSalaryWithDepartment();
}
