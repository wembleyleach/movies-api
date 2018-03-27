package com.example.demo.repositories;

import com.example.demo.entities.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, String> {
    List<Genre> findByMovieId(String movieId);
}
