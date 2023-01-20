package model;

import jakarta.persistence.Entity;

@Entity
public class NonVolant extends Personnel {

    public NonVolant() {
        super();
    }

    public NonVolant(String nom, String prenom) {
        super(nom, prenom);
    }
}
