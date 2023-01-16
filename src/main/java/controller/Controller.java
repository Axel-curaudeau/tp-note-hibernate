package controller;

import jakarta.persistence.*;
import model.Depart;
import model.Personnel;
import model.Vol;
import view.View;
import java.time.LocalDate;
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
        menuItems.put(2, "List");
        menuItems.put(3, "Modify");
        menuItems.put(4, "Delete");
        return view.affichageEtChoixMenu(menuItems);
    }

    public void afficherVolsSurUneJournee() {
        LocalDate date = view.getDate();

        // TODO Récupérer les vols de cette date
        // TODO Afficher les vols à la vue
    }

    public void run() {
        Integer choice = affichageEtChoixMenu();
        while (choice != 0){
            switch (choice){
                case 1:
                    afficherVolsSurUneJournee();
                    break;
                case 2:
                    associerPersonnelVol();
                    break;
                case 3:
                    System.err.println("Modify");
                    break;
                case 4:
                    System.err.println("Delete");
                    break;
                default:
                    System.err.println("Invalid choice");
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
    }
}
