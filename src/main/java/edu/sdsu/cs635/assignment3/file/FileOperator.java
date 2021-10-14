package edu.sdsu.cs635.assignment3.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdsu.cs635.assignment3.Serialization;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperator {

  private final ObjectMapper objectMapper;

  public FileOperator() {
    this.objectMapper = Serialization.getInstance();
  }

  public <T> boolean clearFile(String fileName, TypeReference<T> type) {
    Path path = Paths.get(this.getClass().getClassLoader().getResource(fileName).getPath().replaceFirst("/", ""));
    T commands = null;
    try {
      String line = readFile(fileName);
      commands = objectMapper.readValue(line, type);
      new FileWriter(path.toString(), false).close();
      return true;
    } catch (JsonProcessingException e) {
      return false;
    } catch (IOException e) {
      writeToFile(fileName, commands);
      return false;
    }
  }

  public String readFile(String fileName) throws IOException {
    try (InputStream commandLog = this.getClass().getClassLoader().getResourceAsStream(fileName);
         InputStreamReader commandReader = new InputStreamReader(commandLog);
         BufferedReader commandBuffer = new BufferedReader(commandReader)) {
      StringBuilder line = new StringBuilder();
      String temp;
      while ((temp = commandBuffer.readLine()) != null) {
        line.append(temp);
      }
      return line.toString();
    } catch (IOException e) {
      throw e;
    }
  }

  public <T> void writeToFile(String fileName, T writable) {
    String valueAsString = null;
    try {
      valueAsString = objectMapper.writeValueAsString(writable);
      Path source = Paths.get(this.getClass().getClassLoader().getResource(fileName).getPath().replaceFirst("/", ""));
      try (FileWriter fileWriter = new FileWriter(source.toFile())) {
        fileWriter.write(valueAsString);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
