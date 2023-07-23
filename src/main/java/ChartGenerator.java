import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ChartGenerator {
    public static void generateChart(Map<String, Integer> keyCounts, String outputFilePath) {
        DefaultCategoryDataset dataset = createDataset(keyCounts); //method to generate chart based on key counts
        JFreeChart chart = createChart(dataset);
        customizeChart(chart);

        saveChartAsPNG(chart, outputFilePath);
    }

    private static DefaultCategoryDataset createDataset(Map<String, Integer> keyCounts) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); //generate dataset based on key counts
        for (Map.Entry<String, Integer> entry : keyCounts.entrySet()) {
            dataset.addValue(entry.getValue(), "Occurrences", entry.getKey());
        }
        return dataset;
    }

    private static JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Error Occurrences", //title
                "Errors", // x
                "Occurrences", // y
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // Include legend
                true,
                false
        );
    }
    private static void customizeChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setDrawBarOutline(false);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        domainAxis.setLowerMargin(0.02);
        domainAxis.setUpperMargin(0.02);

        for (int series = 0; series < plot.getDataset().getRowCount(); series++) {
            renderer.setSeriesItemLabelGenerator(series, new CustomLabelGenerator());
            renderer.setSeriesItemLabelsVisible(series, true);
            renderer.setSeriesItemLabelPaint(series, Color.BLACK);
        }

    }

    private static void saveChartAsPNG(JFreeChart chart, String outputFilePath) {
        try {
            ChartUtils.saveChartAsPNG(new File(outputFilePath.replace(".txt", ".png")), chart, 1200, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static class CustomLabelGenerator extends org.jfree.chart.labels.StandardCategoryItemLabelGenerator {
        public CustomLabelGenerator() {
            super();
        }

//        public String generateLabel(org.jfree.data.category.CategoryDataset dataset, int row, int column) {
//
//            return null;
//        }
    }
}
