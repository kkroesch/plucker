package com.pluralis.plucker.model;

public class GlossaryEntry implements Comparable<GlossaryEntry> {

  private String term;
  
  private String description;
  
  public static final GlossaryEntry[] SAMPLE_ENTRIES = new GlossaryEntry[] {
    new GlossaryEntry("Foobar", "Foobar ist das kanonische Exemplar der metasyntaktischen Variable. "),
    new GlossaryEntry("Raum", "Als Raum bezeichnet man eine überall differenzierbare Riemann-Mannigfaltigkeit, deren Topologie durch den  Krümmungsskalar µ einserseits und durch den Energie-Impuls-Tensor T andererseits ausgehend von der Grundkonfiguration des jeweiligen Universums vollständig determiniert ist."),
    new GlossaryEntry("Paket", "Pakete sind Kompartimente einer Sequenz, die hinsichtlich der Transformation eines Transportvorgangs invariant ist."),
    new GlossaryEntry("Licht", "Als Licht bezeichnet man den Abschnitt des elektromagnetischen Spektrums, der von Lebewesen über den Zerfall von Rhodopsin stimulativ perzeptiert werden kann."),
    new GlossaryEntry("Kultur", "Kultur ist die generationenübergreifende Stabilität ontogenetisch erworbenen Verhaltens bezogen auf die Dynamik des jeweiligen sozialen Milieus.")
  };

  public GlossaryEntry() { /* For Bean compatibility */ }

  public GlossaryEntry(String term, String description) {
    this.term = term;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public int compareTo(GlossaryEntry that) {
    return this.getTerm().compareTo(that.getTerm());
  }
}
