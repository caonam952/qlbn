package com.caonam.qlbn.dao;

import com.caonam.qlbn.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//    @Query(value = "UPDATE employee " +
//            "SET name = ?1, position = ?2 " +
//            "WHERE id = ?3", nativeQuery = true)
//    void updateEmployee(String name, String position, int id);
}
