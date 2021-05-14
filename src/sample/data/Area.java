package sample.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains the attributes, methods and constructor to create and
 * manage the areas of the vegetable garden where the crops are going to be sown.
 * @author Eduardo Sacristán Beltri
 * @version 1.0
 */
public class Area {
    protected String id;
    protected float length;
    protected float width;
    protected List<Crop> crops;
    protected List<String> notes;

    /**
     * This is the main constructor for the Area class
     * @param id is name of the area
     * @param length is the length of the area
     * @param width is the width of the area
     */
    public Area(String id, float length, float width) {
        this.id = id;
        this.length = length;
        this.width = width;
        notes = new ArrayList<String>();
        crops = new ArrayList<Crop>();
    }

    /**
     * This method creates a new List where all the works done in an area are
     * represented and sorted by data.
     * @return A string that contains the data of the works done in an area.
     */
    public List<String> showHistory() {
        //create List, add all and sort
        //I can use this method y the loadData method
        List<String> history = new ArrayList<>();
        StringBuilder areaHistory = new StringBuilder();
        for(int i = 0; i < crops.size(); i++) {
            history.add(crops.get(i).toString());
        }

        Collections.sort(history, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        return history;
    }

    /**
     * This method adds a new crop to the crops List of the area using two
     * parameters
     * @param crop is the Crop object
     * @param sowingDate is a string with the date when the crop has been sown
     */
    public void addCrop(Crop crop, String sowingDate) {
        crops.add(crop);
        // adding the sowing date
        crop.setSowingDate(sowingDate);
        System.out.println("Crop added to the area");
    }

    /**
     * This method adds a new crop to the crops List of the area using thee
     * parameters
     * @param crop is the Crop object
     * @param sowingDate is a is a string with the date when the crop has been sown
     * @param harvestDate is a string with the date when the crop has been harvested
     */
    public void addCrop(Crop crop, String sowingDate, String harvestDate) {
        crops.add(crop);
        // adding the sowing date
        crop.setSowingDate(sowingDate);
        crop.setHarvestDate(harvestDate);
        System.out.println("Crop added to the area");
    }

    /**
     * This method adds a new crop to the crops List of the area using one
     * parameter
     * @param crop is the Crop object
     */
    public void addCrop(Crop crop) {
        crops.add(crop);
    }

    /**
     * This method checks the data of a crop in the availableCrops list and
     * compares it with the previous crops sown in the area and the currend data
     * to offer suggestions about the most suitable crop that should be sown
     * in the area.
     * @param availableCrops is the list of the availableCrops stored
     * @return A string with the name of the suggested crop
     */
    public String suggestNextCrop(List<Crop> availableCrops) {
        String answer = "Anything";
        if(crops.size()>0) {
            for( int i = 0; i < availableCrops.size(); i++){
                if(availableCrops.get(i).getRequirementsLevel() <
                        crops.get(crops.size() - 1).getRequirementsLevel()
                        && availableCrops.get(i).getMonths().contains(LocalDate.now().
                        getMonth().toString().toLowerCase())) {
                    System.out.println(availableCrops.get(i).getName());
                    answer = availableCrops.get(i).getName();
                }
                else if (crops.get(crops.size() - 1).getRequirementsLevel() == 1) {
                    System.out.println("I highly reccomend you leave this area " +
                            "as fallow land");
                    answer = "Falow Land";
                }
            }
        }
        else {
            System.out.println("There is no previous crop in this area");
            answer = "Anything";
        }
        return answer;
    }

    /**
     * An Override of the toString method
     * @return A string with the data of the area in a short format
     */
    @Override
    public String toString(){
        return (id + "(" + width + " - " + length + ")");
    }

    //-------  SETTER & GETTERS-----------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public List<Crop> getCrops() {
        return crops;
    }

    public void setCrops(List<Crop> crops) {
        this.crops = crops;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }



}
