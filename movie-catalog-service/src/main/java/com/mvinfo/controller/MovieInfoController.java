package com.mvinfo.controller;

import com.mvinfo.entity.MovieInfo;
import com.mvinfo.repo.MovieInfoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@RequestMapping("/movie-info")
public class MovieInfoController {

    public final MovieInfoRepo movieInfoRepo;

    public MovieInfoController(MovieInfoRepo movieInfoRepo) {
        log.info("constructor xecuted");
        this.movieInfoRepo = movieInfoRepo;
    }


    /* this is for save the movie list*/
    @PostMapping("/save")
    public ResponseEntity<Map<String, List<MovieInfo>>> saveInfo(@RequestBody List<MovieInfo> info) {
        log.info("save method..");
        Map<String, List<MovieInfo>> map = new ConcurrentHashMap();
        map.put("result", movieInfoRepo.saveAll(info));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    /* this is for getting path of the specific movie*/
    @GetMapping("/getPath/{id}")
    public ResponseEntity<Map<String, String>> pathGetting(@PathVariable long id) {
        log.info("path getting method{}------>" + id);
        Map<String, String> map = new ConcurrentHashMap<>();
        MovieInfo info = movieInfoRepo.findById(id).orElse(null);
        String path = info.getPath();
        map.put("result", path);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
