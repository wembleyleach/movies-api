package com.example.demo;

import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.TypeBuilder;
import com.creactiviti.spring.boot.starter.graphql.Types;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieType implements TypeBuilder {
    @Autowired
    private ActorsRepository actorsRepository;

    public static final String NAME = "Movie";
    public static final GraphQLTypeReference REF = Types.ref(NAME);

    @Override
    public GraphQLType build() {
        return Types.objectTypeBuilder()
                .name(NAME)
                .field(Fields.notNull(Fields.stringField("id")))
                .field(Fields.stringField("title"))
                .field(Fields.stringField("synopsis"))
                .field(Fields.stringField("duration"))
                .field(Fields.field("cast").type(Types.list(ActorType.REF)).dataFetcher(env -> {
                    Movie movie = env.getSource();
                    return actorsRepository.findByMovieId(movie.getId());
                }))
                .build();
    }
}
