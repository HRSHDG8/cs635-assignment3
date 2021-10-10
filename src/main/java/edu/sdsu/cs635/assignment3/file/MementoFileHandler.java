package edu.sdsu.cs635.assignment3.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdsu.cs635.assignment3.Serialization;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.store.Inventory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MementoFileHandler {
  private final ObjectMapper objectMapper;

  public MementoFileHandler() {
    this.objectMapper = Serialization.getInstance();
  }

  public void addToFile(Inventory<Integer, Book> bookInventory) {
    try {
      writeToFile(bookInventory);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  private void writeToFile(Inventory<Integer, Book> bookInventory) throws JsonProcessingException {
    String valueAsString = objectMapper.writeValueAsString(bookInventory);
    Path source = Paths.get(this.getClass().getClassLoader().getResource("inventory.json").getPath().replaceFirst("/", ""));
    try (FileWriter fileWriter = new FileWriter(source.toFile())) {
      System.out.println("Huuahhh + " + valueAsString);
      fileWriter.write(valueAsString);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
