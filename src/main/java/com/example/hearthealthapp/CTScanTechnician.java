package com.example.hearthealthapp;

public class CTScanTechnician {

    public static boolean saveCTResults(
            String patientID,
            int totalCACScore,
            int LMScore,
            int LADScore,
            int LCXScore,
            int RCAScore,
            int PDAScore) {

        if (!PatientRecord.patientExists(patientID)) {
            return false;
        }

        CTTest ctTest = new CTTest(
                patientID,
                totalCACScore,
                LMScore,
                LADScore,
                LCXScore,
                RCAScore,
                PDAScore
        );

        ctTest.storeCTScanData();

        return true;
    }
    }