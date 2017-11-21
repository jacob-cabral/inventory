package br.com.empretek.inventory.domain.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import br.com.empretek.inventory.domain.Divergence;
import br.com.empretek.inventory.domain.Inventory;
import br.com.empretek.inventory.domain.Record;
import br.com.empretek.inventory.domain.data.DataCollector;
import br.com.empretek.inventory.domain.data.InvalidDataException;

public class InventoryService {

  public Inventory inventory(File source) throws FileNotFoundException, InvalidDataException {
    Inventory inventory = new Inventory();
    DataCollector collector = new DataCollector(source);

    while (collector.hasNext())
      inventory.add(collector.getNextRecord());

    if (collector != null)
      collector.close();

    return inventory;
  }

  public Set<Divergence> compare(Inventory inventory, Inventory other) {
    return compare(inventory, other);
  }

  public Set<Divergence> compare(Inventory... inventories) {
    // Instancia o conjunto de divergências.
    Set<Divergence> divergences = new HashSet<>();

    // Verifica a validade do argumento - um conjunto de inventários.
    if (inventories == null || Arrays.asList(inventories).isEmpty())
      throw new IllegalArgumentException("Erro ao comparar os inventários. Os argumentos são inválidos.");

    // Itera sobre os inventários.
    for (int i = 0; i < inventories.length; i++) {
      // Recupera o inventário na posição "i".
      Inventory inventory = inventories[i];

      // Itera sobre os registros do inventário.
      for (Record record : inventory.getRecords()) {
        // Instancia um vetor que armazena os registros equivalentes em cada inventário.
        Record[] records = new Record[inventories.length];

        // Itera sobre os inventários em busca dos registros equivalentes ao selecionado na estrutura de repetição acima.
        for (int x = 0; x < inventories.length; x++) {
          // Verifica se o índice corresponde ao do inventário atual.
          if (i == x) {
            // Atribui o registro selecionado ao vetor.
            records[x] = record;
            continue;
          }

          // Verifica a existência de um correspondente do registro no inventário armazenado no índice "x".
          boolean contains = checkContains(inventories[x], record);

          if (contains)
            // Atribui o registro correspondente ao vetor. Caso não seja encontrado um registro correspondente, será mantido um valor nulo.
            records[x] = getEqualRecord(inventories[x], record);
        }

        // Verifica se há divergência nas quantidades dos registros adicionados ao vetor.
        if (!hasSameQuantity(records))
          divergences.add(new Divergence(records));
      }
    }

    // Retorna as divergências encontradas.
    return Collections.unmodifiableSet(divergences);
  }

  private boolean checkContains(Inventory inventory, Record record) {
    return inventory.getRecords().contains(record);
  }

  private boolean hasSameQuantity(Record... records) {
    final int UNDEFINED = -1;
    int quantity = UNDEFINED;

    if (isAllNull(records))
      return false;

    for (Record record : records)
      if (record == null)
        return false;
      else if (quantity == UNDEFINED)
        quantity = record.getQuantity();
      else if (quantity != record.getQuantity())
        return false;

    return true;
  }

  private boolean isAllNull(Record... records) {
    for (Record record : records)
      if (record != null)
        return false;

    return true;
  }

  private Record getEqualRecord(Inventory inventory, Record record) {
    for (Record r : inventory.getRecords())
      if (r.equals(record))
        return r;

    return null;
  }
}