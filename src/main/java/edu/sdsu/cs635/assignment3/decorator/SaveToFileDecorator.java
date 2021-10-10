package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.file.FileHandler;

public class SaveToFileDecorator extends CommandDecorator {
  private final FileHandler fileHandler;

  public SaveToFileDecorator(Command command) {
    super(command);
    fileHandler = new FileHandler();
  }

  @Override
  public void execute() {
    //write to file
    fileHandler.addToFile(command);
    command.execute();
  }

}
