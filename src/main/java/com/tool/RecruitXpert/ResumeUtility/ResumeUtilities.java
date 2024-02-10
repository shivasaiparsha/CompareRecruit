package com.tool.RecruitXpert.ResumeUtility;

import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ResumeUtilities {

    public InputStream combinePdfStreams(List<InputStream> pdfStreams) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            for (InputStream pdfStream : pdfStreams) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = pdfStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            InputStream inputStream = new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray());

            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
            return null;
        }
    }

    public static MediaType getMediaType(String name) {

        switch (name.toLowerCase()) {

            case "pdf":
                return MediaType.APPLICATION_PDF;
            case "jpeg":
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "png":
                return MediaType.IMAGE_PNG;
            case "doc":
                return MediaType.valueOf("application/msword");
            default:
                return MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
    }

}
