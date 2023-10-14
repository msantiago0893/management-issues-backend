package com.app.issues.service;

import com.app.issues.entity.MovementEntity;

import java.util.List;

public interface MovementService {
  List<MovementEntity> findAll();
  MovementEntity findById(Long id);
  MovementEntity findByName(String name);
}
