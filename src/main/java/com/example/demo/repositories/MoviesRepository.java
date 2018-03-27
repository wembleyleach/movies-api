package com.example.demo.repositories;

import com.example.demo.entities.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoviesRepository extends CrudRepository<Movie, String> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
}
