import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import java.util.ArrayList;

public class ManageExcelSheets {

    private static final String[] draftOrderPositions = {"Position", "Wide Receiver", "Wide Receiver", "Wide Receiver",
            "Running Back", "Tight End", "Flex Spot", "Quarterback", "Wide Receiver", "Kicker",
            "Defense", "Wide Receiver", "Running Back", "Tight End", "Quarterback",
            "Wide Receiver", "Kicker"};
    private static final String[] captions = {"Rank", "Value", "Name", "Team", "Draft Order", "Passing Yards",
            "Cmp Pct", "TD", "FFPpG", "Total FFP", "WR-Room", "Rookie", "Bye Week", "Receptions", "Receiving Yards",
            "QB", "Rushing Attempts", "Rushing Yards", "O-Line", "Attempts", "Made", "Pct", "XP", "50+", "Order",
            "Sacks", "Safeties", "Fumble Recoveries", "Ints", "Fumbles"};
    private static final int[] qbCaption = {0, 1, 2, 3, 4, 5, 17, 29, 6, 7, 28, 8, 9, 10, 11, 12};
    private static final int[] wrTeCaption = {0, 1, 2, 3, 4, 13, 14, 7, 8, 9, 15, 11, 12};
    private static final int[] rbCaption = {0, 1, 2, 3, 4, 13, 14, 17, 16, 7, 18, 8, 9, 11, 12};
    private static final int[] kickerCaption = {0, 1, 2, 3, 4, 19, 20, 21, 22, 23, 8, 9, 12};
    private static final int[] defenseCaption = {0, 1, 2, 24, 25, 26, 27, 28, 7, 8, 9, 12};

    /**
     * Erstellen der Excel-Datei
     *
     * @param amountDraftRounds
     * @param qbList
     * @param wrList
     * @param teList
     * @param rbsList
     * @param kickerList
     * @param defenseList
     */
    public static void createExcel(int amountDraftRounds, ArrayList<Qbs> qbList, ArrayList<Wrs> wrList,
                                   ArrayList<Tes> teList, ArrayList<Rbs> rbsList,
                                   ArrayList<Kicker> kickerList, ArrayList<Defense> defenseList) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            CellStyle cellStyle = wb.createCellStyle();
            CellStyle headerStyle = wb.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            HSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Arial");
            font.setBold(true);
            headerStyle.setFont(font);
            HSSFSheet draftPlan = wb.createSheet("Draftplan");
            HSSFSheet quarterbackSheet = wb.createSheet("Quarterback");
            HSSFSheet wideReceiverSheet = wb.createSheet("Wide Receiver");
            HSSFSheet tightEndSheet = wb.createSheet("Tight End");
            HSSFSheet runningBackSheet = wb.createSheet("Running Back");
            HSSFSheet kickerSheet = wb.createSheet("Kicker");
            HSSFSheet defenseSheet = wb.createSheet("Defense");



            positionDraftorder(draftPlan, amountDraftRounds, headerStyle, cellStyle);

            createHeaderOnSheet(qbCaption, quarterbackSheet);
            createHeaderOnSheet(wrTeCaption, wideReceiverSheet);
            createHeaderOnSheet(wrTeCaption, tightEndSheet);
            createHeaderOnSheet(rbCaption, runningBackSheet);
            createHeaderOnSheet(kickerCaption, kickerSheet);
            createHeaderOnSheet(defenseCaption, defenseSheet);

            giveQBsValue(qbList, quarterbackSheet);
            giveReceiverValue(wrList, teList, wideReceiverSheet, tightEndSheet);
            giveRBsValue(rbsList, runningBackSheet);
            giveKickerValue(kickerList, kickerSheet);
            giveDefenseValue(defenseList, defenseSheet);

            setupExcelCells(qbList, 16, quarterbackSheet, cellStyle, headerStyle);
            setupExcelCells(wrList, 13, wideReceiverSheet, cellStyle, headerStyle);
            setupExcelCells(teList, 13, tightEndSheet, cellStyle, headerStyle);
            setupExcelCells(rbsList, 15, runningBackSheet, cellStyle, headerStyle);
            setupExcelCells(kickerList, 13, kickerSheet, cellStyle, headerStyle);
            setupExcelCells(defenseList, 12, defenseSheet, cellStyle, headerStyle);

