package com.mvinfo.repo;

import com.mvinfo.entity.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieInfoRepo  extends JpaRepository<MovieInfo,Long> {

}
