/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movlad.semviz.view;

import com.movlad.semviz.application.CloudViewer;
import com.movlad.semviz.application.Controller;
import com.movlad.semviz.application.InvalidDirectoryException;
import com.movlad.semviz.core.SemvizException;
import com.movlad.semviz.core.math.geometry.PointCloud;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

/**
 * @author Vlad
 */
public class MainWindow extends javax.swing.JFrame implements Observer {

    private Controller svmgrController;

    private CloudViewer viewer;

    private List<String> commands = new ArrayList<>();
    private int commandIndex = -1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox_ViewSelection;
    private javax.swing.JLabel label_CloudInfoTable;
    private java.awt.Label label_CloudList;
    private javax.swing.JLabel label_StatusLED;
    private javax.swing.JLabel label_StatusText;
    private javax.swing.JLabel label_ViewSelection;
    private javax.swing.JList<String> list_CloudURI;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItem_Open;
    private javax.swing.JMenuItem menuItem_Quit;
    private javax.swing.JMenu menu_File;
    private javax.swing.JPanel panel_CloudList;
    private javax.swing.JPanel panel_Control;
    private javax.swing.JPanel panel_GLCanvas;
    private javax.swing.JPanel panel_Status;
    private javax.swing.JPanel panel_VarInfo;
    private javax.swing.JPanel panel_ViewSelection;
    private javax.swing.JScrollPane scroll_CloudURIList;
    private javax.swing.JScrollPane sroll_CloudInfoTable;
    private javax.swing.JTable table_CloudInfo;
    private javax.swing.JTextField textField_Command;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        initViewSelectionComboBox();
        initControllers();
        initCloudViewer();
        updateCloudInfoTable();
        updateCloudURIList();
        updateStatusIndicators();

        textField_Command.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        setFont(new FontUIResource(new Font("Arial", Font.PLAIN, 14)));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    private static void setFont(FontUIResource myFont) {
        UIManager.put("CheckBoxMenuItem.acceleratorFont", myFont);
        UIManager.put("Button.font", myFont);
        UIManager.put("ToggleButton.font", myFont);
        UIManager.put("RadioButton.font", myFont);
        UIManager.put("CheckBox.font", myFont);
        UIManager.put("ColorChooser.font", myFont);
        UIManager.put("ComboBox.font", myFont);
        UIManager.put("Label.font", myFont);
        UIManager.put("List.font", myFont);
        UIManager.put("MenuBar.font", myFont);
        UIManager.put("Menu.acceleratorFont", myFont);
        UIManager.put("RadioButtonMenuItem.acceleratorFont", myFont);
        UIManager.put("MenuItem.acceleratorFont", myFont);
        UIManager.put("MenuItem.font", myFont);
        UIManager.put("RadioButtonMenuItem.font", myFont);
        UIManager.put("CheckBoxMenuItem.font", myFont);
        UIManager.put("OptionPane.buttonFont", myFont);
        UIManager.put("OptionPane.messageFont", myFont);
        UIManager.put("Menu.font", myFont);
        UIManager.put("PopupMenu.font", myFont);
        UIManager.put("OptionPane.font", myFont);
        UIManager.put("Panel.font", myFont);
        UIManager.put("ProgressBar.font", myFont);
        UIManager.put("ScrollPane.font", myFont);
        UIManager.put("Viewport.font", myFont);
        UIManager.put("TabbedPane.font", myFont);
        UIManager.put("Slider.font", myFont);
        UIManager.put("Table.font", myFont);
        UIManager.put("TableHeader.font", myFont);
        UIManager.put("TextField.font", myFont);
        UIManager.put("Spinner.font", myFont);
        UIManager.put("PasswordField.font", myFont);
        UIManager.put("TextArea.font", myFont);
        UIManager.put("TextPane.font", myFont);
        UIManager.put("EditorPane.font", myFont);
        UIManager.put("TabbedPane.smallFont", myFont);
        UIManager.put("TitledBorder.font", myFont);
        UIManager.put("ToolBar.font", myFont);
        UIManager.put("ToolTip.font", myFont);
        UIManager.put("Tree.font", myFont);
        UIManager.put("FormattedTextField.font", myFont);
        UIManager.put("IconButton.font", myFont);
        UIManager.put("InternalFrame.optionDialogTitleFont", myFont);
        UIManager.put("InternalFrame.paletteTitleFont", myFont);
        UIManager.put("InternalFrame.titleFont", myFont);
    }

