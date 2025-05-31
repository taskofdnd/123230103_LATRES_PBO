package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransaksiView extends JFrame {
    public JTextField txtNama = new JTextField(10);
    public JTextField txtObat = new JTextField(10);
    public JTextField txtHarga = new JTextField(10);
    public JTextField txtJumlah = new JTextField(10);
    public JButton btnTambah = new JButton("Tambah");
    public JButton btnEdit = new JButton("Edit");
    public JButton btnHapus = new JButton("Hapus");
    public JTable table = new JTable();
    public DefaultTableModel model;

    public TransaksiView() {
        setTitle("Aplikasi Apotek");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1)); // Panel 2 baris

        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Nama")); formPanel.add(txtNama);
        formPanel.add(new JLabel("Obat")); formPanel.add(txtObat);
        formPanel.add(new JLabel("Harga")); formPanel.add(txtHarga);
        formPanel.add(new JLabel("Jumlah")); formPanel.add(txtJumlah);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);

        inputPanel.add(formPanel);
        inputPanel.add(buttonPanel);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Obat", "Harga", "Jumlah"}, 0);
        table.setModel(model);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }
}