            File file = new File("C:\\Users\\Jelle\\Desktop\\Exceldaten\\Draftrankings.xls");

            wb.write(file);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setupExcelCells(ArrayList<?> playerList, int property, HSSFSheet excelSheet,
                                        CellStyle cellStyle, CellStyle headerStyle) {
        for(int i = 0; i <= playerList.size(); i++) {
            for(int j = 0; j < property; j++) {
                excelSheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                if(i < 1) {
                    excelSheet.autoSizeColumn(j);
                    excelSheet.getRow(i).getCell(j).setCellStyle(headerStyle);
                }
            }
        }
    }

    private static void positionDraftorder(HSSFSheet draftPlan, int amountDraftRounds, CellStyle headerStyle,
                                           CellStyle cellStyle) {
        for (int i = 0; i < amountDraftRounds + 1; i++) {
            draftPlan.createRow(i);
            if (i > 0) {
                draftPlan.getRow(i).createCell(0).setCellValue(i);

            } else {
                draftPlan.getRow(i).createCell(0).setCellValue("Runde");
            }
            draftPlan.getRow(i).getCell(0).setCellStyle(headerStyle);
        }
        for(int i = 0; i < draftOrderPositions.length; i++) {
            draftPlan.getRow(i).createCell(1).setCellValue(draftOrderPositions[i]);
        }
        draftPlan.getRow(0).getCell(1).setCellStyle(headerStyle);
        for (int i = 1; i < amountDraftRounds + 1; i++) {
            draftPlan.getRow(i).getCell(1).setCellStyle(cellStyle);
        }
        draftPlan.autoSizeColumn(0);
        draftPlan.autoSizeColumn(1);
    }

    private static void createHeaderOnSheet(int[] captionPlayer, HSSFSheet excelSheet) {
        excelSheet.createRow(0);
        for (int i = 0; i < captionPlayer.length; i++) {
            excelSheet.getRow(0).createCell(i).setCellValue(captions[captionPlayer[i]]);
        }
    }

    private static void giveQBsValue(ArrayList<Qbs> qbList, HSSFSheet quarterbackSheet){
        for (int i = 0; i < qbList.size(); i++) {
            quarterbackSheet.createRow(i + 1);
            quarterbackSheet.getRow(i + 1).createCell(0).setCellValue(qbList.get(i).getPosition());
            quarterbackSheet.getRow(i + 1).createCell(1).setCellValue(qbList.get(i).getValue());
            quarterbackSheet.getRow(i + 1).createCell(2).setCellValue(qbList.get(i).getName());
            quarterbackSheet.getRow(i + 1).createCell(3).setCellValue(qbList.get(i).getTeam());
            quarterbackSheet.getRow(i + 1).createCell(4).setCellValue(qbList.get(i).getDraftOrder());
            quarterbackSheet.getRow(i + 1).createCell(5).setCellValue(qbList.get(i).getPassingYards());
            quarterbackSheet.getRow(i + 1).createCell(6).setCellValue(qbList.get(i).getRushingYards());
            quarterbackSheet.getRow(i + 1).createCell(7).setCellValue(qbList.get(i).getFumbles());
            quarterbackSheet.getRow(i + 1).createCell(8).setCellValue(qbList.get(i).getCmpPct());
            quarterbackSheet.getRow(i + 1).createCell(9).setCellValue(qbList.get(i).getTd());
            quarterbackSheet.getRow(i + 1).createCell(10).setCellValue(qbList.get(i).getInterception());
            quarterbackSheet.getRow(i + 1).createCell(11).setCellValue(qbList.get(i).getFfppg());
            quarterbackSheet.getRow(i + 1).createCell(12).setCellValue(qbList.get(i).getTotalFFP());
            quarterbackSheet.getRow(i + 1).createCell(13).setCellValue(qbList.get(i).getWrRoom());
            quarterbackSheet.getRow(i + 1).createCell(14).setCellValue(qbList.get(i).getRookie().
                    toString());
            quarterbackSheet.getRow(i + 1).createCell(15).setCellValue(qbList.get(i).getByeWeek());
        }
    }

