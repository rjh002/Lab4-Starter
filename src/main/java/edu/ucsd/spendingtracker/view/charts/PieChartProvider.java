package edu.ucsd.spendingtracker.view.charts;

import edu.ucsd.spendingtracker.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

import java.util.Map;

public class PieChartProvider implements IChartProvider {

    @Override
    public Node createChart(Map<Category, Double> data) {
        ObservableList<PieChart.Data> slices = FXCollections.observableArrayList();

        for (Map.Entry<Category, Double> entry : data.entrySet()) {
            Category category = entry.getKey();
            Double value = entry.getValue();

            if (value != null && value > 0) {
                slices.add(new PieChart.Data(category.toString(), value));
            }
        }

        PieChart chart = new PieChart(slices);
        chart.setLegendVisible(true);
        chart.setLabelsVisible(true);

        // Optional: makes it look nicer in many layouts
        chart.setMinSize(300, 250);

        return chart;
    }

    @Override
    public String getDisplayName() {
        return "Pie Chart";
    }

    // Helps ComboBox display correctly if SummaryView uses default rendering
    @Override
    public String toString() {
        return getDisplayName();
    }
}

