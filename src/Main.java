import java.util.ArrayList;

public class Main {
    private static final double tierS = 5.5;
    private static final double tierA = 3.5;
    private static final double tierB = 2.5;
    private static final double tierC = 2;
    private static final double tierD = 1.5;
    private static final double tierE = 1.0;

    private static ArrayList<Qbs> qbList = new ArrayList<Qbs>();
    private static ArrayList<Wrs> wrList = new ArrayList<Wrs>();
    private static ArrayList<Tes> teList = new ArrayList<Tes>();
    private static ArrayList<Rbs> rbsList = new ArrayList<Rbs>();
    private static ArrayList<Kicker> kickerList = new ArrayList<Kicker>();
    private static ArrayList<Defense> defenseList = new ArrayList<Defense>();

    public static void main(String[] args) {
        addQbToList();
        qbList = Qbs.werteGewichten(qbList, tierS, tierA, tierB, tierC, tierD, tierE);
        qbList = Qbs.valueBerechnen(qbList);
        qbList = Qbs.sortAfterValue(qbList, 3);
        addDefenseToList();
        defenseList = Defense.werteGewichten(defenseList, tierS, tierA, tierB, tierC, tierD, tierE);
        defenseList = Defense.wertBerechnen(defenseList);
        defenseList = Defense.sortAfterValue(defenseList);
        Qbs.printQBs(qbList);
        System.out.println();
        Defense.printDefense(defenseList);
    }


