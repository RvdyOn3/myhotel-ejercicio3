package com.myhotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="departments")
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    private BigDecimal departmentId;
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    @Column(name = "MANAGER_ID")
    private BigDecimal managerId;
    @Column(name = "LOCATION_ID")
    private BigDecimal locationId;
}
