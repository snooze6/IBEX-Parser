package GUI;
/**
 * Created by snooze on 3/17/16.
 */

import Backend.Services.ClientContract;
import Backend.Services.ConnectServerTask;
import Backend.Services.ServerContract;
import com.sun.glass.ui.CommonDialogs;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

public class GUI extends Application {

    ServerContract t;
    ClientContract c;
    Boolean done;

    Button sendbtn;
    ComboBox<String> comboBox;
    ComboBox<String> morelessBox;
    TextField price;

    @Override
    public void start(Stage primaryStage) {

        // Creación de los elementos de la UI
        {
            sendbtn = new Button();
            sendbtn.setText("Send");

            comboBox = new ComboBox<String>();
            comboBox.getItems().addAll();
            comboBox.getSelectionModel().select(1);

            morelessBox = new ComboBox<String>();
            morelessBox.getItems().addAll("More", "Less");

            price = new TextField();

            sendbtn.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    int i = comboBox.getSelectionModel().getSelectedIndex();
                    if (i>=0 && i<35){
                        System.out.println("-- Selected: "+i);
                    }

                    boolean ok; float precio=0;
                    try {
                        precio = Float.parseFloat(price.getText()); ok = true;
                    } catch (NumberFormatException e){
                        ok = false;
                    }
                    if (ok){
                        System.out.println("-- Registrado: "+precio);
                    } else {
                        System.out.println("-- Precio incorrecto");
                        showDialog("Precio incorrecto");
                    }
                }
            });
        }

        new ConnectServerTask(t,c,done, comboBox).doit();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(morelessBox, price);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(comboBox,hBox,sendbtn);

        StackPane root = new StackPane();
        root.getChildren().addAll(vBox);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("IBEX Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showDialog(String text){
//        Stage dialogStage = new Stage();
//        dialogStage.initModality(Modality.WINDOW_MODAL);
//        dialogStage.setScene(new Scene(VBoxBuilder.create().
//                children(new Text(text), new Button("Ok.")).
//                alignment(Pos.CENTER).padding(new Insets(5)).build()));
//        dialogStage.show();

        Dialogs.showInformation("Hi", "Good Morning y'all!");
        if (Dialogs.showConfirm("Choose one baby!", "Can i ask you a question?", Dialogs.YES, Dialogs.NO).equals(Dialogs.YES)) {
            Dialogs.showWarning(null, "Pay attention to my next question!");
            String answer = Dialogs.showTextInput("Are you a pink elephant disguised as a flying pig?", "Tell me!", "No");
            Dialogs.showError(null, "You should not have said " + answer + "!");
            Dialogs.showException("Now i'm angry", "I'm going home...", new RuntimeException("Exception caused by angry dinossaurs"));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
