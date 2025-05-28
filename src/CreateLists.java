import java.util.ArrayList;
import java.util.Arrays;

public class CreateLists {

    /**
     * Gewichtung der einzelnen Werte
     */
    private static final double tierS = 6;
    private static final double tierA = 3.5;
    private static final double tierB = 2.5;
    private static final double tierC = 2;
    private static final double tierD = 1.5;
    private static final double tierE = 1.0;

    /** Anzahl der Runden im Draft */
    private static final int amountDraftRounds = 16;

    /**
     * Erstellen der ganzen Listen der Positionsobjekte
     */
    private static ArrayList<Qbs> qbList = new ArrayList<Qbs>();
    private static ArrayList<Wrs> wrList = new ArrayList<Wrs>();
    private static ArrayList<Tes> teList = new ArrayList<Tes>();
    private static ArrayList<Rbs> rbsList = new ArrayList<Rbs>();
    private static ArrayList<Kicker> kickerList = new ArrayList<Kicker>();
    private static ArrayList<Defense> defenseList = new ArrayList<Defense>();

    /**
     * Anzahl der Rookies im jeweiligen Draft
     */
    private static final int amountRookieQBs = ManageExcelSheets.amountRookies(0);
    private static final int amountRookieWRs = ManageExcelSheets.amountRookies(1);
    private static final int amountRookieRBs = ManageExcelSheets.amountRookies(2);
    private static final int amountRookieTEs = ManageExcelSheets.amountRookies(3);

    /**
     * Listen und Excel-Datei erstellen
     */
    public static void createObjectLists() {
        sortLists();
        ManageExcelSheets.createExcel(amountDraftRounds, qbList, wrList, teList, rbsList, kickerList, defenseList);
    }

    /**
     * Sortiert die Listen der Spieler, sodass der mit der besten Value auf der 1 steht
     */
    private static void sortLists() {
        qbList = (ArrayList<Qbs>) ManageExcelSheets.readExcel("qb");
        qbList = Qbs.rewriteQBValues(qbList);
        qbList = Qbs.werteGewichten(qbList, tierS, tierA, tierB, tierC, tierD, tierE);
        qbList = Qbs.valueBerechnen(qbList);
        qbList = Qbs.sortAfterValue(qbList, amountRookieQBs);
        qbList = Qbs.reverseWerteGewichten(qbList, tierS, tierA, tierB, tierC, tierD, tierE);
        defenseList = (ArrayList<Defense>) ManageExcelSheets.readExcel("defense");
        defenseList = Defense.rewriteDefenseValues(defenseList);
        defenseList = Defense.werteGewichten(defenseList, tierS, tierA, tierB, tierC, tierD, tierE);
        defenseList = Defense.valueBerechnen(defenseList);
        defenseList = Defense.sortAfterValue(defenseList);
        defenseList = Defense.reverseWerteGewichten(defenseList, tierS, tierA, tierB, tierC, tierD, tierE);
        kickerList = (ArrayList<Kicker>) ManageExcelSheets.readExcel("kicker");
        kickerList = Kicker.rewriteKickerValues(kickerList);
        kickerList = Kicker.werteGewichten(kickerList, tierS, tierA, tierB, tierC, tierD, tierE);
        kickerList = Kicker.valueBerechnen(kickerList);
        kickerList = Kicker.sortAfterValue(kickerList);
        kickerList = Kicker.reverseWerteGewichten(kickerList, tierS, tierA, tierB, tierC, tierD, tierE);
        rbsList = (ArrayList<Rbs>) ManageExcelSheets.readExcel("rb");
        rbsList = Rbs.rewriteRBValues(rbsList);
        rbsList = Rbs.werteGewichten(rbsList, tierS, tierA, tierB, tierC, tierD, tierE);
        rbsList = Rbs.valueBerechnen(rbsList);
        rbsList = Rbs.sortAfterValue(rbsList, amountRookieRBs);
        rbsList = Rbs.reverseWerteGewichten(rbsList, tierS, tierA, tierB, tierC, tierD, tierE);
        teList = (ArrayList<Tes>) ManageExcelSheets.readExcel("te");
        teList = Tes.rewriteTEValues(teList);
        teList = Tes.werteGewichten(teList, tierS, tierA, tierB, tierC, tierD, tierE);
        teList = Tes.valueBerechnen(teList);
        teList = Tes.sortAfterValue(teList, amountRookieTEs);
        teList = Tes.reverseWerteGewichten(teList, tierS, tierA, tierB, tierC, tierD, tierE);
        wrList = (ArrayList<Wrs>) ManageExcelSheets.readExcel("wr");
        wrList = Wrs.rewriteWRValues(wrList);
        wrList = Wrs.werteGewichten(wrList, tierS, tierA, tierB, tierC, tierD, tierE);
        wrList = Wrs.valueBerechnen(wrList);
        wrList = Wrs.sortAfterValue(wrList, amountRookieWRs);
        wrList = Wrs.reverseWerteGewichten(wrList, tierS, tierA, tierB, tierC, tierD, tierE);
        addByeWeek();
        printRanking();
    }

