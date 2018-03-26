package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActorsRepository extends CrudRepository<Actor, String> {
    List<Actor> findByMovieId(String movieId);
}
