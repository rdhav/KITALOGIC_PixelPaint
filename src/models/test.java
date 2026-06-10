/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.sql.Connection;
import database.DBConnection;
/**
 *
 * @author Nice
 */
public class test {
        public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("Koneksi sukses!");
        } else {
            System.out.println("Koneksi gagal!");
        }
    }
}
