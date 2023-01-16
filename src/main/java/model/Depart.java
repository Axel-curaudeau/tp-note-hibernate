package model;

import jakarta.persistence.*;

import java.util.List;

public class Depart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int IDDepart;

    @Temporal(TemporalType.DATE)
    private String dateDepart;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "depart")
    private List<Personnel> listePersonnel;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "depart")
    private List<Passager> listePassager;

}
