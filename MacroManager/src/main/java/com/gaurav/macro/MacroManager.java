package com.gaurav.macro;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit; // ✅ Correct import
import java.awt.TrayIcon;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MacroManager {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MacroAppFrame().setVisible(true));
    }
}

class MacroAppFrame extends JFrame implements NativeKeyListener {
    private JTextArea macroDisplay;
    private Map<String, List<MacroAction>> macros = new HashMap<>();
    private final String macroFile = "macros.json";
    private Robot robot;

    public MacroAppFrame() {
        setTitle("Keyboard Macro Manager");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        JLabel heading = new JLabel("Keyboard Macro Manager", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        add(heading, BorderLayout.NORTH);

        macroDisplay = new JTextArea();
        macroDisplay.setEditable(false);
        add(new JScrollPane(macroDisplay), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton createBtn = new JButton("Create New Macro");
        JButton manageBtn = new JButton("Manage Macros");
        JButton trayBtn = new JButton("Minimize to Tray");

        buttonsPanel.add(createBtn);
        buttonsPanel.add(manageBtn);
        buttonsPanel.add(trayBtn);
        add(buttonsPanel, BorderLayout.SOUTH);

        createBtn.addActionListener(e -> createMacro());
        manageBtn.addActionListener(e -> manageMacros());
        trayBtn.addActionListener(e -> minimizeToTray());

        loadMacros();
        refreshMacroList();

        try {
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
            logger.setUseParentHandlers(false);
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createMacro() {
        String key = JOptionPane.showInputDialog(this, "Enter the key to bind (e.g. F2):");
        if (key == null || key.isEmpty()) return;

        if (macros.containsKey(key)) {
            int option = JOptionPane.showConfirmDialog(this, "Macro already exists for this key. Overwrite?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (option != JOptionPane.YES_OPTION) return;
        }

        List<MacroAction> actions = new ArrayList<>();
        JDialog actionDialog = new JDialog(this, "Macro for " + key, true);
        actionDialog.setSize(400, 300);
        actionDialog.setLayout(new BorderLayout());
        actionDialog.setLocationRelativeTo(this);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> actionList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(actionList);
        actionDialog.add(scrollPane, BorderLayout.CENTER);

        JComboBox<String> actionType = new JComboBox<>(new String[]{"Text Input", "Mouse Click", "Delay", "Open Program"});
        JButton addAction = new JButton("Add Action");
        JButton saveAction = new JButton("Save Macro");

        JPanel controlPanel = new JPanel();
        controlPanel.add(actionType);
        controlPanel.add(addAction);
        controlPanel.add(saveAction);
        actionDialog.add(controlPanel, BorderLayout.SOUTH);

        addAction.addActionListener(e -> {
            String selected = (String) actionType.getSelectedItem();
            if (selected.equals("Text Input")) {
                String input = JOptionPane.showInputDialog(this, "Enter the text to type:");
                if (input != null) {
                    actions.add(new MacroAction("text", input));
                    listModel.addElement("Text - " + input);
                }
            } else if (selected.equals("Mouse Click")) {
                actions.add(new MacroAction("click", null));
                listModel.addElement("Mouse Click");
            } else if (selected.equals("Delay")) {
                String input = JOptionPane.showInputDialog(this, "Enter delay in seconds:");
                try {
                    double delay = Double.parseDouble(input);
                    actions.add(new MacroAction("delay", delay));
                    listModel.addElement("Delay - " + delay + "s");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid number");
                }
            } else if (selected.equals("Open Program")) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String path = fc.getSelectedFile().getAbsolutePath();
                    actions.add(new MacroAction("program", path));
                    listModel.addElement("Open Program - " + path);
                }
            }
        });

        saveAction.addActionListener(e -> {
            macros.put(key, actions);
            saveMacros();
            refreshMacroList();
            actionDialog.dispose();
        });

        actionDialog.setVisible(true);
    }

    private void manageMacros() {
        JOptionPane.showMessageDialog(this, "Manage Macros feature to be implemented.");
    }

    private void minimizeToTray() {
        if (SystemTray.isSupported()) {
            try {
                SystemTray tray = SystemTray.getSystemTray();
                Image icon = Toolkit.getDefaultToolkit().createImage("icon.png");
                if (icon == null) {
                    JOptionPane.showMessageDialog(this, "Tray icon not found!");
                    return;
                }

                PopupMenu popup = new PopupMenu();
                MenuItem openItem = new MenuItem("Open");
                openItem.addActionListener(e -> this.setVisible(true));
                popup.add(openItem);

                MenuItem exitItem = new MenuItem("Exit");
                exitItem.addActionListener(e -> System.exit(0));
                popup.add(exitItem);

                TrayIcon trayIcon = new TrayIcon(icon, "Macro Manager", popup);
                trayIcon.setImageAutoSize(true);
                tray.add(trayIcon);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "System tray not supported");
        }
    }

    private void refreshMacroList() {
        StringBuilder builder = new StringBuilder();
        if (macros.isEmpty()) {
            builder.append("No macros available.");
        } else {
            for (String key : macros.keySet()) {
                builder.append("• ").append(key).append("\n");
            }
        }
        macroDisplay.setText(builder.toString());
    }

    private void loadMacros() {
        try {
            if (Files.exists(Paths.get(macroFile))) {
                String json = new String(Files.readAllBytes(Paths.get(macroFile)));
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<Map<String, List<MacroAction>>>() {}.getType();
                macros = gson.fromJson(json, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveMacros() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(macros);
            Files.write(Paths.get(macroFile), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeMacro(List<MacroAction> actions) {
        for (MacroAction action : actions) {
            try {
                switch (action.type) {
                    case "text":
                        String text = (String) action.value;
                        for (char ch : text.toCharArray()) {
                            int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
                            if (keyCode != KeyEvent.VK_UNDEFINED) {
                                robot.keyPress(keyCode);
                                robot.keyRelease(keyCode);
                            }
                        }
                        break;
                    case "click":
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case "delay":
                        Double delaySec = ((Number) action.value).doubleValue();
                        Thread.sleep((long)(delaySec * 1000));
                        break;
                    case "program":
                        String path = (String) action.value;
                        new ProcessBuilder(path).start();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
        if (macros.containsKey(keyText)) {
            executeMacro(macros.get(keyText));
        }
    }

    @Override public void nativeKeyReleased(NativeKeyEvent e) {}
    @Override public void nativeKeyTyped(NativeKeyEvent e) {}
}

class MacroAction {
    String type;
    Object value;

    MacroAction(String type, Object value) {
        this.type = type;
        this.value = value;
    }
}
