import java.util.ArrayList;

public class Kicker{
    private String name;
    private int position;
    private double attempts;
    private double made;
    private double fiftyPlus;
    private double pct;
    private double xp;
    private double ffppg;
    private double totalFFP;
    private double draftOrder;
    private double value;
    private String team;
    private int byeWeek;
    private double id;

    public Kicker(String name, String team, int position, double attempts, double made, double fiftyPlus, double pct,
                  double xp, double ffppg, double totalFFP, double draftOrder, double value, int byeWeek, double id) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.attempts = attempts;
        this.made = made;
        this.fiftyPlus = fiftyPlus;
        this.pct = pct;
        this.xp = xp;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
        this.draftOrder = draftOrder;
        this.value = value;
        this.byeWeek = byeWeek;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String teamName) {
        this.team = teamName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getAttempts() {
        return attempts;
    }

    public void setAttempts(double attempts) {
        this.attempts = attempts;
    }

    public double getMade() {
        return made;
    }

    public void setMade(double made) {
        this.made = made;
    }

    public double getFiftyPlus() {
        return fiftyPlus;
    }

    public void setFiftyPlus(double fiftyPlus) {
        this.fiftyPlus = fiftyPlus;
    }

    public double getPct() {
        return pct;
    }

    public void setPct(double pct) {
        this.pct = pct;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public double getFfppg() {
        return ffppg;
    }

    public void setFfppg(double ffppg) {
        this.ffppg = ffppg;
    }

    public double getTotalFFP() {
        return totalFFP;
    }

    public void setTotalFFP(double totalFFP) {
        this.totalFFP = totalFFP;
    }

    public double getDraftOrder() {
        return draftOrder;
    }

    public void setDraftOrder(double draftOrder) {
        this.draftOrder = draftOrder;
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

    public static ArrayList<Kicker> werteGewichten(ArrayList<Kicker> kicker, double tierS, double tierA, double tierB,
                                                   double tierC, double tierD, double tierE) {
        for (int i = 0; i < kicker.size(); i++) {
            kicker.get(i).setAttempts((33 - kicker.get(i).getAttempts()) * tierD);
            kicker.get(i).setMade((33 - kicker.get(i).getMade()) * tierB);
            kicker.get(i).setFiftyPlus((33 - kicker.get(i).getFiftyPlus()) * tierC);
            kicker.get(i).setPct((33 - kicker.get(i).getPct()) * tierD);
            kicker.get(i).setXp((33 - kicker.get(i).getXp()) * tierC);
            kicker.get(i).setFfppg((33 - kicker.get(i).getFfppg()) * tierA);
            kicker.get(i).setTotalFFP((33 - kicker.get(i).getTotalFFP()) * tierA);
            kicker.get(i).setDraftOrder((33 - kicker.get(i).getDraftOrder()) * tierS);
        }
        return kicker;
    }

    public static ArrayList<Kicker> reverseWerteGewichten(ArrayList<Kicker> kicker, double tierS, double tierA,
                                                          double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < kicker.size(); i++) {
            kicker.get(i).setAttempts(33 - kicker.get(i).getAttempts() / tierD);
            kicker.get(i).setMade(33 - kicker.get(i).getMade() / tierB);
            kicker.get(i).setFiftyPlus(33 - kicker.get(i).getFiftyPlus() / tierC);
            kicker.get(i).setPct(33 - kicker.get(i).getPct() / tierD);
            kicker.get(i).setXp(33 - kicker.get(i).getXp() / tierC);
            kicker.get(i).setFfppg(33 - kicker.get(i).getFfppg() / tierA);
            kicker.get(i).setTotalFFP(33 - kicker.get(i).getTotalFFP() / tierA);
            kicker.get(i).setDraftOrder(33 - kicker.get(i).getDraftOrder() / tierS);
        }
        return kicker;
    }

    public static ArrayList<Kicker> valueBerechnen(ArrayList<Kicker> kicker) {
        for(int i = 0; i < kicker.size(); i++) {
            kicker.get(i).setValue(kicker.get(i).getAttempts() + kicker.get(i).getMade() + kicker.get(i).getFiftyPlus()
                    + kicker.get(i).getPct() + kicker.get(i).getXp() + kicker.get(i).getFfppg() +
                    kicker.get(i).getFfppg() + kicker.get(i).getDraftOrder());
        }
        return kicker;
    }

    public static ArrayList<Kicker> sortAfterValue(ArrayList<Kicker> kicker) {
        Kicker pivot = new Kicker("pivot", "", 0,0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0);
        int pivotNumber;
        ArrayList<Kicker> sortedKickers = new ArrayList<Kicker>();
        while(!kicker.isEmpty()) {
            pivot = kicker.get(kicker.size() - 1);
            pivotNumber = kicker.size() - 1;
            for(int j = 0; j < kicker.size() - 1; j++) {
                if(kicker.get(j).getValue() >= pivot.getValue()) {
                    if(kicker.get(j).getValue() > pivot.getValue() || (kicker.get(j).getValue() == pivot.getValue()
                            && kicker.get(j).getDraftOrder() > pivot.getDraftOrder())) {
                        pivot = kicker.get(j);
                        pivotNumber = j;
                    }
                }
            }
            if(kicker.size() > 1) {
                sortedKickers.add(pivot);
                kicker.remove(pivotNumber);
            } else {
                sortedKickers.add(pivot);
                kicker.remove(kicker.size() - 1);
            }

        }

        for(int i = 0; i < sortedKickers.size(); i ++) {
            sortedKickers.get(i).setPosition(i + 1);
        }
        return sortedKickers;
    }

    /**
     * Schreibt alle Attribute in ein Array, damit automatisch prüfbar
     *
     * @param kickerArrayList Liste aller Runningbacks
     *
     */
    public static ArrayList<ArrayList<Double>> createArrayWithKickerInformation(ArrayList<Kicker> kickerArrayList) {
        Kicker kick;
        ArrayList<ArrayList<Double>> listOfKicker = new ArrayList<>();

        for(int i = 0; i < kickerArrayList.size(); i++) {
            kick = kickerArrayList.get(i);
            double[] kickerStats = {kick.getId(), kick.getDraftOrder(), kick.getAttempts(), kick.getMade(),
                    kick.getPct(), kick.getXp(), kick.getFiftyPlus(), kick.getFfppg(), kick.getTotalFFP()};
            ArrayList<Double> kicker = new ArrayList<Double>();
            for(int j = 0; j < kickerStats.length; j++) {
                kicker.add(kickerStats[j]);
            }
            listOfKicker.add(kicker);
        }
        return listOfKicker;
    }

    /**
     * Alle Werte der Kicker werden sortiert und eingeranked
     * Höchster Wert -> 1, Niedrigster Wert -> |Kicker|
     *
     * @param kickerArrayList Liste aller Kicker
     *
     * @return
     */
    public static ArrayList<ArrayList<Double>> sortKickerList(ArrayList<Kicker> kickerArrayList) {
        ArrayList<ArrayList<Double>> listOfKicker = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> sortedListOfKicker = createArrayWithKickerInformation(kickerArrayList);
        double[] pivot = new double[3];
        int counter = 0;
        int position = -1;
        Boolean same = false;
        for(int i = 1; i <= 8; i++) {
            pivot[2] = -1;
            listOfKicker.addAll(sortedListOfKicker);
            while(!listOfKicker.isEmpty()) {
                pivot[0] = listOfKicker.get(0).get(0);
                pivot[1] = listOfKicker.get(0).get(i);
                for (int j = 0; j < listOfKicker.size(); j++) {
                    if(listOfKicker.get(j).get(i) == pivot[2]) {
                        pivot[0] = listOfKicker.get(j).get(0);
                        pivot[1] = listOfKicker.get(j).get(i);
                        same = true;
                        break;
                    } else if(listOfKicker.get(j).get(i) > pivot[1]) {
                        pivot[0] = listOfKicker.get(j).get(0);
                        pivot[1] = listOfKicker.get(j).get(i);
                    }
                }
                do {
                    position += 1;
                } while(pivot[0] != listOfKicker.get(position).get(0));
                if(!same) {
                    counter += 1;
                } else {
                    same = false;
                }
                sortedListOfKicker.get((int)pivot[0] - 1).set(i, (double)counter);
                listOfKicker.remove(position);
                position = -1;
                pivot[2] = pivot[1];
            }
            counter = 0;
        }
        return sortedListOfKicker;
    }

    /**
     * Zurückschreiben aller Werte aus der doppelten ArrayListe zurück in KickerListe
     *
     * @param kickerArrayList Liste aller Kicker
     *
     * @return Sortierte Liste aller Kicker (eingeranked/sortiert)
     */
    public static ArrayList<Kicker> rewriteKickerValues(ArrayList<Kicker> kickerArrayList) {
        ArrayList<ArrayList<Double>> listOfKicker = sortKickerList(kickerArrayList);
        for (Kicker kicker:kickerArrayList) {
            kicker.setDraftOrder(listOfKicker.get((int)kicker.getId() - 1).get(1));
            kicker.setAttempts(listOfKicker.get((int)kicker.getId() - 1).get(2));
            kicker.setMade(listOfKicker.get((int)kicker.getId() - 1).get(3));
            kicker.setPct(listOfKicker.get((int)kicker.getId() - 1).get(4));
            kicker.setXp(listOfKicker.get((int)kicker.getId() - 1).get(5));
            kicker.setFiftyPlus(listOfKicker.get((int)kicker.getId() - 1).get(6));
            kicker.setFfppg(listOfKicker.get((int)kicker.getId() - 1).get(7));
            kicker.setTotalFFP(listOfKicker.get((int)kicker.getId() - 1).get(8));
        }
        return kickerArrayList;
    }

    public static void printKickers(ArrayList<Kicker> kicker) {
        System.out.println("Kicker-List:");
        for(int i = 0; i < kicker.size(); i++) {
            System.out.println(kicker.get(i).getPosition() + ", " + kicker.get(i).getName() + " (" +
                    kicker.get(i).getTeam() + ")" + ", " + kicker.get(i).getValue());
        }
    }
}