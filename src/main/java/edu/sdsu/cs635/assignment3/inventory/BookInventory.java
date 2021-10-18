package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookInventory implements Inventory {
  private static final long serialVersionUID = -4778810560316362987L;
  private Map<Integer, Book> bookStore;

  public BookInventory() {
    this.bookStore = new HashMap<>();
  }

  @Override
  public void add(Book book) {
    if (bookStore.containsKey(book.getIsbn())) {
      Book inventoryBook = bookStore.get(book.getIsbn());
      inventoryBook.setQuantity(inventoryBook.getQuantity() + 1);
    } else {
      if (book.getQuantity() <= 0) {
        book.setQuantity(1);
      }
      book.setIsbn(computeIndex());
    }
    bookStore.put(book.getIsbn(), book);
  }

  private int computeIndex() {
    return bookStore.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
  }

  @Override
  public void sell(Book book) {
    if (bookStore.containsKey(book.getIsbn())) {
      Book bookToBeSold = bookStore.get(book.getIsbn());
      if (bookToBeSold.getQuantity() <= 0) {
        throw new RuntimeException("Book is not available");
      } else {
        bookToBeSold.setQuantity(bookToBeSold.getQuantity() - 1);
        bookStore.put(bookToBeSold.getIsbn(), bookToBeSold);
      }
    } else {
      throw new RuntimeException("Book is not available");
    }
  }

  @Override
  public void update(Book book) {
    if (bookStore.containsKey(book.getIsbn())) {
      Book inventoryBook = bookStore.get(book.getIsbn());
      inventoryBook.setPrice(book.getPrice());
    }
    bookStore.put(book.getIsbn(), book);
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
