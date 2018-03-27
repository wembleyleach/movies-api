package com.example.demo.mutations;

import com.creactiviti.spring.boot.starter.graphql.Arguments;
import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.MutationBuilder;
import com.example.demo.entities.Movie;
import com.example.demo.repositories.MoviesRepository;
import com.example.demo.types.MovieType;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateMovie implements MutationBuilder {
    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public void build(GraphQLObjectType.Builder builder) {
        builder.field(Fields.field("rateMovie")
                .argument(Arguments.notNull(GraphQLArgument.newArgument()
                        .name("rating")
                        .type(Scalars.GraphQLInt)))
                .argument(Arguments.notNull(Arguments.stringArgument("id")))
                .type(MovieType.REF)
                .dataFetcher(env -> {
                    int rating = env.getArgument("rating");
                    if (!(rating > 0 && rating <= 5)) {
                        throw new IllegalArgumentException("Rating must be from 1 to 5");
                    }
                    Movie movie = moviesRepository.findById(env.getArgument("id"))
                            .orElseThrow(IllegalArgumentException::new);
                    movie.setRating(rating);
                    return moviesRepository.save(movie);
                }));
    }
}
