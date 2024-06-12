import java.util.ArrayList;

public class Qbs {
    private String name;
    private int position;
    private double passingYards;
    private double rushingYards;

    private double fumbles;
    private double ffppg;
    private double totalFFP;
    private double draftOrder;
    private double td;

    private double interception;
    private double cmpPct;
    private double wrRoom;
    private boolean rookie;
    private double value;
    private String team;
    private int byeWeek;
    private double id;

    public Qbs(String name, String team, int position, double passingYards, double rushingYards, double fumbles, double ffppg, double totalFFP, double draftOrder, double td, double interception, double cmpPct, double wrRoom, boolean rookie, double value, int byeWeek, double id) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.passingYards = passingYards;
        this.rushingYards = rushingYards;
        this.fumbles = fumbles;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
        this.draftOrder = draftOrder;
        this.td = td;
        this.interception = interception;
        this.cmpPct = cmpPct;
        this.wrRoom = wrRoom;
        this.rookie = rookie;
        this.value = value;
        this.byeWeek = byeWeek;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String teamName) {
        this.team = teamName;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPassingYards(double passingYards) {
        this.passingYards = passingYards;
    }

    public void setRushingYards(double rushingYards) {
        this.rushingYards = rushingYards;
    }

    public void setFumbles(double fumbles) {this.fumbles = fumbles;}

    public void setFfppg(double ffppg) {
        this.ffppg = ffppg;
    }

    public void setTotalFFP(double totalFFP) {
        this.totalFFP = totalFFP;
    }

    public void setDraftOrder(double draftOrder) {
        this.draftOrder = draftOrder;
    }

    public void setTd(double td) {
        this.td = td;
    }

    public void setInterception(double interception) {
        this.interception = interception;
    }

    public void setCmpPct(double cmpPct) {
        this.cmpPct = cmpPct;
    }

    public void setWrRoom(double wrRoom) {
        this.wrRoom = wrRoom;
    }

    public void setRookie(boolean rookie) {
        this.rookie = rookie;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setByeWeek(int byeWeek) {
        this.byeWeek = byeWeek;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getPosition() {
        return position;
    }

    public double getPassingYards() {
        return passingYards;
    }

    public double getRushingYards() {
        return rushingYards;
    }

    public double getFumbles() {return fumbles;}

    public double getFfppg() {
        return ffppg;
    }

    public double getTotalFFP() {
        return totalFFP;
    }

    public double getDraftOrder() {
        return draftOrder;
    }

    public double getTd() {
        return td;
    }

    public double getInterception() {
        return interception;
    }

    public double getCmpPct() {
        return cmpPct;
    }

    public double getWrRoom() {
        return wrRoom;
    }

    public int getByeWeek() {
        return byeWeek;
    }

    public Boolean getRookie() {
        return rookie;
    }

    public double getValue() {
        return value;
    }

    public double getId() {
        return id;
    }

    /**
     * Gewichtet die Bestenliste der QBs und erstellt ein Highscore.
     *
     * @param qb
     * @param tierS
     * @param tierA
     * @param tierB
     * @param tierC
     * @param tierD
     * @param tierE
     * @return
     */
    public static ArrayList<Qbs> werteGewichten(ArrayList<Qbs> qb, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < qb.size(); i++) {
            qb.get(i).setPassingYards((qb.size() + 1 - qb.get(i).getPassingYards()) * tierB);
            qb.get(i).setRushingYards((qb.size() + 1 - qb.get(i).getRushingYards()) * tierB);
            qb.get(i).setFumbles((qb.size() + 1 - qb.get(i).getFumbles()) * tierC);
            qb.get(i).setFfppg((qb.size() + 1 - qb.get(i).getFfppg()) * tierA);
            qb.get(i).setTotalFFP((qb.size() + 1 - qb.get(i).getTotalFFP()) * tierB);
            qb.get(i).setDraftOrder((qb.size() + 1 - qb.get(i).getDraftOrder()) * tierS);
            qb.get(i).setTd((qb.size() + 1 - qb.get(i).getTd()) * tierB);
            qb.get(i).setInterception((qb.size() + 1 - qb.get(i).getInterception()) * tierB);
            qb.get(i).setCmpPct((qb.size() + 1 - qb.get(i).getCmpPct()) * tierE);
            qb.get(i).setWrRoom((qb.size() + 1 - qb.get(i).getWrRoom()) * tierD);
        }
        return qb;
    }

    /**
     * Setzt die Werte der Spieler in ein Ranking. Der Beste ist 1 der schlechteste n.
     *
     * @param qb
     * @param tierS
     * @param tierA
     * @param tierB
     * @param tierC
     * @param tierD
     * @param tierE
     * @return
     */
    public static ArrayList<Qbs> reverseWerteGewichten(ArrayList<Qbs> qb, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < qb.size(); i++) {
            qb.get(i).setPassingYards(qb.size() + 1 - qb.get(i).getPassingYards() / tierB);
            qb.get(i).setRushingYards(qb.size() + 1 - qb.get(i).getRushingYards() / tierB);
            qb.get(i).setFumbles(qb.size() + 1 - qb.get(i).getFumbles() / tierC);
            qb.get(i).setFfppg(qb.size() + 1 - qb.get(i).getFfppg() / tierA);
            qb.get(i).setTotalFFP(qb.size() + 1 - qb.get(i).getTotalFFP() / tierB);
            qb.get(i).setDraftOrder(qb.size() + 1 - qb.get(i).getDraftOrder() / tierS);
            qb.get(i).setTd(qb.size() + 1 - qb.get(i).getTd() / tierB);
            qb.get(i).setInterception(qb.size() + 1 - qb.get(i).getInterception() / tierB);
            qb.get(i).setCmpPct(qb.size() + 1 - qb.get(i).getCmpPct() / tierE);
            qb.get(i).setWrRoom(qb.size() + 1 - qb.get(i).getWrRoom() / tierD);
        }
        return qb;
    }

    /**
     * Berechnet Highscore(Value) für jeden Spieler.
     *
     * @param qb
     * @return
     */
    public static ArrayList<Qbs> valueBerechnen(ArrayList<Qbs> qb) {
        for(int i = 0; i < qb.size(); i++) {
            qb.get(i).setValue(qb.get(i).getPassingYards() + qb.get(i).getRushingYards() + qb.get(i).getFfppg() + qb.get(i).getTotalFFP() + qb.get(i).getDraftOrder() + qb.get(i).getTd() + qb.get(i).getCmpPct() + qb.get(i).getWrRoom() - qb.get(i).getFumbles() - qb.get(i).getInterception());
        }
        return qb;
    }

    /**
     * Sortiert die Spieler basierend auf ihrer Value.
     *
     * @param qb
     * @param amountRookies
     * @return
     */
    public static ArrayList<Qbs> sortAfterValue(ArrayList<Qbs> qb, int amountRookies) {
        Qbs pivot = new Qbs("pivot", "", 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0, 0);
        int rookiesSorted = 0;
        int pivotNumber;
        ArrayList<Qbs> sortedQBs = new ArrayList<Qbs>();
        while(!qb.isEmpty()) {
            pivot = qb.get(qb.size() - 1);
            pivotNumber = qb.size() - 1;
            for(int j = 0; j < qb.size() - 1; j++) {
               if(qb.get(j).getValue() >= pivot.getValue()) {
                   if(qb.get(j).getValue() > pivot.getValue() || (qb.get(j).getValue() == pivot.getValue() && qb.get(j).getDraftOrder() > pivot.getDraftOrder())) {
                       pivot = qb.get(j);
                       pivotNumber = j;
                   }
               }
            }
            if(qb.size() > 1) {
                sortedQBs.add(pivot);
                qb.remove(pivotNumber);
            } else {
                sortedQBs.add(pivot);
                qb.remove(qb.size() - 1);
            }

        }

        for(int i = 0; rookiesSorted < amountRookies; i++) {
            if(sortedQBs.get(i).getRookie()) {
                pivot = sortedQBs.get(i);
                sortedQBs.remove(i);
                sortedQBs.add(pivot);
                rookiesSorted += 1;
                if(rookiesSorted < amountRookies) {
                    i -= 1;
                }
            }
        }
        for(int i = 0; i < sortedQBs.size(); i ++) {
            sortedQBs.get(i).setPosition(i + 1);
        }
        return sortedQBs;
    }

    /**
     * Schreibt alle Attribute in ein Array, damit automatisch prüfbar
     *
     * @param qbsArrayList Liste aller Quarterbacks
     *
     * @return Doppelte ArrayListe mit den Werten aller Quarterbacks
     */
    public static ArrayList<ArrayList<Double>> createArrayWithQBInformation(ArrayList<Qbs> qbsArrayList) {
        Qbs qb;
        ArrayList<ArrayList<Double>> listOfQbs = new ArrayList<>();

        for(int i = 0; i < qbsArrayList.size(); i++) {
            qb = qbsArrayList.get(i);
            double[] qbsStats = {qb.getId(), qb.getDraftOrder(), qb.getPassingYards(), qb.getRushingYards(), qb.getFumbles(), qb.getCmpPct(), qb.getTd(), qb.getInterception(), qb.getFfppg(), qb.getTotalFFP(), qb.getWrRoom()};
            ArrayList<Double> quarterback = new ArrayList<Double>();
            for(int j = 0; j < qbsStats.length; j++) {
                quarterback.add(qbsStats[j]);
            }
            listOfQbs.add(quarterback);
        }
        return listOfQbs;
    }

    /**
     * Alle Werte der Quarterbacks werden sortiert und eingeranked
     * Höchster Wert -> 1, Niedrigster Wert -> |Quarterbacks|
     *
     * @param qbsArrayList Liste aller Quarterbacks
     *
     * @return
     */
    public static ArrayList<ArrayList<Double>> sortQBList(ArrayList<Qbs> qbsArrayList) {
        ArrayList<ArrayList<Double>> listOfQBs = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> sortedListOfQBs = createArrayWithQBInformation(qbsArrayList);
        double[] pivot = new double[3];
        int counter = 0;
        int position = -1;
        Boolean same = false;
        for(int i = 1; i <= 10; i++) {
            pivot[2] = -1;
            listOfQBs.addAll(sortedListOfQBs);
            while(!listOfQBs.isEmpty()) {
                pivot[0] = listOfQBs.get(0).get(0);
                pivot[1] = listOfQBs.get(0).get(i);
                for (int j = 0; j < listOfQBs.size(); j++) {
                    if(listOfQBs.get(j).get(i) == pivot[2]) {
                        pivot[0] = listOfQBs.get(j).get(0);
                        pivot[1] = listOfQBs.get(j).get(i);
                        same = true;
                        break;
                    } else if(listOfQBs.get(j).get(i) > pivot[1]) {
                        pivot[0] = listOfQBs.get(j).get(0);
                        pivot[1] = listOfQBs.get(j).get(i);
                    }
                }
                do {
                    position += 1;
                } while(pivot[0] != listOfQBs.get(position).get(0));
                if(!same) {
                    counter += 1;
                } else {
                    same = false;
                }
                sortedListOfQBs.get((int)pivot[0] - 1).set(i, (double)counter);
                listOfQBs.remove(position);
                position = -1;
                pivot[2] = pivot[1];
            }
            counter = 0;
        }
        return sortedListOfQBs;
    }

    /**
     * Zurückschreiben aller Werte aus der doppelten ArrayListe zurück in Quarterbacklsite
     *
     * @param qbsArrayList Liste aller Quarterbacks
     *
     * @return Sortierte Liste aller Quarterbacks (eingeranked/sortiert)
     */
    public static ArrayList<Qbs> rewriteQBValues(ArrayList<Qbs> qbsArrayList) {
        ArrayList<ArrayList<Double>> listOfQBs = sortQBList(qbsArrayList);
        for (Qbs qb:qbsArrayList) {
            qb.setDraftOrder(listOfQBs.get((int)qb.getId() - 1).get(1));
            qb.setPassingYards(listOfQBs.get((int)qb.getId() - 1).get(2));
            qb.setRushingYards(listOfQBs.get((int)qb.getId() - 1).get(3));
            qb.setFumbles(listOfQBs.get((int)qb.getId() - 1).get(4));
            qb.setCmpPct(listOfQBs.get((int)qb.getId() - 1).get(5));
            qb.setTd(listOfQBs.get((int)qb.getId() - 1).get(6));
            qb.setInterception(listOfQBs.get((int)qb.getId() - 1).get(7));
            qb.setFfppg(listOfQBs.get((int)qb.getId() - 1).get(8));
            qb.setTotalFFP(listOfQBs.get((int)qb.getId() - 1).get(9));
            qb.setWrRoom(listOfQBs.get((int)qb.getId() - 1).get(10));
        }
        return qbsArrayList;
    }

    /**
     * Gibt die Quarterbackliste in der Konsole aus.
     *
     * @param qb
     */
    public static void printQBs(ArrayList<Qbs> qb) {
        System.out.println("QB-List:");
        for(int i = 0; i < qb.size(); i++) {
            System.out.println(qb.get(i).getPosition() + ", " + qb.get(i).getName() + " (" + qb.get(i).getTeam() + ")" + ", " + qb.get(i).getValue());
        }
    }
}
