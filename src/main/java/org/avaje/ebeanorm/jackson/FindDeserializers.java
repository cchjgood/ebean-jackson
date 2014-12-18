package org.avaje.ebeanorm.jackson;

import com.avaje.ebean.bean.BeanCollection;
import com.avaje.ebean.text.json.JsonContext;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.*;

/**
 * Finds JsonDeserializer implementations for entity beans or entity bean collections.
 */
class FindDeserializers extends Deserializers.Base {


  final JsonContext jsonContext;

  /**
   * Construct with the given JsonContext.
   */
  FindDeserializers(JsonContext jsonContext) {
    this.jsonContext = jsonContext;
  }

  @Override
  public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {

    if (jsonContext.isSupportedType(type.getRawClass())) {
      return new BeanTypeDeserializer(jsonContext, type.getRawClass());
    }
    return null;
  }

  @Override
  public JsonDeserializer<?> findCollectionDeserializer(CollectionType type, DeserializationConfig config,
                                                        BeanDescription beanDesc, TypeDeserializer
          elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {

    if (type.getRawClass().isAssignableFrom(BeanCollection.class)){
      return new BeanListTypeDeserializer(jsonContext, type.getContentType().getRawClass());
    }

    return null;
  }


}
