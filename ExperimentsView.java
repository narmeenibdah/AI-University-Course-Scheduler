package application;

import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ExperimentsView {

	private TableView<ExperimentResult> experimentsTable;

	public VBox getView() {

		Label title = new Label("Genetic Algorithm Experiments");

		title.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;");

		Button runButton = new Button("Run Experiments");

		experimentsTable = createTable();

		runButton.setOnAction(event -> runExperiments());

		VBox view = new VBox(15, title, runButton, experimentsTable);

		view.setPadding(new Insets(10));

		return view;
	}

	private TableView<ExperimentResult> createTable() {

		TableView<ExperimentResult> table = new TableView<>();

		TableColumn<ExperimentResult, Integer> coursesColumn = new TableColumn<>("Courses");

		coursesColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getCourseCount()));

		TableColumn<ExperimentResult, Integer> populationColumn = new TableColumn<>("Population");

		populationColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPopulationSize()));

		TableColumn<ExperimentResult, String> mutationColumn = new TableColumn<>("Mutation");

		mutationColumn.setCellValueFactory(
				data -> new SimpleStringProperty(String.format("%.0f%%", data.getValue().getMutationRate() * 100)));

		TableColumn<ExperimentResult, String> crossoverColumn = new TableColumn<>("Crossover");

		crossoverColumn.setCellValueFactory(
				data -> new SimpleStringProperty(String.format("%.0f%%", data.getValue().getCrossoverRate() * 100)));

		TableColumn<ExperimentResult, Integer> generationsColumn = new TableColumn<>("Generations");

		generationsColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getGenerations()));

		TableColumn<ExperimentResult, String> fitnessColumn = new TableColumn<>("Best Fitness");

		fitnessColumn.setCellValueFactory(
				data -> new SimpleStringProperty(String.format("%.5f", data.getValue().getBestFitness())));

		TableColumn<ExperimentResult, Integer> hardColumn = new TableColumn<>("Hard");

		hardColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getHardViolations()));

		TableColumn<ExperimentResult, Integer> softColumn = new TableColumn<>("Soft");

		softColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getSoftViolations()));

		TableColumn<ExperimentResult, Long> runtimeColumn = new TableColumn<>("Runtime (ms)");

		runtimeColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getRuntime()));

		table.getColumns().addAll(coursesColumn, populationColumn, mutationColumn, crossoverColumn, generationsColumn,
				fitnessColumn, hardColumn, softColumn, runtimeColumn);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table.setPlaceholder(new Label("Click Run Experiments"));

		return table;
	}

	private void runExperiments() {

		experimentsTable.setPlaceholder(new Label("Running experiments..."));

		ExperimentRunner runner = new ExperimentRunner();

		ArrayList<ExperimentResult> results = runner.runExperiments();

		experimentsTable.setItems(FXCollections.observableArrayList(results));
	}
}