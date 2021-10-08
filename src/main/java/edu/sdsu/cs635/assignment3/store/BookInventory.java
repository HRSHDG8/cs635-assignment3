package edu.sdsu.cs635.assignment3.store;

import edu.sdsu.cs635.assignment3.entity.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookInventory implements Inventory<Book, Integer> {
  private final Map<Integer, Book> bookStore;

  public BookInventory() {
    this.bookStore = new HashMap<>();
  }

  @Override
  public boolean add(Book book) {
    if (bookStore.containsKey(book.getId())) {
      Book inventoryBook = bookStore.get(book.getId());
      inventoryBook.setQuantity(inventoryBook.getQuantity() + 1);
    } else {
      book.setId(computeIndex());
    }
    Book inventoryBook = bookStore.put(book.getId(), book);
    return inventoryBook != null;
  }

  private int computeIndex() {
    return bookStore.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
  }

  @Override
  public boolean sell(Integer id) {
    if (bookStore.containsKey(id)) {
      Book book = bookStore.get(id);
      if (book.getQuantity() <= 0) {
        return false;
      } else {
        book.setQuantity(book.getQuantity() - 1);
        bookStore.put(book.getId(), book);
        return true;
      }
    }
    return false;
  }

  @Override
  public Optional<Book> findById(Integer id) {
    return Optional.ofNullable(bookStore.get(id));
  }

  @Override
  public Optional<Book> findByName(String name) {
    return bookStore.values()
       .stream()
       .filter(book -> book.getName().equals(name))
       .findFirst();
  }
}
