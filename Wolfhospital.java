// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine.
// Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all $USER$ with your unity id and $PASSWORD$ with your 9 digit student id or updated password (if changed)


import java.sql.*;

public class Wolfhospital {

	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cwng";

	static String user = "cwng";
	static String passwd = "2001207715";


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
				createInitialTables(stmt);
				populateDemoTables(stmt);

                reportUsageStatus(stmt, rs);

				// System.out.println("Make sure we are here!");
				// checkBeds(conn ,"5001");
				// assignPatientToBed(conn, "3001", "5001");
				// assignPatientToBed(conn, "3001", "5001");
				// releaseBed(conn, "5001");
				//
				// createCheckIn( conn, "3001", "111", "5001", "2019-09-03", "1003", "XXX", "20");
				// transferPatient( conn, "5", "3002", "222", "5001", "2019-09-03", "2019-09-19", "1003", "XXX", "20");
				// enterMedicalRecords(conn, "2003", "6", "baa", "critical", "toolate", "Donttest", "TheEnd", "1000", "50000", "6000");
				// updateMedicalRecords(conn, "2003", "6", "baa", "StillAlive", "Hope", "Try", "Chance", "99", "555", "300");

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
    //#1 (Abhi)
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

    //#2 cwng

    static void checkBeds(Connection conn, String bID) {
       try {
         String sql = "select reserved from Beds where bID=";
         sql += bID +";";
         Statement stmt = null;
         ResultSet rs = null;
         try {
              stmt = conn.createStatement();
              rs = stmt.executeQuery(sql);

               while (rs.next()) {
                   String s = rs.getString("reserved");
                   if(s.equals("0")) {
                     System.out.println("The bed number " + bID + " is available.");
                   }
                   else {
                     System.out.println("The bed number " + bID + " is not available.");
                   }
               }

             } finally {
                 close(rs);
                 close(stmt);
                 //close(conn);
             }

             } catch(Throwable oops) {
                 oops.printStackTrace();
             }

    }

    static void assignPatientToBed(Connection conn, String pID, String bID) {
      //MAKE SURE TO CHANGE BEDS TO RESERVE

      try {
        String sqlcheck = "select reserved from Beds where bID=";
        sqlcheck += bID +";";

        Statement stmt = null;
        ResultSet rs = null;
        try {
             stmt = conn.createStatement();
             rs = stmt.executeQuery(sqlcheck);

              while (rs.next()) {
                  String s = rs.getString("reserved");
                  if(s.equals("1")) {
                    System.out.println("The bed number " + bID + " is not available.");
                  }
                  else {
                    String sql = "UPDATE Beds SET reserved = TRUE, pID= ";
                    sql += pID;
                    sql += " WHERE bID = ";
                    sql += bID +";";
                    stmt.executeUpdate(sql);
                    System.out.println("Successfully assigned patient to the bed!");
                  }
              }

            } finally {
                close(rs);
                close(stmt);
                //close(conn);
            }

            } catch(Throwable oops) {
                oops.printStackTrace();
            }
    }

    static void releaseBed(Connection conn, String bID) {
      try {
        String sqlcheck = "select reserved from Beds where bID=";
        sqlcheck += bID +";";

        Statement stmt = null;
        ResultSet rs = null;
        try {
             stmt = conn.createStatement();
             rs = stmt.executeQuery(sqlcheck);

              while (rs.next()) {
                  String s = rs.getString("reserved");
                  if(s.equals("1")) {
                    String sql = "UPDATE Beds SET reserved = FALSE, pID= ";
                    String pID = "NULL";
                    sql += pID;
                    sql += " WHERE bID = ";
                    sql += bID +";";
                    stmt.executeUpdate(sql);
                    System.out.println("Successfully updated bed number " + bID);
                  }
                  else {
                    System.out.println("Error! There is no patient assigned to this bed.");
                  }
              }

            } finally {
                close(rs);
                close(stmt);
                //close(conn);
            }

          } catch(Throwable oops) {
              oops.printStackTrace();
          }
    }

    static void createCheckIn( Connection conn, String pID, String hID, String bID, String startDate, String respDoctor, String currentDiagnosis, String registrationFee) {
      try {

        Statement stmt = null;
        ResultSet rs = null;
        try {
             stmt = conn.createStatement();

              String sqlInsert = "insert into CheckIn(pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (";

              sqlInsert += pID +", ";
              sqlInsert += hID +", ";
              sqlInsert += bID +", ";
              sqlInsert += "'"+ startDate +"', ";
              sqlInsert += respDoctor +", ";
              sqlInsert += "'"+currentDiagnosis +"', ";
              sqlInsert += registrationFee +");";

              stmt.executeUpdate(sqlInsert);

              System.out.println("CheckIn/Out for patient " + pID + " successfully created!");


            } finally {
                close(rs);
                close(stmt);
                //close(conn);
            }

          } catch(Throwable oops) {
              oops.printStackTrace();
          }
    }

