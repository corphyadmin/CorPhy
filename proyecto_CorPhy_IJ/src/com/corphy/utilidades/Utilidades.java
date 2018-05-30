package com.corphy.utilidades;

import com.corphy.app.Controlador;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.validation.DoubleValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.function.UnaryOperator;

/**
 * <p>Clase que controla las validaciones de las coordenadas elegidas por el usuario para mostrar los campos de texto
 * pertinentes.</p>
 * <p>La clase contiene los m&eacute;todos para:</p>
 * <ul>
 * <li>Obtener cada uno de los atributos de una instancia determinada de la clase Utilidades.</li>
 * <li>Almacenar los nombres de las coordenadas.</li>
 * <li>Validar la selecci&oacute;n de la coordenada para mostrar los campos correspondientes e ingresar los datos.</li>
 * <li>Verificar cadenas de texto y n&uacute;meros.</li>
 * <li>Detectar el evento de teclado del Enter</li>
 * <li>Mostrar mensajes de alerta.</li>
 * <li>Copiar archivo PDF ya existente.</li>
 * <li>Abrir un archivo con la aplicaci&oacute;n predeterminada del sistema operativo.</li>
 * <li>Mostrar informaci&oacute;n del proyecto.</li>
 * </ul>
 *
 * @author CorPhy
 * @version 1.0
 */

public class Utilidades {
    // Atributos

    /**
     * Lista principal que almacena los nombres de las coordenadas para adicionarlas al comboBox principal.
     */
    private ObservableList<String> listCoorNombrePr;

    /**
     * Lista secundaria que almacena los nombres de las coordenadas dependiendo de la selecci&oacute;n en el comboBox principal.
     */
    private ObservableList<String> listCoorNombreSc;

    // Constructor

    /**
     * Constructor de la Clase que inicializa los atributos con su contenido.
     */
    public Utilidades() {
        listCoorNombrePr = FXCollections.observableArrayList();
        listCoorNombreSc = FXCollections.observableArrayList();
        addItemsListCoorNombrePr();
        listCoorNombreSc.add(listCoorNombrePr.get(4));
    }

    /**
     * Obtiene los elementos de la lista principal para adicionarlos al comboBox principal.
     *
     * @return Lista que almacena los nombres de las coordenadas independientes.
     */
    public ObservableList<String> getListCoorNombrePr() {
        return listCoorNombrePr;
    }

    /**
     * Obtiene los elementos de la lista secundaria para adicionarlos al comboBox secundario.
     *
     * @return Lista que almacena los nombres de las coordenadas dependientes.
     */
    public ObservableList<String> getListCoorNombreSc() {
        return listCoorNombreSc;
    }

    /**
     * Adiciona los nombres de las coordenadas a la lista principal.
     */
    private void addItemsListCoorNombrePr() {
        listCoorNombrePr.add("Coordenadas cartesianas 2D");
        listCoorNombrePr.add("Coordenadas cartesianas 3D");
        listCoorNombrePr.add("Coordenadas cilíndricas");
        listCoorNombrePr.add("Coordenadas esféricas");
        listCoorNombrePr.add("Coordenadas polares");
    }

