package com.corphy.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <p>Clase que contiene los m&eacute;todos para ejecutar la aplicaci&oacute;n. Esta hereda de la clase {@link Application}
 * desde la cual se extienden las aplicaciones JavaFX.</p>
 */

public class Main extends Application {

    /**
     * Es el punto de entrada principal para todas las aplicaciones JavaFX. El m&eacute;todo de inicio se invoca
     * despu&eacute;s de que el m&eacute;todo {@code init} haya retornado, y despu&eacute;s de que el sistema est&eacute;
     * listo para que la aplicaci&oacute;n comience a ejecutarse.
     * <strong><em>NOTA:</em></strong> Este m&eacute;todo se invoca en el subproceso de la aplicaci&oacute;n JavaFX.
     *
     * @param primaryStage Escenario principal para esta aplicaci&oacute;n, en la que se puede configurar aspectos como
     *                     el t&iacute;tulo, tama&ntilde;o, estilos, icono, redimensi&oacute;n de la ventana.
     * @throws Exception Construye una nueva excepci&oacute;n con null sin mensaje de detalle.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //FXMLLoader.load(getClass().getResource("InterfazGrafica.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfazGrafica.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("CorPhy");
        primaryStage.getIcons().add(new Image("/com/corphy/img/logo.png"));
        Scene scene = new Scene(root, 1300, 650);
        scene.getStylesheets().add(getClass().getResource("/com/corphy/temas/lightTheme.css").toExternalForm());
        Controlador ctr = loader.getController();
        ctr.setScene(scene);
        ctr.setStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Inicia la ejecuci&oacute;n de cualquier programa en Java. Dicho m&eacute;todo representa el &uacute;nico
     * mecanismo autom&aacute;tico para realizar tareas al invocarse una Clase, esto es, al momento de ejecutarse determinada
     * Clase siempre ser&aacute; ejecutado todo el contenido dentro de este m&eacute;todo.
     *
     * @param args Par&aacute;metro de entrada, siempre ser&aacute; un array de String's (String[]) el cual es tomado de
     *             la l&iacute;nea de comandos o una fuente alterna.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
