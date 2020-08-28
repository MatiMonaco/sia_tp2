package ar.edu.itba;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitnessChartMatrix {
    private SwingWrapper<XYChart> sw;
    private Map<String,XYChart> charts;

    public FitnessChartMatrix() {
        charts = new HashMap<>();
    }

    public void addChart(String name,String xTitle,double xMin,String yTitle,double yMin){
        XYChart chart = new XYChartBuilder().xAxisTitle(xTitle).yAxisTitle(yTitle).width(600).height(400).build();
        chart.getStyler().setYAxisMin((double) 0);
        chart.addSeries(name, new double[]{0}, new double[]{0});
        charts.put(name,chart);
    }

    public void updateSeries(String name, List<Double> xData,List<Double>yData){
        charts.get(name).updateXYSeries(name,xData,yData,null);
    }

    public void displayChartMatrix(){
        List<XYChart> list = new ArrayList<>();
        charts.values().forEach(value-> list.add(value));

        sw = new SwingWrapper<>(list);
        sw.displayChartMatrix();
    }

    public void repaint(){
       for(int i =0 ; i < charts.values().size();i++){
           sw.repaintChart(i);
       }
    }
}
