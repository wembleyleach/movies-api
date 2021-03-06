package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
  @Id
  private String id;

  private String title;

  @JsonProperty(value = "overview")
  private String synopsis;

  private String duration;

  @Column(name = "image_url")
  @JsonProperty(value = "backdrop_path")
  private String imageUrl;

  private Integer rating;

  private String playback;


  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movie")
  private List<Genre> genres = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movie")
  private List<Actor> actors = new ArrayList<>();

  public Movie() {
  }

  public Movie(String id, String title, String synopsis, String duration, String imageUrl, Integer rating, String playback) {
    this.id = id;
    this.title = title;
    this.synopsis = synopsis;
    this.duration = duration;
    this.imageUrl = imageUrl;
    this.rating = rating;
    this.playback = playback;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getTitle() {
    return title;
  }

  public String getId() {
    return id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public String getPlayback() {
    return playback;
  }

  public void setPlayback(String playback) {
    this.playback = playback;
  }

}
