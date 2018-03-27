package com.example.demo.services;

import com.example.demo.entities.Movie;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TMDb {
    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private static final String API_KEY = "c76575de14cf68c830552a0826982e94";

    public static List<Movie> getPopularMovies() {
        final String url = String.format("%s/movie/now_playing?api_key=%s", BASE_URL, API_KEY);
        Movies movies = restTemplate.getForObject(url, Movies.class);
        return movies.getResults();
    }
}
