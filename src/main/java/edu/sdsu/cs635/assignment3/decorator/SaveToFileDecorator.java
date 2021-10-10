package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.command.Command;

public class SaveToFileDecorator extends CommandDecorator{
  public SaveToFileDecorator(Command command) {
    super(command);
  }

  @Override
  public void execute(){
    //write to file
    command.execute();
  }

}
