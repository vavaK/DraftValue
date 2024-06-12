import java.util.ArrayList;

public class Rbs {
    private String name;
    private String team;
    private int position;
    private double receptions;
    private double receivingYards;
    private double rushingYards;
    private double attempts;
    private double tds;
    private double ffppg;
    private double totalFFP;
    private double oLine;
    private double draftorder;
    private double value;
    private Boolean rookie;
    private int byeWeek;

    private double id;

    public Rbs(String name, String team, int position, double receptions, double  receivingYards, double rushingYards, double attempts, double tds, double ffppg, double totalFFP, double oLine, double draftorder, Boolean rookie, double value, int byeWeek, double id) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.receptions = receptions;
        this.receivingYards = receivingYards;
        this.rushingYards = rushingYards;
        this.attempts = attempts;
        this.tds = tds;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
        this.oLine = oLine;
        this.draftorder = draftorder;
        this.rookie = rookie;
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

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getReceptions() {
        return receptions;
    }

    public void setReceptions(double receptions) {
        this.receptions = receptions;
    }

    public double getReceivingYards() {
        return receivingYards;
    }

    public void setReceivingYards(double receivingYards) {
        this.receivingYards = receivingYards;
    }

    public double getRushingYards() {
        return rushingYards;
    }

    public void setRushingYards(double rushingYards) {
        this.rushingYards = rushingYards;
    }

    public double getAttempts() {
        return attempts;
    }

    public void setAttempts(double attempts) {
        this.attempts = attempts;
    }

    public double getTds() {
        return tds;
    }

    public void setTds(double tds) {
        this.tds = tds;
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

    public double getoLine() {
        return oLine;
    }

    public void setoLine(double oLine) {
        this.oLine = oLine;
    }

    public double getDraftorder() {
        return draftorder;
    }

    public void setDraftorder(double draftorder) {
        this.draftorder = draftorder;
    }

    public Boolean getRookie() {
        return rookie;
    }

    public void setRookie(Boolean rookie) {
        this.rookie = rookie;
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

    public static ArrayList<Rbs> werteGewichten(ArrayList<Rbs> rb, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < rb.size(); i++) {
            rb.get(i).setReceptions((42 - rb.get(i).getReceptions()) * tierC);
            rb.get(i).setReceivingYards((42 - rb.get(i).getReceivingYards()) * tierC);
            rb.get(i).setRushingYards((42 - rb.get(i).getRushingYards()) * tierB);
            rb.get(i).setAttempts((42 - rb.get(i).getAttempts()) * tierD);
            rb.get(i).setTds((42 - rb.get(i).getTds()) * tierB);
            rb.get(i).setFfppg((42 - rb.get(i).getFfppg()) * tierA);
            rb.get(i).setTotalFFP((42 - rb.get(i).getTotalFFP()) * tierA);
            rb.get(i).setoLine((42 - rb.get(i).getoLine()) * tierE);
            rb.get(i).setDraftorder((42 - rb.get(i).getDraftorder()) * tierS);
        }
        return rb;
    }

    public static ArrayList<Rbs> reverseWerteGewichten(ArrayList<Rbs> rb, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < rb.size(); i++) {
            rb.get(i).setReceptions(42 - rb.get(i).getReceptions() / tierC);
            rb.get(i).setReceivingYards(42 - rb.get(i).getReceivingYards() / tierC);
            rb.get(i).setRushingYards(42 - rb.get(i).getRushingYards() / tierB);
            rb.get(i).setAttempts(42 - rb.get(i).getAttempts() / tierD);
            rb.get(i).setTds(42 - rb.get(i).getTds() / tierB);
            rb.get(i).setFfppg(42 - rb.get(i).getFfppg() / tierA);
            rb.get(i).setTotalFFP(42 - rb.get(i).getTotalFFP() / tierA);
            rb.get(i).setoLine(42 - rb.get(i).getoLine() / tierE);
            rb.get(i).setDraftorder(42 - rb.get(i).getDraftorder() / tierS);
        }
        return rb;
    }

    public static ArrayList<Rbs> valueBerechnen(ArrayList<Rbs> rb) {
        for(int i = 0; i < rb.size(); i++) {
            rb.get(i).setValue(rb.get(i).getReceptions() + rb.get(i).getReceivingYards() + rb.get(i).getRushingYards() + rb.get(i).getAttempts() + rb.get(i).getTds() + rb.get(i).getFfppg() + rb.get(i).getTotalFFP() + rb.get(i).getoLine() + rb.get(i).getDraftorder());
        }
        return rb;
    }

