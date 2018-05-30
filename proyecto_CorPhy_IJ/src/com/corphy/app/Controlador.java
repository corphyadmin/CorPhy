package com.corphy.app;

import com.corphy.graficador.GraficaFrame;
import com.corphy.utilidades.MetodosConversion;
import com.corphy.utilidades.Utilidades;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>La clase Controlador define la l&oacute;gica de tras de la interfaz. Podemos usar los objetos definidos dentro del
 * archivo FXML para modificarlos y aplicar acciones cuando se usan. Adem&aacute;s, se usan las etiquetas @FXML sobre los
 * objetos y m&eacute;todos definidos en el archivo FXML para lograr comunicarse con ellos.</p>
 *
 * <p>Esta clase contiene los m&eacute;todos principales para el funcionamiento de la aplicaci&oacute;n. Se utiliza la Interface
 * Initializable para ejecutar acciones cuando la aplicaci&oacute;n inicia.</p>
 */
// coemm
public class Controlador implements Initializable {
    /**
     * Objeto de la clase {@link Utilidades} que contiene los m&eacute;todos para almacenar el nombre de las
     * coordenadas, validar y mostrar alertas.
     */
    private Utilidades utl;

    /**
     * Objeto de la clase {@link MetodosConversion} que contiene los m&eacute;todos de conversi&oacute;n.
     */
    private MetodosConversion mc;

    // Stage principal de la aplicación
    private Stage stage;

    // Scene pricipal de la aplicación
    private Scene scene;

    // StackPane principal
    @FXML
    private StackPane rootStackPane;

    // AnchorPane principal
    @FXML
    private AnchorPane rootAnchorPane;

    // Botones del menú principal
    @FXML
    private JFXButton btnConversor, btn_example, btnSettings;

    // Botones del menu Ejemplos de coordenadas
    @FXML
    private JFXButton btn_back, btnCoorCart, btnCoorCil, btnCoorEsf, btnCoorPol;

    // Botón Atrás de las configuraciones
    @FXML
    private JFXButton btn_back_Stt;

    // Panel del conversor de coordenadas y el Panel de la parte física
    @FXML
    private AnchorPane conversorPane, fisicaPane;

    // Panel del menú de los ejemplos de coordenadas y configuraciones
    @FXML
    private AnchorPane addExamplesPane, settingsPane;

    // Iconos (triángulos) de posición
    @FXML
    private OctIconView oneIcon;

    // ComboBox principal y secundario del conversor de coordenadas
    @FXML
    private JFXComboBox cbCoorMain, cbCoorSec;

    // Campos de texto para para ingresar los datos en el conversor de coordenadas
    @FXML
    private JFXTextField txtXRadio, txtYAngulo, txtZPhi;

    // Labels utilizadas para el conversor de coordenadas
    @FXML
    private Label xRadio, yAngulo, zPhi, lbZPhi, lbXRadio, lbYAngulo, label1, label2, label3, lbNumDecimales;

    // CheckBox para habilitar ángulo de las coordenadas en radianes
    @FXML
    private JFXCheckBox cbRadians;

    // Botón para calcular las conversiones de las coordenadas
    @FXML
    private JFXButton btnCalcular;

    // Slider para cambiar dinámicamente el número de decimales al resultado de la conversión de la coordenada
    @FXML
    private JFXSlider slider;

    // Pane de linea superior decorativa
    @FXML
    private Pane paneLinea;

    // ToggleButtons de la configuración
    @FXML
    private JFXToggleButton tgTheme, tgLinea;

