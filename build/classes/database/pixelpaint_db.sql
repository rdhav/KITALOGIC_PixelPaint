-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Jun 2026 pada 05.41
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pixelpaint_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `artworks`
--

CREATE TABLE `artworks` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `image_path` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `artworks`
--

INSERT INTO artworks 
(id, title, description, category, image_path, user_id, created_at)
VALUES
(1, 'Sunset Over Mountains', 'Lukisan digital pemandangan matahari terbenam', 'Landscape', '2_1781556972811_sunset.jpg', 2, '2026-06-12 10:12:05'),

(2, 'Abstract Blue', 'Karya abstrak dengan dominasi warna biru', 'Abstract', '2_1781556972811_abstract_blue.jpg', 2, '2026-06-12 10:12:05'),

(3, 'City at Night', 'Ilustrasi kota malam hari dengan lampu neon', 'Illustration', '3_1781556972811_city_night.jpg', 3, '2026-06-12 10:12:05'),

(4, 'Forest Path', 'Jalan setapak di tengah hutan lebat', 'Landscape', '3_1781556972811_forest.jpg', 3, '2026-06-12 10:12:05'),

(5, 'Pixel Cat', 'Kucing lucu dalam gaya pixel art', 'Pixel Art', '4_1781556972811_pixel_cat.jpg', 4, '2026-06-12 10:12:05'),

(6, 'Retro Space', 'Tema luar angkasa bergaya retro 8-bit', 'Pixel Art', '4_1781556972811_retro_space.jpg', 4, '2026-06-12 10:12:05');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `bio` text DEFAULT NULL,
  `role` enum('admin','user') DEFAULT 'user',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `bio`, `role`, `created_at`) VALUES
(1, 'admin', 'admin123', 'Administrator PixelPaint', 'admin', '2026-06-12 03:12:05'),
(2, 'vin_art', 'vin123', 'Digital artist dari Jakarta', 'user', '2026-06-12 03:12:05'),
(3, 'luna_draws', 'luna123', 'Suka ilustrasi dan sketsa', 'user', '2026-06-12 03:12:05'),
(4, 'pixel_boy', 'pixel123', 'Hobi bikin pixel art', 'user', '2026-06-12 03:12:05');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `artworks`
--
ALTER TABLE `artworks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `artworks`
--
ALTER TABLE `artworks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `artworks`
--
ALTER TABLE `artworks`
  ADD CONSTRAINT `artworks_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
