package com.example.demo.queries;

import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.QueryBuilder;
import com.creactiviti.spring.boot.starter.graphql.Types;
import com.example.demo.repositories.MoviesRepository;
import com.example.demo.types.MovieType;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMoviesByRating implements QueryBuilder {
    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public void build(GraphQLObjectType.Builder builder) {
        builder.field(Fields.field("getMoviesByRating")
                .type(Types.list(MovieType.REF))
                .argument(GraphQLArgument.newArgument()
                        .name("rating")
                        .type(Scalars.GraphQLInt))
                .dataFetcher(env -> {
                    int rating = env.getArgument("rating");
                    if (!(rating > 0 && rating <= 5)) {
                        throw new IllegalArgumentException("Rating must be from 1 to 5");
                    }
                    return moviesRepository.findByRating(rating);
                }));
    }
}
