/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedtaxidriver.OutputHandlers;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Singleton class to manage output.
 * @author Abhishek
 */
public class ServerOutputManager {
    private static ServerOutputManager serverOutputManager = null;
    private JTextPane textPane; 
    private StyledDocument doc;
    
    private ServerOutputManager() {
        
    }
    public static ServerOutputManager getSingletonInstance () {
        if (serverOutputManager == null) {
            serverOutputManager = new ServerOutputManager();
        }
        return serverOutputManager;
    }
    public void setTextPane(JTextPane textPane) {
        this.textPane = textPane;
        doc = textPane.getStyledDocument();
    }
    
    public void write(String input)  {
        // redirects data to the text area 

        Style style = textPane.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.cyan);

        try { 
            doc.insertString(doc.getLength(), input, style); 
            doc.insertString(doc.getLength(), "\n", style); 
        } catch (BadLocationException e){
            System.err.println(e);
        }
    }
    
    public void write(String input, Color colorCode)  {
        // redirects data to the text area 

        Style style = textPane.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, colorCode);

        try { 
            doc.insertString(doc.getLength(), input, style); 
            doc.insertString(doc.getLength(), "\n", style); 
        } catch (BadLocationException e){
            System.err.println(e);
        }
    }
}
