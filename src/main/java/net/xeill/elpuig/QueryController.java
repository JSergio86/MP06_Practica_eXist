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

    /**
     * Este método permite seleccionar un texto concreto de los archivos XML y mostrar los resultados en la consola.
     *
     * @throws XQException si hay un error en la ejecución de la consulta XQuery.
     */
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


    /**
     * Este método permite seleccionar registros de los archivos XML basados en una condición dada por el usuario y mostrar los resultados en la consola.
     *
     * @throws XQException si hay un error en la ejecución de la consulta XQuery.
     */
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

            case 2:
                System.out.println("Escriba el las wins mínimas que desea listar:");
                int minIDMap = sc.nextInt();

                XQResultSequence xqrs1Map = ec.executeQuery("for $match in /doc('/db/foaf/foaf/mapas.xml')/matches/match[./wins > " + minIDMap + "] return $match");
                ec.printResultSequence(xqrs1Map);
                break;
            case 3:
                System.out.println("Escriba el ID mínimo que desea listar:");
                int minIDWeapon = sc.nextInt();

                XQResultSequence xqrs1Weapon = ec.executeQuery("for $weapon in //weapon[@id > " + minIDWeapon + "] return $weapon");
                ec.printResultSequence(xqrs1Weapon);
                break;


        }
    }


    /**
     * Este método permite seleccionar un registro concreto de los archivos XML y mostrar los resultados en la consola.
     *
     * @throws XQException si hay un error en la ejecución de la consulta XQuery.
     */
    public void seleccionarConcreto(){
        System.out.println("Elige el fichero que desea listar. 1.Jugadores, 2.Mapas, 3.Armas");
        int opcion = sc.nextInt();
        switch (opcion){
            case 1:
                System.out.println("Escribe el ID del jugador que desea listar");
                String idJugador = sc.next();
                XQResultSequence xqrs1 = ec.executeQuery("for $player in /doc('/db/foaf/foaf/jugadores.xml')/players/player[@id='"+idJugador+"'] return $player");
                ec.printResultSequence(xqrs1);
                break;

            case 2:
                System.out.println("Escribe el número de victorias (wins) del mapa que desea listar");
                String numVictorias = sc.next();
                XQResultSequence xqrsListMapa = ec.executeQuery("for $match in /doc('/db/foaf/foaf/mapas.xml')/matches/match[wins='"+numVictorias+"'] return $match");
                ec.printResultSequence(xqrsListMapa);
                break;

            case 3:
                System.out.println("Escribe el ID del arma que desea listar");
                String idArma = sc.next();
                XQResultSequence result = ec.executeQuery("//weapon[@id='"+idArma+"']");
                ec.printResultSequence(result);
                break;
        }
    }

    /**
     * Este método permite modificar un registro específico en el archivo XML correspondiente.
     *
     * @throws XQException si hay un error en la ejecución de la consulta XQuery.
     */
    public void modificarRegistro() throws XQException {
        System.out.println("ID del usuario que quieres modificar");
        int id = sc.nextInt();
        XQExpression xqe = ec.getConnection().createExpression();
        String cad4 = "update value \n" + "doc('/db/foaf/foaf/jugadores.xml')/players/player[id = " + id + "]/rank \n+with 'Plata3'";
        xqe.executeCommand(cad4);
    }


    /**
     * Este método permite eliminar un registro específico en el archivo XML correspondiente.
     *
     * @throws XQException si hay un error en la ejecución de la consulta XQuery.
     */
    public void eliminarRegistro() throws XQException {
        System.out.println("ID del usuario que quieres eliminar");
        int id = sc.nextInt();
        XQExpression xqe = ec.getConnection().createExpression();
        String cad4 = "update delete \n" + "doc('/db/foaf/foaf/jugadores.xml')/players/player[id = " + id + "]";
        xqe.executeCommand(cad4);
    }

    /**
     * Este método permite eliminar un conjunto de registros en el archivo XML correspondiente.
     *
     * @throws XQException si hay un error en la ejecución de la consulta XQuery.
     */
    public void eliminarConjunto() throws XQException {
        System.out.println("Ingrese la puntuación mínima de los jugadores que desea eliminar:");
        int minPuntuacion = sc.nextInt();

        XQExpression xqe = ec.getConnection().createExpression();
        String cad4 = "update delete \n" + "doc('/db/foaf/foaf/jugadores.xml')/players/player[puntuacion < " + minPuntuacion + "]";
        xqe.executeCommand(cad4);
    }

}

