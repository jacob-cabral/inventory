package br.com.empretek.inventory.infrastructure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil extends ObjectUtil {

  private static FileUtil instance;

  private FileUtil() {
    super();
  }

  public static synchronized FileUtil getInstance() {
    if (instance == null)
      instance = new FileUtil();

    return instance;
  }

  public File getFile(String filename) throws IllegalArgumentException, IOException {
    File file = null;
    StringUtil stringUtil = StringUtil.getInstance();
    
    if (stringUtil.validateNotNull(filename) && stringUtil.validateNotEmpty(filename))
      file = new File(filename);
    else
      throw new IllegalArgumentException("O nome do arquivo é inválido.");

    if (!validateContentType("text/plain", file))
      throw new IllegalArgumentException("O tipo de conteúdo do arquivo é inválido.");

    return file;
  }

  public boolean validateExistence(File file) {
    if (!validateNotNull(file))
      throw new IllegalArgumentException("O arquivo não pode ser nulo.");

    return file.exists();
  }

  public boolean validateContentType(String contentType, File file) throws IOException {
    StringUtil stringUtil = StringUtil.getInstance();

    if (!stringUtil.validateNotNull(contentType) || !stringUtil.validateNotEmpty(contentType))
      throw new IllegalArgumentException("O tipo de conteúdo do arquivo não pode ser nulo ou vazio.");

    if (!validateExistence(file))
      throw new IllegalArgumentException("Arquivo inexistente.");

    String type = Files.probeContentType(file.toPath());

    if (contentType.equalsIgnoreCase(type))
      return true;

    return false;
  }
}
