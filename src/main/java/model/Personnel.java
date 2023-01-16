package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Personnel {
    @Id
    private int IDPersonnel;

    private String nom;

    private String prenom;

    @ManyToMany(mappedBy = "listePersonnel")
    private List<Depart> listeDepart;
}
