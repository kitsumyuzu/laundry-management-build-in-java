/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laundry.manager;

import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.text.MessageFormat;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import jnafilechooser.api.JnaFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Baskoro Adi
 */
public class CucianMasuk extends javax.swing.JFrame {

    /**
     * Creates new form SetorLaundry
     */
        Connection conn = Koneksi.getConnection();
        private DefaultTableModel model;
    public CucianMasuk() {
        initComponents();
        this.setTitle("Input Pesanan");
        model = new DefaultTableModel();
        jTable_CucianMasuk.setModel(model);
        model.addColumn("ID Pelanggan");
        model.addColumn("ID Cucian");
        model.addColumn("Nama");
        model.addColumn("No HP");
        model.addColumn("Alamat");
        model.addColumn("JK");
        model.addColumn("Berat (kg)");
        model.addColumn("Tanggal Masuk");
        model.addColumn("Harga");
        model.addColumn("Status Bayar");
        model.addColumn("Status Transaksi");
        model.addColumn("Tanggal Keluar");
        label_namaAdmin.setText(String.valueOf(adm.nameAdm));
        this.setLocationRelativeTo(null);
        tanggalNow();
        tampil();
    }
    
    private void exportToExcel(JTable jTable_CucianMasuk, String filePath) {
        
        try {

            TableModel model = jTable_CucianMasuk.getModel();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            //  Membuat header kolom

                Row headerRow = sheet.createRow(0);

                for (int i = 0; i < model.getColumnCount(); i++) {

                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(model.getColumnName(i));

                }

            // Membuat data baris

                for (int i = 0; i < model.getRowCount(); i++) {

                    Row row = sheet.createRow(i + 1);

                    for (int j = 0; j < model.getColumnCount(); j++) {

                        Cell cell = row.createCell(j);
                        cell.setCellValue(model.getValueAt(i, j).toString());

                    }

                }

                // Menyimpan file Excel

                FileOutputStream fileOut = new FileOutputStream(new File(filePath));
                workbook.write(fileOut);
                fileOut.close();

                JOptionPane.showMessageDialog(null, "Data berhasil di ekspor ke dalam file Excel");

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengekspor ke Excel: " + e.getMessage());

        }
        
    }
    
    private void tanggalNow(){
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date_masuk.setDateFormatString("dd/MM/yyyy");
        date_masuk.setDate(date);
    }
    
    private void tampil(){
            try{
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                String query = "SELECT * FROM cucian JOIN pelanggan WHERE cucian.id_pelanggan = pelanggan.id_pelanggan";
                Statement statement = (Statement) conn.createStatement();
                ResultSet result = statement.executeQuery(query);
                if (!result.next()) {
                    JOptionPane.showMessageDialog(this,"Data Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
                }else{
                    while(result.next()){
                        Object[] o = new Object[14];
                        o[0] = result.getString("id_pelanggan");
                        o[1] = result.getString("id_cucian");
                        o[2] = result.getString("nama_pelanggan");
                        o[3] = result.getString("nomer_hp");
                        o[4] = result.getString("alamat_pelanggan");
                        o[5] = result.getString("jenis_kelamin");
                        o[6] = result.getInt("berat");
                        o[7] = result.getString("tanggal_masuk");
                        o[8] = result.getInt("harga");
                        o[9] = result.getString("statuspembayaran");
                        o[10] = result.getString("statustransaksi");
                        o[11] = result.getString("tanggal_keluar");
                        
                        model.addRow(o);
                    }
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(this, ex);
            }
    }
    
    private void reset(){
        txt_Nama.setText("");
        txt_Alamat.setText("");
        txt_NoHP.setText("");
        jk.clearSelection();
        txt_Berat.setText("");
        txt_Harga.setText("");
        ComboStatusBayar.setSelectedItem("Belum Lunas");
        ComboStatusTransaksi.setSelectedItem("On Process");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jk = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txt_Nama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_Alamat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_NoHP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton_Laki = new javax.swing.JRadioButton();
        jRadioButton_Perempuan = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txt_Berat = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_Harga = new javax.swing.JTextField();
        jButton_Simpan = new javax.swing.JButton();
        jButton_Hitung = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jButton_Update = new javax.swing.JButton();
        date_masuk = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButton_Back = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        ComboStatusBayar = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        ComboStatusTransaksi = new javax.swing.JComboBox<String>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_CucianMasuk = new javax.swing.JTable();
        label_namaAdmin = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_pdf = new javax.swing.JButton();
        btn_excel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(60, 60, 60));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Data Pelanggan");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Alamat");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nomer HP");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jenis Kelamin");

