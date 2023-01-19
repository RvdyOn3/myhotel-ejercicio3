package com.myhotel.repository;

import com.myhotel.model.Department;
import com.myhotel.dto.DepartmentAVG;
import com.myhotel.dto.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, BigDecimal> {

    @Query(value = "SELECT new com.myhotel.dto.DepartmentDTO(d.departmentId, d.departmentName, SUM(e.salary), '', '') " +
            "FROM Department d INNER JOIN Employee e ON d.departmentId = e.departmentId " +
            "GROUP BY d.departmentId, d.departmentName " +
            "ORDER BY e.departmentId")
    List<DepartmentDTO> findDepartmentSalary();

    @Query(value = "SELECT new com.myhotel.dto.DepartmentDTO(d.departmentId, d.departmentName, MAX(e.salary), '', CONCAT(e.firstName, ' ' , e.lastName))" +
            "FROM Department d INNER JOIN Employee e ON d.departmentId = e.departmentId " +
            "GROUP BY d.departmentId " +
            "ORDER BY d.departmentId")
    List<DepartmentDTO> findMaxSalaryEmployeeWithDepartment();

    @Query(value = "SELECT new com.myhotel.dto.DepartmentAVG(d.departmentId, d.departmentName, COUNT(e), AVG(e.salary)) " +
            "FROM Department d " +
            "INNER JOIN Employee e on d.departmentId = e.departmentId " +
            "GROUP BY d.departmentId, d.departmentName " +
            "HAVING count(e.employeeId) > 10")
    List<DepartmentAVG> findAVGSalaryWithDepartment();

}
