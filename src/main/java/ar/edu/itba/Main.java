package ar.edu.itba;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import java.util.List;

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

        FitnessChartMatrix fcm = new FitnessChartMatrix();
        fcm.addChart("minFitness","Generation",0,"Minimum Fitness",0);
        fcm.addChart("maxFitness","Generation",0,"Maximum Fitness",0);
        fcm.addChart("avgFitness","Generation",0,"Average Fitness",0);
        fcm.addChart("divGen","Generation",0,"Genetic Divergence",0);
        fcm.displayChartMatrix();

        double j = 0;
       List<Double> dataX, dataY;
       dataX = new ArrayList<>();
       dataY = new ArrayList<>();
        while (j < 100) {
            dataX.add(j);
            dataY.add(j);
            Thread.sleep(50);

            fcm.updateSeries("minFitness",dataX,dataY);
            fcm.updateSeries("maxFitness",dataX,dataY);
            fcm.updateSeries("avgFitness",dataX,dataY);
            fcm.updateSeries("divGen",dataX,dataY);

            fcm.repaint();
             j++;
        }



    }

}
