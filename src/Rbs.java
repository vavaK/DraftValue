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

    public Rbs(String name, String team, int position, double receptions, double  receivingYards, double rushingYards, double attempts, double tds, double ffppg, double totalFFP, double oLine, double draftorder, Boolean rookie, double value, int byeWeek) {
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
        Rbs pivot = new Rbs("pivot", "", 0,0, 0, 0, 0, 0, 0, 0, 0, 0, false, 0, 0);
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

    public static void printRBs(ArrayList<Rbs> rb) {
        System.out.println("RB-List:");
        for(int i = 0; i < rb.size(); i++) {
            System.out.println(rb.get(i).getPosition() + ", " + rb.get(i).getName() + " (" + rb.get(i).getTeam() + ")" + ", " + rb.get(i).getValue());
        }
    }
}