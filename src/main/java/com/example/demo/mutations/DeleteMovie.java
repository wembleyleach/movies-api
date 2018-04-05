package com.example.demo.mutations;

import com.creactiviti.spring.boot.starter.graphql.Arguments;
import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.MutationBuilder;
import com.example.demo.repositories.MoviesRepository;
import com.example.demo.types.MovieType;
import graphql.schema.GraphQLObjectType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DeleteMovie implements MutationBuilder {

  @Autowired
  private MoviesRepository moviesRepository;

  @Override
  public void build(GraphQLObjectType.Builder builder) {
    builder.field(Fields.field("deleteMovie")
            .argument(Arguments.notNull(Arguments.stringArgument("movieId")))
            .type(MovieType.REF)
            .dataFetcher(env -> {
              String movieId = env.getArgument("movieId");
              if (StringUtils.isBlank(movieId)) {
                throw new IllegalArgumentException("movieId not provided");
              }
              moviesRepository.deleteById(movieId);
              return Collections.singletonMap("id", movieId);
            }));
  }

}
