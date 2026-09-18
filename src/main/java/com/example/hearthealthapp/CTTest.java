package com.example.hearthealthapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class CTTest {

    private String patientID;
    private int totalCACScore;
    private int LMScore;
    private int LADScore;
    private int LCXScore;
    private int RCAScore;
    private int PDAScore;

    public CTTest(String patientID,
                  int totalCACScore,
                  int LMScore,
                  int LADScore,
                  int LCXScore,
                  int RCAScore,
                  int PDAScore) {

        this.patientID = patientID;
        this.totalCACScore = totalCACScore;
        this.LMScore = LMScore;
        this.LADScore = LADScore;
        this.LCXScore = LCXScore;
        this.RCAScore = RCAScore;
        this.PDAScore = PDAScore;
    }

    public String getPatientID() {
        return patientID;
    }

    public int getTotalCACScore() {
        return totalCACScore;
    }

    public int getLMScore() {
        return LMScore;
    }

    public int getLADScore() {
        return LADScore;
    }

    public int getLCXScore() {
        return LCXScore;
    }

    public int getRCAScore() {
        return RCAScore;
    }

    public int getPDAScore() {
        return PDAScore;
    }

    public void storeCTScanData() {

        File dataDirectory = new File("data");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        File ctFile = new File(
                dataDirectory,
                patientID + "_CTResults.txt"
        );

        try (FileWriter writer = new FileWriter(ctFile)) {

            writer.write("Patient ID: " + patientID + "\n");
            writer.write("Total CAC Score: " + totalCACScore + "\n");
            writer.write("LM: " + LMScore + "\n");
            writer.write("LAD: " + LADScore + "\n");
            writer.write("LCX: " + LCXScore + "\n");
            writer.write("RCA: " + RCAScore + "\n");
            writer.write("PDA: " + PDAScore + "\n");

        } catch (IOException e) {
            System.out.println("Error saving CT scan results.");
            e.printStackTrace();
        }
    }

    public static CTTest loadCTScanData(String patientID) {

        File ctFile = new File(
                "data",
                patientID + "_CTResults.txt"
        );

        if (!ctFile.exists()) {
            return null;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(ctFile))) {

            reader.readLine(); // Patient ID

            int totalCACScore =
                    Integer.parseInt(reader.readLine().split(": ")[1]);

            int LMScore =
                    Integer.parseInt(reader.readLine().split(": ")[1]);

            int LADScore =
                    Integer.parseInt(reader.readLine().split(": ")[1]);

            int LCXScore =
                    Integer.parseInt(reader.readLine().split(": ")[1]);

            int RCAScore =
                    Integer.parseInt(reader.readLine().split(": ")[1]);

            int PDAScore =
                    Integer.parseInt(reader.readLine().split(": ")[1]);

            return new CTTest(
                    patientID,
                    totalCACScore,
                    LMScore,
                    LADScore,
                    LCXScore,
                    RCAScore,
                    PDAScore
            );

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading CT scan results.");
            e.printStackTrace();
            return null;
        }
    }
}