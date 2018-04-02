package com.example.demo.mutations;

import com.creactiviti.spring.boot.starter.graphql.Arguments;
import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.MutationBuilder;
import com.example.demo.entities.Genre;
import com.example.demo.entities.Movie;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.repositories.MoviesRepository;
import com.example.demo.types.MovieType;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AddMovie implements MutationBuilder {

  @Autowired
  private MoviesRepository moviesRepository;

  @Autowired
  private GenreRepository genreRepository;

  @Override
  public void build(GraphQLObjectType.Builder builder) {
    builder.field(Fields.field("addMovie")
            .argument(Arguments.notNull(Arguments.stringArgument("title")))
            .argument(Arguments.stringArgument("synopsis"))
            .argument(Arguments.stringArgument("duration"))
            .argument(Arguments.stringArgument("image_url"))
            .argument(GraphQLArgument.newArgument().name("rating").type(Scalars.GraphQLInt))
            .argument(Arguments.stringArgument("playback"))
            .argument(GraphQLArgument.newArgument().name("genre").type(GraphQLList.list(Scalars.GraphQLString)))
            .type(MovieType.REF)
            .dataFetcher(env -> {
              Movie movie = new Movie(UUID.randomUUID().toString(),
                      env.getArgument("title"),
                      env.getArgument("synopsis"),
                      env.getArgument("duration"),
                      env.getArgument("image_url"),
                      env.getArgument("rating"),
                      env.getArgument("playback"));
              movie = moviesRepository.save(movie);
              for (String genre : (List<String>) env.getArgument("genre")) {
                genreRepository.save(new Genre(movie.getId(), genre, UUID.randomUUID().toString()));
              }
              return movie;
            }));
  }
}
