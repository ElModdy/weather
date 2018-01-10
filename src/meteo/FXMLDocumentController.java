/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteo;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.DefaultListModel;

/**
 *
 * @author usr
 */
public class FXMLDocumentController implements Initializable, UIMeteoController {
    
    @FXML private ListView<String> placesList;
    @FXML private Label desc;
    @FXML private Label temp;
    @FXML private TextField searchField;
    
    protected ListProperty<String> listProperty = new SimpleListProperty<>();
    
    Meteo meteo;
    Map<String,String> places;

    public void clearText(){
        desc.setText("");
        temp.setText("");
    }

    public void setTemp(String temp){
        this.temp.setText(temp);
    }

    public void setDesc(String desc){
        this.desc.setText(desc);
    }

    public void setPlaces(Map places){
        this.places = places;
        listProperty.set(FXCollections.observableArrayList(places.keySet()));
    }
    
    public void onPlaceSelected(){
        clearText();
        meteo.getWeat(places.get(placesList.getSelectionModel().getSelectedItem()));
    }
    
    public void onKeyTyped(KeyEvent event) throws UnsupportedEncodingException{
        if(!event.getCode().equals(KeyCode.BACK_SPACE))
           meteo.getPlaces(searchField.getText());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        meteo = new Meteo(this);
        placesList.itemsProperty().bind(listProperty);
        
    }
    
}
