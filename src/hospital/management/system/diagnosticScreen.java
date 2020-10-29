/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.system;

/**
 *
 * @author Kaviraj
 */
public class diagnosticScreen extends javax.swing.JFrame {

    /**
     * Creates new form diagnosticScreen
     */

    public save_diagnostic sd1;

    public diagnosticScreen(String nameval, String snameval, String reasonval, String dateval, String checkId) {
        initComponents();
        
        //this will set the frame to maximised size
        this.setExtendedState(this.getExtendedState() | myPatients.MAXIMIZED_BOTH);

        namerpl.setText(nameval);
        surnamerpl.setText(snameval);
        reasonrpl.setText(reasonval);
        daterpl.setText(dateval);
 
        save_diagnostic sd = new save_diagnostic(txtAreaDiagnosis,specialtrtCombo,checkId);
        sd1 = sd;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        diagnosisMenu = new javax.swing.JPanel();
        diagnosisTitle = new javax.swing.JLabel();
        iconDiagnosis = new javax.swing.JLabel();
        diagnosisMain = new javax.swing.JPanel();
        patientDetDiag = new javax.swing.JPanel();
        namelbl = new javax.swing.JLabel();
        namerpl = new javax.swing.JLabel();
        surnamelbl = new javax.swing.JLabel();
        surnamerpl = new javax.swing.JLabel();
        reasonlbl = new javax.swing.JLabel();
        reasonrpl = new javax.swing.JLabel();
        datelbl = new javax.swing.JLabel();
        daterpl = new javax.swing.JLabel();
        detlbl = new javax.swing.JLabel();
        diagnosisArea = new javax.swing.JPanel();
        writeTitle = new javax.swing.JLabel();
        txtPane = new javax.swing.JScrollPane();
        txtAreaDiagnosis = new javax.swing.JTextArea();
        saveDiagnosis = new javax.swing.JButton();
        specialtrtCombo = new javax.swing.JComboBox<>();
        specialtrtLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        diagnosisMenu.setBackground(new java.awt.Color(0, 51, 153));
        diagnosisMenu.setForeground(new java.awt.Color(0, 51, 153));
        diagnosisMenu.setLayout(new java.awt.BorderLayout());

        diagnosisTitle.setBackground(new java.awt.Color(0, 51, 153));
        diagnosisTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        diagnosisTitle.setForeground(new java.awt.Color(255, 255, 255));
        diagnosisTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagnosisTitle.setText("Diagnosis");
        diagnosisTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diagnosisTitle.setPreferredSize(new java.awt.Dimension(34, 72));
        diagnosisMenu.add(diagnosisTitle, java.awt.BorderLayout.CENTER);

        iconDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/cross.jpg"))); // NOI18N
        diagnosisMenu.add(iconDiagnosis, java.awt.BorderLayout.LINE_END);

        getContentPane().add(diagnosisMenu, java.awt.BorderLayout.PAGE_START);

        patientDetDiag.setPreferredSize(new java.awt.Dimension(450, 536));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 35, 0, 35, 0};
        jPanel3Layout.rowHeights = new int[] {0, 35, 0, 35, 0, 35, 0, 35, 0, 35, 0, 35, 0, 35, 0};
        patientDetDiag.setLayout(jPanel3Layout);

        namelbl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        namelbl.setText("Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(namelbl, gridBagConstraints);

        namerpl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        namerpl.setText("name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(namerpl, gridBagConstraints);

        surnamelbl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        surnamelbl.setText("Surname:");
        surnamelbl.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(surnamelbl, gridBagConstraints);

        surnamerpl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        surnamerpl.setText("Surname");
        surnamerpl.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(surnamerpl, gridBagConstraints);

        reasonlbl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        reasonlbl.setText("Reason:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(reasonlbl, gridBagConstraints);

        reasonrpl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        reasonrpl.setText("Reason");
        reasonrpl.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(reasonrpl, gridBagConstraints);

        datelbl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        datelbl.setText("Date:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(datelbl, gridBagConstraints);

        daterpl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        daterpl.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        patientDetDiag.add(daterpl, gridBagConstraints);

        detlbl.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        detlbl.setText("Details");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        patientDetDiag.add(detlbl, gridBagConstraints);

        diagnosisArea.setLayout(new java.awt.GridBagLayout());

        writeTitle.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        writeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        writeTitle.setText("Write Diagnosis");
        writeTitle.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        diagnosisArea.add(writeTitle, gridBagConstraints);

        txtAreaDiagnosis.setBackground(new java.awt.Color(102, 153, 255));
        txtAreaDiagnosis.setColumns(20);
        txtAreaDiagnosis.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtAreaDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        txtAreaDiagnosis.setRows(5);
        txtPane.setViewportView(txtAreaDiagnosis);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 141;
        gridBagConstraints.ipady = 56;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 50, 50);
        diagnosisArea.add(txtPane, gridBagConstraints);

        saveDiagnosis.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        saveDiagnosis.setText("Save");
        saveDiagnosis.setToolTipText("");
        saveDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDiagnosisActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        diagnosisArea.add(saveDiagnosis, gridBagConstraints);

        specialtrtCombo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        specialtrtCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "ENT", "ICU", "X-Ray", "MRI" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 225, 50, 0);
        diagnosisArea.add(specialtrtCombo, gridBagConstraints);

        specialtrtLbl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        specialtrtLbl.setText("Special Treatments");
        specialtrtLbl.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 50, 0);
        diagnosisArea.add(specialtrtLbl, gridBagConstraints);

        javax.swing.GroupLayout diagnosisMainLayout = new javax.swing.GroupLayout(diagnosisMain);
        diagnosisMain.setLayout(diagnosisMainLayout);
        diagnosisMainLayout.setHorizontalGroup(
            diagnosisMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagnosisMainLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(patientDetDiag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(diagnosisArea, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
                .addContainerGap())
        );
        diagnosisMainLayout.setVerticalGroup(
            diagnosisMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, diagnosisMainLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(diagnosisMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(patientDetDiag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diagnosisArea, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );

        getContentPane().add(diagnosisMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDiagnosisActionPerformed
        // TODO add your handling code here:
     
        if(sd1.checkDiag() == false){
            return;
        }  
        sd1.updateChkUp();
        sd1.addTreatment();
        this.dispose();
        
    }//GEN-LAST:event_saveDiagnosisActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel datelbl;
    private javax.swing.JLabel daterpl;
    private javax.swing.JLabel detlbl;
    private javax.swing.JPanel diagnosisArea;
    private javax.swing.JPanel diagnosisMain;
    private javax.swing.JPanel diagnosisMenu;
    private javax.swing.JLabel diagnosisTitle;
    private javax.swing.JLabel iconDiagnosis;
    private javax.swing.JLabel namelbl;
    private javax.swing.JLabel namerpl;
    private javax.swing.JPanel patientDetDiag;
    private javax.swing.JLabel reasonlbl;
    private javax.swing.JLabel reasonrpl;
    private javax.swing.JButton saveDiagnosis;
    private javax.swing.JComboBox<String> specialtrtCombo;
    private javax.swing.JLabel specialtrtLbl;
    private javax.swing.JLabel surnamelbl;
    private javax.swing.JLabel surnamerpl;
    private javax.swing.JTextArea txtAreaDiagnosis;
    private javax.swing.JScrollPane txtPane;
    private javax.swing.JLabel writeTitle;
    // End of variables declaration//GEN-END:variables
}
