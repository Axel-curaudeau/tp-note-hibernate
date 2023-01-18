package view;

import model.Depart;
import model.Personnel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface View {
    Integer affichageEtChoixDepart(HashMap<Integer, Depart> menuItems);

    LocalDate getDate();


    void afficherMessageErreur(String s);

    void afficherMessage(String s);

    void afficherListeDepart(List<Depart> departs);

    Integer affichageEtChoixString(HashMap<Integer, String> menuItems);

    Integer affichageEtChoixPersonnel(HashMap<Integer, Personnel> personnelItem);

}
