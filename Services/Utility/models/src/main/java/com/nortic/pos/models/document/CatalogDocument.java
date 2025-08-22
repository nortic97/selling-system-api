package com.nortic.pos.models.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "catalogs")
@TypeAlias("CatalogDocument")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogDocument {

    @Id
    private String id;

    private String type;

    private String code;

    private String name;

    private String description;

    private Map<String, Object> attributes;
}
