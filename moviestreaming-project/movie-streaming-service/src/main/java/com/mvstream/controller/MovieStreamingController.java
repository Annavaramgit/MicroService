package com.mvstream.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/movie-stream")

public class MovieStreamingController {

    /* Main directory of the video*/
    public static final String VIDEO_DIRECTORY = "C:\\Users\\Sreenivas Bandaru\\Documents\\Microservices\\";

    /*dependecy injecting */
    public final CallMovieCatalogInfo callMovieCatalogInfo;
    public MovieStreamingController(CallMovieCatalogInfo callMovieCatalogInfo) {
        this.callMovieCatalogInfo = callMovieCatalogInfo;
    }


    /* for diplay or play video in the output */
    @GetMapping("/movie/{path}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String path) throws FileNotFoundException {
        log.info("-->streamVideo method executed<--");

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

    /* for diplay or play video in the output */
    @GetMapping("/movieWithId/{id}")
    public ResponseEntity<InputStreamResource> streamVideoWithId(@PathVariable long id) throws FileNotFoundException {
        log.info("{MovieId is {}--->"+id);
        String moviePath = callMovieCatalogInfo.getMoviePath(id);
        log.info("Path Is--->{}"+moviePath);
        /* this calls above method along with passing video path */
        if (moviePath.startsWith("something went wrong")) {
            // Return an appropriate response for the error
            return ResponseEntity.status(503).body(null);
        }
        return streamVideo(moviePath);


    }

}
