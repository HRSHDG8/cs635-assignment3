package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Basic {@link Inventory} that acts as in-memory store for {@link Book}
 * The {@link Inventory} is just a key-value store of a books ISBN and the book.
 * The {@link Inventory} has an option to create a snapshot of its content at any given time and restore it to the last persisted state using Memento pattern.
 */
public class BookInventory implements Inventory, Cloneable {
  private static final long serialVersionUID = 6891873527834431075L;
  private final Map<Integer, Book> bookStore;

  public BookInventory() {
    this.bookStore = new HashMap<>();
  }

  /**
   * Add a new book or copies of the same book.
   */
  @Override
  public void add(Book book) {
    Book inventoryBook = book.clone();
    if (bookStore.containsKey(book.getIsbn())) {
      inventoryBook = bookStore.get(book.getIsbn()).clone();
      inventoryBook.setQuantity(inventoryBook.getQuantity() + 1);
    } else {
      if (book.getQuantity() <= 0) {
        inventoryBook.setQuantity(1);
      }
      int index = computeIndex();
      inventoryBook.setIsbn(index);
      book.setIsbn(index);
    }
    bookStore.put(book.getIsbn(), inventoryBook);
  }

  private int computeIndex() {
    return bookStore.keySet()
       .stream()
       .max(Integer::compareTo)
       .orElse(0) + 1;
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
    Book inventoryBook = book.clone();
    if (bookStore.containsKey(book.getIsbn())) {
      inventoryBook = bookStore.get(book.getIsbn()).clone();
      inventoryBook.setPrice(book.getPrice());
      inventoryBook.setQuantity(book.getQuantity());
    }
    bookStore.put(book.getIsbn(), inventoryBook);
  }

  @Override
  public Optional<Book> findById(Integer isbn) {
    return Optional.ofNullable(bookStore.get(isbn));
  }

  @Override
  public Optional<Book> findByName(String name) {
    return bookStore.values()
       .stream()
       .filter(book -> book.getName().equals(name))
       .findFirst();
  }

  @Override
  public InventoryState createState() {
    return new InventoryState(this.clone());
  }

  @Override
  public void restoreState(InventoryState state) {
    BookInventory inventory = (BookInventory) state.getInventory();
    Map<Integer, Book> bookStore = inventory.getBookStore();
    bookStore.forEach((id, book) -> this.bookStore.put(id, book.clone()));
  }

  public Map<Integer, Book> getBookStore() {
    return bookStore;
  }

  @Override
  public BookInventory clone() {
    BookInventory clone = new BookInventory();
    clone.bookStore.clear();
    bookStore.forEach((id, book) -> clone.bookStore.put(id, book.clone()));
    return clone;
  }
}
