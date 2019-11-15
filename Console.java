import java.util.*;

/**
 * Frontend code for the WolfHospital system.
 * 
 */
public class Console {

    /**
     * Starts the program.
     * 
     * @param args command line arguments
     *
     */
    public static void main(String[] args) {
        /** Scanner that is going to read input in from the user. */
        Scanner console = new Scanner(System.in);
        System.out.println();
        System.out.println("      Welcome to the Wolfpack Hospital System!");

        topLevelUserInterface(console);

    }

    /**
     * Welcome message for the user.
     * 
     */
    public static void printMainHeader() {
        System.out.println("\n                 Main Menu:\n");
        System.out.println("Enter the corresponding command for the operation Task you wish to perform:");
        System.out.println("I for Information Processing, C for CheckIn / CheckOut,");
        System.out
                .println("MM for Maintaining Medical Records, MB for Maintaining Billing Accounts, or R for Reports.");
        System.out.println("To quit the program, Enter Q.");
        System.out.println();
    }

    /**
     * Header for the Information Processing sub-menu.
     * 
     */
    public static void printInfoProcessingHeader() {
        System.out.println("\n       Information Processing Sub-Menu:\n");
        System.out.println("Enter P for Patients, H for Hospitals, S for Staff, B for Beds.");
        System.out.println("Enter R to return to main menu or Q to quit the program.");
        System.out.println();
    }

    /**
     * Header for the CheckIn sub-menu.
     * 
     */
    public static void checkInHeader() {
        System.out.println("\n       CheckIn / CheckOut Sub-Menu:\n");
        System.out.println("Enter E for creating a new CheckIn or");
        System.out.println("U for updating an existing CheckIn.");
        System.out.println("Enter R to return to main menu or Q to quit the program.");
        System.out.println();
    }

    /**
     * Header for the Maintaining Medical Records sub-menu.
     * 
     */
    public static void medicalRecordsHeader() {
        System.out.println("\n      Maintaining Medical Records Sub-Menu:\n");
        System.out.println("Enter E for entering a new Medical Record or");
        System.out.println("U for updating an existing Medical Record.");
        System.out.println("Enter R to return to main menu or Q to quit the program.");
        System.out.println();
    }

    /**
     * Header for the Maintaining Billing Accounts sub-menu.
     * 
     */
    public static void billingAccountsHeader() {
        System.out.println("\n      Maintaining Billing Accounts Sub-Menu:\n");
        System.out.println("Enter E for entering a new Billing Account or");
        System.out.println("U for updating an existing Billing Account.");
        System.out.println("Enter R to return to main menu or Q to quit the program.");
        System.out.println();
    }

    /**
     * Header for the Reports sub-menu.
     * 
     */
    public static void reportsHeader() {
        System.out.println("\n       Generate Reports Sub-Menu:\n");
        System.out.println("Enter PT for generating a report of the billing history for a given patient");
        System.out.println("and for a certain time period (month/year), Enter U for getting the current usage");
        System.out.println("status for all hospitals, NP for getting the number of patients per month, HP for");
        System.out.println("getting the hospital usage percentage, DI for getting information on all the doctors");
        System.out.println("a patient is currently seeing, HS for getting information on hospitals grouped by");
        System.out.println("their specialty. Enter R to return to main menu or Q to quit the program.");
        System.out.println();
    }

