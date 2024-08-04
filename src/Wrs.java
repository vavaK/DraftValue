import java.util.ArrayList;

public class Wrs {
    private String name;
    private String team;
    private int position;
    private double receptions;
    private double receivingYards;
    private double tds;
    private double ffppg;
    private double totalFFP;
    private double qbs;
    private double draftorder;
    private Boolean rookie;
    private double value;
    private int byeWeek;
    private double id;

    public Wrs(String name, String team, int position, double receptions, double receivingYards, double tds, double ffppg, double totalFFP, double qbs, double draftOrder, Boolean rookie, double value, int byeWeek, double id) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.receptions = receptions;
        this.receivingYards = receivingYards;
        this.tds = tds;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
        this.qbs = qbs;
        this.draftorder = draftOrder;
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

    public double getQbs() {
        return qbs;
    }

    public void setQbs(double qbs) {
        this.qbs = qbs;
    }

    public double getDraftorder() {
        return draftorder;
    }

    public void setDraftorder(double draftorder) {
        this.draftorder = draftorder;
    }

    public int getByeWeek() {
        return byeWeek;
    }

    public void setByeWeek(int byeWeek) {
        this.byeWeek = byeWeek;
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

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public static ArrayList<Wrs> werteGewichten(ArrayList<Wrs> wr, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < wr.size(); i++) {
            wr.get(i).setReceptions((98 - wr.get(i).getReceptions()) * tierD);
            wr.get(i).setReceivingYards((98 - wr.get(i).getReceivingYards()) * tierC);
            wr.get(i).setTds((13 - wr.get(i).getTds()) * tierD);
            wr.get(i).setFfppg((98 - wr.get(i).getFfppg()) * tierS);
            wr.get(i).setTotalFFP((98 - wr.get(i).getTotalFFP()) * tierB);
            wr.get(i).setQbs((33 - wr.get(i).getQbs()) * tierD);
            wr.get(i).setDraftorder((107 - wr.get(i).getDraftorder()) * tierS);
        }
        return wr;
    }

