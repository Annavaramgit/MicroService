package com.mvstream.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
@RequestMapping("/movie-stream")
public class MovieStreamingController {
    public static final String VIDEO_DIRECTORY = "C:\\Users\\Sreenivas Bandaru\\Documents\\Microservices\\";

    /* for diplay or play video in the output */
    @GetMapping("/movie/{path}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String path) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY + path);
        if (file.exists()) {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
