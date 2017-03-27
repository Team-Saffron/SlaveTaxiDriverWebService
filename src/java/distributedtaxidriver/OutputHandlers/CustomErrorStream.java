package distributedtaxidriver.OutputHandlers;
 
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
 
/**
 * This class extends from OutputStream to redirect output to a JTextArrea
 * @author Abhishek
 *
 */
public class CustomErrorStream extends OutputStream {
    private JTextPane textPane;
    
    public CustomErrorStream(JTextPane textPane) {
        this.textPane = textPane;
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        
        StyledDocument doc = textPane.getStyledDocument();

        Style style = textPane.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.red);

        try { 
            doc.insertString(doc.getLength(), Character.toString((char) b),style); 
        } catch (BadLocationException e){
            System.err.println(e);
        }
        /*
        textArea.setForeground(Color.red);
        textArea.append(String.valueOf((char)b));
        
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
        */
    }
}