package com.travel.service.impl;

import com.travel.utils.UriUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path pathLocation;

    public static final String HTML_REGEX = "(.*.html$)|(.*.htm$)";

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static final String CONTENT_FILES_LOCATION =
            new StringBuilder(System.getProperty("user.dir"))
                    .append(FILE_SEPARATOR).append("Travel")
                    .append(FILE_SEPARATOR).append("dynamic")
                    .append(FILE_SEPARATOR).append("contents").toString();

    public FileStorageService() throws FileNotFoundException {
        this.pathLocation = Paths.get(CONTENT_FILES_LOCATION);
    }

    /**
     * Store file in  CONTENT_FILES_LOCATION by MultipartFile
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String storeFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (!checkHtmlFormat(fileName))
                throw new Exception("File name is invalid");
            else fileName = fileName.replaceAll("html|htm", "");

            // Copy file to the target location (Replacing existing file with the same name)
            fileName = UriUtils.encodingUri(fileName).concat(".html");
            Path targetLocation = this.pathLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("some error");
        }
    }

    /**
     * Store a file with content is text
     *
     * @param fileName
     * @param text
     * @return
     * @throws Exception
     */
    public String storeFile(String fileName, String text) throws Exception {
        fileName = StringUtils.cleanPath(fileName);
        try {
            if (!checkHtmlFormat(fileName))
                throw new Exception("File name is invalid");
            else fileName = fileName.replaceAll("html|htm", "");

            fileName = UriUtils.encodingUri(fileName).concat(".html");
            Path targetLocation = this.pathLocation.resolve(fileName);
            Files.write(targetLocation, text.getBytes("UTF-8"));
            return fileName;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("some error");
        }
    }

    private boolean checkHtmlFormat(String fileName) {
        return fileName.matches(HTML_REGEX);
    }
}
