package edu.sdsu.cs635.assignment3.inventory;

import java.io.Serializable;

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
