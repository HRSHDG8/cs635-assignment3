package edu.sdsu.cs635.assignment3.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdsu.cs635.assignment3.Serialization;
import edu.sdsu.cs635.assignment3.command.Command;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileHandler {
  private final ObjectMapper objectMapper;

  public FileHandler() {
    this.objectMapper = Serialization.getInstance();
  }

  public void addToFile(Command command) {
    try (InputStream commandLog = this.getClass().getClassLoader().getResourceAsStream("Command.log");
         InputStreamReader commandReader = new InputStreamReader(commandLog);
         BufferedReader commandBuffer = new BufferedReader(commandReader)) {
      StringBuilder line = new StringBuilder();
      String temp;
      while ((temp = commandBuffer.readLine()) != null) {
        line.append(temp);
      }
      if (line.toString().isEmpty()) {
        writeToFile(Collections.singletonList(command));
      } else {
        List<Object> commands = objectMapper.readValue(line.toString(), new TypeReference<List<Object>>() {
        });
        commands.add(command);
        writeToFile(commands);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private void writeToFile(List<Object> commands) throws JsonProcessingException {
    String valueAsString = objectMapper.writeValueAsString(commands);
    Path source = Paths.get(this.getClass().getClassLoader().getResource("Command.log").getPath().replaceFirst("/", ""));
    try (FileWriter fileWriter = new FileWriter(source.toFile())) {
      System.out.println("Huuahhh + " + valueAsString);
      fileWriter.write(valueAsString);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
