package ar.edu.itba;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        JSONParser parser = new JSONParser();
        String path = "./config.json";

        try (Reader reader = new FileReader(path)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
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
        double phase = 0;
        double[][] initdata = {{0},{1}};

        int numCharts = 4;
        List<XYChart> charts = new ArrayList<>();

        for(int i = 0; i < numCharts;i++){
            XYChart chart = new XYChartBuilder().xAxisTitle("Generation").yAxisTitle("Performance").width(600).height(400).build();
            chart.getStyler().setYAxisMin((double) 0);
            chart.addSeries(""+i, initdata[0],initdata[1]);
            charts.add(chart);
        }


        SwingWrapper<XYChart> sw = new SwingWrapper<>(charts);

        sw.displayChartMatrix();


        double j = 0;
       List<Double> dataX, dataY,data2Y;
       dataX = new ArrayList<>();
       dataY = new ArrayList<>();
       data2Y = new ArrayList<>();
        while (j < 100) {
            dataX.add(j);
            dataY.add(j);
            data2Y.add(j/2);
            Thread.sleep(50);

            double[] x = new double[dataX.size()];
            for (int i = 0; i < x.length; i++) {
                x[i] = dataX.get(i);
            }
            double[] y = new double[dataY.size()];
            for (int i = 0; i < y.length; i++) {
                y[i] = dataY.get(i);
            }

            for(int i = 0; i< numCharts;i++){
                charts.get(i).updateXYSeries(""+i, x,y, null);
                sw.repaintChart(i);
            }




            j++;
        }



    }

}
