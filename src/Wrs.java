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

    public Wrs(String name, String team, int position, double receptions, double receivingYards, double tds, double ffppg, double totalFFP, double qbs, double draftOrder, Boolean rookie, double value, int byeWeek) {
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
        Wrs pivot = new Wrs("pivot", "", 0,0, 0, 0, 0, 0, 0, 0, false, 0, 0);
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

    public static void printWrs(ArrayList<Wrs> wr) {
        System.out.println("WR-List:");
        for(int i = 0; i < wr.size(); i++) {
            System.out.println(wr.get(i).getPosition() + ", " + wr.get(i).getName() + " (" + wr.get(i).getTeam() + ")" + ", " + wr.get(i).getValue());
        }
    }
}