package com.app.issues.serviceImpl;

import com.app.issues.dao.MovementDao;
import com.app.issues.entity.MovementEntity;
import com.app.issues.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl implements MovementService {
  @Autowired
  private MovementDao movementDao;


  @Override
  public List<MovementEntity> findAll() {
    return movementDao.findAll();
  }

  @Override
  public MovementEntity findById(Long id) {
    Optional<MovementEntity> optionalEntity = movementDao.findById(id);
    return optionalEntity.orElse(null);
  }

  @Override
  public MovementEntity findByName(String name) {
    return movementDao.findByMethodName(name);
  }
}
