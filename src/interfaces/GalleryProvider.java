/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import java.util.*;
import models.Wallpaper;

/**
 *
 * @author user
 */
public interface GalleryProvider {
    //abstract method
    List<Wallpaper> getGalleryWallpaper(int userId);
    
    //default method
    default List<Wallpaper> filterByCategory(List<Wallpaper> daftarWallpaper, String kategori) {
        List<Wallpaper> hasilFilter = new ArrayList<>();
        
        if (daftarWallpaper == null || kategori == null || kategori.isEmpty()) {
            return hasilFilter;
        }
        
        for (Wallpaper wp : daftarWallpaper) {
            if (wp.getCategory() != null && wp.getCategory().equalsIgnoreCase(kategori)) {
                hasilFilter.add(wp);
            }
        }
        return hasilFilter;
    }
    
    //static method
    static int countWallpaper (List<Wallpaper> daftarWallpaper) {       
        if (daftarWallpaper == null || daftarWallpaper.isEmpty()) {
            return 0;
        }     
        return  daftarWallpaper.size();       
    }
    
}
