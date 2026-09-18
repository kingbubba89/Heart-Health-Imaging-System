# Heart Health Imaging and Recording System

A JavaFX application developed for CSE 460 Software Analysis and Design.

The system manages patient intake information, CT scan results, patient result viewing, and cardiovascular risk assessment for heart health imaging.

## Features

- User login
- Patient intake and registration
- Automatic five-digit patient ID generation
- CT scan appointment scheduling
- CT scan result recording
- Patient CT result viewing
- Doctor CT result viewing
- CAC-based cardiovascular risk assessment
- Email preparation for patient results
- Text-file data persistence

## Technologies

- Java 21
- JavaFX 21
- Maven
- IntelliJ IDEA
- Astah Professional

## Project Structure

The application includes the following primary classes:

- `HeartHealthApplication` - JavaFX user interface and application entry point
- `Login` - User authentication
- `User` - User account information
- `Role` - User role information
- `Receptionist` - Patient ID generation
- `PatientRecord` - Patient information and persistence
- `Appointment` - CT scan appointment scheduling
- `CTScanTechnician` - CT result entry
- `CTTest` - CT scan result storage and retrieval
- `Patient` - Patient result access
- `HeartSpecialist` - Risk assessment and email functionality

## Requirements

- JDK 21
- Maven, or the included Maven Wrapper

JavaFX dependencies are defined in `pom.xml`.

## Running the Application

### IntelliJ IDEA

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Allow Maven to load the dependencies from `pom.xml`.
4. Set the Project SDK to JDK 21.
5. Open `HeartHealthApplication.java`.
6. Run the `HeartHealthApplication` class.

### Maven Wrapper

On Windows, from the project root:

```powershell
.\mvnw.cmd clean javafx:run
```

On macOS/Linux:

```bash
./mvnw clean javafx:run
```

If Maven is installed globally, you can also run:

```bash
mvn clean javafx:run
```

## Demo Login Accounts

| Role | Username | Password |
| --- | --- | --- |
| Receptionist | `reception` | `1234` |
| CT Scan Technician | `tech` | `1234` |
| Heart Specialist | `doctor` | `1234` |
| Patient | `patient` | `1234` |

These credentials are intended only for demonstration purposes and are not a production authentication system.

## Data Storage

Application data is stored locally in text files inside the `data` directory. Files are associated with the generated five-digit patient ID:

```text
12345_PatientInfo.txt
12345_Appointment.txt
12345_CTResults.txt
```

The `data` directory is generated at runtime and is excluded from version control.

## CT Scan Data

The system records:

- Total Agatston CAC Score
- Left Main (LM)
- Left Anterior Descending (LAD)
- Left Circumflex (LCX)
- Right Coronary Artery (RCA)
- Posterior Descending Artery (PDA)

The Heart Specialist view uses the total CAC score to determine the patient's risk category.

## UML Class Diagram

The final UML class diagram for the implementation was developed using Astah Professional.

## Course

CSE 460 - Software Analysis and Design
