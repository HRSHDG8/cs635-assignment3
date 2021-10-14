package edu.sdsu.cs635.assignment3.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.sdsu.cs635.assignment3.command.SellBook;

import java.io.IOException;

public class SellBookSerializer extends JsonSerializer<SellBook> {
  @Override
  public void serialize(SellBook sellBook, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
    jgen.writeStartObject();
    jgen.writeStringField("action", "sell");
    jgen.writeObjectField("book", sellBook.getBook());
    jgen.writeEndObject();
  }
}
