package controller;

import view.CommandLineView;
import view.View;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Controller {
    private View view;
    public Controller(View view) {
        this.view = view;
    }

    public Integer showAndChoiceMenu() {
        HashMap<Integer, String> menuItems = new HashMap<>();
        menuItems.put(0, "Exit");
        menuItems.put(1, "Afficher les détails d'un vol");
        menuItems.put(2, "List");
        menuItems.put(3, "Modify");
        menuItems.put(4, "Delete");
        return view.showAndChoice(menuItems);
    }

    public void run() {
        Integer choice = showAndChoiceMenu();
        while (choice != 0){
            switch (choice){
                case 1:
                    LocalDate date = View.AfficherVolsSurUneJournee();
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
            choice = showAndChoiceMenu();
        }
    }
}
