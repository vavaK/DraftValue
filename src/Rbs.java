import java.util.ArrayList;

public class Rbs {
    private String name;
    private int position;
    private double receptions;
    private double receivingYards;
    private double rushingYards;
    private double tds;
    private double ffppg;
    private double totalFFP;
    private double oLine;
    private double draftorder;
    private double value;

    public Rbs(String name, int position, double receptions, double  receivingYards, double rushingYards, double tds, double ffppg, double totalFFP, double oLine, double draftorder, double value) {
        this.name = name;
        this.position = position;
        this.receptions = receptions;
        this.receivingYards = receivingYards;
        this.rushingYards = rushingYards;
        this.tds = tds;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
        this.oLine = oLine;
        this.draftorder = draftorder;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ArrayList<Rbs> werteGewichten(ArrayList<Rbs> rb, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < rb.size(); i++) {
            rb.get(i).setReceptions(rb.get(i).getReceptions() * tierC);
            rb.get(i).setReceivingYards(rb.get(i).getReceivingYards() * tierC);
            rb.get(i).setRushingYards(rb.get(i).getRushingYards() * tierB);
            rb.get(i).setTds(rb.get(i).getTds() * tierB);
            rb.get(i).setFfppg(rb.get(i).getFfppg() * tierA);
            rb.get(i).setTotalFFP(rb.get(i).getTotalFFP() * tierA);
            rb.get(i).setoLine(rb.get(i).getoLine() * tierE);
            rb.get(i).setDraftorder(rb.get(i).getDraftorder() * tierS);
        }
        return rb;
    }

    public ArrayList<Rbs> wertBerechnen(ArrayList<Rbs> rb) {
        for(int i = 0; i < rb.size(); i++) {
            rb.get(i).setValue(rb.get(i).getReceptions() + rb.get(i).getReceivingYards() + rb.get(i).getRushingYards() + rb.get(i).getTds() + rb.get(i).getFfppg() + rb.get(i).getTotalFFP() + rb.get(i).getoLine() + rb.get(i).getDraftorder());
        }
        return rb;
    }
}