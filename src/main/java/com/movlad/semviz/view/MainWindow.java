/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movlad.semviz.view;

import com.movlad.semviz.application.Configuration;
import com.movlad.semviz.application.ViewItem;
import com.movlad.semviz.application.Viewer;
import com.movlad.semviz.core.graphics.engine.BoxEdgesGeometry;
import com.movlad.semviz.core.graphics.engine.Object3d;
import com.movlad.semviz.core.math.geometry.BoundingBox;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.joml.Vector3f;

public class MainWindow extends javax.swing.JFrame implements PropertyChangeListener {

    private Viewer viewer;
    private final List<ViewItem> viewItems;

    private final DefaultListModel listIndividualsModel;

    private Configuration controller;

    private boolean isInitialized = false;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBox_GeometrySelection;
    private javax.swing.JLabel Label_GeometrySelection;
    private java.awt.Label Label_Individuals;
    private javax.swing.JLabel Label_StatusLED;
    private javax.swing.JLabel Label_StatusText;
    private javax.swing.JLabel Label_VarInfo;
    private javax.swing.JList<String> List_Individuals;
    private javax.swing.JPanel Panel_Control;
    private javax.swing.JPanel Panel_GLCanvas;
    private javax.swing.JPanel Panel_GeometrySelection;
    private javax.swing.JPanel Panel_Individuals;
    private javax.swing.JPanel Panel_Status;
    private javax.swing.JPanel Panel_VarInfo;
    private javax.swing.JScrollPane Scroll_Individuals;
    private javax.swing.JScrollPane Scroll_VarInfo;
    private javax.swing.JTable Table_VarTable;
    private javax.swing.JTextField TextField_Command;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItem_Open;
    private javax.swing.JMenuItem menuItem_Quit;
    private javax.swing.JMenu menu_File;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();

        controller = new Configuration();

        controller.register(this);

        viewer = new Viewer(Panel_GLCanvas.getWidth(), Panel_GLCanvas.getHeight());

