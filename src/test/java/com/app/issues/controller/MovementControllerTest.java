package com.app.issues.controller;

import com.app.issues.entity.MovementEntity;
import com.app.issues.service.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovementControllerTest {
  @Mock
  private MovementService movementService;

  @InjectMocks
  private MovementController movementController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(movementController).build();

    when(movementService.findAll()).thenReturn(Arrays.asList(new MovementEntity(), new MovementEntity()));
    when(movementService.findById(1L)).thenReturn(new MovementEntity());
    when(movementService.findByName("closed")).thenReturn(new MovementEntity());
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
    mockMvc.perform(get("/api/v1/operation").param("name", "open"))
            .andExpect(status().isOk());
  }
}