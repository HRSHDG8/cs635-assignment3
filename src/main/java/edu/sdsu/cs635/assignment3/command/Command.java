package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.store.Inventory;

import java.io.Serializable;

public interface Command extends Serializable {
  void execute(Inventory<Integer, Book> inventory);
}
