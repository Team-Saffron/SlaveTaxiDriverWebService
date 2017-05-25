/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedtaxidriver.OutputHandlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Abhishek
 */
public class Logger {
    private static final String LOG_FILE = "D:\\\\server.log"; 
   
    public Logger() {
        
    }
    
    public static void write(String data) {
        File logFile = new File(LOG_FILE);
        BufferedWriter bw = null;
	FileWriter fw = null;
        
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data + "\n");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null)
                        bw.close();
                if (fw != null)
                        fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
    }
}
