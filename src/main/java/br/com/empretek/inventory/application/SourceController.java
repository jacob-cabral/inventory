package br.com.empretek.inventory.application;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import br.com.empretek.inventory.infrastructure.FileUtil;

public class SourceController {

  private Set<File> sources;

  public SourceController() {
    super();
    sources = new HashSet<>();
  }
  
  public boolean add(File source) {
    FileUtil util = FileUtil.getInstance();
    
    if (!util.validateNotNull(source))
      throw new IllegalArgumentException("O arquivo não pode ser nulo.");
    
    if (!util.validateExistence(source))
      throw new IllegalArgumentException("Arquivo inexsitente ou inacessível.");
    
    try {
      if (!util.validateContentType("text/plain", source))
        throw new IllegalArgumentException("O tipo de conteúdo do arquivo é inválido.");
    } catch (IOException e) {
      throw new RuntimeException("Ocorreu um erro de I/O.", e);
    }
    
    return sources.add(source);
  }
  
  public boolean remove(File source) {
    return sources.remove(source);
  }
  
  public Set<File> getSources() {
    return Collections.unmodifiableSet(sources);
  }
}