        Panel_GLCanvas.add(viewer.getGlCanvas());
        Panel_GLCanvas.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                viewer.getGlCanvas().setSize(Panel_GLCanvas.getSize());
            }

        });

        viewer.start();

        ComboBox_GeometrySelection.setEnabled(false);
        ComboBox_GeometrySelection.removeAllItems();

        ComboBox_GeometrySelection.addItem("High Resolution");
        ComboBox_GeometrySelection.addItem("High Resolution (Normals)");
        ComboBox_GeometrySelection.addItem("Convex Hull");

        TextField_Command.setEnabled(false);

        viewItems = new ArrayList<>();
        listIndividualsModel = new DefaultListModel();

        List_Individuals.setModel(listIndividualsModel);

        update();

        isInitialized = true;
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

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            JFrame mainWindow = new MainWindow();

            mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
            mainWindow.setVisible(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextField_Command = new javax.swing.JTextField();
        Panel_GLCanvas = new javax.swing.JPanel();
        Panel_Control = new javax.swing.JPanel();
        Panel_Status = new javax.swing.JPanel();
        Label_StatusLED = new javax.swing.JLabel();
        Label_StatusText = new javax.swing.JLabel();
        Panel_Individuals = new javax.swing.JPanel();
        Label_Individuals = new java.awt.Label();
        Scroll_Individuals = new javax.swing.JScrollPane();
        List_Individuals = new javax.swing.JList<>();
        Panel_VarInfo = new javax.swing.JPanel();
        Label_VarInfo = new javax.swing.JLabel();
        Scroll_VarInfo = new javax.swing.JScrollPane();
        Table_VarTable = new javax.swing.JTable();
        Panel_GeometrySelection = new javax.swing.JPanel();
        Label_GeometrySelection = new javax.swing.JLabel();
        ComboBox_GeometrySelection = new javax.swing.JComboBox<>();
        menuBar = new javax.swing.JMenuBar();
        menu_File = new javax.swing.JMenu();
        menuItem_Open = new javax.swing.JMenuItem();
        menuItem_Quit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextField_Command.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        TextField_Command.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextField_CommandKeyPressed(evt);
            }
        });

        Panel_GLCanvas.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Panel_GLCanvasLayout = new javax.swing.GroupLayout(Panel_GLCanvas);
        Panel_GLCanvas.setLayout(Panel_GLCanvasLayout);
        Panel_GLCanvasLayout.setHorizontalGroup(
            Panel_GLCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
        );
        Panel_GLCanvasLayout.setVerticalGroup(
            Panel_GLCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Panel_Control.setBackground(new java.awt.Color(204, 204, 204));

        Label_StatusLED.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        Label_StatusLED.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_StatusLED.setText("�");

        Label_StatusText.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        Label_StatusText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Label_StatusText.setText("Ontology Model Loaded");

        Panel_Individuals.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Label_Individuals.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Label_Individuals.setText("Cloud List");

        List_Individuals.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        List_Individuals.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        List_Individuals.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                List_IndividualsValueChanged(evt);
            }
        });
        Scroll_Individuals.setViewportView(List_Individuals);

        javax.swing.GroupLayout Panel_IndividualsLayout = new javax.swing.GroupLayout(Panel_Individuals);
        Panel_Individuals.setLayout(Panel_IndividualsLayout);
        Panel_IndividualsLayout.setHorizontalGroup(
            Panel_IndividualsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_IndividualsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(Panel_IndividualsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_IndividualsLayout.createSequentialGroup()
                        .addComponent(Label_Individuals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Scroll_Individuals))
                .addContainerGap())
        );
        Panel_IndividualsLayout.setVerticalGroup(
            Panel_IndividualsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_IndividualsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_Individuals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Scroll_Individuals, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        Label_VarInfo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Label_VarInfo.setText("Queried Info");

        Table_VarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Scroll_VarInfo.setViewportView(Table_VarTable);

        javax.swing.GroupLayout Panel_VarInfoLayout = new javax.swing.GroupLayout(Panel_VarInfo);
        Panel_VarInfo.setLayout(Panel_VarInfoLayout);
        Panel_VarInfoLayout.setHorizontalGroup(
            Panel_VarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_VarInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_VarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Scroll_VarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(Panel_VarInfoLayout.createSequentialGroup()
                        .addComponent(Label_VarInfo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Panel_VarInfoLayout.setVerticalGroup(
            Panel_VarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_VarInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_VarInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Scroll_VarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Label_GeometrySelection.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Label_GeometrySelection.setText("View");

        ComboBox_GeometrySelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBox_GeometrySelection.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBox_GeometrySelectionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout Panel_GeometrySelectionLayout = new javax.swing.GroupLayout(Panel_GeometrySelection);
        Panel_GeometrySelection.setLayout(Panel_GeometrySelectionLayout);
        Panel_GeometrySelectionLayout.setHorizontalGroup(
            Panel_GeometrySelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_GeometrySelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_GeometrySelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBox_GeometrySelection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Panel_GeometrySelectionLayout.createSequentialGroup()
                        .addComponent(Label_GeometrySelection)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Panel_GeometrySelectionLayout.setVerticalGroup(
            Panel_GeometrySelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_GeometrySelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_GeometrySelection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBox_GeometrySelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_StatusLayout = new javax.swing.GroupLayout(Panel_Status);
        Panel_Status.setLayout(Panel_StatusLayout);
        Panel_StatusLayout.setHorizontalGroup(
            Panel_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_StatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_StatusLED, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_StatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .addComponent(Panel_Individuals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Panel_VarInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Panel_GeometrySelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Panel_StatusLayout.setVerticalGroup(
            Panel_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_StatusLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(Panel_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Label_StatusLED, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label_StatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_Individuals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_VarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_GeometrySelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Panel_ControlLayout = new javax.swing.GroupLayout(Panel_Control);
        Panel_Control.setLayout(Panel_ControlLayout);
        Panel_ControlLayout.setHorizontalGroup(
            Panel_ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Panel_ControlLayout.setVerticalGroup(
            Panel_ControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(TextField_Command)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_Control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_GLCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_GLCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panel_Control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextField_Command, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItem_OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_OpenActionPerformed
        final JFileChooser fc = new JFileChooser();

        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getAbsolutePath();

            controller.load(path);
            viewer.clearScene();
        }
    }//GEN-LAST:event_menuItem_OpenActionPerformed

    private void menuItem_QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_QuitActionPerformed
        viewer.stop();
        System.exit(0);
    }//GEN-LAST:event_menuItem_QuitActionPerformed

    private void TextField_CommandKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextField_CommandKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                controller.queryExec(TextField_Command.getText());

                break;
            case KeyEvent.VK_UP:
                controller.commandBackward();

                break;
            case KeyEvent.VK_DOWN:
                controller.commandForward();

                break;
        }
    }//GEN-LAST:event_TextField_CommandKeyPressed

    private void List_IndividualsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_List_IndividualsValueChanged
        int index = List_Individuals.getSelectedIndex();

        if (index != -1) {
            int selection = viewItems.get(index).getSelection();

            onIndividualSelection();

            ComboBox_GeometrySelection.setEnabled(true);
            ComboBox_GeometrySelection.setSelectedIndex(selection);
        } else {
            ComboBox_GeometrySelection.setEnabled(false);
        }
    }//GEN-LAST:event_List_IndividualsValueChanged

    private void ComboBox_GeometrySelectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBox_GeometrySelectionItemStateChanged
        int selection = ComboBox_GeometrySelection.getSelectedIndex();

        if (isInitialized) {
            viewer.replaceObject(List_Individuals.getSelectedIndex(),
                    viewItems.get(List_Individuals.getSelectedIndex()).getGeometry(selection));
        }
    }//GEN-LAST:event_ComboBox_GeometrySelectionItemStateChanged

    private void update() {
        updateStatus();
        updateCommandLine();
        updateIndividualsList();
        onIndividualSelection();
        updateGeometrySelectionComboBox();
    }

    private void updateStatus() {
        if (!controller.isInitialized()) {
            Label_StatusLED.setForeground(Color.RED);
            Label_StatusText.setText("Offline.");
        } else {
            Label_StatusLED.setForeground(Color.GREEN);
            Label_StatusText.setText("Online.");
        }
    }

    private void updateIndividualsList() {
        listIndividualsModel.removeAllElements();

        if (controller.getQueryResults() != null
                && !controller.getQueryResults().isEmpty()) {
            controller.getQueryResults().forEach(result -> {
                listIndividualsModel.addElement(result.getIndividual().getLocalName());
            });
        }
    }

    private void updateGeometrySelectionComboBox() {
        int index = List_Individuals.getSelectedIndex();

        if (index != -1) {
            ComboBox_GeometrySelection.setEnabled(true);
        } else {
            ComboBox_GeometrySelection.setSelectedIndex(0);
            ComboBox_GeometrySelection.setEnabled(false);
        }
    }

    private void onIndividualSelection() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Variable");
        model.addColumn("Value");

        Panel_VarInfo.setEnabled(false);

        int index = List_Individuals.getSelectedIndex();

        viewer.removeObject("selection");

        if (index != -1) {
            controller.getQueryResults().get(index).getKeys().forEach(key -> {
                String[] row = new String[2];

                row[0] = key;
                row[1] = controller.getQueryResults().get(index).getAttribute(key);

                model.addRow(row);
            });

            Object3d box = new BoxEdgesGeometry(new BoundingBox(viewItems.get(index).getCloud()),
                    new Vector3f(255.0f, 255.0f, 0.0f));

            box.setId("selection");

            viewer.addObject(box);
        }

        Table_VarTable.setModel(model);
    }

    private void updateCommandLine() {
        if (!controller.isInitialized()) {
            TextField_Command.setText("");
            TextField_Command.setEnabled(false);
        } else {
            TextField_Command.setEnabled(true);
            TextField_Command.setText(controller.getCurrentCommand());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("error")) {
            JOptionPane.showMessageDialog(this, evt.getNewValue(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (evt.getPropertyName().equals("load")) {
            JOptionPane.showMessageDialog(this, "Semviz data directory successfully loaded.",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        if (evt.getPropertyName().equals("queryExec")) {
            viewer.clearScene();
            viewItems.clear();

            controller.getSuperCloud().forEach(cluster -> {
                viewItems.add(new ViewItem(cluster));
            });

            viewer.fromViewItems(viewItems);
        }

        update();
    }

}
