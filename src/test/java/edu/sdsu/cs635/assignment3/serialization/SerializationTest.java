package edu.sdsu.cs635.assignment3.serialization;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.command.SellBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerializationTest {
  private Serialization serialization;

  @BeforeEach
  void setUp() {
    serialization = Serialization.getInstance();
  }

  @Test
  @SuppressWarnings("unchecked")
  public void writeToFile() throws IOException, ClassNotFoundException {
    Command add = new AddBook(new Book(1, "Harry", 10f, 1));
    Command sell = new SellBook(new Book(1, "Harry", 10f, 1));
    serialization.write("test.ser", new ArrayList<>(Arrays.asList(add, sell)));
    List<Command> commands = (List<Command>) serialization.read("test.ser");
    assertEquals(2, commands.size());
  }

}
