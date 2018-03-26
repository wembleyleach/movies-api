package com.example.demo;

import com.creactiviti.spring.boot.starter.graphql.Arguments;
import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.MutationBuilder;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddMovie implements MutationBuilder {

    public static final int COUNT = 16;
    @Autowired
    private MoviesRepository moviesRepository;

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String randomAlphanumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHANUMERIC_STRING.length());
            builder.append(ALPHANUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @Override
    public void build(GraphQLObjectType.Builder builder) {
        builder.field(Fields.field("addMovie")
                .argument(Arguments.notNull(Arguments.stringArgument("title")))
                .argument(Arguments.stringArgument("synopsis"))
                .argument(Arguments.stringArgument("duration"))
                .argument(Arguments.stringArgument("image_url"))
                .type(MovieType.REF)
                .dataFetcher(env -> {
                    String id = randomAlphanumeric(COUNT);
                    Movie movie = new Movie(id,
                            env.getArgument("title"),
                            env.getArgument("synopsis"),
                            env.getArgument("duration"),
                            env.getArgument("image_url"));
                    return moviesRepository.save(movie);
                }));
    }
}
