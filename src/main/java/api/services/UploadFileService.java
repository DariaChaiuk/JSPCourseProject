package api.services;

import contracts.services.S3Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class UploadFileService {
    public static List<String> uploadFile(HttpServletRequest request, String offerId) {
        List<String> fileNames = new ArrayList<String>();
        try {
            List<Part> parts = (List<Part>) request.getParts();
            for (Part part : parts) {
                String a = part.getName();
                if (part.getName().equalsIgnoreCase("uploadPhotos")) {
                    String fileName = getFileName(part);
                    fileNames.add(fileName);
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    try {

                        inputStream = part.getInputStream();
                        S3Util.uploadFiles(offerId, fileName, inputStream);

                    } catch (Exception ex) {
                        fileName = null;
                    } finally {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            fileNames = null;
        }
        return fileNames;
    }

    private static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
