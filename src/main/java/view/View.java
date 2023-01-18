package view;

import model.Depart;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface View {
    Integer affichageEtChoixMenu(HashMap<Integer, Object> menuItems);

    LocalDate getDate();


    void afficherMessageErreur(String s);

    void afficherMessage(String s);

    void afficherListeDepart(List<Depart> departs);
}
