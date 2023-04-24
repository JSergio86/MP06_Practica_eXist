package net.xeill.elpuig;

import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class QueryController {
    Scanner sc = new Scanner(System.in);
    ExistController ec = new ExistController();

    public void seleccionarTextoConcreto(){
        System.out.println("Elige el fichero que desea listar. 1.Jugadores, 2.Mapas, 3.Armas");
        int opcion = sc.nextInt();
        switch (opcion){
            case 1:
                XQResultSequence xqrs1 = ec.executeQuery("for $player in /doc('/db/foaf/foaf/jugadores.xml')/players/player return $player");
                ec.printResultSequence(xqrs1);

                System.out.println("\nEscribe el texto concreto que desea listar");
                String texto = sc.next();
                XQResultSequence xqrs = ec.executeQuery("for $player in /doc('/db/foaf/foaf/jugadores.xml')/players/player return $player/"+texto);
                ec.printResultSequence(xqrs);
                break;

            case 2:
                XQResultSequence xqrsListMapa = ec.executeQuery("for $match in /doc('/db/foaf/foaf/mapas.xml')/matches/match return $match");
                ec.printResultSequence(xqrsListMapa);

                System.out.println("\nEscribe el texto concreto que desea listar");
                String textoMapa = sc.next();
                XQResultSequence xqrsMapa = ec.executeQuery("for $match in /doc('/db/foaf/foaf/mapas.xml')/matches/match return $match/"+textoMapa);
                ec.printResultSequence(xqrsMapa);
                break;

            case 3:
                System.out.println("\nEscribe el texto concreto que desea listar");
                String textoConcretoArmas = sc.next();

                XQResultSequence result = ec.executeQuery("//weapon[contains(@name, '" + textoConcretoArmas + "')]");
                ec.printResultSequence(result);
                break;
        }
    }
    //XQResultSequence xqrs = ec.executeQuery("for $player in /doc('/db/foaf/foaf/jugadores.xml')/players/player return $player");

    public void seleccionarConCondicion(){
        System.out.println("Elige el fichero que desea listar. 1.Jugadores, 2.Mapas, 3.Armas");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Escriba el ID mínimo que desea listar:");
                int minID = sc.nextInt();
                XQResultSequence xqrs1 = ec.executeQuery("for $player in /doc('/db/foaf/foaf/jugadores.xml')/players/player[./id > " + minID + "] return $player");
                ec.printResultSequence(xqrs1);
                break;
        }
    }

    public void eliminarRegistro() throws XQException {
        System.out.println("ID del usuario que quieres eliminar");
        int id = sc.nextInt();
        XQExpression xqe = ec.getConnection().createExpression();
        String cad4 = "update delete \n" + "doc('/db/foaf/foaf/jugadores.xml')/players/player[id = " + id + "]";
        xqe.executeCommand(cad4);
    }




}

