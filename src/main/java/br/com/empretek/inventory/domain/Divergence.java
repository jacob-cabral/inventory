package br.com.empretek.inventory.domain;

import java.util.LinkedList;
import java.util.List;

public class Divergence {

  private List<Record> records;

  public Divergence() {
    super();
    records = new LinkedList<Record>();
  }

  public boolean add(Record record) {
    if (!validateEquals(record))
      throw new IllegalArgumentException("Registo inválido. O código do registro informado difere dos demais.");

    return records.add(record);
  }

  public boolean remove(Record record) {
    if (!validateEquals(record))
      throw new IllegalArgumentException("Registro inválido. O código do registro informado difere dos demais.");

    return records.remove(record);
  }

  private boolean validateEquals(Record record) {
    for (Record r : records)
      if (!r.equals(record))
        return false;

    return true;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Divergência [\n");
    
    records.forEach(registro -> {
      buffer.append(registro.toString());
      buffer.append("\n");
    });

    buffer.append("]");
        
    return buffer.toString();
  }
}