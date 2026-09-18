package com.example.hearthealthapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class HeartHealthApplication extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        showLoginPage();
        primaryStage.setTitle(
                "Heart Health Imaging and Recording System"
        );
        primaryStage.show();
    }

    private void showWelcomePage() {
        Label title = new Label(
                "Welcome to Heart Health Imaging and Recording System"
        );
        title.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold;"
        );
        Button intakeButton =
                new Button("Patient Intake");
        Button technicianButton =
                new Button("CT Scan Tech View");
        Button patientButton =
                new Button("Patient View");
        Button doctorButton =
                new Button("Doctor View");
        intakeButton.setOnAction(event -> showPatientIntake());
        intakeButton.setPrefWidth(220);
        technicianButton.setPrefWidth(220);
        patientButton.setPrefWidth(220);
        doctorButton.setPrefWidth(220);
        technicianButton.setOnAction(
                event -> showCTScanTechView());
        patientButton.setOnAction(
                event -> showPatientView());
        doctorButton.setOnAction(
                event -> showDoctorView());
        Button logoutButton = new Button("Logout");

        logoutButton.setOnAction(
                event -> showLoginPage()
        );


        VBox layout = new VBox(
                15,
                title,
                intakeButton,
                technicianButton,
                patientButton,
                doctorButton,
                logoutButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 600, 400);

        primaryStage.setScene(scene);
    }

    private void showPatientIntake() {
        Label title = new Label("Patient Intake Form");

        title.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();
        TextField phoneField = new TextField();
        TextArea healthHistoryArea = new TextArea();
        TextField insuranceIDField = new TextField();
        DatePicker examDatePicker = new DatePicker();

        healthHistoryArea.setPrefRowCount(3);

        GridPane form = new GridPane();

        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("First Name:"), 0, 0);
        form.add(firstNameField, 1, 0);

        form.add(new Label("Last Name:"), 0, 1);
        form.add(lastNameField, 1, 1);

        form.add(new Label("Email:"), 0, 2);
        form.add(emailField, 1, 2);

        form.add(new Label("Phone Number:"), 0, 3);
        form.add(phoneField, 1, 3);

        form.add(new Label("Health History:"), 0, 4);
        form.add(healthHistoryArea, 1, 4);

        form.add(new Label("Insurance ID:"), 0, 5);
        form.add(insuranceIDField, 1, 5);

        form.add(new Label("Exam Date:"), 0, 6);
        form.add(examDatePicker, 1, 6);

        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showWelcomePage());
        Label messageLabel = new Label();

        saveButton.setOnAction(event -> {

            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneField.getText();
            String healthHistory = healthHistoryArea.getText();
            String insuranceID = insuranceIDField.getText();

            if (firstName.isBlank()
                    || lastName.isBlank()
                    || email.isBlank()
                    || phoneNumber.isBlank()
                    || insuranceID.isBlank()
                    || examDatePicker.getValue() == null) {

                messageLabel.setText(
                        "Please complete all required fields."
                );

                return;
            }

            String patientID =
                    Receptionist.generatePatientID();

            PatientRecord patient =
                    new PatientRecord(
                            patientID,
                            firstName,
                            lastName,
                            email,
                            phoneNumber,
                            healthHistory,
                            insuranceID
                    );

            patient.storePatientInformation();
            Appointment appointment = new Appointment(
                    patientID,
                    examDatePicker.getValue()
            );

            appointment.scheduleCTScan();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient Created");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Patient saved successfully.\nPatient ID: " + patientID
            );
            alert.showAndWait();
        });

        VBox layout = new VBox(
                20,
                title,
                form,
                saveButton,
                backButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        primaryStage.setScene(
                new Scene(layout, 650, 550)
        );
    }

    private void showCTScanTechView() {

        Label title = new Label("CT Scan Technician View");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField patientIDField = new TextField();
        TextField totalCACField = new TextField();
        TextField lmField = new TextField();
        TextField ladField = new TextField();
        TextField lcxField = new TextField();
        TextField rcaField = new TextField();
        TextField pdaField = new TextField();

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setAlignment(Pos.CENTER);

        form.add(new Label("Patient ID:"), 0, 0);
        form.add(patientIDField, 1, 0);

        form.add(new Label("Total CAC Score:"), 0, 1);
        form.add(totalCACField, 1, 1);

        form.add(new Label("LM Score:"), 0, 2);
        form.add(lmField, 1, 2);

        form.add(new Label("LAD Score:"), 0, 3);
        form.add(ladField, 1, 3);

        form.add(new Label("LCX Score:"), 0, 4);
        form.add(lcxField, 1, 4);

        form.add(new Label("RCA Score:"), 0, 5);
        form.add(rcaField, 1, 5);

        form.add(new Label("PDA Score:"), 0, 6);
        form.add(pdaField, 1, 6);

        Button saveButton = new Button("Save Results");
        Button backButton = new Button("Back");

        Label messageLabel = new Label();

        saveButton.setOnAction(event -> {

            try {
                String patientID = patientIDField.getText().trim();

                int totalCAC = Integer.parseInt(totalCACField.getText());
                int lm = Integer.parseInt(lmField.getText());
                int lad = Integer.parseInt(ladField.getText());
                int lcx = Integer.parseInt(lcxField.getText());
                int rca = Integer.parseInt(rcaField.getText());
                int pda = Integer.parseInt(pdaField.getText());

                boolean saved = CTScanTechnician.saveCTResults(
                        patientID,
                        totalCAC,
                        lm,
                        lad,
                        lcx,
                        rca,
                        pda
                );

                if (saved) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("CT results saved successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong patient ID.");
                    alert.showAndWait();
                }

            } catch (NumberFormatException e) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("All CT scores must be numbers.");
                    alert.showAndWait();
                }
        });

        backButton.setOnAction(event -> showWelcomePage());

        VBox layout = new VBox(
                20,
                title,
                form,
                saveButton,
                messageLabel,
                backButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        primaryStage.setScene(new Scene(layout, 600, 550));
    }

    private void showPatientView() {

        Label title = new Label("Patient View");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField patientIDField = new TextField();
        patientIDField.setPromptText("Enter Patient ID");

        Button viewButton = new Button("View Results");
        Button backButton = new Button("Back");

        Label nameLabel = new Label();
        Label totalLabel = new Label();
        Label lmLabel = new Label();
        Label ladLabel = new Label();
        Label lcxLabel = new Label();
        Label rcaLabel = new Label();
        Label pdaLabel = new Label();
        Label messageLabel = new Label();

        viewButton.setOnAction(event -> {

            String patientID = patientIDField.getText().trim();
            if (!PatientRecord.patientExists(patientID)) {

                messageLabel.setText("Wrong patient ID.");

                clearResultLabels(
                        nameLabel,
                        totalLabel,
                        lmLabel,
                        ladLabel,
                        lcxLabel,
                        rcaLabel,
                        pdaLabel
                );

                return;
            }

            PatientRecord patient =
                    PatientRecord.loadPatientInformation(patientID);

            CTTest ctTest =
                    Patient.viewCTScanResults(patientID);

            if (ctTest == null) {

                nameLabel.setText(
                        "Hello " + patient.getFirstName()
                );

                messageLabel.setText(
                        "No data is available yet."
                );

                totalLabel.setText("");
                lmLabel.setText("");
                ladLabel.setText("");
                lcxLabel.setText("");
                rcaLabel.setText("");
                pdaLabel.setText("");

                return;
            }

            nameLabel.setText(
                    "Hello " + patient.getFirstName()
            );

            totalLabel.setText(
                    "Total CAC Score: "
                            + ctTest.getTotalCACScore()
            );

            lmLabel.setText("LM: " + ctTest.getLMScore());
            ladLabel.setText("LAD: " + ctTest.getLADScore());
            lcxLabel.setText("LCX: " + ctTest.getLCXScore());
            rcaLabel.setText("RCA: " + ctTest.getRCAScore());
            pdaLabel.setText("PDA: " + ctTest.getPDAScore());

            messageLabel.setText("");
        });

        backButton.setOnAction(event -> showWelcomePage());

        VBox layout = new VBox(
                12,
                title,
                patientIDField,
                viewButton,
                nameLabel,
                totalLabel,
                lmLabel,
                ladLabel,
                lcxLabel,
                rcaLabel,
                pdaLabel,
                messageLabel,
                backButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        primaryStage.setScene(new Scene(layout, 600, 550));
    }

    private void showDoctorView() {

        Label title = new Label("Doctor View");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField patientIDField = new TextField();
        patientIDField.setPromptText("Enter Patient ID");

        Button viewButton = new Button("View Results");
        Button riskButton = new Button("Determine Risk");
        Button backButton = new Button("Back");
        Button emailButton = new Button("Email Patient");

        Label nameLabel = new Label();
        Label totalLabel = new Label();
        Label lmLabel = new Label();
        Label ladLabel = new Label();
        Label lcxLabel = new Label();
        Label rcaLabel = new Label();
        Label pdaLabel = new Label();

        Label messageLabel = new Label();

        Label riskLabel = new Label();
        riskLabel.setWrapText(true);
        riskLabel.setMaxWidth(500);

        final CTTest[] currentTest = new CTTest[1];
        final PatientRecord[] currentPatient =
                new PatientRecord[1];

        viewButton.setOnAction(event -> {

            String patientID = patientIDField.getText().trim();

            if (!PatientRecord.patientExists(patientID)) {

                messageLabel.setText("Wrong patient ID.");
                riskLabel.setText("");
                currentTest[0] = null;
                currentPatient[0] = null;

                clearResultLabels(
                        nameLabel,
                        totalLabel,
                        lmLabel,
                        ladLabel,
                        lcxLabel,
                        rcaLabel,
                        pdaLabel
                );

                return;
            }

            PatientRecord patient = PatientRecord.loadPatientInformation(patientID);
            currentPatient[0] = patient;

            CTTest ctTest =
                    CTTest.loadCTScanData(patientID);

            if (ctTest == null) {

                nameLabel.setText(
                        "Hello " + patient.getFirstName()
                );

                messageLabel.setText(
                        "No data is available yet."
                );

                totalLabel.setText("");
                lmLabel.setText("");
                ladLabel.setText("");
                lcxLabel.setText("");
                rcaLabel.setText("");
                pdaLabel.setText("");

                currentTest[0] = null;
                riskLabel.setText("");

                return;
            }

            currentTest[0] = ctTest;

            nameLabel.setText(
                    "Hello " + patient.getFirstName()
            );

            totalLabel.setText(
                    "Total CAC Score: "
                            + ctTest.getTotalCACScore()
            );

            lmLabel.setText("LM: " + ctTest.getLMScore());
            ladLabel.setText("LAD: " + ctTest.getLADScore());
            lcxLabel.setText("LCX: " + ctTest.getLCXScore());
            rcaLabel.setText("RCA: " + ctTest.getRCAScore());
            pdaLabel.setText("PDA: " + ctTest.getPDAScore());

            messageLabel.setText("");
            riskLabel.setText("");
        });

        riskButton.setOnAction(event -> {

            if (currentTest[0] == null) {
                riskLabel.setText(
                        "Load valid CT results first."
                );

                return;
            }

            riskLabel.setText(
                    HeartSpecialist.determineRisk(
                            currentTest[0].getTotalCACScore()
                    )
            );
        });

        emailButton.setOnAction(event -> {

            if (currentPatient[0] == null
                    || currentTest[0] == null) {

                messageLabel.setText(
                        "Load valid CT results first."
                );

                return;
            }

            boolean opened =
                    HeartSpecialist.emailPatient(
                            currentPatient[0],
                            currentTest[0]
                    );

            if (!opened) {
                messageLabel.setText(
                        "Unable to open email application."
                );
            }
        });

        backButton.setOnAction(event -> showWelcomePage());

        VBox layout = new VBox(
                10,
                title,
                patientIDField,
                viewButton,
                nameLabel,
                totalLabel,
                lmLabel,
                ladLabel,
                lcxLabel,
                rcaLabel,
                pdaLabel,
                messageLabel,
                riskButton,
                riskLabel,
                emailButton,
                backButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        primaryStage.setScene(new Scene(layout, 650, 650));
    }

    private void showLoginPage() {

        Label title = new Label(
                "Heart Health Imaging and Recording System"
        );

        title.setStyle(
                "-fx-font-size: 20px; -fx-font-weight: bold;"
        );

        Label loginTitle = new Label("Login");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();

        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);

        Button loginButton = new Button("Login");

        Label messageLabel = new Label();

        loginButton.setOnAction(event -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    passwordField.getText();

            User user = Login.authenticateUser(
                    username,
                    password
            );

            if (user == null) {

                messageLabel.setText(
                        "Invalid username or password."
                );

                return;
            }

            showWelcomePage();
        });

        VBox layout = new VBox(
                15,
                title,
                loginTitle,
                usernameField,
                passwordField,
                loginButton,
                messageLabel
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        primaryStage.setScene(
                new Scene(layout, 600, 400)
        );
    }


    private void clearResultLabels(Label... labels) {

        for (Label label : labels) {
            label.setText("");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}