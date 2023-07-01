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
    }


    private static void addQbToList() {
        Qbs kylerMurray = new Qbs("Kyler Murray", 0, 19, 6, 8, 18, 22, 17, 11, 28, 0, false, 0);
        Qbs desmondRidder = new Qbs("Desmond Ridder", 0, 26, 24, 28, 27, 30, 25, 20, 31, 0, false, 0);
        Qbs lamarJackson = new Qbs("Lamar Jackson", 0, 21, 3, 6, 13, 4, 15, 23,20, 0, false, 0);
        Qbs joshAllen = new Qbs("Josh Allen", 0, 6, 2, 3, 2, 3, 4, 21, 11, 0,false, 0);
        Qbs bryceYoung = new Qbs("Bryce Young", 0,0,0, 0,0, 24,0,0,23, 0, true, 0);
        Qbs justinFields = new Qbs("Justin Fields", 0,20, 1, 5, 6, 6, 13, 26, 15, 0, false, 0);
        Qbs joeBurrow = new Qbs("Joe Burrow", 0, 4, 11, 4, 4, 5, 3, 2, 1, 0, false, 0);
        Qbs deshaunWatson = new Qbs("Deshaun Watson", 0, 25, 14, 21, 26, 9, 24, 28, 17, 0, false, 0);
        Qbs dakPrescott = new Qbs("Dak Prescott", 0, 15, 13, 15, 17, 11, 19, 13, 9, 0, false, 0);
        Qbs jaredGoff = new Qbs("Jared Goff", 0, 5, 22, 16, 10, 17, 6, 17, 22, 0, false, 0);
        Qbs russelWilson = new Qbs("Russel Wilson", 0, 11, 10, 18, 15, 18, 21, 25, 13, 0, false, 0);
        Qbs cjStroud = new Qbs("C. J. Stroud", 0, 0, 0, 0, 0, 25, 0, 0, 30, 0, true, 0);
        Qbs jordanLove = new Qbs("Jordan Love", 0, 27, 29, 29, 29, 20, 28, 9, 29, 0, false, 0);
        Qbs matthewStafford = new Qbs("Matthew Stafford", 0, 23, 28, 25, 24, 21, 23, 4, 18, 0, false, 0);
        Qbs anthonyRichardson = new Qbs("Anthony Richardson", 0, 0, 0, 0, 0, 14, 0, 0, 25, 0, true, 0);
        Qbs trevorLawrence = new Qbs("Trevor Lawrence", 0, 8, 9, 14, 8, 8, 5, 12, 12, 0, false, 0);
        Qbs kirkCousins = new Qbs("Kirk Cousins", 0, 3, 19, 13, 7, 12, 8, 14, 8, 0, false, 0);
        Qbs darekCarr = new Qbs("Darek Carr", 0, 12, 16, 19, 16, 19, 16, 24, 19, 0, false, 0);
        Qbs patrickMahomes = new Qbs("Patrick Mahomes", 0, 1, 8, 2, 1, 1, 1, 7, 26, 0, false, 0);
        Qbs jimmyGaroppolo = new Qbs("Jimmy Garoppolo", 0, 17, 26, 20, 20, 29, 14, 6, 4, 0, false, 0);
        Qbs danielJones = new Qbs("Daniel Jones", 0, 13, 5, 10, 9, 13, 10, 5, 24, 0, false, 0);
        Qbs jalenHurts = new Qbs("Jalen Hurts", 0, 8, 4, 1, 3, 2, 2, 10, 2, 0, false, 0);
        Qbs justinHerbert = new Qbs("Justin Herbert", 0, 2, 15, 17, 11, 7, 12, 3, 5, 0, false, 0);
        Qbs tuaTagovailoa = new Qbs("Tua Tagovailoa", 0, 10, 23, 11, 14, 10, 9, 18, 3, 0, false, 0);
        Qbs brockPurdy = new Qbs("Brock Purdy", 0, 24, 27, 12, 25, 27, 18, 8, 10, 0, false, 0);
        Qbs genoSmith = new Qbs("Geno Smith", 0, 7, 7, 9, 5, 15, 7, 1, 7, 0, false, 0);
        Qbs macJones = new Qbs("Mac Jones", 0, 14, 17, 24, 19, 26, 22, 15, 27, 0, false, 0);
        Qbs aaronRodgers = new Qbs("Aaron Rodgers", 0, 9, 20, 22, 12, 16, 11, 19, 14, 0, false, 0);
        Qbs bakerMayfield = new Qbs("Baker Mayfield", 0, 22, 21, 27, 23, 32, 23, 27, 6, 0, false, 0);
        Qbs samHowell = new Qbs("Sam Howell", 0, 28, 25, 7, 28, 28, 27, 29, 26, 0, false, 0);
        Qbs kennyPickett = new Qbs("Kenny Pickett", 0, 18, 12, 26, 22, 23, 26, 22, 21, 0, false, 0);
        Qbs ryanTennehill = new Qbs("Ryan Tennehill", 0, 16, 18, 23, 21, 31, 20, 16, 32, 0, false, 0);

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
        Defense sanFrancisco49ers = new Defense("San Francisco 49ers", 0, 0, 0, 0, 0, 0, 0, 0);
    }
}