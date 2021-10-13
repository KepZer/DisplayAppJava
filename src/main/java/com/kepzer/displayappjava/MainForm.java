
package com.kepzer.displayappjava;

import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.Native;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.LinkedHashSet;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainForm extends javax.swing.JFrame {

    public WinUserNative winUser;
    private PixelAsyncDetector pixelAsyncDetector;
    
    
    public MainForm() {
        winUser = (WinUserNative) Native.load(WinUserNative.class);
        initComponents();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("images (png, jpg, jpeg)", "png", "jpeg","jpg"));
        pixelAsyncDetector = new PixelAsyncDetector(jColorCodeLabel, winUser);
        Thread thread = new Thread(pixelAsyncDetector);
        thread.start();
        setComboxData();
    }
    public Dimension screenSize;
    public void setComboxData(){
        GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment()
           .getScreenDevices();
        GraphicsDevice dev = devices[0];
        DisplayMode[] modes = dev.getDisplayModes();
        LinkedHashSet<Dimension> dimensionSet = new LinkedHashSet<Dimension>();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        for (int j = 0; j < modes.length; j++) {
            DisplayMode m = modes[j];
            dimensionSet.add(new Dimension(m.getWidth(), m.getHeight()));
        }
        int index = 0;
        for(Dimension dimension : dimensionSet){
            jResComboBox.addItem((int)dimension.getWidth()+"x"+(int)dimension.getHeight());
            if(screenSize.equals(dimension)) jResComboBox.setSelectedIndex(index);
            index++;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        jImageChooserLabel = new javax.swing.JLabel();
        jImageChooserButton = new javax.swing.JButton();
        jColorDetectorLabel = new javax.swing.JLabel();
        jColorCodeLabel = new javax.swing.JLabel();
        jResComboBox = new javax.swing.JComboBox<>();
        jResLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jImageChooserLabel.setText("Выбрать фон рабочего стола");
        jImageChooserLabel.setToolTipText("");

        jImageChooserButton.setText("Выбрать");
        jImageChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jImageChooserButtonActionPerformed(evt);
            }
        });

        jColorDetectorLabel.setText("Текущий цвет:");
        jColorDetectorLabel.setToolTipText("");

        jColorCodeLabel.setText("█████████");

        jResLabel.setText("Разрешение экрана");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jImageChooserLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jColorDetectorLabel)
                                .addComponent(jColorCodeLabel))
                            .addComponent(jImageChooserButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jResLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jResComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jImageChooserLabel)
                    .addComponent(jResLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jImageChooserButton)
                    .addComponent(jResComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jColorDetectorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jColorCodeLabel)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jImageChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jImageChooserButtonActionPerformed
        // Set DesktopImage
        int val = jFileChooser.showOpenDialog(this);
        if (val == jFileChooser.APPROVE_OPTION){
            winUser.SystemParametersInfoA(0x0014,0, jFileChooser.getSelectedFile().getPath(), WinUser.SWP_NOSENDCHANGING);
        }
    }//GEN-LAST:event_jImageChooserButtonActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jColorCodeLabel;
    private javax.swing.JLabel jColorDetectorLabel;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JButton jImageChooserButton;
    private javax.swing.JLabel jImageChooserLabel;
    private javax.swing.JComboBox<String> jResComboBox;
    private javax.swing.JLabel jResLabel;
    // End of variables declaration//GEN-END:variables
}
