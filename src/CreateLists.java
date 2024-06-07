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
        qbList = ManageExcelSheets.readExcelQB();
        qbList = Qbs.rewriteQBValues(qbList);
        qbList = Qbs.werteGewichten(qbList, tierS, tierA, tierB, tierC, tierD, tierE);
        qbList = Qbs.valueBerechnen(qbList);
        qbList = Qbs.sortAfterValue(qbList, amountRookieQBs);
        qbList = Qbs.reverseWerteGewichten(qbList, tierS, tierA, tierB, tierC, tierD, tierE);
        addDefenseToList();
        defenseList = Defense.rewriteDefenseValues(defenseList);
        defenseList = Defense.werteGewichten(defenseList, tierS, tierA, tierB, tierC, tierD, tierE);
        defenseList = Defense.valueBerechnen(defenseList);
        defenseList = Defense.sortAfterValue(defenseList);
        defenseList = Defense.reverseWerteGewichten(defenseList, tierS, tierA, tierB, tierC, tierD, tierE);
        addKickerToList();
        kickerList = Kicker.rewriteKickerValues(kickerList);
        kickerList = Kicker.werteGewichten(kickerList, tierS, tierA, tierB, tierC, tierD, tierE);
        kickerList = Kicker.valueBerechnen(kickerList);
        kickerList = Kicker.sortAfterValue(kickerList);
        kickerList = Kicker.reverseWerteGewichten(kickerList, tierS, tierA, tierB, tierC, tierD, tierE);
        addRunningbackToList();
        rbsList = Rbs.rewriteRBValues(rbsList);
        rbsList = Rbs.werteGewichten(rbsList, tierS, tierA, tierB, tierC, tierD, tierE);
        rbsList = Rbs.valueBerechnen(rbsList);
        rbsList = Rbs.sortAfterValue(rbsList, amountRookieRBs);
        rbsList = Rbs.reverseWerteGewichten(rbsList, tierS, tierA, tierB, tierC, tierD, tierE);
        teList = (ArrayList<Tes>) ManageExcelSheets.readExcelReceiver("te");
        teList = Tes.rewriteTEValues(teList);
        teList = Tes.werteGewichten(teList, tierS, tierA, tierB, tierC, tierD, tierE);
        teList = Tes.valueBerechnen(teList);
        teList = Tes.sortAfterValue(teList, amountRookieTEs);
        teList = Tes.reverseWerteGewichten(teList, tierS, tierA, tierB, tierC, tierD, tierE);
        addWideReceiverToList();
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
     * ToDo: Für diese Season aktualisieren.
     *
     * @param team
     * @return
     */
    private static int determineByeWeek(String team) {
        switch (team) {
            case "Cleveland Browns":
                return 10;
            case "Los Angeles Chargers":
                return 5;
            case "Seattle Seahawks":
                return 10;
            case "Tampa Bay Buccaneers":
                return 11;
            case "Green Bay Packers":
                return 10;
            case "Pittsburgh Steelers":
                return 9;
            case "Carolina Panthers":
                return 11;
            case "Cincinnati Bengals":
                return 12;
            case "Dallas Cowboys":
                return 7;
            case "Houston Texans":
                return 14;
            case "New York Jets":
                return 12;
            case "Tennessee Titans":
                return 5;
            case "Denver Broncos":
                return 14;
            case "Detroit Lions":
                return 5;
            case "Jacksonville Jaguars":
                return 12;
            case "San Francisco 49ers":
                return 9;
            case "Kansas City Chiefs":
                return 6;
            case "Los Angeles Rams":
                return 6;
            case "Miami Dolphins":
                return 6;
            case "Philadelphia Eagles":
                return 5;
            case "Atlanta Falcons":
                return 12;
            case "Indianapolis Colts":
                return 14;
            case "New England Patriots":
                return 14;
            case "New Orleans Saints":
                return 12;
            case "Baltimore Ravens":
                return 14;
            case "Buffalo Bills":
                return 12;
            case "Chicago Bears":
                return 7;
            case "Las Vegas Raiders":
                return 10;
            case "Minnesota Vikings":
                return 6;
            case "New York Giants":
                return 11;
            case "Arizona Cardinals":
                return 11;
            case "Washington Commanders":
                return 14;
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

    /**
     * Creates and adds all Defenses to the defenseList
     */
    private static void addDefenseToList() {
        Defense sanFrancisco49ers = new Defense("San Francisco 49ers", 0, 6, 1, 7, 3, 4, 1, 0, 0, 1);
        Defense chicagoBears = new Defense("Chicago Bears", 0, 16, 6, 14, 3, 25, 31, 0, 0, 2);
        Defense cincinnatiBengals = new Defense("Cincinnati Bengals", 0, 15, 7, 10, 3, 20, 14, 0, 0, 3);
        Defense buffaloBills = new Defense("Buffalo Bills", 0, 9, 3, 13, 1, 16, 5, 0, 0, 4);
        Defense denverBroncos = new Defense("Denver Broncos", 0, 12, 5, 19, 2, 24, 12, 0, 0, 5);
        Defense clevelandBrowns = new Defense("Cleveland Browns", 0, 14, 9, 17, 3, 6, 13, 0, 0, 6);
        Defense tampaBayBuccaneers = new Defense("Tampa Bay Buccaneers", 0, 5, 10, 12, 3, 22, 18, 0, 0, 7);
        Defense arizonaCardinals = new Defense("Arizona Cardinals", 0, 12, 9, 15, 3, 2, 32, 0, 0, 8);
        Defense losAngelesChargers = new Defense("Los Angeles Chargers", 0, 9, 6, 9, 2, 19, 16, 0, 0, 9);
        Defense kansasCityChiefs = new Defense("Kansas City Chiefs", 0, 2, 9, 16, 2, 11, 17, 0, 0, 10);
        Defense indianapolisColts = new Defense("Indianapolis Colts", 0, 6, 10, 5, 3, 12, 29, 0, 0, 11);
        Defense washingtonCommanders = new Defense("Washington Commanders", 0, 7, 11, 18, 2, 8, 11, 0, 0, 12);
        Defense dallasCowboys = new Defense("Dallas Cowboys", 0, 3, 4, 1, 3, 5, 3, 0, 0, 13);
        Defense miamiDolphins = new Defense("Miami Dolphins", 0, 9, 12, 23, 2, 13, 10, 0, 0, 14);
        Defense philadelphiaEagles = new Defense("Philadelphia Eagles", 0, 1, 3, 8, 3, 10, 2, 0, 0, 15);
        Defense atlantaFalcons = new Defense("Atlanta Falcons", 0, 17, 10, 27, 2, 22, 22, 0, 0, 16);
        Defense newYorkGiants = new Defense("New York Giants", 0, 8, 14, 2, 3, 14, 25, 0, 0, 17);
        Defense jacksonvilleJaguars = new Defense("Jacksonville Jaguars", 0, 13, 6, 3, 3, 3, 24, 0, 0, 18);
        Defense newYorkJets = new Defense("New York Jets", 0, 5, 8, 28, 1, 26, 4, 0, 0, 19);
        Defense detroitLions = new Defense("Detroit Lions", 0, 10, 8, 10, 2, 21, 23, 0, 0, 20);
        Defense greenBayPackers = new Defense("Green Bay Packers", 0, 14, 3, 21, 2, 10, 15, 0, 0, 21);
        Defense carolinaPanthers = new Defense("Carolina Panthers", 0, 13, 10, 26, 3, 7, 19, 0, 0, 22);
        Defense newEnglandPatriots = new Defense("New England Patriots", 0, 3, 2, 6, 3, 1, 8, 0, 0, 23);
        Defense lasVegasRaiders = new Defense("Las Vegas Raiders", 0, 16, 14, 22, 3, 9, 26, 0, 0, 24);
        Defense losAngelesRams = new Defense("Los Angeles Rams", 0, 11, 4, 25, 3, 17, 27, 0, 0, 25);
        Defense baltimoreRavens = new Defense("Baltimore Ravens", 0, 4, 6, 11, 3, 25, 9, 0, 0, 26);
        Defense newOrleansSaints = new Defense("New Orleans Saints", 0, 4, 13, 20, 3, 23, 7, 0, 0, 27);
        Defense seattleSeahawks = new Defense("Seattle Seahawks", 0, 5, 6, 4, 3, 19, 21, 0, 0, 28);
        Defense pittsburghSteelers = new Defense("Pittsburgh Steelers", 0, 9, 1, 29, 3, 15, 6, 0, 0, 29);
        Defense houstonTexans = new Defense("Houston Texans", 0, 10, 4, 10, 2, 17, 28, 0, 0, 30);
        Defense tennesseeTitans = new Defense("Tennessee Titans", 0, 10, 6, 24, 3, 19, 20, 0, 0, 31);
        Defense minnesotaVikings = new Defense("Minnesota Vikings", 0, 11, 5, 13, 3, 18, 30, 0, 0, 32);

        defenseList.add(sanFrancisco49ers);
        defenseList.add(chicagoBears);
        defenseList.add(cincinnatiBengals);
        defenseList.add(buffaloBills);
        defenseList.add(denverBroncos);
        defenseList.add(clevelandBrowns);
        defenseList.add(tampaBayBuccaneers);
        defenseList.add(arizonaCardinals);
        defenseList.add(losAngelesChargers);
        defenseList.add(kansasCityChiefs);
        defenseList.add(indianapolisColts);
        defenseList.add(washingtonCommanders);
        defenseList.add(dallasCowboys);
        defenseList.add(miamiDolphins);
        defenseList.add(philadelphiaEagles);
        defenseList.add(atlantaFalcons);
        defenseList.add(newYorkGiants);
        defenseList.add(jacksonvilleJaguars);
        defenseList.add(newYorkJets);
        defenseList.add(detroitLions);
        defenseList.add(greenBayPackers);
        defenseList.add(carolinaPanthers);
        defenseList.add(newEnglandPatriots);
        defenseList.add(lasVegasRaiders);
        defenseList.add(losAngelesRams);
        defenseList.add(baltimoreRavens);
        defenseList.add(newOrleansSaints);
        defenseList.add(seattleSeahawks);
        defenseList.add(pittsburghSteelers);
        defenseList.add(houstonTexans);
        defenseList.add(tennesseeTitans);
        defenseList.add(minnesotaVikings);
    }

    /**
     * Creates and adds all kickers to the kickerList
     */
    private static void addKickerToList() {
        Kicker justinTucker = new Kicker("Justin Tucker", "Baltimore Ravens", 0, 1, 1, 2, 13, 10, 2, 1, 1, 0, 0, 1);
        Kicker danielCarlson = new Kicker("Daniel Carlson", "Las Vegas Raiders", 0, 3, 3, 1, 6, 7, 3, 2, 2, 0, 0, 2);
        Kicker jasonMyers = new Kicker("Jason Myer", "Seattle Seahawks", 0, 2, 2, 5, 6, 3, 4, 3, 7, 0, 0, 3);
        Kicker younghoeKoo = new Kicker("Younghoe Koo", "Atlanta Falcons", 0, 4, 5, 4, 12, 8, 5, 4, 4, 0, 0, 4);
        Kicker tylerBass = new Kicker("Tyler Bass", "Buffalo Bills", 0, 13, 12, 9, 10, 2, 6, 6, 5, 0, 0, 5);
        Kicker nickFolk = new Kicker("Nick Folk", "New England Patriots", 0, 5, 6,7, 12, 9, 6, 7, 11, 0, 0, 6);
        Kicker eddyPineiro = new Kicker("EddyPineiro", "Carolina Panthers", 0, 9, 4, 9, 3, 11, 6, 5, 15, 0, 0, 7);
        Kicker grahamGano = new Kicker("Graham Gano", "New York Giants", 0, 12, 11, 3, 8, 9, 7, 8, 6, 0, 0, 8);
        Kicker rileyPatterson = new Kicker("Riley Patterson", "Detroit Lions", 0, 10, 7, 9, 14, 6, 12, 9, 19, 0, 0, 9);
        Kicker gregZwerlin = new Kicker("Greg Zwerlin", "New York Jets", 0, 6, 9, 5, 20, 12, 13, 10, 13, 0, 0, 10);
        Kicker chaseMcLaughlin = new Kicker("Chase McLaughlin", "Tampa Bay Buccaneers", 0, 7, 8, 2, 15, 16, 8, 11, 28, 0, 0, 11);
        Kicker jasonSanders = new Kicker("Jason Sanders", "Miami Dolphins", 0, 14, 15, 9, 19, 3, 11, 12, 17, 0, 0, 12);
        Kicker brandonMcManas = new Kicker("Brandon McManas", "Jacksonville Jaguars", 0, 8, 14, 3, 21, 14, 11, 13, 10, 0, 0, 13);
        Kicker mattGay = new Kicker("Matt Gay", "Indianapolis Colts", 0, 18, 13, 4, 5, 10, 12, 14, 8, 0, 0, 14);
        Kicker gregJoseph = new Kicker("Greg Joseph", "Minnesota Vikings", 0, 11, 16, 7, 18, 4, 12, 15, 20, 0, 0, 15);
        Kicker evanMcPherson = new Kicker("Evan McPherson", "Cincinnati Bengals", 0, 20, 28, 6, 17, 3, 13, 17, 3, 0, 0, 16);
        Kicker kaimiFairbairn = new Kicker("Ka'imi Fairbairn", "Houston Texans", 0, 16, 10, 5, 4, 15, 13, 16, 16, 0, 0, 17);
        Kicker jakeElliott = new Kicker("Jake Elliott", "Philadelphia Eagles", 0, 25, 24, 6, 11, 1, 15, 18, 9, 0, 0, 18);
        Kicker cadeYork = new Kicker("Cade York", "Cleveland Browns", 0, 15, 19, 7, 22, 7, 17, 19, 27, 0, 0, 19);
        Kicker joeySlye = new Kicker("Joey Slye", "Washington Commanders", 0, 19, 17, 7, 16, 15, 18, 20, 24, 0, 0, 20);
        Kicker wilLutz = new Kicker("Wil Lutz", "New Orleans Saints", 0, 17, 20, 7, 25, 8, 19, 21, 23, 0, 0, 21);
        Kicker cairoSantos = new Kicker("Cairo Santos", "Chicago Bears", 0, 24, 23, 7, 7, 13, 20, 22, 12, 0, 0, 22);
        Kicker harrisonButker = new Kicker("Harrison Butker", "Kansas City Chiefs", 0, 23, 26, 8, 23, 5, 14, 23, 4, 0, 0, 23);
        Kicker mattPrater = new Kicker("Matt Prater", "Arizona Cardinals", 0, 22, 21, 6, 9, 18, 16, 24, 18, 0, 0, 24);
        Kicker chrisBoswell = new Kicker("Chris Boswell", "Pittsburgh Steelers", 0, 21, 25, 4, 26, 17, 10, 25, 22, 0, 0, 25);
        Kicker cameronDicker = new Kicker("Cameron Dicker", "Los Angeles Chargers", 0, 26, 22, 10, 2, 15, 9, 26, 14, 0, 0, 26);
        Kicker calebShudak = new Kicker("Caleb Shudak", "Tennessee Titans", 0, 27, 27, 10, 24, 20, 1, 27, 26, 0, 0, 27);
        Kicker tristanVizcaino = new Kicker("Tristan Vizcaino", "Dallas Cowboys", 0, 28, 28, 10, 1, 19, 21, 28, 21, 0, 0, 28);
        Kicker elliottFry = new Kicker("Elliott Fry", "Denver Broncos", 0, 29, 29, 10, 27, 21, 22, 29, 25, 0, 0, 29);
        Kicker tannerBrown = new Kicker("Tanner Brown", "Los Angeles Rams", 0, 29, 29, 10, 27, 21, 22, 29, 30, 0, 0, 30);
        Kicker jakeMoody = new Kicker("Jake Moody", "San Francisco 49ers", 0, 29, 29, 10, 27, 21, 22, 29, 29, 0, 0, 31);
        Kicker andersCarlson = new Kicker("Anders Carlson", "Green Bay Packers", 0, 29, 29, 10, 27, 21, 22, 29, 31, 0, 0, 32);

        kickerList.add(justinTucker);
        kickerList.add(danielCarlson);
        kickerList.add(jasonMyers);
        kickerList.add(younghoeKoo);
        kickerList.add(tylerBass);
        kickerList.add(nickFolk);
        kickerList.add(eddyPineiro);
        kickerList.add(grahamGano);
        kickerList.add(rileyPatterson);
        kickerList.add(gregZwerlin);
        kickerList.add(chaseMcLaughlin);
        kickerList.add(jasonSanders);
        kickerList.add(brandonMcManas);
        kickerList.add(mattGay);
        kickerList.add(gregJoseph);
        kickerList.add(evanMcPherson);
        kickerList.add(kaimiFairbairn);
        kickerList.add(jakeElliott);
        kickerList.add(cadeYork);
        kickerList.add(joeySlye);
        kickerList.add(wilLutz);
        kickerList.add(cairoSantos);
        kickerList.add(harrisonButker);
        kickerList.add(mattPrater);
        kickerList.add(chrisBoswell);
        kickerList.add(cameronDicker);
        kickerList.add(calebShudak);
        kickerList.add(tristanVizcaino);
        kickerList.add(elliottFry);
        kickerList.add(tannerBrown);
        kickerList.add(jakeMoody);
        kickerList.add(andersCarlson);
    }

    /**
     * Creates and adds all running backs to the runningBackList
     */
    private static void addRunningbackToList() {
        Rbs joshJacobs = new Rbs("Josh Jacobs", "Las Vegas Raiders", 0, 8, 7, 1, 2, 4, 2, 1, 19, 10, false, 0, 0, 1);
        Rbs christianMcCaffrey = new Rbs("Christian McCaffrey", "San Francisco 49ers", 0, 2, 1, 6, 8, 3, 3, 2, 18, 1, false, 0, 0, 2);
        Rbs derrickHenry = new Rbs("Derrick Henry", "Tennessee Titans", 0, 10, 8, 2, 1, 3, 1, 3, 32, 8, false, 0, 0, 3);
        Rbs austinEkeler = new Rbs("Austin Ekeler", "Los Angeles Chargers", 0, 1, 2, 16, 16, 1, 4, 4, 9, 2, false, 0, 0, 4);
        Rbs nickChubb = new Rbs("Nick Chubb", "Cleveland Browns", 0, 25, 21, 3, 3, 3, 5, 5, 2, 5, false, 0, 0, 5);
        Rbs saquonBarkley = new Rbs("SaquonBarkley", "New York Giants", 0, 6, 13, 4, 4, 6, 6, 6, 29, 6, false, 0, 0, 6);
        Rbs jamaalWilliams = new Rbs("Jamaal Williams", "New Orleans Saints", 0, 35, 36, 9, 6, 2, 9, 7, 24, 35, false, 0, 0, 7);
        Rbs tonyPollard = new Rbs("Tony Pollard", "Dallas Cowboys", 0, 15, 11, 14, 19, 4, 8, 8, 6, 7, false, 0, 0, 8);
        Rbs milesSanders = new Rbs("Miles Sanders", "Carolina Panthers", 0, 28, 34, 5, 7, 5, 13, 9, 16, 20, false, 0, 0, 9);
        Rbs aaronJones = new Rbs("Aaron Jones", "Green Bay Packers", 0, 5, 9, 8, 12, 9, 14, 10, 8, 17, false, 0, 0, 10);
        Rbs najeeHarris = new Rbs("Najee Harris", "Pittsburgh Steelers", 0, 14, 22, 13, 5, 6, 15, 11, 12, 12, false, 0, 0, 11);
        Rbs joeMixon = new Rbs("Joe Mixon", "Cinncinnati Bengals", 0, 4, 5, 22, 13, 7, 10, 12, 17, 15, false, 0, 0, 12);
        Rbs rhamondreStevenson = new Rbs("Rhamondre Stevenson", "New England Patriots", 0, 3, 6, 11, 14, 10, 16, 13, 13, 9, false, 0, 0, 13);
        Rbs kennethWalker3 = new Rbs("Kenneth Walker III", "Seattle Seahawks", 0, 26, 27, 10, 8, 7, 12, 14, 40, 16, false, 0, 0, 14);
        Rbs travisEtienneJr = new Rbs("Travis Etienne Jr.", "Jacksonville Jaguars", 0, 18, 14, 7, 10, 11, 19, 15, 26, 13, false, 0, 0, 15);
        Rbs jamesConner = new Rbs("James Conner", "Arizona Cardinals", 0, 12, 16, 26, 23, 8, 11, 17, 31, 27, false, 0, 0, 16);
        Rbs davidMontgomery = new Rbs("David Montgomery", "Detroit Lions", 0, 19, 15, 24, 18, 10, 23, 18, 5, 25, false, 0, 0, 17);
        Rbs dAndreSwift = new Rbs("D'Andre Swift", "Philadelphia Eagles", 0, 10, 10, 30, 31, 8, 18, 20, 1, 23, false, 0, 0, 18);
        Rbs jerickMcKinnon = new Rbs("Jerick McKinnon", "Kansas City Chiefs", 0, 7, 3, 37, 36, 6, 27, 21, 3, 36, false, 0, 0, 19);
        Rbs alvinKamara = new Rbs("Alvin Kamara", "New Orleans Saints", 0, 5, 4, 18, 9, 12, 17, 16, 24, 30, false, 0, 0, 20);
        Rbs tylerAllgeier = new Rbs("Tyler Allgeier", "Atlanta Falcons", 0, 30, 29, 12, 15, 12, 24, 19, 7, 37, false, 0, 0, 21);
        Rbs devinSingletary = new Rbs("Devin Singletary", "Houston Texans", 0, 16, 20, 23, 25, 20, 28, 22, 25, 38, false, 0, 0, 22);
        Rbs ajDillon = new Rbs("Aj Dillon", "Green Bay Packers", 0, 23, 24, 27, 22, 9, 29, 23, 8, 32, false, 0, 0, 23);
        Rbs raheemMostert = new Rbs("Raheem Mostert", "Miami Dolphins", 0, 21, 25, 19, 24, 11, 25, 24, 20, 30, false, 0, 0, 24);
        Rbs camAkers = new Rbs("Cam Akers", "Los Angeles Rams", 0, 34, 32, 25, 21, 7, 26, 26, 28, 22, false, 0, 0, 25);
        Rbs lataviusMerray = new Rbs("Latavius Merray", "Buffalo Bills", 0, 27, 30, 28, 26, 20, 22, 27, 22, 29, false, 0, 0, 26);
        Rbs dOntaForeman = new Rbs("D'Onta Foreman", "Chicago Bears", 0, 37, 38, 17, 27, 11, 30, 28, 21, 40, false, 0, 0, 27);
        Rbs isiahPacheco = new Rbs("Isiah Pacheco", "Kansas City Chiefs", 0, 33, 31, 21, 27, 11, 31, 29, 3, 24, false, 0, 0, 28);
        Rbs antonioGibson = new Rbs("Antonio Gibson", "Washington Commanders", 0, 11, 12, 29, 28, 11, 29, 30, 27, 3, false, 0, 0, 29);
        Rbs jonathanTaylor = new Rbs("Jonathan Taylor", "Indianapolis Colts", 0, 24, 28, 20, 20, 12, 20, 31, 11, 34, false, 0, 0, 30);
        Rbs samajePerine = new Rbs("Samaje Perine", "Denver Broncos", 0, 17, 19, 35, 32, 10, 32, 32, 11, 34, false, 0, 0, 31);
        Rbs rachaadWhite = new Rbs("Rachaad White", "Tampa Bay Buccaneers", 0, 9, 17, 32, 29, 13, 36, 34, 14, 26, false, 0, 0, 32);
        Rbs michaelCarter = new Rbs("Michael Carter", "New York Jets", 0, 13, 18, 34, 30, 13, 35, 35, 23, 41, false, 0, 0, 33);
        Rbs jkDobbins = new Rbs("Jk Dobbins", "Baltimore Ravens", 0, 36, 37, 31, 33, 13, 21, 36, 4, 18, false, 0, 0, 34);
        Rbs alexanderMattison = new Rbs("Alexander Mattison", "Minnesota Vikings", 0, 32, 33, 37, 35, 10, 37, 37, 15, 29, false, 0, 0, 35);
        Rbs jahmyrGibbs = new Rbs("Jahmyr Gibbs", "Detroit Lions", 0, 38, 38, 39, 38, 12, 38, 38, 5, 14, true, 0, 0, 36);
        Rbs bijanRobinson = new Rbs("Bijan Robbinson", "Atlanta Falcons", 0, 38, 38, 39, 38, 12, 38, 38, 7, 3, true, 0, 0, 37);
        Rbs zachCharbonnet = new Rbs("Zach Charbonnet", "Seattle Seahawks", 0, 38, 38, 39, 38, 12, 38, 38, 30, 31, true, 0, 0, 38);
        Rbs breeceHall = new Rbs("Breece Hall", "New York Jets", 0, 29, 23, 33, 34, 11, 7, 33, 23, 11, false, 0, 0, 39);
        Rbs deamonPierce = new Rbs("Deamon Pierce", "Houston Texans", 0, 22, 26, 15, 11, 11, 33, 25, 25, 21, false, 0, 0, 40);
        Rbs javonteWiliams = new Rbs("Javonte Wiliams", "Denver Broncos", 0, 31, 35, 38, 37, 12, 34, 37, 11, 28, false, 0, 0, 41);

        rbsList.add(joshJacobs);
        rbsList.add(christianMcCaffrey);
        rbsList.add(derrickHenry);
        rbsList.add(austinEkeler);
        rbsList.add(nickChubb);
        rbsList.add(saquonBarkley);
        rbsList.add(jamaalWilliams);
        rbsList.add(tonyPollard);
        rbsList.add(milesSanders);
        rbsList.add(aaronJones);
        rbsList.add(najeeHarris);
        rbsList.add(joeMixon);
        rbsList.add(rhamondreStevenson);
        rbsList.add(kennethWalker3);
        rbsList.add(travisEtienneJr);
        rbsList.add(jamesConner);
        rbsList.add(davidMontgomery);
        rbsList.add(dAndreSwift);
        rbsList.add(jerickMcKinnon);
        rbsList.add(alvinKamara);
        rbsList.add(tylerAllgeier);
        rbsList.add(devinSingletary);
        rbsList.add(ajDillon);
        rbsList.add(raheemMostert);
        rbsList.add(camAkers);
        rbsList.add(lataviusMerray);
        rbsList.add(dOntaForeman);
        rbsList.add(isiahPacheco);
        rbsList.add(antonioGibson);
        rbsList.add(jonathanTaylor);
        rbsList.add(samajePerine);
        rbsList.add(rachaadWhite);
        rbsList.add(michaelCarter);
        rbsList.add(jkDobbins);
        rbsList.add(alexanderMattison);
        rbsList.add(jahmyrGibbs);
        rbsList.add(bijanRobinson);
        rbsList.add(zachCharbonnet);
        rbsList.add(breeceHall);
        rbsList.add(deamonPierce);
        rbsList.add(javonteWiliams);
    }

    /**
     * Creates and adds all tight ends to the tightEndList
     */
    private static void addTightEndToList() {
        Tes travisKelce = new Tes("Travis Kelce", "Kansas City Ciefs", 0, 1, 1, 1, 1, 1, 2, 1, false, 0, 0, 1);
        Tes georgeKittle = new Tes("George Kittle", "San Francisco 49ers", 0, 7, 5, 2, 2, 2, 21, 4, false, 0, 0, 2);
        Tes taysomHill = new Tes("Taysom Hill", "New Orleans Saints", 0, 34, 8, 3, 3, 3, 17, 19, false, 0, 0, 3);
        Tes tjHockenson = new Tes("T. J. Hockenson", "Minnesota Vikings", 0, 2, 2, 5, 5, 4, 8, 3, false, 0, 0, 4);
        Tes markAndrews = new Tes("Mark Andrews", "Baltimore Ravens", 0, 3, 3, 6, 4, 5, 11, 2, false, 0, 0, 5);
        Tes evanEngram = new Tes("Evan Engram", "Jacksonville Jaguars", 0, 4, 4, 7, 9, 6, 7, 8, false, 0, 0, 6);
        Tes coleKmet = new Tes("Cole Kmet", "Chicago Bears", 0, 14, 14, 4, 13, 7, 9, 16, false, 0, 0, 7);
        Tes juwanJohnson = new Tes("Juwan Johnson", "New Orleans Saints", 0, 18, 16, 4, 11, 8, 17, 21, false, 0, 0, 8);
        Tes dawsonKnox = new Tes("Dawson Knox", "Buffalo Bills", 0, 16, 15, 5, 12, 9, 4, 22, false, 0, 0, 9);
        Tes dallasGoedert = new Tes("Dallas Goedert", "Philadelphia Eagles", 0, 12, 7, 8, 6, 10, 1, 6, false, 0, 0, 10);
        Tes daltonSchultz = new Tes("Dalton Schultz", "Houston Texans", 0, 11, 11, 6, 14, 11, 32, 14, false, 0, 0, 11);
        Tes patFreiermuth = new Tes("Pat Freiermuth", "Pittsburgh Steelers", 0, 6, 6, 9, 15, 12, 24, 10, false, 0, 0, 12);
        Tes davidNjoku = new Tes("David Njoku", "Cleveland Browns", 0, 8, 9, 7, 10, 13, 19, 9, false, 0, 0, 13);
        Tes geraldEverett = new Tes("Gerald Everett", "Los Angeles Chargers", 0, 9, 12, 7, 16, 14, 5, 18, false, 0, 0, 14);
        Tes tylerHigbee = new Tes("Tyler Higbee", "Los Angeles Rams", 0, 5, 10, 8, 19, 15, 22, 15, false, 0, 0, 15);
        Tes jordanAkins = new Tes("Jordan Akins", "Cleveland Browns", 0, 22, 16, 6, 17, 16, 19, 36, false, 0, 0, 16);
        Tes tylerConklin = new Tes("Tyler Conklin", "New York Jets", 0, 10, 13, 8, 21, 17, 15, 24, false, 0, 0, 17);
        Tes noahFant = new Tes("Noah Fant", "Seattle Seahawks", 0, 15, 18, 7, 22, 18, 6, 29 , false, 0, 0, 18);
        Tes zachErtz = new Tes("Zach Ertz", "Arizona Cardinals", 0, 17, 23, 7, 7, 19, 18, 33, false, 0, 0, 19);
        Tes mikeGesicki = new Tes("Mike Gesicki", "New England Patriots", 0, 25, 26, 6, 26, 20, 23, 23, false, 0, 0, 20);
        Tes chigoziemOkonkwo = new Tes("Chigoziem Okonkwo", "Tennessee Titans", 0,24, 19, 8, 23, 21, 26, 11, false, 0, 0, 21);
        Tes darrenWaller = new Tes("Darren Waller", "New York Giants", 0, 26, 25, 8, 8, 22, 12, 7, false, 0, 0, 22);
        Tes austinHooper = new Tes("Austin Hooper", "Las Vegas Raiders", 0, 20, 20, 9, 28, 23, 20, 38, false, 0, 0, 23);
        Tes haydenHurst = new Tes("Hayden Hurst", "Carolina Panthers", 0, 13, 21, 9, 25, 24, 31, 25, false, 0, 0, 24);
        Tes gregDulcich = new Tes("Greg Dulcich", "Denver Broncos", 0, 23, 22, 9, 18, 25, 16, 13, false, 0, 0, 25);
        Tes cadeOtton = new Tes("Cade Otton", "Tampa Bay Buccaneers", 0, 19, 24, 9, 30, 26, 28, 32, false, 0, 0, 26);
        Tes jelaniWoods = new Tes("Jelani Woods", "Indianapolis Colts", 0, 28, 29, 8, 24, 27, 30, 30, false, 0, 0, 27);
        Tes kylePitts = new Tes("Kyle Pitts", "Atlanta Falcons", 0, 27, 27, 9, 20, 28, 29, 5, false, 0, 0, 28);
        Tes loganThomas = new Tes("Logan Thomas", "Washington Commanders", 0, 21, 28, 10, 31, 30, 25, 31, false, 0, 0, 29);
        Tes irvSmithJr = new Tes("Irv Smith Jr.", "Cincinnati Bengals", 0, 29, 31, 11, 27, 31, 3, 17, false, 0, 0, 30);
        Tes jakeFerguson = new Tes("Jake Ferguson", "Dallas Cowboys", 0, 30, 32, 9, 32, 32, 14, 27, false, 0, 0, 31);
        Tes darhamSmythe = new Tes("Darham Smythe", "Miami Dolphins", 0, 32, 33, 10, 33, 33, 10, 34, false, 0, 0, 32);
        Tes josiahDeguard = new Tes("Josiah Deguard", "Green Bay Packers", 0, 33, 34, 11, 34, 34, 27, 40, false, 0, 0, 33);
        Tes daltonKincaid = new Tes("Dalton Kincaid", "Buffalo Bills", 0, 35, 35, 11, 35, 35, 4, 12, true, 0, 0, 34);
        Tes samLaPorta = new Tes("Sam LaPorta", "Detroit Lions", 0, 35, 35, 11, 35, 35, 13, 20, true, 0, 0, 35);
        Tes michaelMeyer = new Tes("Michael Meyer", "Las Vegas Raiders", 0, 35, 35, 11, 35, 35, 20, 28, true, 0, 0, 36);
        Tes lukeMusgrave = new Tes("Lukas Musgrave", "Green Bay Packers", 0, 35, 35, 11, 35, 35, 27, 26, true, 0, 0, 37);
        Tes lukeSchoonmaker = new Tes("Luke Schoonmaker", "Dallas Cowboys", 0, 35, 35, 11, 35, 35, 14, 35, true, 0, 0, 38);
        Tes brentonStrange = new Tes("Brenton Strange", "Jacksonville Jaguars", 0, 35, 35, 11, 35, 35, 7, 39, true, 0, 0, 39);

        teList.add(travisKelce);
        teList.add(georgeKittle);
        teList.add(taysomHill);
        teList.add(tjHockenson);
        teList.add(markAndrews);
        teList.add(evanEngram);
        teList.add(coleKmet);
        teList.add(juwanJohnson);
        teList.add(dawsonKnox);
        teList.add(dallasGoedert);
        teList.add(daltonSchultz);
        teList.add(patFreiermuth);
        teList.add(davidNjoku);
        teList.add(geraldEverett);
        teList.add(tylerHigbee);
        teList.add(jordanAkins);
        teList.add(tylerConklin);
        teList.add(noahFant);
        teList.add(zachErtz);
        teList.add(mikeGesicki);
        teList.add(chigoziemOkonkwo);
        teList.add(darrenWaller);
        teList.add(austinHooper);
        teList.add(haydenHurst);
        teList.add(gregDulcich);
        teList.add(cadeOtton);
        teList.add(jelaniWoods);
        teList.add(kylePitts);
        teList.add(loganThomas);
        teList.add(irvSmithJr);
        teList.add(jakeFerguson);
        teList.add(darhamSmythe);
        teList.add(josiahDeguard);
        teList.add(daltonKincaid);
        teList.add(samLaPorta);
        teList.add(michaelMeyer);
        teList.add(lukeMusgrave);
        teList.add(lukeSchoonmaker);
        teList.add(brentonStrange);
    }

    /**
     * Creates and adds all wide receivers to the wideReceiverList
     */
    private static void addWideReceiverToList() {
        Wrs marquiseBrown = new Wrs("Marquise Brown", "Arizona Cardinals", 0, 32, 43, 9, 28, 44, 18, 31, false, 0, 0, 1);
        Wrs gregDortch = new Wrs("Greg Dortch", "Arizona Cardinals", 0, 50, 68, 10, 73, 62, 18, 81, false, 0, 0, 2);
        Wrs ronadaleMoore = new Wrs("Ronadale Moore", "Arizona Cardinals", 0, 66, 78, 11, 40, 79, 18, 54, false, 0, 0, 3);
        Wrs drakeLondon = new Wrs("Drake London", "Atlanta Falcons", 0, 27, 28, 8, 46, 31, 29, 22, false, 0, 0, 4);
        Wrs mackHollins = new Wrs("Mack Hollins", "Atlanta Falcons", 0, 44, 45, 8, 56, 46, 29, 82, false, 0, 0, 5);
        Wrs khaDarelHodge = new Wrs("KhaDare Hodge", "Atlanta Falcons", 0, 93, 91, 11, 95, 93, 29, 106, false, 0, 0, 6);
        Wrs devinDuvernay = new Wrs("Devin Duvernay", "Baltimore Ravens", 0, 70, 79, 8, 63, 58, 11, 86, false, 0, 0, 7);
        Wrs nelsonAgholor = new Wrs("Nelson Agholor", "Baltimore Ravens", 0, 78, 84, 10, 89, 86, 11, 85, false, 0, 0, 8);
        Wrs rashodBateman = new Wrs("Rashod Bateman", "Baltimore Ravens", 0, 92,89, 10, 58, 91, 11, 42, false, 0, 0, 9);
        Wrs odellBackhamJr = new Wrs("Odell Backham Jr.", "Baltimore Ravens", 0, 97, 97, 12, 97, 97, 11, 47, true, 0, 0, 10);
        Wrs stefonDiggs = new Wrs("Stefon Diggs", "Buffalo Bills", 0, 3, 5, 2, 5, 4, 4, 5, false, 0, 0, 11);
        Wrs gabeDavis = new Wrs("Gabe Davis", "Buffalo Bills", 0, 52, 30, 5, 37, 36, 4, 38, false, 0, 0, 12);
        Wrs trentSherfield = new Wrs("Trent Sherfield", "Buffalo Bills", 0, 80, 76, 10, 85, 81, 4, 83, false, 0, 0, 13);
        Wrs adamThielen = new Wrs("Adam Thielen", "Carolina Panthers", 0, 29, 42, 6, 43, 30, 31, 58, false, 0, 0, 14);
        Wrs djCarkJr = new Wrs("Dj Cark Jr.", "Carolina Panthers", 0, 79, 63, 9, 57, 74, 31, 63, false, 0, 0, 15);
        Wrs terraceMarshallJr = new Wrs("Terrace Marshall Jr.", "Carolina Panthers", 0, 82, 65, 11, 82, 82, 31, 74, false, 0, 0, 16);
        Wrs djMoore = new Wrs("Dj Moore", "Chicago Bears", 0, 37, 26, 5, 32, 24, 9, 26, false, 0, 0, 17);
        Wrs darnellMooney = new Wrs("Darnell Mooney", "Chicago Bears", 0, 68, 64, 10, 55, 71, 9, 53, false, 0, 0, 18);
        Wrs chaseClaypool = new Wrs("Chase Claypool", "Chicago Bears", 0, 59, 70, 11, 75, 67, 9, 76, false, 0, 0, 19);
        Wrs jaMarrChase = new Wrs("Ja'Marr Chase", "Cincinnati Bengals", 0, 12, 16, 3, 6, 11, 3, 2, false, 0, 0, 20);
        Wrs teeHiggins = new Wrs("Tee Higgins", "Cincinnati Bengals", 0, 24, 17, 5, 15, 18, 3, 14, false, 0, 0, 21);
        Wrs tylerBoyd = new Wrs("Tyler Boyd", "Cincinnati Bengals", 0, 43, 37, 6, 48, 34, 3, 45, false, 0, 0, 22);
        Wrs amariCooper = new Wrs("Amari Cooper", "Cleveland Browns", 0, 18, 11, 3, 18, 10, 19, 17, false, 0, 0, 23);
        Wrs denovanPeoplesJones = new Wrs("Denovan Peoples-Jones", "Cleveland Browns", 0, 39, 29, 9, 50, 39, 19, 66, false, 0, 0, 24);
        Wrs marquiseGoodwin = new Wrs("Marquise Goodwin", "Cleveland Browns", 0, 83, 82, 8, 76, 77, 19, 97, false, 0, 0, 25);
        Wrs ceeDeeLamb = new Wrs("CeeDee Lamb", "Dallas Cowboys", 0, 4, 6, 3, 7, 5, 14, 7, false, 0, 0, 26);
        Wrs brandinCooks = new Wrs("Brandin Cooks", "Dallas Cowboys", 0, 45, 44, 9, 38, 49, 14, 41, false, 0, 0, 27);
        Wrs michaelGallup = new Wrs("Michael Gallup", "Dallas Cowboys", 0, 69, 74, 8, 69, 66, 14, 55, false, 0, 0, 28);
        Wrs jerryJeudy = new Wrs("Jerry Jeudy", "Denver Broncos", 0, 30, 22, 6, 20, 22, 16, 20, false, 0, 0, 29);
        Wrs courtlandSutton = new Wrs("Courtland Sutton", "Denver Broncos", 0, 35, 31, 10, 45, 43, 16, 44, false, 0, 0, 30);
        Wrs kendallHinton = new Wrs("Kendall Hinton", "Denver Broncos", 0, 87, 87, 12, 88, 90, 16, 101, false, 0, 0, 31);
        Wrs amonRaStBrown = new Wrs("Amon-Ra St. Brown", "Detroit Lions", 0, 5, 10, 6, 10, 7, 13, 9, false, 0, 0, 32);
        Wrs marvinJonesJr = new Wrs("Marvin Jones Jr.", "Detroit Lions", 0, 57, 60, 9, 70, 56, 13, 77, false, 0, 0, 33);
        Wrs kalifRaymond = new Wrs("Kalif Raymons", "Detroit Lions", 0, 55, 51, 12, 77, 57, 13, 91, false, 0, 0, 34);
        Wrs christianWatson = new Wrs("Christian Watson", "Green Bay Packers", 0, 64, 52, 3, 34, 41, 27, 21, false, 0, 0, 35);
        Wrs romeoDoubs = new Wrs("Romeo Doubs", "Green Bay Packers", 0, 63, 73, 9, 66, 70, 27, 50, false, 0, 0, 36);
        Wrs samoriToure = new Wrs("Samori Toure", "Green Bay Packers", 0, 96, 95, 11, 91, 95, 27, 96, false, 0, 0, 37);
        Wrs noahBrown = new Wrs("Noah Brown", "Houston Texans", 0, 60, 55, 9, 72, 61, 32, 98, false, 0, 0, 38);
        Wrs robertWoods = new Wrs("Robert Woods", "Houston Texans", 0, 48, 61, 10, 78, 59, 32, 73, false, 0, 0, 39);
        Wrs nicoCollins = new Wrs("Nico Collins", "Houston Texans", 0, 72, 67, 10, 52, 75, 32, 49, false, 0, 0, 40);
        Wrs michaelPittmanJr = new Wrs("Michael Pittman Jr", "Indianapolis Colts", 0, 8, 24, 8, 23, 20, 30, 34, false, 0, 0, 41);
        Wrs isaiahMcKenzie = new Wrs("Isaiah McKenzie", "Indianapolis Colts", 0, 62, 75, 7, 68, 55, 30, 95, false, 0, 0, 42);
        Wrs alecPierce = new Wrs("Alec Pierce", "Indianapolis Colts", 0, 64, 53, 10, 74, 64, 30, 62, false, 0, 0, 43);
        Wrs christianKirk = new Wrs("Christian Kirk", "Jacksonville Jaguars", 0, 14, 13, 4, 19, 12, 7, 27, false, 0, 0, 44);
        Wrs zayJones = new Wrs("Zay Jones", "Jacksonville Jaguars", 0, 17, 32, 7, 31, 26, 7, 51, false, 0, 0, 45);
        Wrs jamalAgrew = new Wrs("Jamal Agrew", "Jacksonville Jaguars", 0, 88, 92, 9, 90, 87, 7, 93, false, 0, 0, 46);
        Wrs calvinRidley = new Wrs("Calvin Ridley", "Jacksonville Jaguars", 0, 97, 97, 12, 97, 97, 7, 16, true, 0, 0, 47);
        Wrs marquezValdesScantling = new Wrs("Marquez Valdes-Scantling", "Kansas City Chiefs", 0, 61, 46, 10, 71, 54, 2, 61, false, 0, 0, 48);
        Wrs richieJamesJr = new Wrs("Richie Jamees Jr.", "Kansas City Chiefs", 0, 46, 54, 8, 62, 50, 2, 78, false, 0, 0, 49);
        Wrs justinWatson = new Wrs("Justin Watson", "Kansas City Chiefs", 0, 91, 86, 10, 93, 88, 2, 94, false, 0, 0, 50);
        Wrs mikeWilliams = new Wrs("Mike Williams", "Los Angeles Chargers", 0, 38, 25, 8, 21, 32, 5, 25, false, 0, 0, 51);
        Wrs keenanAllen = new Wrs("Keenan Allen", "Los Angeles Chargers", 0, 33, 39, 8, 11, 42, 5, 19, false, 0, 0, 52);
        Wrs joshuaPalmer = new Wrs("Joshua Palmer", "Los Angeles Chargers", 0, 28, 38, 9, 44, 37, 5, 79, false, 0, 0, 53);
        Wrs cooperKupp = new Wrs("Cooper Kupp", "Los Angeles Rams", 0, 25, 33, 5, 1, 23, 22, 3, false, 0, 0, 54);
        Wrs demarcusRobinson = new Wrs("Demarcus Robinson", "Los Angeles Rams", 0, 54, 69, 10, 81, 68, 22, 103, false, 0, 0, 55);
        Wrs vanJefferson = new Wrs("Van Jefferson", "Los Angeles Rams", 0, 86, 83, 9, 65, 85, 22, 56, false, 0, 0, 56);
        Wrs devanteAdams = new Wrs("Devante Adams", "Las Vegas Raiders", 0, 7, 3, 1, 4, 3, 20, 10, false, 0, 0, 57);
        Wrs jakobiMeyers = new Wrs("Jakobi Meyers", "Las Vegas Raiders", 0, 31, 34, 6, 29, 29, 20, 52, false, 0, 0, 58);
        Wrs deAndreCarter = new Wrs("DeAndre Carter", "Las Vegas Raiders", 0, 58, 58, 9, 79, 63, 20, 102, false, 0, 0, 59);
        Wrs tyreekHill = new Wrs("Tyreek Hill", "Miami Dolphins", 0, 2, 2, 4, 3, 2, 10, 4, false, 0, 0, 60);
        Wrs jaylenWaddle = new Wrs("Jaylen Waddle", "Miami Dolphins", 0, 23, 7, 4, 12, 8, 10, 11, false, 0, 0, 61);
        Wrs braxtonBerrios = new Wrs("Braxton Berrios", "Miami Dolphins", 0, 90, 94, 10, 94, 89, 10, 84, false, 0, 0, 62);
        Wrs justinJefferson = new Wrs("Justin Jefferson", "Minnesota Vikings", 0, 1, 1, 3, 2, 1, 8, 1, false, 0, 0, 63);
        Wrs kJOsborn = new Wrs("K. J. Osborn", "Minnesota Vikings", 0, 42, 48, 7, 54, 45, 8, 59, false, 0, 0, 64);
        Wrs jalenNailor = new Wrs("Jalen Nailor", "Minnesota Vikings", 0, 94, 93, 11, 92, 94, 8, 100, false, 0, 0, 65);
        Wrs juJuSmithSchuster = new Wrs("JuJu Smith-Schuster", "New England Patriots", 0, 20, 23, 9, 36, 27, 23, 48, false, 0, 0, 66);
        Wrs deVanteParker = new Wrs("DeVante Parker", "New England Patriots", 0, 77, 57, 9, 64, 69, 23, 71, false, 0, 0, 67);
        Wrs kendrickBourne = new Wrs("Kendrick Bourne", "New England Patriots", 0, 74, 71, 11, 83, 78, 23, 89, false, 0, 0, 68);
        Wrs chrisOlave = new Wrs("Chris Olave", "New Orleans Saints", 0, 26, 18, 8, 26, 25, 17, 12, false, 0, 0, 69);
        Wrs rushidShaheed = new Wrs("Rushid Shaheed", "New Orleans Saints", 0, 81, 66, 9, 61, 72, 17, 67, false, 0, 0, 70);
        Wrs treQuanSmith = new Wrs("Tre'Quan Smith", "New Orleans Saints", 0, 89, 90, 11, 84, 92, 17, 67, false, 0, 0, 71);
        Wrs parrisCampbell = new Wrs("Parris Campbell", "New York Giants", 0, 40, 50, 9, 59, 48, 12, 69, false, 0, 0, 72);
        Wrs dariusSlayton = new Wrs("Darius Slayton", "New York Giants", 0, 56, 40, 10, 49, 52, 12, 72, false, 0, 0, 73);
        Wrs isaiahHodgins = new Wrs("Isaiah Hodgins", "New York Giants", 0, 71, 81, 8, 39, 73, 12, 68, false, 0, 0, 74);
        Wrs garretWilson = new Wrs("Garret Wilson", "New York Jets", 0, 16, 14, 8, 30, 21, 15, 8, false, 0, 0, 75);
        Wrs allenLazard = new Wrs("Allen Lazard", "New York Jets", 0, 41, 36, 6, 33, 35, 15, 46, false, 0, 0, 76);
        Wrs mecoleHardmanJr = new Wrs("Mecole Hardman Jr.", "New York Jets", 0, 84, 88, 6, 35, 76, 15, 75, false, 0, 0, 77);
        Wrs ajBrown = new Wrs("A. J. Brown", "Philadelphia Eagles", 0, 11, 4, 2, 8, 6, 1, 6, false, 0, 0, 78);
        Wrs deVontaSmith = new Wrs("DeVonta Smith", "Philadelphia Eagles", 0, 9, 8, 5, 13, 9, 1, 13, false, 0, 0, 79);
        Wrs olamideZaccheaus = new Wrs("Olamide Zaccheaus", "Philadelphia Eagles", 0, 67, 59, 9, 80, 65, 1, 90, false, 0, 0, 80);
        Wrs georgePickens = new Wrs("George Pickens", "Pittsburgh Steelers", 0, 49, 35, 7, 51, 40, 24, 39, false, 0, 0, 81);
        Wrs dionteaJohnson = new Wrs("Diontae Johnson", "Pittsburgh Steelers", 0, 13, 27, 12, 42, 28, 24, 29, false, 0, 0, 82);
        Wrs allenRobinsonII = new Wrs("Allen Robinson II", "Pittsburgh Steelers", 0, 76, 85, 9, 60, 80, 24, 80, false, 0, 0, 83);
        Wrs tylerLockett = new Wrs("Tyler Lockett", "Seattle Seahawks", 0, 15, 19, 3, 17, 13, 6, 33, false, 0, 0, 84);
        Wrs dkMetcalf = new Wrs("Dk Metcalf", "Seattle Seahawks", 0, 10, 15, 6, 25, 16, 6, 15, false, 0, 0, 85);
        Wrs deeEskridge = new Wrs("Dee Eskridge", "Seattle Seahawks", 0, 95, 96, 12, 96, 96, 6, 104, false, 0, 0, 86);
        Wrs brandonAiyuk = new Wrs("Brandon Aiyuk", "San Francisco 49ers", 0, 19, 21, 4, 24, 15, 21, 28, false, 0, 0, 87);
        Wrs deeboSamuel = new Wrs("Deebo Samuel", "San Francisco 49ers", 0, 47, 49, 7, 27, 38, 21, 18, false, 0, 0, 88);
        Wrs jauanJennings = new Wrs("Jauan Jennings", "San Francisco 49ers", 0, 75, 77, 11, 87, 84, 21, 88, false, 0, 0, 89);
        Wrs mikeEvans = new Wrs("Mike Evans", "Tampa Bay Buccaneers", 0, 22, 12, 6, 14, 17, 28, 35, false, 0, 0, 90);
        Wrs chrisGodwin = new Wrs("Chris Godwin", "Tampa Bay Buccaneers", 0, 6, 20, 9, 16, 19, 28, 30, false, 0, 0, 91);
        Wrs russelGage = new Wrs("Russel Gage", "Tampa Bay Buccaneers", 0, 51, 72, 7, 53, 53, 28, 87, false, 0, 0, 92);
        Wrs deAndreHopkins = new Wrs("DeAndre Hopkins", "Tennessee Titans", 0, 36, 41, 9, 9, 47, 26, 23, false, 0, 0, 93);
        Wrs chrisMoore = new Wrs("Chris Moore", "Tennessee Titans", 0, 53, 56, 10, 67, 60, 26, 105, false, 0, 0, 94);
        Wrs nickWestbrookIkhine = new Wrs("Nick Westbrook-Ikhine", "Tennessee Titans", 0, 85, 80, 9, 86, 83, 26, 92, false, 0, 0, 95);
        Wrs terryMcLaurin = new Wrs("Terry McLaurin", "Washington Commanders", 0, 21, 9, 7, 22, 14, 25, 24, false, 0, 0, 96);
        Wrs curtisSamuel = new Wrs("Curtis Samuel", "Washington Commanders", 0, 34, 47, 7, 47, 33, 25, 65, false, 0, 0, 97);
        Wrs juhanDotson = new Wrs("Juhan Dotson", "Washington Commanders", 0, 73, 62, 5, 41, 51, 25, 37, false, 0, 0, 98);
        Wrs jaxonSmithNjigba = new Wrs("Jaxon Smith-Njigba", "Seattle Seahawks", 0, 97, 97, 12, 97, 97, 6, 32, true, 0, 0, 99);
        Wrs quentinJohnston = new Wrs("Quentin Johnston", "Los Angeles Chargers", 0, 97, 97, 12, 97, 97, 5, 40, true, 0, 0, 100);
        Wrs zayFlowers = new Wrs("Zay Flowers", "Baltimore Ravens", 0, 97, 97, 12, 97, 97, 11, 43, true, 0, 0, 101);
        Wrs jordanAddison = new Wrs("Jordan Addison", "Minnesota Vikings", 0, 97, 97, 12, 97, 97, 8, 36, true, 0, 0, 102);
        Wrs jonathanMingo = new Wrs("Jonathan Mingo", "Carolina Panthers", 0, 97, 97, 12, 97, 97, 31, 57, true, 0, 0, 103);
        Wrs jaydenReed = new Wrs("Jayden Reed", "Green Bay Packers", 0, 97, 97, 12, 97, 97, 27, 70, true, 0, 0, 104);
        Wrs rasheeRice = new Wrs("Rashee Rice", "Kansas City Chiefs", 0, 97, 97, 12, 97, 97, 2, 60, true, 0, 0, 105);
        Wrs marvinMims = new Wrs("Marvin Mims", "Denver Broncos", 0, 97, 97, 12, 97, 97, 16, 64, true, 0, 0, 106);

        wrList.add(marquiseBrown);
        wrList.add(gregDortch);
        wrList.add(ronadaleMoore);
        wrList.add(drakeLondon);
        wrList.add(mackHollins);
        wrList.add(khaDarelHodge);
        wrList.add(devinDuvernay);
        wrList.add(nelsonAgholor);
        wrList.add(rashodBateman);
        wrList.add(odellBackhamJr);
        wrList.add(stefonDiggs);
        wrList.add(gabeDavis);
        wrList.add(trentSherfield);
        wrList.add(adamThielen);
        wrList.add(djCarkJr);
        wrList.add(terraceMarshallJr);
        wrList.add(djMoore);
        wrList.add(darnellMooney);
        wrList.add(chaseClaypool);
        wrList.add(jaMarrChase);
        wrList.add(teeHiggins);
        wrList.add(tylerBoyd);
        wrList.add(amariCooper);
        wrList.add(denovanPeoplesJones);
        wrList.add(marquiseGoodwin);
        wrList.add(ceeDeeLamb);
        wrList.add(brandinCooks);
        wrList.add(michaelGallup);
        wrList.add(jerryJeudy);
        wrList.add(courtlandSutton);
        wrList.add(kendallHinton);
        wrList.add(amonRaStBrown);
        wrList.add(marvinJonesJr);
        wrList.add(kalifRaymond);
        wrList.add(christianWatson);
        wrList.add(romeoDoubs);
        wrList.add(samoriToure);
        wrList.add(noahBrown);
        wrList.add(robertWoods);
        wrList.add(nicoCollins);
        wrList.add(michaelPittmanJr);
        wrList.add(isaiahMcKenzie);
        wrList.add(alecPierce);
        wrList.add(christianKirk);
        wrList.add(zayJones);
        wrList.add(jamalAgrew);
        wrList.add(calvinRidley);
        wrList.add(marquezValdesScantling);
        wrList.add(richieJamesJr);
        wrList.add(justinWatson);
        wrList.add(mikeWilliams);
        wrList.add(keenanAllen);
        wrList.add(joshuaPalmer);
        wrList.add(cooperKupp);
        wrList.add(demarcusRobinson);
        wrList.add(vanJefferson);
        wrList.add(devanteAdams);
        wrList.add(jakobiMeyers);
        wrList.add(deAndreCarter);
        wrList.add(tyreekHill);
        wrList.add(jaylenWaddle);
        wrList.add(braxtonBerrios);
        wrList.add(justinJefferson);
        wrList.add(kJOsborn);
        wrList.add(jalenNailor);
        wrList.add(juJuSmithSchuster);
        wrList.add(deVanteParker);
        wrList.add(kendrickBourne);
        wrList.add(chrisOlave);
        wrList.add(rushidShaheed);
        wrList.add(treQuanSmith);
        wrList.add(parrisCampbell);
        wrList.add(dariusSlayton);
        wrList.add(isaiahHodgins);
        wrList.add(garretWilson);
        wrList.add(allenLazard);
        wrList.add(mecoleHardmanJr);
        wrList.add(ajBrown);
        wrList.add(deVontaSmith);
        wrList.add(olamideZaccheaus);
        wrList.add(georgePickens);
        wrList.add(dionteaJohnson);
        wrList.add(allenRobinsonII);
        wrList.add(tylerLockett);
        wrList.add(dkMetcalf);
        wrList.add(deeEskridge);
        wrList.add(brandonAiyuk);
        wrList.add(deeboSamuel);
        wrList.add(jauanJennings);
        wrList.add(mikeEvans);
        wrList.add(chrisGodwin);
        wrList.add(russelGage);
        wrList.add(deAndreHopkins);
        wrList.add(chrisMoore);
        wrList.add(nickWestbrookIkhine);
        wrList.add(terryMcLaurin);
        wrList.add(curtisSamuel);
        wrList.add(juhanDotson);
        wrList.add(jaxonSmithNjigba);
        wrList.add(quentinJohnston);
        wrList.add(zayFlowers);
        wrList.add(jordanAddison);
        wrList.add(jonathanMingo);
        wrList.add(jaydenReed);
        wrList.add(rasheeRice);
        wrList.add(marvinMims);
    }
}
