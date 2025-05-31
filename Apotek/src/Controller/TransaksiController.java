package controller;

import model.TransaksiModel;
import view.TransaksiView;

import javax.swing.*;
import java.awt.event.*;

public class TransaksiController {
    private TransaksiModel model;
    private TransaksiView View;

    public TransaksiController(TransaksiModel model, TransaksiView view) {
        this.model = model;
        this.View = view;

        view.btnTambah.addActionListener(e -> tambah());
        view.btnHapus.addActionListener(e -> hapus());
        view.btnEdit.addActionListener(e -> edit());
        view.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.table.getSelectedRow();
                if (row != -1) {
                    double harga = Double.parseDouble(view.model.getValueAt(row, 3).toString());
                    int jumlah = Integer.parseInt(view.model.getValueAt(row, 4).toString());
                    double total = model.hitungTotal(harga, jumlah);
                    JOptionPane.showMessageDialog(view, "Total Bayar: Rp " + total);
                }
            }
        });
        refreshTable();
    }

    private void tambah() {
    try {
        String nama = View.txtNama.getText();
        String obat = View.txtObat.getText();
        double harga = Double.parseDouble(View.txtHarga.getText());
        int jumlah = Integer.parseInt(View.txtJumlah.getText());
        model.tambah(nama, obat, harga, jumlah);
        refreshTable();
        System.out.println("Tambah dipanggil");
    } catch (Exception e) {
        e.printStackTrace(); // tampilkan error detail di console
        JOptionPane.showMessageDialog(View, "Input tidak valid");
    }
}


    private void hapus() {
        int row = View.table.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(View.model.getValueAt(row, 0).toString());
            model.hapus(id);
            refreshTable();
        }
    }

    private void edit() {
        int row = View.table.getSelectedRow();
        if (row != -1) {
            try {
                int id = Integer.parseInt(View.model.getValueAt(row, 0).toString());
                String nama = View.txtNama.getText();
                String obat = View.txtObat.getText();
                double harga = Double.parseDouble(View.txtHarga.getText());
                int jumlah = Integer.parseInt(View.txtJumlah.getText());
                model.edit(id, nama, obat, harga, jumlah);
                refreshTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(View, "Edit gagal");
            }
        }
    }

    private void refreshTable() {
        try {
            var rs = model.tampilHariIni();
            View.model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("nama_obat"),
                    rs.getDouble("harga_satuan"),
                    rs.getInt("jumlah_beli")
                };
                View.model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}