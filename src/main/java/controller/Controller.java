package controller;

import jakarta.persistence.*;
import model.*;
import view.View;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
    private View view;

    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;
    private EntityTransaction tx;
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

    public Integer affichageEtChoixMenu() {
        HashMap<Integer, Object> menuItems = new HashMap<>();
        menuItems.put(0, "Quitter");
        menuItems.put(1, "Afficher les détails d'un vol");
        menuItems.put(2, "Associer du personnel à un départ de vol");
        menuItems.put(3, "Modify");
        menuItems.put(4, "Delete");
        return view.affichageEtChoixMenu(menuItems);
    }

    public void afficherVolsSurUneJournee() {
        LocalDate date = view.getDate();
        if (date == null){
            view.afficherMessageErreur("Date saisie invalide !");
            return;
        }
        List<Depart> departs = em.createQuery("from Depart").getResultList();
        view.afficherListeDepart(departs);
        if (departs.isEmpty()) {
            view.afficherMessage("Aucun vol n'est enregistré dans la base de données.");
            return;
        }

        List<Depart> departsSurDate = new ArrayList<Depart>();

        for (Depart depart : departs) {
            Depart departTemporaire = (Depart) depart;
            if (LocalDate.parse(departTemporaire.getDateDepart()) == date) {
                departsSurDate.add(depart);
            }
        }
        //TODO: Vérifier si l'affichage est correcte
        view.afficherListeDepart(departsSurDate);
    }

    public void run() {
        Integer choice = affichageEtChoixMenu();
        while (choice != 0){
            switch (choice) {
                case 1 -> afficherVolsSurUneJournee();
                case 2 -> associerPersonnelVol();
                case 3 -> afficherInfoPersonnel();
                case 4 -> System.err.println("Delete");
                default -> System.err.println("Invalid choice");
            }
            choice = affichageEtChoixMenu();
        }
    }

    private List<Personnel> recupererToutPersonnel() {
        Query query = em.createQuery("from Personnel");
        return (List<Personnel>) query.getResultList();
    }

    private List<Depart> recupererToutDepart() {
        Query query = em.createQuery("from Depart");
        return (List<Depart>) query.getResultList();
    }

    private void associerPersonnelVol() {

        HashMap<Integer, Object> menuItems = new HashMap<>();

        List<Personnel> personnels = recupererToutPersonnel();
        for (Personnel personnel : personnels) {
            menuItems.put(personnel.getIDPersonnel(), personnel);
        }

        Integer choice = view.affichageEtChoixMenu(menuItems);
        Personnel personnel = (Personnel) menuItems.get(choice);

        menuItems = new HashMap<>();
        List<Depart> departs = recupererToutDepart();
        for (Depart depart : departs) {
            menuItems.put(depart.getIDDepart(), depart);
        }
        Integer choice2 = view.affichageEtChoixMenu(menuItems);
        Depart depart = (Depart) menuItems.get(choice2);

        personnel.addDepart(depart);
        depart.addPersonnel(personnel);

        commit(personnel);
        commit(depart);
    }

    private void afficherInfoPersonnel() {
        view.afficherListePersonnel(recupererToutPersonnel());
    }


    public void insertDummyData() {
        Volant pilote = new Volant("jean", "Dupont");
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
