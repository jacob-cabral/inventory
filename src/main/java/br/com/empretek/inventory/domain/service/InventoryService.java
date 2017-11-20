package br.com.empretek.inventory.domain.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import br.com.empretek.inventory.domain.Divergence;
import br.com.empretek.inventory.domain.Inventory;
import br.com.empretek.inventory.domain.Record;
import br.com.empretek.inventory.infrastructure.StringUtil;

public class InventoryService {

  private static final int CODE_INDEX = 0;
  private static final int QUANTITY_INDEX = 1;

  public Inventory inventory(File source) throws IOException {
    Inventory inventory = new Inventory();

    StringUtil util = StringUtil.getInstance();

    BufferedReader reader = new BufferedReader(new FileReader(source));
    String line = null;

    while (util.validateNotNull(line = reader.readLine())) {
      if (!util.validateNotEmpty(line))
        continue;

      String[] data = line.trim().split(",");
      String code = null;
      String quantity = null;

      if (util.validateNotNull(data) && !Arrays.asList(data).isEmpty()) {
        code = data[CODE_INDEX];

        if (!util.validateNotNull(code) || !util.validateNotEmpty(code))
          continue;

        if (Arrays.asList(data).size() > 1)
          quantity = data[QUANTITY_INDEX];

        if (!util.validateNotNull(quantity) || !util.validateNotEmpty(quantity))
          quantity = "1";

        inventory.add(new Record(source, Long.parseLong(code), Integer.parseInt(quantity)));
      }
    }

    if (reader != null)
      reader.close();

    return inventory;
  }

  public Set<Divergence> compare(Inventory inventory, Inventory other) {
    return compare(inventory, other);
  }

  public Set<Divergence> compare(Inventory... inventories) {
    Set<Divergence> divergences = new HashSet<>();

    if (inventories == null || Arrays.asList(inventories).isEmpty())
      throw new IllegalArgumentException("Erro ao comparar os inventários. Os argumentos são inválidos.");

    for (int i = 0; i < inventories.length; i++) {
      Inventory inventory = inventories[i];

      for (Record record : inventory.getRecords()) {
        Record[] records = new Record[inventories.length];

        for (int x = 0; x < inventories.length; x++) {
          if (i == x) {
            records[x] = record;
            continue;
          }

          boolean contains = checkContains(inventories[x], record);

          if (contains)
            records[x] = getEqualRecord(inventories[x], record);
        }

        if (!hasSameQuantity(records))
          divergences.add(new Divergence(records));
      }
    }

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