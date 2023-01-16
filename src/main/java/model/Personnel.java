package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int IDPersonnel;

    private String nom;

    private String prenom;

    @ManyToMany(mappedBy = "listePersonnel")
    private List<Depart> listeDepart;
}
