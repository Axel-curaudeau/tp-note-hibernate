package model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Personnel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int IDPersonnel;

    private String nom;

    private String prenom;

    @ManyToMany(mappedBy = "listePersonnel")
    private List<Depart> listeDepart;

    protected Personnel(String prenom, String nom) {
        this.nom = nom;
        this.prenom = prenom;
        listeDepart = new ArrayList<Depart>();
    }

    public Personnel() {
        listeDepart = new ArrayList<Depart>();
    }

    public Integer getIDPersonnel() {
        return IDPersonnel;
    }

    public void addDepart(Depart depart) {
        listeDepart.add(depart);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public List<Depart> getListeDepart() {
        return listeDepart;
    }
}
