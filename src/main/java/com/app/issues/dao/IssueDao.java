package com.app.issues.dao;

import com.app.issues.entity.IssueEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueDao extends CrudRepository<IssueEntity, Long> {
  List<IssueEntity> findAll();
  List<IssueEntity> findByOperator(String operador);
}
