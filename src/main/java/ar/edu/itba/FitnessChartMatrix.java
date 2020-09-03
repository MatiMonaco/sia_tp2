package ar.edu.itba;

import ar.edu.itba.classes.Character;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class FitnessChartMatrix {
    private SwingWrapper<XYChart> sw;
    private Map<String,XYChart> charts;

    public FitnessChartMatrix() {
        charts = new HashMap<>();
    }

    public void addChart(String name,String xTitle,double xMin,String yTitle,double yMin){
        XYChart chart = new XYChartBuilder().xAxisTitle(xTitle).yAxisTitle(yTitle).width(600).height(400).build();
        chart.getStyler().setYAxisMin((double) 0);


        XYSeries series = chart.addSeries(name, new double[]{0}, new double[]{0});
        series.setLineColor(XChartSeriesColors.BLUE);
        series.setMarkerColor(Color.BLUE);
        series.setMarker(SeriesMarkers.CROSS);
        series.setLineStyle(SeriesLines.DOT_DOT);
        charts.put(name,chart);
    }

    public void updateSeries(String name, List<Double> xData,List<Double>yData){
        charts.get(name).updateXYSeries(name,xData,yData,null);
    }

    public JFrame displayChartMatrix(){
        List<XYChart> list = new ArrayList<>();
        charts.values().forEach(value-> list.add(value));
        System.out.println("Chart list:"+list);
        sw = new SwingWrapper<>(list);
        JFrame frame = sw.displayChartMatrix();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_R) {
                  //run again
                    System.out.println("R");
                }

            }
        });
        System.out.println(frame.getContentPane().getLayout());
        return frame;
    }

    public void repaint(){
       for(int i =0 ; i < charts.values().size();i++){
           sw.repaintChart(i);
       }
    }


}
