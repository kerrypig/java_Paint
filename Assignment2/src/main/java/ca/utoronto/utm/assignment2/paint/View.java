package ca.utoronto.utm.assignment2.paint;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {

	private PaintModel paintModel;
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;


	public View(PaintModel model, Stage stage) {
		this.paintModel = model;

		this.paintPanel = new PaintPanel(this.paintModel);
		this.shapeChooserPanel = new ShapeChooserPanel(this);

		BorderPane root = new BorderPane();
		root.setTop(createMenuBar());
		root.setCenter(this.paintPanel);
		root.setLeft(this.shapeChooserPanel);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();

		// Adjust Canvas size actively
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			paintPanel.setWidth(newVal.doubleValue());
		});

		stage.heightProperty().addListener((obs, oldVal, newVal) -> {
			paintPanel.setHeight(newVal.doubleValue());
		});



		// Ctrl+Z
		scene.getAccelerators().put(
				new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN),
				() -> this.paintModel.removeLastShape()
		);

		// Ctrl+Y
		scene.getAccelerators().put(
				new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN),
				() -> this.paintModel.addOneRemovedShape()
		);




	}

	public PaintModel getPaintModel() {
		return this.paintModel;
	}

	public PaintPanel getPaintPanel() {return this.paintPanel;}

	// ugly way to do this?
	public void setMode(String mode) {
		this.paintPanel.setMode(mode);
	}

	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menu.getItems().add(new SeparatorMenuItem());
		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		return menuBar;
	}


	@Override
	public void handle(ActionEvent event) {
		System.out.println(((MenuItem) event.getSource()).getText());
		String command = ((MenuItem) event.getSource()).getText();
		System.out.println(command);
		if (command.equals("Exit")) {
			Platform.exit();
		}
		if (command.equals("Undo")) {
			this.paintModel.removeLastShape();

		}
		if (command.equals("Redo")) {
			this.paintModel.addOneRemovedShape();
		}
	}

}
