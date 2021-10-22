package edu.sdsu.cs635.assignment3.serialization;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.command.SellBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite to check the general logic to read and write objects from and to a file
 */
class SerializerTest {
  private Serializer serializer;
  private static final String TEST_FILE = "test.ser";

  @BeforeEach
  void setUp() {
    serializer = Serializer.instance();
  }

  @Test
  @DisplayName("Method that checks our serialization logic by writing and reading a list of commands from a file (test.ser)")
  @SuppressWarnings("unchecked")
  public void writeToAndReadFromFile() throws IOException, ClassNotFoundException {
    Command add = new AddBook(new Book(1, "Harry", 10f, 1));
    Command sell = new SellBook(new Book(1, "Harry", 10f, 1));
    serializer.write(TEST_FILE, new ArrayList<>(Arrays.asList(add, sell)));
    List<Command> commands = (List<Command>) serializer.read(TEST_FILE);
    assertEquals(2, commands.size());
  }

}
