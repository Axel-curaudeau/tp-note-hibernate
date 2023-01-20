package model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Vol {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int IDVol;

    private String villeDepart;

    private String villeArrivee;

    @OneToMany(mappedBy = "vol")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Depart> listeDepart;

    public Vol() {
        listeDepart = new ArrayList<Depart>();
    }

    public Vol(String villeDepart, String villeArrivee) {
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        listeDepart = new ArrayList<Depart>();
    }

    public Integer getIDVol() {
        return IDVol;
    }

    public void addDepart(Depart depart) {
        this.listeDepart.add(depart);
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }
}
