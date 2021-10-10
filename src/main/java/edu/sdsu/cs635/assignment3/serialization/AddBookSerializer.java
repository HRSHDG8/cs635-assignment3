package edu.sdsu.cs635.assignment3.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.sdsu.cs635.assignment3.command.AddBook;

import java.io.IOException;

public class AddBookSerializer extends JsonSerializer<AddBook> {
  @Override
  public void serialize(AddBook addBook, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
    jgen.writeStartObject();
    jgen.writeStringField("action", "add");
    jgen.writeObjectField("book", addBook.getBook());
    jgen.writeEndObject();
  }
}
