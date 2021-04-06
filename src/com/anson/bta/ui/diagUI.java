package com.anson.bta.ui;

import javax.swing.*;

public class diagUI {
    public static void informationBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "Build-To-Automation: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "Build-To-Automation: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }
}
