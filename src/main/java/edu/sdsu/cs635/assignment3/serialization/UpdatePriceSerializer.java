package edu.sdsu.cs635.assignment3.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.sdsu.cs635.assignment3.command.UpdatePrice;

import java.io.IOException;

public class UpdatePriceSerializer extends JsonSerializer<UpdatePrice> {
  @Override
  public void serialize(UpdatePrice updatePrice, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
    jgen.writeStartObject();
    jgen.writeStringField("action", "update");
    jgen.writeObjectField("book", updatePrice.getBook());
    jgen.writeEndObject();
  }
}
