package model;

import java.sql.*;

public class TransaksiModel {

    // Menambah transaksi baru
   public void tambah(String nama, String obat, double harga, int jumlah) {
    try (Connection conn = koneksi.getConnection()) {
        String sql = "INSERT INTO transaksi (nama_pelanggan, nama_obat, harga_satuan, jumlah_beli, tanggal_transaksi) " +
                     "VALUES (?, ?, ?, ?, CURDATE())";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nama);
        stmt.setString(2, obat);
        stmt.setDouble(3, harga);
        stmt.setInt(4, jumlah);
        stmt.executeUpdate();

        System.out.println("Data berhasil ditambahkan"); // ✅ Tes log
    } catch (Exception e) {
        e.printStackTrace(); // ✅ Penting untuk debugging
    }
}


    // Menghapus transaksi berdasarkan ID
    public void hapus(int id) {
        try (Connection conn = koneksi.getConnection()) {
            String sql = "DELETE FROM transaksi WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mengedit transaksi berdasarkan ID
    public void edit(int id, String nama, String obat, double harga, int jumlah) {
        try (Connection conn = koneksi.getConnection()) {
            String sql = "UPDATE transaksi SET nama_pelanggan = ?, nama_obat = ?, harga_satuan = ?, jumlah_beli = ? " +
                         "WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, obat);
            stmt.setDouble(3, harga);
            stmt.setInt(4, jumlah);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Menghitung total harga dengan diskon 10% jika beli > 5
    public double hitungTotal(double harga, int jumlah) {
        double total = harga * jumlah;
        if (jumlah > 5) {
            total *= 0.9; // diskon 10%
        }
        return total;
    }

    // Menampilkan transaksi yang dilakukan hari ini
    public ResultSet tampilHariIni() {
        try {
            Connection conn = koneksi.getConnection();
            String sql = "SELECT * FROM transaksi WHERE tanggal_transaksi = CURDATE()";
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
