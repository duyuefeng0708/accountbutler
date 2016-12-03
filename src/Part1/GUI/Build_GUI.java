/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1.GUI;
import Part1.Currency;
import Part2.*;
import Part3.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author youhan
 */
public class Build_GUI extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    Statement st = null;
    ResultSet rs = null;
    //This is the different sub-types of Budget, can be defined by users
    private ArrayList<String> budget_types;
    private Currency currency;
    private Date date;
    private String[] name = {"a","b"};
    private double[] value = {1.1,2.2};
    
    /**
     * Creates new form Build_GUI
     */
    public Build_GUI() {
        initComponents();
        currency = new Currency();
        budget_types = new ArrayList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btCash = new javax.swing.JButton();
        btSavings = new javax.swing.JButton();
        btbudget = new javax.swing.JButton();
        btCreditCard = new javax.swing.JButton();
        ptime = new javax.swing.JPanel();
        lblmonth = new javax.swing.JLabel();
        lblday = new javax.swing.JLabel();
        lblweek = new javax.swing.JLabel();
        pother = new javax.swing.JPanel();
        pchart = new javax.swing.JPanel();
        ptoday = new javax.swing.JLabel();
        btadd = new javax.swing.JButton();
        pthismonth = new javax.swing.JLabel();
        btLogout = new javax.swing.JButton();
        creditCard = new javax.swing.JButton();
        btCheckingAccount = new javax.swing.JButton();
        btInvestment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btCash.setText("Cash");
        btCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCashActionPerformed(evt);
            }
        });

        btSavings.setText("Savings");
        btSavings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSavingsActionPerformed(evt);
            }
        });

        btbudget.setText("Budget");
        btbudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbudgetActionPerformed(evt);
            }
        });

        btCreditCard.setText("Credit Card");
        btCreditCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreditCardActionPerformed(evt);
            }
        });

        lblmonth.setText((new SimpleDateFormat("MM")).format(new Date()));

        lblday.setText((new SimpleDateFormat("dd")).format(new Date()));

        lblweek.setText((new SimpleDateFormat("EEEEE")).format(new Date()));

        javax.swing.GroupLayout ptimeLayout = new javax.swing.GroupLayout(ptime);
        ptime.setLayout(ptimeLayout);
        ptimeLayout.setHorizontalGroup(
            ptimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ptimeLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addGroup(ptimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblweek)
                    .addComponent(lblday)
                    .addComponent(lblmonth))
                .addGap(39, 39, 39))
        );
        ptimeLayout.setVerticalGroup(
            ptimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptimeLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblday, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(lblweek, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        Part3.DrawBarChart barChart = new Part3.DrawBarChart(value, name, "t");
        pchart.add(barChart);

        javax.swing.GroupLayout pchartLayout = new javax.swing.GroupLayout(pchart);
        pchart.setLayout(pchartLayout);
        pchartLayout.setHorizontalGroup(
            pchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );
        pchartLayout.setVerticalGroup(
            pchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );

        conn = MySqlConnect.ConnectDB();
        String todaySql = "Select Sum(Amount) from [Record] where UserName = ? and Income = 0 and Date = cast(GETDATE() as date) ";
        try{
            pst = conn.prepareStatement(todaySql);
            pst.setString(1,UserName.getUserName());
            rs = pst.executeQuery();
            if(rs.next()){
                if(rs.getString(1)==null){
                    ptoday.setText("Today I spent $ 0");
                }
                ptoday.setText("Today I spent $ "+ Round.round(rs.getFloat(1),2));
            }
            else{
                ptoday.setText("Today I spent $ 0");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        btadd.setText("Add");
        btadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddActionPerformed(evt);
            }
        });

        String monthSql = "Select Sum(Amount) from [Record] where UserName = '"+ UserName.getUserName() +"' and Income = 0 and MONTH(Date) = MONTH(GETDATE()) ";
        try{
            st = conn.createStatement();
            rs = st.executeQuery(monthSql);
            if(rs.next()){
                if(rs.getString(1)==null){
                    pthismonth.setText("This month I spent $ 0");
                }
                pthismonth.setText("This month I spent $ "+ Round.round(rs.getFloat(1),2));
            }
            else{
                pthismonth.setText("This month I spent $ 0");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        btLogout.setText("Log out");
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });

        creditCard.setText("Set Credit Card");
        creditCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditCardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout potherLayout = new javax.swing.GroupLayout(pother);
        pother.setLayout(potherLayout);
        potherLayout.setHorizontalGroup(
            potherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(potherLayout.createSequentialGroup()
                .addComponent(pchart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, potherLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(potherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pthismonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(ptoday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(potherLayout.createSequentialGroup()
                .addGroup(potherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(potherLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btadd, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(creditCard, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(potherLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(btLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        potherLayout.setVerticalGroup(
            potherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(potherLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pchart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ptoday, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pthismonth, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(potherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btadd, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(creditCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btCheckingAccount.setText("Checking Account");
        btCheckingAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCheckingAccountActionPerformed(evt);
            }
        });

        btInvestment.setText("Investment");
        btInvestment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInvestmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btCheckingAccount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btSavings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCreditCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btInvestment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btbudget, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(ptime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(pother, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pother, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(ptime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btbudget, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCash, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSavings, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(btCreditCard, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btCheckingAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btInvestment, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCashActionPerformed
        String btName = btCash.getText();
        AssetType.setAssetType(btName);        
        Asset asset = new Asset();
        this.dispose();
        asset.setVisible(true);     
    }//GEN-LAST:event_btCashActionPerformed

    private void btaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddActionPerformed
        AddDetails addDetails = new AddDetails();
        this.dispose();
        addDetails.setVisible(true);
    }//GEN-LAST:event_btaddActionPerformed

    private void btSavingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSavingsActionPerformed
        String btName = btSavings.getText();
        AssetType.setAssetType(btName); 
        Asset asset = new Asset();
        this.dispose();
        asset.setVisible(true); 
    }//GEN-LAST:event_btSavingsActionPerformed

    private void btbudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbudgetActionPerformed
        BudgetTime b_guitime = null;
        
        b_guitime = new BudgetTime();
        
        b_guitime.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btbudgetActionPerformed

    private void btInvestmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInvestmentActionPerformed
        String btName = btInvestment.getText();
        AssetType.setAssetType(btName);       
        Asset asset = new Asset();
        this.dispose();
        asset.setVisible(true); 
    }//GEN-LAST:event_btInvestmentActionPerformed

    private void btCreditCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreditCardActionPerformed
        String btName = btCreditCard.getText();
        AssetType.setAssetType(btName);        
        Asset asset = new Asset();
        this.dispose();
        asset.setVisible(true); 
    }//GEN-LAST:event_btCreditCardActionPerformed

    private void btCheckingAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCheckingAccountActionPerformed
        String btName = btCheckingAccount.getText();
        AssetType.setAssetType(btName);        
        Asset asset = new Asset();
        this.dispose();
        asset.setVisible(true); 
    }//GEN-LAST:event_btCheckingAccountActionPerformed

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        UserName.setUserName(null);        
        Login login = new Login();
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_btLogoutActionPerformed

    private void creditCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditCardActionPerformed
        CreditCardUI credit = new CreditCardUI();
        credit.setVisible(true);
        this.dispose();
        credit.setVisible(true);
    }//GEN-LAST:event_creditCardActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Build_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Build_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Build_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Build_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Build_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCash;
    private javax.swing.JButton btCheckingAccount;
    private javax.swing.JButton btCreditCard;
    private javax.swing.JButton btInvestment;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btSavings;
    private javax.swing.JButton btadd;
    private javax.swing.JButton btbudget;
    private javax.swing.JButton creditCard;
    private javax.swing.JLabel lblday;
    private javax.swing.JLabel lblmonth;
    private javax.swing.JLabel lblweek;
    private javax.swing.JPanel pchart;
    private javax.swing.JPanel pother;
    private javax.swing.JLabel pthismonth;
    private javax.swing.JPanel ptime;
    private javax.swing.JLabel ptoday;
    // End of variables declaration//GEN-END:variables
}