    public static ArrayList<Rbs> sortAfterValue(ArrayList<Rbs> rb, int amountRookies) {
        Rbs pivot = new Rbs("pivot", "", 0,0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
        int rookiesSorted = 0;
        int pivotNumber;
        ArrayList<Rbs> sortedRBs = new ArrayList<Rbs>();
        while(!rb.isEmpty()) {
            pivot = rb.get(rb.size() - 1);
            pivotNumber = rb.size() - 1;
            for(int j = 0; j < rb.size() - 1; j++) {
                if(rb.get(j).getValue() >= pivot.getValue()) {
                    if(rb.get(j).getValue() > pivot.getValue() || (rb.get(j).getValue() == pivot.getValue() && rb.get(j).getDraftorder() > pivot.getDraftorder())) {
                        pivot = rb.get(j);
                        pivotNumber = j;
                    }
                }
            }
            if(rb.size() > 1) {
                sortedRBs.add(pivot);
                rb.remove(pivotNumber);
            } else {
                sortedRBs.add(pivot);
                rb.remove(rb.size() - 1);
            }

        }

        for(int i = 0; rookiesSorted < amountRookies; i++) {
            if (sortedRBs.get(i).getRookie()) {
                pivot = sortedRBs.get(i);
                sortedRBs.remove(i);
                sortedRBs.add(pivot);
                rookiesSorted += 1;
                if(rookiesSorted < amountRookies) {
                    i -= 1;
                }

            }
        }
        for(int i = 0; i < sortedRBs.size(); i ++) {
            sortedRBs.get(i).setPosition(i + 1);
        }
        return sortedRBs;
    }

    /**
     * Schreibt alle Attribute in ein Array, damit automatisch prüfbar
     *
     * @param rbsArrayList Liste aller Runningbacks
     *
     */
    public static ArrayList<ArrayList<Double>> createArrayWithRBInformation(ArrayList<Rbs> rbsArrayList) {
        Rbs rb;
        ArrayList<ArrayList<Double>> listOfRbs = new ArrayList<>();

        for(int i = 0; i < rbsArrayList.size(); i++) {
            rb = rbsArrayList.get(i);
            double[] rbsStats = {rb.getId(), rb.getDraftorder(), rb.getReceptions(), rb.getReceivingYards(), rb.getRushingYards(), rb.getAttempts(), rb.getTds(), rb.getoLine(), rb.getFfppg(), rb.getTotalFFP()};
            ArrayList<Double> runningBack = new ArrayList<Double>();
            for(int j = 0; j < rbsStats.length; j++) {
                runningBack.add(rbsStats[j]);
            }
            listOfRbs.add(runningBack);
        }
        return listOfRbs;
    }

    /**
     * Alle Werte der Runningbacks werden sortiert und eingeranked
     * Höchster Wert -> 1, Niedrigster Wert -> |Runningbacks|
     *
     * @param rbsArrayList Liste aller Runningbacks
     *
     * @return
     */
    public static ArrayList<ArrayList<Double>> sortRBList(ArrayList<Rbs> rbsArrayList) {
        ArrayList<ArrayList<Double>> listOfRBs = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> sortedListOfRBs = createArrayWithRBInformation(rbsArrayList);
        double[] pivot = new double[3];
        int counter = 0;
        int position = -1;
        Boolean same = false;
        for(int i = 1; i <= 9; i++) {
            pivot[2] = -1;
            listOfRBs.addAll(sortedListOfRBs);
            while(!listOfRBs.isEmpty()) {
                pivot[0] = listOfRBs.get(0).get(0);
                pivot[1] = listOfRBs.get(0).get(i);
                for (int j = 0; j < listOfRBs.size(); j++) {
                    if(listOfRBs.get(j).get(i) == pivot[2]) {
                        pivot[0] = listOfRBs.get(j).get(0);
                        pivot[1] = listOfRBs.get(j).get(i);
                        same = true;
                        break;
                    } else if(listOfRBs.get(j).get(i) > pivot[1]) {
                        pivot[0] = listOfRBs.get(j).get(0);
                        pivot[1] = listOfRBs.get(j).get(i);
                    }
                }
                do {
                    position += 1;
                } while(pivot[0] != listOfRBs.get(position).get(0));
                if(!same) {
                    counter += 1;
                } else {
                    same = false;
                }
                sortedListOfRBs.get((int)pivot[0] - 1).set(i, (double)counter);
                listOfRBs.remove(position);
                position = -1;
                pivot[2] = pivot[1];
            }
            counter = 0;
        }
        return sortedListOfRBs;
    }

    /**
     * Zurückschreiben aller Werte aus der doppelten ArrayListe zurück in Runningbackliste
     *
     * @param rbsArrayList Liste aller Runningbacks
     *
     * @return Sortierte Liste aller Runningbacks (eingeranked/sortiert)
     */
    public static ArrayList<Rbs> rewriteRBValues(ArrayList<Rbs> rbsArrayList) {
        ArrayList<ArrayList<Double>> listOfRBs = sortRBList(rbsArrayList);
        for (Rbs rb:rbsArrayList) {
            rb.setDraftorder(listOfRBs.get((int)rb.getId() - 1).get(1));
            rb.setReceptions(listOfRBs.get((int)rb.getId() - 1).get(2));
            rb.setReceivingYards(listOfRBs.get((int)rb.getId() - 1).get(3));
            rb.setRushingYards(listOfRBs.get((int)rb.getId() - 1).get(4));
            rb.setAttempts(listOfRBs.get((int)rb.getId() - 1).get(5));
            rb.setTds(listOfRBs.get((int)rb.getId() - 1).get(6));
            rb.setoLine(listOfRBs.get((int)rb.getId() - 1).get(7));
            rb.setFfppg(listOfRBs.get((int)rb.getId() - 1).get(8));
            rb.setTotalFFP(listOfRBs.get((int)rb.getId() - 1).get(9));
        }
        return rbsArrayList;
    }


    public static void printRBs(ArrayList<Rbs> rb) {
        System.out.println("RB-List:");
        for(int i = 0; i < rb.size(); i++) {
            System.out.println(rb.get(i).getPosition() + ", " + rb.get(i).getName() + " (" + rb.get(i).getTeam() + ")" + ", " + rb.get(i).getValue());
        }
    }
}