package view;

import model.Depart;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface View {
    Integer affichageEtChoixMenu(HashMap<Integer, Object> menuItems);

    LocalDate getDate();

    void afficherListe(List<Object> list);

    void afficherMessageErreur(String s);

    void afficherMessage(String s);
}
