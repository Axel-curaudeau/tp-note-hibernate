package view;

import model.Depart;
import model.Personnel;

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
        for (Depart depart : listeDepart) {
            System.out.println(depart.getIDDepart() + " - " + depart.getDateDepart() + " - " + depart.getVol().getVilleDepart() + " - " + depart.getVol().getVilleArrivee());
        }
    }

    @Override
    public void afficherListePersonnel(List<Personnel> personnels) {
        for (Personnel personnel : personnels) {
            System.out.println(personnel.getIDPersonnel() + " - " + personnel.getNom() + " - " + personnel.getPrenom());
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
