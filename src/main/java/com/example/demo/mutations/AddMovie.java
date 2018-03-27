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
import graphql.schema.GraphQLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddMovie implements MutationBuilder {

    @Autowired
    private MoviesRepository moviesRepository;

    @Override
    public void build(GraphQLObjectType.Builder builder) {
        builder.field(Fields.field("addMovie")
                .argument(Arguments.notNull(Arguments.stringArgument("title")))
                .argument(Arguments.stringArgument("synopsis"))
                .argument(Arguments.stringArgument("duration"))
                .argument(Arguments.stringArgument("image_url"))
                .argument(GraphQLArgument.newArgument().name("rating").type(Scalars.GraphQLInt))
                .type(MovieType.REF)
                .dataFetcher(env -> {
                    Movie movie = new Movie(UUID.randomUUID().toString(),
                            env.getArgument("title"),
                            env.getArgument("synopsis"),
                            env.getArgument("duration"),
                            env.getArgument("image_url"),
                            env.getArgument("rating"));
                    return moviesRepository.save(movie);
                }));
    }
}
