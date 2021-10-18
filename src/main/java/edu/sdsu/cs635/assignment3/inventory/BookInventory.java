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
    Book inventoryBook = book.clone();
    if (bookStore.containsKey(book.getIsbn())) {
      inventoryBook = bookStore.get(book.getIsbn()).clone();
      inventoryBook.setPrice(book.getPrice());
      inventoryBook.setQuantity(book.getQuantity());
    }
    bookStore.put(book.getIsbn(), inventoryBook);
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

  @Override
  public InventoryState createState() {
    return new InventoryState(this);
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

}
