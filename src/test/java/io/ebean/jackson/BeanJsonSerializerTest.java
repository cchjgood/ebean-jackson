package io.ebean.jackson;

import io.ebean.Ebean;
import io.ebean.text.json.JsonContext;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.example.domain.Customer;
import org.testng.annotations.Test;

import java.io.StringWriter;

import static org.testng.Assert.assertTrue;

public class BeanJsonSerializerTest {

  @Test
  public void testSerialize() throws Exception {


    JsonContext jsonContext = Ebean.json();

    BeanJsonSerializer jsonSerialiser = new BeanJsonSerializer(jsonContext);

    Customer customer = new Customer();
    customer.setId(42L);
    customer.setName("rob");
    customer.setVersion(1L);

    JsonFactory jsonFactory = new JsonFactory();

    StringWriter writer = new StringWriter();
    JsonGenerator generator = jsonFactory.createGenerator(writer);


    jsonSerialiser.serialize(customer, generator, null);

    generator.flush();
    generator.close();

    assertTrue(writer.toString().contains("{\"id\":42,"));

  }
}