    /**
     * Invocado para inicializar el controlador despu&eacute;s de que su elemento ra&iacute;z haya sido procesado por completo.
     * Al iniciar la aplicaci&oacute;n se ejecutan acciones tales como seleccionar valores por defecto de los ComboBox principal
     * y secundario, y validaci&oacute;n de campos de texto.
     *
     * @param url La ubicaci&oacute;n utilizada para resolver las rutas relativas para el objeto ra&iacute;z, o nulo si
     *            la ubicaci&oacute;n no se conoce.
     * @param rb  Los recursos utilizados para localizar el objeto ra&iacute;z, o null si el objeto ra&iacute;z no fue localizado.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utl = new Utilidades();
        mc = new MetodosConversion();

        // Seleccionar valor por defecto del ComboBox princiapal
        cbCoorMain.setItems(utl.getListCoorNombrePr());
        cbCoorMain.getSelectionModel().select(0);

        // Seleccionar valor por defecto del ComboBox secundario
        cbCoorSec.setItems(utl.getListCoorNombreSc());
        cbCoorSec.getSelectionModel().select(0);

        // Validar campos de texto solo para permitir números usando TextFormatter
        utl.doubleFormatted(txtXRadio);
        utl.doubleFormatted(txtYAngulo);
        utl.doubleFormatted(txtZPhi);

        // Mensaje de validación de campos de texto del conversor de coordenadas
        utl.validarInputsDecimal(txtXRadio, "Verificar el dato ingresado");
        utl.validarInputsDecimal(txtYAngulo, "Verificar el dato ingresado");
        utl.validarInputsDecimal(txtZPhi, "Verificar el dato ingresado");

        // Agregar evento para poder presionar Enter al botón Calcular del conversor de coordenadas
        utl.addEventKeyPress(conversorPane, btnCalcular);
    }

    /**
     * Contenedor JavaFX de nivel superior. La Etapa primaria est&aacute; construida por la plataforma. La aplicaci&oacute;n
     * puede construir objetos Stage adicionales.
     *
     * @param stage Contenedor de nivel superior.
     */
    void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Contenedor para todo el contenido gr&aacute;fico de la aplicaci&oacute;n en el cual se debe especificar la ra&iacute;z de cada elemento.
     *
     * @param scene Contenedor gr&aacute;fico.
     */
    void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Evento principal que ser&aacute; un clic sobre los items del men&uacute; para manejar la nevagaci&oacute;n entre
     * submenus e indicar la posici&oacute;n del usuario en el programa.
     *
     * @param event Evento que se ejecutara cuando se haga clic sobre el Bot&oacute;n.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btn_example) addExamplesPane.setVisible(true);
        else if (event.getSource() == btnSettings) settingsPane.setVisible(true);
        else {
            if (event.getSource() == btn_back) addExamplesPane.setVisible(false);
            if (event.getSource() == btn_back_Stt) settingsPane.setVisible(false);
        }
    }

    /**
     * Evento principal de los botones de ejemplos el cual mostrar&aacute; el archivo del bot&oacute;n seleccionado en
     * la aplicaci&oacute;n predeterminada del sistema operativo.
     *
     * @param event Evento que ser&aacute; un clic.
     */
    @FXML
    private void handleShowFileAction(ActionEvent event) {
        String message = "Se ha producido un error al leer los componentes, posiblemente fueron eliminados o modificados." +
                " Si el problema persiste por favor descargue e instale nuevamente el programa.";
        if (event.getSource() == btnCoorCart) {
            try {
                utl.abrirArchivo("./Resources/CoordenadasCartesianas.pdf");
            } catch (IOException e) {
                utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Error al abrir archivo", message);
            }
        }
        if (event.getSource() == btnCoorCil) {
            try {
                utl.abrirArchivo("./Resources/CoordenadasCilindricas.pdf");
            } catch (IOException e) {
                utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Error al abrir archivo", message);
            }
        }
        if (event.getSource() == btnCoorEsf) {
            try {
                utl.abrirArchivo("./Resources/CoordenadasEsfericas.pdf");
            } catch (IOException e) {
                utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Error al abrir archivo", message);
            }
        }
        if (event.getSource() == btnCoorPol) {
            try {
                utl.abrirArchivo("./Resources/CoordenadasPolares.pdf");
            } catch (IOException e) {
                utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Error al abrir archivo", message);
            }
        }
    }

    /**
     * Evento que ser&aacute; un clic sobre los items del ComboBox principal.
     */
    @FXML
    private void cbCoorMainHandleButtonAction() {
        cbCoorSec.getItems().clear();
        utl.validarCoordenada((String) cbCoorMain.getSelectionModel().getSelectedItem(), xRadio, yAngulo, zPhi, txtZPhi);
        cbCoorSec.setItems(utl.getListCoorNombreSc());
        cbCoorSec.setValue(utl.getListCoorNombreSc().get(0));
    }

    /**
     * Evento que se ejecutará cuando se de clic para activar o desactivar el tema oscuro del programa.
     */
    @FXML
    private void handleTgThemeAction() {
        if (tgTheme.isSelected()) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/com/corphy/temas/darkTheme.css").toExternalForm());
            tgTheme.setText("ON");
            paneLinea.setStyle(tgLinea.isSelected() ? "-fx-background-color: #999999" : "-fx-background-color: #090909");
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/com/corphy/temas/lightTheme.css").toExternalForm());
            tgTheme.setText("OFF");
            paneLinea.setStyle(tgLinea.isSelected() ? "-fx-background-color: #0aab8a" : "-fx-background-color: white");
        }
    }

    /**
     * Evento que ser&aacute; un clic sobre el ToggleButton para mostrar u ocultar la l&iacute;nea decorativa superior de
     * los t&iacute;tulos.
     */
    @FXML
    private void handleTgLineaAction() {
        if (tgLinea.isSelected()) {
            tgLinea.setText("ON");
            paneLinea.setStyle(tgTheme.isSelected() ? "-fx-background-color: #999999" : "-fx-background-color: #0aab8a");
        } else {
            tgLinea.setText("OFF");
            paneLinea.setStyle(tgTheme.isSelected() ? "-fx-background-color: #090909" : "-fx-background-color: white");
        }
    }

    /**
     * Evento que muestra el graficador de funciones al hacer click en el boto&oacute;n.
     */
    @FXML
    private void btnGraficadorHandleButtonAction() {
        GraficaFrame gf = new GraficaFrame();
        gf.setVisible(true);
    }

    @FXML
    private void btnAboutHandleButtonAction() {
        String message = "Gracias por usar CorPhy";
        utl.acercaDe(rootStackPane);
        utl.showTrayMessage(String.format("Hola %s!", System.getProperty("user.name")), message);
    }

    /**
     * Evento de que permite la conversi&oacute;n entre las diferentes coordenadas.
     */
    @FXML
    private void btnCalcularHandleButtonAction() {
        String coorNombrePr = cbCoorMain.getSelectionModel().getSelectedItem().toString();
        String coorNombreSc = cbCoorSec.getSelectionModel().getSelectedItem().toString();
        int nDe = (int) BigDecimal.valueOf(slider.getValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
        double[] calCoor;

        switch (mc.validarConversion(coorNombrePr, coorNombreSc)) {
            // Conversión de coordenadas Cartesianas 2D a Polares
            case 0:
                if (txtXRadio.getText().isEmpty() || txtYAngulo.getText().isEmpty()) {
                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Verifique que todos los campos no estén vacíos.");
                } else {
                    if (utl.isNumeric(txtXRadio.getText()) && utl.isNumeric(txtYAngulo.getText())) {
                        // Obtenemos los valores de los inputs del conversor
                        double valorXRadio = Double.parseDouble(txtXRadio.getText());
                        double valorYAngulo = Double.parseDouble(txtYAngulo.getText());
                        // Se realiza la conversión de las coordenadas
                        calCoor = mc.calcularCoorCar2DPo(valorXRadio, valorYAngulo, cbRadians.isSelected(), nDe);

                        lbNumDecimales.setVisible(true);
                        slider.setVisible(true);
                        lbXRadio.setText("Radio:");
                        lbXRadio.setVisible(true);
                        lbYAngulo.setText("θ (Theta):");
                        lbYAngulo.setVisible(true);
                        lbZPhi.setVisible(false);

                        label1.setText(String.valueOf(calCoor[0]));
                        label2.setText(String.valueOf(calCoor[1]));
                        label3.setVisible(false);
                        // Cambio del valor del Slider que indican la cantidad de los números decimales
                        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                            int nDecimales = (int) BigDecimal.valueOf(newValue.doubleValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
                            double[] calCoorSec = mc.calcularCoorCar2DPo(valorXRadio, valorYAngulo, cbRadians.isSelected(), nDecimales);
                            label1.setText(String.valueOf(calCoorSec[0]));
                            label2.setText(String.valueOf(calCoorSec[1]));
                        });
                    } else {
                        utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Los datos ingresados no son números o el formato es incorrecto.");
                    }
                }
                break;
            case 1:
                // Conversión de coordenadas Cartesianas 3D a Cilíndricas
                if (txtXRadio.getText().isEmpty() || txtYAngulo.getText().isEmpty() || txtZPhi.getText().isEmpty()) {
                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Verifique que todos los campos no estén vacíos.");
                } else {
                    if (utl.isNumeric(txtXRadio.getText()) && utl.isNumeric(txtYAngulo.getText()) && utl.isNumeric(txtZPhi.getText())) {
                        // Obtenemos los valores de los inputs del conversor
                        double valorXRadio = Double.parseDouble(txtXRadio.getText());
                        double valorYAngulo = Double.parseDouble(txtYAngulo.getText());
                        double valorZPhi = Double.parseDouble(txtZPhi.getText());
                        // Se realiza la conversión de las coordenadas
                        calCoor = mc.calcularCoorCar3DCil(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDe);

                        lbNumDecimales.setVisible(true);
                        slider.setVisible(true);
                        lbXRadio.setText("Radio:");
                        lbXRadio.setVisible(true);
                        lbYAngulo.setText("θ (Theta):");
                        lbYAngulo.setVisible(true);
                        lbZPhi.setText("Z:");
                        lbZPhi.setVisible(true);

                        label1.setText(String.valueOf(calCoor[0]));
                        label2.setText(String.valueOf(calCoor[1]));
                        label3.setText(String.valueOf(calCoor[2]));
                        label3.setVisible(true);
                        // Cambio del valor del Slider que indican la cantidad de los números decimales
                        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                            int nDecimales = (int) BigDecimal.valueOf(newValue.doubleValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
                            double[] calCoorSec = mc.calcularCoorCar3DCil(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDecimales);
                            label1.setText(String.valueOf(calCoorSec[0]));
                            label2.setText(String.valueOf(calCoorSec[1]));
                            label3.setText(String.valueOf(calCoorSec[2]));
                        });
                    } else {
                        utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Los datos ingresados no son números o el formato es incorrecto.");
                    }
                }
                break;
            case 2:
                // Conversión de coordenadas Cartesianas 3D a Esféricas
                if (txtXRadio.getText().isEmpty() || txtYAngulo.getText().isEmpty() || txtZPhi.getText().isEmpty()) {
                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Verifique que todos los campos no estén vacíos.");
                } else {
                    if (utl.isNumeric(txtXRadio.getText()) && utl.isNumeric(txtYAngulo.getText()) && utl.isNumeric(txtZPhi.getText())) {
                        // Obtenemos los valores de los inputs del conversor
                        double valorXRadio = Double.parseDouble(txtXRadio.getText());
                        double valorYAngulo = Double.parseDouble(txtYAngulo.getText());
                        double valorZPhi = Double.parseDouble(txtZPhi.getText());
                        // Se realiza la conversión de las coordenadas
                        calCoor = mc.calcularCoorCar3DEsf(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDe);

                        lbNumDecimales.setVisible(true);
                        slider.setVisible(true);
                        lbXRadio.setText("Radio:");
                        lbXRadio.setVisible(true);
                        lbYAngulo.setText("θ (Theta):");
                        lbYAngulo.setVisible(true);
                        lbZPhi.setText("φ (Phi):");
                        lbZPhi.setVisible(true);

                        label1.setText(String.valueOf(calCoor[0]));
                        label2.setText(String.valueOf(calCoor[2]));
                        label3.setText(String.valueOf(calCoor[1]));
                        label3.setVisible(true);
                        // Cambio del valor del Slider que indican la cantidad de los números decimales
                        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                            int nDecimales = (int) BigDecimal.valueOf(newValue.doubleValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
                            double[] calCoorSec = mc.calcularCoorCar3DEsf(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDecimales);
                            label1.setText(String.valueOf(calCoorSec[0]));
                            label2.setText(String.valueOf(calCoorSec[2]));
                            label3.setText(String.valueOf(calCoorSec[1]));
                        });
                    } else {
                        utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Los datos ingresados no son números o el formato es incorrecto.");
                    }
                }
                break;
            case 3:
                // Conversión de coordenadas Cilíndricas a Cartesianas 3D
                if (txtXRadio.getText().isEmpty() || txtYAngulo.getText().isEmpty() || txtZPhi.getText().isEmpty()) {
                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Verifique que todos los campos no estén vacíos.");
                } else {
                    if (utl.isNumeric(txtXRadio.getText()) && utl.isNumeric(txtYAngulo.getText()) && utl.isNumeric(txtZPhi.getText())) {
                        // Obtenemos los valores de los inputs del conversor
                        double valorXRadio = Double.parseDouble(txtXRadio.getText());
                        double valorYAngulo = Double.parseDouble(txtYAngulo.getText());
                        double valorZPhi = Double.parseDouble(txtZPhi.getText());
                        // El ángulo Theta debe tener valores entre 0\u00b0 y 360\u00b0
                        if (valorYAngulo >= 0 && valorYAngulo <= 360) {
                            // Se realiza la conversión de las coordenadas
                            calCoor = mc.calcularCoorCilCar3D(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDe);

                            lbNumDecimales.setVisible(true);
                            slider.setVisible(true);
                            lbXRadio.setText("X:");
                            lbXRadio.setVisible(true);
                            lbYAngulo.setText("Y:");
                            lbYAngulo.setVisible(true);
                            lbZPhi.setText("Z:");
                            lbZPhi.setVisible(true);

                            label1.setText(String.valueOf(calCoor[0]));
                            label2.setText(String.valueOf(calCoor[1]));
                            label3.setText(String.valueOf(calCoor[2]));
                            label3.setVisible(true);
                            // Cambio del valor del Slider que indican la cantidad de los números decimales
                            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                                int nDecimales = (int) BigDecimal.valueOf(newValue.doubleValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
                                double[] calCoorSec = mc.calcularCoorCilCar3D(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDecimales);
                                label1.setText(String.valueOf(calCoorSec[0]));
                                label2.setText(String.valueOf(calCoorSec[1]));
                                label3.setText(String.valueOf(calCoorSec[2]));
                            });
                        } else {
                            utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "El ángulo θ (Theta) debe tener valores entre 0° y 360°.");
                        }
                    } else {
                        utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Los datos ingresados no son números o el formato es incorrecto.");
                    }
                }
                break;
            case 4:
                // Conversión de coordenadas Cilíndricas a Esféricas
                if (txtXRadio.getText().isEmpty() || txtYAngulo.getText().isEmpty() || txtZPhi.getText().isEmpty()) {
                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Verifique que todos los campos no estén vacíos.");
                } else {
                    if (utl.isNumeric(txtXRadio.getText()) && utl.isNumeric(txtYAngulo.getText()) && utl.isNumeric(txtZPhi.getText())) {
                        // Obtenemos los valores de los inputs del conversor
                        double valorXRadio = Double.parseDouble(txtXRadio.getText());
                        double valorYAngulo = Double.parseDouble(txtYAngulo.getText());
                        double valorZPhi = Double.parseDouble(txtZPhi.getText());
                        // El ángulo Theta debe tener valores entre 0\u00b0 y 360\u00b0
                        if (valorYAngulo >= 0 && valorYAngulo <= 360) {
                            // Se realiza la conversión de las coordenadas
                            calCoor = mc.calcularCoorCilEsf(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDe);

                            lbNumDecimales.setVisible(true);
                            slider.setVisible(true);
                            lbXRadio.setText("Radio:");
                            lbXRadio.setVisible(true);
                            lbYAngulo.setText("θ (Theta):");
                            lbYAngulo.setVisible(true);
                            lbZPhi.setText("φ (Phi):");
                            lbZPhi.setVisible(true);

                            label1.setText(String.valueOf(calCoor[0]));
                            label2.setText(String.valueOf(calCoor[1]));
                            label3.setText(String.valueOf(calCoor[2]));
                            label3.setVisible(true);
                            // Cambio del valor del Slider que indican la cantidad de los números decimales
                            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                                int nDecimales = (int) BigDecimal.valueOf(newValue.doubleValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
                                double[] calCoorSec = mc.calcularCoorCilEsf(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDecimales);
                                label1.setText(String.valueOf(calCoorSec[0]));
                                label2.setText(String.valueOf(calCoorSec[1]));
                                label3.setText(String.valueOf(calCoorSec[2]));
                            });
                        } else {
                            utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "El ángulo θ (Theta) debe tener valores entre 0° y 360°.");
                        }
                    } else {
                        utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Los datos ingresados no son números o el formato es incorrecto.");
                    }
                }
                break;
            case 5:
                // Conversión de coordenadas Esféricas a Cartesianas 3D
                if (txtXRadio.getText().isEmpty() || txtYAngulo.getText().isEmpty() || txtZPhi.getText().isEmpty()) {
                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Verifique que todos los campos no estén vacíos.");
                } else {
                    if (utl.isNumeric(txtXRadio.getText()) && utl.isNumeric(txtYAngulo.getText()) && utl.isNumeric(txtZPhi.getText())) {
                        // Obtenemos los valores de los inputs del conversor
                        double valorXRadio = Double.parseDouble(txtXRadio.getText());
                        double valorYAngulo = Double.parseDouble(txtYAngulo.getText());
                        double valorZPhi = Double.parseDouble(txtZPhi.getText());
                        // Validamos que el radio sea mayor o igual a 0
                        if (valorXRadio >= 0) {
                            // Validamos que ángulo Theta este entre 0\u00b0 y 360\u00b0
                            if (valorYAngulo >= 0 && valorYAngulo <= 360) {
                                // Validamos que ángulo Phi este entre 0\u00b0 y 180\u00b0
                                if (valorZPhi >= 0 && valorZPhi <= 180) {
                                    // Se realiza la conversión de las coordenadas
                                    calCoor = mc.calcularCoorEsfCar3D(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDe);

                                    lbNumDecimales.setVisible(true);
                                    slider.setVisible(true);
                                    lbXRadio.setText("X:");
                                    lbXRadio.setVisible(true);
                                    lbYAngulo.setText("Y:");
                                    lbYAngulo.setVisible(true);
                                    lbZPhi.setText("Z:");
                                    lbZPhi.setVisible(true);

                                    label1.setText(String.valueOf(calCoor[0]));
                                    label2.setText(String.valueOf(calCoor[1]));
                                    label3.setText(String.valueOf(calCoor[2]));
                                    label3.setVisible(true);
                                    // Cambio del valor del Slider que indican la cantidad de los números decimales
                                    slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                                        int nDecimales = (int) BigDecimal.valueOf(newValue.doubleValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
                                        double[] calCoorSec = mc.calcularCoorEsfCar3D(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDecimales);
                                        label1.setText(String.valueOf(calCoorSec[0]));
                                        label2.setText(String.valueOf(calCoorSec[1]));
                                        label3.setText(String.valueOf(calCoorSec[2]));
                                    });
                                } else {
                                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "El ángulo φ (Phi) debe tener valores entre 0° y 180°.");
                                }
                            } else {
                                utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "El ángulo θ (Theta) debe tener valores entre 0° y 360°.");
                            }
                        } else {
                            utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "El radio debe tener valores iguales o mayores a 0.");
                        }
                    } else {
                        utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Los datos ingresados no son números o el formato es incorrecto.");
                    }
                }
                break;
            case 6:
                // Conversión de coordenadas Esféricas a Cilíndricas
                if (txtXRadio.getText().isEmpty() || txtYAngulo.getText().isEmpty() || txtZPhi.getText().isEmpty()) {
                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Verifique que todos los campos no estén vacíos.");
                } else {
                    if (utl.isNumeric(txtXRadio.getText()) && utl.isNumeric(txtYAngulo.getText()) && utl.isNumeric(txtZPhi.getText())) {
                        // Obtenemos los valores de los inputs del conversor
                        double valorXRadio = Double.parseDouble(txtXRadio.getText());
                        double valorYAngulo = Double.parseDouble(txtYAngulo.getText());
                        double valorZPhi = Double.parseDouble(txtZPhi.getText());
                        // El ángulo Theta debe tener valores entre 0\u00b0 y 360\u00b0
                        if (valorYAngulo >= 0 && valorYAngulo <= 360) {
                            // Se realiza la conversión de las coordenadas
                            calCoor = mc.calcularCoorEsfCil(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDe);

                            lbNumDecimales.setVisible(true);
                            slider.setVisible(true);
                            lbXRadio.setText("Radio:");
                            lbXRadio.setVisible(true);
                            lbYAngulo.setText("θ (Theta):");
                            lbYAngulo.setVisible(true);
                            lbZPhi.setText("Z:");
                            lbZPhi.setVisible(true);

                            label1.setText(String.valueOf(calCoor[0]));
                            label2.setText(String.valueOf(calCoor[1]));
                            label3.setText(String.valueOf(calCoor[2]));
                            label3.setVisible(true);
                            // Cambio del valor del Slider que indican la cantidad de los números decimales
                            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                                int nDecimales = (int) BigDecimal.valueOf(newValue.doubleValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
                                double[] calCoorSec = mc.calcularCoorEsfCil(valorXRadio, valorYAngulo, valorZPhi, cbRadians.isSelected(), nDecimales);
                                label1.setText(String.valueOf(calCoorSec[0]));
                                label2.setText(String.valueOf(calCoorSec[1]));
                                label3.setText(String.valueOf(calCoorSec[2]));
                            });
                        } else {
                            utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "El ángulo θ (Theta) debe tener valores entre 0° y 360°.");
                        }
                    } else {
                        utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Los datos ingresados no son números o el formato es incorrecto.");
                    }
                }
                break;
            case 7:
                // Conversión de coordenadas Polares a Cartesianas 2D
                if (txtXRadio.getText().isEmpty() || txtYAngulo.getText().isEmpty()) {
                    utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Verifique que todos los campos no estén vacíos.");
                } else {
                    if (utl.isNumeric(txtXRadio.getText()) && utl.isNumeric(txtYAngulo.getText())) {
                        // Obtenemos los valores de los inputs del conversor
                        double valorXRadio = Double.parseDouble(txtXRadio.getText());
                        double valorYAngulo = Double.parseDouble(txtYAngulo.getText());
                        // Validamos si el número ingresado es positivo
                        if (utl.isPositive(valorXRadio) && utl.isPositive(valorYAngulo)) {
                            // El ángulo Theta debe tener valores entre 0\u00b0 y 360\u00b0
                            if (valorYAngulo >= 0 && valorYAngulo <= 360) {
                                // Se realiza la conversión de las coordenadas
                                calCoor = mc.calcularCoorPoCar2D(valorXRadio, valorYAngulo, cbRadians.isSelected(), nDe);

                                lbNumDecimales.setVisible(true);
                                slider.setVisible(true);
                                lbXRadio.setText("X:");
                                lbXRadio.setVisible(true);
                                lbYAngulo.setText("Y:");
                                lbYAngulo.setVisible(true);
                                lbZPhi.setVisible(false);

                                label1.setText(String.valueOf(calCoor[0]));
                                label2.setText(String.valueOf(calCoor[1]));
                                label3.setVisible(false);
                                // Cambio del valor del Slider que indican la cantidad de los números decimales
                                slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                                    int nDecimales = (int) BigDecimal.valueOf(newValue.doubleValue()).setScale(0, RoundingMode.HALF_UP).doubleValue();
                                    double[] calCoorSec = mc.calcularCoorPoCar2D(valorXRadio, valorYAngulo, cbRadians.isSelected(), nDecimales);
                                    label1.setText(String.valueOf(calCoorSec[0]));
                                    label2.setText(String.valueOf(calCoorSec[1]));
                                });
                            } else {
                                utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "El ángulo θ (Theta) debe tener valores entre 0° y 360°.");
                            }
                        } else {
                            utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Solo se permiten números positivos.");
                        }
                    } else {
                        utl.mostrarAlerta(rootStackPane, rootAnchorPane, "Advertencia", "Los datos ingresados no son números o el formato es incorrecto.");
                    }
                }
                break;
        }
    }
}
