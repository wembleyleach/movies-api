package com.example.demo.queries;

import com.creactiviti.spring.boot.starter.graphql.Arguments;
import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.QueryBuilder;
import com.creactiviti.spring.boot.starter.graphql.Types;
import com.example.demo.repositories.MoviesRepository;
import com.example.demo.types.MovieType;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMovies implements QueryBuilder {
    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public void build(GraphQLObjectType.Builder builder) {
        builder.field(Fields.field("getMovies")
                .type(Types.list(MovieType.REF))
                .argument(Arguments.stringArgument("q"))
                .dataFetcher(env -> moviesRepository.findByTitleContainingIgnoreCase(env.getArgument("q"))));
    }
}
