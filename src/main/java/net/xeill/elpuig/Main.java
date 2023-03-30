package net.xeill.elpuig;


import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;

public class Main {
    public static void main(String[] args) throws XQException {

        Menu menu = new Menu();
        int option = menu.mainMenu();
        while (option > 0 && option < 4) {
            switch (option) {
                case 1:
                    ExistController ec = new ExistController();
                    XQResultSequence xqrs = ec.executeQuery("for $weapon in /doc('/db/foaf/foaf/weapons.xml') return $weapon/@name");
                    ec.printResultSequence(xqrs);
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;


                default:
                    System.out.println("Introdueixi una de les opcions anteriors");
                    break;

            }
            option = menu.mainMenu();
        }


    }
}

