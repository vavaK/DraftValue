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

    public static ArrayList<Defense> werteGewichten(ArrayList<Defense> defense, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < defense.size(); i++) {
            defense.get(i).setSacks((33 - defense.get(i).getSacks()) * tierC);
            defense.get(i).setInts((33 - defense.get(i).getInts()) * tierB);
            defense.get(i).setFumbleRecoveries((33 - defense.get(i).getFumbleRecoveries()) * tierD);
            defense.get(i).setSafeties((33 - defense.get(i).getSafeties()) * tierD);
            defense.get(i).setTds((33 - defense.get(i).getTds()) * tierE);
            defense.get(i).setOrder((33 - defense.get(i).getOrder()) * tierS);
        }
        return defense;
    }

    public static ArrayList<Defense> valueBerechnen(ArrayList<Defense> defense) {
        for(int i = 0; i < defense.size(); i++) {
            defense.get(i).setValue(defense.get(i).getSacks() + defense.get(i).getInts() + defense.get(i).getFumbleRecoveries() + defense.get(i).getSafeties() + defense.get(i).getTds() + defense.get(i).getOrder());
        }
        return defense;
    }

    public static ArrayList<Defense> sortAfterValue(ArrayList<Defense> defense) {
        Defense pivot = new Defense("pivot", 0, 0, 0, 0, 0, 0, 0, 0);
        int pivotNumber;
        ArrayList<Defense> sortedDefense = new ArrayList<Defense>();
        while(!defense.isEmpty()) {
            pivot = defense.get(defense.size() - 1);
            pivotNumber = defense.size() - 1;
            for(int j = 0; j < defense.size() - 1; j++) {
                if(defense.get(j).getValue() >= pivot.getValue()) {
                    if(defense.get(j).getValue() > pivot.getValue() || (defense.get(j).getValue() == pivot.getValue() && defense.get(j).getOrder() > pivot.getOrder())) {
                        pivot = defense.get(j);
                        pivotNumber = j;
                    }
                }
            }
            if(defense.size() > 1) {
                sortedDefense.add(pivot);
                defense.remove(pivotNumber);
            } else {
                sortedDefense.add(pivot);
                defense.remove(defense.size() - 1);
            }

        }
        for(int i = 0; i < sortedDefense.size(); i ++) {
            sortedDefense.get(i).setPosition(i + 1);
        }
        return sortedDefense;
    }

    public static void printDefense(ArrayList<Defense> defense) {
        System.out.println("Defense List:");
        for(int i = 0; i < defense.size(); i++) {
            System.out.println(defense.get(i).getPosition() + ", " + defense.get(i).getName() + ", " + defense.get(i).getValue());
        }
    }
}