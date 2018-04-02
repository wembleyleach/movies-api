package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {
  @Id
  private String id;

  private String name;

  @Column(name = "movie_id")
  private String movieId;

  public Genre() {
  }

  public Genre(String movieId, String name, String id) {
    this.id = id;
    this.name = name;
    this.movieId = movieId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMovieId() {
    return movieId;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }
}