    private static void giveReceiverValue(ArrayList<Wrs> wrList, ArrayList<Tes> teList, HSSFSheet wideReceiverSheet,
                                          HSSFSheet tightEndSheet) {
        for (int j = 0; j < wrList.size(); j++) {
            wideReceiverSheet.createRow(j + 1);
            wideReceiverSheet.getRow(j + 1).createCell(0).setCellValue(wrList.get(j).getPosition());
            wideReceiverSheet.getRow(j + 1).createCell(1).setCellValue(wrList.get(j).getValue());
            wideReceiverSheet.getRow(j + 1).createCell(2).setCellValue(wrList.get(j).getName());
            wideReceiverSheet.getRow(j + 1).createCell(3).setCellValue(wrList.get(j).getTeam());
            wideReceiverSheet.getRow(j + 1).createCell(4).setCellValue(wrList.get(j).getDraftorder());
            wideReceiverSheet.getRow(j + 1).createCell(5).setCellValue(wrList.get(j).getReceptions());
            wideReceiverSheet.getRow(j + 1).createCell(6).setCellValue(wrList.get(j).
                    getReceivingYards());
            wideReceiverSheet.getRow(j + 1).createCell(7).setCellValue(wrList.get(j).getTds());
            wideReceiverSheet.getRow(j + 1).createCell(8).setCellValue(wrList.get(j).getFfppg());
            wideReceiverSheet.getRow(j + 1).createCell(9).setCellValue(wrList.get(j).getTotalFFP());
            wideReceiverSheet.getRow(j + 1).createCell(10).setCellValue(wrList.get(j).getQbs());
            wideReceiverSheet.getRow(j + 1).createCell(11).setCellValue(wrList.get(j).getRookie().
                    toString());
            wideReceiverSheet.getRow(j + 1).createCell(12).setCellValue(wrList.get(j).getByeWeek());
            if (j < teList.size()) {
                tightEndSheet.createRow(j + 1);
                tightEndSheet.getRow(j + 1).createCell(0).setCellValue(teList.get(j).getPosition());
                tightEndSheet.getRow(j + 1).createCell(1).setCellValue(teList.get(j).getValue());
                tightEndSheet.getRow(j + 1).createCell(2).setCellValue(teList.get(j).getName());
                tightEndSheet.getRow(j + 1).createCell(3).setCellValue(teList.get(j).getTeam());
                tightEndSheet.getRow(j + 1).createCell(4).setCellValue(teList.get(j).getDraftorder());
                tightEndSheet.getRow(j + 1).createCell(5).setCellValue(teList.get(j).getReceptions());
                tightEndSheet.getRow(j + 1).createCell(6).setCellValue(teList.get(j).
                        getReceivingYards());
                tightEndSheet.getRow(j + 1).createCell(7).setCellValue(teList.get(j).getTds());
                tightEndSheet.getRow(j + 1).createCell(8).setCellValue(teList.get(j).getFfppg());
                tightEndSheet.getRow(j + 1).createCell(9).setCellValue(teList.get(j).getTotalFFP());
                tightEndSheet.getRow(j + 1).createCell(10).setCellValue(teList.get(j).getQbs());
                tightEndSheet.getRow(j + 1).createCell(11).setCellValue(teList.get(j).getRookie().
                        toString());
                tightEndSheet.getRow(j + 1).createCell(12).setCellValue(teList.get(j).getByeWeek());
            }
        }
    }

