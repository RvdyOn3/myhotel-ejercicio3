package com.myhotel.service.impl;

import com.myhotel.dto.DepartmentDTO;
import com.myhotel.repository.DepartmentRepository;
import com.myhotel.dto.DepartmentAVG;
import com.myhotel.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Flux<DepartmentDTO> getDepartmentSalaryxSegment() {
        List<DepartmentDTO> departmentDTOS = departmentRepository.findDepartmentSalary();
        departmentDTOS = departmentDTOS.stream().map(x -> {
           if(x.getSalary() < 3500) x.setSegment("A");
           else if(x.getSalary() >= 3500 && x.getSalary() < 8000) x.setSegment("B");
           else x.setSegment("C");
           return x;
        }).collect(Collectors.toList());
        return Flux.fromIterable(departmentDTOS);
    }

    @Override
    public Flux<DepartmentDTO> getEmployeeMaxSalaryWithDepartment() {
        List<DepartmentDTO> departmentDTOS = departmentRepository.findMaxSalaryEmployeeWithDepartment();
        return Flux.fromIterable(departmentDTOS);
    }

    @Override
    public Flux<DepartmentAVG> getAVGSalaryWithDepartment() {
        List<DepartmentAVG> departmentAVGS = departmentRepository.findAVGSalaryWithDepartment();
        return Flux.fromIterable(departmentAVGS);
    }
}
