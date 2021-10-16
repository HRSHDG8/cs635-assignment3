package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.sdsu.cs635.assignment3.store.BookInventory;

import java.io.Serializable;

@JsonTypeInfo(
   use = JsonTypeInfo.Id.NAME,
   property = "action",
   defaultImpl = AddBook.class
)
@JsonSubTypes({
   @JsonSubTypes.Type(value = AddBook.class, name = "add"),
   @JsonSubTypes.Type(value = SellBook.class, name = "sell"),
   @JsonSubTypes.Type(value = UpdatePrice.class, name = "update")
})
public interface Command extends Serializable {
  void execute(BookInventory inventory);
}
