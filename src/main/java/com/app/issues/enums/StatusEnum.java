package com.app.issues.enums;

public enum StatusEnum {
  OPEN("OPEN"),
  PROGRESS("PROGRESS"),
  CLOSED("CLOSED");

  private final String value;

  StatusEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
