package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.*;

public class Controller {
    private  View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);

        view.init();
        controller.init();

    }

    public void init(){
        createNewDocument();
    }

    public void exit(){
        System.exit(0);
    }


    public HTMLDocument getDocument() {
        return document;
    }

    public void resetDocument(){
       if(document!=null) document.removeUndoableEditListener(view.getUndoListener());
       document= (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
       document.addUndoableEditListener(view.getUndoListener());
       view.update();
    }

    public void setPlainText(String text){
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader,document,0);
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public  String getPlainText(){

        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter,document,0, document.getLength());
        } catch (Exception e){
            ExceptionHandler.log(e);
        }

        return stringWriter.toString();

    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile=null;

    }

    public void openDocument() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int choose = fileChooser.showOpenDialog(view);
        if (choose == JFileChooser.OPEN_DIALOG) {
            if(currentFile!=null) {
                try {
                    currentFile =fileChooser.getSelectedFile();
                    view.setTitle(currentFile.getName());
                    view.selectHtmlTab();
                    resetDocument();
                    FileReader fileReader = new FileReader(currentFile);
                    new HTMLEditorKit().read(fileReader,document,0);
                    view.resetUndo();

                }catch (Exception e){
                    ExceptionHandler.log(e);
                }
            }
        }
    }

    public void saveDocument() {
        view.selectHtmlTab();
        if(currentFile!=null) {
            try {
                FileWriter writer = new FileWriter(currentFile);
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
         if(currentFile==null){
            saveDocumentAs();
        }

    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int choose = fileChooser.showSaveDialog(view);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile =fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter writer = new FileWriter(currentFile);
                new HTMLEditorKit().write(writer,document,0,document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }

    }

}