    static void transferPatient( Connection conn, String cID, String pID, String hID, String bID, String startDate, String endDate, String respDoctor, String currentDiagnosis, String registrationFee ) {

        try {
          // Update the checkIns
          String sqlUpdate = "UPDATE CheckIn SET endDate=";
          sqlUpdate += "'"+ endDate;
          sqlUpdate += "' WHERE cID =";
          sqlUpdate += cID + ";";

          Statement stmt = null;
          ResultSet rs = null;
          try {
                 stmt = conn.createStatement();
                 stmt.executeUpdate(sqlUpdate);
                 String sqlshow = "select hID from CheckIn where cID=";
                 sqlshow += cID + ";";
                 rs = stmt.executeQuery(sqlshow);
                 String prevHopital = null;
                 while (rs.next()) {
                   prevHopital = rs.getString("hID");
                 }
                 // Create new CheckIn
                 // Note: The startdate for new checkIns records is endDate of previous checkIns records
                 String sqlInsert = "insert into CheckIn(pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (";
                 sqlInsert += pID +", ";
                 sqlInsert += hID +", ";
                 sqlInsert += bID +", ";
                 sqlInsert += "'"+ endDate +"', "; // startDate
                 sqlInsert += respDoctor +", ";
                 sqlInsert += "'"+currentDiagnosis +"', ";
                 sqlInsert += registrationFee +");";

                 stmt.executeUpdate(sqlInsert);

                 System.out.println("Successfully transferred patient " + pID + " from Hospital " + prevHopital +" to Hospital " +hID);

              } finally {
                  close(rs);
                  close(stmt);
                  //close(conn);
              }

            } catch(Throwable oops) {
                oops.printStackTrace();
            }
    }


    // Manage MedicalRecords
    static void enterMedicalRecords(Connection conn, String mID ,String cID, String prescriptions, String diagnosisDetails, String treatment, String test, String result, String consultationfee, String testfee, String treatmentfee) {
      try {

        Statement stmt = null;
        ResultSet rs = null;
        try {
             stmt = conn.createStatement();

              String sqlInsert = "insert into MedicalRecords(mID ,cID, prescriptions, diagnosisDetails, treatment, test, result, consultationfee, testfee, treatmentfee) VALUES(";
              sqlInsert += mID +", ";
              sqlInsert += cID +", ";
              sqlInsert += "'"+ prescriptions +"', ";
              sqlInsert += "'"+ diagnosisDetails +"', ";
              sqlInsert += "'"+ treatment +"', ";
              sqlInsert += "'"+ test +"', ";
              sqlInsert += "'"+ result +"', ";
              sqlInsert += consultationfee +", ";
              sqlInsert += testfee +", ";
              sqlInsert += treatmentfee +");";

              stmt.executeUpdate(sqlInsert);


              String sqlshow = "select pID from CheckIn where cID=";
              sqlshow += cID + ";";
              rs = stmt.executeQuery(sqlshow);

              String patientmedicalRecord = null;
              while (rs.next()) {
                patientmedicalRecord = rs.getString("pID");
              }

              System.out.println("MedicalRecord for patient " + patientmedicalRecord + " successfully created!");


            } finally {
                close(rs);
                close(stmt);
                //close(conn);
            }

          } catch(Throwable oops) {
              oops.printStackTrace();
          }
    }

