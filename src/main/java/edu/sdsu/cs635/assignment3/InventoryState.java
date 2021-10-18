package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.Inventory;

public class InventoryState {
  private Inventory inventory;

  public InventoryState(Inventory inventory) {
    this.inventory = inventory;
  }

  public Inventory getInventory() {
    return inventory;
  }
}
