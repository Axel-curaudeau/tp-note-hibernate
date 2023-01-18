package model;

import jakarta.persistence.Entity;

@Entity
public class Volant extends Personnel {

    public Volant(String nom, String prenom) {
        super(nom, prenom);
    }
}