    static void updateMedicalRecords(Connection conn, String mID ,String cID, String prescriptions, String diagnosisDetails, String treatment, String test, String result, String consultationfee, String testfee, String treatmentfee) {
      try {

        Statement stmt = null;
        ResultSet rs = null;
        try {
             stmt = conn.createStatement();

              String sqlUpdate = "UPDATE MedicalRecords SET ";
              sqlUpdate += "prescriptions= "+"'"+ prescriptions +"', ";
              sqlUpdate += "diagnosisDetails= "+"'"+ diagnosisDetails +"', ";
              sqlUpdate += "treatment= "+"'"+ treatment +"', ";
              sqlUpdate+= "test= "+"'"+ test +"', ";
              sqlUpdate+= "result= "+"'"+ result +"', ";
              sqlUpdate += "consultationfee= " + consultationfee +", ";
              sqlUpdate += "testfee= " + testfee +", ";
              sqlUpdate += "treatmentfee= "+ treatmentfee;
              sqlUpdate += " WHERE mID =";
              sqlUpdate += mID + ";";

              stmt.executeUpdate(sqlUpdate);


              String sqlshow = "select pID from CheckIn where cID=";
              sqlshow += cID + ";";
              rs = stmt.executeQuery(sqlshow);

              String patientmedicalRecord = null;
              while (rs.next()) {
                patientmedicalRecord = rs.getString("pID");
              }

              System.out.println("MedicalRecord for patient " + patientmedicalRecord + " successfully Updated!");


            } finally {
                close(rs);
                close(stmt);
                //close(conn);
            }

          } catch(Throwable oops) {
              oops.printStackTrace();
          }
    }
    // #3 jasalina arthur
    //Manage BillingAccounts
    static void createBillingAccount(String baID, String pID, String payerSSN, String billingAddress, String paymentInfo, String registrationFee, String medicationPrescribed, String accommodationFee, String visitDate) {
      // check beds first before you create account
    }

    //Report

    static void reportBillingHistory(String startDate, String endDate, String pId) {

    }

   /**
    * This is the function to report the Usage Status of the Hospitals. It will print the number of hospital beds currently
    * occupied in each hospital. The hospitals are defined by their hospital ids.
    * @param stmt statement object used to execute queries.
    * @param rs this is the resultset object necessary to print the results from the sql query.
    */
    static void reportUsageStatus(Statement stmt, ResultSet rs) {
        try{
            System.out.println("\nBeds currently in use per Hospital (Sorted by Hospital ID): ");
            System.out.println("+-------------+----------------+");
            rs = stmt.executeQuery("select h.hID, count(*) from Beds b, Hospital h WHERE b.hID = h.hID AND b.reserved = false group by h.hID;");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("| Hospital ID | Beds Reserved  |");
            System.out.println("+-------------+----------------+");
            while (rs.next()) {
                //Print one row
                
                System.out.print("| ");
                System.out.print(rs.getString(1) + "         | "); //Print each  col element
                System.out.print(rs.getString(2) + "              | "); 

                System.out.println();
                System.out.println("+-------------+----------------+");
            }
  
        }catch(Throwable oops) {
              oops.printStackTrace();
        }
    }

    static void reportPatientsPerMonth(String hID) {

    }

    static void reportHospitalPercentage(String hID){

    }

    static void reportDoctorByPatient(String pID) {

    }

    static void reportAllHospitalSpeciality() {

    }


    /**
		Create the initial tables to be used in the database.
		@param stmt is the statement object used to execute MariaDB statements.
    */
    static void createInitialTables(Statement stmt){
		try {

			//Drop Tables 
			stmt.executeUpdate("DROP TABLE IF EXISTS BillingAccounts");
			stmt.executeUpdate("DROP TABLE IF EXISTS MedicalRecords");
			stmt.executeUpdate("DROP TABLE IF EXISTS CheckIn");
			stmt.executeUpdate("DROP TABLE IF EXISTS Staff");
			stmt.executeUpdate("DROP TABLE IF EXISTS Beds");
			stmt.executeUpdate("DROP TABLE IF EXISTS Hospital");
			stmt.executeUpdate("DROP TABLE IF EXISTS Patient");
			stmt.executeUpdate("DROP TABLE IF EXISTS Admin");
			// Create the table for Admin

			stmt.executeUpdate("CREATE TABLE Admin(aID integer UNIQUE NOT NULL, aName varchar(50) NOT NULL, PRIMARY KEY(aID))");

			// Create the table for hospital

			stmt.executeUpdate("create table Hospital(hID integer NOT NULL UNIQUE, aID integer NOT NULL, hAddress varchar(150) NOT NULL, " +
			"hPhone varchar(15) NOT NULL, spec1 varchar(100), s1CostPerDay integer, spec2 varchar(100), s2CostPerDay integer, capacity integer NOT NULL, PRIMARY KEY(hID), FOREIGN KEY(aID) REFERENCES Admin(aID))");


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
			stmt.executeUpdate("create table CheckIn(cID integer NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, pID integer NOT NULL, hID integer NOT NULL, bID integer, " +
			"startDate DATE NOT NULL, endDate DATE, respDoctor varchar(50) NOT NULL, currentDiagnosis varchar(500), registrationFee double NOT NULL, " +
			"FOREIGN KEY(pID) REFERENCES Patient(pID), FOREIGN KEY(hID) REFERENCES Hospital(hID))");

			// MedicalRecords
			stmt.executeUpdate("create table MedicalRecords(mID integer NOT NULL UNIQUE PRIMARY KEY,cID integer NOT NULL, " +
			"respNurse varchar(50), prescriptions varchar(500), diagnosisDetails varchar(500), treatment varchar(500), test varchar(500), " +
			"result varchar(500), consultationfee integer NOT NULL, testfee integer NOT NULL, treatmentfee integer NOT NULL, FOREIGN KEY(cID) REFERENCES CheckIn(cID))");

			// BillingAccounts ##TBD
			stmt.executeUpdate("create table BillingAccounts(baID integer NOT NULL UNIQUE PRIMARY KEY,pID integer NOT NULL, payerSSN varchar(11), " +
			"billingAddress varchar(150) NOT NULL, paymentInfo varchar(100), registrationFee double, medicationPrescribed varchar(200), accommodationFee double, visitDate DATE NOT NULL, FOREIGN KEY(pID) REFERENCES Patient(pID))");


			
		} catch(Throwable oops) {
				oops.printStackTrace();
		}
    }
	
