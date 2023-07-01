import java.util.ArrayList;

public class Wrs {
    private String name;
    private int position;
    private double receptions;
    private double receivingYards;
    private double tds;
    private double ffppg;
    private double totalFFP;
    private double qbs;
    private double draftorder;
    private double value;

    public Wrs(String name, int position, double receptions, double receivingYards, double tds, double ffppg, double totalFFP, double qbs, double draftOrder, double value) {
        this.name = name;
        this.position = position;
        this.receptions = receptions;
        this.receivingYards = receivingYards;
        this.tds = tds;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
        this.qbs = qbs;
        this.draftorder = draftOrder;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ArrayList<Wrs> werteGewichten(ArrayList<Wrs> wr, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < wr.size(); i++) {
            wr.get(i).setReceptions(wr.get(i).getReceptions() * tierC);
            wr.get(i).setReceivingYards(wr.get(i).getReceivingYards() * tierB);
            wr.get(i).setTds(wr.get(i).getTds() * tierB);
            wr.get(i).setFfppg(wr.get(i).getFfppg() * tierA);
            wr.get(i).setTotalFFP(wr.get(i).getTotalFFP() * tierA);
            wr.get(i).setQbs(wr.get(i).getQbs() * tierE);
            wr.get(i).setDraftorder(wr.get(i).getDraftorder() * tierS);
        }
        return wr;
    }

    public ArrayList<Wrs> wertBerechnen(ArrayList<Wrs> wr) {
        for(int i = 0; i < wr.size(); i++) {
            wr.get(i).setValue(wr.get(i).getReceptions() + wr.get(i).getReceivingYards() + wr.get(i).getTds() + wr.get(i).getFfppg() + wr.get(i).getTotalFFP() + wr.get(i).getQbs() + wr.get(i).getDraftorder());
        }
        return wr;
    }
}