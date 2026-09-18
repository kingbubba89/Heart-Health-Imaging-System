package com.example.hearthealthapp;

public class Patient {

    public static CTTest viewCTScanResults(String patientID) {
        if (!PatientRecord.patientExists(patientID)) {
            return null;
        }
        return CTTest.loadCTScanData(patientID);
    }
}