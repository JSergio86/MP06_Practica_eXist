package net.xeill.elpuig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private int option;

    public Menu() {
        super();
    }

    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println(" \nMENU PRINCIPAL \n");
            System.out.println("1. Listar elementos con un texto concreto");
            System.out.println("2. Listar elementos concretos");
            System.out.println("3. Listar con condición");
            System.out.println("4. Modificar registro");
            System.out.println("5. Eliminar un registro");
            System.out.println("6. Eliminar un conjunto");
            System.out.println("7. Sortir");
            System.out.println("Escoje una opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Valor no valido");
                e.printStackTrace();

            }

        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7);

        return option;
    }
}
