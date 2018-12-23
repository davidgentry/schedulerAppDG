/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;

/**
 *
 * @author David Gentry
 */
public class LoggerUtility {
   
        private final static Logger LOGGER = Logger.getLogger(LoggerUtility.class.getName());
        private static FileHandler handler = null;
 
        public static void init(){
           try {
           handler = new FileHandler("logFile.txt");
           } catch (SecurityException | IOException error) {
            }
           Logger logger = Logger.getLogger("");
           handler.setFormatter(new SimpleFormatter());
           logger.addHandler(handler);
           logger.setLevel(Level.INFO);
        }

}
