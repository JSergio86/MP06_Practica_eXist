package net.xeill.elpuig;

import javax.xml.xquery.XQResultSequence;
import java.util.Scanner;

public class QueryController {
    Scanner sc = new Scanner(System.in);
    ExistController ec = new ExistController();

    public void listar(){
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
                XQResultSequence xqrsListArmas = ec.executeQuery("for $group in /doc('/db/foaf/foaf/weapons.xml')/catalog/group return $group");
                ec.printResultSequence(xqrsListArmas);

                System.out.println("\nEscribe el texto concreto que desea listar");
                String textoArma = sc.next();
                XQResultSequence xqrsArma= ec.executeQuery("for $group in /doc('/db/foaf/foaf/weapons.xml')/catalog/group return $group/"+textoArma);
                ec.printResultSequence(xqrsArma);
                break;
        }
    }
    //XQResultSequence xqrs = ec.executeQuery("for $player in /doc('/db/foaf/foaf/jugadores.xml')/players/player return $player");

}
