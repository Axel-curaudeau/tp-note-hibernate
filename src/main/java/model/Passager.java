package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Passager {
    @Id
    private int IDPassager;

    private String nom;

    private String prenom;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "listePassager")
    private List<Depart> ListeDepart;
}
