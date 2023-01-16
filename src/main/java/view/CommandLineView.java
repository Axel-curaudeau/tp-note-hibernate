package view;

import java.util.HashMap;
import java.util.Scanner;

public class CommandLineView implements View {

    @Override
    public Integer showAndChoice(HashMap<Integer, String> menuItems) {
        System.out.println("*--------------- MENU ---------------*");
        for (Integer key : menuItems.keySet()) {
            System.out.println(key + " - " + menuItems.get(key));
        }
        System.out.println("*------------------------------------*");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
