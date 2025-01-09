package PharmaHouseBE.services.Implementation;

import java.io.*;

import PharmaHouseBE.entities.Medicament;
import PharmaHouseBE.entities.Ordonnance;
import PharmaHouseBE.entities.Prise;
import PharmaHouseBE.entities.Utilisateur;
import PharmaHouseBE.repositories.MedicamentRepository;
import PharmaHouseBE.repositories.OrdonnanceRepository;
import PharmaHouseBE.repositories.PriseRepository;
import PharmaHouseBE.repositories.UtilisateurRepository;
import PharmaHouseBE.services.Interfaces.IPrise;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@AllArgsConstructor
@Service
public class PriseServiceImpl implements IPrise {
    private OrdonnanceRepository ordonnanceRepository;
    private MedicamentRepository medicamentRepository;
    private PriseRepository priseRepository;
    private UtilisateurRepository utilisateurRepository;
    @Override
    public Set<Prise> addMedicamentsToOrdonnance(Long ordonnanceId,
                                                 Set<Prise> ordonnanceMedicaments) {
        Optional<Ordonnance> ordonnanceOptional = ordonnanceRepository.findById(ordonnanceId);
        if (!ordonnanceOptional.isPresent()) {
            throw new RuntimeException("Ordonnance not found");
        }
        Ordonnance ordonnance = ordonnanceOptional.get();
        Set<Prise> savedOrdonnanceMedicaments = new HashSet<>();

        for (Prise ordonnanceMedicament : ordonnanceMedicaments) {
            Optional<Medicament> medicamentOptional = medicamentRepository.findById(ordonnanceMedicament.getMedicament().getId());
            if (medicamentOptional.isPresent()) {
                Medicament medicament = medicamentOptional.get();
                ordonnanceMedicament.setOrdonnance(ordonnance);
                ordonnanceMedicament.setMedicament(medicament);
                savedOrdonnanceMedicaments.add(priseRepository.save(ordonnanceMedicament));
            }
        }

        return savedOrdonnanceMedicaments;
    }


