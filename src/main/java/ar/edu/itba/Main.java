package ar.edu.itba;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.ObjectRowProcessor;
import com.univocity.parsers.conversions.Conversions;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        JSONParser jsonParser = new JSONParser();
        String path = "./config.json";

        TsvParserSettings settings = new TsvParserSettings();
        ObjectRowProcessor rowProcessor = new ObjectRowProcessor() {
            @Override
            public void rowProcessed(Object[] row, ParsingContext context) {
                //here is the row. Let's just print it.
                System.out.println(Arrays.toString(row));
            }
        };
        rowProcessor.convertIndexes(Conversions.toBigDecimal()).set(2, 5);
        rowProcessor.convertFields(Conversions.trim(), Conversions.toLowerCase()).set("Description", "Model");
        settings.setRowProcessor(rowProcessor);

        TsvParser parser = new TsvParser(settings);
        try {
            parser.parse(new FileReader("./src/main/Resources/armas.tsv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
