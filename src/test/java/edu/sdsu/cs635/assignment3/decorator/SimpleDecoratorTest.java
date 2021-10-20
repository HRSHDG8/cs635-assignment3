package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.command.BaseCommand;
import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SimpleDecoratorTest {

  @Test
  public void simpleDecorator() {
    AtomicInteger atomicInteger = new AtomicInteger(-1);
    BaseCommand simpleCommand = new BaseCommand(inventory -> atomicInteger.set(0)) {
      @Override
      public void execute(Inventory inventory) {
        super.execute(inventory);
      }
    };
    simpleCommand.execute(new BookInventory());
    assertEquals(0, atomicInteger.get());
  }
}
