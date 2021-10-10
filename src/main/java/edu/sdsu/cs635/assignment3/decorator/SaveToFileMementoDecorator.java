package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.file.MementoFileHandler;
import edu.sdsu.cs635.assignment3.store.Inventory;

public class SaveToFileMementoDecorator {
  private final MementoFileHandler fileHandler;

  public SaveToFileMementoDecorator() {
    fileHandler = new MementoFileHandler();
  }

  public void execute(Inventory<Integer, Book> command) {
    //write to file
    fileHandler.addToFile(command);
  }

}
