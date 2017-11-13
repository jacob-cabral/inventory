package br.com.empretek.inventory.infrastructure;

public abstract class ObjectUtil {

  public boolean validateNotNull(Object object) {
    return object != null;
  }
  
  public boolean validadeAllNotNull(Object... objects) {
    for (Object object : objects)
      if (!validateNotNull(object))
        return false;
    
    return true;
  }
}