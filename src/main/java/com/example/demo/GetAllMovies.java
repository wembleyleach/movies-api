package com.example.demo;

import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.QueryBuilder;
import com.creactiviti.spring.boot.starter.graphql.Types;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllMovies implements QueryBuilder {
    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public void build(GraphQLObjectType.Builder builder) {
        builder.field(Fields.field("getAllMovies")
                .type(Types.list(MovieType.REF))
                .dataFetcher(env -> moviesRepository.findAll()));
    }
}
