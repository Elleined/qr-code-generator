package com.qrcodegenerator;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class MainTest {
    @Test
    void writeAndReadQRCode() throws IOException, WriterException, NotFoundException {
        QrCodeWriter qrCodeWriter = new QrCodeWriter();

        String data = "Sample Data";
        int width = 500;
        int height = 500;
        String filePath = "C:\\";
        String fileName = "my-first-qr-code";
        String imageFormat = "jpg";

        // Generating QR Code
        qrCodeWriter.generateQrCode(data, width, height, filePath, fileName, imageFormat);

        QrCodeReader qrCodeReader = new QrCodeReader();

        // Reading QR Code
        String decodedQrCode = qrCodeReader.readQrCode(filePath, fileName, imageFormat);

        System.out.println("Decoded data in QR Code " + decodedQrCode);
    }

    @Test
    void multiValuedQrCode() throws IOException, WriterException, NotFoundException {
        QrCodeWriter qrCodeWriter = new QrCodeWriter();

        List<String> list = List.of("Value 1", "Value 2", "Value 3");
        int width = 500;
        int height = 500;
        String filePath = "C:\\";
        String fileName = "my-first-qr-code";
        String imageFormat = "jpg";

        // Generating QR Code
        qrCodeWriter.generateQrCode(list, width, height, filePath, fileName, imageFormat);

        QrCodeReader qrCodeReader = new QrCodeReader();

        // Reading QR Code
        List<String> decodedData = qrCodeReader.readMultiValuedQrCode(filePath, fileName, imageFormat);

        System.out.println("Decoded data in QR Code ");
        decodedData.forEach(System.out::println);
    }

    @Test
    void returnQrCodeImage() throws IOException, WriterException, NotFoundException {
        QrCodeWriter qrCodeWriter = new QrCodeWriter();

        String data = "Sample Data";
        int width = 500;
        int height = 500;
        String imageFormat = "jpg";

        // Generating QR Code byte[]
        byte[] qrCodeByteArray = qrCodeWriter.generateQrCodeImage(data, width, height, imageFormat);

        String filePath = "C:\\";
        String fileName = "my-first-qr-code";

        // Creating QR Code image
        qrCodeWriter.createQrCodeImage(qrCodeByteArray, filePath, fileName, imageFormat);

        QrCodeReader qrCodeReader = new QrCodeReader();

        // Reading QR CODE
        String decodedQrCode = qrCodeReader.readQrCode(filePath, fileName, imageFormat);

        System.out.println("Decoded data in QR Code " + decodedQrCode);
    }

    @Test
    void returnMultiValuedQrCodeImage() throws IOException, WriterException, NotFoundException {
        QrCodeWriter qrCodeWriter = new QrCodeWriter();

        List<String> list = List.of("Value 1", "Value 2", "Value 3");
        int width = 500;
        int height = 500;
        String imageFormat = "jpg";

        // Generating QR Code byte[]
        byte[] qrCodeByteArray = qrCodeWriter.generateQrCodeImage(list, width, height, imageFormat);

        String filePath = "C:\\";
        String fileName = "my-first-qr-code";

        // Creating QR Code image
        qrCodeWriter.createQrCodeImage(qrCodeByteArray, filePath, fileName, imageFormat);

        QrCodeReader qrCodeReader = new QrCodeReader();

        // Reading QR CODE
        List<String> decodedDataList = qrCodeReader.readMultiValuedQrCode(filePath, fileName, imageFormat);
        
        System.out.println("Decoded data in QR Code ");
        decodedDataList.forEach(System.out::println);
    }
}