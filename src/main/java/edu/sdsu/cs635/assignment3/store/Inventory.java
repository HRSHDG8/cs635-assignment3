package edu.sdsu.cs635.assignment3.store;

import edu.sdsu.cs635.assignment3.entity.Book;

import java.util.Optional;

public interface Inventory<T, I> {
  boolean add(T t);

  boolean sell(I id);

  Optional<Book> findById(I i);

  Optional<Book> findByName(String name);
}
