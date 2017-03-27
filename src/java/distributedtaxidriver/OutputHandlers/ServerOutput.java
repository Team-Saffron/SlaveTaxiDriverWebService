/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedtaxidriver.OutputHandlers;

import distributedtaxidriver.OutputHandlers.CustomOutputStream;
import distributedtaxidriver.OutputHandlers.CustomErrorStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author Abhishek
 */
public class ServerOutput extends JFrame {
    private JTextPane textPane;
    
    private PrintStream standardOut;
    private PrintStream standardErr;
    
    public void execute() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        initComponents();
        this.setVisible(true);
    }
    private void initComponents() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.black);
        textPane.setFont(new Font("Lucida Console", Font.PLAIN, 15));
        textPane.setForeground(Color.green);
        
        /**
         * Set Text pane
         */
        PrintStream outputPrintStream = new PrintStream(new CustomOutputStream(textPane));
        PrintStream errorPrintStream = new PrintStream(new CustomErrorStream(textPane));
        ServerOutputManager serverOutputManager = ServerOutputManager.getSingletonInstance();
        serverOutputManager.setTextPane(textPane);
        // keeps reference of standard output stream
        standardOut = System.out;
        standardErr = System.err; 
        // re-assigns standard output stream and error output stream
        System.setOut(outputPrintStream);
        System.setErr(errorPrintStream);
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        
        this.add(new JScrollPane(textPane), constraints);
    }
}
