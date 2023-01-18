package model;

import jakarta.persistence.*;

import java.util.ArrayList;
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

    public Personnel(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        listeDepart = new ArrayList<Depart>();
    }

    public Integer getIDPersonnel() {
        return IDPersonnel;
    }

    public void addDepart(Depart depart) {
        listeDepart.add(depart);
    }
}
