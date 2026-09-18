package com.example.hearthealthapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class PatientRecord {

    private String patientID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String healthHistory;
    private String insuranceID;

    public PatientRecord(String patientID,
                         String firstName,
                         String lastName,
                         String email,
                         String phoneNumber,
                         String healthHistory,
                         String insuranceID) {

        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.healthHistory = healthHistory;
        this.insuranceID = insuranceID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHealthHistory() {
        return healthHistory;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public void storePatientInformation() {
        File dataDirectory = new File("data");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File patientFile = new File(
                dataDirectory,
                patientID + "_PatientInfo.txt");

        try (FileWriter writer = new FileWriter(patientFile)) {
            writer.write("Patient ID: " + patientID + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Health History: " + healthHistory + "\n");
            writer.write("Insurance ID: " + insuranceID + "\n");

        } catch (IOException e) {
            System.out.println("Error saving patient information.");
            e.printStackTrace();
        }
    }

    public static boolean patientExists(String patientID) {
        File patientFile = new File(
                "data",
                patientID + "_PatientInfo.txt"
        );
        return patientFile.exists();
    }

    public static PatientRecord loadPatientInformation(String patientID) {
        File patientFile = new File(
                "data",
                patientID + "_PatientInfo.txt"
        );

        if (!patientFile.exists()) {
            return null;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(patientFile))) {
            reader.readLine();

            String firstName =
                    reader.readLine().split(": ", 2)[1];
            String lastName =
                    reader.readLine().split(": ", 2)[1];
            String email =
                    reader.readLine().split(": ", 2)[1];
            String phoneNumber =
                    reader.readLine().split(": ", 2)[1];
            String healthHistory =
                    reader.readLine().split(": ", 2)[1];
            String insuranceID =
                    reader.readLine().split(": ", 2)[1];

            return new PatientRecord(
                    patientID,
                    firstName,
                    lastName,
                    email,
                    phoneNumber,
                    healthHistory,
                    insuranceID
            );

        } catch (IOException e) {
            System.out.println("Error reading patient information.");
            e.printStackTrace();
            return null;
        }
    }

}



