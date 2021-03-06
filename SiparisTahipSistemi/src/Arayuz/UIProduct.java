package Arayuz;

import Veritabani.Veritabani;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UIProduct extends javax.swing.JFrame {

    Veritabani veritabani = new Veritabani();
    int id = 0;

    public UIProduct() {
        initComponents();
        setSize(320, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Product");
    }

    public UIProduct(int _id) throws SQLException {
        id = _id;
        initComponents();

        try {
            ArrayList<String> bilgiler = veritabani.GetirUrun(id);
            txtAd.setText(bilgiler.get(1));
            txtMiktar.setText(bilgiler.get(2));
            txtFiyat.setText(bilgiler.get(3));

            setTitle("Product NO : " + bilgiler.get(0));
            setSize(320, 300);
            setLocationRelativeTo(null);
            setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(UIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() {

        txtAd = new javax.swing.JTextField();
        lblAd = new javax.swing.JLabel();
        txtFiyat = new javax.swing.JTextField();
        lblFiyat = new javax.swing.JLabel();
        txtMiktar = new javax.swing.JTextField();
        lblMiktar = new javax.swing.JLabel();
        lblBaslik = new javax.swing.JLabel();
        btnUrunEkle = new javax.swing.JButton();

    
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().add(txtAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 210, -1));

        lblAd.setText("Product Name");
        getContentPane().add(lblAd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 50, 30));

        getContentPane().add(txtFiyat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 110, -1));

        lblFiyat.setText("Price");
        getContentPane().add(lblFiyat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 50, 30));

        getContentPane().add(txtMiktar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 110, -1));

        lblMiktar.setText("Quantity");
        getContentPane().add(lblMiktar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 50, 30));

        lblBaslik.setFont(new java.awt.Font("Dialog", 1, 24));

        lblBaslik.setText("ORDER ADD");
        if (id != 0) {
            lblBaslik.setText("ORDER UPDATE");
        }

        getContentPane().add(lblBaslik, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        btnUrunEkle.setFont(new java.awt.Font("Dialog", 1, 18));

        btnUrunEkle.setText("Product Add");
        if (id != 0) {
            btnUrunEkle.setText("Product Update");
        }
        btnUrunEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (id == 0) {

                    String sorgu = "Insert Into tbl_urun (ad,miktar,fiyat) Values('"
                            + txtAd.getText() + "'," + Integer.parseInt(txtMiktar.getText()) + ","
                            + Integer.parseInt(txtFiyat.getText()) + ")";

                    if (veritabani.Sorgu(sorgu)) {
                        JOptionPane.showMessageDialog(null, "Record added.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Record error.");
                    }
                } else {
                    String sorgu = "Update tbl_urun set ad='" + txtAd.getText() + "',miktar="
                            + Integer.parseInt(txtMiktar.getText()) + ",fiyat=" + Integer.parseInt(txtFiyat.getText()) + " where id=" + id;
                    if (veritabani.Sorgu(sorgu)) {
                        JOptionPane.showMessageDialog(null, "Record updated.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Update error.");
                    }
                }
            }
        });
        getContentPane().add(btnUrunEkle, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 205, 210, 45));

        pack();
        setLocationRelativeTo(null);
    }


    private javax.swing.JButton btnUrunEkle;
    private javax.swing.JLabel lblAd;
    private javax.swing.JLabel lblBaslik;
    private javax.swing.JLabel lblFiyat;
    private javax.swing.JLabel lblMiktar;
    private javax.swing.JTextField txtAd;
    private javax.swing.JTextField txtFiyat;
    private javax.swing.JTextField txtMiktar;

}
