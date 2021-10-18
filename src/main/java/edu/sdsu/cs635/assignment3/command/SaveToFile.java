package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.inventory.DecoratedInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.serialization.Serializer;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveToFile extends CommandDecorator {
  private static final String COMMAND_JSON = "command.ser";
  private final Serializer serializer;

  public SaveToFile(Command command) {
    super(command);
    this.serializer = Serializer.getInstance();
  }

  @Override
  public void execute(Inventory inventory) {
    //write to file
    try {
      command.execute(inventory);
      List<Command> commands = addToFile(command);
      if (commands.size() >= 10) {
        serializer.write(COMMAND_JSON, new ArrayList<>());
        new DecoratedInventory(inventory).createState();
      }
    } catch (IOException | ClassNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private List<Command> addToFile(Command command) throws IOException, ClassNotFoundException {
    List<Command> commands;
    try {
      commands = (List<Command>) serializer.read(COMMAND_JSON);
    } catch (EOFException e) {
      commands = new ArrayList<>();
    }
    commands.add(command);
    serializer.write(COMMAND_JSON, commands);
    return commands;
  }

}
