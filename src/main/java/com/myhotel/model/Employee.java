package com.myhotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
/*@NamedNativeQuery(name = "Employee.listSegmento",
        query="SELECT s.SEGMENTO AS typeSegment, COUNT(s.EMPLOYEE_ID) AS countEmployees" +
                "FROM " +
                "(SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.LAST_NAME, e.SALARY," +
                "CASE WHEN e.SALARY < 3500 then 'SEGMENTO A' WHEN e.SALARY >= 3500.00 AND e.SALARY < 8000.00  then 'SEGMENTO B' WHEN e.SALARY >= 8000.00 then 'SEGMENTO C' END AS SEGMENTO" +
                "FROM employees e) AS s" +
                "GROUP BY s.segmento",
        resultSetMapping = "Mapping.SegmentosDTO")
@SqlResultSetMapping(name = "Mapping.SegmentosDTO",
                       classes =@ConstructorResult(targetClass = SegmentosDTO.class,
                       columns = {
                               @ColumnResult(name = "typeSegment", type = String.class),
                               @ColumnResult(name = "countEmployees", type = Integer.class)
                       }))*/
@Table(name ="employees")
public class Employee {

    @Id
    @Column(name= "EMPLOYEE_ID")
    private BigDecimal employeeId;
    @Column(name= "FIRST_NAME")
    private String firstName;
    @Column(name= "LAST_NAME")
    private String lastName;
    @Column(name= "EMAIL")
    private String email;
    @Column(name= "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name= "HIRE_DATE")
    private Date hireDate;
    @Column(name= "JOB_ID")
    private String jobId;
    @Column(name= "SALARY")
    private Double salary;
    @Column(name= "COMMISSION_PCT")
    private BigDecimal commissionPct;
    @Column(name= "MANAGER_ID")
    private BigDecimal managerId;
    @Column(name= "DEPARTMENT_ID")
    private BigDecimal departmentId;
}