        jk.add(jRadioButton_Laki);
        jRadioButton_Laki.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Laki.setText("Laki Laki");

        jk.add(jRadioButton_Perempuan);
        jRadioButton_Perempuan.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Perempuan.setText("Perempuan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(txt_Nama)
                        .addComponent(jLabel4)
                        .addComponent(txt_Alamat)
                        .addComponent(jLabel6)
                        .addComponent(txt_NoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jRadioButton_Laki)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jRadioButton_Perempuan))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Laki)
                    .addComponent(jRadioButton_Perempuan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(60, 60, 60));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Data Cucian");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Berat (Kg)");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tanggal Masuk");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Total Harga (Rp)");

        txt_Harga.setEditable(false);

        jButton_Simpan.setText("Tambah");
        jButton_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SimpanActionPerformed(evt);
            }
        });

        jButton_Hitung.setText("Hitung");
        jButton_Hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HitungActionPerformed(evt);
            }
        });

        jButton_Delete.setText("Delete");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        jButton_Update.setText("Update");
        jButton_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_Simpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Update))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(date_masuk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Berat, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Harga, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Hitung)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Berat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(9, 9, 9)
                .addComponent(date_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Hitung))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Simpan)
                    .addComponent(jButton_Delete)
                    .addComponent(jButton_Update))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(60, 60, 60));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Controls");

        jButton_Back.setText("Back To Homepage");
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Status Pembayaran");

        ComboStatusBayar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum Lunas", "Lunas" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Status Transaksi");

        ComboStatusTransaksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "On Process", "Selesai" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton_Back)
                    .addComponent(jLabel8)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ComboStatusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel9)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(ComboStatusTransaksi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboStatusBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboStatusTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Back)
                .addContainerGap())
        );

        jTable_CucianMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_CucianMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CucianMasukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_CucianMasuk);

        label_namaAdmin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        label_namaAdmin.setForeground(new java.awt.Color(0, 0, 0));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama Admin:");

        jPanel3.setBackground(new java.awt.Color(0, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cucian Masuk ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        btn_pdf.setText("PDF");
        btn_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pdfActionPerformed(evt);
            }
        });

        btn_excel.setText("Excel");
        btn_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label_namaAdmin))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btn_pdf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_excel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_namaAdmin)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pdf)
                    .addComponent(btn_excel))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jButton_HitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HitungActionPerformed
        // TODO add your handling code here:
        try{
            String str = txt_Berat.getText();
            if (str.length()==0) {
                JOptionPane.showMessageDialog(this,"Isi berat terlebih dahulu!","Notification",JOptionPane.WARNING_MESSAGE);
            }else{
                String kg, cost;
                int berat, harga;
                kg = txt_Berat.getText();
                berat = Integer.parseInt(kg);
                harga = berat*5000;

                cost = String.valueOf(harga);
                txt_Harga.setText(cost);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Inputan harus angka!","Notification",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton_HitungActionPerformed

    private void jButton_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SimpanActionPerformed
    if (txt_Nama.getText().equals("")||txt_Alamat.getText().equals("")||txt_NoHP.getText().equals("")||txt_Harga.getText().equals("")||txt_Berat.getText().equals("")||jk.getSelection()==null){
        JOptionPane.showMessageDialog(this, "Isi Semua Data!","Notification",JOptionPane.WARNING_MESSAGE);
    }else if (ComboStatusBayar.getSelectedItem()=="Belum Lunas" && ComboStatusTransaksi.getSelectedItem()=="Selesai") {
        JOptionPane.showMessageDialog(this,"Tekan tombol update untuk ubah status lunas!","Notification",JOptionPane.WARNING_MESSAGE);    
    }else{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date_masuk.setDateFormatString("dd/MM/yyyy");
        String strdate = dateFormat.format(date_masuk.getDate());
        String nama = txt_Nama.getText();
        String alamat = txt_Alamat.getText();
        String noHp = txt_NoHP.getText();
        String jenisKelamin = null;
        String weight = txt_Berat.getText();
        int berat = Integer.parseInt(weight);
        String cost = txt_Harga.getText();
        int harga = Integer.parseInt(cost);
        if (jRadioButton_Laki.isSelected()) {
            jenisKelamin = "Laki-Laki";
        } else if (jRadioButton_Perempuan.isSelected()) {
            jenisKelamin = "Perempuan";
        }
        String statusbayar = ComboStatusBayar.getSelectedItem().toString();
        String statustransaksi = ComboStatusTransaksi.getSelectedItem().toString();
            try {
                String queryId = "SELECT id_pelanggan FROM pelanggan ORDER BY id_pelanggan DESC LIMIT 1";
                try {
                    Statement statement = conn.createStatement();
                    ResultSet resultId = statement.executeQuery(queryId);
                    while (resultId.next()) {
                        int newId = resultId.getInt("id_pelanggan");
                        adm.fixId = newId + 1;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "gagal getId", "Failed", JOptionPane.ERROR_MESSAGE);
                }
                String query = "INSERT INTO pelanggan(nama_pelanggan, alamat_pelanggan, nomer_hp, jenis_kelamin) VALUES(?,?,?,?)";
                PreparedStatement statement = (PreparedStatement) conn.prepareStatement(query);
                statement.setString(1, nama);
                statement.setString(2, alamat);
                statement.setString(3, noHp);
                statement.setString(4, jenisKelamin);
                statement.executeUpdate();

                String query2 = "INSERT INTO cucian(berat, tanggal_masuk, harga, statuspembayaran, statustransaksi, id_pelanggan, id_admin) VALUES(?,?,?,?,?,?,?)";
                PreparedStatement statement2 = (PreparedStatement) conn.prepareStatement(query2);
                statement2.setInt(1, berat);
                statement2.setString(2, strdate);
                statement2.setInt(3, harga);
                statement2.setString(4, statusbayar);
                statement2.setString(5, statustransaksi);
                statement2.setInt(6, adm.fixId);
                statement2.setInt(7, adm.idAdmin);
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(this, "Berhasil Menambahkan!", "Success", JOptionPane.PLAIN_MESSAGE);
                tampil();
                reset();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal Menambahkan! " + ex, "Failed", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_SimpanActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        // TODO add your handling code here:
        if (jTable_CucianMasuk.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,"Tabel Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
        }else if(jTable_CucianMasuk.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this,"Pilih Data yang Akan Dihapus!","Notification",JOptionPane.WARNING_MESSAGE);
        }else{
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this,"Yakin Ingin Menghapus?");
            if (dialogResult == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM cucian WHERE id_cucian=?";
                try{
                    String id_cuci = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),1).toString();
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, id_cuci);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(this,"Data Berhasil Dihapus!","Success",JOptionPane.PLAIN_MESSAGE);
                    tampil();
                    reset();
                    
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(this,"Gagal Menghapus Data! "+e,"Failed",JOptionPane.ERROR_MESSAGE);    
                } 
            }
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jTable_CucianMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CucianMasukMouseClicked
        // TODO add your handling code here:
        txt_Nama.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),2).toString());
        txt_NoHP.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),3).toString());
        txt_Alamat.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),4).toString());
        String jk = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),5).toString();
        if (jk.equalsIgnoreCase("laki-laki")) {
            jRadioButton_Laki.setSelected(true);
        }else if (jk.equalsIgnoreCase("perempuan")) {
            jRadioButton_Perempuan.setSelected(true);
        }
        txt_Berat.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),6).toString());
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date createDate = null;
            createDate = df.parse(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),7).toString());
            date_masuk.setDate(createDate);
        }catch(Exception e){
            System.out.println(e);
        }
        txt_Harga.setText(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),8).toString());
        ComboStatusBayar.setSelectedItem(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),9));
        ComboStatusTransaksi.setSelectedItem(model.getValueAt(jTable_CucianMasuk.getSelectedRow(),10));
    }//GEN-LAST:event_jTable_CucianMasukMouseClicked

    private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateActionPerformed
        // TODO add your handling code here:
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date_masuk.setDateFormatString("dd/MM/yyyy");
        
        if (jTable_CucianMasuk.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,"Tabel Kosong!","Notification",JOptionPane.WARNING_MESSAGE);
        }else if(jTable_CucianMasuk.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this,"Pilih Data yang Akan Diubah!","Notification",JOptionPane.WARNING_MESSAGE);
        }else if(ComboStatusBayar.getSelectedItem()=="Belum Lunas" && ComboStatusTransaksi.getSelectedItem()=="Selesai"){
            JOptionPane.showMessageDialog(this,"Harap lunasi terlebih dahulu!","Notification",JOptionPane.WARNING_MESSAGE);
        }else if (ComboStatusBayar.getSelectedItem()=="Lunas" && ComboStatusTransaksi.getSelectedItem()=="Selesai") {
            String id_cuci = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),1).toString();
            String id_pelanggan = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),0).toString();
            String nama = txt_Nama.getText();
            String strdate = dateFormat.format(date_masuk.getDate());
            String alamat = txt_Alamat.getText();
            String noHp = txt_NoHP.getText();
            String jenisKelamin = null;
            String weight = txt_Berat.getText();
            int berat = Integer.parseInt(weight);
            String cost = txt_Harga.getText();
            int harga = Integer.parseInt(cost);
            if (jRadioButton_Laki.isSelected()) {
                jenisKelamin = "Laki-Laki";
            }else if (jRadioButton_Perempuan.isSelected()) {
                jenisKelamin = "Perempuan";
            }
            String statusbayar = ComboStatusBayar.getSelectedItem().toString();
            String statustransaksi = ComboStatusTransaksi.getSelectedItem().toString();
            Date date = new Date();
            String tanggalNow = dateFormat.format(date);
            String sql = "UPDATE pelanggan SET nama_pelanggan=?,alamat_pelanggan=?,nomer_hp=?,jenis_kelamin=? WHERE id_pelanggan =?";
            String sql2 = "UPDATE cucian SET berat=?,tanggal_masuk=?,harga=?,statuspembayaran=?,statustransaksi=?,tanggal_keluar=? WHERE id_cucian=?";
            try{
                PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
                statement.setString(1, nama);
                statement.setString(2, alamat);
                statement.setString(3, noHp);
                statement.setString(4, jenisKelamin);
                statement.setString(5, id_pelanggan);
                
                PreparedStatement statement2 = (PreparedStatement)conn.prepareStatement(sql2);
                statement2.setInt(1, berat);
                statement2.setString(2, strdate);
                statement2.setInt(3, harga);
                statement2.setString(4, statusbayar);
                statement2.setString(5, statustransaksi);
//                statement2.setString(6, tanggalNow);
                statement2.setString(6, tanggalNow);
                statement2.setString(7, id_cuci);
                
                statement.executeUpdate();
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(this,"Berhasil Update!","Success",JOptionPane.PLAIN_MESSAGE);
                reset();
                tampil();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,"Gagal Update! "+e,"Failed",JOptionPane.ERROR_MESSAGE);
            }
        } else{
            String id_cuci = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),1).toString();
            String id_pelanggan = model.getValueAt(jTable_CucianMasuk.getSelectedRow(),0).toString();
            String nama = txt_Nama.getText();
            String strdate = dateFormat.format(date_masuk.getDate());
            String alamat = txt_Alamat.getText();
            String noHp = txt_NoHP.getText();
            String jenisKelamin = null;
            String weight = txt_Berat.getText();
            int berat = Integer.parseInt(weight);
            String cost = txt_Harga.getText();
            int harga = Integer.parseInt(cost);
            if (jRadioButton_Laki.isSelected()) {
                jenisKelamin = "Laki-Laki";
            }else if (jRadioButton_Perempuan.isSelected()) {
                jenisKelamin = "Perempuan";
            }
            String statusbayar = ComboStatusBayar.getSelectedItem().toString();
            String statustransaksi = ComboStatusTransaksi.getSelectedItem().toString();
            String sql = "UPDATE pelanggan SET nama_pelanggan=?,alamat_pelanggan=?,nomer_hp=?,jenis_kelamin=? WHERE id_pelanggan =?";
            String sql2 = "UPDATE cucian SET berat=?,tanggal_masuk=?,harga=?,statuspembayaran=?,statustransaksi=? WHERE id_cucian=?";
            try{
                PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
                statement.setString(1, nama);
                statement.setString(2, alamat);
                statement.setString(3, noHp);
                statement.setString(4, jenisKelamin);
                statement.setString(5, id_pelanggan);
                
                PreparedStatement statement2 = (PreparedStatement)conn.prepareStatement(sql2);
                statement2.setInt(1, berat);
                statement2.setString(2, strdate);
                statement2.setInt(3, harga);
                statement2.setString(4, statusbayar);
                statement2.setString(5, statustransaksi);
                statement2.setString(6, id_cuci);
                
                statement.executeUpdate();
                statement2.executeUpdate();
                reset();
                tampil();
                JOptionPane.showMessageDialog(this,"Berhasil Update!","Success",JOptionPane.PLAIN_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,"Gagal Update! "+e,"Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton_UpdateActionPerformed

    private void btn_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pdfActionPerformed
        // TODO add your handling code here:
        
        MessageFormat header = new MessageFormat("LAPORAN");
        MessageFormat footer = new MessageFormat("Myu Laundry 2023");
        
        this.dispose();

        try{

            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.LANDSCAPE);
            jTable_CucianMasuk.print(JTable.PrintMode.FIT_WIDTH, header, footer, true,  set, true);
            JOptionPane.showMessageDialog(null, "\n" + "Printed Succesfully");
         
        } catch (java.awt.print.PrinterException e){

            JOptionPane.showMessageDialog(null, "\n" + "Failed" + "\n" + e);

        }
        
    }//GEN-LAST:event_btn_pdfActionPerformed

    private void btn_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excelActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        
        JnaFileChooser filechooser = new JnaFileChooser();
        Window window = null;
        boolean action = filechooser.showOpenDialog(window);
        
        if (action) {
            
            String fileString = filechooser.getSelectedFile() + " .xlsx";
            exportToExcel(jTable_CucianMasuk, fileString);
            
        }
        
    }//GEN-LAST:event_btn_excelActionPerformed

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
            java.util.logging.Logger.getLogger(CucianMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CucianMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CucianMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CucianMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CucianMasuk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboStatusBayar;
    private javax.swing.JComboBox<String> ComboStatusTransaksi;
    private javax.swing.JButton btn_excel;
    private javax.swing.JButton btn_pdf;
    private com.toedter.calendar.JDateChooser date_masuk;
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Hitung;
    private javax.swing.JButton jButton_Simpan;
    private javax.swing.JButton jButton_Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton_Laki;
    private javax.swing.JRadioButton jRadioButton_Perempuan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable_CucianMasuk;
    private javax.swing.ButtonGroup jk;
    private javax.swing.JLabel label_namaAdmin;
    private javax.swing.JTextField txt_Alamat;
    private javax.swing.JTextField txt_Berat;
    private javax.swing.JTextField txt_Harga;
    private javax.swing.JTextField txt_Nama;
    private javax.swing.JTextField txt_NoHP;
    // End of variables declaration//GEN-END:variables
}
