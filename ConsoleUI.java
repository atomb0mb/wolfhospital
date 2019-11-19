import java.util.*;
import java.sql.*;

/**
 * WolfHospital System User Application.
 * 
 */
public class ConsoleUI {

    /** URL for connecting to the database. */
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/esarrit";
    /** Connection object for the database. */
    private Connection conn;
    /** General statement object for for sending SQL statements to the database. */
    private Statement stmt;

    /**
     * Class constructor. Handles the initizaliation of the database.
     * 
     */
    public ConsoleUI() {
        try {

            // Load the driver. This creates an instance of the driver
            // and calls the registerDriver method to make MySql Thin
            // driver, available to clients.
            Class.forName("org.mariadb.jdbc.Driver");

            String user = "esarrit";
            String passwd = "CSC440";

            // Get a connection from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL
            this.conn = DriverManager.getConnection(jdbcURL, user, passwd);

            // Create a statement object that will be sending your
            // SQL statements to the DBMS
            this.stmt = this.conn.createStatement();
            Wolfhospital.createInitialTables(this.stmt);
            Wolfhospital.populateDemoTables(this.stmt);

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Starts the program.
     * 
     * @param args command line arguments
     *
     */
    public static void main(String[] args) {
        /** Initialize database and populate it. */
        ConsoleUI consoleUI = new ConsoleUI();

        /** Scanner that is going to read input in from the user. */
        Scanner console = new Scanner(System.in);
        System.out.println();
        System.out.println("      Welcome to the Wolfpack Hospital System!");

        consoleUI.topLevelUserInterface(console);
        consoleUI.close();

    }

    /**
     * Function used to close the objects related to database connection. It closes
     * Statement and Connection.
     * 
     */
    public void close() {
        try {
            Wolfhospital.close(this.stmt);
            Wolfhospital.close(this.conn);
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Welcome message for the user. Serves as the main menu.
     * 
     */
    public void printMainHeader() {
        System.out.println("\n                 Main Menu\n");
        System.out.println("Enter the corresponding number for the operation Task you wish to perform:");
        System.out.println("1. Information Processing");
        System.out.println("2. CheckIn / CheckOut");
        System.out.println("3. Maintaining Medical Records");
        System.out.println("4. Maintaining Billing Accounts");
        System.out.println("5. Reports");
        System.out.println("6. Quit Program");
        System.out.println();
    }

    /**
     * Header for the Information Processing sub-menu.
     * 
     */
    public void printInfoProcessingHeader() {
        System.out.println("\n      Information Processing Sub-Menu\n");
        System.out.println("Enter the corresponding number for the desired option:");
        System.out.println("1. Patients");
        System.out.println("2. Hospitals");
        System.out.println("3. Staff");
        System.out.println("4. Beds");
        System.out.println("5. Return to main menu");
        System.out.println("6. Quit Program");
        System.out.println();
    }

    /**
     * Header for the CheckIn sub-menu.
     * 
     */
    public void checkInHeader() {
        System.out.println("\n       CheckIn / CheckOut Sub-Menu\n");
        System.out.println("Enter the corresponding number for the desired option:");
        System.out.println("0. Show CheckIn table");
        System.out.println("1. Create a new CheckIn");
        System.out.println("2. Checkout a Patient");
        System.out.println("3. Return to main menu");
        System.out.println("4. Quit the program.");
        System.out.println();
    }

    /**
     * Header for the Maintaining Medical Records sub-menu.
     * 
     */
    public void medicalRecordsHeader() {
        System.out.println("\n      Maintaining Medical Records Sub-Menu\n");
        System.out.println("Enter the corresponding number for the desired option:");
        System.out.println("0. Show Medical Records table");
        System.out.println("1. Enter a new Medical Record");
        System.out.println("2. Update an existing Medical Record");
        System.out.println("3. Return to main menu");
        System.out.println("4. Quit the program.");
        System.out.println();
    }

    /**
     * Header for the Maintaining Billing Accounts sub-menu.
     * 
     */
    public void billingAccountsHeader() {
        System.out.println("\n  Maintaining Billing Accounts Sub-Menu\n");
        System.out.println("0. Show Billing Accounts table");
        System.out.println("1. Enter a new Billing Account");
        System.out.println("2. Update an existing Billing Account");
        System.out.println("3. Return to main menu");
        System.out.println("4. Quit the program.");
        System.out.println();
    }

    /**
     * Header for the Reports sub-menu.
     * 
     */
    public void reportsHeader() {
        System.out.println("\n       Generate Reports Sub-Menu\n");
        System.out.println(
                "1. Generate a report of the billing history for a given patient and for a certain time period (month/year)");
        System.out.println("2. Get the current usage status for all hospitals");
        System.out.println("3. Get the number of patients per month");
        System.out.println("4. Get the hospital usage percentage");
        System.out.println("5. Get information on all the doctors a patient is currently seeing");
        System.out.println("6. Get information on hospitals grouped by their specialty");
        System.out.println("7. Return to main menu");
        System.out.println("8. Quit the program.");
        System.out.println();
    }

    /**
     * Parent layer of the application. Handles user input to perform one of the 4
     * main operations and tasks.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void topLevelUserInterface(Scanner console) {
        printMainHeader();

        /** String object representing the command desired by user. */
        String userAction = "";

        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();

        while (!userAction.equals("6")) {
            while (!userAction.equals("1") && !userAction.equals("2") && !userAction.equals("3")
                    && !userAction.equals("4") && !userAction.equals("5") && !userAction.equals("6")
                    && !userAction.equals("test")) {
                System.out.print("Error! Invalid command. Please enter 1, 2, 3, 4, 5, or 6: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("test")) { // hidden test option to run backend tests from UI
                Wolfhospital.tests(conn, stmt);
                System.out.println("RUN BACKEND TESTS");
            } else if (userAction.equals("1")) {
                informationProcessingSubPanel(console);
            } else if (userAction.equals("2")) {
                checkInSubPanel(console);
            } else if (userAction.equals("3")) {
                medicalRecordsSubPanel(console);
            } else if (userAction.equals("4")) {
                billingAccountsSubPanel(console);
            } else if (userAction.equals("5")) {
                generateReportsSubPanel(console);
            } else {
                System.exit(1);
            }
            printMainHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }
    }

    /**
     * Information Processing Sub-Layer of the application. Handles logic related to
     * handling input related to operations within information processing.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void informationProcessingSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        printInfoProcessingHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("5")) {
            while (!userAction.equals("1") && !userAction.equals("2") && !userAction.equals("3")
                    && !userAction.equals("4") && !userAction.equals("5") && !userAction.equals("6")) {
                System.out.print("Error! Invalid command. Please enter 1, 2, 3, 4, 5, or 6: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("1")) {
                patientOperationsMenu(console);
            } else if (userAction.equals("2")) {
                hospitalOperationsMenu(console);
            } else if (userAction.equals("3")) {
                staffOperationsMenu(console);
            } else if (userAction.equals("4")) {
                bedOperationsMenu(console);
            } else if (userAction.equals("5")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }

    }

    /**
     * CheckIn / CheckOut sub-menu of the application. Handles user input for
     * performing tasks related to patient checkins.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void checkInSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        checkInHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("3")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, or 4: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                Wolfhospital.showCheckInOut(this.stmt);
            } else if (userAction.equals("1")) {
                createCheckIn(console);
            } else if (userAction.equals("2")) {
                patientCheckOut(console);
            } else if (userAction.equals("3")) {
                break;
            } else {
                System.exit(1);
            }
            checkInHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }

    }

    /**
     * Maintaining Medical Records sub-menu. Handles logic related to performing
     * tasks within medical records.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void medicalRecordsSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        medicalRecordsHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("3")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, or 4: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                Wolfhospital.showMedicalRecords(this.stmt);
            } else if (userAction.equals("1")) {
                createMedicalRecord(console);
            } else if (userAction.equals("2")) {
                updateMedicalRecord(console);
            } else if (userAction.equals("3")) {
                break;
            } else {
                System.exit(1);
            }
            medicalRecordsHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }

    }

    /**
     * Handling of logic related to user operations within billing accounts
     * maintenance.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void billingAccountsSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        billingAccountsHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("3")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, or 4: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                Wolfhospital.showBillingAccounts(this.stmt);
            } else if (userAction.equals("1")) {
                createBillingAccount(console);
            } else if (userAction.equals("2")) {
                updateBillingAccount(console);
            } else if (userAction.equals("3")) {
                break;
            } else {
                System.exit(1);
            }
            billingAccountsHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }

    }

    /**
     * Sub-panel for generating the different types of reports.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void generateReportsSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        reportsHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("7")) {
            while (!userAction.equals("1") && !userAction.equals("2") && !userAction.equals("3")
                    && !userAction.equals("4") && !userAction.equals("5") && !userAction.equals("6")
                    && !userAction.equals("7") && !userAction.equals("8")) {
                System.out.print("Error! Invalid command. Please enter 1, 2, 3, 4, 5, 6, 7, or 8: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("1")) {
                reportBillingHistory(console);
            } else if (userAction.equals("2")) {
                // Usage Status Report
                System.out.println("\nCurrent Usage Status for all Hospitals Report");
                System.out.println();
                Wolfhospital.reportUsageStatus(this.stmt);
            } else if (userAction.equals("3")) {
                reportPatientsPerMonth(console);
            } else if (userAction.equals("4")) {
                // Hospital Usage Percentage
                String hID = "";
                System.out.println("\nHospital Usage Percentage Report");
                System.out.print("\nEnter the Hospital ID: ");
                hID = console.nextLine();
                System.out.println();
                Wolfhospital.reportHospitalPercentage(this.stmt, hID);
            } else if (userAction.equals("5")) {
                // Information about all Doctors a Patient is Seeing
                String pID = "";
                System.out.println("\nDoctors a Patient is Currently Seeing Report");
                System.out.print("\nEnter the Patient ID: ");
                pID = console.nextLine();
                System.out.println();
                Wolfhospital.reportDoctorByPatient(this.stmt, pID);
            } else if (userAction.equals("6")) {
                // Information on Hospitals Grouped by Specialty
                System.out.println("\nHospital Information grouped by Specialization Report");
                System.out.println();
                Wolfhospital.reportAllHospitalSpeciality(this.stmt);
            } else if (userAction.equals("7")) {
                break;
            } else {
                System.exit(1);
            }
            reportsHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }
    }

    /**
     * Header for Patient operations menu.
     * 
     */
    public void patientOperationsMenuHeader() {
        System.out.println("\n       Patient Operations Sub-Menu\n");
        System.out.println("Enter the corresponding number for the desired option:");
        System.out.println("0. Show Patient table");
        System.out.println("1. Create a new Patient");
        System.out.println("2. Update an existing Patient");
        System.out.println("3. Delete a Patient");
        System.out.println("4. Transfer a patient to another hospital");
        System.out.println("5. Return to previous menu");
        System.out.println("6. Quit the program.");
        System.out.println();
    }

    /**
     * Header for Hospital operations menu.
     * 
     */
    public void hospitalOperationsMenuHeader() {
        System.out.println("\n       Hospital Operations Sub-Menu\n");
        System.out.println("Enter the corresponding number for the desired option:");
        System.out.println("0. Show Hospital table");
        System.out.println("1. Create a new Hospital");
        System.out.println("2. Update an existing Hospital");
        System.out.println("3. Delete a Hospital");
        System.out.println("4. Return to previous menu");
        System.out.println("5. Quit the program.");
        System.out.println();
    }

    /**
     * Header for Staff Member operations menu.
     * 
     */
    public void staffOperationsMenuHeader() {
        System.out.println("\n       Staff Operations Sub-Menu\n");
        System.out.println("Enter the corresponding number for the desired option:");
        System.out.println("0. Show Staff table");
        System.out.println("1. Create a new Staff Member");
        System.out.println("2. Update an existing Staff Member");
        System.out.println("3. Delete a Staff Member");
        System.out.println("4. Return to previous menu");
        System.out.println("5. Quit the program.");
        System.out.println();
    }

    /**
     * Header for Beds operations menu.
     * 
     */
    public void bedOperationsMenuHeader() {
        System.out.println("\n         Beds Operations Sub-Menu\n");
        System.out.println("Enter the corresponding number for the desired option:");
        System.out.println("0. Show Beds table");
        System.out.println("1. Check if a bed is available by bed number");
        System.out.println("2. Check available beds in a hospital with appropriate specialty");
        System.out.println("3. Reserve beds in hospitals");
        System.out.println("4. Release beds in hospitals");
        System.out.println("5. Return to previous menu");
        System.out.println("6. Quit the program.");
        System.out.println();
    }

    /**
     * Logic for the Patient operations menu. Makes the pertinent calls to the
     * backend class that has functions for performing operations related to
     * Patients.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void patientOperationsMenu(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        patientOperationsMenuHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("5")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4") && !userAction.equals("5")
                    && !userAction.equals("6")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, 4, 5, or 6: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                Wolfhospital.showPatient(this.stmt);
            } else if (userAction.equals("1")) {
                createPatient(console);
            } else if (userAction.equals("2")) {
                updatePatient(console);
            } else if (userAction.equals("3")) {
                // Delete Patient Record
                String pID = "";
                System.out.print("Enter the ID of Patient to be deleted: ");
                pID = console.nextLine();
                System.out.println();
                Wolfhospital.deletePatient(this.stmt, pID);
            } else if (userAction.equals("4")) {
                transferPatient(console);
            } else if (userAction.equals("5")) {
                break;
            } else {
                System.exit(1);
            }
            patientOperationsMenuHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }
    }

    /**
     * Logic for the Hospital operations menu. Makes the pertinent calls to the
     * backend class that has functions for performing operations related to
     * Hospitals.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void hospitalOperationsMenu(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        hospitalOperationsMenuHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("4")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4") && !userAction.equals("5")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, 4, or 5: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                Wolfhospital.showHospital(this.stmt);
            } else if (userAction.equals("1")) {
                createHospital(console);
            } else if (userAction.equals("2")) {
                updateHospital(console);
            } else if (userAction.equals("3")) {
                // Delete Hospital Record
                String hID = "";
                System.out.print("Enter the ID of Hospital to be deleted: ");
                hID = console.nextLine();
                System.out.println();
                Wolfhospital.deleteHospital(this.stmt, hID);
            } else if (userAction.equals("4")) {
                break;
            } else {
                System.exit(1);
            }
            hospitalOperationsMenuHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }
    }

    /**
     * Logic for the Staff Member operations menu. Makes the pertinent calls to the
     * backend class that has functions for performing operations related to Staff
     * Members.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void staffOperationsMenu(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        staffOperationsMenuHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("4")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4") && !userAction.equals("5")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, 4, or 5: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                Wolfhospital.showStaff(this.stmt);
            } else if (userAction.equals("1")) {
                createStaffMember(console);
            } else if (userAction.equals("2")) {
                updateStaffMember(console);
            } else if (userAction.equals("3")) {
                // Delete Staff Member Record
                String staffID = "";
                System.out.print("Enter the ID of Staff Member to be deleted: ");
                staffID = console.nextLine();
                System.out.println();
                Wolfhospital.deleteHospital(this.stmt, staffID);
            } else if (userAction.equals("4")) {
                break;
            } else {
                System.exit(1);
            }
            staffOperationsMenuHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }
    }

    /**
     * Logic for the Beds operations menu. Makes the pertinent calls to the backend
     * class that has functions for performing operations related to Beds.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void bedOperationsMenu(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        bedOperationsMenuHeader();
        System.out.print("Enter command # : ");
        userAction = console.nextLine();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("5")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4") && !userAction.equals("5")
                    && !userAction.equals("6")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, 4, 5, or 6: ");
                userAction = console.nextLine();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                Wolfhospital.showBeds(this.stmt);
            } else if (userAction.equals("1")) {
                userCheckBeds(console);
            } else if (userAction.equals("2")) {
                checkBedsInHospitalPerSpecialty(console);
            } else if (userAction.equals("3")) {
                userAssignPatientToBed(console);
            } else if (userAction.equals("4")) {
                releaseBed(console);
            } else if (userAction.equals("5")) {
                break;
            } else {
                System.exit(1);
            }
            bedOperationsMenuHeader();
            System.out.print("Enter command # : ");
            userAction = console.nextLine();
            userAction = userAction.toLowerCase();
        }
    }

    /**
     * Asks the user for a bed number that is to be checked for availabilty. Calls
     * backend method that handles this operation given the bed number.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void userCheckBeds(Scanner console) {
        String bID = "";

        System.out.println("\nCheck Bed Availability by Bed Number ");
        System.out.print("\nEnter the Bed number: ");
        bID = console.nextLine();
        bID = bID.toLowerCase();

        System.out.println();
        Wolfhospital.checkBeds(this.stmt, bID);

    }

    /**
     * Reserve beds in a hospital, which means assigning a patient to a bed.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void userAssignPatientToBed(Scanner console) {
        String bID = "";
        String pID = "";

        System.out.println("\nAssign a Patient to a Bed (Reserve) ");

        System.out.print("\nEnter the Bed number: ");
        bID = console.nextLine();

        System.out.print("Enter the patient ID: ");
        pID = console.nextLine();

        System.out.println();
        Wolfhospital.assignPatientToBed(this.stmt, pID, bID);
    }

    /**
     * Release beds in a hospital.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void releaseBed(Scanner console) {
        String bID = "";

        System.out.println("\nRelease a Bed given its number");

        System.out.print("\nEnter the Bed number: ");
        bID = console.nextLine();

        System.out.println();
        Wolfhospital.releaseBed(this.stmt, bID);
    }

    /**
     * User creates a checkIn.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void createCheckIn(Scanner console) {
        String pID = "";
        String hID = "";
        String bID = "";
        String startDate = "";
        String respDoctor = "";
        String currentDiagnosis = "";
        String registrationFee = "";

        System.out.println("\nCreate a new CheckIn");

        System.out.print("\nEnter the Patient ID: ");
        pID = console.nextLine();
        System.out.print("Enter the Hospital ID: ");
        hID = console.nextLine();
        System.out.print("Enter the Bed number: ");
        bID = console.nextLine();
        System.out.print("Enter the Start Date (yyyy-mm-dd): ");
        startDate = console.nextLine();
        System.out.print("Enter the Responsible Doctor ID: ");
        respDoctor = console.nextLine();
        System.out.print("Enter the Current Diagnosis: ");
        currentDiagnosis = console.nextLine();
        System.out.print("Enter the Registration Fee: ");
        registrationFee = console.nextLine();

        System.out.println();
        Wolfhospital.createCheckIn(this.stmt, pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee);
    }

    /**
     * User Checks Out a patient.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void patientCheckOut(Scanner console) {
        String cID = "";
        String endDate = "";

        System.out.println("\nCheck Out a Patient");

        System.out.print("\nEnter the CheckIn ID: ");
        cID = console.nextLine();
        System.out.print("Enter the check out date (yyyy-mm-dd): ");
        endDate = console.nextLine();

        System.out.println();
        Wolfhospital.checkOut(this.stmt, cID, endDate);
    }

    /**
     * Transfer a Patient from one hospital to another.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void transferPatient(Scanner console) {
        String cID = "";
        String pID = "";
        String hID = "";
        String bID = "";
        String endDate = "";
        String respDoctor = "";
        String currentDiagnosis = "";
        String registrationFee = "";

        System.out.println("\nTransfer a Patient from one Hospital to another");

        System.out.print("\nEnter the CheckIn ID: ");
        cID = console.nextLine();
        System.out.print("Enter the Patient ID: ");
        pID = console.nextLine();
        System.out.print("Enter the Hospital ID of the hospital to be transferred to: ");
        hID = console.nextLine();
        System.out.print("Enter the Bed number of the bed to be transferred to: ");
        bID = console.nextLine();
        System.out.print("Enter the check out date (yyyy-mm-dd): ");
        endDate = console.nextLine();
        System.out.print("Enter the Responsible Doctor ID: ");
        respDoctor = console.nextLine();
        System.out.print("Enter the Current Diagnosis: ");
        currentDiagnosis = console.nextLine();
        System.out.print("Enter the Registration Fee: ");
        registrationFee = console.nextLine();

        System.out.println();
        try {
            Wolfhospital.transferPatient(this.conn, cID, pID, hID, bID, endDate, respDoctor, currentDiagnosis,
                    registrationFee);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String answer = "";
        System.out.print("Would you like to see the updated CheckIn table? (yes/no): ");
        answer = console.nextLine();
        answer.toLowerCase();
        System.out.println();

        if (answer.equals("y") || answer.equals("ye") || answer.equals("yes")) {
            Wolfhospital.showCheckInOut(this.stmt);
        }
    }

    /**
     * User creates a Medical Record.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void createMedicalRecord(Scanner console) {
        String mID = "";
        String cID = "";
        String prescriptions = "";
        String diagnosisDetails = "";
        String treatment = "";
        String test = "";
        String result = "";
        String consultationfee = "";
        String testfee = "";
        String treatmentfee = "";

        System.out.println("\nCreate a new Medical Record");

        System.out.print("\nEnter the Medical Record ID: ");
        mID = console.nextLine();
        System.out.print("Enter the CheckIn ID to be tied to this Medical Record: ");
        cID = console.nextLine();
        System.out.print("Enter Diagnosis Details: ");
        diagnosisDetails = console.nextLine();
        System.out.print("Enter Prescriptions: ");
        prescriptions = console.nextLine();
        System.out.print("Enter Test: ");
        test = console.nextLine();
        System.out.print("Enter Result: ");
        result = console.nextLine();
        System.out.print("Enter Treatment: ");
        treatment = console.nextLine();
        System.out.print("Enter Test Fee: ");
        treatmentfee = console.nextLine();
        System.out.print("Enter Treatment Fee: ");
        treatmentfee = console.nextLine();
        System.out.print("Enter Consultation Fee: ");
        consultationfee = console.nextLine();

        System.out.println();
        Wolfhospital.enterMedicalRecords(this.stmt, mID, cID, prescriptions, diagnosisDetails, treatment, test, result,
                consultationfee, testfee, treatmentfee);
    }

    /**
     * User updates an existing Medical Record.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void updateMedicalRecord(Scanner console) {
        String mID = "";
        String prescriptions = "";
        String diagnosisDetails = "";
        String treatment = "";
        String test = "";
        String result = "";
        String consultationfee = "";
        String testfee = "";
        String treatmentfee = "";

        System.out.println("\nUpdate Medical Record");

        System.out.print("\nEnter the ID of the Medical Record to be updated: ");
        mID = console.nextLine();
        System.out.print("Enter Diagnosis Details: ");
        diagnosisDetails = console.nextLine();
        System.out.print("Enter Prescriptions: ");
        prescriptions = console.nextLine();
        System.out.print("Enter Test: ");
        test = console.nextLine();
        System.out.print("Enter Result: ");
        result = console.nextLine();
        System.out.print("Enter Treatment: ");
        treatment = console.nextLine();
        System.out.print("Enter Test Fee: ");
        treatmentfee = console.nextLine();
        System.out.print("Enter Treatment Fee: ");
        treatmentfee = console.nextLine();
        System.out.print("Enter Consultation Fee: ");
        consultationfee = console.nextLine();

        System.out.println();
        Wolfhospital.updateMedicalRecords(this.stmt, mID, prescriptions, diagnosisDetails, treatment, test, result,
                consultationfee, testfee, treatmentfee);
    }

    /**
     * User creates a Billing Account.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void createBillingAccount(Scanner console) {
        String hID = "";
        String pID = "";
        String payerSSN = "";
        String billingAddress = "";
        String paymentInfo = "";
        String visitDate = "";
        String registrationfee = "";
        String accommodationfee = "";
        String specializationFee = "";
        String consultationFee = "";
        String testFee = "";
        String treatmentFee = "";

        System.out.println("\nCreate a new Billing Account");

        System.out.print("\nEnter the Hospital ID where medical care was provided: ");
        hID = console.nextLine();
        System.out.print("Enter the Patient ID: ");
        pID = console.nextLine();
        System.out.print("Enter the Payer Social Security Number (SSN): ");
        payerSSN = console.nextLine();
        System.out.print("Enter the Visit Date: ");
        visitDate = console.nextLine();
        System.out.print("Enter the Billing Address: ");
        billingAddress = console.nextLine();
        System.out.print("Enter the Payment Information: ");
        paymentInfo = console.nextLine();
        System.out.print("Enter the Registration Fee: ");
        registrationfee = console.nextLine();
        System.out.print("Enter the Accommodation Fee: ");
        accommodationfee = console.nextLine();
        System.out.print("Enter the Charges per day for Specialization: ");
        specializationFee = console.nextLine();
        System.out.print("Enter the Consultation Fee: ");
        consultationFee = console.nextLine();
        System.out.print("Enter the Test Fee: ");
        testFee = console.nextLine();
        System.out.print("Enter the Treatment Fee: ");
        treatmentFee = console.nextLine();

        if (payerSSN.isEmpty()) { // if no payerSSN is specified, pass in null
            payerSSN = null;
        }

        System.out.println();
        try {
            Wolfhospital.createBillingAccount(this.conn, hID, pID, payerSSN, billingAddress, paymentInfo,
                    registrationfee, accommodationfee, consultationFee, testFee, treatmentFee, specializationFee,
                    visitDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * User updates an existing Billing Account.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void updateBillingAccount(Scanner console) {
        String baID = "";
        String pID = "";
        String payerSSN = "";
        String billingAddress = "";
        String paymentInfo = "";
        String visitDate = "";
        String registrationfee = "";
        String accommodationfee = "";
        String specializationFee = "";
        String consultationFee = "";
        String testFee = "";
        String treatmentFee = "";

        System.out.println("\nUpdate Billing Account");

        System.out.print("\nEnter ID of Billing Account to be updated: ");
        baID = console.nextLine();
        System.out.print("Enter the Patient ID: ");
        pID = console.nextLine();
        System.out.print("Enter the Payer Social Security Number (SSN): ");
        payerSSN = console.nextLine();
        System.out.print("Enter the Visit Date: ");
        visitDate = console.nextLine();
        System.out.print("Enter the Billing Address: ");
        billingAddress = console.nextLine();
        System.out.print("Enter the Payment Information: ");
        paymentInfo = console.nextLine();
        System.out.print("Enter the Registration Fee: ");
        registrationfee = console.nextLine();
        System.out.print("Enter the Accommodation Fee: ");
        accommodationfee = console.nextLine();
        System.out.print("Enter the Charges per day for Specialization: ");
        specializationFee = console.nextLine();
        System.out.print("Enter the Consultation Fee: ");
        consultationFee = console.nextLine();
        System.out.print("Enter the Test Fee: ");
        testFee = console.nextLine();
        System.out.print("Enter the Treatment Fee: ");
        treatmentFee = console.nextLine();

        System.out.println();
        Wolfhospital.updateBillingAccount(this.stmt, baID, pID, payerSSN, billingAddress, paymentInfo, registrationfee,
                accommodationfee, consultationFee, testFee, treatmentFee, specializationFee, visitDate);
    }

    /**
     * User creates a Patient record.
     * 
     * @param console Scanner object to be used to obtain input from user String
     * 
     */
    public void createPatient(Scanner console) {
        String pID = "";
        String SSN = "";
        String pName = "";
        String dob = "";
        String gender = "";
        String patientAge = "";
        String patientPhone = "";
        String patientAddress = "";
        String status = "";

        System.out.println("\nCreate a new Patient record");

        System.out.print("\nEnter the Patient ID: ");
        pID = console.nextLine();
        System.out.print("Enter the Social Security Number (SSN): ");
        SSN = console.nextLine();
        System.out.print("Enter Name: ");
        pName = console.nextLine();
        System.out.print("Enter Date of Birth: ");
        dob = console.nextLine();
        System.out.print("Enter Gender: ");
        gender = console.nextLine();
        System.out.print("Enter Age: ");
        patientAge = console.nextLine();
        System.out.print("Enter Phone: ");
        patientPhone = console.nextLine();
        System.out.print("Enter Address: ");
        patientAddress = console.nextLine();
        System.out.print("Enter Patient Status: ");
        status = console.nextLine();

        System.out.println();

        if (SSN.isEmpty()) { // if no SSN is specified, pass in null
            SSN = null;
        }

        Wolfhospital.enterPatient(this.stmt, pID, SSN, pName, dob, gender, patientAge, patientPhone, patientAddress,
                status);

    }

    /**
     * User updates a Patient record.
     * 
     * @param console Scanner object to be used to obtain input from user String
     * 
     */
    public void updatePatient(Scanner console) {
        String pID = "";
        String SSN = "";
        String pName = "";
        String dob = "";
        String gender = "";
        String patientAge = "";
        String patientPhone = "";
        String patientAddress = "";
        String status = "";

        System.out.println("\nUpdate Patient record");

        System.out.print("\nEnter ID of Patient to be updated: ");
        pID = console.nextLine();
        System.out.print("Enter the Social Security Number (SSN): ");
        SSN = console.nextLine();
        System.out.print("Enter Name: ");
        pName = console.nextLine();
        System.out.print("Enter Date of Birth: ");
        dob = console.nextLine();
        System.out.print("Enter Gender: ");
        gender = console.nextLine();
        System.out.print("Enter Age: ");
        patientAge = console.nextLine();
        System.out.print("Enter Phone: ");
        patientPhone = console.nextLine();
        System.out.print("Enter Address: ");
        patientAddress = console.nextLine();
        System.out.print("Enter Patient Status: ");
        status = console.nextLine();

        System.out.println();

        if (SSN.isEmpty()) { // if no SSN is specified, pass in null
            SSN = null;
        }

        Wolfhospital.updatePatient(this.stmt, pID, SSN, pName, dob, gender, patientAge, patientPhone, patientAddress,
                status);

    }

    /**
     * User creates a Hospital record.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void createHospital(Scanner console) {
        String hID = "";
        String aID = "";
        String hAddress = "";
        String hPhone = "";
        String s1 = "";
        String s1Cost = "";
        String s2 = "";
        String s2Cost = "";
        String capacity = "";

        System.out.println("\nCreate a new Hospital record");

        System.out.print("\nEnter Hospital ID: ");
        hID = console.nextLine();
        System.out.print("Enter Administrator ID: ");
        aID = console.nextLine();
        System.out.print("Enter Address: ");
        hAddress = console.nextLine();
        System.out.print("Enter Phone: ");
        hPhone = console.nextLine();
        System.out.print("Enter Specialization 1: ");
        s1 = console.nextLine();
        System.out.print("Enter Charges per day for Specialization 1: ");
        s1Cost = console.nextLine();
        System.out.print("Enter Specialization 2: ");
        s2 = console.nextLine();
        System.out.print("Enter Charges per day for Specialization 2: ");
        s2Cost = console.nextLine();
        System.out.print("Enter Hospital Capacity: ");
        capacity = console.nextLine();

        System.out.println();
        Wolfhospital.enterHospital(this.stmt, hID, aID, hAddress, hPhone, s1, s1Cost, s2, s2Cost, capacity);

    }

    /**
     * User updates a Hospital record.
     * 
     * @param console Scanner object to be used to obtain input from user String
     * 
     */
    public void updateHospital(Scanner console) {
        String hID = "";
        String hAddress = "";
        String hPhone = "";
        String s1 = "";
        String s1Cost = "";
        String s2 = "";
        String s2Cost = "";
        String capacity = "";

        System.out.println("\nUpdate Hospital record");

        System.out.print("\nEnter ID of Hospital to be updated: ");
        hID = console.nextLine();
        System.out.print("Enter Address: ");
        hAddress = console.nextLine();
        System.out.print("Enter Phone: ");
        hPhone = console.nextLine();
        System.out.print("Enter Specialization 1: ");
        s1 = console.nextLine();
        System.out.print("Enter Charges per day for Specialization 1: ");
        s1Cost = console.nextLine();
        System.out.print("Enter Specialization 2: ");
        s2 = console.nextLine();
        System.out.print("Enter Charges per day for Specialization 2: ");
        s2Cost = console.nextLine();
        System.out.print("Enter Hospital Capacity: ");
        capacity = console.nextLine();

        System.out.println();
        Wolfhospital.updateHospital(this.stmt, hID, hAddress, hPhone, s1, s1Cost, s2, s2Cost, capacity);

    }

    /**
     * User creates a Staff Member record.
     * 
     * @param console Scanner object to be used to obtain input from user String
     * 
     */
    public void createStaffMember(Scanner console) {
        String staffID = "";
        String hID = "";
        String staffName = "";
        String homeAddress = "";
        String officeAddress = "";
        String sgender = "";
        String age = "";
        String jobTitle = "";
        String department = "";
        String specPosition = "";
        String staffPhone = "";
        String email = "";

        System.out.println("\nCreate a new Staff Member record");

        System.out.print("\nEnter Staff Member ID: ");
        staffID = console.nextLine();
        System.out.print("Enter Hospital ID: ");
        hID = console.nextLine();
        System.out.print("Enter Name: ");
        staffName = console.nextLine();
        System.out.print("Enter Home Address: ");
        homeAddress = console.nextLine();
        System.out.print("Enter Office Address: ");
        officeAddress = console.nextLine();
        System.out.print("Enter Gender: ");
        sgender = console.nextLine();
        System.out.print("Enter Age: ");
        age = console.nextLine();
        System.out.print("Enter Job Title: ");
        jobTitle = console.nextLine();
        System.out.print("Enter Department: ");
        department = console.nextLine();
        System.out.print("Enter Professional Title: ");
        specPosition = console.nextLine();
        System.out.print("Enter Phone: ");
        staffPhone = console.nextLine();
        System.out.print("Enter Email: ");
        email = console.nextLine();

        System.out.println();
        Wolfhospital.enterStaff(this.stmt, staffID, hID, staffName, homeAddress, officeAddress, sgender, age, jobTitle,
                department, specPosition, staffPhone, email);

    }

    /**
     * User updates a Staff Member record.
     * 
     * @param console Scanner object to be used to obtain input from user String
     * 
     */
    public void updateStaffMember(Scanner console) {
        String staffID = "";
        String staffName = "";
        String homeAddress = "";
        String officeAddress = "";
        String sgender = "";
        String age = "";
        String jobTitle = "";
        String department = "";
        String specPosition = "";
        String staffPhone = "";
        String email = "";

        System.out.println("\nUpdate Staff Member record");

        System.out.print("\nEnter ID of Staff Member to be updated: ");
        staffID = console.nextLine();
        System.out.print("Enter Name: ");
        staffName = console.nextLine();
        System.out.print("Enter Home Address: ");
        homeAddress = console.nextLine();
        System.out.print("Enter Office Address: ");
        officeAddress = console.nextLine();
        System.out.print("Enter Gender: ");
        sgender = console.nextLine();
        System.out.print("Enter Age: ");
        age = console.nextLine();
        System.out.print("Enter Job Title: ");
        jobTitle = console.nextLine();
        System.out.print("Enter Department: ");
        department = console.nextLine();
        System.out.print("Enter Professional Title: ");
        specPosition = console.nextLine();
        System.out.print("Enter Phone: ");
        staffPhone = console.nextLine();
        System.out.print("Enter Email: ");
        email = console.nextLine();

        System.out.println();
        Wolfhospital.updateStaff(this.stmt, staffID, staffName, homeAddress, officeAddress, sgender, age, jobTitle,
                department, specPosition, staffPhone, email);

    }

    /**
     * Billing History report for a Patient given a time period.
     * 
     * @param console Scanner object to be used to obtain input from user String
     * 
     */
    public void reportBillingHistory(Scanner console) {
        String startDate = "";
        String endDate = "";
        String pID = "";

        System.out.println("\nBilling History Report");

        System.out.print("\nEnter the start date of the range desired (yyyy-mm-dd): ");
        startDate = console.nextLine();
        System.out.print("Enter the end date of the range desired (yyyy-mm-dd): ");
        endDate = console.nextLine();
        System.out.print("Enter the Patient ID: ");
        pID = console.nextLine();

        System.out.println();
        Wolfhospital.reportBillingHistory(this.stmt, startDate, endDate, pID);
    }

    /**
     * Report the number of Patients per Month.
     * 
     * @param console Scanner object to be used to obtain input from user String
     * 
     */
    public void reportPatientsPerMonth(Scanner console) {
        String hID = "";
        String month = "";
        String year = "";

        System.out.println("\nNumber of Patients per Month Report");

        System.out.print("\nEnter the Hospital ID: ");
        hID = console.nextLine();
        System.out.print("Enter desired month (mm): ");
        month = console.nextLine();
        System.out.print("Enter desired year (yyyy): ");
        year = console.nextLine();

        System.out.println();
        Wolfhospital.reportPatientsPerMonth(this.stmt, hID, month, year);
    }

    /**
     * Check available beds in a hospital with appropriate specialty.
     * 
     * @param console Scanner object to be used to obtain input from user
     * 
     */
    public void checkBedsInHospitalPerSpecialty(Scanner console) {
        String hID = "";
        String spec = "";

        System.out.println("\nNumber of Patients per Month Report");

        System.out.print("\nEnter the Hospital ID: ");
        hID = console.nextLine();
        System.out.print("Enter desired Specialization to check beds for: ");
        spec = console.nextLine();

        System.out.println();
        Wolfhospital.checkBedsBySpeciality(this.stmt, hID, spec);
    }

}
