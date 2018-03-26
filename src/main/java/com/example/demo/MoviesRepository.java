package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movie, String> {

}
