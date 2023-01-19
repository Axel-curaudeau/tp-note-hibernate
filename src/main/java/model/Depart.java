package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Depart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int IDDepart;

    @Temporal(TemporalType.DATE)
    private String dateDepart;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vol vol;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Personnel> listePersonnel;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Passager> listePassager;

    public Depart(String dateDepart) {
        this.dateDepart = dateDepart;
        listePersonnel = new ArrayList<Personnel>();
        listePassager = new ArrayList<Passager>();
    }

    public Integer getIDDepart() {
        return IDDepart;
    }

    public void addPersonnel(Personnel personnel) {
        listePersonnel.add(personnel);
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void addVol(Vol vol) {
        this.vol = vol;
    }

    public Vol getVol() {
        return vol;
    }

    public List<Personnel> getListePersonnel() {
        return listePersonnel;
    }
}
