package br.com.empretek.inventory.domain.report;

import java.util.Set;

public class ReportConfigurationBuilder {

  private String reportTitle;
  private String reportSubject;
  private Set<String> reportKeywords;
  private String reportAuthor;
  private String reportCreator;
  private String clientName;
  
  public ReportConfigurationBuilder toReportTitle(String reportTitle) {
    this.reportTitle = reportTitle;
    return this;
  }

  public ReportConfigurationBuilder toReportSubject(String reportSubject) {
    this.reportSubject = reportSubject;
    return this;
  }

  public ReportConfigurationBuilder toReportKeywords(Set<String> reportKeywords) {
    this.reportKeywords = reportKeywords;
    return this;
  }

  public ReportConfigurationBuilder toReportAuthor(String reportAuthor) {
    this.reportAuthor = reportAuthor;
    return this;
  }

  public ReportConfigurationBuilder toReportCreator(String reportCreator) {
    this.reportCreator = reportCreator;
    return this;
  }

  public ReportConfigurationBuilder toClientName(String clientName) {
    this.clientName = clientName;
    return this;
  }
  
  public ReportConfiguration build() {
    return new ReportConfiguration(reportTitle, reportSubject, reportKeywords, reportAuthor, reportCreator, clientName);
  }
}