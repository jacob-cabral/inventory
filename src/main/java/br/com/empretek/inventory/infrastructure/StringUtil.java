package br.com.empretek.inventory.infrastructure;

public class StringUtil extends ObjectUtil {

  private static StringUtil instance;

  private StringUtil() {
    super();
  }

  public static synchronized StringUtil getInstance() {
    if (instance == null)
      instance = new StringUtil();

    return instance;
  }

  public boolean validateNotEmpty(String string) {
    return validateNotNull(string) && !string.trim().isEmpty();
  }
  
  public boolean validateAllNotEmpty(String... strings) {
    for (String string : strings)
      if (!validateNotEmpty(string))
        return false;
    
    return true;
  }

}