    /**
     * Fügt Bye Week zu den Spielern hinzu
     */
    private static void addByeWeek() {
        for (int i = 0; i < wrList.size(); i++) {
            if(i < qbList.size()) {
                qbList.get(i).setByeWeek(determineByeWeek(qbList.get(i).getTeam()));
            }
            if(i < teList.size()) {
                teList.get(i).setByeWeek(determineByeWeek(teList.get(i).getTeam()));
            }
            if(i < rbsList.size()) {
                rbsList.get(i).setByeWeek(determineByeWeek(rbsList.get(i).getTeam()));
            }
            if(i < kickerList.size()) {
                kickerList.get(i).setByeWeek(determineByeWeek(kickerList.get(i).getTeam()));
            }
            if(i < defenseList.size()) {
                defenseList.get(i).setByeWeek(determineByeWeek(defenseList.get(i).getName()));
            }
            wrList.get(i).setByeWeek(determineByeWeek(wrList.get(i).getTeam()));
        }
    }

    /**
     * Bestimmt wann welches TEam seine jeweilige Bye Week hat
     *
     * @param team
     * @return
     */
    private static int determineByeWeek(String team) {
        switch (team) {
            case "Arizona Cardinals":
                return 8;
            case "Atlanta Falcons":
                return 5;
            case "Baltimore Ravens":
                return 7;
            case "Buffalo Bills":
                return 7;
            case "Carolina Panthers":
                return 14;
            case "Chicago Bears":
                return 5;
            case "Cincinnati Bengals":
                return 10;
            case "Cleveland Browns":
                return 9;
            case "Dallas Cowboys":
                return 10;
            case "Denver Broncos":
                return 12;
            case "Detroit Lions":
                return 8;
            case "Green Bay Packers":
                return 5;
            case "Houston Texans":
                return 6;
            case "Indianapolis Colts":
                return 11;
            case "Jacksonville Jaguars":
                return 8;
            case "Kansas City Chiefs":
                return 10;
            case "Las Vegas Raiders":
                return 8;
            case "Los Angeles Chargers":
                return 12;
            case "Los Angeles Rams":
                return 8;
            case "Miami Dolphins":
                return 12;
            case "Minnesota Vikings":
                return 6;
            case "New England Patriots":
                return 14;
            case "New Orleans Saints":
                return 11;
            case "New York Giants":
                return 14;
            case "New York Jets":
                return 9;
            case "Philadelphia Eagles":
                return 9;
            case "Pittsburgh Steelers":
                return 5;
            case "San Francisco 49ers":
                return 14;
            case "Seattle Seahawks":
                return 8;
            case "Tampa Bay Buccaneers":
                return 9;
            case "Tennessee Titans":
                return 10;
            case "Washington Commanders":
                return 12;
        }
        return 0;
    }


    /**
     * Prints the ranking in the console
     */
    private static void printRanking() {
        Qbs.printQBs(qbList);
        System.out.println();
        Defense.printDefense(defenseList);
        System.out.println();
        Kicker.printKickers(kickerList);
        System.out.println();
        Rbs.printRBs(rbsList);
        System.out.println();
        Tes.printTes(teList);
        System.out.println();
        Wrs.printWrs(wrList);
    }
}
