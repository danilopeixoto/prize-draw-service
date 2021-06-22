package com.meli.prizedraw.api.repository;

import com.meli.prizedraw.api.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {
  List<StudentModel> findByNameContaining(String name);

  @Query(nativeQuery = true, value = "select * from Student order by random() limit :count")
  List<StudentModel> findOrderByRandomLimited(@Param("count") Integer count);
}
