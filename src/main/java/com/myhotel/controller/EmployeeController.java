package com.myhotel.controller;

import com.myhotel.dto.EmployeeDTO;
import com.myhotel.dto.SegmentosDTO;
import com.myhotel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/segment")
    ResponseEntity<Flux<SegmentosDTO>> getEmployeexSegment(){
        Flux<SegmentosDTO> employeeFlux = employeeService.getEmployeexSegment();
        return new ResponseEntity<Flux<SegmentosDTO>>(employeeFlux, HttpStatus.OK);
    }

    @GetMapping("/managers-old")
    ResponseEntity<Flux<EmployeeDTO>> getEmployeeOldYears(){
        Flux<EmployeeDTO> employeeFlux = employeeService.getEmployeeOldYears();
        return new ResponseEntity<Flux<EmployeeDTO>>(employeeFlux, HttpStatus.OK);
    }
}
