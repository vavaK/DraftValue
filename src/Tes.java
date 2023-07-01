import java.util.ArrayList;

public class Tes {
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

    public Tes(String name, int position, double receptions, double receivingYards, double tds, double ffppg, double totalFFP, double qbs, double draftOrder, double value) {
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

    public ArrayList<Tes> werteGewichten(ArrayList<Tes> te, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < te.size(); i++) {
            te.get(i).setReceptions(te.get(i).getReceptions() * tierC);
            te.get(i).setReceivingYards(te.get(i).getReceivingYards() * tierB);
            te.get(i).setTds(te.get(i).getTds() * tierB);
            te.get(i).setFfppg(te.get(i).getFfppg() * tierA);
            te.get(i).setTotalFFP(te.get(i).getTotalFFP() * tierA);
            te.get(i).setQbs(te.get(i).getQbs() * tierE);
            te.get(i).setDraftorder(te.get(i).getDraftorder() * tierS);
        }
        return te;
    }

    public ArrayList<Tes> wertBerechnen(ArrayList<Tes> te) {
        for(int i = 0; i < te.size(); i++) {
            te.get(i).setValue(te.get(i).getReceptions() + te.get(i).getReceivingYards() + te.get(i).getTds() + te.get(i).getFfppg() + te.get(i).getTotalFFP() + te.get(i).getQbs() + te.get(i).getDraftorder());
        }
        return te;
    }
}