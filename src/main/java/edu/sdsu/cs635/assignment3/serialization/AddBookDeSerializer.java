package edu.sdsu.cs635.assignment3.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.command.AddBook;

import java.io.IOException;

public class AddBookDeSerializer extends JsonDeserializer<AddBook> {
  @Override
  public AddBook deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JacksonException {
    JsonNode node = jp.getCodec().readTree(jp);
    JsonNode bookNode = node.get("book");
    Book book = new Book(bookNode.get("id").asInt(), bookNode.get("name").asText(), bookNode.get("price").floatValue(), bookNode.get("quantity").asInt());
    return new AddBook(book);
  }
}