	/**
	* This is the function that populates the data values for all of the tables. 
	* @param stmt is the Statement object used to execute MariaDB statements
	*/
	static void populateDemoTables(Statement stmt){
		//populate data
		try {
			// Admin
			stmt.executeUpdate("insert into Admin(aID, aName) VALUES (11101, 'amagant'), (22201, 'jasalina')");


			// Patient #1
			stmt.executeUpdate("insert into Patient(pID, pName, DOB, gender, patientAge, patientPhone, patientAddress, status) VALUES(3001, 'John', '1980-02-22', 'M', 39, 513, '81 ABC St, NC 27', 'Treatment complete')");

			// Patient #2
			stmt.executeUpdate("insert into Patient(pID, pName, DOB, gender, patientAge, patientPhone, patientAddress, status) VALUES(3002, 'Jason', '1999-04-22', 'M', 20, 418, '82 ABC St, NC 27', 'In Treatment')");


			// hospital #1
			stmt.executeUpdate("insert into Hospital(hID, aID, hAddress, hPhone, spec1, s1CostPerDay, spec2, s2CostPerDay, capacity) VALUES (111, 11101, '111 St NC, 111', '101', 'pediatrics', 10, 'neurology', 15, 100)");

			// hospital #2
			stmt.executeUpdate("insert into Hospital(hID, aID, hAddress, hPhone, spec1, s1CostPerDay, spec2, s2CostPerDay, capacity) VALUES (222, 22201, '222 St NC, 222', '202', 'cardiology', 20, 'oncology', 25, 200)");

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
			stmt.executeUpdate("insert into CheckIn(pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3001, 111, 5001, '2019-08-05', 1003, 'abc', 20)");

			// Check-in/out #2
			stmt.executeUpdate("insert into CheckIn(pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3002, 111, 5002, '2019-10-15', 1003, 'def', 20)");


			// Possibly revise for startDate and endDate for MedicalRecords instead of creating new checkins

			// Check-in/out # 3 for MR #1
			stmt.executeUpdate("insert into CheckIn(pID, hID, bID, startDate, endDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3001, 111, 5001, '2019-08-05', '2019-08-31', 1003, 'abc', 20)");

			// MedicalRecords #1
			stmt.executeUpdate("insert into MedicalRecords(mID ,cID, prescriptions, diagnosisDetails, treatment, test, result, consultationfee, testfee, treatmentfee) VALUES(2001, 3, 'antibiotics', 'Testing for TB', 'TB treatment', 'TB blood test', 'positive', 50, 75, 200 )");

			// Check-in/out # 4 FOR MR #2
			stmt.executeUpdate("insert into CheckIn(pID, hID, bID, startDate, endDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3001, 111, 5001, '2019-09-01', '2019-09-16', 1003, 'abc', 20)");

			// MedicalRecords #2
			stmt.executeUpdate("insert into MedicalRecords(mID ,cID, prescriptions, diagnosisDetails, treatment, test, result, consultationfee, testfee, treatmentfee) " +
			"VALUES(2002, 4, 'continue antibiotics', 'Testing for TB', 'Not required', 'X-ray chest (TB) Advanced', 'negative', 0, 125, 0 )");	
		} catch(Throwable oops) {
				oops.printStackTrace();
		}
	}
	

}
