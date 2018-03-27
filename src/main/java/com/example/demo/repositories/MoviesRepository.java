package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesRepository extends CrudRepository<Movie, String> {
    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByRating(Integer rating);

    @Query(value = "SELECT m FROM Movie m WHERE m.id IN (SELECT g.movieId FROM Genre g WHERE g.name = :genre)")
    List<Movie> findByGenre(@Param("genre") String genre);
}
