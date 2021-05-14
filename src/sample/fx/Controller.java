package sample.fx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.data.Area;
import sample.data.Crop;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class contains the attributes, constructors and methods to manage the
 * interaction of the controls and displays of the app with the code.
 * @author Eduardo Sacristan Beltri
 * @version 1.0
 */
public class Controller implements Initializable {

    @FXML
    private TextArea areaHistory;
    @FXML
    private TextField txtMonths;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtLength;
    @FXML
    private TextField txtWidth;
    @FXML
    private Button btnCrteate;
    @FXML
    private ComboBox comboCrop;
    @FXML
    private ComboBox comboArea;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtWaterNeeds;
    @FXML
    private TextField txtDistance;
    @FXML
    private TextField txtReq;
    @FXML
    private TextArea txtInfo;
    @FXML
    private Label lblNext;
    @FXML
    private TextField txtHarvestDate;

    ArrayList<Crop> availableCrops = new ArrayList<Crop>();
    ArrayList<Area> availableAreas = new ArrayList<Area>();

    /**
     * This method initializes the app
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method controls the button that creates new areas taking the data
     * from the area text fields
     * @param actionEvent is the actions that starts the method
     */
    public void btnCreateAction(ActionEvent actionEvent) {
        String name;
        float length;
        float width;

        name = txtId.getText();
        length = Float.parseFloat(txtLength.getText());
        width = Float.parseFloat(txtWidth.getText());

        availableAreas.add(new Area(name, length, width));
        for(int i = 0; i < availableAreas.size(); i++) {
            if(!comboArea.getItems().contains(availableAreas.get(i).toString()))
                comboArea.getItems().add(availableAreas.get(i).toString());
        }

        System.out.println("creado el area");

        txtId.clear();
        txtLength.clear();
        txtWidth.clear();
    }

    /**
     * This method controls the button that adds an existing crop to an
     * existing area. It takes the data from the combo boxes.
     * @param actionEvent
     */
    public void btnSowAction(ActionEvent actionEvent) {
        Crop cropToSow = null;
        Area areaWhereToSow;

        for(int i = 0; i < availableCrops.size(); i++) {
            if(availableCrops.get(i).getName().equals(comboCrop.getValue()))
                cropToSow = availableCrops.get(i);
        }

        for (int i = 0; i < availableAreas.size(); i++) {
            if(availableAreas.get(i).toString().equals(comboArea.getValue()))
                availableAreas.get(i).addCrop(cropToSow, LocalDate.now().toString());
        }

        System.out.println(cropToSow.getName());
    }

    /**
     * This method controls de button for harvest. I takes the data from the
     * combo boxes.
     * @param actionEvent
     */
    public void btnHarvestAction(ActionEvent actionEvent) {
        Area area = null;
        Crop cropToHarvest;
        for (int i = 0; i < availableAreas.size(); i++) {
            if(availableAreas.get(i).toString().equals(comboArea.getValue())) {
                area = availableAreas.get(i);
            }
        }
        for(int i = 0; i < availableCrops.size(); i++) {
            if(availableCrops.get(i).getName().equals(comboCrop.getValue()))
                cropToHarvest = availableCrops.get(i);
        }
        area.getCrops().get(area.getCrops().size() - 1).
            setHarvestDate(txtHarvestDate.getText());
    }

    /**
     * This method controls the button that starts the sugestsNextCrop method
     * It gives the information using a label next to the button.
     * @param actionEvent
     */
    public void suggestAction(ActionEvent actionEvent) {
        Area area = null;

        for (int i = 0; i < availableAreas.size(); i++) {
            if(availableAreas.get(i).toString().equals(comboArea.getValue())) {
                area = availableAreas.get(i);
            }
        }
        lblNext.setText(area.suggestNextCrop(availableCrops));
    }

    /**
     * Tis method takes the data from the textFields of the area and creates a new
     * area with that info.
     * @param actionEvent
     */
    public void btnCreateCrop(ActionEvent actionEvent) {
        String name = txtName.getText();
        float waterNeeds = Float.parseFloat(txtWaterNeeds.getText());
        float plantDistance = Float.parseFloat(txtDistance.getText());
        int requirementsLevel = Integer.parseInt(txtReq.getText());
        String cropInfo = txtInfo.getText();
        String months = txtMonths.getText();

        availableCrops.add(new Crop(name,waterNeeds,plantDistance,requirementsLevel,
                cropInfo, months));

        for(int i = 0; i < availableCrops.size(); i++) {
            if(!comboCrop.getItems().contains(availableCrops.get(i).getName()))
                comboCrop.getItems().add(availableCrops.get(i).getName());
        }

        System.out.println("cultivo creado");

        txtName.clear();
        txtWaterNeeds.clear();
        txtDistance.clear();
        txtReq.clear();
        txtInfo.clear();
        txtMonths.clear();

    }

