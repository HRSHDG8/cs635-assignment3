package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;

import java.util.Optional;

public interface Inventory {
  void add(Book book);

  void sell(Book book);

  void update(Book book);

  Optional<Book> findById(Integer id);

  Optional<Book> findByName(String name);
}
