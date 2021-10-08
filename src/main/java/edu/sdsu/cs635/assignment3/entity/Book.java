package edu.sdsu.cs635.assignment3.entity;

import edu.sdsu.cs635.assignment3.memento.InventoryMemento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
  private static final long serialVersionUID = -4778810560316362986L;
  private Integer id;
  private String name;
  private Float price;
  private Integer quantity;
}
