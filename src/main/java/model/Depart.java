package model;

import jakarta.persistence.*;

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

    public Integer getIDDepart() {
        return IDDepart;
    }

    public void addPersonnel(Personnel personnel) {
        listePersonnel.add(personnel);
    }
}
