package edu.sdsu.cs635.assignment3.decorator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.file.FileOperator;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.serialization.Serialization;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SaveToFile extends CommandDecorator {
  private static final String COMMAND_JSON = "command.json";
  private final FileOperator fileOperator;
  private final ObjectMapper objectMapper;

  public SaveToFile(Command command) {
    super(command);
    this.objectMapper = Serialization.getInstance();
    this.fileOperator = new FileOperator();
  }

  @Override
  public void execute(Inventory inventory) {
    //write to file
    List<Command> commands = addToFile(command);
    try {
      command.execute(inventory);
      if (commands.size() > 10) {
//        inventory.createMemento();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private List<Command> addToFile(Command command) {
    List<Command> commands = Collections.emptyList();
    try {
      String line = fileOperator.readFile(COMMAND_JSON);
      if (line.isEmpty()) {
        commands = Collections.singletonList(command);
      } else {
        commands = objectMapper.readValue(line, new TypeReference<List<Command>>() {
        });
        commands.add(command);
      }
      fileOperator.writeToFile(COMMAND_JSON, commands);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return commands;
  }

}
