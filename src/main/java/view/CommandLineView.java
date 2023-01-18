package view;

import model.Depart;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CommandLineView implements View {

    public Integer affichageEtChoixMenu(HashMap<Integer, Object> menuItems) {
        System.out.println("*--------------- MENU ---------------*");
        for (Integer key : menuItems.keySet()) {
            System.out.println(key + " - " + menuItems.get(key));
        }
        System.out.println("*------------------------------------*");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public LocalDate getDate() {
        System.out.print("Entrez une date (format: yyyy-mm-dd):");
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        try {
            date = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException parseException) {
            date = null;
        }
        return date;

    }

    @Override
    public void afficherListeDepart(List<Depart> listeDepart) {
        if (listeDepart.isEmpty()) {
            System.out.println("Aucun Depart correspondant.");
        }
        for (Depart depart : listeDepart) {
            System.out.println(
                    "Départ numéro " +
                    depart.getIDDepart() +
                    " du " + depart.getDateDepart() +
                    " partant de " + depart.getVol().getVilleDepart() +
                    " et allant à " + depart.getVol().getVilleArrivee());
        }
    }

    @Override
    public void afficherMessageErreur(String s) {
        System.err.println(s);
    }

    @Override
    public void afficherMessage(String s) {
        System.out.println(s);
    }


}
