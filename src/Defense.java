import java.util.ArrayList;

public class Defense {
    private String name;
    private int position;
    private double sacks;
    private double ints;
    private double fumbleRecoveries;
    private double safeties;
    private double tds;
    private double order;
    private double value;

    public Defense(String name, int position, double sacks, double ints, double fumbleRecoveries, double safeties, double tds, double order, double value) {
        this.name = name;
        this.position = position;
        this.sacks = sacks;
        this.ints = ints;
        this.fumbleRecoveries = fumbleRecoveries;
        this.safeties = safeties;
        this.tds = tds;
        this.order = order;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getSacks() {
        return sacks;
    }

    public void setSacks(double sacks) {
        this.sacks = sacks;
    }

    public double getInts() {
        return ints;
    }

    public void setInts(double ints) {
        this.ints = ints;
    }

    public double getFumbleRecoveries() {
        return fumbleRecoveries;
    }

    public void setFumbleRecoveries(double fumbleRecoveries) {
        this.fumbleRecoveries = fumbleRecoveries;
    }

    public double getSafeties() {
        return safeties;
    }

    public void setSafeties(double safeties) {
        this.safeties = safeties;
    }

    public double getTds() {
        return tds;
    }

    public void setTds(double tds) {
        this.tds = tds;
    }

    public double getOrder() {
        return order;
    }

    public void setOrder(double order) {
        this.order = order;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ArrayList<Defense> werteGewichten(ArrayList<Defense> defense, double tierOne, double tierTwo, double tierThree, double tierFour, double tierFive) {
        for (int i = 0; i < defense.size(); i++) {
            defense.get(i).setSacks(defense.get(i).getSacks() * tierFour);
            defense.get(i).setInts(defense.get(i).getInts() * tierTwo);
            defense.get(i).setFumbleRecoveries(defense.get(i).getFumbleRecoveries() * tierThree);
            defense.get(i).setSafeties(defense.get(i).getSafeties() * tierFour);
            defense.get(i).setTds(defense.get(i).getTds() * tierFive);
            defense.get(i).setOrder(defense.get(i).getOrder() * tierOne);
        }
        return defense;
    }

    public ArrayList<Defense> wertBerechnen(ArrayList<Defense> defense) {
        for(int i = 0; i < defense.size(); i++) {
            defense.get(i).setValue(defense.get(i).getSacks() + defense.get(i).getInts() + defense.get(i).getFumbleRecoveries() + defense.get(i).getSafeties() + defense.get(i).getTds() + defense.get(i).getOrder());
        }
        return defense;
    }
}