package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.inventory.PersistedInventory;
import edu.sdsu.cs635.assignment3.serialization.Serializer;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveToFile extends BaseCommand {
  private static final String COMMANDS = "command.ser";
  private static final int MAX_COMMAND_SIZE = 10;
  private final Serializer serializer;

  public SaveToFile(Command command) {
    super(command);
    this.serializer = Serializer.getInstance();
  }

  @Override
  public void execute(Inventory inventory) {
    try {
      command.execute(inventory);
      List<Command> commands = addToFile(command);
      // Create a copy of inventory state if the commands are greater than MAX_COMMAND_SIZE
      // Clear the command file once the inventory is persisted
      // if an error occurs in persisting the inventory the command file will not be cleared
      if (commands.size() >= MAX_COMMAND_SIZE) {
        new PersistedInventory(inventory).createState();
        serializer.write(COMMANDS, new ArrayList<>());
      }
    } catch (IOException | ClassNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private List<Command> addToFile(Command command) throws IOException, ClassNotFoundException {
    List<Command> commands;
    try {
      commands = (List<Command>) serializer.read(COMMANDS);
    } catch (EOFException e) {
      commands = new ArrayList<>();
    }
    commands.add(command);
    serializer.write(COMMANDS, commands);
    return commands;
  }

}
