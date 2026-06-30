/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Merepresentasikan data sebuah wallpaper/karya visual yang diunggah oleh
 * pengguna dalam aplikasi PixelPaint, mencakup judul, kategori, path
 * gambar, deskripsi, ID pengunggah, serta waktu unggah dan pembaruan.
 * Field-field di kelas ini dideklarasikan sebagai {@code protected}
 * sehingga dapat diwariskan dan diakses langsung oleh subclass apabila
 * diperlukan, sambil tetap menerapkan enkapsulasi melalui getter dan
 * setter untuk akses dari luar kelas.
 *
 * @author user
 */

public class Wallpaper  {   
    /** ID unik wallpaper. */
    protected int id;
 
    /** Judul wallpaper. */
    protected String title;
 
    /** Kategori wallpaper (contoh: "Landscape", "Pixel Art"). */
    protected String category;
 
    /** Nama berkas gambar wallpaper yang tersimpan di folder uploads. */
    protected String imagePath;
 
    /** Deskripsi singkat mengenai wallpaper. */
    protected String description;
 
    /** ID pengguna yang mengunggah wallpaper ini. */
    protected int userId;
 
    /** Waktu wallpaper pertama kali diunggah. */
    protected String timeAdded;
 
    /** Waktu wallpaper terakhir diperbarui, dapat bernilai {@code null}
     *  jika belum pernah diperbarui sejak diunggah. */
    protected String timeUpdated;
 

    /**
     * Membuat objek {@code Wallpaper} kosong tanpa nilai awal.
     * Biasanya digunakan saat data akan diisi kemudian melalui setter,
     * misalnya saat mengisi formulir update.
     */
    public Wallpaper(){}

    
    /**
     * Membuat objek {@code Wallpaper} baru lengkap dengan seluruh atributnya.
     *
     * @param id          ID unik wallpaper
     * @param title       judul wallpaper
     * @param category    kategori wallpaper
     * @param imagePath   nama berkas gambar wallpaper
     * @param description deskripsi singkat wallpaper
     * @param userId      ID pengguna yang mengunggah wallpaper
     * @param timeAdded   waktu wallpaper pertama kali diunggah
     * @param timeUpdated waktu wallpaper terakhir diperbarui
     */
    public Wallpaper(int id, String title, String category, String imagePath, String description, int userId,String timeAdded,String timeUpdated) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.imagePath = imagePath;
        this.description = description;
        this.userId = userId;
        this.timeAdded = timeAdded;
        this.timeUpdated = timeUpdated;
    }

    // --- GETTER AND SETTER (Mengontrol akses variabel beraliran enkapsulasi) ---
    /**
     * Mengembalikan ID unik wallpaper.
     *
     * @return ID wallpaper
     */
    public int getId() {
        return id;
    }
 
    /**
     * Mengubah ID wallpaper.
     *
     * @param id ID baru yang akan di-set
     */
    public void setId(int id) {
        this.id = id;
    }
 
    /**
     * Mengembalikan judul wallpaper.
     *
     * @return judul wallpaper
     */
    public String getTitle() {
        return title;
    }
 
    /**
     * Mengubah judul wallpaper.
     *
     * @param title judul baru yang akan di-set
     */
    public void setTitle(String title) {
        this.title = title;
    }
 
    /**
     * Mengembalikan kategori wallpaper.
     *
     * @return kategori wallpaper
     */
    public String getCategory() {
        return category;
    }
 
    /**
     * Mengubah kategori wallpaper.
     *
     * @param category kategori baru yang akan di-set
     */
    public void setCategory(String category) {
        this.category = category;
    }
 
    /**
     * Mengembalikan nama berkas gambar wallpaper.
     *
     * @return path/nama berkas gambar wallpaper
     */
    public String getImagePath() {
        return imagePath;
    }
 
    /**
     * Mengubah nama berkas gambar wallpaper.
     *
     * @param imagePath path/nama berkas gambar baru yang akan di-set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
 
    /**
     * Mengembalikan deskripsi wallpaper.
     *
     * @return deskripsi wallpaper
     */
    public String getDescription() {
        return description;
    }
 
    /**
     * Mengubah deskripsi wallpaper.
     *
     * @param description deskripsi baru yang akan di-set
     */
    public void setDescription(String description) {
        this.description = description;
    }
 
    /**
     * Mengembalikan ID pengguna yang mengunggah wallpaper ini.
     *
     * @return ID pengguna pengunggah
     */
    public int getUserId() {
        return userId;
    }
 
    /**
     * Mengubah ID pengguna pengunggah wallpaper.
     *
     * @param userId ID pengguna baru yang akan di-set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
 
    /**
     * Mengembalikan waktu wallpaper pertama kali diunggah.
     *
     * @return waktu unggah wallpaper
     */
    public String getTimeAdded() {
        return timeAdded;
    }
 
    /**
     * Mengubah waktu wallpaper pertama kali diunggah.
     *
     * @param timeAdded waktu unggah baru yang akan di-set
     */
    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }
 
    /**
     * Mengembalikan waktu wallpaper terakhir diperbarui.
     *
     * @return waktu pembaruan terakhir, atau {@code null} jika belum
     *         pernah diperbarui
     */
    public String getTimeUpdated() {
        return timeUpdated;
    }
 
    /**
     * Mengubah waktu wallpaper terakhir diperbarui.
     *
     * @param timeUpdated waktu pembaruan baru yang akan di-set
     */
    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }  
}