    /**
     * This method controls the button than makes appear the history of all the
     * crops that have been sown in an area selected in the area combo box.
     * @param actionEvent
     */
    public void btnCheckHistory(ActionEvent actionEvent) {
        areaHistory.clear();
        Area area = null;
        for(int i = 0; i < availableAreas.size(); i++) {
            if(availableAreas.get(i).toString().equals(comboArea.getValue())){
                area = availableAreas.get(i);
            }
        }
        List<String>history = area.showHistory();
        for (int i = 0; i < history.size(); i++) {
            areaHistory.appendText(history.get(i) + "\n");
            System.out.println(history.get(i));
        }
        System.out.println(area.getId());
        System.out.println(area.showHistory().toString());
    }

    /**
     * This buttons saves the data of all the classes of the app in some text files
     * @param actionEvent
     */
    public void btnSaveData(ActionEvent actionEvent) {
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter("available_crops.txt");

            for(int i = 0; i < availableCrops.size(); i++) {
                printWriter.println(availableCrops.get(i).getName() + "¬" +
                        availableCrops.get(i).getWaterNeeds() + "¬" +
                        availableCrops.get(i).getPlantDistance() + "¬" +
                        availableCrops.get(i).getRequirementsLevel() + "¬" +
                        availableCrops.get(i).getCropInfo() + "¬" +
                        availableCrops.get(i).getMonths());
            }
            printWriter.close();

            printWriter = new PrintWriter("available_areas.txt");

            for(int i = 0; i < availableAreas.size(); i++) {
                printWriter.println(availableAreas.get(i).getId() + "¬" +
                        availableAreas.get(i).getLength() + "¬" +
                        availableAreas.get(i).getWidth());
            }
            printWriter.close();

            for(int i = 0; i < availableAreas.size(); i++) {
                printWriter = new PrintWriter("area" + i + ".txt");

                for (int j = 0; j < availableAreas.get(i).getCrops().size(); j++) {
                    printWriter.println(availableAreas.get(i).getCrops().get(j).getName()
                            + "¬" + availableAreas.get(i).getCrops().get(j).getWaterNeeds()
                            + "¬" + availableAreas.get(i).getCrops().get(j).getPlantDistance()
                            + "¬" + availableAreas.get(i).getCrops().get(j).getRequirementsLevel()
                            + "¬" + availableAreas.get(i).getCrops().get(j).getCropInfo()
                            + "¬" + availableAreas.get(i).getCrops().get(j).getMonths()
                            + "¬" + availableAreas.get(i).getCrops().get(j).getSowingDate()
                            + "¬" + availableAreas.get(i).getCrops().get(j).getHarvestDate());
                }
                printWriter.close();
            }
            System.out.println("Data saved");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This buttons loads the data of all the classes of the app in some text files
     * @param actionEvent
     */
    public void btnLoadData(ActionEvent actionEvent) {
        String line;
        String[] loadedLine;

        try {
            BufferedReader inputFile = new BufferedReader(
                    new FileReader(new File("available_crops.txt")));

            line = inputFile.readLine();
            while (line != null) {
                loadedLine = line.split("¬");
                availableCrops.add(new Crop(loadedLine[0],
                        Float.parseFloat(loadedLine[1]),
                        Float.parseFloat(loadedLine[2]),
                        Integer.parseInt(loadedLine[3]),
                        loadedLine[4], loadedLine[5]));

                line = inputFile.readLine();
            }
            inputFile.close();

            BufferedReader inputFile2 = new BufferedReader(new FileReader(new
                    File("available_areas.txt")));
            line = inputFile2.readLine();
            while(line != null){
                loadedLine = line.split("¬");
                availableAreas.add(new Area(loadedLine[0],
                        Float.parseFloat(loadedLine[1]),
                        Float.parseFloat(loadedLine[2])));

                line =inputFile2.readLine();
            }
            inputFile2.close();
            //------------------------------------------------------------------
            for (int i = 0; i < availableAreas.size(); i++) {
                String fileName = "area" + i + ".txt";
                inputFile = new BufferedReader(new FileReader(new
                    File(fileName)));

                line = inputFile.readLine();
                while (line != null) {
                    loadedLine = line.split("¬");
                    availableAreas.get(i).addCrop(new Crop(loadedLine[0],
                            Float.parseFloat(loadedLine[1]),
                            Float.parseFloat(loadedLine[2]),
                            Integer.parseInt(loadedLine[3]),
                            loadedLine[4],
                            loadedLine[5],
                            loadedLine[6],
                            loadedLine[7]));
                    line = inputFile.readLine();
                }
                inputFile.close();
                comboArea.getItems().add(availableAreas.get(i).toString());
                comboCrop.getItems().add(availableCrops.get(i).getName());
            }
        }

        catch (IOException fileError) {
            fileError.getStackTrace();
        }
    }
}
