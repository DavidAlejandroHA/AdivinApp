package dad.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class adivinApp extends Application {
	TextField numText = new TextField();
	Label cuadroLabel = new Label();
	Label cuadroLabels = new Label();
	int intentos = 0;
	int numero_correcto = (int)(Math.random()*100+1); // se define el número aleatorio (entre 1 y 100)
	public void start(Stage primaryStage) throws Exception{
		//creamos el cuadro de texto
		
		cuadroLabels.setText("Introduce un número del 1 al 100");
		numText.setPromptText("Introduce un número aquí");
		
		
		//creamos un boton
		Button comprobarButton = new Button("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setOnAction(e -> onPresionarAction(e)/*this::onSaludarAction*/); 
		
		//creamos el panel con disposicion vertical
		VBox rootPanel = new VBox();
		rootPanel.setSpacing(15);
		rootPanel.setFillWidth(false); // el cuadro de texto no se estira tanto
		rootPanel.setAlignment(Pos.CENTER); // alinea todo en el centro
		rootPanel.getChildren().addAll(cuadroLabels, numText, comprobarButton, cuadroLabel); // se añaden todos los elementos al panel
		
		Scene scene = new Scene(rootPanel, 320, 200);
		
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void onPresionarAction(ActionEvent e) {
		try {
			String numero_string = numText.getText();
			int numero = Integer.parseInt(numero_string);
			
			if(numero < 1 || numero > 100)
				throw new NumberFormatException();
			
			if(numero==numero_correcto) {
				intentos++;
				Alert alerta = new Alert(AlertType.INFORMATION);
				
				alerta.setHeaderText("¡Has ganado!");
				alerta.setContentText("Sólo has necesitado " + intentos + " intentos.\n\nVuelve a jugar y hazlo mejor.");

				alerta.showAndWait();
				intentos = 0;
				numero_correcto = (int)(Math.random()*100+1); // se redefine el número aleatorio (entre 1 y 100)
			}else {
				intentos++;
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("AdivinApp");
				alerta.setHeaderText("¡Has fallado!");
				String comparar = (numero>numero_correcto) ? "menor" : "mayor";
				alerta.setContentText("El número a adivinar es " + comparar + " que " + numero + ".\n\nVuelve a intentarlo.");
				alerta.showAndWait();
			}
		} catch (NumberFormatException nfe) {
			
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("AdivinApp");
			alerta.setHeaderText("Error");
			alerta.setContentText("El número introducido no es válido");

			alerta.showAndWait();
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
