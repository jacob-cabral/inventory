package br.com.empretek.inventory.application;

import java.util.Scanner;

import br.com.empretek.inventory.infrastructure.StringUtil;

public abstract class GenericController {

  public String requestUserEntry(String label) {
    return requestUserEntry(label, false);
  }
  
  public String requestUserEntry(String label, boolean required) {
    String value = null;

    Scanner scanner = new Scanner(System.in);
    StringUtil util = StringUtil.getInstance();

    do {
      // Exibe a solicitação de entrada de dados.
      System.out.print(label);
      // Obtém o valor informado pelo usuário.
      value = scanner.nextLine();
    // Verifica se o valor é obrigatório e, quando for o caso, se é válido.
    } while (required && !(util.validateNotNull(value) && util.validateNotEmpty(value)));

    if (scanner != null)
      scanner.close();

    return value;
  }

}