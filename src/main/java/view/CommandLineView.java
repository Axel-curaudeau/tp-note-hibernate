package view;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class CommandLineView implements View {

    public Integer affichageEtChoixMenu(HashMap<Integer, String> menuItems) {
        System.out.println("*--------------- MENU ---------------*");
        for (Integer key : menuItems.keySet()) {
            System.out.println(key + " - " + menuItems.get(key));
        }
        System.out.println("*------------------------------------*");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public LocalDate getDate() {
        System.out.print("Entrez une date (format: dd-mm-yyyy):");
        // TODO Récupérer la date saisie par l'utilisateur
        return null;
    }


}
