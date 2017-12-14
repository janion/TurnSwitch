package turnswitch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LauncherV2 extends Application {

   @Override
   public void start(Stage stage) throws Exception {
		Scene scene = new Scene(new TurnSwitch().getNode());
		stage.setScene(scene);

		stage.setWidth(200);
		stage.setHeight(200);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
