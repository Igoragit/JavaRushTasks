package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class View extends JFrame implements ActionListener{

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private Controller controller;
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);

        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){

            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        }
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init(){
        initGui();
        FrameListener frameListener= new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
        //frameListener.windowActivated(new WindowEvent());
    }

    public void exit(){
        controller.exit();
    }

    public void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this,jMenuBar);
        MenuHelper.initEditMenu(this,jMenuBar);
        MenuHelper.initStyleMenu(this,jMenuBar);
        MenuHelper.initAlignMenu(this,jMenuBar);
        MenuHelper.initColorMenu(this,jMenuBar);
        MenuHelper.initFontMenu(this,jMenuBar);
        MenuHelper.initHelpMenu(this,jMenuBar);
        getContentPane().add(jMenuBar,BorderLayout.NORTH);
    }

    public  void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML",scrollPane);

        JScrollPane painScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст",painScrollPane);
        tabbedPane.setPreferredSize(new Dimension());
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane,BorderLayout.CENTER);


    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {
       int index = tabbedPane.getSelectedIndex();
       if(index==0){
           controller.setPlainText(plainTextPane.getText());

       }
       if(index==1){
           plainTextPane.setText(controller.getPlainText());
       }

       resetUndo();
    }

    public boolean canUndo() {
        return  undoManager.canUndo();

    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public    void undo(){
       try {
           undoManager.undo();
       }catch (Exception e){
           ExceptionHandler.log(e);
       }
    }

    public void redo(){
       try {
           undoManager.redo();
       }catch (Exception e){
           ExceptionHandler.log(e);
       }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        isHtmlTabSelected();
        resetUndo();
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(null, "This is my soft", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
