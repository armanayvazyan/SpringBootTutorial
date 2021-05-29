package com.learn.springboot.repository;

import com.learn.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByFirstName(String firstName);

    List<Student> findAllByLastName(String lastName);

    List<Student> findAllByFirstNameContaining(String firstName);

    List<Student> findAllByFirstNameStartingWith(String firstName);

    List<Student> findAllByFirstNameEndingWith(String firstName);

    List<Student> findAllByFirstNameStartsWith(String firstName);

    List<Student> findAllByAddressCity(String city);

    @Query("from Student where address.city = :city")
    List<Student> findAllByAddressCityQuery(String city);


}
