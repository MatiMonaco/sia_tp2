package ar.edu.itba;

import ar.edu.itba.classes.Character;
import ar.edu.itba.classes.CharacterType;
import ar.edu.itba.convergences.Convergence;
import ar.edu.itba.convergences.GenerationQuantityConvergence;
import ar.edu.itba.convergences.TimeConvergence;
import ar.edu.itba.selections.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.knowm.xchart.style.markers.Cross;

import javax.xml.crypto.Data;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        JSONParser jsonParser = new JSONParser();
        String path = "./config.json";
        Database.load();

        try (Reader reader = new FileReader(path)) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            System.out.println(jsonObject);

            String crossing = (String) jsonObject.get("crossing");
            if(crossing == null){
                System.out.println("Se debe especificar el cruce a utilizar");
            }
            String mutation = (String) jsonObject.get("mutation");
            if(mutation == null){
                System.out.println("Se debe especificar el mutación a utilizar");
            }
            String selection = (String) jsonObject.get("selection");
            if(selection == null){
                System.out.println("Se debe especificar la seleccion de padres y reemplazo de individuos a utilizar");
            }
            String implementation = (String) jsonObject.get("implementation");
            if(implementation == null){
                System.out.println("Se debe especificar la implementación a utilizar");
            }
            String cut = (String) jsonObject.get("cut");
            if(cut == null){
                System.out.println("Se debe especificar el criterio de corte a utilizar");
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


        GeneticAlgorithm fillAll = new FillAll(100,50,new DeterministicTournament(4),new Elite(),new DeterministicTournament(4),new Elite(),
                100,0.7,0.5,0.5, new TimeConvergence(30,System.currentTimeMillis()/1000),
                Crossing::annularCrossing,Mutation::genMutation,CharacterType.WARRIOR);

        fillAll.start();
    }

}
