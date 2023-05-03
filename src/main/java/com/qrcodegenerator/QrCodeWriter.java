package com.qrcodegenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class QrCodeWriter {
    private final PathValidator validator = new PathValidator();

    /**
     * This method will create the single valued QR Code right away in the specified directory path
     * If you want to store an array or multivalued data in your QR Code consider using generateQrCode method with the parameter of List<String>
     * @param data is the text you want to encode/store in your QR Code
     * @param width is the width of the QR Code image
     * @param height is the height of the QR Code image
     * @param filePath is the full path directory where the file is located. example: C://
     * @param fileName is the actual file name of the qr code. example: myqrcode
     * @param imageFormat is file format of the qr code. example: jpg, png, etc...
     */
    public void generateQrCode(String data, int width, int height, String filePath, String fileName, String imageFormat) throws WriterException, IOException {
        if (validator.validate(data)) throw new IllegalArgumentException("Please specify the data that will be encoded in your qr code");
        if (validator.validate(filePath)) filePath = "./";
        if (validator.validate(fileName)) fileName = "generated-qr-code";
        if (validator.validate(imageFormat)) imageFormat = "jpg";

        BitMatrix matrix = new MultiFormatWriter()
                .encode(data, BarcodeFormat.QR_CODE, width, height);

        String formattedPath = filePath + "\\" + fileName + '.' + imageFormat;
        MatrixToImageWriter.writeToPath(matrix, imageFormat, Paths.get(formattedPath));
        System.out.println("Qr Code Created Successfully");
    }

    /**
     * This method will store an List<String> that will encoded in your QR Code
     * If you want to read the list back consider using readMultiValuedQrCode method in QrCodeReader
     * Note: If you use readQrCode it will only returns the literal String of all the values you encoded
     * @param dataList is the list of values you want to store in your QR Code
     * @param width is the width of the QR Code image
     * @param height is the height of the QR Code image
     * @param filePath is the full path directory where the file is located. example: C://
     * @param fileName is the actual file name of the qr code. example: myqrcode
     * @param imageFormat is file format of the qr code. example: jpg, png, etc...
     */
    public void generateQrCode(List<String> dataList, int width, int height, String filePath, String fileName, String imageFormat) throws WriterException, IOException {
        if (dataList.isEmpty()) throw new IllegalArgumentException("Please specify the data that will be encoded in your qr code");
        if (validator.validate(filePath)) filePath = "./";
        if (validator.validate(fileName)) fileName = "generated-qr-code";
        if (validator.validate(imageFormat)) imageFormat = "jpg";

        String joinedData = String.join(", ", dataList);

        BitMatrix matrix = new MultiFormatWriter()
                .encode(joinedData, BarcodeFormat.QR_CODE, width, height);

        String formattedPath = filePath + "\\" + fileName + '.' + imageFormat;
        MatrixToImageWriter.writeToPath(matrix, imageFormat, Paths.get(formattedPath));
        System.out.println("Qr Code Created Successfully");
    }

    /**
     * This method will not create the image right away you need to use the createQrCodeImage method in QrCodeReader to actually create the QR Code image
     * @param data is the text you want to encode/store in your QR Code
     * @param width is the width of the QR Code image
     * @param height is the height of the QR Code image
     * @param imageFormat is file format of the qr code. example: jpg, png, etc...
     */
    public byte[] generateQrCodeImage(String data, int width, int height, String imageFormat) throws WriterException, IOException {
        if (validator.validate(data)) throw new IllegalArgumentException("Please specify the data that will be encoded in your qr code");
        if (validator.validate(imageFormat)) imageFormat = "jpg";
        BitMatrix matrix = new MultiFormatWriter()
                .encode(data, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, imageFormat, outputStream);
        System.out.println("Qr Generated Successfully");
        return outputStream.toByteArray();
    }
}
