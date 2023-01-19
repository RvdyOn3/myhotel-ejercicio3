package com.myhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private BigDecimal employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private Double salary;
    private BigDecimal commissionPct;
    private BigDecimal managerId;
    private BigDecimal departmentId;
    private String segmento;

    public EmployeeDTO(BigDecimal employeeId, String firstName, String lastName, Date hireDate, String jobId){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.jobId = jobId;
    }
}
