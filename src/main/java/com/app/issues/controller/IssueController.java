package com.app.issues.controller;

import com.app.issues.entity.IssueEntity;
import com.app.issues.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/issue")
public class IssueController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private IssueService issueService;

  @GetMapping
  public List<IssueEntity> getAll() {
    log.info("Fetching all issues");
    return issueService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<IssueEntity> getById(@PathVariable Long id) {
    log.info("Fetching issue by id: {}", id);

    IssueEntity issue = issueService.findById(id);

    if (issue == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(issue);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody IssueEntity issue) {
    log.info("Creating issue: {}", issue);

    IssueEntity issueCreated = issueService.save(issue);

    return ResponseEntity.status(HttpStatus.CREATED).body(issueCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<IssueEntity> update(
    @RequestBody IssueEntity issue,
    @PathVariable Long id
  ) {
    log.info("update issue: {}", id );

    IssueEntity issueFound = issueService.findById(id);

    issueFound.setTitle(issue.getTitle());
    issueFound.setOperator(issue.getOperator());
    issueFound.setDescription(issue.getDescription());
    issueFound.setStatus(issue.getStatus());
    issueFound.setCreationAt(issue.getCreationAt());

    issueService.edit(issueFound);

    return ResponseEntity.ok(issueFound);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    log.info("Deleting issue by id: {}", id);

    IssueEntity issueFound = issueService.findById(id);

    if (issueFound == null) {
      return ResponseEntity.notFound().build();
    }

    issueService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
