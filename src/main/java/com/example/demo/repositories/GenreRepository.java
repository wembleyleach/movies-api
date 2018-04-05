package com.example.demo.repositories;

import com.example.demo.entities.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, String> {
  List<Genre> findByMovieId(String movieId);

  @Transactional
  Long deleteByMovieId(String movieId);

}
