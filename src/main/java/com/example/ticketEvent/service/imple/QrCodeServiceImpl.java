package com.example.ticketEvent.service.imple;

import java.util.UUID;

import com.example.ticketEvent.domain.entities.QrCode;

@RequiredArgsConstructor
@Service
public class QrCodeServiceImpl implements QrCodeService {

    private final QrCodeWriter qrCodeWriter;
    private QrCodeRepository qrCodeRepository;

    private final static int QR_CODE_IMAGE_WIDTH = 200;
    private final static int QR_CODE_IMAGE_HEIGHT = 200;

    @Override
    public QrCode generateQrCode(Ticket ticket) {
        
        try{

            UUID uniqueId = UUID.randomUUID();
            String qrCodeImage = generateQrCodeImage(uniqueId);

            QrCode qrCode = new QrCode();
            qrCode.setId(uniqueId); 
            qrCode.setValue(qrCodeImage);
            qrCode.setStatus(QrCodeStatus.ACTIVE);
            qrCode.setTicket(ticket);

            return qrCodeRepository.saveAndFlush(qrCode);  

        } catch(WriterException | IOException ex){
            throw new QrCodeGenerationException("Failed to generate QR code" , ex);
        }
        

    }

    private String generateQrCodeImage(UUID uniqueId) throws WriterException, IOException {
        
            BitMatrix bitMatrix = qrCodeWriter.encode(uniqueId.toString(), BarcodeFormat.QR_CODE, QR_CODE_IMAGE_WIDTH, QR_CODE_IMAGE_HEIGHT);
                // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                // MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                ImageIO.write(qrCodeImage, "PNG", outputStream);
                byte[] qrCodeBytes = outputStream.toByteArray();
                String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeBytes);
                return qrCodeBase64;
           
      
        }
    } 
}