    private static void addQbToList() {
        Qbs kylerMurray = new Qbs("Kyler Murray", "Arizona Cardinals", 0, 19, 6, 8, 18, 22, 17, 11, 28,  false, 0);
        Qbs desmondRidder = new Qbs("Desmond Ridder", "Atlanta Falcons", 0, 26, 24, 28, 27, 30, 25, 20, 31, false, 0);
        Qbs lamarJackson = new Qbs("Lamar Jackson", "Baltimore Ravens", 0, 21, 3, 6, 13, 4, 15, 23,20, false, 0);
        Qbs joshAllen = new Qbs("Josh Allen", "Buffalo Bills", 0, 6, 2, 3, 2, 3, 4, 21, 11,false, 0);
        Qbs bryceYoung = new Qbs("Bryce Young", "Carolina Panthers", 0,0,0, 0,0, 24,0,0,23, true, 0);
        Qbs justinFields = new Qbs("Justin Fields", "Chicago Bears", 0,20, 1, 5, 6, 6, 13, 26, 15, false, 0);
        Qbs joeBurrow = new Qbs("Joe Burrow", "Cincinnati Bengals", 0, 4, 11, 4, 4, 5, 3, 2, 1, false, 0);
        Qbs deshaunWatson = new Qbs("Deshaun Watson", "Cleveland Browns", 0, 25, 14, 21, 26, 9, 24, 28, 17, false, 0);
        Qbs dakPrescott = new Qbs("Dak Prescott", "Dallas Cowboys", 0, 15, 13, 15, 17, 11, 19, 13, 9, false, 0);
        Qbs jaredGoff = new Qbs("Jared Goff", "Detroit Lions", 0, 5, 22, 16, 10, 17, 6, 17, 22, false, 0);
        Qbs russelWilson = new Qbs("Russel Wilson", "Denver Broncos", 0, 11, 10, 18, 15, 18, 21, 25, 13, false, 0);
        Qbs cjStroud = new Qbs("C. J. Stroud", "Houston Texans", 0, 0, 0, 0, 0, 25, 0, 0, 30, true, 0);
        Qbs jordanLove = new Qbs("Jordan Love", "Green Bay Packers", 0, 27, 29, 29, 29, 20, 28, 9, 29, false, 0);
        Qbs matthewStafford = new Qbs("Matthew Stafford", "Los Angeles Rams", 0, 23, 28, 25, 24, 21, 23, 4, 18, false, 0);
        Qbs anthonyRichardson = new Qbs("Anthony Richardson", "Indianapolis Colts", 0, 0, 0, 0, 0, 14, 0, 0, 25, true, 0);
        Qbs trevorLawrence = new Qbs("Trevor Lawrence", "Jacksonville Jaguars", 0, 8, 9, 14, 8, 8, 5, 12, 12, false, 0);
        Qbs kirkCousins = new Qbs("Kirk Cousins", "Minnesota Vikings", 0, 3, 19, 13, 7, 12, 8, 14, 8, false, 0);
        Qbs darekCarr = new Qbs("Darek Carr", "New Orleans Saints", 0, 12, 16, 19, 16, 19, 16, 24, 19, false, 0);
        Qbs patrickMahomes = new Qbs("Patrick Mahomes", "Kansas City Chiefs", 0, 1, 8, 2, 1, 1, 1, 7, 26, false, 0);
        Qbs jimmyGaroppolo = new Qbs("Jimmy Garoppolo", "Las Vegas Raiders", 0, 17, 26, 20, 20, 29, 14, 6, 4, false, 0);
        Qbs danielJones = new Qbs("Daniel Jones", "New York Giants", 0, 13, 5, 10, 9, 13, 10, 5, 24, false, 0);
        Qbs jalenHurts = new Qbs("Jalen Hurts", "Philadelphia Eagles", 0, 8, 4, 1, 3, 2, 2, 10, 2, false, 0);
        Qbs justinHerbert = new Qbs("Justin Herbert", "Los Angeles Chargers", 0, 2, 15, 17, 11, 7, 12, 3, 5, false, 0);
        Qbs tuaTagovailoa = new Qbs("Tua Tagovailoa", "Miami Dolphins", 0, 10, 23, 11, 14, 10, 9, 18, 3, false, 0);
        Qbs brockPurdy = new Qbs("Brock Purdy", "San Francisco 49ers", 0, 24, 27, 12, 25, 27, 18, 8, 10, false, 0);
        Qbs genoSmith = new Qbs("Geno Smith", "Seattle Seahawks", 0, 7, 7, 9, 5, 15, 7, 1, 7, false, 0);
        Qbs macJones = new Qbs("Mac Jones", "New England Patriots", 0, 14, 17, 24, 19, 26, 22, 15, 27, false, 0);
        Qbs aaronRodgers = new Qbs("Aaron Rodgers", "New York Jets", 0, 9, 20, 22, 12, 16, 11, 19, 14, false, 0);
        Qbs bakerMayfield = new Qbs("Baker Mayfield", "Tampa Bay Buccaneers", 0, 22, 21, 27, 23, 32, 23, 27, 6, false, 0);
        Qbs samHowell = new Qbs("Sam Howell", "Washington Commanders", 0, 28, 25, 7, 28, 28, 27, 29, 26, false, 0);
        Qbs kennyPickett = new Qbs("Kenny Pickett", "Pittsburgh Steelers", 0, 18, 12, 26, 22, 23, 26, 22, 21, false, 0);
        Qbs ryanTennehill = new Qbs("Ryan Tennehill", "Tennessee Titans", 0, 16, 18, 23, 21, 31, 20, 16, 32, false, 0);

        qbList.add(kylerMurray);
        qbList.add(desmondRidder);
        qbList.add(lamarJackson);
        qbList.add(joshAllen);
        qbList.add(bryceYoung);
        qbList.add(justinFields);
        qbList.add(joeBurrow);
        qbList.add(deshaunWatson);
        qbList.add(dakPrescott);
        qbList.add(jaredGoff);
        qbList.add(russelWilson);
        qbList.add(cjStroud);
        qbList.add(jordanLove);
        qbList.add(matthewStafford);
        qbList.add(anthonyRichardson);
        qbList.add(trevorLawrence);
        qbList.add(kirkCousins);
        qbList.add(darekCarr);
        qbList.add(patrickMahomes);
        qbList.add(jimmyGaroppolo);
        qbList.add(danielJones);
        qbList.add(jalenHurts);
        qbList.add(justinHerbert);
        qbList.add(tuaTagovailoa);
        qbList.add(brockPurdy);
        qbList.add(genoSmith);
        qbList.add(macJones);
        qbList.add(aaronRodgers);
        qbList.add(bakerMayfield);
        qbList.add(samHowell);
        qbList.add(kennyPickett);
        qbList.add(ryanTennehill);
    }

