package GUI;
/**
 * Created by snooze on 3/17/16.
 */

import Backend.Services.ClientContract;
import Backend.Services.ConnectServerTask;
import Backend.Services.ServerContract;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import GUI.Dialogs;

public class GUI extends Application {

    ServerContract t;
    ClientContract c;
    Boolean done = false;

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

            morelessBox = new ComboBox<String>();
            morelessBox.getItems().addAll("More", "Less");
            morelessBox.getSelectionModel().select(0);

            price = new TextField();

            sendbtn.setOnAction(event -> {
                if (done){
                    int i = comboBox.getSelectionModel().getSelectedIndex();
                    boolean ok;
                    if (i>=0 && i<35){
                        System.out.println("-- Selected: "+i);

                        float precio=0;
                        try {
                            precio = Float.parseFloat(price.getText()); ok = true;
                        } catch (NumberFormatException e){
                            ok = false;
                        }
                        if (ok){
                            String s; int type;
                            if (morelessBox.getSelectionModel().getSelectedIndex()==0){
                                s = "vender";
                                type = ServerContract.TYPE_MORE_THAN;
                            } else {
                                s = "comprar";
                                type = ServerContract.TYPE_LESS_THAN;
                            }

                            System.out.println("-- Registrado: "+precio);
                            Dialogs.showInformation(
                                    "Registrado para recibir notificación",
                                    "Intentando "+s+" acciones de "+comboBox.getItems().get(i)+" por\n"+precio
                            );
                            try {
                                t.register(c,i,type,precio);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Dialogs.showError(null, "Precio incorrecto");
                        }
                    } else {
                        ok = false;
                        Dialogs.showError(null, "Seleccione una empresa");
                    }
                } else {
                    Dialogs.showError(null, "Aún no está listo");
                }
            });
        }

        new ConnectServerTask(this).doit();

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("-- Saliendo");
        if (done) {
            t.unregister(c);
        }
    }

    public void regenerate(ClientContract c, ServerContract t, Boolean done){
        this.c = c;
        this.t = t;
        this.done = done;
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }

    public ServerContract getT() {
        return t;
    }

    public ClientContract getC() {
        return c;
    }

    public Boolean getDone() {
        return done;
    }

    public void showMessage(String message){
//        Dialogs.showInformation(
//                "Recibida notificación",
//                message
//        );
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                Dialogs.showInformation(
                        "Recibida notificación",
                        message
                );
            }
        });
    }
}
