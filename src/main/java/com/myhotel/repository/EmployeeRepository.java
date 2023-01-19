package com.myhotel.repository;

import com.myhotel.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, BigDecimal> {

    @Query(value = "SELECT e " +
            "FROM Employee e " +
            "WHERE e.jobId IN('FI_MGR', 'AC_MGR', 'SA_MAN', 'PU_MAN', 'ST_MAN', 'MK_MAN') " +
            "AND timestampdiff(YEAR, e.hireDate, CURDATE()) > 15")
    List<Employee> findManagers();
}
