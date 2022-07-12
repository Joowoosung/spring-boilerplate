package com.jws.settings.config.json_serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page> {

  @Override
  public void serialize(Page page, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    gen.writeObjectField("content", page.getContent());
    gen.writeBooleanField("first", page.isFirst());
    gen.writeBooleanField("last", page.isLast());
    gen.writeNumberField("totalPages", page.getTotalPages());
    gen.writeNumberField("totalElements", page.getTotalElements());
    gen.writeNumberField("numberOfElements", page.getNumberOfElements());
    gen.writeNumberField("size", page.getSize());
    gen.writeNumberField("number", page.getNumber());
    gen.writeEndObject();
  }
}
