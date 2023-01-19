package com.myhotel.service.impl;

import com.myhotel.config.ApplicationConfig;
import com.myhotel.dto.EmployeeDTO;
import com.myhotel.dto.SegmentosDTO;
import com.myhotel.model.Employee;
import com.myhotel.repository.EmployeeRepository;
import com.myhotel.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ApplicationConfig applicationConfig;
    @Override
    public Flux<SegmentosDTO> getEmployeexSegment() {

        /*Flux<SegmentosDTO> segmentosDTOFlux = segmentosRepository.listSegmento().doOnNext(e -> System.out.println(e));
        return segmentosDTOFlux;*/
        /*Flux<SegmentosDTO> employeeDTOFlux = employeeRepository.listSegmento().doOnNext(e -> System.out.println(e));



        return employeeDTOFlux;*/
        /*
        Flux<EmployeeDTO> employees = employeeRepository.findAll()
                .flatMap( e -> {
                    return Mono.just(modelMapper().map(e, EmployeeDTO.class));
                });
        Mono<Integer> segmentoA = employees.filter(e -> e.getSalary() < 3500)
                .count()
                .map(a -> {
                    System.out.println("ENTRO:      " + a);
                    return 3;
                });

        Flux<SegmentosDTO> segmentos = Flux.from(Mono.just(new SegmentosDTO("A", segmentoA.block())));*/



        /*employees.filter(e -> e.getSalary() >= 3500 && e.getSalary() < 8000)
                .count()
                .subscribe(c -> segmentos.add(new SegmentosDTO("B", c.intValue())));

        employees.filter(e -> e.getSalary() >= 8000)
                .count()
                .subscribe(c -> segmentos.add(new SegmentosDTO("C", c.intValue())));*/

        //return segmentos;

  /*      Flux<EmployeeDTO> employeeDTOFlux = employeeRepository.findAll()
                                 .flatMap( e -> {
                                     return Mono.just(modelMapper().map(e, EmployeeDTO.class));
                                 })
                                .doOnNext(p -> System.out.println(p))
                                 .flatMap(e -> {
                                     if(e.getSalary() < 3500) {
                                        e.setSegmento("A");
                                     } else if(e.getSalary() >= 3500 && e.getSalary() < 8000){
                                        e.setSegmento("B");
                                     } else {
                                        e.setSegmento("C");
                                     }
                                        return Mono.just(e);
                                 });

        Mono<SegmentosDTO> segmentoA = employeeDTOFlux
                .filter(x -> x.getSegmento().equalsIgnoreCase("A"))
                .count()
                .doOnNext(p -> System.out.println(p))
                .flatMap(s -> {
                    return Mono.just(new SegmentosDTO("A", s.intValue()));
                });
*/

        List<Employee> list = employeeRepository.findAll();
        List<SegmentosDTO> segmentos = new ArrayList<>();
        Long segementoA = list.stream()
                .filter(x -> x.getSalary() < 3500)
                .count();
        Long segementoB = list.stream()
                .filter(x -> x.getSalary() >= 3500 && x.getSalary() < 8000)
                .count();
        Long segementoC = list.stream()
                .filter(x -> x.getSalary() >= 8000)
                .count();
        segmentos.add( new SegmentosDTO("A", segementoA.intValue()));
        segmentos.add( new SegmentosDTO("B", segementoB.intValue()));
        segmentos.add( new SegmentosDTO("C", segementoC.intValue()));


        return  Flux.fromIterable(segmentos);
    }

    @Override
    public Flux<EmployeeDTO> getEmployeeOldYears() {
        List<EmployeeDTO> employees = employeeRepository
                .findManagers()
                .stream()
                .map(e -> {
                    return applicationConfig.modelMapper().map(e, EmployeeDTO.class);
                })
                .collect(Collectors.toList());


        return Flux.fromIterable(employees);
    }


}
