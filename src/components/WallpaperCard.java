package components;

import interfaces.GalleryProvider;
import models.Wallpaper;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import views.DetailFrame;
import java.awt.image.BufferedImage;

public class WallpaperCard extends javax.swing.JPanel {

    private final Wallpaper wallpaperInfo;
    private final JFrame mainFrame;
    private final int currentUserId;

    private JLabel jLabelImage;
    private JLabel jLabelImageTitle;

    private static final int MAX_LEBAR = 245;
    private static final int MAX_TINGGI_GAMBAR = 250;

    private Image scaleAndCrop(Image original, int targetW, int targetH) {
        BufferedImage bi = new BufferedImage(
            original.getWidth(null), original.getHeight(null), BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g0 = bi.createGraphics();
        g0.drawImage(original, 0, 0, null);
        g0.dispose();

        int origW = bi.getWidth();
        int origH = bi.getHeight();

        double scaleX = (double) targetW / origW;
        double scaleY = (double) targetH / origH;
        double scale = Math.max(scaleX, scaleY);

        int scaledW = (int) (origW * scale);
        int scaledH = (int) (origH * scale);

        int cropX = (scaledW - targetW) / 2;
        int cropY = (scaledH - targetH) / 2;

        int radius = 20;

        BufferedImage result = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = result.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setClip(new java.awt.geom.RoundRectangle2D.Float(0, 0, targetW, targetH, radius, radius));
        g2.drawImage(bi, -cropX, -cropY, scaledW, scaledH, null);
        g2.dispose();

        return result;
    }

    // Constructor ketika user sudah login
    public WallpaperCard(Wallpaper wp, int currentUserId, JFrame mainFrame) {
        this.wallpaperInfo = wp;
        this.currentUserId = currentUserId;
        this.mainFrame = mainFrame;
        buildCard();
    }

    // Constructor ketika belum login (guest)
    public WallpaperCard(Wallpaper wp, JFrame mainFrame) {
        this.wallpaperInfo = wp;
        this.currentUserId = -1;
        this.mainFrame = mainFrame;
        buildCard();
    }

    private void buildCard() {
        if (wallpaperInfo == null) {
            System.out.println("Error: Data wallpaper tidak ditemukan.");
            return;
        }

        setLayout(null);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        File fileGambar = new File("src/uploads/" + wallpaperInfo.getImagePath());
        ImageIcon originalIcon;

        if (fileGambar.exists() && wallpaperInfo.getImagePath() != null) {
            originalIcon = new ImageIcon(fileGambar.getAbsolutePath());
        } else {
            originalIcon = new ImageIcon("src/uploads/default.jpg");
        }

        int targetWidth = MAX_LEBAR;
        int targetHeight = MAX_TINGGI_GAMBAR;

        Image scaledImage = scaleAndCrop(originalIcon.getImage(), targetWidth, targetHeight);
        jLabelImage = new JLabel(new ImageIcon(scaledImage));
        jLabelImage.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelImage.setBounds(0, 0, targetWidth, targetHeight);

        final float[] alpha = {0f};
        final javax.swing.Timer[] timer = {null};

        int tinggiOverlay = 70;

        JPanel overlayBackground = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, (int)(180 * alpha[0])));
                g2.fillRoundRect(0, 0, getWidth(), getHeight() + 20, 20, 20);
                g2.dispose();
            }
        };
        overlayBackground.setOpaque(false);
        overlayBackground.setLayout(new BoxLayout(overlayBackground, BoxLayout.Y_AXIS));
        overlayBackground.setBounds(0, targetHeight - tinggiOverlay, targetWidth, tinggiOverlay); 
        overlayBackground.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        // Title
        jLabelImageTitle = new JLabel(kapitalHurufAwal(potongTeksJikaPanjang(wallpaperInfo.getTitle(), 22)));
        jLabelImageTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabelImageTitle.setForeground(new Color(1f, 1f, 1f, 0f));

        // Kategori
        JLabel jLabelKategori = new JLabel(kapitalHurufAwal(potongTeksJikaPanjang(wallpaperInfo.getCategory(), 28)));
        jLabelKategori.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabelKategori.setForeground(new Color(1f, 1f, 1f, 0f));

        // Gap antara kategori dan author
        Box.Filler gap = new Box.Filler(
            new Dimension(0, 6), new Dimension(0, 0), new Dimension(0, 0)
        );

        // Author
        JLabel jLabelAuthor = new JLabel(kapitalHurufAwal(potongTeksJikaPanjang(GalleryProvider.getUsernameFromId(wallpaperInfo.getUserId()), 28)));
        jLabelAuthor.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabelAuthor.setForeground(new Color(1f, 1f, 1f, 0f));

        overlayBackground.add(jLabelImageTitle);
        overlayBackground.add(jLabelKategori);
        overlayBackground.add(gap); // ← gap di antara keduanya
        overlayBackground.add(jLabelAuthor);

        java.awt.event.MouseAdapter hoverListener = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (timer[0] != null) timer[0].stop();
                timer[0] = new javax.swing.Timer(15, e -> {
                    alpha[0] = Math.min(1f, alpha[0] + 0.08f);
                    overlayBackground.repaint();
                    jLabelImageTitle.setForeground(new Color(1f, 1f, 1f, alpha[0]));
                    jLabelKategori.setForeground(new Color(1f, 1f, 1f, alpha[0] * 0.7f));
                    jLabelAuthor.setForeground(new Color(1f, 1f, 1f, alpha[0] * 0.7f));
                    if (alpha[0] >= 1f) timer[0].stop();
                });
                timer[0].start();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (timer[0] != null) timer[0].stop();
                timer[0] = new javax.swing.Timer(15, e -> {
                    alpha[0] = Math.max(0f, alpha[0] - 0.08f);
                    overlayBackground.repaint();
                    jLabelImageTitle.setForeground(new Color(1f, 1f, 1f, alpha[0]));
                    jLabelKategori.setForeground(new Color(1f, 1f, 1f, alpha[0] * 0.7f));
                    jLabelAuthor.setForeground(new Color(1f, 1f, 1f, alpha[0] * 0.7f));
                    if (alpha[0] <= 0f) timer[0].stop();
                });
                timer[0].start();
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bukaDetail();
            }
        };

        jLabelImage.addMouseListener(hoverListener);
        overlayBackground.addMouseListener(hoverListener);

        add(overlayBackground);
        add(jLabelImage);

        Dimension ukuranCard = new Dimension(targetWidth, targetHeight);
        setPreferredSize(ukuranCard);
        setMinimumSize(ukuranCard);
        setMaximumSize(ukuranCard);
    }

    private String kapitalHurufAwal(String teks) {
        if (teks == null || teks.isEmpty()) return "";
        String[] kata = teks.split(" ");
        StringBuilder hasil = new StringBuilder();
        for (String k : kata) {
            if (!k.isEmpty()) {
                hasil.append(Character.toUpperCase(k.charAt(0)))
                     .append(k.substring(1).toLowerCase())
                     .append(" ");
            }
        }
        return hasil.toString().trim();
    }

    private String potongTeksJikaPanjang(String teks, int maksKarakter) {
        if (teks == null) return "";
        if (teks.length() <= maksKarakter) return teks;
        return teks.substring(0, maksKarakter) + "...";
    }

    private void bukaDetail() {
        try {
            File fileGambar = new File("src/uploads/" + wallpaperInfo.getImagePath());
            DetailFrame detail = new DetailFrame(wallpaperInfo, currentUserId, mainFrame, fileGambar);
            detail.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}