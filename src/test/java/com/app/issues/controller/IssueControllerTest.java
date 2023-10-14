package com.app.issues.controller;

import com.app.issues.entity.IssueEntity;
import com.app.issues.enums.StatusEnum;
import com.app.issues.service.IssueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IssueControllerTest {
  @Mock
  private IssueService issueService;

  @InjectMocks
  private IssueController issueController;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();

    objectMapper = new ObjectMapper();

    when(issueService.findAll()).thenReturn(Arrays.asList(new IssueEntity(), new IssueEntity()));
    when(issueService.findById(1L)).thenReturn(new IssueEntity());
    when(issueService.findById(2L)).thenReturn(null);

    IssueEntity issueToSave = new IssueEntity();
    when(issueService.save(issueToSave)).thenReturn(issueToSave);

    IssueEntity issueToUpdate = new IssueEntity();
    when(issueService.edit(issueToUpdate)).thenReturn(issueToUpdate);

    doNothing().when(issueService).delete(1L);
  }

  @Test
  public void testGetAll() throws Exception {
    mockMvc.perform(get("/api/v1/issue"))
            .andExpect(status().isOk());
  }

  @Test
  public void testGetByIdExisting() throws Exception {
    mockMvc.perform(get("/api/v1/issue/1"))
            .andExpect(status().isOk());
  }

  @Test
  public void testGetByIdNonExisting() throws Exception {
    mockMvc.perform(get("/api/v1/issue/2"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void testCreateIssue() throws Exception {
    IssueEntity newIssue = new IssueEntity();

    mockMvc.perform(post("/api/v1/issue")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(newIssue)))
            .andExpect(status().isCreated());
  }

  @Test
  public void testUpdateIssue() throws Exception {
    IssueEntity updatedIssue = new IssueEntity();
    updatedIssue.setId(1L);
    updatedIssue.setTitle("Soporte a servidor");
    updatedIssue.setOperator("Antonio");
    updatedIssue.setDescription("Mantenimiento");
    updatedIssue.setStatus(StatusEnum.OPEN);
    updatedIssue.setCreationAt(new Date());

    mockMvc.perform(put("/api/v1/issue/1")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(updatedIssue)))
            .andExpect(status().isOk());
  }

  @Test
  public void testDeleteExisting() throws Exception {
    mockMvc.perform(delete("/api/v1/issue/1"))
            .andExpect(status().isNoContent());
  }

  @Test
  public void testDeleteNonExisting() throws Exception {
    mockMvc.perform(delete("/api/v1/issue/2"))
            .andExpect(status().isNotFound());
  }
}