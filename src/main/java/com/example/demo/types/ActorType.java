package com.example.demo.types;

import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.TypeBuilder;
import com.creactiviti.spring.boot.starter.graphql.Types;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;
import org.springframework.stereotype.Component;

@Component
public class ActorType implements TypeBuilder {
    public static final String NAME = "Actor";
    public static final GraphQLTypeReference REF = Types.ref(NAME);

    @Override
    public GraphQLType build() {
        return Types.objectTypeBuilder()
                .name(NAME)
                .field(Fields.notNull(Fields.stringField("id")))
                .field(Fields.stringField("role"))
                .field(Fields.stringField("name"))
                .build();
    }
}