package br.com.empretek.inventory.application;

import br.com.empretek.inventory.domain.report.ReportConfiguration;
import br.com.empretek.inventory.domain.report.ReportConfigurationBuilder;

public class ReportController extends GenericController {

  private ReportConfiguration configuration;

  public void configureReport() {
    final boolean required = true;
    
    ReportConfigurationBuilder builder = new ReportConfigurationBuilder();
    // Solicita e atribiu o nome do cliente.
    builder.toClientName(requestUserEntry("Informe o nome do cliente: ", required));
    // Solicita e atribiu o título do relatório.
    builder.toReportTitle(requestUserEntry("Informe o título do relatório: ", required));
    // Solicita e atribiu o assunto do relatório.
    builder.toReportSubject(requestUserEntry("Informe o assunto do relatório: ", required));
    // Solicita o nome do responsável pelo relatório - autor e criador.
    String reportAuthorAndCreator = requestUserEntry("Informe o nome do responsável pelo relatório: ", required);
    // Atribui o nome do autor do relatório.
    builder.toReportAuthor(reportAuthorAndCreator);
    // Atribui o nome do criador do relatório.
    builder.toReportCreator(reportAuthorAndCreator);
    // Solicita e atribiu as palavras-chave do relatório.
    builder.toReportSubject(requestUserEntry("Informe as palavras-chave do relatório: "));
    
    configuration = builder.build();
  }

}