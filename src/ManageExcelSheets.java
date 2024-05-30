import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ManageExcelSheets {

    private static final String[] draftOrderPositions = {"Position", "Wide Receiver", "Wide Receiver", "Wide Receiver", "Running Back", "Tight End", "Flex Spot", "Quarterback", "Wide Receiver", "Kicker", "Defense", "Wide Receiver", "Running Back", "Tight End", "Quarterback", "Wide Receiver", "Kicker"};
    private static final String[] captions = {"Rank", "Value", "Name", "Team", "Draft Order", "Passing Yards", "Cmp Pct", "TD", "FFPpG", "Total FFP",
            "WR-Room", "Rookie", "Bye Week", "Receptions", "Receiving Yards", "QB", "Rushing Attempts", "Rushing Yards", "O-Line", "Attempts",
            "Made", "Pct", "XP", "50+", "Order", "Sacks", "Safeties", "Fumble Recoveries", "Ints", "Fumbles"};
    private static final int[] qbCaption = {0, 1, 2, 3, 4, 5, 17, 29, 6, 7, 28, 8, 9, 10, 11, 12};
    private static final int[] wrTeCaption = {0, 1, 2, 3, 4, 13, 14, 7, 8, 9, 15, 11, 12};
    private static final int[] rbCaption = {0, 1, 2, 3, 4, 13, 14, 17, 16, 7, 18, 8, 9, 11, 12};
    private static final int[] kickerCaption = {0, 1, 2, 3, 4, 20, 21, 22, 23, 24, 8, 9, 12};
    private static final int[] defenseCaption = {0, 1, 2, 25, 26, 27, 28, 29, 15, 12};

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
    public static void createExcel(int amountDraftRounds, ArrayList<Qbs> qbList, ArrayList<Wrs> wrList, ArrayList<Tes> teList, ArrayList<Rbs> rbsList, ArrayList<Kicker> kickerList, ArrayList<Defense> defenseList) {
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
            setupExcelCells(defenseList, 10, defenseSheet, cellStyle, headerStyle);

            File file = new File("C:\\Users\\Jelle\\OneDrive\\Desktop\\Exceldaten\\Draftrankings.xls");

            wb.write(file);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setupExcelCells(ArrayList<?> playerList, int property, HSSFSheet excelSheet, CellStyle cellStyle, CellStyle headerStyle) {
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

    private static void positionDraftorder(HSSFSheet draftPlan, int amountDraftRounds, CellStyle headerStyle, CellStyle cellStyle) {
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
            quarterbackSheet.getRow(i + 1).createCell(14).setCellValue(qbList.get(i).getRookie().toString());
            quarterbackSheet.getRow(i + 1).createCell(15).setCellValue(qbList.get(i).getByeWeek());
        }
    }

    private static void giveReceiverValue(ArrayList<Wrs> wrList, ArrayList<Tes> teList, HSSFSheet wideReceiverSheet, HSSFSheet tightEndSheet) {
        for (int j = 0; j < wrList.size(); j++) {
            wideReceiverSheet.createRow(j + 1);
            wideReceiverSheet.getRow(j + 1).createCell(0).setCellValue(wrList.get(j).getPosition());
            wideReceiverSheet.getRow(j + 1).createCell(1).setCellValue(wrList.get(j).getValue());
            wideReceiverSheet.getRow(j + 1).createCell(2).setCellValue(wrList.get(j).getName());
            wideReceiverSheet.getRow(j + 1).createCell(3).setCellValue(wrList.get(j).getTeam());
            wideReceiverSheet.getRow(j + 1).createCell(4).setCellValue(wrList.get(j).getDraftorder());
            wideReceiverSheet.getRow(j + 1).createCell(5).setCellValue(wrList.get(j).getReceptions());
            wideReceiverSheet.getRow(j + 1).createCell(6).setCellValue(wrList.get(j).getReceivingYards());
            wideReceiverSheet.getRow(j + 1).createCell(7).setCellValue(wrList.get(j).getTds());
            wideReceiverSheet.getRow(j + 1).createCell(8).setCellValue(wrList.get(j).getFfppg());
            wideReceiverSheet.getRow(j + 1).createCell(9).setCellValue(wrList.get(j).getTotalFFP());
            wideReceiverSheet.getRow(j + 1).createCell(10).setCellValue(wrList.get(j).getQbs());
            wideReceiverSheet.getRow(j + 1).createCell(11).setCellValue(wrList.get(j).getRookie().toString());
            wideReceiverSheet.getRow(j + 1).createCell(12).setCellValue(wrList.get(j).getByeWeek());
            if (j < teList.size()) {
                tightEndSheet.createRow(j + 1);
                tightEndSheet.getRow(j + 1).createCell(0).setCellValue(teList.get(j).getPosition());
                tightEndSheet.getRow(j + 1).createCell(1).setCellValue(teList.get(j).getValue());
                tightEndSheet.getRow(j + 1).createCell(2).setCellValue(teList.get(j).getName());
                tightEndSheet.getRow(j + 1).createCell(3).setCellValue(teList.get(j).getTeam());
                tightEndSheet.getRow(j + 1).createCell(4).setCellValue(teList.get(j).getDraftorder());
                tightEndSheet.getRow(j + 1).createCell(5).setCellValue(teList.get(j).getReceptions());
                tightEndSheet.getRow(j + 1).createCell(6).setCellValue(teList.get(j).getReceivingYards());
                tightEndSheet.getRow(j + 1).createCell(7).setCellValue(teList.get(j).getTds());
                tightEndSheet.getRow(j + 1).createCell(8).setCellValue(teList.get(j).getFfppg());
                tightEndSheet.getRow(j + 1).createCell(9).setCellValue(teList.get(j).getTotalFFP());
                tightEndSheet.getRow(j + 1).createCell(10).setCellValue(teList.get(j).getQbs());
                tightEndSheet.getRow(j + 1).createCell(11).setCellValue(teList.get(j).getRookie().toString());
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
            runningBackSheet.getRow(k + 1).createCell(6).setCellValue(rbsList.get(k).getReceivingYards());
            runningBackSheet.getRow(k + 1).createCell(9).setCellValue(rbsList.get(k).getTds());
            runningBackSheet.getRow(k + 1).createCell(10).setCellValue(rbsList.get(k).getoLine());
            runningBackSheet.getRow(k + 1).createCell(11).setCellValue(rbsList.get(k).getFfppg());
            runningBackSheet.getRow(k + 1).createCell(12).setCellValue(rbsList.get(k).getTotalFFP());
            runningBackSheet.getRow(k + 1).createCell(13).setCellValue(rbsList.get(k).getRookie().toString());
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
            defenseSheet.getRow(m + 1).createCell(6).setCellValue(defenseList.get(m).getFumbleRecoveries());
            defenseSheet.getRow(m + 1).createCell(7).setCellValue(defenseList.get(m).getInts());
            defenseSheet.getRow(m + 1).createCell(8).setCellValue(defenseList.get(m).getTds());
            defenseSheet.getRow(m + 1).createCell(9).setCellValue(defenseList.get(m).getByeWeek());
        }
    }
}
