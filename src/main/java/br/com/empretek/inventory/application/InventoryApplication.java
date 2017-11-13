package br.com.empretek.inventory.application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import br.com.empretek.inventory.infrastructure.FileUtil;

public class InventoryApplication {

  public static void main(String[] args) {
    SourceController controller = new SourceController();
    boolean hasMoreSources = true;
    Scanner scanner = new Scanner(System.in);

    while (hasMoreSources) {
      System.out.print("Informe o nome do arquivo: ");
      String filename = scanner.nextLine();
      
      try {
        File source = FileUtil.getInstance().getFile(filename);
        controller.add(source);
      } catch (RuntimeException | IOException e) {
        System.out.println(e.getMessage());
      }
      
      System.out.print("Deseja adicionar mais arquivos? (Sim ou Não): ");
      String yesOrNo = scanner.nextLine();
      hasMoreSources = Pattern.matches("^(\\s)*([sS][iI][mM])(\\s)*$", yesOrNo);
    }

    System.out.println(controller.getSources());
    scanner.close();
  }

}