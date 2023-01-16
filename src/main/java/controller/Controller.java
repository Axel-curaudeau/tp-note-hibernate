package controller;

import jakarta.persistence.*;
import view.View;
import java.time.LocalDate;
import java.util.HashMap;

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
        HashMap<Integer, String> menuItems = new HashMap<>();
        menuItems.put(0, "Exit");
        menuItems.put(1, "Afficher les détails d'un vol");
        menuItems.put(2, "List");
        menuItems.put(3, "Modify");
        menuItems.put(4, "Delete");
        return view.affichageEtChoixMenu(menuItems);
    }

    public void run() {
        Integer choice = affichageEtChoixMenu();
        while (choice != 0){
            switch (choice){
                case 1:
                    LocalDate date = View.afficherVolsSurUneJournee();
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

    private void associerPersonnelVol() {
        View.afficherPersonnel();
    }
}
