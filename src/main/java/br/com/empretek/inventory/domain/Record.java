package br.com.empretek.inventory.domain;

import java.io.File;

public class Record {

  private long code;
  private int quantity;
  private File source;

  public Record(File source, long code, int quantity) {
    super();
    this.code = code;
    this.quantity = quantity;
    this.source = source;
  }

  public File getSource() {
    return source;
  }

  public long getCode() {
    return code;
  }

  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (code ^ (code >>> 32));
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
    Record other = (Record) obj;
    if (code != other.code)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Registro [arquivo=" + source + ", código=" + code + ", quantidade=" + quantity + "]";
  }
}