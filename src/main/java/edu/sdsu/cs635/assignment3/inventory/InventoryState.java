package edu.sdsu.cs635.assignment3.inventory;

import java.io.Serializable;

/**
 * holds a copy of the Inventory to be able to roll back to a previous stored version
 */
public class InventoryState implements Serializable {
  private static final long serialVersionUID = -4778810560316362990L;
  private final Inventory inventory;

  public InventoryState(Inventory inventory) {
    this.inventory = inventory;
  }

  public Inventory getInventory() {
    return inventory;
  }
}
