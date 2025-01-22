package com.joshepen.everything.ui;
import java.util.*;
import java.awt.Color;
import java.awt.Font;

import javax.sound.sampled.Line;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

import com.joshepen.everything.logic.DirectoryHandler;
import com.joshepen.everything.objects.DisplayData;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author joshu
 */
public class UI extends javax.swing.JFrame implements iUI {

    DirectoryHandler directoryHandler;
    private Color primaryColour;
    private Color secondaryColour;
    private Color tertiaryColour;
    private Color quaternaryColour;


    /**
     * Creates new form UI
     */
    public UI() {
        setColours();
        initComponents();
        changeFont(getContentPane(), "Bahnschrift");
        this.pack();
        this.setVisible(true);
    }

    public static void changeFont ( java.awt.Component component, String name )
    {
        // assumes no styling on fonts. Only way I could see this working nicely.
        component.setFont(new Font(name,Font.PLAIN, component.getFont().getSize()));
        if ( component instanceof JComponent )
        {
            for ( java.awt.Component child : ( ( JComponent ) component ).getComponents() )
            {
                changeFont ( child, name );
            }
        }
    }
    
    public void setDirectoryHandler(DirectoryHandler directoryHandler){
        this.directoryHandler = directoryHandler;
    }

    public void setColours(){
        primaryColour = new Color(24, 28, 20);
        secondaryColour = new Color(60,61,55);
        tertiaryColour = new Color(105,117,101);
        quaternaryColour = new Color(236,233,204);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        resultsTableModel = new javax.swing.table.DefaultTableModel();
        chooseDirButton = new javax.swing.JButton();
        recursiveCheckBox = new javax.swing.JCheckBox();
        sortOrderBox = new javax.swing.JComboBox<>();
        sortByBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        caseSensitiveCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(primaryColour);

        searchBar.setToolTipText("Search");
        searchBar.setName(""); // NOI18N
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });
        searchBar.addKeyListener(new java.awt.event.KeyListener() {
            public void keyPressed(java.awt.event.KeyEvent evt) {}
            public void keyReleased(java.awt.event.KeyEvent evt) {
                directoryHandler.search(searchBar.getText());
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {}
        });
        searchBar.setFont(new Font("Trebuchet MS",0,18));
        searchBar.setBackground(quaternaryColour);
        searchBar.setForeground(secondaryColour);
        searchBar.setBorder(new LineBorder(tertiaryColour));

        resultsTable.setModel(resultsTableModel);
        resultsTable.setBackground(tertiaryColour);
        resultsTable.setGridColor(secondaryColour);
        resultsTable.setForeground(quaternaryColour);
        resultsTable.getTableHeader().setBackground(secondaryColour);
        resultsTable.getTableHeader().setForeground(quaternaryColour);
        resultsTable.getTableHeader().setBorder(new LineBorder(primaryColour));
        
        jScrollPane2.setViewportView(resultsTable);

        chooseDirButton.setText("Choose Directory");
        chooseDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseDirButtonActionPerformed(evt);
            }
        });
        chooseDirButton.setForeground(quaternaryColour);
        chooseDirButton.setBackground(tertiaryColour);
        chooseDirButton.setBorder(new LineBorder(secondaryColour));

        recursiveCheckBox.setText("Recursive");
        recursiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recursiveCheckBoxActionPerformed(evt);
            }
        });
        recursiveCheckBox.setOpaque(false);
        recursiveCheckBox.setForeground(quaternaryColour);

        sortOrderBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));

        sortOrderBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortOrderBoxActionPerformed(evt);
            }
        });
        sortOrderBox.setForeground(quaternaryColour);
        sortOrderBox.setBackground(tertiaryColour);

        sortByBox.setModel(new javax.swing.DefaultComboBoxModel<>());

        sortByBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByBoxActionPerformed(evt);
            }
        });
        sortByBox.setForeground(quaternaryColour);
        sortByBox.setBackground(tertiaryColour);

        jLabel1.setText("Sort By:");
        jLabel1.setForeground(quaternaryColour);

        caseSensitiveCheckBox.setText("Case Sensitive");
        caseSensitiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caseSensitiveCheckBoxActionPerformed(evt);
            }
        });
        caseSensitiveCheckBox.setOpaque(false);
        caseSensitiveCheckBox.setForeground(quaternaryColour);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(recursiveCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caseSensitiveCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sortByBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortOrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(searchBar)
                        .addGap(18, 18, 18)
                        .addComponent(chooseDirButton)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(chooseDirButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recursiveCheckBox)
                    .addComponent(sortOrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sortByBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(caseSensitiveCheckBox))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        directoryHandler.search(searchBar.getText());
    }//GEN-LAST:event_searchBarActionPerformed

    private void chooseDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseDirButtonActionPerformed
        directoryHandler.chooseDir();
    }//GEN-LAST:event_chooseDirButtonActionPerformed

    private void recursiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recursiveCheckBoxActionPerformed
        directoryHandler.setRecursive(recursiveCheckBox.isSelected());
    }//GEN-LAST:event_recursiveCheckBoxActionPerformed

    private void caseSensitiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caseSensitiveCheckBoxActionPerformed
        directoryHandler.setCaseSensitivity(caseSensitiveCheckBox.isSelected());
    }//GEN-LAST:event_caseSensitiveCheckBoxActionPerformed

    private void sortOrderBoxActionPerformed(java.awt.event.ActionEvent evt){
        String choice = sortOrderBox.getSelectedItem().toString();
        directoryHandler.setAscending(choice.equals("Ascending"));
    }

    private void sortByBoxActionPerformed(java.awt.event.ActionEvent evt){
        if(sortByBox.getSelectedItem() != null)
            directoryHandler.setSortBy(sortByBox.getSelectedItem().toString());
    }

    /**
     * @param args the command line arguments
     */
  

     /*
      * public function to clear results list and set to new collection
      */
    public void setResults(DisplayData dd){
        if(sortByBox.getItemCount() <= 0){
            for(int i=0;i<dd.columnNames.length;i++){
                sortByBox.addItem(dd.columnNames[i]);
            }
        }
        
        resultsTableModel.setRowCount(0);
        resultsTableModel.setColumnCount(0);

        ArrayList<Vector<String>> columnVectors = new ArrayList<>();
        for(int i=0;i<dd.columnNames.length;i++){
            columnVectors.add(new Vector<>());
            resultsTableModel.addColumn(dd.columnNames[i], columnVectors.get(i));
        }
        
        for(int j=0;j<dd.size();j++){
            resultsTableModel.addRow(dd.get(j));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox caseSensitiveCheckBox;
    private javax.swing.JButton chooseDirButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox recursiveCheckBox;
    private javax.swing.JTable resultsTable;
    private javax.swing.JTextField searchBar;
    private javax.swing.JComboBox<String> sortByBox;
    private javax.swing.JComboBox<String> sortOrderBox;
    // End of variables declaration//GEN-END:variables
    private javax.swing.table.DefaultTableModel resultsTableModel;
}
