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
    private int byeWeek;

    private double id;

    private double ffppg;

    private double totalffp;

    public Defense(String name, int position, double sacks, double ints, double fumbleRecoveries, double safeties, double tds, double order, double value, int byeeWeek, double id, double ffppg, double totalffp) {
        this.name = name;
        this.position = position;
        this.sacks = sacks;
        this.ints = ints;
        this.fumbleRecoveries = fumbleRecoveries;
        this.safeties = safeties;
        this.tds = tds;
        this.order = order;
        this.value = value;
        this.byeWeek = byeeWeek;
        this.id = id;
        this.ffppg = ffppg;
        this.totalffp = totalffp;
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

    public int getByeWeek() {
        return byeWeek;
    }

    public void setByeWeek(int byeWeek) {
        this.byeWeek = byeWeek;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getFfppg() {
        return ffppg;
    }

    public void setFfppg(double ffppg) {
        this.ffppg = ffppg;
    }

    public double getTotalffp() {
        return totalffp;
    }

    public void setTotalffp(double totalffp) {
        this.totalffp = totalffp;
    }

    public static ArrayList<Defense> werteGewichten(ArrayList<Defense> defense, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < defense.size(); i++) {
            defense.get(i).setSacks((33 - defense.get(i).getSacks()) * tierC);
            defense.get(i).setInts((33 - defense.get(i).getInts()) * tierB);
            defense.get(i).setFumbleRecoveries((33 - defense.get(i).getFumbleRecoveries()) * tierD);
            defense.get(i).setSafeties((33 - defense.get(i).getSafeties()) * tierD);
            defense.get(i).setTds((33 - defense.get(i).getTds()) * tierE);
            defense.get(i).setOrder((33 - defense.get(i).getOrder()) * tierS);
            defense.get(i).setFfppg((33 - defense.get(i).getFfppg()) * tierA);
            defense.get(i).setTotalffp((33 - defense.get(i).getTotalffp()) * tierA);
        }
        return defense;
    }

    public static ArrayList<Defense> reverseWerteGewichten(ArrayList<Defense> defense, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < defense.size(); i++) {
            defense.get(i).setSacks(33 - defense.get(i).getSacks() / tierC);
            defense.get(i).setInts(33 - defense.get(i).getInts() / tierB);
            defense.get(i).setFumbleRecoveries(33 - defense.get(i).getFumbleRecoveries() / tierD);
            defense.get(i).setSafeties(33 - defense.get(i).getSafeties() / tierD);
            defense.get(i).setTds(33 - defense.get(i).getTds() / tierE);
            defense.get(i).setOrder(33 - defense.get(i).getOrder() / tierS);
            defense.get(i).setFfppg(33 - defense.get(i).getFfppg() / tierA);
            defense.get(i).setTotalffp(33 - defense.get(i).getTotalffp() / tierA);
        }
        return defense;
    }

    public static ArrayList<Defense> valueBerechnen(ArrayList<Defense> defense) {
        for(int i = 0; i < defense.size(); i++) {
            defense.get(i).setValue(defense.get(i).getSacks() + defense.get(i).getInts() + defense.get(i).getFumbleRecoveries() + defense.get(i).getSafeties() + defense.get(i).getTds() + defense.get(i).getOrder() + defense.get(i).getFfppg() + defense.get(i).getTotalffp());
        }
        return defense;
    }

    public static ArrayList<Defense> sortAfterValue(ArrayList<Defense> defense) {
        Defense pivot = new Defense("pivot", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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

    /**
     * Schreibt alle Attribute in ein Array, damit automatisch prüfbar
     *
     * @param defenseArrayList Liste aller Runningbacks
     *
     */
    public static ArrayList<ArrayList<Double>> createArrayWithDefenseInformation(ArrayList<Defense> defenseArrayList) {
        Defense def;
        ArrayList<ArrayList<Double>> listOfDefense = new ArrayList<>();

        for(int i = 0; i < defenseArrayList.size(); i++) {
            def = defenseArrayList.get(i);
            double[] defenseStats = {def.getId(), def.getOrder(), def.getSacks(), def.getSafeties(), def.getFumbleRecoveries(), def.getInts(), def.getTds(), def.getFfppg(), def.getTotalffp()};
            ArrayList<Double> defense = new ArrayList<Double>();
            for(int j = 0; j < defenseStats.length; j++) {
                defense.add(defenseStats[j]);
            }
            listOfDefense.add(defense);
        }
        return listOfDefense;
    }

    /**
     * Alle Werte der Defense werden sortiert und eingeranked
     * Höchster Wert -> 1, Niedrigster Wert -> |Defense|
     *
     * @param defenseArrayList Liste aller Defense
     *
     * @return
     */
    public static ArrayList<ArrayList<Double>> sortDefenseList(ArrayList<Defense> defenseArrayList) {
        ArrayList<ArrayList<Double>> listOfDefense = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> sortedListOfDefense = createArrayWithDefenseInformation(defenseArrayList);
        double[] pivot = new double[3];
        int counter = 0;
        int position = -1;
        Boolean same = false;
        Boolean reverse = false;
        for(int i = 1; i <= 8; i++) {
            pivot[2] = -1;
            listOfDefense.addAll(sortedListOfDefense);
            if(i == 6) {
                reverse = true;
            } else {
                reverse = false;
            }
            while(!listOfDefense.isEmpty()) {
                pivot[0] = listOfDefense.get(0).get(0);
                pivot[1] = listOfDefense.get(0).get(i);
                for (int j = 0; j < listOfDefense.size(); j++) {
                    if(listOfDefense.get(j).get(i) == pivot[2]) {
                        pivot[0] = listOfDefense.get(j).get(0);
                        pivot[1] = listOfDefense.get(j).get(i);
                        same = true;
                        break;
                    } else if(listOfDefense.get(j).get(i) > pivot[1] && !reverse) {
                        pivot[0] = listOfDefense.get(j).get(0);
                        pivot[1] = listOfDefense.get(j).get(i);
                    } else if(listOfDefense.get(j).get(i) < pivot[1] && reverse) {
                        pivot[0] = listOfDefense.get(j).get(0);
                        pivot[1] = listOfDefense.get(j).get(i);
                    }
                }
                do {
                    position += 1;
                } while(pivot[0] != listOfDefense.get(position).get(0));
                if(!same) {
                    counter += 1;
                } else {
                    same = false;
                }
                sortedListOfDefense.get((int)pivot[0] - 1).set(i, (double)counter);
                listOfDefense.remove(position);
                position = -1;
                pivot[2] = pivot[1];
            }
            counter = 0;
        }
        return sortedListOfDefense;
    }

    /**
     * Zurückschreiben aller Werte aus der doppelten ArrayListe zurück in KickerListe
     *
     * @param defenseArrayList Liste aller Defense
     *
     * @return Sortierte Liste aller Kicker (eingeranked/sortiert)
     */
    public static ArrayList<Defense> rewriteDefenseValues(ArrayList<Defense> defenseArrayList) {
        ArrayList<ArrayList<Double>> listOfDefense = sortDefenseList(defenseArrayList);
        for (Defense defense:defenseArrayList) {
            defense.setOrder(listOfDefense.get((int)defense.getId() - 1).get(1));
            defense.setSacks(listOfDefense.get((int)defense.getId() - 1).get(2));
            defense.setSafeties(listOfDefense.get((int)defense.getId() - 1).get(3));
            defense.setFumbleRecoveries(listOfDefense.get((int)defense.getId() - 1).get(4));
            defense.setInts(listOfDefense.get((int)defense.getId() - 1).get(5));
            defense.setTds(listOfDefense.get((int)defense.getId() - 1).get(6));
            defense.setFfppg(listOfDefense.get((int)defense.getId() - 1).get(7));
            defense.setTotalffp(listOfDefense.get((int)defense.getId() - 1).get(8));
        }
        return defenseArrayList;
    }

    public static void printDefense(ArrayList<Defense> defense) {
        System.out.println("Defense List:");
        for(int i = 0; i < defense.size(); i++) {
            System.out.println(defense.get(i).getPosition() + ", " + defense.get(i).getName() + ", " + defense.get(i).getValue() + ", Sacks: " + defense.get(i).getSacks());
        }
    }
}