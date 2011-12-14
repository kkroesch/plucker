package com.pluralis.plucker.gui.dialog;



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.FocusRequester;
import com.pluralis.plucker.gui.action.CancelDialog;
import com.pluralis.plucker.gui.action.CreateDialog;
import com.pluralis.plucker.gui.action.HelpContent;
import com.pluralis.plucker.gui.action.OkDialog;
import com.pluralis.plucker.gui.widget.FileList;
import com.pluralis.plucker.model.UseCase;


/**
 * 
 * @author karsten.kroesch
 */
public class UseCaseEditor extends JPanel implements Editor {

  private ListModel complexityListModel = new DefaultComboBoxModel(UseCase.complexities);
  private ListModel statusListModel = new DefaultComboBoxModel(UseCase.possibleStati);
  private ListModel levelModel = new DefaultComboBoxModel(UseCase.levels);
  
  private UseCase usecase;
  
  private PresentationModel adapter;
  
  private Application application;
  
  private JDialog dialog;
  
  private JTabbedPane tabPane;

  public void add() {
    adapter.triggerCommit();
    application.getProject().generateId(usecase);
    application.getProject().getUsecase().add((UseCase) adapter.getBean());
    application.getProject().getUsecaseModel().fireTableDataChanged();
  }
  
  public void save() {
    adapter.triggerCommit();
    application.getProject().getUsecaseModel().fireTableDataChanged();
  }
  
  public void cancel() {
    adapter.triggerFlush();
  }
  
  /** Creates new form UseCaseEditor */
  public UseCaseEditor(Application application) {
    this(new UseCase(), application);
  }
  
  public UseCaseEditor(UseCase usecase, Application application) {
    this.usecase = usecase;
    this.application = application;
    adapter = new PresentationModel(usecase);
    dialog = new JDialog(application.rootWindow(), "Edit Use Case", true);

    setLayout(new BorderLayout(2,2));
    JLabel usecaseTitle = new JLabel(usecase.getName());
    usecaseTitle.setFont(new java.awt.Font("Tahoma", 1, 14));
    usecaseTitle.setBorder(new EmptyBorder(21,21,21,21));
    add(usecaseTitle, BorderLayout.NORTH);

    tabPane = new javax.swing.JTabbedPane();
    tabPane.addTab("Main", createOverview());
    tabPane.addTab("Supplemental", createSupplemental());
    add(tabPane, BorderLayout.CENTER);
  }
  
  public void openDialog(boolean create) {
    dialog.getContentPane().add(this, BorderLayout.CENTER);
    
    JPanel buttonDialog = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    if (create)
      buttonDialog.add(new JButton(new CreateDialog(dialog, this)));
    else
      buttonDialog.add(new JButton(new OkDialog(dialog, this)));
    buttonDialog.add(new JButton(new CancelDialog(dialog)));
    buttonDialog.add(new JButton(new HelpContent(application, "uc")));
    
    dialog.getContentPane().add(buttonDialog, BorderLayout.SOUTH);
    dialog.pack();
    application.centerDialog(dialog);
    dialog.setVisible(true);
  }
  
  public void setTabIndex(int index) {
    tabPane.setSelectedIndex(index);
  }
  
  private JComponent createOverview() {
    FormLayout layout = new FormLayout(
        "right:pref, 6dlu, 120dlu:grow, 6dlu, right:pref, 6dlu, 75dlu", "");
    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.setBorder(new EmptyBorder(6,6,6,6));

    builder.appendSeparator("General");
    JTextField nameField = BasicComponentFactory.createTextField(adapter.getBufferedModel("name"));
    dialog.addWindowListener(new FocusRequester(nameField));
    builder.append("Name", nameField);
    
    builder.append("Priority", BasicComponentFactory.createIntegerField(adapter.getBufferedModel("priority")));
    
    JTextArea descriptionArea = BasicComponentFactory.createTextArea(adapter.getBufferedModel("description"));
    descriptionArea.setRows(4);
    builder.append("Description", new JScrollPane(descriptionArea), 5);
    
    builder.appendSeparator("Actors");
    builder.append("Primary", BasicComponentFactory.createComboBox(
        new SelectionInList(application.getProject().getActorListModel(), 
            adapter.getBufferedModel("primaryActor"))));
    builder.append("Supporting", BasicComponentFactory.createComboBox(
        new SelectionInList(application.getProject().getActorListModel(), 
            adapter.getBufferedModel("supportingActor"))));
    
    builder.appendSeparator("Workflow");
    JTextArea preArea = BasicComponentFactory.createTextArea(adapter.getBufferedModel("preCondition"));
    preArea.setRows(2);
    builder.append("Preconditions", new JScrollPane(preArea), 5);
    JTextArea proseArea = BasicComponentFactory.createTextArea(adapter.getBufferedModel("flow"));
    proseArea.setRows(5);
    builder.append("Steps", new JScrollPane(proseArea), 5);
    JTextArea postArea = BasicComponentFactory.createTextArea(adapter.getBufferedModel("postCondition"));
    postArea.setRows(2);
    builder.append("Postconditions", new JScrollPane(postArea), 5);
    
    builder.appendSeparator("Details");
    builder.append("Level", BasicComponentFactory.createComboBox(
        new SelectionInList(levelModel, adapter.getBufferedModel("level"))));
    builder.append("Complexity", BasicComponentFactory.createComboBox(
        new SelectionInList(complexityListModel, adapter.getBufferedModel("complexity"))));
    builder.append("Assigned to", BasicComponentFactory.createComboBox(
        new SelectionInList(application.getProject().getUseCaseListModel(), 
            adapter.getBufferedModel("parent"))));
    builder.append("Status", BasicComponentFactory.createComboBox(
        new SelectionInList(statusListModel, adapter.getBufferedModel("status"))));
    builder.append("Release", new JComboBox());
    builder.nextLine();
    builder.append("Verifying Test Case", BasicComponentFactory.createComboBox(
        new SelectionInList(application.getProject().getTestCase(), adapter.getBufferedModel("testCase"))), 5);

    return builder.getPanel();
  }
  
  private JComponent createSupplemental() {
    FormLayout layout = new FormLayout("150dlu:grow", "");
    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    builder.setBorder(new EmptyBorder(6,6,6,6));
    
    builder.appendSeparator("Related Documents");
    builder.append(new FileList(usecase.getRelatedDoc(), application).create());
    builder.appendSeparator("Open Issues");
    builder.append(new FileList(new ArrayList<String>(), application).create());
    builder.appendSeparator("Notes");
    builder.append(new JScrollPane(new JTextArea(4,40)));
   
    return builder.getPanel();
  }
}
