package com.app.issues.serviceImpl;

import com.app.issues.dao.IssueDao;
import com.app.issues.dao.MovementDao;
import com.app.issues.entity.IssueEntity;
import com.app.issues.entity.MovementEntity;
import com.app.issues.enums.StatusEnum;
import com.app.issues.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
  private Logger logger = LoggerFactory.getLogger(IssueServiceImpl.class);

  @Autowired
  private IssueDao issueDao;

  @Autowired
  private MovementDao executionCountRepository;

  @Override
  public List<IssueEntity> findAll() {
    logger.info("find all issues");
    logMethodExecution("finAll");
    return issueDao.findAll();
  }

  @Override
  public IssueEntity findById(Long id) {
    logger.info("find issue {}", id);
    return issueDao.findById(id).orElse(null);
  }

  @Override
  public IssueEntity save(IssueEntity issue) {
    logger.info("save issue ", issue.getTitle());
    logMethodExecution("insert");

    IssueEntity issueCreated = issueDao.save(issue);

    if (issueCreated != null) {
     this.changestatus(issue.getStatus().getValue());
     return issueCreated;
    }

    return null;
  }

  @Override
  public IssueEntity edit(IssueEntity issue) {
    logger.info("edit issue {}", issue.toString());

    this.changestatus(issue.getStatus().getValue());

    return issueDao.save(issue);
  }

  @Override
  public void delete(Long id) {
    logger.info("delete issue");
    issueDao.deleteById(id);
  }

  private void logMethodExecution(String methodName) {
    logger.info("Executing method: {}", methodName);
    MovementEntity executionCount = executionCountRepository.findByMethodName(methodName);
    if (executionCount == null) {
      executionCount = new MovementEntity();
      executionCount.setMethodName(methodName);
      executionCount.setExecutionCount(1L);
    } else {
      executionCount.setExecutionCount(executionCount.getExecutionCount() + 1);
    }
    executionCountRepository.save(executionCount);
  }

  private void changestatus(String status) {
    if (status.equals(StatusEnum.OPEN.getValue())) {
      logMethodExecution("open");
    }

    if (status.equals(StatusEnum.CLOSED.getValue())) {
      logMethodExecution("closed");
    }

  }
}
