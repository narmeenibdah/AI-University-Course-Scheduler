package application;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXManagement {

	private TableView<Gene> timetableTable;
	private LineChart<Number, Number> fitnessChart;

	private TableView<AlgorithmResult> comparisonTable;
	private LineChart<Number, Number> comparisonChart;

	private ComboBox<Integer> courseCountBox;

	private TextField populationField;
	private TextField generationsField;
	private TextField mutationField;
	private TextField crossoverField;

	private ComboBox<String> selectionBox;
	private ComboBox<String> crossoverMethodBox;
	private ComboBox<String> mutationMethodBox;

	private Label coursesLabel;
	private Label hardLabel;
	private Label softLabel;
	private Label penaltyLabel;
	private Label fitnessLabel;
	private Label generationsLabel;
	private Label runtimeLabel;

	private StackPane contentStack;

	private BorderPane timetablePage;
	private BorderPane fitnessPage;
	private VBox experimentsPage;
	private BorderPane comparisonPage;

	private ToggleButton timetableNavigationButton;

	public void show(Stage primaryStage) {

		timetableTable = createTable();
		fitnessChart = createFitnessChart();

		comparisonTable = createComparisonTable();

		comparisonChart = createComparisonChart();

		createResultLabels();

		timetablePage = createTimetablePage();

		fitnessPage = createFitnessPage();

		comparisonPage = createComparisonPage();

		ExperimentsView experimentsView = new ExperimentsView();

		experimentsPage = experimentsView.getView();

		experimentsPage.getStyleClass().add("content-page");

		contentStack = new StackPane(timetablePage, fitnessPage, experimentsPage, comparisonPage);

		showPage(timetablePage);

		HBox navigation = createNavigation();

		Label title = new Label("AI University Course Scheduler");

		title.getStyleClass().add("app-title");

		Button generateButton = new Button("Generate Timetable");

		generateButton.getStyleClass().add("primary-button");

		generateButton.setMinWidth(180);

		generateButton.setOnAction(event -> generateTimetable());

		Region spacer = new Region();

		HBox.setHgrow(spacer, Priority.ALWAYS);

		HBox titleRow = new HBox(15, title, spacer, generateButton);

		titleRow.setAlignment(Pos.CENTER_LEFT);
		titleRow.setAlignment(Pos.CENTER_LEFT);

		GridPane settingsPane = createSettingsPane();

		VBox headerPanel = new VBox(9, titleRow, settingsPane);

		headerPanel.getStyleClass().add("header-panel");

		VBox topArea = new VBox(navigation, headerPanel);

		VBox.setMargin(headerPanel, new Insets(8, 0, 0, 0));

		BorderPane root = new BorderPane();

		root.getStyleClass().add("app-root");

		root.setPadding(new Insets(0, 12, 12, 12));

		root.setTop(topArea);
		root.setCenter(contentStack);

		BorderPane.setMargin(contentStack, new Insets(10, 0, 0, 0));

		Scene scene = new Scene(root, 1380, 820);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setTitle("University Course Scheduler");

		primaryStage.setMinWidth(1150);
		primaryStage.setMinHeight(700);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private HBox createNavigation() {

		ToggleGroup navigationGroup = new ToggleGroup();

		timetableNavigationButton = createNavigationButton("Timetable", timetablePage, navigationGroup);

		ToggleButton fitnessButton = createNavigationButton("Fitness", fitnessPage, navigationGroup);

		ToggleButton experimentsButton = createNavigationButton("Experiments", experimentsPage, navigationGroup);

		ToggleButton comparisonButton = createNavigationButton("Comparison", comparisonPage, navigationGroup);

		timetableNavigationButton.setSelected(true);

		HBox navigation = new HBox(4, timetableNavigationButton, fitnessButton, experimentsButton, comparisonButton);

		navigation.getStyleClass().add("top-navigation");

		navigation.setAlignment(Pos.CENTER_LEFT);

		return navigation;
	}

	private ToggleButton createNavigationButton(String text, Node page, ToggleGroup group) {

		ToggleButton button = new ToggleButton(text);

		button.setToggleGroup(group);

		button.getStyleClass().add("nav-button");

		button.setOnAction(event -> {

			button.setSelected(true);
			showPage(page);
		});

		return button;
	}

	private void showPage(Node selectedPage) {

		for (Node page : contentStack.getChildren()) {

			boolean selected = page == selectedPage;

			page.setVisible(selected);
			page.setManaged(selected);
		}
	}

	private GridPane createSettingsPane() {

		courseCountBox = new ComboBox<>(FXCollections.observableArrayList(10, 20, 30, 40, 50));

		courseCountBox.setValue(10);

		populationField = new TextField("100");

		generationsField = new TextField("500");

		mutationField = new TextField("5");

		crossoverField = new TextField("80");

		selectionBox = new ComboBox<>(FXCollections.observableArrayList("Tournament", "Roulette Wheel"));

		selectionBox.setValue("Tournament");

		crossoverMethodBox = new ComboBox<>(FXCollections.observableArrayList("Two Point", "One Point"));

		crossoverMethodBox.setValue("Two Point");

		mutationMethodBox = new ComboBox<>(FXCollections.observableArrayList("Random", "Swap"));

		mutationMethodBox.setValue("Random");

		setControlWidth(courseCountBox, 95);

		setControlWidth(populationField, 105);

		setControlWidth(generationsField, 105);

		setControlWidth(mutationField, 95);

		setControlWidth(crossoverField, 95);

		setControlWidth(selectionBox, 135);

		setControlWidth(crossoverMethodBox, 135);

		setControlWidth(mutationMethodBox, 120);

		GridPane grid = new GridPane();

		grid.getStyleClass().add("settings-card");

		grid.setHgap(9);
		grid.setVgap(3);

		addSetting(grid, "Courses", courseCountBox, 0);

		addSetting(grid, "Population", populationField, 1);

		addSetting(grid, "Generations", generationsField, 2);

		addSetting(grid, "Mutation %", mutationField, 3);

		addSetting(grid, "Crossover %", crossoverField, 4);

		addSetting(grid, "Selection", selectionBox, 5);

		addSetting(grid, "Crossover Method", crossoverMethodBox, 6);

		addSetting(grid, "Mutation Method", mutationMethodBox, 7);

		return grid;
	}

	private void setControlWidth(javafx.scene.control.Control control, double width) {

		control.setPrefWidth(width);
		control.setMinWidth(width);
		control.setMaxWidth(width);
	}

	private void addSetting(GridPane grid, String labelText, Node control, int column) {

		Label label = new Label(labelText);

		label.getStyleClass().add("field-label");

		grid.add(label, column, 0);

		grid.add(control, column, 1);
	}

	private BorderPane createTimetablePage() {

		HBox resultsBox = createResultsBox();

		BorderPane page = new BorderPane();

		page.getStyleClass().add("content-page");

		page.setPadding(new Insets(10));

		page.setTop(resultsBox);
		page.setCenter(timetableTable);

		BorderPane.setMargin(timetableTable, new Insets(9, 0, 0, 0));

		return page;
	}

	private BorderPane createFitnessPage() {

		BorderPane page = new BorderPane();

		page.getStyleClass().add("content-page");

		page.setPadding(new Insets(10));

		page.setCenter(fitnessChart);

		return page;
	}

	private BorderPane createComparisonPage() {

		Button compareButton = new Button("Compare Algorithms");

		compareButton.getStyleClass().add("primary-button");

		compareButton.setOnAction(event -> runAlgorithmComparison());

		HBox buttonBox = new HBox(compareButton);

		buttonBox.setAlignment(Pos.CENTER_LEFT);

		VBox comparisonCenter = new VBox(9, comparisonTable, comparisonChart);

		VBox.setVgrow(comparisonChart, Priority.ALWAYS);

		BorderPane page = new BorderPane();

		page.getStyleClass().add("content-page");

		page.setPadding(new Insets(10));

		page.setTop(buttonBox);
		page.setCenter(comparisonCenter);

		BorderPane.setMargin(comparisonCenter, new Insets(9, 0, 0, 0));

		return page;
	}

	private void createResultLabels() {

		coursesLabel = new Label("Courses: -");

		hardLabel = new Label("Hard: -");

		softLabel = new Label("Soft: -");

		penaltyLabel = new Label("Penalty: -");

		fitnessLabel = new Label("Fitness: -");

		generationsLabel = new Label("Generations: -");

		runtimeLabel = new Label("Runtime: -");
	}

	private HBox createResultsBox() {

		HBox resultsBox = new HBox(8);

		resultsBox.getStyleClass().add("results-container");

		resultsBox.getChildren().addAll(createMetricCard(coursesLabel), createMetricCard(hardLabel),
				createMetricCard(softLabel), createMetricCard(penaltyLabel), createMetricCard(fitnessLabel),
				createMetricCard(generationsLabel), createMetricCard(runtimeLabel));

		for (Node card : resultsBox.getChildren()) {

			HBox.setHgrow(card, Priority.ALWAYS);
		}

		return resultsBox;
	}

	private VBox createMetricCard(Label label) {

		VBox card = new VBox(label);

		card.getStyleClass().add("metric-card");

		card.setAlignment(Pos.CENTER_LEFT);

		card.setMaxWidth(Double.MAX_VALUE);

		return card;
	}

	private TableView<Gene> createTable() {

		TableView<Gene> table = new TableView<>();

		TableColumn<Gene, String> dayColumn = new TableColumn<>("Day");

		dayColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLectureTime().getDay()));

		TableColumn<Gene, String> timeColumn = new TableColumn<>("Time");

		timeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLectureTime().getTime()));

		TableColumn<Gene, String> courseColumn = new TableColumn<>("Course");

		courseColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCourse().getName()));

		TableColumn<Gene, String> instructorColumn = new TableColumn<>("Instructor");

		instructorColumn.setCellValueFactory(
				data -> new SimpleStringProperty(data.getValue().getCourse().getInstructor().getName()));

		TableColumn<Gene, String> roomColumn = new TableColumn<>("Room");

		roomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoom().getRoomId()));

		table.getColumns().addAll(dayColumn, timeColumn, courseColumn, instructorColumn, roomColumn);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table.setPlaceholder(new Label("Choose the settings, then generate a timetable"));

		table.setMinHeight(250);

		table.setMaxHeight(Double.MAX_VALUE);

		return table;
	}

	private LineChart<Number, Number> createFitnessChart() {

		NumberAxis generationAxis = new NumberAxis();

		generationAxis.setLabel("Generation");

		NumberAxis fitnessAxis = new NumberAxis();

		fitnessAxis.setLabel("Best Fitness");

		LineChart<Number, Number> chart = new LineChart<>(generationAxis, fitnessAxis);

		chart.setTitle("Generation vs Best Fitness");

		chart.setAnimated(false);
		chart.setCreateSymbols(true);
		chart.setLegendVisible(false);

		chart.setMinHeight(300);

		chart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		return chart;
	}

	private TableView<AlgorithmResult> createComparisonTable() {

		TableView<AlgorithmResult> table = new TableView<>();

		TableColumn<AlgorithmResult, String> nameColumn = new TableColumn<>("Algorithm");

		nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAlgorithmName()));

		TableColumn<AlgorithmResult, String> fitnessColumn = new TableColumn<>("Fitness");

		fitnessColumn.setCellValueFactory(
				data -> new SimpleStringProperty(String.format("%.5f", data.getValue().getFitness())));

		TableColumn<AlgorithmResult, Integer> hardColumn = new TableColumn<>("Hard");

		hardColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getHardViolations()));

		TableColumn<AlgorithmResult, Integer> softColumn = new TableColumn<>("Soft");

		softColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getSoftViolations()));

		TableColumn<AlgorithmResult, Integer> iterationsColumn = new TableColumn<>("Iterations");

		iterationsColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getIterations()));

		TableColumn<AlgorithmResult, Long> runtimeColumn = new TableColumn<>("Runtime (ms)");

		runtimeColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getRuntime()));

		table.getColumns().addAll(nameColumn, fitnessColumn, hardColumn, softColumn, iterationsColumn, runtimeColumn);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table.setPrefHeight(105);
		table.setMinHeight(105);
		table.setMaxHeight(105);

		return table;
	}

	private LineChart<Number, Number> createComparisonChart() {

		NumberAxis iterationAxis = new NumberAxis();

		iterationAxis.setLabel("Generation / Iteration");

		NumberAxis fitnessAxis = new NumberAxis();

		fitnessAxis.setLabel("Best Fitness");

		LineChart<Number, Number> chart = new LineChart<>(iterationAxis, fitnessAxis);

		chart.setTitle("Genetic Algorithm vs Hill Climbing");

		chart.setAnimated(false);
		chart.setCreateSymbols(false);

		chart.setMinHeight(220);

		chart.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		return chart;
	}

	private void generateTimetable() {

		try {

			int courseCount = courseCountBox.getValue();

			int populationSize = Integer.parseInt(populationField.getText());

			int maximumGenerations = Integer.parseInt(generationsField.getText());

			double mutationPercentage = Double.parseDouble(mutationField.getText());

			double crossoverPercentage = Double.parseDouble(crossoverField.getText());

			validateSettings(populationSize, maximumGenerations, mutationPercentage, crossoverPercentage);

			double mutationRate = mutationPercentage / 100.0;

			double crossoverRate = crossoverPercentage / 100.0;

			int elitismCount = Math.max(1, (int) (populationSize * 0.05));

			GeneticAlgorithm.SelectionMethod selectionMethod;

			if (selectionBox.getValue().equals("Roulette Wheel")) {

				selectionMethod = GeneticAlgorithm.SelectionMethod.ROULETTE_WHEEL;

			} else {

				selectionMethod = GeneticAlgorithm.SelectionMethod.TOURNAMENT;
			}

			GeneticAlgorithm.CrossoverMethod crossoverMethod;

			if (crossoverMethodBox.getValue().equals("One Point")) {

				crossoverMethod = GeneticAlgorithm.CrossoverMethod.ONE_POINT;

			} else {

				crossoverMethod = GeneticAlgorithm.CrossoverMethod.TWO_POINT;
			}

			GeneticAlgorithm.MutationMethod mutationMethod;

			if (mutationMethodBox.getValue().equals("Swap")) {

				mutationMethod = GeneticAlgorithm.MutationMethod.SWAP;

			} else {

				mutationMethod = GeneticAlgorithm.MutationMethod.RANDOM;
			}

			UniversityData data = new UniversityData(courseCount);

			GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(data, populationSize, maximumGenerations,
					crossoverRate, mutationRate, elitismCount, selectionMethod, crossoverMethod, mutationMethod);

			long startTime = System.currentTimeMillis();

			Chromosome bestSchedule = geneticAlgorithm.run();

			long endTime = System.currentTimeMillis();

			long runtime = endTime - startTime;

			showTimetable(bestSchedule);

			showResults(bestSchedule, data, geneticAlgorithm, runtime);

			showFitnessChart(geneticAlgorithm);

			timetableNavigationButton.setSelected(true);

			showPage(timetablePage);

		} catch (NumberFormatException exception) {

			showError("Please enter valid numbers.");

		} catch (IllegalArgumentException exception) {

			showError(exception.getMessage());
		}
	}

	private void validateSettings(int populationSize, int maximumGenerations, double mutationPercentage,
			double crossoverPercentage) {

		if (populationSize <= 0) {

			throw new IllegalArgumentException("Population must be greater than zero.");
		}

		if (maximumGenerations <= 0) {

			throw new IllegalArgumentException("Generations must be greater than zero.");
		}

		if (mutationPercentage < 0 || mutationPercentage > 100) {

			throw new IllegalArgumentException("Mutation must be between 0 and 100.");
		}

		if (crossoverPercentage < 0 || crossoverPercentage > 100) {

			throw new IllegalArgumentException("Crossover must be between 0 and 100.");
		}
	}

	private void showTimetable(Chromosome bestSchedule) {

		ObservableList<Gene> rows = FXCollections.observableArrayList(bestSchedule.getGenes());

		rows.sort(Comparator.comparingInt((Gene gene) -> getDayOrder(gene.getLectureTime().getDay()))
				.thenComparing(gene -> gene.getLectureTime().getTime()));

		timetableTable.setItems(rows);
	}

	private void showResults(Chromosome bestSchedule, UniversityData data, GeneticAlgorithm geneticAlgorithm,
			long runtime) {

		coursesLabel.setText("Courses: " + bestSchedule.getGenes().size() + "/" + data.getCourses().size());

		hardLabel.setText("Hard: " + bestSchedule.getHardViolations());

		softLabel.setText("Soft: " + bestSchedule.getSoftViolations());

		penaltyLabel.setText("Penalty: " + bestSchedule.getTotalPenalty());

		fitnessLabel.setText(String.format("Fitness: %.5f", bestSchedule.getFitness()));

		generationsLabel.setText("Generations: " + geneticAlgorithm.getGenerationsUsed());

		runtimeLabel.setText("Runtime: " + runtime + " ms");
	}

	private void showFitnessChart(GeneticAlgorithm geneticAlgorithm) {

		fitnessChart.getData().clear();

		XYChart.Series<Number, Number> series = new XYChart.Series<>();

		series.setName("Best Fitness");

		for (int generation = 0; generation < geneticAlgorithm.getFitnessHistory().size(); generation++) {

			double fitness = geneticAlgorithm.getFitnessHistory().get(generation);

			series.getData().add(new XYChart.Data<>(generation, fitness));
		}

		fitnessChart.getData().add(series);
	}

	private void runAlgorithmComparison() {

		try {

			int courseCount = courseCountBox.getValue();

			int populationSize = Integer.parseInt(populationField.getText());

			int maximumIterations = Integer.parseInt(generationsField.getText());

			double mutationPercentage = Double.parseDouble(mutationField.getText());

			double crossoverPercentage = Double.parseDouble(crossoverField.getText());

			validateSettings(populationSize, maximumIterations, mutationPercentage, crossoverPercentage);

			double mutationRate = mutationPercentage / 100.0;

			double crossoverRate = crossoverPercentage / 100.0;

			AlgorithmComparison comparison = new AlgorithmComparison();

			ArrayList<AlgorithmResult> results = comparison.compare(courseCount, populationSize, maximumIterations,
					crossoverRate, mutationRate);

			comparisonTable.setItems(FXCollections.observableArrayList(results));

			comparisonChart.getData().clear();

			for (AlgorithmResult result : results) {

				XYChart.Series<Number, Number> series = new XYChart.Series<>();

				series.setName(result.getAlgorithmName());

				for (int i = 0; i < result.getFitnessHistory().size(); i++) {

					series.getData().add(new XYChart.Data<>(i, result.getFitnessHistory().get(i)));
				}

				comparisonChart.getData().add(series);
			}

		} catch (NumberFormatException exception) {

			showError("Please enter valid numbers.");

		} catch (IllegalArgumentException exception) {

			showError(exception.getMessage());
		}
	}

	private void showError(String message) {

		Alert alert = new Alert(Alert.AlertType.ERROR);

		alert.setTitle("Invalid Input");

		alert.setHeaderText("Please check the settings");

		alert.setContentText(message);
		alert.showAndWait();
	}

	private int getDayOrder(String day) {

		if (day.equals("Sunday")) {
			return 1;
		}

		if (day.equals("Monday")) {
			return 2;
		}

		if (day.equals("Tuesday")) {
			return 3;
		}

		if (day.equals("Wednesday")) {
			return 4;
		}

		return 5;
	}
}