package edu.sdsu.cs635.assignment3.decorator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdsu.cs635.assignment3.Serialization;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.file.FileOperator;
import edu.sdsu.cs635.assignment3.store.Inventory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SaveToFileDecorator extends CommandDecorator {
  private static final String COMMAND_JSON = "command.json";
  private final FileOperator fileOperator;
  private final ObjectMapper objectMapper;

  public SaveToFileDecorator(Command command) {
    super(command);
    this.objectMapper = Serialization.getInstance();
    fileOperator = new FileOperator();
  }

  @Override
  public void execute(Inventory<Integer, Book> inventory) {
    //write to file
    addToFile(command);
    command.execute(inventory);
  }

  private void addToFile(Command command) {
    try {
      String line = fileOperator.readFile(COMMAND_JSON);
      if (line.isEmpty()) {
        fileOperator.writeToFile(COMMAND_JSON, Collections.singletonList(command));
      } else {
        List<Command> commands = objectMapper.readValue(line, new TypeReference<List<Command>>() {
        });
        commands.add(command);
        fileOperator.writeToFile(COMMAND_JSON, commands);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