    private void initCloudViewer() {
        viewer = new CloudViewer(panel_GLCanvas.getWidth(), panel_GLCanvas.getHeight());

        panel_GLCanvas.add(viewer.getCanvas(), BorderLayout.CENTER);
    }

    private void initControllers() {
        svmgrController = Controller.get();

        svmgrController.addObserver(this);
    }

    private void initViewSelectionComboBox() {
        comboBox_ViewSelection.setEnabled(false);
        comboBox_ViewSelection.removeAllItems();

        comboBox_ViewSelection.addItem("High Resolution");
        comboBox_ViewSelection.addItem("High Resolution (Normals)");
        comboBox_ViewSelection.addItem("Convex Hull");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField_Command = new javax.swing.JTextField();
        panel_GLCanvas = new javax.swing.JPanel();
        panel_Control = new javax.swing.JPanel();
        panel_Status = new javax.swing.JPanel();
        label_StatusLED = new javax.swing.JLabel();
        label_StatusText = new javax.swing.JLabel();
        panel_CloudList = new javax.swing.JPanel();
        label_CloudList = new java.awt.Label();
        scroll_CloudURIList = new javax.swing.JScrollPane();
        list_CloudURI = new javax.swing.JList<>();
        panel_VarInfo = new javax.swing.JPanel();
        label_CloudInfoTable = new javax.swing.JLabel();
        sroll_CloudInfoTable = new javax.swing.JScrollPane();
        table_CloudInfo = new javax.swing.JTable();
        panel_ViewSelection = new javax.swing.JPanel();
        label_ViewSelection = new javax.swing.JLabel();
        comboBox_ViewSelection = new javax.swing.JComboBox<>();
        menuBar = new javax.swing.JMenuBar();
        menu_File = new javax.swing.JMenu();
        menuItem_Open = new javax.swing.JMenuItem();
        menuItem_Quit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textField_Command.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        textField_Command.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField_CommandKeyPressed(evt);
            }
        });

        panel_GLCanvas.setBackground(new java.awt.Color(0, 0, 0));
        panel_GLCanvas.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panel_GLCanvasComponentResized(evt);
            }
        });

        javax.swing.GroupLayout panel_GLCanvasLayout = new javax.swing.GroupLayout(panel_GLCanvas);
        panel_GLCanvas.setLayout(panel_GLCanvasLayout);
        panel_GLCanvasLayout.setHorizontalGroup(
                panel_GLCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 708, Short.MAX_VALUE)
        );
        panel_GLCanvasLayout.setVerticalGroup(
                panel_GLCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_Control.setBackground(new java.awt.Color(204, 204, 204));

        label_StatusLED.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        label_StatusLED.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_StatusLED.setText("�");

        label_StatusText.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        label_StatusText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_StatusText.setText("Ontology Model Loaded");

        panel_CloudList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        label_CloudList.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label_CloudList.setText("Cloud List");

        list_CloudURI.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        list_CloudURI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        list_CloudURI.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                list_CloudURIValueChanged(evt);
            }
        });
        scroll_CloudURIList.setViewportView(list_CloudURI);

        javax.swing.GroupLayout panel_CloudListLayout = new javax.swing.GroupLayout(panel_CloudList);
        panel_CloudList.setLayout(panel_CloudListLayout);
        panel_CloudListLayout.setHorizontalGroup(
                panel_CloudListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_CloudListLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(panel_CloudListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_CloudListLayout.createSequentialGroup()
                                                .addComponent(label_CloudList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(scroll_CloudURIList))
                                .addContainerGap())
        );
        panel_CloudListLayout.setVerticalGroup(
                panel_CloudListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_CloudListLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label_CloudList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scroll_CloudURIList, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                .addContainerGap())
        );

        label_CloudInfoTable.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        label_CloudInfoTable.setText("Queried Info");

        table_CloudInfo.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        sroll_CloudInfoTable.setViewportView(table_CloudInfo);

        javax.swing.GroupLayout panel_VarInfoLayout = new javax.swing.GroupLayout(panel_VarInfo);
        panel_VarInfo.setLayout(panel_VarInfoLayout);
        panel_VarInfoLayout.setHorizontalGroup(
                panel_VarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_VarInfoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_VarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sroll_CloudInfoTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(panel_VarInfoLayout.createSequentialGroup()
                                                .addComponent(label_CloudInfoTable)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panel_VarInfoLayout.setVerticalGroup(
                panel_VarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_VarInfoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label_CloudInfoTable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sroll_CloudInfoTable, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label_ViewSelection.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        label_ViewSelection.setText("View");

        comboBox_ViewSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        comboBox_ViewSelection.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBox_ViewSelectionItemStateChanged(evt);
            }
        });
        comboBox_ViewSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                none(evt);
            }
        });

        javax.swing.GroupLayout panel_ViewSelectionLayout = new javax.swing.GroupLayout(panel_ViewSelection);
        panel_ViewSelection.setLayout(panel_ViewSelectionLayout);
        panel_ViewSelectionLayout.setHorizontalGroup(
                panel_ViewSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_ViewSelectionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_ViewSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboBox_ViewSelection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panel_ViewSelectionLayout.createSequentialGroup()
                                                .addComponent(label_ViewSelection)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panel_ViewSelectionLayout.setVerticalGroup(
                panel_ViewSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_ViewSelectionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label_ViewSelection)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBox_ViewSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_StatusLayout = new javax.swing.GroupLayout(panel_Status);
        panel_Status.setLayout(panel_StatusLayout);
        panel_StatusLayout.setHorizontalGroup(
                panel_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel_StatusLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label_StatusLED, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_StatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(34, Short.MAX_VALUE))
                        .addComponent(panel_CloudList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_VarInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_ViewSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_StatusLayout.setVerticalGroup(
                panel_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_StatusLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(panel_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label_StatusLED, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label_StatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panel_CloudList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panel_VarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panel_ViewSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout panel_ControlLayout = new javax.swing.GroupLayout(panel_Control);
        panel_Control.setLayout(panel_ControlLayout);
        panel_ControlLayout.setHorizontalGroup(
                panel_ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel_Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_ControlLayout.setVerticalGroup(
                panel_ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel_Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menu_File.setText("File");

        menuItem_Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItem_Open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-opened-folder-16.png"))); // NOI18N
        menuItem_Open.setText("Open");
        menuItem_Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_OpenActionPerformed(evt);
            }
        });
        menu_File.add(menuItem_Open);

        menuItem_Quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menuItem_Quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-exit-16.png"))); // NOI18N
        menuItem_Quit.setText("Exit");
        menuItem_Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_QuitActionPerformed(evt);
            }
        });
        menu_File.add(menuItem_Quit);

        menuBar.add(menu_File);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textField_Command)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel_Control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel_GLCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panel_GLCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panel_Control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField_Command, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItem_OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_OpenActionPerformed
        final JFileChooser fc = new JFileChooser();

        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getAbsolutePath();

            try {
                svmgrController.load(path);

                JOptionPane.showMessageDialog(this, "Semviz data directory successfully loaded.",
                        "Info", JOptionPane.INFORMATION_MESSAGE);

                textField_Command.setEnabled(true);
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(this, "File could not be loaded.", "Error", JOptionPane.ERROR_MESSAGE);

                textField_Command.setEnabled(false);
            } catch (InvalidDirectoryException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_menuItem_OpenActionPerformed

    private void textField_CommandKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_CommandKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                String queryString = textField_Command.getText();

                commands.add(queryString);
                commandIndex = commands.size();

                // TODO

                textField_Command.setText("");

                break;
            case KeyEvent.VK_UP:
                if ((commandIndex - 1) >= 0) {
                    commandIndex--;
                    textField_Command.setText(commands.get(commandIndex));

                }
                break;
            case KeyEvent.VK_DOWN:
                if ((commandIndex + 1) <= commands.size()) {
                    commandIndex++;

                    String text;

                    if (commandIndex >= commands.size()) {
                        text = "";
                    } else {
                        text = commands.get(commandIndex);
                    }

                    textField_Command.setText(text);

                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_textField_CommandKeyPressed

    private void panel_GLCanvasComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panel_GLCanvasComponentResized
        viewer.getCanvas().setSize(panel_GLCanvas.getWidth(), panel_GLCanvas.getHeight());
    }//GEN-LAST:event_panel_GLCanvasComponentResized

    private void list_CloudURIValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_list_CloudURIValueChanged
        updateCloudInfoTable();

        PointCloud cloud = null;

        if (!list_CloudURI.isSelectionEmpty()) {
            // TODO
        }

        viewer.setCloud(cloud);

        if (cloud != null) {
            comboBox_ViewSelection.setEnabled(true);
            comboBox_ViewSelection.setSelectedIndex(0);
        } else {
            comboBox_ViewSelection.setEnabled(false);
        }
    }//GEN-LAST:event_list_CloudURIValueChanged

    private void none(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_none
        // TODO add your handling code here:
    }//GEN-LAST:event_none

    private void comboBox_ViewSelectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBox_ViewSelectionItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED
                && comboBox_ViewSelection.isEnabled() && comboBox_ViewSelection.getSelectedIndex() >= 0) {
            viewer.setView(comboBox_ViewSelection.getSelectedIndex());
        }
    }//GEN-LAST:event_comboBox_ViewSelectionItemStateChanged

    private void menuItem_QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_QuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItem_QuitActionPerformed
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        updateCloudURIList();
        updateStatusIndicators();
    }

    /**
     * Updates the list with new information from the last executed query.
     */
    private void updateCloudURIList() {
        list_CloudURI.removeAll();

        Vector<String> uris = new Vector<>();

        // TODO

        list_CloudURI.setListData(uris);
    }

    /**
     * Upon cloud URI selection, the table is updated with vars
     */
    private void updateCloudInfoTable() {
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Variable");
        tableModel.addColumn("Value");

        panel_VarInfo.setEnabled(false);

        if (!list_CloudURI.isSelectionEmpty()) {
            String cloudURI = list_CloudURI.getSelectedValue();

            /*
            // TODO

            for (QuerySolution querySolution : svmgrController.getCloudSelection()) {
                if (cloudURI.equals(querySolution.getResource("?cloud").getLocalName())) {
                    Iterator<String> varNameIt = querySolution.varNames();

                    while (varNameIt.hasNext()) {
                        String varName = varNameIt.next();

                        if (!varName.equals("cloud")) {
                            String varValue;

                            if (querySolution.get(varName).isResource()) {
                                varValue = querySolution.getResource(varName).getLocalName();
                            } else {
                                varValue = querySolution.getLiteral(varName).getValue().toString();
                            }

                            String[] data = new String[2];

                            data[0] = varName;
                            data[1] = varValue;

                            tableModel.addRow(data);
                        }
                    }
                }
            }*/

            if (tableModel.getRowCount() > 0) {
                panel_VarInfo.setEnabled(true);
            }
        }

        table_CloudInfo.setModel(tableModel);
    }

    private void updateStatusIndicators() {
        /*
        if (!SemvizManager.get().isInitialized()) {
            label_StatusLED.setForeground(Color.RED);
            label_StatusText.setText("No ontology loaded.");
        } else {
            label_StatusLED.setForeground(Color.GREEN);
            label_StatusText.setText(SemvizManager.get().getOntologyVersionIRI());
        }*/ // TODO
    }

}
