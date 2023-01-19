package com.myhotel.repository;

import com.myhotel.dto.CountryDTO;
import com.myhotel.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "SELECT new com.myhotel.dto.CountryDTO(c.countryId, c.countryName, COUNT(e), " +
            "COALESCE(AVG(e.salary),0.0), COALESCE(MAX(e.salary),0.0), COALESCE(min(e.salary),0.0), 35.0) " +
            "FROM Country c " +
            "LEFT JOIN Location l ON c.countryId = l.countryId " +
            "LEFT JOIN Department d ON l.locationId = d.locationId " +
            "LEFT JOIN Employee e ON d.departmentId = e.departmentId " +
            "GROUP BY c.countryId, c.countryName")
    List<CountryDTO> findInfoEmployeeWithCountry();
}

//"COALESCE(AVG(e.salary),0), COALESCE(MAX(e.salary),0), COALESCE(min(e.salary),0), COALESCE(AVG(TIMESTAMPDIFF(YEAR, e.hireDate, CURDATE())),0)) " +