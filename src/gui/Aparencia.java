/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.UIManager;

/**
 *
 * @author brenno
 */
public class Aparencia {
    public static String lookAndFeel(){
        String sistema = System.getProperty("os.name");
        switch(sistema){
            case "Linux":return "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            case "Windows": return "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            default: return UIManager.getSystemLookAndFeelClassName();
        }
    }
}
