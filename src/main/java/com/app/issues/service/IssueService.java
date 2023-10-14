package com.app.issues.service;

import com.app.issues.entity.IssueEntity;

import java.util.List;

public interface IssueService {
  public List<IssueEntity> findAll();

  public IssueEntity findById(Long id);

  public IssueEntity save(IssueEntity issue);

  public IssueEntity edit(IssueEntity issue);

  public void delete(Long id);
}
