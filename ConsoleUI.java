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
            BackendCopy.createInitialTables(this.stmt);
            BackendCopy.populateDemoTables(this.stmt);

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
            BackendCopy.close(this.stmt);
            BackendCopy.close(this.conn);
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
        System.out.println("2. Update an existing CheckIn");
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
        userAction = console.next();
        userAction = userAction.toLowerCase();

        while (!userAction.equals("6")) {
            while (!userAction.equals("1") && !userAction.equals("2") && !userAction.equals("3")
                    && !userAction.equals("4") && !userAction.equals("5") && !userAction.equals("6")) {
                System.out.print("Error! Invalid command. Please enter 1, 2, 3, 4, 5, or 6: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("1")) {
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
            userAction = console.next();
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("5")) {
            while (!userAction.equals("1") && !userAction.equals("2") && !userAction.equals("3")
                    && !userAction.equals("4") && !userAction.equals("5") && !userAction.equals("6")) {
                System.out.print("Error! Invalid command. Please enter 1, 2, 3, 4, 5, or 6: ");
                userAction = console.next();
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
            userAction = console.next();
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("3")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, or 4: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                // BackendCopy.printBeds()
                System.out.println("\nPRINT CHECKIN TABLE\n");
            } else if (userAction.equals("1")) {
                // userEnterNewCheckIn()
                System.out.println("\nENTER NEW CHECKIN\n");
            } else if (userAction.equals("2")) {
                // userUpdateCheckIn()
                System.out.println("\nUPDATE CHECK IN\n");
            } else if (userAction.equals("3")) {
                break;
            } else {
                System.exit(1);
            }
            checkInHeader();
            System.out.print("Enter command # : ");
            userAction = console.next();
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("3")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, or 4: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                // BackendCopy.printMedicalRecords()
                System.out.println("\nPRINT MEDICAL RECORDS TABLE\n");
            }
            if (userAction.equals("1")) {
                // userEnterNewMedicalRecord()
                System.out.println("\nENTER NEW MEDICAL RECORD\n");
            } else if (userAction.equals("2")) {
                // userUpdateMedicalRecord()
                System.out.println("\nUPDATE MEDICAL RECORD\n");
            } else if (userAction.equals("3")) {
                break;
            } else {
                System.exit(1);
            }
            medicalRecordsHeader();
            System.out.print("Enter command # : ");
            userAction = console.next();
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("3")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, or 4: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                // BackendCopy.printBillingAccounts()
                System.out.println("\nPRINT BILLING ACCOUNTS\n");
            } else if (userAction.equals("1")) {
                // userEnterNewBillingAccount()
                System.out.println("\nCREATE NEW BILLING ACCOUNT\n");
            } else if (userAction.equals("2")) {
                // userUpdateBillingAccount()
                System.out.println("\nUPDATE BILLING ACCOUNT\n");
            } else if (userAction.equals("3")) {
                break;
            } else {
                System.exit(1);
            }
            billingAccountsHeader();
            System.out.print("Enter command # : ");
            userAction = console.next();
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("7")) {
            while (!userAction.equals("1") && !userAction.equals("2") && !userAction.equals("3")
                    && !userAction.equals("4") && !userAction.equals("5") && !userAction.equals("6")
                    && !userAction.equals("7") && !userAction.equals("8")) {
                System.out.print("Error! Invalid command. Please enter 1, 2, 3, 4, 5, 6, 7, or 8: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("1")) {
                // generateBillingHistoryForPatientGivenTime()
                System.out.println("\nBILLING HISTORY REPORT FOR A PATIENT GIVEN TIMEFRAME\n");
            } else if (userAction.equals("2")) {
                // generateUsageStatusAllHospitals()
                System.out.println("\nUSAGE STATUS FOR ALL HOSPITALS\n");
            } else if (userAction.equals("3")) {
                // generateNumberOfPatientsPerMonth()
                System.out.println("\nNUMBER OF PATIENTS PER MONTH\n");
            } else if (userAction.equals("4")) {
                // generateHospitalUsagePercentage()
                System.out.println("\nHOSPITAL USAGE PERCENTAGE\n");
            } else if (userAction.equals("5")) {
                // generateAllPatientDoctorInfo()
                System.out.println("\nINFORMATION ON ALL DOCTORS A PATIENT IS CURRENTLY SEEING\n");
            } else if (userAction.equals("6")) {
                // generateInfoOnHospitalsGroupedBySpecialty()
                System.out.println("\nINFORMATION ON HOSPITALS GROUPED BY SPECIALTY\n");
            } else if (userAction.equals("7")) {
                break;
            } else {
                System.exit(1);
            }
            reportsHeader();
            System.out.print("Enter command # : ");
            userAction = console.next();
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
        System.out.println("5. Assign patients to a hospital if beds are available");
        System.out.println("6. Return to previous menu");
        System.out.println("7. Quit the program.");
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("6")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4") && !userAction.equals("5")
                    && !userAction.equals("6") && !userAction.equals("7")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, 4, 5, 6, or 7: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                // BackendCopy.printPatients()
                System.out.println("\nPRINT PATIENT TABLE\n");
            } else if (userAction.equals("1")) {
                // userEnterNewPatient()
                System.out.println("\nENTER NEW PATIENT\n");
            } else if (userAction.equals("2")) {
                // userUpdatePatient()
                System.out.println("\nUPDATE EXISTING PATIENT\n");
            } else if (userAction.equals("3")) {
                // userDeletePatient()
                System.out.println("\nDELETE EXISTING PATIENT\n");
            } else if (userAction.equals("4")) {
                // userTransferPatient()
                System.out.println("\nTRANSFER EXISTING PATIENT\n");
            } else if (userAction.equals("5")) {
                // userAssignPatientToHospital()
                System.out.println("\nASSIGN PATIENT TO HOSPITAL\n");
            } else if (userAction.equals("6")) {
                break;
            } else {
                System.exit(1);
            }
            patientOperationsMenuHeader();
            System.out.print("Enter command # : ");
            userAction = console.next();
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("4")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4") && !userAction.equals("5")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, 4, or 5: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                // BackendCopy.printHospitals()
                System.out.println("\nPRINT HOSPITAL TABLE\n");
            } else if (userAction.equals("1")) {
                // userEnterNewHospital()
                System.out.println("\nENTER NEW HOSPITAL\n");
            } else if (userAction.equals("2")) {
                // userUpdateHospital()
                System.out.println("\nUPDATE EXISTING HOSPITAL\n");
            } else if (userAction.equals("3")) {
                // userDeleteHospital()
                System.out.println("\nDELETE EXISTING HOSPITAL\n");
            } else if (userAction.equals("4")) {
                break;
            } else {
                System.exit(1);
            }
            hospitalOperationsMenuHeader();
            System.out.print("Enter command # : ");
            userAction = console.next();
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("4")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4") && !userAction.equals("5")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, 4, or 5: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                // BackendCopy.printStaff()
                System.out.println("\nPRINT STAFF MEMBER\n");
            }
            if (userAction.equals("1")) {
                // userEnterNewStaff()
                System.out.println("\nENTER NEW STAFF\n");
            } else if (userAction.equals("2")) {
                // userUpdateStaff()
                System.out.println("\nUPDATE EXISTING STAFF\n");
            } else if (userAction.equals("3")) {
                // userDeleteStaff()
                System.out.println("\nDELETE EXISTING STAFF\n");
            } else if (userAction.equals("4")) {
                break;
            } else {
                System.exit(1);
            }
            staffOperationsMenuHeader();
            System.out.print("Enter command # : ");
            userAction = console.next();
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
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("5")) {
            while (!userAction.equals("0") && !userAction.equals("1") && !userAction.equals("2")
                    && !userAction.equals("3") && !userAction.equals("4") && !userAction.equals("5")
                    && !userAction.equals("6")) {
                System.out.print("Error! Invalid command. Please enter 0, 1, 2, 3, 4, 5, or 6: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("0")) {
                // BackendCopy.printBeds()
                System.out.println("\nPRINT BEDS TABLE\n");
            } else if (userAction.equals("1")) {
                userCheckBeds(console);
            } else if (userAction.equals("2")) {
                // userCheckBedsInHospitalBySpecialty(console);
                System.out.println("\nCHECK BEDS IN A HOSPITAL WITH APPROPRIATE SPECIALTY\n");
            } else if (userAction.equals("3")) {
                // userReserveBedsInHospital()
                System.out.println("\nRESERVE BEDS IN A HOSPITAL\n");
            } else if (userAction.equals("4")) {
                // userReleaseBedsInHospital()
                System.out.println("\nRELEASE BEDS IN A HOSPITAL\n");
            } else if (userAction.equals("5")) {
                break;
            } else {
                System.exit(1);
            }
            bedOperationsMenuHeader();
            System.out.print("Enter command # : ");
            userAction = console.next();
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
        bID = console.next();
        bID = bID.toLowerCase();

        System.out.println();
        BackendCopy.checkBeds(this.conn, bID);

    }

}
