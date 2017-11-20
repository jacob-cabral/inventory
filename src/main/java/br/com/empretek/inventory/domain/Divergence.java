package br.com.empretek.inventory.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Divergence {

  private static final long UNDEFINED_CODE = -1;

  private long code;
  private List<Record> records;

  public Divergence() {
    super();
    code = UNDEFINED_CODE;
    records = new LinkedList<Record>();
  }

  public Divergence(Record... records) {
    this();

    if (records == null || Arrays.asList(records).isEmpty())
      throw new IllegalArgumentException("Os registros não podem ser nulos ou vazios.");

    Arrays.asList(records).forEach(record -> add(record));
  }

  public boolean add(Record record) {
    if (record != null && !validateEquals(record))
      throw new IllegalArgumentException("Registo inválido. O código do registro informado difere dos demais.");

    if (code == UNDEFINED_CODE && record != null)
      code = record.getCode();

    return records.add(record);
  }

  public boolean remove(Record record) {
    if (!validateEquals(record))
      throw new IllegalArgumentException("Registro inválido. O código do registro informado difere dos demais.");

    return records.remove(record);
  }
  
  public List<Record> getRecords() {
    return Collections.unmodifiableList(records);
  }

  private boolean validateEquals(Record record) {
    for (Record r : records)
      if (!r.equals(record))
        return false;

    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (code ^ (code >>> 32));
    result = prime * result + ((records == null) ? 0 : records.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Divergence other = (Divergence) obj;
    if (code != other.code)
      return false;
    if (records == null) {
      if (other.records != null)
        return false;
    } else if (!records.equals(other.records))
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