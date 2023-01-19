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
public class DepartmentAVG {
    private BigDecimal departmentId;
    private String departmentName;
    private Long quantityEmployee;
    private Double averageSalary;
}
