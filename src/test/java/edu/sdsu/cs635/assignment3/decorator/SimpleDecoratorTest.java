package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.BookInventory;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleDecoratorTest {

  @Test
  public void simpleDecorator() {
    AtomicInteger atomicInteger = new AtomicInteger(-1);
    CommandDecorator simpleCommand = new CommandDecorator(inventory -> atomicInteger.set(0)) {
      @Override
      public void execute(BookInventory inventory) {
        super.execute(inventory);
      }
    };
    simpleCommand.execute(new BookInventory());
    assertEquals(0, atomicInteger.get());
  }
}
