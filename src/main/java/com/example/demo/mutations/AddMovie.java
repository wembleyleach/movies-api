package com.example.demo.mutations;

import com.creactiviti.spring.boot.starter.graphql.Arguments;
import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.MutationBuilder;
import com.example.demo.entities.Actor;
import com.example.demo.entities.Genre;
import com.example.demo.entities.Movie;
import com.example.demo.repositories.ActorsRepository;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.repositories.MoviesRepository;
import com.example.demo.types.MovieType;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLInputObjectField;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class AddMovie implements MutationBuilder {

  @Autowired
  private MoviesRepository moviesRepository;

  @Autowired
  private GenreRepository genreRepository;

  @Autowired
  private ActorsRepository actorsRepository;

  @Override
  public void build(GraphQLObjectType.Builder builder) {
    builder.field(Fields.field("addMovie")
            .argument(Arguments.notNull(Arguments.stringArgument("title")))
            .argument(Arguments.stringArgument("synopsis"))
            .argument(Arguments.stringArgument("duration"))
            .argument(Arguments.stringArgument("image_url"))
            .argument(GraphQLArgument.newArgument().name("rating").type(Scalars.GraphQLInt))
            .argument(Arguments.stringArgument("playback"))
            .argument(GraphQLArgument.newArgument().name("genres").type(GraphQLList.list(Scalars.GraphQLString)))
            .argument(GraphQLArgument.newArgument().name("actors").type(GraphQLList.list(new GraphQLInputObjectType("actors", "new actors",
                    Arrays.asList(new GraphQLInputObjectField("name", Scalars.GraphQLString), new GraphQLInputObjectField("role", Scalars.GraphQLString))))))
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
              List<String> genres = env.getArgument("genres");
              if (Objects.nonNull(genres) && !genres.isEmpty()) {
                for (String genre : genres) {
                  genreRepository.save(new Genre(genre, UUID.randomUUID().toString(), movie));
                }
              }

              List<Map<String, String>> actors = env.getArgument("actors");
              if (Objects.nonNull(actors) && !actors.isEmpty()) {
                for (Map<String, String> actor : actors) {
                  String id = UUID.randomUUID().toString();
                  String name = actor.get("name");
                  String role = actor.get("role");
                  actorsRepository.save(new Actor(id, name, role, movie));
                }
              }

              return movie;
            }));
  }
}
