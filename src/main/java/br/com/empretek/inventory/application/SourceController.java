package br.com.empretek.inventory.application;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import br.com.empretek.inventory.infrastructure.FileUtil;

public class SourceController {

  private Set<File> sources;

  public SourceController() {
    super();
    sources = new HashSet<>();
  }

  public void addSource() throws IllegalArgumentException, RuntimeException {
    Scanner scanner = new Scanner(System.in);
    // Solicita o nome do arquivo.
    System.out.print("Informe o nome do arquivo: ");
    // Obtém o nome do arquivo informado pelo usuário.
    String filename = scanner.nextLine();

    try {
      // Recupera o arquivo informado e adiciona o mesmo à lista de fontes.
      add(FileUtil.getInstance().getFile(filename));
    } catch (IOException e) {
      throw new RuntimeException("Ocorreu um erro de I/O ao tentar adicionar o arquivo.", e);
    } finally {
      if (scanner != null)
        scanner.close();
    }
  }

  public void clearSources() {
    sources.clear();
  }

  public Set<File> getSources() {
    return Collections.unmodifiableSet(sources);
  }

  private boolean add(File source) throws IllegalArgumentException, RuntimeException {
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
}