    /**
     * Verifica el nombre de la coordenada que seleccion&oacute; el usuario y adiciona el nombre de las opciones (coordenadas)
     * a elegir, y activa los campos para ingresar los datos correspondientes.
     *
     * @param coorNombre Nombre de la coordenada que seleccion&oacute; el usuario.
     * @param lbXRadio   Label para cambiar su valor por X o Radio dependiendo de la coordenada seleccionada.
     * @param lbYAngulo  Label para cambiar su  valor por Y o &Aacute;ngulo dependiendo de la coordenada seleccionada.
     * @param lbZPhi     Label para cambiar su valor por Z o Phi, y se habilita o deshabilita dependiendo de la coordenada
     *                   seleccionada.
     * @param txtZPhi    Campo de texto que se habilita o deshabilita dependiendo de la coordenada seleccionada.
     */
    public void validarCoordenada(String coorNombre, Label lbXRadio, Label lbYAngulo, Label lbZPhi, JFXTextField txtZPhi) {
        if (coorNombre.equals(listCoorNombrePr.get(0))) {
            //Coordenadas cartesianas 2D
            listCoorNombreSc.add("Coordenadas polares");
            lbXRadio.setText("X");
            lbYAngulo.setText("Y");
            lbZPhi.setDisable(true);
            txtZPhi.setDisable(true);
        } else if (coorNombre.equals(listCoorNombrePr.get(1))) {
            //Coordenadas cartesianas 3D
            listCoorNombreSc.addAll("Coordenadas cilíndricas", "Coordenadas esféricas");
            lbXRadio.setText("X");
            lbYAngulo.setText("Y");
            lbZPhi.setDisable(false);
            lbZPhi.setText("Z");
            txtZPhi.setDisable(false);
        } else if (coorNombre.equals(listCoorNombrePr.get(2))) {
            //Coordenadas cilíndricas
            listCoorNombreSc.addAll("Coordenadas cartesianas 3D", "Coordenadas esféricas");
            lbXRadio.setText("Radio");
            lbYAngulo.setText("θ (Theta)");
            lbZPhi.setDisable(false);
            lbZPhi.setText("Z");
            txtZPhi.setDisable(false);
        } else if (coorNombre.equals(listCoorNombrePr.get(3))) {
            //Coordenadas esféricas
            listCoorNombreSc.addAll("Coordenadas cartesianas 3D", "Coordenadas cilíndricas");
            lbXRadio.setText("Radio");
            lbYAngulo.setText("θ (Theta)");
            lbZPhi.setDisable(false);
            lbZPhi.setText("φ (Phi)");
            txtZPhi.setDisable(false);
        } else if (coorNombre.equals(listCoorNombrePr.get(4))) {
            //Coordenadas polares
            listCoorNombreSc.add("Coordenadas cartesianas 2D");
            lbXRadio.setText("Radio");
            lbYAngulo.setText("θ (Theta)");
            lbZPhi.setDisable(true);
            txtZPhi.setDisable(true);
        }
    }

