package edu.ucsd.spendingtracker.presenter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.ucsd.spendingtracker.model.Category;
import edu.ucsd.spendingtracker.model.Expense;
import edu.ucsd.spendingtracker.model.Model;
import edu.ucsd.spendingtracker.view.SummaryView;
import edu.ucsd.spendingtracker.view.charts.IChartProvider;
import javafx.scene.Node;



public class SummaryPresenter extends AbstractPresenter<SummaryView> {
    private Runnable onBack;
    private final List<IChartProvider> chartProviders; //Lab 5 Addition
    //Lab 5 Additions
    public SummaryPresenter(Model model, SummaryView view, List<IChartProvider> chartProviders) {
        super(model, view);
        //Lab 5 Additions
        this.chartProviders = chartProviders;
        this.view.getChartSelector().getItems().addAll(this.chartProviders);

        if (!this.chartProviders.isEmpty()){
            this.view.getChartSelector().setValue(this.chartProviders.get(0));
        }

        this.view.getBackButton().setOnAction(e -> {
            if (onBack != null)
                onBack.run();
        });
        //UpdateView instead of Refresh
        this.view.getChartSelector().setOnAction(e -> updateView());
    }

    public void setOnBack(Runnable action) {
        this.onBack = action;
    }

    //"If you are working from your code in Lab 4, this is the updateView() method"
    //Might have to comment out updateView and change this to say updateView().
    public void updateView(){
        List<Expense> expenses = model.getExpenses();
        Map<Category, Double> totals = new TreeMap<>();

        for (Category cat: Category.values()){
            totals.put(cat, 0.0);
        }

        for (Expense expense : expenses){
            Category category = expense.getCategory();
            double amount = expense.getAmount();

            totals.put(category, totals.get(category) + amount);
        }

        view.setTotal(model.getTotalSpending());

        IChartProvider selectedProvider = view.getChartSelector().getValue();

        if (selectedProvider != null){
            Node chartNode = selectedProvider.createChart(totals);
            view.setChartDisplay(chartNode);
        }

    }

    
    //@Override
    //public void updateView() {
    //    view.setTotal(model.getTotalSpending());
    //}
    

    @Override
    public String getViewTitle() {
        return "Summary";
    }

}
