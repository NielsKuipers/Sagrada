package dev.nielskuipers.sagrada.model;

import org.springframework.data.annotation.Id;

public class ObjectiveCard {
    @Id
    private Long id;
    private String name;
    private String description;
    private String type;
    private String config;

    public ObjectiveCard(String name, String description, String type, String config) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getConfig() {
        return config;
    }
}
