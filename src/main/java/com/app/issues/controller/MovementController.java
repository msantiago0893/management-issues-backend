package com.app.issues.controller;

import com.app.issues.entity.MovementEntity;
import com.app.issues.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
public class MovementController {

  @Autowired
  private MovementService movementService;

  @GetMapping("/all")
  public List<MovementEntity> getAllMethodExecutionCounts() {
    return movementService.findAll();
  }

  @GetMapping("/{id}")
  public MovementEntity getById(@PathVariable Long id) {
    return movementService.findById(id);
  }

  @GetMapping
  public MovementEntity getByName(@RequestParam(name = "name") String name) {
    return movementService.findByName(name);
  }
}
