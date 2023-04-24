package net.xeill.elpuig;


import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;

public class Main {
    public static void main(String[] args) throws XQException {
        Menu menu = new Menu();
        QueryController queryController = new QueryController();

        int option = menu.mainMenu();
        while (option > 0 && option < 8) {
            switch (option) {
                case 1:
                   queryController.seleccionarTextoConcreto();
                    break;

                case 2:
                    queryController.seleccionarConCondicion();
                    break;

                case 3:
                    queryController.seleccionarConcreto();
                    break;

                case 4:
                    queryController.modificarRegistro();
                    break;

                case 5:
                    queryController.eliminarRegistro();
                    break;

                case 6:
                    queryController.eliminarConjunto();
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

