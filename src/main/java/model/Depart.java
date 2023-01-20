package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Depart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int IDDepart;

    @Temporal(TemporalType.DATE)
    private String dateDepart;

    @ManyToOne()
    private Vol vol;

    @ManyToMany()
    private List<Personnel> listePersonnel;

    @ManyToMany()
    private List<Passager> listePassager;

    public Depart() {
        this.listePersonnel = new ArrayList<>();
        this.listePassager = new ArrayList<>();
    }

    public Depart(LocalDate dateDepart, Vol vol) {
        this.dateDepart = dateDepart.toString();
        this.vol = vol;
        listePersonnel = new ArrayList<Personnel>();
        listePassager = new ArrayList<Passager>();
    }

    public Depart(String dateDepart, Vol vol) {
        this.dateDepart = dateDepart;
        this.vol = vol;
        listePersonnel = new ArrayList<Personnel>();
        listePassager = new ArrayList<Passager>();
    }



    public Integer getIDDepart() {
        return IDDepart;
    }

    public Integer addPersonnel(Personnel personnel) {
        Integer compteurVolant = 0;
        for (Personnel p : listePersonnel) {
            if (p instanceof Volant) {
                compteurVolant++;
            }
        }
        if (compteurVolant == 6) {
            return 1;
        }
        if (listePersonnel.contains(personnel)) {
            return 2;
        }
        listePersonnel.add(personnel);
        return 0;
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
