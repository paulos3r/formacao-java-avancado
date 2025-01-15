package br.paulos3r.reflection.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Transformator {

  public <I, O> O trasform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Class<?> source = input.getClass();
    Class<?> target = Class.forName(source + "DTO");

    O targetClass = (O) target.getDeclaredConstructor().newInstance();

    Field[] sourceFields = source.getDeclaredFields();
    Field[] targetFields = target.getDeclaredFields();

    Arrays.stream(sourceFields).forEach(sourceField->
            Arrays.stream(targetFields).forEach(targetField ->{
              validade(sourceField,targetField);
              try {
                targetField.set(targetClass,sourceField.get(input));
              } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
              }
            }));

    return targetClass;
  }

  private void validade(Field sourceField, Field targetField){
    if (sourceField.getName().equals(targetField.getName())
            && sourceField.getType().equals(targetField.getType())){
      sourceField.setAccessible(true);
      targetField.setAccessible(true);
    }
  }
}
