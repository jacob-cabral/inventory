package br.com.empretek.inventory.application;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InventoryApplication {

  public static void main(String[] args) {
    SourceController sourceController = new SourceController();
    boolean hasMoreSources = true;
    Scanner scanner = new Scanner(System.in);

    while (hasMoreSources) {
      try {
        sourceController.addSource();
      } catch (RuntimeException e) {
        System.out.println(e.getMessage());
      }
      
      System.out.print("Deseja adicionar mais arquivos? (Sim ou Não): ");
      String yesOrNo = scanner.nextLine();
      hasMoreSources = Pattern.matches("^(\\s)*([sS][iI][mM])(\\s)*$", yesOrNo);
    }

    System.out.println(sourceController.getSources());
    scanner.close();
  }

}