import java.util.ArrayList;

public class Kicker{
    private String name;
    private int position;
    private double attempts;
    private double made;
    private double fiftyPlus;
    private double pct;
    private double xp;
    private double ffppg;
    private double totalFFP;
    private double draftOrder;
    private double value;
    private String team;
    private int byeWeek;

    public Kicker(String name, String team, int position, double attempts, double made, double fiftyPlus, double pct, double xp, double ffppg, double totalFFP, double draftOrder, double value, int byeWeek) {
        this.name = name;
        this.team = team;
        this.position = position;
        this.attempts = attempts;
        this.made = made;
        this.fiftyPlus = fiftyPlus;
        this.pct = pct;
        this.xp = xp;
        this.ffppg = ffppg;
        this.totalFFP = totalFFP;
        this.draftOrder = draftOrder;
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

    public void setTeam(String teamName) {
        this.team = teamName;
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

    public double getFiftyPlus() {
        return fiftyPlus;
    }

    public void setFiftyPlus(double fiftyPlus) {
        this.fiftyPlus = fiftyPlus;
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

    public double getDraftOrder() {
        return draftOrder;
    }

    public void setDraftOrder(double draftOrder) {
        this.draftOrder = draftOrder;
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

    public static ArrayList<Kicker> werteGewichten(ArrayList<Kicker> kicker, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < kicker.size(); i++) {
            kicker.get(i).setAttempts((33 - kicker.get(i).getAttempts()) * tierD);
            kicker.get(i).setMade((33 - kicker.get(i).getMade()) * tierB);
            kicker.get(i).setFiftyPlus((33 - kicker.get(i).getFiftyPlus()) * tierC);
            kicker.get(i).setPct((33 - kicker.get(i).getPct()) * tierD);
            kicker.get(i).setXp((33 - kicker.get(i).getXp()) * tierC);
            kicker.get(i).setFfppg((33 - kicker.get(i).getFfppg()) * tierA);
            kicker.get(i).setTotalFFP((33 - kicker.get(i).getTotalFFP()) * tierA);
            kicker.get(i).setDraftOrder((33 - kicker.get(i).getDraftOrder()) * tierS);
        }
        return kicker;
    }

    public static ArrayList<Kicker> reverseWerteGewichten(ArrayList<Kicker> kicker, double tierS, double tierA, double tierB, double tierC, double tierD, double tierE) {
        for (int i = 0; i < kicker.size(); i++) {
            kicker.get(i).setAttempts(33 - kicker.get(i).getAttempts() / tierD);
            kicker.get(i).setMade(33 - kicker.get(i).getMade() / tierB);
            kicker.get(i).setFiftyPlus(33 - kicker.get(i).getFiftyPlus() / tierC);
            kicker.get(i).setPct(33 - kicker.get(i).getPct() / tierD);
            kicker.get(i).setXp(33 - kicker.get(i).getXp() / tierC);
            kicker.get(i).setFfppg(33 - kicker.get(i).getFfppg() / tierA);
            kicker.get(i).setTotalFFP(33 - kicker.get(i).getTotalFFP() / tierA);
            kicker.get(i).setDraftOrder(33 - kicker.get(i).getDraftOrder() / tierS);
        }
        return kicker;
    }

    public static ArrayList<Kicker> valueBerechnen(ArrayList<Kicker> kicker) {
        for(int i = 0; i < kicker.size(); i++) {
            kicker.get(i).setValue(kicker.get(i).getAttempts() + kicker.get(i).getMade() + kicker.get(i).getFiftyPlus() + kicker.get(i).getPct() + kicker.get(i).getXp() + kicker.get(i).getFfppg() + kicker.get(i).getFfppg() + kicker.get(i).getDraftOrder());
        }
        return kicker;
    }

    public static ArrayList<Kicker> sortAfterValue(ArrayList<Kicker> kicker) {
        Kicker pivot = new Kicker("pivot", "", 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int pivotNumber;
        ArrayList<Kicker> sortedKickers = new ArrayList<Kicker>();
        while(!kicker.isEmpty()) {
            pivot = kicker.get(kicker.size() - 1);
            pivotNumber = kicker.size() - 1;
            for(int j = 0; j < kicker.size() - 1; j++) {
                if(kicker.get(j).getValue() >= pivot.getValue()) {
                    if(kicker.get(j).getValue() > pivot.getValue() || (kicker.get(j).getValue() == pivot.getValue() && kicker.get(j).getDraftOrder() > pivot.getDraftOrder())) {
                        pivot = kicker.get(j);
                        pivotNumber = j;
                    }
                }
            }
            if(kicker.size() > 1) {
                sortedKickers.add(pivot);
                kicker.remove(pivotNumber);
            } else {
                sortedKickers.add(pivot);
                kicker.remove(kicker.size() - 1);
            }

        }

        for(int i = 0; i < sortedKickers.size(); i ++) {
            sortedKickers.get(i).setPosition(i + 1);
        }
        return sortedKickers;
    }

    public static void printKickers(ArrayList<Kicker> kicker) {
        System.out.println("Kicker-List:");
        for(int i = 0; i < kicker.size(); i++) {
            System.out.println(kicker.get(i).getPosition() + ", " + kicker.get(i).getName() + " (" + kicker.get(i).getTeam() + ")" + ", " + kicker.get(i).getValue());
        }
    }
}