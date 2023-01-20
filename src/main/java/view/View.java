package view;

import model.Depart;
import model.Personnel;
import model.Vol;

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

    void afficherListePersonnel(List<Personnel> personnels, List<Depart> departs);

    String demanderString(String message);

    void afficherListeVol(List<Vol> volsVersDestination);

    Vol demanderVol();

    Personnel demanderPersonnel();

    Depart demanderDepart(Vol vol);

    Integer affichageEtChoixVol(HashMap<Integer, Vol> volsItems);
}
