package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.serialization.Serialization;

import java.io.IOException;
import java.util.List;

public class SaveToFile extends CommandDecorator {
  private static final String COMMAND_JSON = "command.ser";
  private final Serialization serialization;

  public SaveToFile(Command command) {
    super(command);
    this.serialization = Serialization.getInstance();
  }

  @Override
  public void execute(Inventory inventory) {
    //write to file
    try {
      command.execute(inventory);
      List<Command> commands = addToFile(command);
      if (commands.size() > 10) {
//        inventory.createMemento();
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private List<Command> addToFile(Command command) throws IOException, ClassNotFoundException {
    List<Command> commands = (List<Command>) serialization.read(COMMAND_JSON);
    commands.add(command);
    serialization.write(COMMAND_JSON, commands);
    return commands;
  }

}
