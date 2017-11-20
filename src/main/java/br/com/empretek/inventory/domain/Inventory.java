package br.com.empretek.inventory.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Inventory {

  private Set<Record> records;

  public Inventory() {
    super();
    records = new HashSet<>();
  }

  public Set<Record> getRecords() {
    return Collections.unmodifiableSet(records);
  }

  public boolean add(Record novo) {
    if (records.contains(novo)) {
      for (Record record : records) {
        if (record.equals(novo)) {
          // Calcula a nova quantidade.
          int quantidade = record.getQuantity() + novo.getQuantity();
          // Atualiza a quantidade do registro.
          record.setQuantity(quantidade);
          // Retorna verdadeiro, indicando o sucesso na adição da quantidade do item.
          return true;
        }
      }
    }

    return records.add(novo);
  }

  public boolean remove(Record record) {
    return records.remove(record);
  }
}