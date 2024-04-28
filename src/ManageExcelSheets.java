import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.util.ArrayList;

public class ManageExcelSheets {

    /**
     * Erstellen der Excel-Datei
     *
     * ToDO: In Methoden Aufteilen.
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

            for (int i = 0; i < amountDraftRounds + 1; i++) {
                draftPlan.createRow(i);
                if (i > 0) {
                    draftPlan.getRow(i).createCell(0).setCellValue(i);

                } else {
                    draftPlan.getRow(i).createCell(0).setCellValue("Runde");
                }
                draftPlan.getRow(i).getCell(0).setCellStyle(headerStyle);
            }
            draftPlan.getRow(0).createCell(1).setCellValue("Position");
            draftPlan.getRow(0).getCell(1).setCellStyle(headerStyle);
            draftPlan.getRow(1).createCell(1).setCellValue("Wide Receiver");
            draftPlan.getRow(2).createCell(1).setCellValue("Wide Receiver");
            draftPlan.getRow(3).createCell(1).setCellValue("Wide Receiver");
            draftPlan.getRow(4).createCell(1).setCellValue("Running Back");
            draftPlan.getRow(5).createCell(1).setCellValue("Tight End");
            draftPlan.getRow(6).createCell(1).setCellValue("Flex Spot");
            draftPlan.getRow(7).createCell(1).setCellValue("Quarterback");
            draftPlan.getRow(8).createCell(1).setCellValue("Kicker");
            draftPlan.getRow(9).createCell(1).setCellValue("Defense");
            draftPlan.getRow(10).createCell(1).setCellValue("Wide Receiver");
            draftPlan.getRow(11).createCell(1).setCellValue("Wide Receiver");
            draftPlan.getRow(12).createCell(1).setCellValue("Running Back");
            draftPlan.getRow(13).createCell(1).setCellValue("Tight End");
            draftPlan.getRow(14).createCell(1).setCellValue("Quarterback");
            draftPlan.getRow(15).createCell(1).setCellValue("Kicker");
            draftPlan.getRow(16).createCell(1).setCellValue("Defense");
            for (int i = 1; i < amountDraftRounds + 1; i++) {
                draftPlan.getRow(i).getCell(1).setCellStyle(cellStyle);
            }
            draftPlan.autoSizeColumn(0);
            draftPlan.autoSizeColumn(1);

            String[] captions = {"Rank", "Value", "Name", "Team", "Draft Order", "Passing Yards", "Cmp Pct", "TD/INT", "FFPpG", "Total FFP", "WR-Room", "Rookie", "Bye Week", "Receptions", "Receiving Yards", "TD", "QB", "Rushing Attempts", "Rushing Yards", "O-Line", "Attempts", "Made", "Pct", "XP", "50+", "Order", "Sacks", "Safeties", "Fumble Recoveries", "Ints", "Fumbles"};
            int[] qbCaption = {0, 1, 2, 3, 4, 5, 6, 7, 8, 18, 30, 9, 10, 11, 12};
            int[] wrTeCaption = {0, 1, 2, 3, 4, 13, 14, 15, 8, 9, 16, 11, 12};
            int[] rbCaption = {0, 1, 2, 3, 4, 17, 18, 13, 14, 15, 19, 8, 9, 11, 12};
            int[] kickerCaption = {0, 1, 2, 3, 4, 20, 21, 22, 23, 24, 8, 9, 12};
            int[] defenseCaption = {0, 1, 2, 25, 26, 27, 28, 29, 15, 12};

            quarterbackSheet.createRow(0);
            for (int i = 0; i < qbCaption.length; i++) {
                quarterbackSheet.getRow(0).createCell(i).setCellValue(captions[qbCaption[i]]);
            }

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
                quarterbackSheet.getRow(i + 1).createCell(9).setCellValue(qbList.get(i).getTdInt());
                quarterbackSheet.getRow(i + 1).createCell(10).setCellValue(qbList.get(i).getFfppg());
                quarterbackSheet.getRow(i + 1).createCell(11).setCellValue(qbList.get(i).getTotalFFP());
                quarterbackSheet.getRow(i + 1).createCell(12).setCellValue(qbList.get(i).getWrRoom());
                quarterbackSheet.getRow(i + 1).createCell(13).setCellValue(qbList.get(i).getRookie().toString());
                quarterbackSheet.getRow(i + 1).createCell(14).setCellValue(qbList.get(i).getByeWeek());
            }

            wideReceiverSheet.createRow(0);
            tightEndSheet.createRow(0);
            for (int j = 0; j < wrTeCaption.length; j++) {
                wideReceiverSheet.getRow(0).createCell(j).setCellValue(captions[wrTeCaption[j]]);
                tightEndSheet.getRow(0).createCell(j).setCellValue(captions[wrTeCaption[j]]);
            }

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

            runningBackSheet.createRow(0);
            for (int k = 0; k < rbCaption.length; k++) {
                runningBackSheet.getRow(0).createCell(k).setCellValue(captions[rbCaption[k]]);
            }

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

            kickerSheet.createRow(0);
            for (int m = 0; m < kickerCaption.length; m++) {
                kickerSheet.getRow(0).createCell(m).setCellValue(captions[kickerCaption[m]]);
            }

            for (int m = 0; m < kickerList.size(); m++) {
                kickerSheet.createRow(m + 1);
                kickerSheet.getRow(m + 1).createCell(0).setCellValue(kickerList.get(m).getPosition());
                kickerSheet.getRow(m + 1).createCell(1).setCellValue(kickerList.get(m).getValue());
                kickerSheet.getRow(m + 1).createCell(2).setCellValue(kickerList.get(m).getName());
                kickerSheet.getRow(m + 1).createCell(3).setCellValue(kickerList.get(m).getTeam());
                kickerSheet.getRow(m + 1).createCell(4).setCellValue(kickerList.get(m).getDraftOrder());
                kickerSheet.getRow(m + 1).createCell(5).setCellValue(kickerList.get(m).getAttempts());
                kickerSheet.getRow(m + 1).createCell(6).setCellValue(kickerList.get(m).getMade());
                kickerSheet.getRow(m + 1).createCell(7).setCellValue(kickerList.get(m).getPct());
                kickerSheet.getRow(m + 1).createCell(8).setCellValue(kickerList.get(m).getXp());
                kickerSheet.getRow(m + 1).createCell(9).setCellValue(kickerList.get(m).getFiftyPlus());
                kickerSheet.getRow(m + 1).createCell(10).setCellValue(kickerList.get(m).getFfppg());
                kickerSheet.getRow(m + 1).createCell(11).setCellValue(kickerList.get(m).getTotalFFP());
                kickerSheet.getRow(m + 1).createCell(12).setCellValue(kickerList.get(m).getByeWeek());
            }

            defenseSheet.createRow(0);
            for (int l = 0; l < defenseCaption.length; l++) {
                defenseSheet.getRow(0).createCell(l).setCellValue(captions[defenseCaption[l]]);
            }

            for (int l = 0; l < defenseList.size(); l++) {
                defenseSheet.createRow(l + 1);
                defenseSheet.getRow(l + 1).createCell(0).setCellValue(defenseList.get(l).getPosition());
                defenseSheet.getRow(l + 1).createCell(1).setCellValue(defenseList.get(l).getValue());
                defenseSheet.getRow(l + 1).createCell(2).setCellValue(defenseList.get(l).getName());
                defenseSheet.getRow(l + 1).createCell(3).setCellValue(defenseList.get(l).getOrder());
                defenseSheet.getRow(l + 1).createCell(4).setCellValue(defenseList.get(l).getSacks());
                defenseSheet.getRow(l + 1).createCell(5).setCellValue(defenseList.get(l).getSafeties());
                defenseSheet.getRow(l + 1).createCell(6).setCellValue(defenseList.get(l).getFumbleRecoveries());
                defenseSheet.getRow(l + 1).createCell(7).setCellValue(defenseList.get(l).getInts());
                defenseSheet.getRow(l + 1).createCell(8).setCellValue(defenseList.get(l).getTds());
                defenseSheet.getRow(l + 1).createCell(9).setCellValue(defenseList.get(l).getByeWeek());
            }

            for (int i = 0; i <= wrList.size(); i++) {
                if (i <= qbList.size()) {
                    for (int j = 0; j < 13; j++) {
                        quarterbackSheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                        if (i < 1) {
                            quarterbackSheet.autoSizeColumn(j);
                            quarterbackSheet.getRow(i).getCell(j).setCellStyle(headerStyle);
                        }
                    }
                }
                if (i <= teList.size()) {
                    for (int j = 0; j < 13; j++) {
                        tightEndSheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                        if (i < 1) {
                            tightEndSheet.autoSizeColumn(j);
                            tightEndSheet.getRow(i).getCell(j).setCellStyle(headerStyle);
                        }
                    }
                }
                if (i <= rbsList.size()) {
                    for (int j = 0; j < 15; j++) {
                        runningBackSheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                        if (i < 1) {
                            runningBackSheet.autoSizeColumn(j);
                            runningBackSheet.getRow(i).getCell(j).setCellStyle(headerStyle);
                        }
                    }
                }
                if (i <= kickerList.size()) {
                    for (int j = 0; j < 13; j++) {
                        kickerSheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                        if (i < 1) {
                            kickerSheet.autoSizeColumn(j);
                            kickerSheet.getRow(i).getCell(j).setCellStyle(headerStyle);
                        }
                    }
                }
                if (i <= defenseList.size()) {
                    for (int j = 0; j < 10; j++) {
                        defenseSheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                        if (i < 1) {
                            defenseSheet.autoSizeColumn(j);
                            defenseSheet.getRow(i).getCell(j).setCellStyle(headerStyle);
                        }
                    }
                }
                for (int j = 0; j < 13; j++) {
                    wideReceiverSheet.getRow(i).getCell(j).setCellStyle(cellStyle);
                    if (i < 1) {
                        wideReceiverSheet.autoSizeColumn(j);
                        wideReceiverSheet.getRow(i).getCell(j).setCellStyle(headerStyle);
                    }
                }
            }

            File file = new File("C:\\Users\\Jelle\\OneDrive\\Desktop\\Exceldaten\\Draftrankings.xls");

            wb.write(file);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