    /**
     * Parent layer of the application.
     * 
     */
    public static void topLevelUserInterface(Scanner console) {
        printMainHeader();

        /** String object representing the command desired by user. */
        String userAction = "";

        System.out.print("Enter I, C, MM, MB, R, or Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();

        while (!userAction.equals("q")) {
            while (!userAction.equals("i") && !userAction.equals("c") && !userAction.equals("mm")
                    && !userAction.equals("mb") && !userAction.equals("r") && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter I, C, MM, MB, R, or Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("i")) {
                informationProcessingSubPanel(console);
            } else if (userAction.equals("c")) {
                checkInSubPanel(console);
            } else if (userAction.equals("mm")) {
                medicalRecordsSubPanel(console);
            } else if (userAction.equals("mb")) {
                billingAccountsSubPanel(console);
            } else if (userAction.equals("r")) {
                generateReportsSubPanel(console);
            } else {
                System.exit(1);
            }
            printMainHeader();
            System.out.print("Enter I, C, MM, MB, R, or Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }
    }

    public static void informationProcessingSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        printInfoProcessingHeader();
        System.out.print("Enter P, H, S, B, R, or Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("p") && !userAction.equals("h") && !userAction.equals("s")
                    && !userAction.equals("b") && !userAction.equals("r") && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter P, H, S, B, R, or Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("p")) {
                patientOperationsMenu(console);
            } else if (userAction.equals("h")) {
                hospitalOperationsMenu(console);
            } else if (userAction.equals("s")) {
                staffOperationsMenu(console);
            } else if (userAction.equals("b")) {
                bedOperationsMenu(console);
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter P, H, S, B, R, or Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }

    }

    public static void checkInSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        checkInHeader();
        System.out.print("Enter E, U, R, or Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("e") && !userAction.equals("u") && !userAction.equals("r")
                    && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter E, U, R, or Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("e")) {
                // userEnterNewCheckIn()
                System.out.println("\nENTER NEW CHECKIN\n");
            } else if (userAction.equals("u")) {
                // userUpdateCheckIn()
                System.out.println("\nUPDATE CHECK IN\n");
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter E, U, R, or Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }

    }

    public static void medicalRecordsSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        medicalRecordsHeader();
        System.out.print("Enter E, U, R, or Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("e") && !userAction.equals("u") && !userAction.equals("r")
                    && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter E, U, R, or Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("e")) {
                // userEnterNewMedicalRecord()
                System.out.println("\nENTER NEW MEDICAL RECORD\n");
            } else if (userAction.equals("u")) {
                // userUpdateMedicalRecord()
                System.out.println("\nUPDATE MEDICAL RECORD\n");
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter E, U, R, or Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }

    }