    private byte[] generateImageFromPrises(List<Prise> prises) {
        int width = 800;
        int height = 1000;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        try (InputStream logoStream = getClass().getResourceAsStream("/logo.png")) {
            if (logoStream != null) {
                BufferedImage logo = ImageIO.read(logoStream);
                Image scaledLogo = logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                int logoX = (width - scaledLogo.getWidth(null)) / 2;
                int logoY = 20;
                g.drawImage(scaledLogo, logoX, logoY, null);
            } else {
                System.err.println("Logo introuvable");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.GREEN);
        String pharmaHouseText1 = "PharmaHouse";
        g.drawString(pharmaHouseText1, 20, 50);
        g.setFont(new Font("Arial", Font.PLAIN, 19));
        g.setColor(Color.BLACK);
        String pharmaHouseText2 = "Boîte à pharmacie en ligne";
        g.drawString(pharmaHouseText2, 20, 70);
        String phoneText = "+21651594959";
        String emailText = "pharmaHouse001@gmail.com";
        int phoneWidth = g.getFontMetrics().stringWidth(phoneText);
        int emailWidth = g.getFontMetrics().stringWidth(emailText);
        int textY = 50;
        int phoneX = width - 20 - phoneWidth;
        int emailX = width - 20 - emailWidth;
        g.drawString(phoneText, phoneX, textY);
        textY += 20;
        g.drawString(emailText, emailX, textY);
        g.setColor(Color.LIGHT_GRAY);
        g.setStroke(new BasicStroke(2));
        int lineY = 128;
        g.drawLine(20, lineY, width - 20, lineY);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        String ordonnanceText = "Ordonnance médicale";
        int ordonnanceTextWidth = g.getFontMetrics().stringWidth(ordonnanceText);
        int ordonnanceTextX = (width - ordonnanceTextWidth) / 2;
        int ordonnanceTextY = lineY + 30;
        g.drawString(ordonnanceText, ordonnanceTextX, ordonnanceTextY);
        int underlineY = ordonnanceTextY + 5;
        g.setStroke(new BasicStroke(1));
        g.drawLine(ordonnanceTextX, underlineY, ordonnanceTextX + ordonnanceTextWidth, underlineY);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        String locationDateText = "Fait à Tunis, le " + formattedDate;
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.setColor(Color.BLUE);
        int locationDateTextWidth = g.getFontMetrics().stringWidth(locationDateText);
        int locationDateTextX = (width - locationDateTextWidth) / 2;
        int locationDateTextY = underlineY + 21;
        g.drawString(locationDateText, locationDateTextX, locationDateTextY);
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, width - 20, height - 20);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.BLACK);
        String[] headers = {"Médicament", "Jour", "Midi", "Soir", "De", "À"};
        int[] colWidths = {200, 60, 60, 60, 100, 100};
        int tableStartY = locationDateTextY + 60;
        int tableStartX = (width - (colWidths[0] + colWidths[1] + colWidths[2] + colWidths[3] + colWidths[4] + colWidths[5])) / 2;
        int x = tableStartX;
        for (int i = 0; i < headers.length; i++) {
            g.drawString(headers[i], x, tableStartY);
            x += colWidths[i] + 10;
        }
        x = tableStartX;
        for (int i = 0; i < headers.length; i++) {
            g.drawRect(x - 5, tableStartY - 15, colWidths[i] + 10, 20);
            x += colWidths[i] + 10;
        }
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        int y = tableStartY + 20;
        for (Prise prise : prises) {
            x = tableStartX;
            g.drawString(prise.getMedicament().getTitre(), x, y);
            g.drawRect(x - 5, y - 15, colWidths[0] + 10, 20);
            x += colWidths[0] + 10;
            g.drawString(String.valueOf(prise.getPriseJour()), x, y);
            g.drawRect(x - 5, y - 15, colWidths[1] + 10, 20);
            x += colWidths[1] + 10;
            g.drawString(String.valueOf(prise.getPriseMidi()), x, y);
            g.drawRect(x - 5, y - 15, colWidths[2] + 10, 20);
            x += colWidths[2] + 10;
            g.drawString(String.valueOf(prise.getPriseSoir()), x, y);
            g.drawRect(x - 5, y - 15, colWidths[3] + 10, 20);
            x += colWidths[3] + 10;
            g.drawString(prise.getDateDebutPrise().toString(), x, y);
            g.drawRect(x - 5, y - 15, colWidths[4] + 10, 20);
            x += colWidths[4] + 10;
            g.drawString(prise.getDateFinPrise().toString(), x, y);
            g.drawRect(x - 5, y - 15, colWidths[5] + 10, 20);
            y += 25;
        }
        int bottomLineY = height - 50;
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawLine(20, bottomLineY, width - 20, bottomLineY);
        String addressText = "Adresse: Boite à Pharmacie en ligne - Tunisie - Tunis, www.pharmaHouse.com";
        int addressTextWidth = g.getFontMetrics().stringWidth(addressText);
        int addressTextX = (width - addressTextWidth) / 2;
        int addressTextY = bottomLineY + 20;
        g.drawString(addressText, addressTextX, addressTextY);
        String qrCodeData = generateUniqueIdentifier();
        int qrCodeSize = 150;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitMatrix matrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize);
            MatrixToImageWriter.writeToStream(matrix, "png", baos);
            baos.flush();
            byte[] qrCodeBytes = baos.toByteArray();
            baos.close();
            BufferedImage qrImage = ImageIO.read(new ByteArrayInputStream(qrCodeBytes));
            int qrX = 10;
            int qrY = height - qrImage.getHeight() - 10;
            g.drawImage(qrImage, qrX, qrY, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    private String generateUniqueIdentifier() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<Prise> addPrises(Long userId, List<Prise> prises) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Ordonnance ordonnance = new Ordonnance();
        ordonnance.setDateCreation(LocalDate.now());
        ordonnance.setEtat(1);
        ordonnance.setUtilisateur(utilisateur);
        byte[] image = generateImageFromPrises(prises);
        ordonnance.setPhoto(image);
        ordonnance = ordonnanceRepository.save(ordonnance);
        for (Prise prise : prises) {
            medicamentRepository.findById(prise.getMedicament().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicament not found"));
            prise.setOrdonnance(ordonnance);
        }
        return priseRepository.saveAll(prises);
    }

    @Override
    public List<Prise> getMedicamentsByOrdonnance(Long ordonnanceId) {
        return priseRepository.findByOrdonnanceId(ordonnanceId);
    }
    @Override
    public List<Prise> getPrisesByUserId(Long userId) {
        return priseRepository.findAllByUserId(userId);
    }






}
