package com.alteredmechanism.lslaf;

import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LookAndFeelEnumerator {

    public static void main(String[] args) {
        if (System.getProperty("os.name").startsWith("Mac")) {
            // Stop Java coffee cup icon from appearing in the dock on MacOS.
            // Older versions of MacOS X require this:
            System.setProperty("java.awt.headless", "true");
            // Newer versions (also?) require this:
            System.setProperty("apple.awt.UIElement", "true");
        }

        String systemLaf = UIManager.getSystemLookAndFeelClassName();
        LookAndFeelInfo[] lafInfos = UIManager.getInstalledLookAndFeels();
        int lengthOfLongestName = 0;
        int lengthOfLongestClass = 0;
        Map<String,String> lafs = new HashMap<String,String>();
        
        for (LookAndFeelInfo info : lafInfos) {
            String className = info.getClassName();
            String name = info.getName();
            lafs.put(name, className);
            if (name.length() > lengthOfLongestName) {
                lengthOfLongestName = name.length();
            }
            if (className.length() > lengthOfLongestClass) {
                lengthOfLongestClass = className.length();
            }
        }
         
        final String lineFormat = "%-" + lengthOfLongestName + "s %-" + lengthOfLongestClass + "s";
        for (String name : lafs.keySet()) {
            String className = lafs.get(name);
            System.out.printf(lineFormat, name, className);
            if (className.equals(systemLaf)) {
                System.out.println(" SystemLookAndFeel");
            }
            else {
                System.out.println();
            }
        }
    
    }

}
