package com.pluralis.plucker.export;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.rtf.RtfWriter2;
import com.pluralis.plucker.model.Actor;
import com.pluralis.plucker.model.Project;
import com.pluralis.plucker.model.Requirement;
import com.pluralis.plucker.model.UseCase;

public class PdfExport extends BaseExporter {

  public Rectangle pageSize = PageSize.A4;
  
  public float marginLeft = 108;
  public float marginRight = 72;
  public float marginTop = 72;
  public float marginBottom = 72;
  
  public float spacingBeforeParagraph = 12;
  public float spacingAfterParagraph = 6;
  
  public final Font HEADING_1 = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, Color.gray);
  public final Font HEADING_2 = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, Color.black);
  public final Font HEADING_3 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, Color.black);
  public final Font PLAIN = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.black);
  public final Font ITALIC = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC, Color.black);
  public final Font BOLD = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.black);
  
  private Project project;
  
  public PdfExport(Project project) {
    this.project = project;
  }

  private Paragraph createParagraph(String text) {
    Paragraph para = new Paragraph(text, PLAIN);
    para.setSpacingBefore(spacingBeforeParagraph);
    para.setSpacingAfter(spacingAfterParagraph);
    
    return para;
  }
  
  private Paragraph createHeading2(String text) {
    Paragraph para = new Paragraph(text, HEADING_2);
    para.setSpacingBefore(spacingBeforeParagraph);
    para.setSpacingAfter(spacingAfterParagraph);
    
    return para;
  }
  
  private Paragraph createHeading3(String text) {
    Paragraph para = new Paragraph(text, HEADING_3);
    para.setSpacingBefore(spacingBeforeParagraph);
    para.setSpacingAfter(spacingAfterParagraph);
    
    return para;
  }
  
  public void export(String filename) throws IOException, DocumentException  {
    
    Document document = new Document(pageSize, marginLeft, marginRight, marginTop, marginBottom);
    
    RtfWriter2.getInstance(document, new FileOutputStream(filename));
    
    document.open();
    
    document.add(new Paragraph("Software Requirements Specification", HEADING_1));
    document.add(createHeading2("Actors"));
    
    for (Actor actor : project.getActor()) {
      document.add(createHeading3(actor.getId() + " " + actor.getName()));
      document.add(createParagraph(actor.getDescription()));
      document.add(new Paragraph("Complexity: " + actor.getComplexity(), ITALIC));
    }

    document.add(createHeading2("Use Cases"));
    
    for (UseCase uc : project.getUsecase()) {
      document.add(createHeading3(uc.getId() + " " + uc.getName()));
      document.add(createParagraph(uc.getDescription()));
      document.add(new Paragraph("Complexity: " + uc.getComplexity(), ITALIC));
      document.add(new Paragraph("Primary Actor: " + uc.getPrimaryActor(), ITALIC));
    }

    document.add(createHeading2("Non-Functional Requirements"));
    
    for (Requirement req : project.getRequirement()) {
      document.add(createHeading3(req.getId() + " " + req.getName()));
      document.add(createParagraph(req.getDescription()));
    }

    document.close();
  }

}
