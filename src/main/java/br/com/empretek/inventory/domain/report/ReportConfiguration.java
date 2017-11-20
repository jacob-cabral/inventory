package br.com.empretek.inventory.domain.report;

import java.util.Collections;
import java.util.Set;

public class ReportConfiguration {

  private String reportTitle;
  private String reportSubject;
  private Set<String> reportKeywords;
  private String reportAuthor;
  private String reportCreator;
  private String clientName;

  public String getReportTitle() {
    return reportTitle;
  }

  public String getReportSubject() {
    return reportSubject;
  }

  public Set<String> getReportKeywords() {
    return Collections.unmodifiableSet(reportKeywords);
  }

  public String getReportAuthor() {
    return reportAuthor;
  }

  public String getReportCreator() {
    return reportCreator;
  }

  public String getClientName() {
    return clientName;
  }

  public ReportConfiguration(String reportTitle, String reportSubject, Set<String> reportKeywords, String reportAuthor,
      String reportCreator, String clientName) {
    super();
    this.reportTitle = reportTitle;
    this.reportSubject = reportSubject;
    this.reportKeywords = reportKeywords;
    this.reportAuthor = reportAuthor;
    this.reportCreator = reportCreator;
    this.clientName = clientName;
  }

}