package com.qrcodegenerator;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class QrCodeReader {

    private final PathValidator validator = new PathValidator();

    /**
     * Use this if you want to read a single valued data in your generated qr code otherwise use readMultiValuedQrCode method
     * @param filePath is the full path directory where the file is located. example: C://
     * @param fileName is the actual file name of the qr code. example: myqrcode
     * @param imageFormat is file format of the qr code. example: jpg, png, etc...
     * This method will transform the specified paths like: C://myqrcode.jpg automatically
     */
    public String readQrCode(String filePath, String fileName, String imageFormat) throws NotFoundException, IOException {
        if (validator.validate(filePath))   throw new IllegalArgumentException("Please specify the correct file path example: C://");
        if (validator.validate(fileName)) throw new IllegalArgumentException("Please specify the correct file name example: yourqrcode");
        if (validator.validate(imageFormat)) throw new IllegalArgumentException("Please specify the correct image format example: jpg");
        String formattedPath = filePath + "\\" + fileName + '.' + imageFormat;
        BufferedImage bf = ImageIO.read(new FileInputStream(formattedPath));

        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(bf)
        ));

        Result result = new MultiFormatReader().decode(bitmap);
        System.out.println("Reading the QR Code Success");
        return result.getText();
    }

    /**
     * Use this if you want to read a multivalued data in your generated qr code that you created otherwise use readQrCode method
     * @param filePath is the full path directory where the file is located. example: C://
     * @param fileName is the actual file name of the qr code. example: myqrcode
     * @param imageFormat is file format of the qr code. example: jpg, png, etc...
     * This method will transform the specified paths like: C://myqrcode.jpg automatically
     */
    public List<String> readMultiValuedQrCode(String filePath, String fileName, String imageFormat) throws NotFoundException, IOException {
        if (validator.validate(filePath))   throw new IllegalArgumentException("Please specify the correct file path example: C://");
        if (validator.validate(fileName)) throw new IllegalArgumentException("Please specify the correct file name example: yourqrcode");
        if (validator.validate(imageFormat)) throw new IllegalArgumentException("Please specify the correct image format example: jpg");

        String formattedPath = filePath + "\\" + fileName + '.' + imageFormat;
        BufferedImage bf = ImageIO.read(new FileInputStream(formattedPath));

        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(bf)
        ));

        Result result = new MultiFormatReader().decode(bitmap);
        String decodeQrCode = result.getText();

        List<String> list = Arrays.asList(decodeQrCode.split(","));

        System.out.println("Reading the QR Code Success");
        return list;
    }
}
