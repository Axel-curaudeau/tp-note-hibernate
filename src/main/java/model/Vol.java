package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vol {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int IDVol;

    private String villeDepart;

    private String villeArrivee;

    @OneToMany(mappedBy = "vol")
    private List<Depart> depart;

    public Integer getIDVol() {
        return IDVol;
    }
}
