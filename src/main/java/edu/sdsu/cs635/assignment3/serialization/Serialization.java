package edu.sdsu.cs635.assignment3.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Serialization {
  private static ObjectMapper objectMapper;

  private Serialization() {
  }

  synchronized public static ObjectMapper getInstance() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper();
    }
    return objectMapper;
  }

}
