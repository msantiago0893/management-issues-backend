package com.app.issues.controller;
import com.app.issues.entity.MovementEntity;
import com.app.issues.service.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovementController.class)
@AutoConfigureMockMvc
class MovementControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Mock
  private MovementService movementService;

  @InjectMocks
  private MovementController movementController;

  @BeforeEach
  public void setUp() {
    when(movementService.findAll()).thenReturn(Arrays.asList(new MovementEntity(), new MovementEntity()));
    when(movementService.findById(1L)).thenReturn(new MovementEntity());
    when(movementService.findByName("exampleName")).thenReturn(new MovementEntity());
  }

  @Test
  public void testGetAllMethodExecutionCounts() throws Exception {
    mockMvc.perform(get("/api/v1/operation/all"))
            .andExpect(status().isOk());
  }

  @Test
  public void testGetById() throws Exception {
    mockMvc.perform(get("/api/v1/operation/1"))
            .andExpect(status().isOk());
  }

  @Test
  public void testGetByName() throws Exception {
    mockMvc.perform(get("/api/v1/operation").param("name", "exampleName"))
            .andExpect(status().isOk());
  }
}