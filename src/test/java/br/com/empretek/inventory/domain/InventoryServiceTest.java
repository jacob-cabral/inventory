package br.com.empretek.inventory.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.empretek.inventory.domain.Inventory;
import br.com.empretek.inventory.domain.Record;
import br.com.empretek.inventory.domain.data.InvalidDataException;
import br.com.empretek.inventory.domain.service.InventoryService;

public class InventoryServiceTest {

  private static final int FIRST_INDEX = 0;
  private static final int SECOND_INDEX = 1;

  private static final File SOURCE_1 = new File("source-1.txt");
  private static final File SOURCE_2 = new File("source-2.txt");

  private static final long CODE_FOR_RECORD_WITH_DIVERGENCE_QUANTITY = 7897760900434L;
  private static final long CODE_FOR_RECORD_THAT_ONLY_APPEARS_IN_ONE_INVENTORY = 7898917000298L;
  
  private List<File> sources;
  private List<Inventory> inventories;

  @Before
  public void setup() throws IOException {
    String[] records1 = { "7896316111133,97", "7897534803831,155", "7897534812673,12", "7897534812673,4", "7898198860017,2", "7896316111133,46", "7896316111133,48", "7897760900434,01", "7897760900434,01", "7897760900434,01" };
    String[] records2 = { "7896316111133,97", "7897534803831,155", "7897534812673,12", "7897534812673,4", "7898917000298,18", "7898198860017,2", "7896316111133,46", "7896316111133,48", "7897760900434,01", "7897760900434,01" };

    writeContentToFile(records1, SOURCE_1);
    writeContentToFile(records2, SOURCE_2);

    Inventory inventory1 = new Inventory();
    Inventory inventory2 = new Inventory();

    inventory1.add(new Record(SOURCE_1, 7896316111133L, 97));
    inventory1.add(new Record(SOURCE_1, 7897534803831L, 155));
    inventory1.add(new Record(SOURCE_1, 7897534812673L, 12));
    inventory1.add(new Record(SOURCE_1, 7897534812673L, 4));
    inventory1.add(new Record(SOURCE_1, 7898198860017L, 2));
    inventory1.add(new Record(SOURCE_1, 7896316111133L, 46));
    inventory1.add(new Record(SOURCE_1, 7896316111133L, 48));
    inventory1.add(new Record(SOURCE_1, CODE_FOR_RECORD_WITH_DIVERGENCE_QUANTITY, 1));
    inventory1.add(new Record(SOURCE_1, CODE_FOR_RECORD_WITH_DIVERGENCE_QUANTITY, 01));
    inventory1.add(new Record(SOURCE_1, CODE_FOR_RECORD_WITH_DIVERGENCE_QUANTITY, 01));

    inventory2.add(new Record(SOURCE_2, 7896316111133L, 97));
    inventory2.add(new Record(SOURCE_2, 7897534803831L, 155));
    inventory2.add(new Record(SOURCE_2, 7897534812673L, 12));
    inventory2.add(new Record(SOURCE_2, 7897534812673L, 4));
    inventory2.add(new Record(SOURCE_2, CODE_FOR_RECORD_THAT_ONLY_APPEARS_IN_ONE_INVENTORY, 18));
    inventory2.add(new Record(SOURCE_2, 7898198860017L, 2));
    inventory2.add(new Record(SOURCE_2, 7896316111133L, 46));
    inventory2.add(new Record(SOURCE_2, 7896316111133L, 48));
    inventory2.add(new Record(SOURCE_2, CODE_FOR_RECORD_WITH_DIVERGENCE_QUANTITY, 1));
    inventory2.add(new Record(SOURCE_2, CODE_FOR_RECORD_WITH_DIVERGENCE_QUANTITY, 01));

    sources = Arrays.asList(new File[] { SOURCE_1, SOURCE_2 });
    inventories = Arrays.asList(new Inventory[] { inventory1, inventory2 });
  }
  
  @AfterClass
  public static void clear() {
    SOURCE_1.deleteOnExit();
    SOURCE_2.deleteOnExit();
  }

  @Test
  public void inventoryTest() throws FileNotFoundException {
    InventoryService service = new InventoryService();

    try {
      Inventory inventory = service.inventory(sources.get(FIRST_INDEX));

      Assert.assertArrayEquals(inventories.get(FIRST_INDEX).getRecords().toArray(), inventory.getRecords().toArray());
    } catch (InvalidDataException e) { }
  }

  @Test
  public void compareInventoriesTest() throws FileNotFoundException {
    InventoryService service = new InventoryService();

    try {
      Inventory inventory1 = service.inventory(sources.get(FIRST_INDEX));
      Inventory inventory2 = service.inventory(sources.get(SECOND_INDEX));
      
      Set<Divergence> divergences = service.compare(inventory1, inventory2);
      
      Assert.assertFalse(divergences.isEmpty());
      Assert.assertEquals(2, divergences.size());
    } catch (InvalidDataException e) { }
  }

  @Test
  public void compareTwoInventoriesTest() {
    Assert.fail("Not implemented yet!");
  }

  private void writeContentToFile(String[] records1, File source1) throws IOException {
    PrintWriter writer = new PrintWriter(new FileWriter(source1));

    for (String content : records1)
      writer.println(content);

    writer.flush();
    writer.close();
  }
}