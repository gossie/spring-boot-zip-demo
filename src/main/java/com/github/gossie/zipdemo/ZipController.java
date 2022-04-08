package com.github.gossie.zipdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/zip")
public class ZipController {

    private static final Logger LOG = LoggerFactory.getLogger(ZipController.class);

    private final FileService fileService;

    public ZipController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public void download(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=download.zip");
        response.setStatus(HttpServletResponse.SC_OK);

        List<String> fileNames = List.of("one.txt", "two.txt", "three.txt");

        try (ZipOutputStream zip = new ZipOutputStream(response.getOutputStream())) {
            for (String fileName : fileNames) {
                ZipEntry zipEntry = new ZipEntry(fileName);
                zip.putNextEntry(zipEntry);
                fileService.addFile(zip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