    private static void addDefenseToList() {
        Defense sanFrancisco49ers = new Defense("San Francisco 49ers", 0, 6, 1, 7, 3, 4, 1, 0);
        Defense chicagoBears = new Defense("Chicago Bears", 0, 16, 6, 14, 3, 25, 31, 0);
        Defense cincinnatiBengals = new Defense("Cincinnati Bengals", 0, 15, 7, 10, 3, 20, 14, 0);
        Defense buffaloBills = new Defense("Buffalo Bills", 0, 9, 3, 13, 1, 16, 5, 0);
        Defense denverBroncos = new Defense("Denver Broncos", 0, 12, 5, 19, 2, 24, 12, 0);
        Defense clevelandBrowns = new Defense("Cleveland Browns", 0, 14, 9, 17, 3, 6, 13, 0);
        Defense tampaBayBuccaneers = new Defense("Tampa Bay Buccaneers", 0, 5, 10, 12, 3, 22, 18, 0);
        Defense arizonaCardinals = new Defense("Arizona Cardinals", 0, 12, 9, 15, 3, 2, 32, 0);
        Defense losAngelesChargers = new Defense("Los Angeles Chargers", 0, 9, 6, 9, 2, 19, 16, 0);
        Defense kansasCityChiefs = new Defense("Kansas City Chiefs", 0, 2, 9, 16, 2, 11, 17, 0);
        Defense indianapolisColts = new Defense("Indianapolis Colts", 0, 6, 10, 5, 3, 12, 29, 0);
        Defense washingtonCommanders = new Defense("Washington Commanders", 0, 7, 11, 18, 2, 8, 11, 0);
        Defense dallasCowboys = new Defense("Dallas Cowboys", 0, 3, 4, 1, 3, 5, 3, 0);
        Defense miamiDolphins = new Defense("Miami Dolphins", 0, 9, 12, 23, 2, 13, 10, 0);
        Defense philadelphiaEagles = new Defense("Philadelphia Eagles", 0, 1, 3, 8, 3, 10, 2, 0);
        Defense atlantaFalcons = new Defense("Atlanta Falcons", 0, 17, 10, 27, 2, 22, 22, 0);
        Defense newYorkGiants = new Defense("New York Giants", 0, 8, 14, 2, 3, 14, 25, 0);
        Defense jacksonvilleJaguars = new Defense("Jacksonville Jaguars", 0, 13, 6, 3, 3, 3, 24, 0);
        Defense newYorkJets = new Defense("New York Jets", 0, 5, 8, 28, 1, 26, 4, 0);
        Defense detroitLions = new Defense("Detroit Lions", 0, 10, 8, 10, 2, 21, 23, 0);
        Defense greenBayPackers = new Defense("Green Bay Packers", 0, 14, 3, 21, 2, 10, 15, 0);
        Defense carolinaPanthers = new Defense("Carolina Panthers", 0, 13, 10, 26, 3, 7, 19, 0);
        Defense newEnglandPatriots = new Defense("New England Patriots", 0, 3, 2, 6, 3, 1, 8, 0);
        Defense lasVegasRaiders = new Defense("Las Vegas Raiders", 0, 16, 14, 22, 3, 9, 26, 0);
        Defense losAngelesRams = new Defense("Los Angeles Rams", 0, 11, 4, 25, 3, 17, 27, 0);
        Defense baltimoreRavens = new Defense("Baltimore Ravens", 0, 4, 6, 11, 3, 25, 9, 0);
        Defense newOrleansSaints = new Defense("New Orleans Saints", 0, 4, 13, 20, 3, 23, 7, 0);
        Defense seattleSeahawks = new Defense("Seattle Seahawks", 0, 5, 6, 4, 3, 19, 21, 0);
        Defense pittsburghSteelers = new Defense("Pittsburgh Steelers", 0, 9, 1, 29, 3, 15, 6, 0);
        Defense houstonTexans = new Defense("Houston Texans", 0, 10, 4, 10, 2, 17, 28, 0);
        Defense tennesseeTitans = new Defense("Tennessee Titans", 0, 10, 6, 24, 3, 19, 20, 0);
        Defense minnesotaVikings = new Defense("Minnesota Vikings", 0, 11, 5, 13, 3, 18, 30, 0);

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
}