    private static void giveRBsValue(ArrayList<Rbs> rbsList, HSSFSheet runningBackSheet) {
        for (int k = 0; k < rbsList.size(); k++) {
            runningBackSheet.createRow(k + 1);
            runningBackSheet.getRow(k + 1).createCell(0).setCellValue(rbsList.get(k).getPosition());
            runningBackSheet.getRow(k + 1).createCell(1).setCellValue(rbsList.get(k).getValue());
            runningBackSheet.getRow(k + 1).createCell(2).setCellValue(rbsList.get(k).getName());
            runningBackSheet.getRow(k + 1).createCell(3).setCellValue(rbsList.get(k).getTeam());
            runningBackSheet.getRow(k + 1).createCell(4).setCellValue(rbsList.get(k).getDraftorder());
            runningBackSheet.getRow(k + 1).createCell(8).setCellValue(rbsList.get(k).getAttempts());
            runningBackSheet.getRow(k + 1).createCell(7).setCellValue(rbsList.get(k).getRushingYards());
            runningBackSheet.getRow(k + 1).createCell(5).setCellValue(rbsList.get(k).getReceptions());
            runningBackSheet.getRow(k + 1).createCell(6).setCellValue(rbsList.get(k).
                    getReceivingYards());
            runningBackSheet.getRow(k + 1).createCell(9).setCellValue(rbsList.get(k).getTds());
            runningBackSheet.getRow(k + 1).createCell(10).setCellValue(rbsList.get(k).getoLine());
            runningBackSheet.getRow(k + 1).createCell(11).setCellValue(rbsList.get(k).getFfppg());
            runningBackSheet.getRow(k + 1).createCell(12).setCellValue(rbsList.get(k).getTotalFFP());
            runningBackSheet.getRow(k + 1).createCell(13).setCellValue(rbsList.get(k).getRookie().
                    toString());
            runningBackSheet.getRow(k + 1).createCell(14).setCellValue(rbsList.get(k).getByeWeek());
        }
    }

    private static void giveKickerValue(ArrayList<Kicker> kickerList, HSSFSheet kickerSheet) {
        for (int l = 0; l < kickerList.size(); l++) {
            kickerSheet.createRow(l + 1);
            kickerSheet.getRow(l + 1).createCell(0).setCellValue(kickerList.get(l).getPosition());
            kickerSheet.getRow(l + 1).createCell(1).setCellValue(kickerList.get(l).getValue());
            kickerSheet.getRow(l + 1).createCell(2).setCellValue(kickerList.get(l).getName());
            kickerSheet.getRow(l + 1).createCell(3).setCellValue(kickerList.get(l).getTeam());
            kickerSheet.getRow(l + 1).createCell(4).setCellValue(kickerList.get(l).getDraftOrder());
            kickerSheet.getRow(l + 1).createCell(5).setCellValue(kickerList.get(l).getAttempts());
            kickerSheet.getRow(l + 1).createCell(6).setCellValue(kickerList.get(l).getMade());
            kickerSheet.getRow(l + 1).createCell(7).setCellValue(kickerList.get(l).getPct());
            kickerSheet.getRow(l + 1).createCell(8).setCellValue(kickerList.get(l).getXp());
            kickerSheet.getRow(l + 1).createCell(9).setCellValue(kickerList.get(l).getFiftyPlus());
            kickerSheet.getRow(l + 1).createCell(10).setCellValue(kickerList.get(l).getFfppg());
            kickerSheet.getRow(l + 1).createCell(11).setCellValue(kickerList.get(l).getTotalFFP());
            kickerSheet.getRow(l + 1).createCell(12).setCellValue(kickerList.get(l).getByeWeek());
        }
    }

    private static void giveDefenseValue(ArrayList<Defense> defenseList, HSSFSheet defenseSheet) {
        for (int m = 0; m < defenseList.size(); m++) {
            defenseSheet.createRow(m + 1);
            defenseSheet.getRow(m + 1).createCell(0).setCellValue(defenseList.get(m).getPosition());
            defenseSheet.getRow(m + 1).createCell(1).setCellValue(defenseList.get(m).getValue());
            defenseSheet.getRow(m + 1).createCell(2).setCellValue(defenseList.get(m).getName());
            defenseSheet.getRow(m + 1).createCell(3).setCellValue(defenseList.get(m).getOrder());
            defenseSheet.getRow(m + 1).createCell(4).setCellValue(defenseList.get(m).getSacks());
            defenseSheet.getRow(m + 1).createCell(5).setCellValue(defenseList.get(m).getSafeties());
            defenseSheet.getRow(m + 1).createCell(6).setCellValue(defenseList.get(m).
                    getFumbleRecoveries());
            defenseSheet.getRow(m + 1).createCell(7).setCellValue(defenseList.get(m).getInts());
            defenseSheet.getRow(m + 1).createCell(8).setCellValue(defenseList.get(m).getTds());
            defenseSheet.getRow(m + 1).createCell(9).setCellValue(defenseList.get(m).getFfppg());
            defenseSheet.getRow(m + 1).createCell(10).setCellValue(defenseList.get(m).getTotalffp());
            defenseSheet.getRow(m + 1).createCell(11).setCellValue(defenseList.get(m).getByeWeek());
        }
    }

