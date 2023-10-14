package com.app.issues.dao;

import com.app.issues.entity.IssueEntity;
import com.app.issues.entity.MovementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementDao extends CrudRepository<MovementEntity, Long> {
  List<MovementEntity> findAll();
  MovementEntity findByMethodName(String methodName);
}
