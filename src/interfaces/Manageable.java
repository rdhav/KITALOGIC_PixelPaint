/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

/**
 *
 * @author Nice
 */
public interface Manageable {
    // Abstract methods
    void save();
    void delete();

    // Static method
    static String getAppName() {
        return "PixelPaint";
    }

    // Default method
    default String getInfo() {
        return "Ini adalah konten dari aplikasi " + getAppName();
    }
}