    /**
     * Lesen der Quarterback-Werte aus der Excel
     *
     * @return Qb liste
     */
    public static ArrayList<?> readExcel(String position) {
        int qbColumn = 14;
        int receiverColumns = 11;
         int rbColumns = 13;
         int kickerColumns = 11;
         int defenseColumns = 10;

        ArrayList<Qbs> qbs = new ArrayList<Qbs>();
        ArrayList<Wrs> wrs = new ArrayList<Wrs>();
        ArrayList<Tes> tes = new ArrayList<Tes>();
        ArrayList<Rbs> rbs = new ArrayList<Rbs>();
        ArrayList<Kicker> kickers = new ArrayList<Kicker>();
        ArrayList<Defense> defenses = new ArrayList<Defense>();
        double[] numbersQB = new double[qbColumn];
        double[] numbersWR = new double[receiverColumns];
        double[] numbersTE = new double[receiverColumns];
        double[] numbersRB = new double[rbColumns];
        double[] numbersK = new double[kickerColumns];
        double[] numbersD = new double[defenseColumns];
        try {
            FileInputStream file = new FileInputStream(new File
                    ("C:\\Users\\Jelle\\Desktop\\Exceldaten\\Spielerbase.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(file);
            Sheet quarterbackInputSheet = wb.getSheetAt(0);
            Sheet wideReceiverInputSheet = wb.getSheetAt(1);
            Sheet tightEndInputSheet = wb.getSheetAt(2);
            Sheet runningBackInputSheet = wb.getSheetAt(3);
            Sheet kickerInputSheet = wb.getSheetAt(4);
            Sheet defenseInputSheet = wb.getSheetAt(5);

            int qbRows = quarterbackInputSheet.getPhysicalNumberOfRows() - 1;
            int wrRows = wideReceiverInputSheet.getPhysicalNumberOfRows() - 1;
            int teRows = tightEndInputSheet.getPhysicalNumberOfRows() - 1;
            int rbRows = runningBackInputSheet.getPhysicalNumberOfRows() - 1;
            int kickerRows = kickerInputSheet.getPhysicalNumberOfRows();
            int defenseRows = defenseInputSheet.getPhysicalNumberOfRows() - 1;

            if(position == "qb") {
                for (int i = 1; i < qbRows; i++) {
                    Row currentRow = quarterbackInputSheet.getRow(i);
                    for (int j = 0; j < qbColumn; j++) {
                        if (j == 1) j = 3;
                        numbersQB[j] = currentRow.getCell(j).getNumericCellValue();
                    }
                    qbs.add(new Qbs(currentRow.getCell(1).getStringCellValue(),
                            currentRow.getCell(2).getStringCellValue(), 0, numbersQB[3], numbersQB[8],
                            numbersQB[7], numbersQB[9], numbersQB[10], numbersQB[13], numbersQB[5], numbersQB[6],
                            numbersQB[4], numbersQB[11], rookie(numbersQB[12]), 0, 0, numbersQB[0]));
                }
            } else if(position == "wr" || position == "te") {
                for (int i = 1; i < wrRows; i++) {
                    Row currentRowWR = wideReceiverInputSheet.getRow(i);
                    Row currentRowTE = tightEndInputSheet.getRow(i);
                    for (int j = 0; j < receiverColumns; j++) {
                        if (j == 1) j = 3;
                        numbersWR[j] = currentRowWR.getCell(j).getNumericCellValue();
                        if (i < teRows) numbersTE[j] = currentRowTE.getCell(j).getNumericCellValue();
                    }
                    wrs.add(new Wrs(currentRowWR.getCell(1).getStringCellValue(),
                            currentRowWR.getCell(2).getStringCellValue(), 0, numbersWR[3], numbersWR[4],
                            numbersWR[5], numbersWR[6], numbersWR[7], numbersWR[8], numbersWR[10], rookie(numbersWR[9]),
                            0, 0, numbersWR[0]));
                    if (i < teRows)
                        tes.add(new Tes(currentRowTE.getCell(1).getStringCellValue(),
                                currentRowTE.getCell(2).getStringCellValue(), 0, numbersTE[3], numbersTE[4],
                                numbersTE[5], numbersTE[6], numbersTE[7], numbersTE[8], numbersTE[10],
                                rookie(numbersTE[9]), 0, 0, numbersTE[0]));
                }
            } else if(position == "rb") {
                for (int i = 1; i < rbRows; i++) {
                    Row currentRowRB = runningBackInputSheet.getRow(i);
                    for (int j = 0; j < rbColumns; j++) {
                        if (j == 1) j = 3;
                        numbersRB[j] = currentRowRB.getCell(j).getNumericCellValue();
                    }
                    rbs.add(new Rbs(currentRowRB.getCell(1).getStringCellValue(),
                            currentRowRB.getCell(2).getStringCellValue(), 0, numbersRB[5], numbersRB[6],
                            numbersRB[4], numbersRB[3], numbersRB[7], numbersRB[8], numbersRB[9], numbersRB[10],
                            numbersRB[12], rookie(numbersRB[11]), 0, 0, numbersRB[0]));
                }
            } else if(position == "kicker") {
                for (int i = 1; i < kickerRows; i++) {
                    Row currentRowK = kickerInputSheet.getRow(i);
                    for (int j = 0; j < kickerColumns; j++) {
                        if (j == 1) j = 3;
                        numbersK[j] = currentRowK.getCell(j).getNumericCellValue();
                    }
                    kickers.add(new Kicker(currentRowK.getCell(1).getStringCellValue(),
                            currentRowK.getCell(2).getStringCellValue(), 0, numbersK[3], numbersK[4],
                            numbersK[7], numbersK[5], numbersK[6], numbersK[8], numbersK[9], numbersK[10], 0,
                            0, numbersK[0]));
                }
            } else if(position == "defense") {
                for (int i = 1; i < defenseRows; i++) {
                    Row currentRowD = defenseInputSheet.getRow(i);
                    for (int j = 0; j < defenseColumns; j++) {
                        if (j == 1) j = 2;
                        numbersD[j] = currentRowD.getCell(j).getNumericCellValue();
                    }
                    defenses.add(new Defense(currentRowD.getCell(1).getStringCellValue(), 0, numbersD[2],
                            numbersD[5], numbersD[4], numbersD[3], numbersD[6], numbersD[7], 0, 0,
                            numbersD[0], numbersD[8], numbersD[9]));
                }
            }
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }
        switch (position) {
            case "qb": return qbs;
            case "wr": return wrs;
            case "te": return tes;
            case "rb": return rbs;
            case "kicker": return kickers;
        }
        return defenses;
    }

    /**
     * Reading the amount of
     *
     * @param position
     * @return
     */
    public static int amountRookies(int position) {
        try {
            FileInputStream file = new FileInputStream(
                    new File("C:\\Users\\Jelle\\OneDrive\\Desktop\\Exceldaten\\Spielerbase.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(file);
            Sheet quarterbackInputSheet = wb.getSheetAt(0);
            Sheet wideReceiverInputSheet = wb.getSheetAt(1);
            Sheet tightEndInputSheet = wb.getSheetAt(2);
            Sheet runningBackInputSheet = wb.getSheetAt(3);

            switch (position) {
                // Case QB
                case 0:
                    return (int) quarterbackInputSheet.getRow(
                            quarterbackInputSheet.getPhysicalNumberOfRows() - 1).getCell(12).getNumericCellValue();
                // Case WR
                case 1:
                    return (int) wideReceiverInputSheet.getRow(
                            wideReceiverInputSheet.getPhysicalNumberOfRows() - 1).getCell(9).getNumericCellValue();
                // Case TE
                case 2:
                    return (int) tightEndInputSheet.getRow(
                            tightEndInputSheet.getPhysicalNumberOfRows() - 1).getCell(9).getNumericCellValue();
            }
            // Case RB
            return (int) runningBackInputSheet.getRow(
                    runningBackInputSheet.getPhysicalNumberOfRows() - 1).getCell(11).getNumericCellValue();
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }
        return 0;
    }

    /**
     * Convert Excel-Input into Boolean
     *
     * @param isRookie
     * @return If Player is a rookie
     */
    private static boolean rookie(double isRookie) {
        if(isRookie == 1) return true;
        return false;
    }
}
