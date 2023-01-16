package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Vol {
    @Id
    private int IDVol;

    private String villeDepart;

    private String villeArrivee;

    @OneToMany(mappedBy = "vol")
    private List<Depart> depart;

}
