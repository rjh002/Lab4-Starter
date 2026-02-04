package edu.ucsd.spendingtracker.view;

//import javax.swing.ButtonGroup;

import edu.ucsd.spendingtracker.view.charts.IChartProvider;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.StringConverter;

public class SummaryView extends VBox {
    private Label totalLabel = new Label();
    private Button backButton = new Button("Back to Expenses");

    private final ComboBox<IChartProvider> chartSelector = new ComboBox<>();

    private final StackPane chartContainer = new StackPane();

    public SummaryView() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(30)); //Lab 5 Addition
        this.setStyle("-fx-background-color: #FFFFFF;");

        Label title = new Label("Spending Analysis"); //Lab 5 Change
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        totalLabel.setStyle("-fx-font-size: 32px; -fx-text-fill: #2E7D32;");

        //Lab 5 Additions
        chartSelector.setPromptText("Select Visualization Type");
        chartSelector.setPrefWidth(200);

        chartSelector.setConverter(new StringConverter<IChartProvider>() {
            @Override
            public String toString(IChartProvider provider){
                return (provider == null) ? "" : provider.getDisplayName();
            }

            @Override
            public IChartProvider fromString(String string){
                return null;
            }
        });

        chartContainer.setPrefSize(400,300);
        chartContainer.setStyle("-fx-border-color: #EEEEEE; -fx-border-width: 1; -fx-border-radius: 5;");

        backButton.setStyle("-fx-background-color: #757575; -fx-text-fill: white;");

        this.getChildren().addAll(
            title, 
            totalLabel, 
            new Label("View Mode:"), 
            chartSelector, 
            chartContainer, 
            backButton);

        //this.getChildren().addAll(title, totalLabel, backButton);
    }

    //Lab 5 Addtion
    public void setChartDisplay(Node chartNode){
        chartContainer.getChildren().clear();
        if (chartNode != null){
            chartContainer.getChildren().add(chartNode);
        }
    }

    public void setTotal(double total) {
        totalLabel.setText("$" + String.format("%.2f", total));
    }

    public Button getBackButton() {
        return backButton;
    }

    //Lab 5 Addition
    public ComboBox<IChartProvider> getChartSelector(){
        return chartSelector;
    }
}