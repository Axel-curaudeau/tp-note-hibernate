package controller;

import view.View;

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
        menuItems.put(1, "Add");
        menuItems.put(2, "List");
        menuItems.put(3, "Modify");
        menuItems.put(4, "Delete");
        return view.ListAndChoice(menuItems);
    }

    public void run() {
        Integer choice = showAndChoiceMenu();
        while (choice != 0){
            switch (choice){
                case 1:
                    System.out.println("Add");
                    break;
                case 2:
                    System.out.println("List");
                    break;
                case 3:
                    System.out.println("Modify");
                    break;
                case 4:
                    System.out.println("Delete");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            choice = showAndChoiceMenu();
        }
    }
}
