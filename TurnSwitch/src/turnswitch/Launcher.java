package turnswitch;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Launcher extends Application {

   @Override
   public void start(Stage stage) throws Exception {
//		TurnSwitch switch1 = new TurnSwitch(Arrays.asList(CabDirectionSwitchPosition.values()));
//		TurnSwitch switch2 = new TurnSwitch(Arrays.asList(CabDirectionSwitchPosition.ON, CabDirectionSwitchPosition.OFF, CabDirectionSwitchPosition.REVERSE));
//		Scene scene = new Scene(new HBox(switch1.getNode(), switch2.getNode()));
//		HBox.setHgrow(switch1.getNode(), Priority.ALWAYS);
//		HBox.setHgrow(switch2.getNode(), Priority.ALWAYS);
		
		TurnSwitch switch1 = new TurnSwitch(Arrays.asList(CabDirectionSwitchPosition.values()));
		TurnSwitch switch2 = new TurnSwitch(Arrays.asList(CabDirectionSwitchPosition.ON, CabDirectionSwitchPosition.OFF, CabDirectionSwitchPosition.REVERSE));
		BorderPane pane = new BorderPane(new Pane());
		pane.setBottom(new HBox(switch1.getNode(), switch2.getNode()));
		Scene scene = new Scene(pane);
		HBox.setHgrow(switch1.getNode(), Priority.ALWAYS);
		HBox.setHgrow(switch2.getNode(), Priority.ALWAYS);
		
		switch1.setPrefSize(400, 400);

		stage.setScene(scene);
		stage.setWidth(200);
		stage.setHeight(200);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
