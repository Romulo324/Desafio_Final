package br.com.codewave.codewave.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUpLoadUtil {

    public static void saveFile(String uploadir, String fileName, MultipartFile multipartFile)
            throws IOException {
        Path upLoadPath = Paths.get(uploadir);

        if (!Files.exists(upLoadPath)){
            Files.createDirectories(upLoadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = upLoadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ioException){
            throw new IOException("nÃ£o foi possivel adicionar sua imagem" + fileName, ioException);
        }
    }

}
