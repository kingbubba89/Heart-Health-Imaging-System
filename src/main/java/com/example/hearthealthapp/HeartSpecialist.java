package com.example.hearthealthapp;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HeartSpecialist {

    public static String determineRisk(int totalCACScore) {

        if (totalCACScore < 0) {
            return "Invalid CAC score.";
        }

        if (totalCACScore == 0) {
            return "No plaque. Your risk of heart attack is low.";
        }

        if (totalCACScore <= 10) {
            return "Small amount of plaque. You have less than a 10 percent "
                    + "chance of having heart disease, and your risk of heart "
                    + "attack is low.";
        }

        if (totalCACScore <= 100) {
            return "Some plaque. You have mild heart disease and a moderate "
                    + "chance of heart attack. Your doctor may recommend other "
                    + "treatment in addition to lifestyle changes.";
        }

        if (totalCACScore <= 400) {
            return "Moderate amount of plaque. You have heart disease and "
                    + "plaque may be blocking an artery. Your chance of having "
                    + "a heart attack is moderate to high. Your health "
                    + "professional may want more tests and may start treatment.";
        }

        return "Large amount of plaque. You have more than a 90 percent chance "
                + "that plaque is blocking one of your arteries. Your chance "
                + "of heart attack is high. Your health professional will want "
                + "more tests and will start treatment.";
    }

    public static boolean emailPatient(
            PatientRecord patient,
            CTTest ctTest) {

        if (patient == null || ctTest == null) {
            return false;
        }

        try {
            String subject =
                    "Heart Health CT Scan Results";

            String body =
                    "Hello " + patient.getFirstName() + ",\n\n"
                            + "Your total CAC score is "
                            + ctTest.getTotalCACScore() + ".\n\n"
                            + "Risk Assessment:\n"
                            + determineRisk(ctTest.getTotalCACScore());

            String mailto = "mailto:"
                    + patient.getEmail()
                    + "?subject="
                    + URLEncoder.encode(
                    subject,
                    StandardCharsets.UTF_8
            )
                    + "&body="
                    + URLEncoder.encode(
                    body,
                    StandardCharsets.UTF_8
            );

            Desktop.getDesktop().mail(new URI(mailto));

            return true;

        } catch (Exception e) {
            System.out.println(
                    "Unable to open email application."
            );

            return false;
        }
    }


}