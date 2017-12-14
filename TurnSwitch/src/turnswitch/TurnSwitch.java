package turnswitch;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TurnSwitch {

	private TurnSwitchKnob knob;
	private TurnSwitchModel model;
	private BorderPane pane;
	
	public TurnSwitch() {
		knob = new TurnSwitchKnob();
		pane = new BorderPane(knob.getNode());
		
		model = new TurnSwitchModel();

		pane.setTop(createLabel(CabDirectionSwitchPosition.ON));
		pane.setRight(createLabel(CabDirectionSwitchPosition.OFF));
		pane.setBottom(createLabel(CabDirectionSwitchPosition.REVERSE));
		
		model.getObserverManager().addObserver(TurnSwitchModel.POSITION_CHANGED,
				position -> knob.setRotation(position.getAngle()));
	}
	
	private Label createLabel(CabDirectionSwitchPosition position) {
		Label label = new Label(position.getText());
		BorderPane.setAlignment(label, Pos.CENTER);
		label.setOnMouseClicked(event -> model.setCurrentPosition(position));
		
		return label;
	}
	
	public Pane getNode() {
		return pane;
	}

}