    /**
     * Verifica que el texto ingresado en un campo de texto sea un n&uacute;mero entero o decimal y en caso de que no lo
     * sea se mostrar&aacute; un mensaje de error.
     *
     * @param txtField Campo de texto en el que se ingresar&aacute; el texto a verificar.
     * @param mensaje  Mensaje que se mostrar&aacute; al usuario en caso de que el texto ingresado no cumpla con las
     *                 caracter&iacute;sticas de un n&uacute;mero.
     */
    public void validarInputsDecimal(JFXTextField txtField, String mensaje) {
        DoubleValidator dbValidator = new DoubleValidator();
        txtField.getValidators().add(dbValidator);
        dbValidator.setMessage(mensaje);
        txtField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                txtField.validate();
            }
        });
    }

    /**
     * Formatea el texto de un campo de texto para permitir solo el valor de n&uacute;meros enteros y decimales,
     * negativos o positivos excluyendo las letras y signos a excepción del signo menos y el punto.
     *
     * @param textField Campo de texto a validar.
     */
    public void doubleFormatted(JFXTextField textField) {
        UnaryOperator<TextFormatter.Change> filter = txt -> {
            if (txt.isAdded()) {
                // La expresión "[^0-9.-]" No permite letras pero el punto y el menos si
                if (txt.getText().matches("[^0-9.-]")) {
                    txt.setText("");
                }
            }
            return txt;
        };
        textField.setTextFormatter(new TextFormatter<>(filter));
    }

    /**
     * Reconoce el evento de presionar la tecla Enter para ejecutar una funcionalidad de un bot&oacute;n.
     *
     * @param ap  Contenedor padre del bot&oacute;n.
     * @param btn Bot&oacute;n afectado por el evento.
     */
    public void addEventKeyPress(AnchorPane ap, Button btn) {
        ap.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, evt -> {
            if (evt.getCode() == KeyCode.ENTER) {
                btn.fire();
                evt.consume();
            }
        });
    }

    /**
     * Muestra una alerta al usuario.
     *
     * @param sp      Contenedor principal o ra&iacute;z en primer grado de la interfaz del programa.
     * @param ap      Contenedor principal en segundo grado que contiene la interfaz del programa.
     * @param titulo  T&iacute;tulo de la alerta.
     * @param mensaje Mensaje que se mostrar&aacute; al usuario.
     */
    public void mostrarAlerta(StackPane sp, AnchorPane ap, String titulo, String mensaje) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setId("dialogAlerts");
        JFXButton dialogButton = new JFXButton("Cerrar");
        dialogButton.getStyleClass().add("dialog-button");
        JFXDialog dialog = new JFXDialog(sp, dialogLayout, JFXDialog.DialogTransition.TOP);
        dialogButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            ap.setEffect(null);
            dialog.close();
        });
        dialogLayout.setHeading(new Label(titulo));
        dialogLayout.setBody(new Label(mensaje));
        dialogLayout.setActions(dialogButton);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event) -> {
            ap.setEffect(null);
        });
        ap.setEffect(blur);
    }

    /**
     * Genera una copia de un archivo PDF ya existente permiti&eacute;ndolo guardar en cualquier parte del equipo.
     *
     * @param stage           Escenario principal para esta aplicaci&oacute;n, en la que se puede configurar aspectos como
     *                        el t&iacute;tulo, tama&ntilde;o, estilos, icono, redimensi&oacute;n de la ventana.
     * @param initialFileName Nombre inicial del archivo a exportar que aparecer&aacute; en el cuadro de dialogo para guardarlo
     *                        en el equipo.
     * @throws IOException
     */
    public void saveFile(Stage stage, String initialFileName) throws IOException {
        // Especificar el origen de archivo
        Path sourcePath = Paths.get("./Resources/Cuadro-comparativo-Metodos-Ordenamiento.pdf");
        // Tirar excepción si el origen del archivo no existe
        if (!Files.exists(sourcePath)) throw new IOException("El Archivo no fue encontrado");
        // Crear el dialogo de selección
        FileChooser fileChooser = new FileChooser();
        // Nombre inicial del archivo a guardar
        fileChooser.setInitialFileName(initialFileName);
        // Validar que los archivos sean con extensión .pdf
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Adobe Acrobat Document", "*.pdf"));
        // Mostramos el dialogo de selección
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            // Ruta donde se guardará el archivo
            Path destinationPath = file.toPath();
            // Sobreescribir el archivo de destino si existe y lo copia
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Permite abrir un archivo con la aplicaci&oacute;n predeterminada del sistema operativo.
     *
     * @param pathName Ruta del archivo que ser&aacute; abierto.
     * @throws IOException Lanza una excepci&oacute;n producto de interrupciones o fallos de entrada o salida que ocurrieron.
     */
    public void abrirArchivo(String pathName) throws IOException {
        File path = new File(pathName);
        // Tirar excepción si el origen del archivo no existe
        if (!path.exists()) throw new IOException("El Archivo no fue encontrado");
        Desktop.getDesktop().open(path);
    }

    /**
     * Muestra una notificaci&oacute;n en el sistema operativo.
     *
     * @param title   T&iacute;tulo de la notificaci&oacute;n.
     * @param message Mensaje que se mostrar&aacute; al usuario.
     */
    public void showTrayMessage(String title, String message) {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = ImageIO.read(Controlador.class.getResource("/com/corphy/img/logo.png"));
            TrayIcon trayIcon = new TrayIcon(image, "CorPhy");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("CorPhy");
            tray.add(trayIcon);
            trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
            tray.remove(trayIcon);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Muestra la informaci&oacute;n acerca del proyecto.
     *
     * @param sp Contenedor principal o ra&iacute;z en primer grado de la interfaz del programa.
     */
    public void acercaDe(StackPane sp) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setId("dialogAcercaDe");
        dialogLayout.setMinSize(616, 364);
        JFXDialog dialog = new JFXDialog(sp, dialogLayout, JFXDialog.DialogTransition.LEFT);
        dialog.show();
    }

    /**
     * Verifica si una cadena de texto es un n&uacute;mero entero o decimal.
     *
     * @param str Cadena de texto que ser&aacute; verificada por una condici&oacute;n.
     * @return Falso o verdadero dependiendo si el texto es o no un n&uacute;mero.
     */
    public boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && !str.equals(""));
    }

    /**
     * Verifica si un n&uacute;mero entero o decimal es positivo.
     *
     * @param num N&uacute;mero entero o decimal que ser&aacute; verificado por una condici&oacute;n.
     * @return Falso o verdadero dependiendo si el n&uacute;mero ingresado cumple o no con la condici&oacute;n.
     */
    public boolean isPositive(double num) {
        return num >= 0;
    }
}