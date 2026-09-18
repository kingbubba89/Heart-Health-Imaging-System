package com.example.hearthealthapp;

public class Login {

    public static User authenticateUser(
            String username,
            String password) {

        if (username.equals("reception")
                && password.equals("1234")) {

            return new User(
                    username,
                    password,
                    new Role("Receptionist")
            );
        }

        if (username.equals("tech")
                && password.equals("1234")) {

            return new User(
                    username,
                    password,
                    new Role("CT Scan Technician")
            );
        }

        if (username.equals("doctor")
                && password.equals("1234")) {

            return new User(
                    username,
                    password,
                    new Role("Heart Specialist")
            );
        }

        if (username.equals("patient")
                && password.equals("1234")) {

            return new User(
                    username,
                    password,
                    new Role("Patient")
            );
        }

        return null;
    }
}