    public static ArrayList<Wrs> reverseWerteGewichten(ArrayList<Wrs> wr, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < wr.size(); i++) {
            wr.get(i).setReceptions(98 - wr.get(i).getReceptions() / tierD);
            wr.get(i).setReceivingYards(98 - wr.get(i).getReceivingYards() / tierC);
            wr.get(i).setTds(13 - wr.get(i).getTds() / tierD);
            wr.get(i).setFfppg(98 - wr.get(i).getFfppg() / tierS);
            wr.get(i).setTotalFFP(98 - wr.get(i).getTotalFFP() / tierB);
            wr.get(i).setQbs(33 - wr.get(i).getQbs() / tierD);
            wr.get(i).setDraftorder(107 - wr.get(i).getDraftorder() /  tierS);
        }
        return wr;
    }

    public static ArrayList<Wrs> valueBerechnen(ArrayList<Wrs> wr) {
        for(int i = 0; i < wr.size(); i++) {
            wr.get(i).setValue(wr.get(i).getReceptions() + wr.get(i).getReceivingYards() + wr.get(i).getTds() + wr.get(i).getFfppg() + wr.get(i).getTotalFFP() + wr.get(i).getQbs() + wr.get(i).getDraftorder());
        }
        return wr;
    }

    public static ArrayList<Wrs> sortAfterValue(ArrayList<Wrs> wr, int amountRookies) {
        Wrs pivot = new Wrs("pivot", "", 0,0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
        int rookiesSorted = 0;
        int pivotNumber;
        ArrayList<Wrs> sortedWRs = new ArrayList<Wrs>();
        while(!wr.isEmpty()) {
            pivot = wr.get(wr.size() - 1);
            pivotNumber = wr.size() - 1;
            for(int j = 0; j < wr.size() - 1; j++) {
                if(wr.get(j).getValue() >= pivot.getValue()) {
                    if(wr.get(j).getValue() > pivot.getValue() || (wr.get(j).getValue() == pivot.getValue() && wr.get(j).getDraftorder() > pivot.getDraftorder())) {
                        pivot = wr.get(j);
                        pivotNumber = j;
                    }
                }
            }
            if(wr.size() > 1) {
                sortedWRs.add(pivot);
                wr.remove(pivotNumber);
            } else {
                sortedWRs.add(pivot);
                wr.remove(wr.size() - 1);
            }

        }

        for(int i = 0; rookiesSorted < amountRookies; i++) {
            if (sortedWRs.get(i).getRookie()) {
                pivot = sortedWRs.get(i);
                sortedWRs.remove(i);
                sortedWRs.add(pivot);
                rookiesSorted += 1;
                if(rookiesSorted < amountRookies) {
                    i -= 1;
                }

            }
        }
        for(int i = 0; i < sortedWRs.size(); i ++) {
            sortedWRs.get(i).setPosition(i + 1);
        }
        return sortedWRs;
    }

    /**
     * Schreibt alle Attribute in ein Array, damit automatisch prüfbar
     *
     * @param wrsArrayList Liste aller Wide Receiver
     *
     */
    public static ArrayList<ArrayList<Double>> createArrayWithWRInformation(ArrayList<Wrs> wrsArrayList) {
        Wrs wr;
        ArrayList<ArrayList<Double>> listOfWrs = new ArrayList<>();

        for(int i = 0; i < wrsArrayList.size(); i++) {
            wr = wrsArrayList.get(i);
            double[] wrsStats = {wr.getId(), wr.getDraftorder(), wr.getReceptions(), wr.getReceivingYards(), wr.getTds(), wr.getFfppg(), wr.getTotalFFP(), wr.getQbs()};
            ArrayList<Double> wideReceiver = new ArrayList<Double>();
            for(int j = 0; j < wrsStats.length; j++) {
                wideReceiver.add(wrsStats[j]);
            }
            listOfWrs.add(wideReceiver);
        }
        return listOfWrs;
    }

    /**
     * Alle Werte der Wide Receiver werden sortiert und eingeranked
     * Höchster Wert -> 1, Niedrigster Wert -> |Wide Receiver|
     *
     * @param wrsArrayList Liste aller Wide Receiver
     *
     * @return
     */
    public static ArrayList<ArrayList<Double>> sortWRList(ArrayList<Wrs> wrsArrayList) {
        ArrayList<ArrayList<Double>> listOfWrs = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> sortedListOfWRs = createArrayWithWRInformation(wrsArrayList);
        double[] pivot = new double[3];
        int counter = 0;
        int position = -1;
        Boolean same = false;
        Boolean reverse = false;
        for(int i = 1; i <= 7; i++) {
            pivot[2] = -1;
            listOfWrs.addAll(sortedListOfWRs);
            if(i > 5) {
                reverse = true;
            } else {
                reverse = false;
            }
            while(!listOfWrs.isEmpty()) {
                pivot[0] = listOfWrs.get(0).get(0);
                pivot[1] = listOfWrs.get(0).get(i);
                for (int j = 0; j < listOfWrs.size(); j++) {
                    if(listOfWrs.get(j).get(i) == pivot[2]) {
                        pivot[0] = listOfWrs.get(j).get(0);
                        pivot[1] = listOfWrs.get(j).get(i);
                        same = true;
                        break;
                    } else if(listOfWrs.get(j).get(i) > pivot[1] && !reverse) {
                        pivot[0] = listOfWrs.get(j).get(0);
                        pivot[1] = listOfWrs.get(j).get(i);
                    } else if(listOfWrs.get(j).get(i) < pivot[1] && reverse) {
                        pivot[0] = listOfWrs.get(j).get(0);
                        pivot[1] = listOfWrs.get(j).get(i);
                    }
                }
                do {
                    position += 1;
                } while(pivot[0] != listOfWrs.get(position).get(0));
                if(!same) {
                    counter += 1;
                } else {
                    same = false;
                }
                sortedListOfWRs.get((int)pivot[0] - 1).set(i, (double)counter);
                listOfWrs.remove(position);
                position = -1;
                pivot[2] = pivot[1];
            }
            counter = 0;
        }
        return sortedListOfWRs;
    }

    /**
     * Zurückschreiben aller Werte aus der doppelten ArrayListe zurück in Wide Receiver Liste
     *
     * @param wrsArrayList Liste aller Wide Receiver
     *
     * @return Sortierte Liste aller Wide Receiver (eingeranked/sortiert)
     */
    public static ArrayList<Wrs> rewriteWRValues(ArrayList<Wrs> wrsArrayList) {
        ArrayList<ArrayList<Double>> listOfWRs = sortWRList(wrsArrayList);
        for (Wrs wr:wrsArrayList) {
            wr.setDraftorder(listOfWRs.get((int)wr.getId() - 1).get(1));
            wr.setReceptions(listOfWRs.get((int)wr.getId() - 1).get(2));
            wr.setReceivingYards(listOfWRs.get((int)wr.getId() - 1).get(3));
            wr.setTds(listOfWRs.get((int)wr.getId() - 1).get(4));
            wr.setFfppg(listOfWRs.get((int)wr.getId() - 1).get(5));
            wr.setTotalFFP(listOfWRs.get((int)wr.getId() - 1).get(6));
            wr.setQbs(listOfWRs.get((int)wr.getId() - 1).get(7));
        }
        return wrsArrayList;
    }

    public static void printWrs(ArrayList<Wrs> wr) {
        System.out.println("WR-List:");
        for(int i = 0; i < wr.size(); i++) {
            System.out.println(wr.get(i).getPosition() + ", " + wr.get(i).getName() + " (" + wr.get(i).getTeam() + ")" + ", " + wr.get(i).getValue());
        }
    }
}