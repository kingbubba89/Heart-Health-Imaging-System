package com.example.hearthealthapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Appointment {

    private String patientID;
    private LocalDate examDate;

    public Appointment(String patientID, LocalDate examDate) {
        this.patientID = patientID;
        this.examDate = examDate;
    }

    public void scheduleCTScan() {

        File dataDirectory = new File("data");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        File appointmentFile = new File(
                dataDirectory,
                patientID + "_Appointment.txt"
        );

        try (FileWriter writer = new FileWriter(appointmentFile)) {

            writer.write("Patient ID: " + patientID + "\n");
            writer.write("Exam Date: " + examDate + "\n");

        } catch (IOException e) {
            System.out.println("Error saving appointment.");
            e.printStackTrace();
        }
    }

    public String getPatientID() {
        return patientID;
    }

    public LocalDate getExamDate() {
        return examDate;
    }
}
