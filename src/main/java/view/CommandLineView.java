package view;

import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommandLineView implements View {

    public Integer affichageEtChoixDepart(HashMap<Integer, Depart> menuItems) {
        System.out.println("Veuillez choisir un départ :");
        System.out.println("*--------------- MENU ---------------*");
        for (Integer key : menuItems.keySet()) {
            System.out.println(key + " - " + menuItems.get(key).getDateDepart() + "-" + menuItems.get(key).getDateDepart() + "-" + menuItems.get(key).getVol().getVilleDepart() + "-" + menuItems.get(key).getVol().getVilleArrivee());
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

        System.out.println(" ===================== Informations des Départs =====================");

        for (Depart depart : listeDepart) {
            System.out.println(
                    "    - " +
                    "Départ numéro " +
                    depart.getIDDepart() +
                    " du " + depart.getDateDepart() +
                    " partant de " + depart.getVol().getVilleDepart() +
                    " et allant à " + depart.getVol().getVilleArrivee());
        }

        System.out.println(" =====================================================================");

        System.out.println("Appuyez sur Entré pour continuer.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    @Override
    public Integer affichageEtChoixString(HashMap<Integer, String> menuItems) {
        System.out.println("*------------------------------ MENU ------------------------------*");
        for (Integer key : menuItems.keySet()) {
            System.out.println(key + " - " + menuItems.get(key));
        }
        System.out.println("*------------------------------------------------------------------*");

        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    @Override
    public Integer affichageEtChoixPersonnel(HashMap<Integer, Personnel> personnelItem) {
        System.out.println("Veuillez choisir un personnel :");
        System.out.println("*---------------------- MENU ----------------------*");
        for (Integer key : personnelItem.keySet()) {
            System.out.println(key + " - " + personnelItem.get(key).getNom() + " " + personnelItem.get(key).getPrenom());
        }
        System.out.println("*--------------------------------------------------*");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void afficherListePersonnel(List<Personnel> personnels, List<Depart> departs) {
        System.out.println();
        if (personnels.isEmpty()) {
            System.out.println("Il n'y a aucun personnel.");
            return;
        }

        System.out.println(" ===================== Informations du personnel =====================");

        for (Personnel personnel : personnels) {
            System.out.print("    - " + personnel.getPrenom() + " " + personnel.getNom() + " (" + personnel.getIDPersonnel() + ")");
            if (personnel instanceof  Volant) {
                System.out.println(" (Volant)");
            } else {
                System.out.println(" (Non volant)");
            }

            for (Depart depart : departs) {
                if (depart.getListePersonnel().contains(personnel)) {
                    if (personnel instanceof Volant) {
                        System.out.print("        " + (char) 11153 + " Départ le ");
                    } else {
                        System.out.print("        " + (char) 11153 + " Travaille le ");
                    }
                    System.out.println(depart.getDateDepart() + " sur le vol " + depart.getVol().getVilleDepart() + " " + (char) 8594 + " " + depart.getVol().getVilleArrivee());
                }
            }
        }
        System.out.println(" =====================================================================");
        System.out.println("Appuyez sur Entré pour continuer.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println();
    }

    @Override
    public String demanderString(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void afficherListeVol(List<Vol> volsVersDestination) {
        System.out.println();
        if (volsVersDestination.isEmpty()) {
            System.out.println("Il n'y a aucun vol.");
            return;
        }

        System.out.println(" ======================= Informations des vols =======================");

        for (Vol vol : volsVersDestination) {
            System.out.println("    - " + vol.getVilleDepart() + " " + (char) 8594 + " " + vol.getVilleArrivee() + " (" + vol.getIDVol() + ")");
        }
        System.out.println(" =====================================================================");
        System.out.println("Appuyez sur Entré pour continuer.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println();
    }

    @Override
    public Vol demanderVol() {
        String villeDepart = demanderString("Entrez un lieu: ");
        String villeArrivee = demanderString("Entrez un lieu: ");
        return new Vol(villeDepart, villeArrivee);
    }

    @Override
    public Personnel demanderPersonnel() {
        String nom = demanderString("Entrez un nom: ");
        String prenom = demanderString("Entrez un prénom: ");

        System.out.println("Le personnel est-il volant ?");
        System.out.println("1 - Volant");
        System.out.println("2 - Non volant");
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        if (choix == 1) {
            return new Volant(nom, prenom);
        } else {
            return new NonVolant(nom, prenom);
        }
    }

    @Override
    public Depart demanderDepart(Vol vol) {
        LocalDate dateDepart = getDate();
        return new Depart(dateDepart, vol);
    }

    @Override
    public Integer affichageEtChoixVol(HashMap<Integer, Vol> volsItems) {
        System.out.println("Veuillez choisir un vol :");
        System.out.println("*---------------------- MENU ----------------------*");
        for (Integer key : volsItems.keySet()) {
            System.out.println(key + " - " + volsItems.get(key).getVilleDepart() + " " + (char) 8594 + " " + volsItems.get(key).getVilleArrivee());
        }
        System.out.println("*--------------------------------------------------*");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
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