    public static void billingAccountsSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        billingAccountsHeader();
        System.out.print("Enter E, U, R, or Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("e") && !userAction.equals("u") && !userAction.equals("r")
                    && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter E, U, R, or Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("e")) {
                // userEnterNewBillingAccount()
                System.out.println("\nCREATE NEW BILLING ACCOUNT\n");
            } else if (userAction.equals("u")) {
                // userUpdateBillingAccount()
                System.out.println("\nUPDATE BILLING ACCOUNT\n");
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter E, U, R, or Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }

    }

    public static void generateReportsSubPanel(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        reportsHeader();
        System.out.print("Enter PT, U, NP, HP, DI, HS, R, Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("pt") && !userAction.equals("u") && !userAction.equals("np")
                    && !userAction.equals("hp") && !userAction.equals("di") && !userAction.equals("hs")
                    && !userAction.equals("r") && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter PT, U, NP, HP, DI, HS, R, Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("pt")) {
                // generateBillingHistoryForPatientGivenTime()
                System.out.println("\nBILLING HISTORY REPORT FOR A PATIENT GIVEN TIMEFRAME\n");
            } else if (userAction.equals("u")) {
                // generateUsageStatusAllHospitals()
                System.out.println("\nUSAGE STATUS FOR ALL HOSPITALS\n");
            } else if (userAction.equals("np")) {
                // generateNumberOfPatientsPerMonth()
                System.out.println("\nNUMBER OF PATIENTS PER MONTH\n");
            } else if (userAction.equals("hp")) {
                // generateHospitalUsagePercentage()
                System.out.println("\nHOSPITAL USAGE PERCENTAGE\n");
            } else if (userAction.equals("di")) {
                // generateAllPatientDoctorInfo()
                System.out.println("\nINFORMATION ON ALL DOCTORS A PATIENT IS CURRENTLY SEEING\n");
            } else if (userAction.equals("hs")) {
                // generateInfoOnHospitalsGroupedBySpecialty()
                System.out.println("\nINFORMATION ON HOSPITALS GROUPED BY SPECIALTY\n");
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter PT, U, NP, HP, DI, HS, R, Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }
    }

    public static void patientOperationsMenuHeader() {
        System.out.println("\n       Patient Operations Sub-Menu:\n");
        System.out.println("Enter E for creating a new Patient, U for updating an existing Patient,");
        System.out.println("D for deleting a Patient, T for transferring a patient to another hospital,");
        System.out.println("A to assign patients to a hospital if beds are available.");
        System.out.println("Enter R to return to previous menu or Q to quit the program.");
        System.out.println();
    }

    public static void hospitalOperationsMenuHeader() {
        System.out.println("\n       Hospital Operations Sub-Menu:\n");
        System.out.println("Enter E for creating a new Hospital, U for updating an existing Hospital,");
        System.out.println("D for deleting a Hospital, R to return to previous menu, or Q to quit the program.");
        System.out.println();
    }

    public static void staffOperationsMenuHeader() {
        System.out.println("\n       Staff Operations Sub-Menu:\n");
        System.out.println("Enter E for creating a new Staff Member, U for updating an existing Staff Member,");
        System.out.println("D for deleting a Staff Member, R to return to previous menu, or Q to quit the program.");
        System.out.println();
    }

    public static void bedOperationsMenuHeader() {
        System.out.println("\n       Beds Operations Sub-Menu:\n");
        System.out.println("Enter C for checking available beds in a hospital with appropriate specialty,");
        System.out.println("RES to reserve beds in hospitals, REL to release beds in hospitals.");
        System.out.println("Enter R to return to previous menu, or Q to quit the program.");
        System.out.println();
    }

    public static void patientOperationsMenu(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        patientOperationsMenuHeader();
        System.out.print("Enter E, U, D, T, A, R, Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("e") && !userAction.equals("u") && !userAction.equals("d")
                    && !userAction.equals("t") && !userAction.equals("a") && !userAction.equals("r")
                    && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter E, U, D, T, A, R, Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("e")) {
                // userEnterNewPatient()
                System.out.println("\nENTER NEW PATIENT\n");
            } else if (userAction.equals("u")) {
                // userUpdatePatient()
                System.out.println("\nUPDATE EXISTING PATIENT\n");
            } else if (userAction.equals("d")) {
                // userDeletePatient()
                System.out.println("\nDELETE EXISTING PATIENT\n");
            } else if (userAction.equals("t")) {
                // userTransferPatient()
                System.out.println("\nTRANSFER EXISTING PATIENT\n");
            } else if (userAction.equals("a")) {
                // userAssignPatientToHospital()
                System.out.println("\nASSIGN PATIENT TO HOSPITAL\n");
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter E, U, D, T, A, R, Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }
    }

    public static void hospitalOperationsMenu(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        hospitalOperationsMenuHeader();
        System.out.print("Enter E, U, D, R, Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("e") && !userAction.equals("u") && !userAction.equals("d")
                    && !userAction.equals("r") && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter E, U, D, R, Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("e")) {
                // userEnterNewHospital()
                System.out.println("\nENTER NEW HOSPITAL\n");
            } else if (userAction.equals("u")) {
                // userUpdateHospital()
                System.out.println("\nUPDATE EXISTING HOSPITAL\n");
            } else if (userAction.equals("d")) {
                // userDeleteHospital()
                System.out.println("\nDELETE EXISTING HOSPITAL\n");
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter E, U, D, R, Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }
    }

    public static void staffOperationsMenu(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        staffOperationsMenuHeader();
        System.out.print("Enter E, U, D, R, Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("e") && !userAction.equals("u") && !userAction.equals("d")
                    && !userAction.equals("r") && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter E, U, D, R, Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("e")) {
                // userEnterNewStaff()
                System.out.println("\nENTER NEW STAFF\n");
            } else if (userAction.equals("u")) {
                // userUpdateStaff()
                System.out.println("\nUPDATE EXISTING STAFF\n");
            } else if (userAction.equals("d")) {
                // userDeleteStaff()
                System.out.println("\nDELETE EXISTING STAFF\n");
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter E, U, D, R, Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }
    }

    public static void bedOperationsMenu(Scanner console) {
        /** String object representing the command desired by user. */
        String userAction = "";

        bedOperationsMenuHeader();
        System.out.print("Enter C, RES, REL, R, Q: ");
        userAction = console.next();
        userAction = userAction.toLowerCase();
        while (!userAction.equals("r")) {
            while (!userAction.equals("c") && !userAction.equals("res") && !userAction.equals("rel")
                    && !userAction.equals("r") && !userAction.equals("q")) {
                System.out.print("Error! Invalid command. Please enter C, RES, REL, R, Q: ");
                userAction = console.next();
                userAction = userAction.toLowerCase();
            }
            if (userAction.equals("c")) {
                // userCheckBedsInHospital()
                System.out.println("\nCHECK BEDS IN A HOSPITAL WITH APPROPRIATE SPECIALTY\n");
            } else if (userAction.equals("res")) {
                // userReserveBedsInHospital()
                System.out.println("\nRESERVE BEDS IN A HOSPITAL\n");
            } else if (userAction.equals("rel")) {
                // userReleaseBedsInHospital()
                System.out.println("\nRELEASE BEDS IN A HOSPITAL\n");
            } else if (userAction.equals("r")) {
                break;
            } else {
                System.exit(1);
            }
            printInfoProcessingHeader();
            System.out.print("Enter C, RES, REL, R, Q: ");
            userAction = console.next();
            userAction = userAction.toLowerCase();
        }
    }

}
