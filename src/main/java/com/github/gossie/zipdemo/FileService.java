package com.github.gossie.zipdemo;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class FileService {

    public void addFile(OutputStream out) throws IOException {
        out.write("Hallo, das ist der Inhaltder Datei.".getBytes(StandardCharsets.UTF_8));
    }

}
