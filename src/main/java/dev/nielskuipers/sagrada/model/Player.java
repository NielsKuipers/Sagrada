package dev.nielskuipers.sagrada.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {
    @Id
    private int id;
    private String name;
}
