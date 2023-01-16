package view;

import java.time.LocalDate;
import java.util.HashMap;

public interface View {
    Integer affichageEtChoixMenu(HashMap<Integer, String> menuItems);

    LocalDate getDate();
}
