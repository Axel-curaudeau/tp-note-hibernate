package controller;

import jakarta.persistence.*;
import model.*;
import view.View;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
    private final View view;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager em;
    private final EntityTransaction tx;

    public Controller(View view) {
        this.view = view;
        this.entityManagerFactory = Persistence.createEntityManagerFactory("TP9");
        this.em = entityManagerFactory.createEntityManager();
        this.tx = em.getTransaction();

        insertDummyData();
    }

    public void disconnect() {
        em.close();
        entityManagerFactory.close();
    }

    public void commit(Object object) {
        tx.begin();
        em.persist(object);
        tx.commit();
    }

    //Menu Initial
    public Integer affichageEtChoixMenu() {
        HashMap<Integer, String> menuItems = new HashMap<>();
        menuItems.put(0, "Quitter");
        menuItems.put(1, "Afficher...");
        menuItems.put(2, "Ajouter...");
        menuItems.put(3, "Supprimer...");
        menuItems.put(4, "Afficher les détails d'un vol");
        menuItems.put(5, "Associer du personnel à un départ de vol");
        menuItems.put(6, "Afficher toutes les informations du personnel");
        menuItems.put(7, "Afficher tous les vols qui ont pour destination un lieu donné");
        return view.affichageEtChoixString(menuItems);
    }

    public Integer affichafeEtChoixSousMenu() {
        HashMap<Integer, String> menuItems = new HashMap<>();
        menuItems.put(0, "Retour");
        menuItems.put(1, "Vol");
        menuItems.put(2, "Personnel");
        menuItems.put(3, "Départ");
        return view.affichageEtChoixString(menuItems);
    }

    //Fonctionnalité 1
    public void afficherVolsSurUneJournee() {
        LocalDate date = view.getDate();
        if (date == null){
            view.afficherMessageErreur("Date saisie invalide !");
            return;
        }

        List<Depart> departs = recupererToutDepart();
        List<Depart> departsSurDate = new ArrayList<>();

        for (Depart depart : departs) {
            if (LocalDate.parse(depart.getDateDepart()).equals(date)) {
                departsSurDate.add(depart);
            }
        }
        view.afficherListeDepart(departsSurDate);
    }

    //Fonctionnalité 2
    private void associerPersonnelVol() {

        HashMap<Integer, Personnel> personnelItem = new HashMap<>();

        List<Personnel> personnels = recupererToutPersonnel();
        for (Personnel personnel : personnels) {
            personnelItem.put(personnel.getIDPersonnel(), personnel);
        }

        Integer choice = view.affichageEtChoixPersonnel(personnelItem);
        Personnel personnel = (Personnel) personnelItem.get(choice);

        HashMap<Integer, Depart> departItem = new HashMap<>();
        List<Depart> departs = recupererToutDepart();
        for (Depart depart : departs) {
            departItem.put(depart.getIDDepart(), depart);
        }
        Integer choice2 = view.affichageEtChoixDepart(departItem);
        Depart depart = departItem.get(choice2);

        personnel.addDepart(depart);
        depart.addPersonnel(personnel);

        commit(personnel);
        commit(depart);
    }

    //Fonctionnalité 3
    private void afficherInfoPersonnel() {
        view.afficherListePersonnel(recupererToutPersonnel(), recupererToutDepart());
    }

    //Fonctionnalité 4
    public void afficherTousVolsVersLieux() {
        List<Vol> vols = recupererToutVol();

        String destination = view.demanderLieu();

        List<Vol> volsVersDestination = new ArrayList<>();

        for (Vol vol : vols) {
            if (vol.getVilleArrivee().equals(destination)) {
                volsVersDestination.add(vol);
            }
        }

        view.afficherListeVol(volsVersDestination);
    }

    private List<Personnel> recupererToutPersonnel() {
        Query query = em.createQuery("from Personnel");
        return (List<Personnel>) query.getResultList();
    }

    private List<Depart> recupererToutDepart() {
        Query query = em.createQuery("from Depart");
        return (List<Depart>) query.getResultList();
    }

    private List<Vol> recupererToutVol() {
        Query query = em.createQuery("from Vol");
        return (List<Vol>) query.getResultList();
    }

    public void run() {
        Integer choice = affichageEtChoixMenu();
        while (choice != 0){
            switch (choice) {
                case 1 -> {
                    Integer choice2 = affichafeEtChoixSousMenu();
                    switch (choice2) {
                        case 1 -> view.afficherListeVol(recupererToutVol());
                        case 2 -> view.afficherListePersonnel(recupererToutPersonnel(), recupererToutDepart());
                        case 3 -> view.afficherListeDepart(recupererToutDepart());
                    }
                }
                case 4 -> afficherVolsSurUneJournee();
                case 5 -> associerPersonnelVol();
                case 6 -> afficherInfoPersonnel();
                case 7 -> afficherTousVolsVersLieux();
                default -> System.err.println("Invalid choice");
            }
            choice = affichageEtChoixMenu();
        }
        disconnect();
    }

    public void insertDummyData() {
        Volant pilote = new Volant("Jean", "Dupont");
        NonVolant Valiseur = new NonVolant("Charles", "Valise");
        Depart depart = new Depart("2021-05-01");
        Vol vol = new Vol("Paris", "Londres");
        vol.addDepart(depart);
        depart.addVol(vol);
        pilote.addDepart(depart);
        Valiseur.addDepart(depart);
        depart.addPersonnel(pilote);
        depart.addPersonnel(Valiseur);
        commit(pilote);
        commit(Valiseur);
        commit(depart);
        commit(vol);
    }
}
