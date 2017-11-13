package br.com.empretek.inventory.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import br.com.empretek.inventory.infrastructure.FileUtil;

public class Report {

  private Document document;

  public Report(File target) throws IOException, DocumentException {
    super();

    FileUtil util = FileUtil.getInstance();

    if (util.validateContentType("application/pdf", target))
      throw new IllegalArgumentException("O arquivo é inválido.");

    init(target);
  }

  private void init(File target) throws FileNotFoundException, DocumentException {
    document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(target));

    document.open();
    
    document.setPageSize(PageSize.A4);
    document.addHeader("EMPRETEK INVENTÁRIOS", "Conferência do Inventário.");
    document.addCreationDate();
    
    addMetaData(document);
    
    document.newPage();
  }

  public void write(String text) throws DocumentException {
    document.add(new Paragraph(text));
  }
  
  public void close() {
    document.close();
  }

  private void addMetaData(Document document) {
    String empretek = "Empretek Inventários";
    document.addTitle(empretek.toUpperCase());
    document.addSubject("Conferência do Inventário da Empresa ");
    document.addKeywords("Inventário, Conferência, Estoque, Empretek");
    document.addAuthor(empretek);
    document.addCreator(empretek);
  }
}