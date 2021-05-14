package sample.data;

/**
 * This class contains the attributes, constructors and methods to manage
 * the crops stored in the app
 * @author Eduardo Sacristán Beltri
 * @version 1.0
 */
public class Crop {
    protected String name;
    protected float waterNeeds;
    protected float plantDistance;
    protected int requirementsLevel;
    protected String cropInfo;
    protected String sowingDate = "x";
    protected String harvestDate = "x";
    protected String months;

    /**
     * This is a constructor for objects of the Crop class. It receives 6
     * parameters.
     * @param name is the name of the crop
     * @param waterNeeds is the amount of water needed by the crop by time. Usually l/week
     * @param plantDistance is the minimum distance between plants it can be
     *                      measured in longitude or in square units.
     * @param requirementsLevel Is a number froom 1 to 4 that represents how
     *                          greedy is that crop about the nutrients of the soil.
     * @param cropInfo Is any other important information about the crop
     * @param months Is a string that contains the name of the months when this
     *               crop can be sown.
     */
   public Crop(String name, float waterNeeds, float plantDistance,
                int requirementsLevel, String cropInfo, String months) {
        this.name = name;
        this.waterNeeds = waterNeeds;
        this.plantDistance = plantDistance;
        this.requirementsLevel = requirementsLevel;
        this.cropInfo = cropInfo;
        this.months = months;
    }

    /**
     *
     * This is a constructor for objects of the Crop class. It receives 6
     *      parameters.
     * @param name is the name of the crop
     * @param waterNeeds is the amount of water needed by the crop by time.
     *                   Usually l/week
     * @param plantDistance is the minimum distance between plants it can be
     *                   measured in longitude or in square units.
     * @param requirementsLevel Is a number froom 1 to 4 that represents how
     *                   greedy is that crop about the nutrients of the soil.
     * @param cropInfo Is any other important information about the crop
     * @param months Is a string that contains the name of the months when this
     *                 crop can be sown.
     * @param sowingDate Is a string with the date when the plants has been sown
     * @param harvestDate Is a string with the date when the plants has been harvested
     */
    public Crop(String name, float waterNeeds, float plantDistance,
                int requirementsLevel, String cropInfo, String months,
                String sowingDate, String harvestDate) {
        this.name = name;
        this.waterNeeds = waterNeeds;
        this.plantDistance = plantDistance;
        this.requirementsLevel = requirementsLevel;
        this.cropInfo = cropInfo;
        this.months = months;
        this.sowingDate = sowingDate;
        this.harvestDate = harvestDate;
    }

    /**
     * An Override of the toString method
     * @return A string with the data of the crops sowed in the area in a short format
     */
    @Override
    public String toString() {
        return sowingDate +", " + name + ". Harvest date: " + harvestDate;
    }

    //-----------------SETTERS & GETTERS---------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWaterNeeds() {
        return waterNeeds;
    }

    public void setWaterNeeds(float waterNeeds) {
        this.waterNeeds = waterNeeds;
    }

    public float getPlantDistance() {
        return plantDistance;
    }

    public void setPlantDistance(float plantDistance) {
        this.plantDistance = plantDistance;
    }

    public int getRequirementsLevel() {
        return requirementsLevel;
    }

    public void setRequirementsLevel(int requirementsLevel) {
        this.requirementsLevel = requirementsLevel;
    }

    public String getCropInfo() {
        return cropInfo;
    }

    public void setCropInfo(String cropInfo) {
        this.cropInfo = cropInfo;
    }

    public String getSowingDate() {
        return sowingDate;
    }

    public void setSowingDate(String sowingDate) {
        this.sowingDate = sowingDate;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

}
