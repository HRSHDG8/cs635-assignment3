package edu.sdsu.cs635.assignment3.decorator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdsu.cs635.assignment3.Serialization;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.file.FileOperator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SaveToFileDecorator extends CommandDecorator {
  private final FileOperator fileOperator;
  private final ObjectMapper objectMapper;

  public SaveToFileDecorator(Command command) {
    super(command);
    this.objectMapper = Serialization.getInstance();
    fileOperator = new FileOperator();
  }

  @Override
  public void execute() {
    //write to file
    addToFile(command);
    command.execute();
  }

  private void addToFile(Command command) {
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
