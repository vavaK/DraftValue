import java.util.ArrayList;

public class Tes {
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

    public Tes(String name, String team, int position, double receptions, double receivingYards, double tds, double ffppg, double totalFFP, double qbs, double draftOrder, Boolean rookie, double value, int byeWeek, double id) {
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

    public static ArrayList<Tes> werteGewichten(ArrayList<Tes> te, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < te.size(); i++) {
            te.get(i).setReceptions((36 - te.get(i).getReceptions()) * tierC);
            te.get(i).setReceivingYards((36 - te.get(i).getReceivingYards()) * tierB);
            te.get(i).setTds((36 - te.get(i).getTds()) * tierB);
            te.get(i).setFfppg((36 - te.get(i).getFfppg()) * tierA);
            te.get(i).setTotalFFP((36 - te.get(i).getTotalFFP()) * tierA);
            te.get(i).setQbs((36 - te.get(i).getQbs()) * tierE);
            te.get(i).setDraftorder((36 - te.get(i).getDraftorder()) * tierS);
        }
        return te;
    }

    public static ArrayList<Tes> reverseWerteGewichten(ArrayList<Tes> te, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < te.size(); i++) {
            te.get(i).setReceptions(36 - te.get(i).getReceptions() / tierC);
            te.get(i).setReceivingYards(36 - te.get(i).getReceivingYards() / tierB);
            te.get(i).setTds(36 - te.get(i).getTds() / tierB);
            te.get(i).setFfppg(36 - te.get(i).getFfppg() / tierA);
            te.get(i).setTotalFFP(36 - te.get(i).getTotalFFP() / tierA);
            te.get(i).setQbs(36 - te.get(i).getQbs() / tierE);
            te.get(i).setDraftorder(36 - te.get(i).getDraftorder() / tierS);
        }
        return te;
    }

    public static ArrayList<Tes> valueBerechnen(ArrayList<Tes> te) {
        for(int i = 0; i < te.size(); i++) {
            te.get(i).setValue(te.get(i).getReceptions() + te.get(i).getReceivingYards() + te.get(i).getTds() + te.get(i).getFfppg() + te.get(i).getTotalFFP() + te.get(i).getQbs() + te.get(i).getDraftorder());
        }
        return te;
    }

    public static ArrayList<Tes> sortAfterValue(ArrayList<Tes> te, int amountRookies) {
        Tes pivot = new Tes("pivot", "", 0,0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
        int rookiesSorted = 0;
        int pivotNumber;
        ArrayList<Tes> sortedTEs = new ArrayList<Tes>();
        while(!te.isEmpty()) {
            pivot = te.get(te.size() - 1);
            pivotNumber = te.size() - 1;
            for(int j = 0; j < te.size() - 1; j++) {
                if(te.get(j).getValue() >= pivot.getValue()) {
                    if(te.get(j).getValue() > pivot.getValue() || (te.get(j).getValue() == pivot.getValue() && te.get(j).getDraftorder() > pivot.getDraftorder())) {
                        pivot = te.get(j);
                        pivotNumber = j;
                    }
                }
            }
            if(te.size() > 1) {
                sortedTEs.add(pivot);
                te.remove(pivotNumber);
            } else {
                sortedTEs.add(pivot);
                te.remove(te.size() - 1);
            }

        }

        for(int i = 0; rookiesSorted < amountRookies; i++) {
            if (sortedTEs.get(i).getRookie()) {
                pivot = sortedTEs.get(i);
                sortedTEs.remove(i);
                sortedTEs.add(pivot);
                rookiesSorted += 1;
                if(rookiesSorted < amountRookies) {
                    i -= 1;
                }

            }
        }
        for(int i = 0; i < sortedTEs.size(); i ++) {
            sortedTEs.get(i).setPosition(i + 1);
        }
        return sortedTEs;
    }

    /**
     * Schreibt alle Attribute in ein Array, damit automatisch prüfbar
     *
     * @param tesArrayList Liste aller Tight Ends
     *
     */
    public static ArrayList<ArrayList<Double>> createArrayWithTEInformation(ArrayList<Tes> tesArrayList) {
        Tes te;
        ArrayList<ArrayList<Double>> listOfTes = new ArrayList<>();

        for(int i = 0; i < tesArrayList.size(); i++) {
            te = tesArrayList.get(i);
            double[] tesStats = {te.getId(), te.getDraftorder(), te.getReceptions(), te.getReceivingYards(), te.getTds(), te.getFfppg(), te.getTotalFFP(), te.getQbs()};
            ArrayList<Double> tightEnd = new ArrayList<Double>();
            for(int j = 0; j < tesStats.length; j++) {
                tightEnd.add(tesStats[j]);
            }
            listOfTes.add(tightEnd);
        }
        return listOfTes;
    }

    /**
     * Alle Werte der Tight Ends werden sortiert und eingeranked
     * Höchster Wert -> 1, Niedrigster Wert -> |Tight Ends|
     *
     * @param tesArrayList Liste aller Tight Ends
     *
     * @return
     */
    public static ArrayList<ArrayList<Double>> sortTEList(ArrayList<Tes> tesArrayList) {
        ArrayList<ArrayList<Double>> listOfTes = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> sortedListOfTEs = createArrayWithTEInformation(tesArrayList);
        double[] pivot = new double[3];
        int counter = 0;
        int position = -1;
        Boolean same = false;
        Boolean reverse = false;
        for(int i = 1; i <= 7; i++) {
            pivot[2] = -1;
            listOfTes.addAll(sortedListOfTEs);
            if(i > 5) {
                reverse = true;
            } else {
                reverse = false;
            }
            while(!listOfTes.isEmpty()) {
                pivot[0] = listOfTes.get(0).get(0);
                pivot[1] = listOfTes.get(0).get(i);
                for (int j = 0; j < listOfTes.size(); j++) {
                    if(listOfTes.get(j).get(i) == pivot[2]) {
                        pivot[0] = listOfTes.get(j).get(0);
                        pivot[1] = listOfTes.get(j).get(i);
                        same = true;
                        break;
                    } else if(listOfTes.get(j).get(i) > pivot[1] && !reverse) {
                        pivot[0] = listOfTes.get(j).get(0);
                        pivot[1] = listOfTes.get(j).get(i);
                    } else if(listOfTes.get(j).get(i) < pivot[1] && reverse) {
                        pivot[0] = listOfTes.get(j).get(0);
                        pivot[1] = listOfTes.get(j).get(i);
                    }
                }
                do {
                    position += 1;
                } while(pivot[0] != listOfTes.get(position).get(0));
                if(!same) {
                    counter += 1;
                } else {
                    same = false;
                }
                sortedListOfTEs.get((int)pivot[0] - 1).set(i, (double)counter);
                listOfTes.remove(position);
                position = -1;
                pivot[2] = pivot[1];
            }
            counter = 0;
        }
        return sortedListOfTEs;
    }

    /**
     * Zurückschreiben aller Werte aus der doppelten ArrayListe zurück in Tight End Liste
     *
     * @param tesArrayList Liste aller Tight Ends
     *
     * @return Sortierte Liste aller Tight Ends (eingeranked/sortiert)
     */
    public static ArrayList<Tes> rewriteTEValues(ArrayList<Tes> tesArrayList) {
        ArrayList<ArrayList<Double>> listOfWRs = sortTEList(tesArrayList);
        for (Tes te:tesArrayList) {
            te.setDraftorder(listOfWRs.get((int)te.getId() - 1).get(1));
            te.setReceptions(listOfWRs.get((int)te.getId() - 1).get(2));
            te.setReceivingYards(listOfWRs.get((int)te.getId() - 1).get(3));
            te.setTds(listOfWRs.get((int)te.getId() - 1).get(4));
            te.setFfppg(listOfWRs.get((int)te.getId() - 1).get(5));
            te.setTotalFFP(listOfWRs.get((int)te.getId() - 1).get(6));
            te.setQbs(listOfWRs.get((int)te.getId() - 1).get(7));
        }
        return tesArrayList;
    }


    public static void printTes(ArrayList<Tes> te) {
        System.out.println("TE-List:");
        for(int i = 0; i < te.size(); i++) {
            System.out.println(te.get(i).getPosition() + ", " + te.get(i).getName() + " (" + te.get(i).getTeam() + ")" + ", " + te.get(i).getValue());
        }
    }
}