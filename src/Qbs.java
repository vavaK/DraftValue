import java.util.ArrayList;

public class Qbs {
    private String name;
    private int position;
    private double passingYards;
    private double rushingYards;
    private double ffppg;
    private double totalFFP;
    private double draftOrder;
    private double tdInt;
    private double cmpPct;
    private double wrRoom;
    private boolean rookie;
    private double value;
    private String team;

    public Qbs(String name, String team, int position, double passingYards, double rushingYards, double ffppg, double totalFFP, double draftOrder, double tdInt, double cmpPct, double wrRoom, boolean rookie, double value) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.passingYards = passingYards;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
        this.draftOrder = draftOrder;
        this.tdInt = tdInt;
        this.cmpPct = cmpPct;
        this.wrRoom = wrRoom;
        this.rookie = rookie;
        this.value = value;
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

    public void setFfppg(double ffppg) {
        this.ffppg = ffppg;
    }

    public void setTotalFFP(double totalFFP) {
        this.totalFFP = totalFFP;
    }

    public void setDraftOrder(double draftOrder) {
        this.draftOrder = draftOrder;
    }

    public void setTdInt(double tdInt) {
        this.tdInt = tdInt;
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

    public double getFfppg() {
        return ffppg;
    }

    public double getTotalFFP() {
        return totalFFP;
    }

    public double getDraftOrder() {
        return draftOrder;
    }

    public double getTdInt() {
        return tdInt;
    }

    public double getCmpPct() {
        return cmpPct;
    }

    public double getWrRoom() {
        return wrRoom;
    }

    public Boolean getRookie() {
        return rookie;
    }

    public double getValue() {
        return value;
    }

    public static ArrayList<Qbs> werteGewichten(ArrayList<Qbs> qb, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < qb.size(); i++) {
            qb.get(i).setPassingYards((33 - qb.get(i).getPassingYards()) * tierB);
            qb.get(i).setRushingYards((33 - qb.get(i).getRushingYards()) * tierB);
            qb.get(i).setFfppg((33 - qb.get(i).getFfppg()) * tierA);
            qb.get(i).setTotalFFP((33 - qb.get(i).getTotalFFP()) * tierB);
            qb.get(i).setDraftOrder((33 - qb.get(i).getDraftOrder()) * tierS);
            qb.get(i).setTdInt((33 - qb.get(i).getTdInt()) * tierB);
            qb.get(i).setCmpPct((33 - qb.get(i).getCmpPct()) * tierE);
            qb.get(i).setWrRoom((33 - qb.get(i).getWrRoom()) * tierD);
        }
        return qb;
    }

    public static ArrayList<Qbs> valueBerechnen(ArrayList<Qbs> qb) {
        for(int i = 0; i < qb.size(); i++) {
            qb.get(i).setValue(qb.get(i).getPassingYards() + qb.get(i).getRushingYards() + qb.get(i).getFfppg() + qb.get(i).getTotalFFP() + qb.get(i).getDraftOrder() + qb.get(i).getTdInt() + qb.get(i).getCmpPct() + qb.get(i).getWrRoom());
        }
        return qb;
    }

    public static ArrayList<Qbs> sortAfterValue(ArrayList<Qbs> qb, int amountRookies) {
        Qbs pivot = new Qbs("pivot", "", 0,0, 0, 0, 0, 0, 0, 0, 0, false, 0);
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

    public static void printQBs(ArrayList<Qbs> qb) {
        System.out.println("QB-List:");
        for(int i = 0; i < qb.size(); i++) {
            System.out.println(qb.get(i).getPosition() + ", " + qb.get(i).getName() + " (" + qb.get(i).getTeam() + ")" + ", " + qb.get(i).getValue());
        }
    }
}
