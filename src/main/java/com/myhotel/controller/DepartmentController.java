package com.myhotel.controller;

import com.myhotel.dto.DepartmentDTO;
import com.myhotel.service.DepartmentService;
import com.myhotel.dto.DepartmentAVG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;

    @GetMapping("/segment-salary")
    ResponseEntity<Flux<DepartmentDTO>> getDepartmentSalaryxSegment(){
        Flux<DepartmentDTO> departmentDTOFlux = departmentService.getDepartmentSalaryxSegment();
        return new ResponseEntity<Flux<DepartmentDTO>>(departmentDTOFlux, HttpStatus.OK);
    }

    @GetMapping("/max-salary")
    ResponseEntity<Flux<DepartmentDTO>> getEmployeeMaxSalaryWithDepartment(){
        Flux<DepartmentDTO> departmentDTOFlux = departmentService.getEmployeeMaxSalaryWithDepartment();
        return new ResponseEntity<Flux<DepartmentDTO>>(departmentDTOFlux, HttpStatus.OK);
    }

    @GetMapping("/avg-salary")
    ResponseEntity<Flux<DepartmentAVG>> getAVGSalaryWithDepartment(){
        Flux<DepartmentAVG> departmentDTOFlux = departmentService.getAVGSalaryWithDepartment();
        return new ResponseEntity<Flux<DepartmentAVG>>(departmentDTOFlux, HttpStatus.OK);
    }
}
