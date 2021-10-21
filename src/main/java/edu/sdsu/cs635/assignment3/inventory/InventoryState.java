package edu.sdsu.cs635.assignment3.inventory;

import java.io.Serializable;

/**
 * holds a deep copy of the {@link Inventory} to be able to roll back to a previous stored version
 */
public class InventoryState implements Serializable {
  private static final long serialVersionUID = 7191850344043730250L;
  private final Inventory inventory;

  public InventoryState(Inventory inventory) {
    this.inventory = inventory;
  }

  public Inventory getInventory() {
    return inventory;
  }
}
