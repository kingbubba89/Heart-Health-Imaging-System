package com.example.hearthealthapp;

import java.io.File;
import java.util.Random;

public class Receptionist {

    private static final String DATA_DIRECTORY = "data";

    public static String generatePatientID() {

        Random random = new Random();
        String patientID;
        File patientFile;

        do {
            int id = 10000 + random.nextInt(90000);
            patientID = String.valueOf(id);

            patientFile = new File(
                    DATA_DIRECTORY,
                    patientID + "_PatientInfo.txt"
            );

        } while (patientFile.exists());

        return patientID;
    }
}