package com.example.demo;

import com.creactiviti.spring.boot.starter.graphql.Fields;
import com.creactiviti.spring.boot.starter.graphql.TypeBuilder;
import com.creactiviti.spring.boot.starter.graphql.Types;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;
import org.springframework.stereotype.Component;

@Component
public class Image implements TypeBuilder {
    public static final String NAME = "Image";
    public static final GraphQLTypeReference REF = Types.ref(NAME);

    @Override
    public GraphQLType build() {
        return Types.objectTypeBuilder()
                .name(NAME)
                .field(Fields.stringField("url"))
                .build();
    }
}
