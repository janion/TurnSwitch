package turnswitch;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TurnSwitch {

	private TurnSwitchKnob knob;
	private TurnSwitchModel model;
	private StackPane pane;
	
	public TurnSwitch(List<TurnSwitchPosition> positions) {
		knob = new TurnSwitchKnob();
		pane = new StackPane(knob.getNode());
		
		model = new TurnSwitchModel();
		
		Pane labelPane = new StackPane();
		for (TurnSwitchPosition position : positions) {
			Label label = createLabel(position);
			labelPane.getChildren().add(label);
		}
		pane.getChildren().add(labelPane);

		model.getObserverManager().addObserver(TurnSwitchModel.POSITION_CHANGED,
				position -> knob.setRotation(position.getAngle()));
		
		knob.getNode().setPadding(new Insets(30));
		
		pane.getStylesheets().add("turnswitch/TurnSwitch.css");
	}
	
	public void setPrefSize(double prefWidth, double prefHeight) {
		pane.setPrefSize(prefWidth, prefHeight);
	}
	
	private Label createLabel(TurnSwitchPosition position) {
		Label label = new Label(position.getText());
		BorderPane.setAlignment(label, Pos.CENTER);
		label.setOnMouseClicked(event -> model.setCurrentPosition(position));
		knob.radiusProperty().addListener((observable, oldVal, newVal) -> handleKnobSizeChange(position, label));
		
		return label;
	}
	
	private void handleKnobSizeChange(TurnSwitchPosition position, Label label) {
		double angle = position.getAngle();
		double length = knob.radiusProperty().get();
		double cos = Math.cos((angle * Math.PI) / 180);
		double sin = Math.sin((angle * Math.PI) / 180);
		
		if (cos > 0) {
			label.setTranslateX((length * Math.cos((angle * Math.PI) / 180)) + label.getWidth() / 2);
		} else {
			label.setTranslateX((length * Math.cos((angle * Math.PI) / 180)) - label.getWidth() / 2);
		}
		
		if (sin > 0) {
			label.setTranslateY((length * Math.sin((angle * Math.PI) / 180)) + label.getHeight() / 2);
		} else {
			label.setTranslateY((length * Math.sin((angle * Math.PI) / 180)) - label.getHeight() / 2);
		}
	}
	
	public Pane getNode() {
		return pane;
	}

}
