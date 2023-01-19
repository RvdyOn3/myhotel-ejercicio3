package com.myhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private BigDecimal departmentId;
    private String departmentName;
    private Double salary;
    private String segment;
    private String employeeName;
}
