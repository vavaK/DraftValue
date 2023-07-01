import java.util.ArrayList;

public class Kicker{
    private String name;
    private int position;
    private double attempts;
    private double made;
    private double pct;
    private double xp;
    private double ffppg;
    private double totalFFP;
    private double draftorder;
    private double value;

    public Kicker(String name, int position, double attempts, double made, double pct, double xp, double ffppg, double totalFFP, double draftorder, double value) {
        this.name = name;
        this.position = position;
        this.attempts = attempts;
        this.made = made;
        this.pct = pct;
        this.xp = xp;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
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

    public double getAttempts() {
        return attempts;
    }

    public void setAttempts(double attempts) {
        this.attempts = attempts;
    }

    public double getMade() {
        return made;
    }

    public void setMade(double made) {
        this.made = made;
    }

    public double getPct() {
        return pct;
    }

    public void setPct(double pct) {
        this.pct = pct;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
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

    public ArrayList<Kicker> werteGewichten(ArrayList<Kicker> kicker, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < kicker.size(); i++) {
            kicker.get(i).setAttempts(kicker.get(i).getAttempts() * tierD);
            kicker.get(i).setMade(kicker.get(i).getMade() * tierB);
            kicker.get(i).setPct(kicker.get(i).getPct() * tierE);
            kicker.get(i).setXp(kicker.get(i).getXp() * tierC);
            kicker.get(i).setFfppg(kicker.get(i).getFfppg() * tierA);
            kicker.get(i).setTotalFFP(kicker.get(i).getTotalFFP() * tierA);
            kicker.get(i).setDraftorder(kicker.get(i).getDraftorder() * tierS);
        }
        return kicker;
    }

    public ArrayList<Kicker> wertBerechnen(ArrayList<Kicker> kicker) {
        for(int i = 0; i < kicker.size(); i++) {
            kicker.get(i).setValue(kicker.get(i).getAttempts() + kicker.get(i).getMade() + kicker.get(i).getPct() + kicker.get(i).getXp() + kicker.get(i).getFfppg() + kicker.get(i).getFfppg() + kicker.get(i).getDraftorder());
        }
        return kicker;
    }
}