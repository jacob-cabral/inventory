package br.com.empretek.inventory.domain;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.lowagie.text.DocumentException;

public class ReportTest {

  @Test
  public void testReport() throws IOException, DocumentException {
    File target = new File("relatorio.pdf");
    target.createNewFile();
    Report report = new Report(target);
    report.write("Conteúdo de teste do relatório.");
    report.close();
  }
  
}