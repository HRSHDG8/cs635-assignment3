package edu.sdsu.cs635.assignment3.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdsu.cs635.assignment3.Serialization;
import edu.sdsu.cs635.assignment3.command.Command;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
  private final ObjectMapper objectMapper;
  private final FileOperator fileOperator;

  public FileHandler() {
    this.objectMapper = Serialization.getInstance();
    this.fileOperator = new FileOperator();
  }

  public void addToFile(Command command) {
    try {
      String line = fileOperator.readFile("command.json");
      if (line.isEmpty()) {
        fileOperator.writeToFile("command.json", Arrays.asList(command));
      } else {
        List<Object> commands = objectMapper.readValue(line, new TypeReference<List<Object>>() {
        });
        commands.add(command);
        fileOperator.writeToFile("command.json", commands);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
