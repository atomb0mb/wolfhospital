// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine.
// Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all $USER$ with your unity id and $PASSWORD$ with your 9 digit student id or updated password (if changed)


import java.sql.*;

public class Wolfhospital {

static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cwng";

public static void main(String[] args) {
        try {

        // Load the driver. This creates an instance of the driver
    // and calls the registerDriver method to make MySql Thin
    // driver, available to clients.

    Class.forName("org.mariadb.jdbc.Driver");

    String user = "cwng";
    String passwd = "200207715";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {

        // Get a connection from the first driver in the
        // DriverManager list that recognizes the URL jdbcURL

        conn = DriverManager.getConnection(jdbcURL, user, passwd);

// Create a statement object that will be sending your
// SQL statements to the DBMS

stmt = conn.createStatement();
// Drop tables

stmt.executeUpdate("DROP TABLE IF EXISTS BillingAccounts");
stmt.executeUpdate("DROP TABLE IF EXISTS MedicalRecords");
stmt.executeUpdate("DROP TABLE IF EXISTS CheckIn");
stmt.executeUpdate("DROP TABLE IF EXISTS Staff");
stmt.executeUpdate("DROP TABLE IF EXISTS Beds");
// stmt.executeUpdate("DROP TABLE IF EXISTS Specializations");
stmt.executeUpdate("DROP TABLE IF EXISTS Patient");
stmt.executeUpdate("DROP TABLE IF EXISTS Hospital");
stmt.executeUpdate("DROP TABLE IF EXISTS Admin");

// Create the table for Admin

stmt.executeUpdate("CREATE TABLE Admin(aID integer UNIQUE NOT NULL, aName varchar(50) NOT NULL, PRIMARY KEY(aID))");

// Create the table for hospital

stmt.executeUpdate("create table Hospital(hID integer NOT NULL UNIQUE, aID integer NOT NULL, hAddress varchar(150) NOT NULL, " +
"hPhone varchar(15) NOT NULL, spec1 varchar(100), s1CostPerDay integer, spec2 varchar(100), s2CostPerDay integer, capacity integer NOT NULL, PRIMARY KEY(hID), FOREIGN KEY(aID) REFERENCES Admin(aID))");

// Specializations
// stmt.executeUpdate("create table Specializations(sID integer NOT NULL UNIQUE, hID integer NOT NULL, sName varchar(100) NOT NULL, costPerDay double NOT NULL, " +
// "PRIMARY KEY(sID), FOREIGN KEY(hID) REFERENCES Hospital(hID))");

// Patient
stmt.executeUpdate("create table Patient(pID integer NOT NULL UNIQUE PRIMARY KEY, pName varchar(50) NOT NULL, SSN varchar(11), " +
"DOB DATE NOT NULL, gender varchar(10) NOT NULL, patientAge integer NOT NULL, " +
"patientPhone varchar(15) NOT NULL, patientAddress varchar(150) NOT NULL, status varchar(150))");

// Beds
stmt.executeUpdate("create table Beds(bID int NOT NULL UNIQUE PRIMARY KEY, hID integer NOT NULL, spec varchar(100) NOT NULL, staffID integer, pID integer, reserved boolean NOT NULL, " +
"FOREIGN KEY (hID) REFERENCES Hospital(hID), FOREIGN KEY (pID) REFERENCES Patient(pID))");

// Staff
stmt.executeUpdate("create table Staff(staffID integer NOT NULL UNIQUE PRIMARY KEY, hID integer NOT NULL, staffName varchar(50) NOT NULL, " +
"homeAddress varchar(150) NOT NULL, officeAddress varchar(150), sgender varchar(10), age integer NOT NULL, jobTitle varchar(50) NOT NULL, department varchar(30), " +
"specPosition varchar(50), staffPhone varchar(15) NOT NULL, email varchar(30) NOT NULL, FOREIGN KEY(hID) REFERENCES Hospital(hID))");


// Check-in #when creating check in ensure that patient is assigned to a bed by updating bed (perhaps?)
stmt.executeUpdate("create table CheckIn(cID integer NOT NULL UNIQUE PRIMARY KEY, pID integer NOT NULL, hID integer NOT NULL, bID integer, " +
"startDate DATE NOT NULL, endDate DATE, respDoctor varchar(50) NOT NULL, currentDiagnosis varchar(500), registrationFee double NOT NULL, " +
"FOREIGN KEY(pID) REFERENCES Patient(pID), FOREIGN KEY(hID) REFERENCES Hospital(hID))");

// MedicalRecords
stmt.executeUpdate("create table MedicalRecords(mID integer NOT NULL UNIQUE PRIMARY KEY,cID integer NOT NULL, " +
"respNurse varchar(50), prescriptions varchar(500), diagnosisDetails varchar(500), treatment varchar(500), test varchar(500), " +
"result varchar(500), consultationfee integer NOT NULL, testfee integer NOT NULL, treatmentfee integer NOT NULL, FOREIGN KEY(cID) REFERENCES CheckIn(cID))");

// BillingAccounts ##TBD
stmt.executeUpdate("create table BillingAccounts(baID integer NOT NULL UNIQUE PRIMARY KEY,pID integer NOT NULL, payerSSN varchar(11) NOT NULL, " +
"billingAddress varchar(150) NOT NULL, paymentInfo varchar(100), registrationFee double, medicationPrescribed varchar(200), accommodationFee double, visitDate DATE NOT NULL, FOREIGN KEY(pID) REFERENCES Patient(pID))");


//populate data


// Admin
stmt.executeUpdate("insert into Admin(aID, aName) VALUES (11101, 'amagant'), (22201, 'jasalina')");


// Patient #1
stmt.executeUpdate("insert into Patient(pID, pName, DOB, gender, patientAge, patientPhone, patientAddress, status) VALUES(3001, 'John', '1980-02-22', 'M', 39, 513, '81 ABC St, NC 27', 'Treatment complete')");

// Patient #2
stmt.executeUpdate("insert into Patient(pID, pName, DOB, gender, patientAge, patientPhone, patientAddress, status) VALUES(3002, 'Jason', '1999-04-22', 'M', 20, 418, '82 ABC St, NC 27', 'In Treatment')");


// hospital #1
stmt.executeUpdate("insert into Hospital(hID, aID, hAddress, hPhone, spec1, s1CostPerDay, spec2, s2CostPerDay, capacity) VALUES (111, 11101, '111 St NC, 111', '101', 'pediatrics', 10, 'neurology', 15, 100)");
// Specializations
// stmt.executeUpdate("insert into Specializations(sID, hID, sName, costPerDay) VALUES (1, 111, 'pediatrics', 10)");
// stmt.executeUpdate("insert into Specializations(sID, hID, sName, costPerDay) VALUES (2, 111, 'neurology', 15)");

// hospital #2
stmt.executeUpdate("insert into Hospital(hID, aID, hAddress, hPhone, spec1, s1CostPerDay, spec2, s2CostPerDay, capacity) VALUES (222, 22201, '222 St NC, 222', '202', 'cardiology', 20, 'oncology', 25, 200)");
// Specializations
// stmt.executeUpdate("insert into Specializations(sID, hID, sName, costPerDay) VALUES (3, 222, 'cardiology', 20)");
// stmt.executeUpdate("insert into Specializations(sID, hID, sName, costPerDay) VALUES (4, 222, 'oncology', 25)");

// Staff Operator (Registration office)
stmt.executeUpdate("insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, " +
"specPosition, staffPhone, email) VALUES (1001, 111, 'Simpson', '21 ABC St, NC 27', 'F', 36, 'Biller', 'Billing', 'Account Supervisor', '919', '1001@gmail.com')");

// Nurse #1
stmt.executeUpdate("insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, "+
"specPosition, staffPhone, email) VALUES (1002, 111, 'David', '22 ABC St, NC 27', 'M', 45, 'Nurse', 'Neurology', 'Senior Nurse', '123', '1002@gmail.com')");

// Nurse #2
stmt.executeUpdate("insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, " +
"specPosition, staffPhone, email) VALUES (1005, 222, 'Ruth', '23 ABC St, NC 27', 'F', 35, 'Nurse', 'pediatrics', 'Assistant Nurse', '456', '1005@gmail.com')");

// Doctor #1
stmt.executeUpdate("insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, " +
"specPosition, staffPhone, email) VALUES (1003, 111, 'Lucy', '42 ABC St, NC 27', 'F', 40, 'Doctor', 'pediatrics', 'Senior Surgeon', '631', '1003@gmail.com')");

// Doctor #2
stmt.executeUpdate("insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, " +
"specPosition, staffPhone, email) VALUES (1010, 111, 'Steven', '48 ABC St, NC 27', 'M', 65, 'Doctor', 'pediatrics', 'Senior Surgeon', '632', '1010@gmail.com')");

// Doctor #3
stmt.executeUpdate("insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, " +
"specPosition, staffPhone, email) VALUES (1004, 222, 'Joseph', '51 ABC St, NC 27', 'M', 41, 'Doctor', 'cardiology', 'cardiologist', '327', '1004@gmail.com')");

// Beds #1
stmt.executeUpdate("insert into Beds(bID, hID, spec, staffID, reserved) VALUES (5001, 111, 'neurology', 1002, FALSE)");

// Beds #2
stmt.executeUpdate("insert into Beds(bID, hID, spec, staffID, reserved) VALUES (5002, 111, 'neurology', 1002, FALSE)");


// Check-in/out #1
stmt.executeUpdate("insert into CheckIn(cID, pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (1, 3001, 111, 5001, '2019-08-05', 1003, 'abc', 20)");

// Check-in/out #2
stmt.executeUpdate("insert into CheckIn(cID, pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (2, 3002, 111, 5002, '2019-10-15', 1003, 'def', 20)");



// Possibly revise for startDate and endDate for MedicalRecords instead of creating new checkins

// Check-in/out # 3 for MR #1
stmt.executeUpdate("insert into CheckIn(cID, pID, hID, bID, startDate, endDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3, 3001, 111, 5001, '2019-08-05', '2019-08-31', 1003, 'abc', 20)");

// MedicalRecords #1
stmt.executeUpdate("insert into MedicalRecords(mID ,cID, prescriptions, diagnosisDetails, treatment, test, result, consultationfee, testfee, treatmentfee) VALUES(2001, 3, 'antibiotics', 'Testing for TB', 'TB treatment', 'TB blood test', 'positive', 50, 75, 200 )");

// Check-in/out # 4 FOR MR #2
stmt.executeUpdate("insert into CheckIn(cID, pID, hID, bID, startDate, endDate, respDoctor, currentDiagnosis, registrationFee) VALUES (4, 3001, 111, 5001, '2019-09-01', '2019-09-16', 1003, 'abc', 20)");

// MedicalRecords #2
stmt.executeUpdate("insert into MedicalRecords(mID ,cID, prescriptions, diagnosisDetails, treatment, test, result, consultationfee, testfee, treatmentfee) " +
"VALUES(2002, 4, 'continue antibiotics', 'Testing for TB', 'Not required', 'X-ray chest (TB) Advanced', 'negative', 0, 125, 0 )");












/*
while (rs.next()) {
    String s = rs.getString("COF_NAME");
    float n = rs.getFloat("PRICE");
    System.out.println(s + "  " + n);
}
*/
            } finally {
                close(rs);
                close(stmt);
                close(conn);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }

    static void enterHospital(String hId, String aId, String hAddress, String hPhone, String s1, String s1cost, String s2, String s2cost, String capacity) {

    }

    static void updateHospital(String hId, String aId, String hAddress, String hPhone, String s1, String s1cost, String s2, String s2cost, String capacity) {

    }

    static void deleteHospital(String hId) {

    }

    static void enterPatient(String pId, String ssn, String pName, String dob, String gender, String patientAge, String patientPhone, String patientAddress, String status) {

    }

    static void updatePatient(String pId, String ssn, String pName, String dob, String gender, String age, String patientPhone, String patientAddress, String status) {

    }

    static void deletePatient(String pId) {

    }

    static void enterStaff(String staffID, String hID, String staffName, String homeAddress, String officeAddress, String sgender, String age, String jobTitle, String department, String specPosition, String staffPhone, String email) {

    }

    static void updateStaff(String staffID, String hID, String staffName, String homeAddress, String officeAddress, String sgender, String age, String jobTitle, String department, String specPosition, String staffPhone, String email) {

    }

    static void deleteStaff(String staffID) {

    }

    static void checkBeds(String bID) {

    }

    static void assignPatientToBeds(String pID, String bID){
      //MAKE SURE TO CHANGE BEDS TO RESERVE
    }

    static void releaseBed(String bID) {

    }

    static void createCheckIn( String pID, String hID, String bID, String startDate, String respDoctor, String currentDiagnosis, String registrationFee) {

    }

    static void transferPatient( String pID, String hID, String bID, String startDate, String endDate, String respDoctor, String currentDiagnosis, String registrationFee ) {
        // update
        // create 
    }


}
