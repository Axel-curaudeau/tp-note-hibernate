package controller;

import view.View;

import java.util.HashMap;

public class Controller {
    private View view;
    public Controller(View view) {
        this.view = view;
    }

    public Integer affichageEtChoixMenu() {
        HashMap<Integer, String> menuItems = new HashMap<>();
        menuItems.put(0, "Exit");
        menuItems.put(1, "Add");
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
                    System.err.println("Add");
                    break;
                case 2:
                    System.err.println("List");
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
}
