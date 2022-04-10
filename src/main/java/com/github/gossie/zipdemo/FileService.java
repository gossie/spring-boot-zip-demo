package com.github.gossie.zipdemo;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileService {

    public void addFile(OutputStream out) throws IOException {
        Path tempFilePath = null;
        try {
            tempFilePath = writeToTempFile(UUID.randomUUID().toString());
            addTempFileToOutputStream(tempFilePath, out);
        } finally {
            if (tempFilePath != null) {
                Files.deleteIfExists(tempFilePath);
            }
        }
    }

    private Path writeToTempFile(String filename) throws IOException {
        return Files.writeString(Files.createTempFile(filename, ".txt"), "Hallo, das ist der Inhalt der Datei " + filename + ".txt.");
    }

    private void addTempFileToOutputStream(Path tempFilePath, OutputStream out) throws IOException {
        out.write(Files.readAllBytes(tempFilePath));
    }

}
