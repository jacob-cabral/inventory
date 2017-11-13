package br.com.empretek.inventory.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import br.com.empretek.inventory.infrastructure.StringUtil;

public class DataCollector {

  private File source;
  private Scanner scanner;

  public DataCollector(File source) throws FileNotFoundException {
    super();
    this.source = source;
    this.scanner = new Scanner(source);
  }
  
  public boolean hasNext() {
    return scanner.hasNext();
  }
  
  public Record getNextRecord() throws InvalidDataException {
    String line = scanner.nextLine();

    StringUtil util = StringUtil.getInstance();
    
    if (!util.validateNotNull(line) || util.validateNotEmpty(line))
      throw new InvalidDataException("O registro corresponde a um valor nulo ou vazio.");

    String[] data = line.split(",");

    if (data.length == 0)
      throw new InvalidDataException("O registro não possui dados válidos.");

    long code = Long.parseLong(data[0]);

    int quantity = 1;

    if (data.length == 2)
      quantity = Integer.parseInt(data[1]);

    return new Record(source, code, quantity);
  }
  
  public void close() {
    scanner.close();
  }

  @Override
  protected void finalize() throws Throwable {
    close();
    source = null;
    scanner = null;
    super.finalize();
  }
  
}