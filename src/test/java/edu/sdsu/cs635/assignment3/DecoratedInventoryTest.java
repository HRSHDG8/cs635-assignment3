package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.DecoratedInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.inventory.InventoryState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratedInventoryTest {
  private Inventory bookInventory;

  @BeforeEach
  public void init() {
    bookInventory = new DecoratedInventory(new BookInventory()) {
    };
  }

  @Test
  public void restoringAnInventoryToItsLastStateMustRestoreTheContentAndValuesOfTheContents() {
    Book path = new Book("Frail Path", 9.99f, 1);
    bookInventory.add(path);
    bookInventory.add(path);
    //create a memento here
    InventoryState inventoryState = bookInventory.createState();
    bookInventory.sell(path);
    bookInventory.sell(path);
    path.setPrice(19.99f);
    bookInventory.update(path);
    //restore to the last saved state
    bookInventory.restoreState(inventoryState);
    assertEquals(9.99f, bookInventory.findById(path.getIsbn()).get().getPrice());
  }

  @Test
  public void aBookOnEmptyInventoryShouldNotBePresent() {
    Optional<Book> bookOpt = bookInventory.findByName("Harry Potter");
    assertFalse(bookOpt.isPresent());
  }

  @Test
  @DisplayName("Changing a book after inserting into the inventory does not alter the inventory")
  public void changesAreImmutable() {
    Book book = new Book("Alice in wonder land", 99.99f, 1);
    bookInventory.add(book);
    book.setName("A I W L");
    book.setPrice(49.99f);
    bookInventory.update(book);
    Optional<Book> aliceOpt = bookInventory.findById(book.getIsbn());
    assertTrue(aliceOpt.isPresent());
    Book alice = aliceOpt.get();
    assertEquals("Alice in wonder land", alice.getName());
  }
}
