package view;

import java.time.LocalDate;
import java.util.HashMap;

public interface View {
    Integer affichageEtChoixMenu(HashMap<Integer, Object> menuItems);

    LocalDate getDate();
}
