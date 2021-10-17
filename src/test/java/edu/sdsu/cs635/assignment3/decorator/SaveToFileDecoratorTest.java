package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.BookInventory;
import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.file.FileOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveToFileDecoratorTest {
  private Command saveToFile;
  private BookInventory bookInventory;
  private FileOperator fileOperator;

  @BeforeEach
  public void init() {
    bookInventory = new BookInventory();
    fileOperator = new FileOperator();
    saveToFile = new SaveToFile(new AddBook(new Book("LOTR", 299.99f, 10)));
  }

  @Test
  public void saveToFileMustAddCommandToFile() throws IOException {
    saveToFile.execute(bookInventory);
    String commands = fileOperator.readFile("command.json");
    assertTrue(commands.contains("LOTR"));
  }

  @Test
  public void onAdding10CommandsCommandFileMustBeEmpty() throws IOException {
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    saveToFile.execute(bookInventory);
    String commands = fileOperator.readFile("command.json");
    assertFalse(commands.contains("LOTR"));
  }
}
