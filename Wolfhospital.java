// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine.
// Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all $USER$ with your unity id and $PASSWORD$ with your 9 digit
// student id or updated password (if changed)

// package org.verdictdb.commons.DBTablePrinter;
import java.sql.*;

/**
 * Wrapper class around database. Performs all necessary queries to statisfy the
 * required tasks and operations and exposes them as Java methods to be used by
 * the UI.
 * 
 */
public class Wolfhospital {

    /**
     * Utility function to close the Connection object.
     * 
     * @param conn Connection to be closed.
     * 
     */
    static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Throwable whatever) {
            }
        }
    }

    /**
     * Utility function to close the Statement object.
     * 
     * @param st Statement to be closed.
     * 
     */
    static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Throwable whatever) {
            }
        }
    }

    /**
     * Utility function to close the ResultSet object.
     * 
     * @param rs ResultSet to be closed.
     * 
     */
    static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable whatever) {
            }
        }
    }

    /**
     * Create a new Hospital record.
     * 
     * @param stmt     the statement from the db connection
     * @param hID      hospital ID
     * @param aId      administrator ID
     * @param hAddress hospital address
     * @param hPhone   hospital phone
     * @param s1       specialization 1
     * @param s1cost   charges per day for specialization 1
     * @param s2       specialization 2
     * @param s2cost   charges per day for specialization 2
     * @param capacity hospital capacity
     * 
     */
    static void enterHospital(Statement stmt, String hID, String aId, String hAddress, String hPhone, String s1,
            String s1cost, String s2, String s2cost, String capacity) {
        try {
            ResultSet rs = null;

            try {

                String sqlInsert = "insert into Hospital(hID, aID, hAddress, hPhone, spec1, s1CostPerDay, spec2, s2CostPerDay, capacity) VALUES(";
                sqlInsert += hID + ", ";
                sqlInsert += aId + ", ";
                sqlInsert += "'" + hAddress + "', ";
                sqlInsert += "'" + hPhone + "', ";
                sqlInsert += "'" + s1 + "', ";
                sqlInsert += s1cost + ", ";
                sqlInsert += "'" + s2 + "', ";
                sqlInsert += s2cost + ", ";
                sqlInsert += capacity + ");";

                rs = stmt.executeQuery(sqlInsert);
                System.out.println("Hospital with ID " + hID + " Successfully created.");

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Update a Hospital with given ID with provided values.
     * 
     * @param stmt     the statement from the db connection
     * @param hID      ID of hospital to be updated
     * @param aId      administrator ID
     * @param hAddress hospital address
     * @param hPhone   hospital phone
     * @param s1       specialization 1
     * @param s1cost   charges per day for specialization 1
     * @param s2       specialization 2
     * @param s2cost   charges per day for specialization 2
     * @param capacity hospital capacity
     * 
     */
    static void updateHospital(Statement stmt, String hID, String hAddress, String hPhone, String s1, String s1cost,
            String s2, String s2cost, String capacity) {
        try {
            ResultSet rs = null;

            try {

                String sqlUpdate = "UPDATE Hospital SET ";
                sqlUpdate += "hAddress = " + "'" + hAddress + "', ";
                sqlUpdate += "hPhone = " + "'" + hPhone + "', ";
                sqlUpdate += "spec1 = " + "'" + s1 + "', ";
                sqlUpdate += "s1CostPerDay = " + s1cost + ", ";
                sqlUpdate += "spec2 = " + "'" + s2 + "', ";
                sqlUpdate += "s2CostPerDay = " + s2cost + ", ";
                sqlUpdate += "capacity = " + capacity;
                sqlUpdate += " WHERE hId = " + hID;

                stmt.executeUpdate(sqlUpdate);
                System.out.println("Hospital with ID " + hID + " successfully updated.");

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Delete a Hospital given its ID.
     * 
     * @param stmt the statement from the db connection
     * @param hID  ID of hospital to be deleted
     * 
     */
    static void deleteHospital(Statement stmt, String hID) {
        try {
            ResultSet rs = null;

            try {

                String sqlDelete = "DELETE FROM Hospital WHERE hID = ";
                sqlDelete += hID + ";";
                stmt.executeUpdate(sqlDelete);
                System.out.println("Hospital with ID " + hID + " successfully deleted.");

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Create a new Patient record.
     * 
     * @param stmt           the statement from the db connection
     * @param pId            patient ID
     * @param ssn            patient social security number
     * @param pName          patient name
     * @param dob            patient date of birth
     * @param gender         patient gender
     * @param patientAge     patient age
     * @param patientPhone   patient phone
     * @param patientAddress patient address
     * @param status         patient status
     * 
     */
    static void enterPatient(Statement stmt, String pId, String ssn, String pName, String dob, String gender,
            String patientAge, String patientPhone, String patientAddress, String status) {
        try {
            ResultSet rs = null;

            try {
                if (ssn == null) {
                    String sqlInsert = "insert into Patient(pID, pName, DOB, gender, patientAge, patientPhone, patientAddress, status) VALUES(";
                    sqlInsert += pId + ", ";
                    sqlInsert += "'" + pName + "', ";
                    sqlInsert += "'" + dob + "', ";
                    sqlInsert += "'" + gender + "', ";
                    sqlInsert += patientAge + ", ";
                    sqlInsert += "'" + patientPhone + "', ";
                    sqlInsert += "'" + patientAddress + "', ";
                    sqlInsert += "'" + status + "');";

                    rs = stmt.executeQuery(sqlInsert);
                    System.out.println("Patient with " + pId + " successfully created.");
                } else {
                    String sqlInsert = "insert into Patient(pID, pName, SSN, DOB, gender, patientAge, patientPhone, patientAddress, status) VALUES(";
                    sqlInsert += pId + ", ";
                    sqlInsert += "'" + pName + "', ";
                    sqlInsert += "'" + ssn + "', ";
                    sqlInsert += "'" + dob + "', ";
                    sqlInsert += "'" + gender + "', ";
                    sqlInsert += patientAge + ", ";
                    sqlInsert += "'" + patientPhone + "', ";
                    sqlInsert += "'" + patientAddress + "', ";
                    sqlInsert += "'" + status + "');";

                    rs = stmt.executeQuery(sqlInsert);
                    System.out.println("Patient with ID " + pId + " successfully created.");
                }
            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Update a Patient with given ID with provided values.
     * 
     * @param stmt           the statement from the db connection
     * @param pId            ID of Patient to be updated
     * @param ssn            patient social security number
     * @param pName          patient name
     * @param dob            patient date of birth
     * @param gender         patient gender
     * @param patientAge     patient age
     * @param patientPhone   patient phone
     * @param patientAddress patient address
     * @param status         patient status
     * 
     */
    static void updatePatient(Statement stmt, String pId, String ssn, String pName, String dob, String gender,
            String patientAge, String patientPhone, String patientAddress, String status) {
        try {
            ResultSet rs = null;

            try {
                if (ssn == null) {

                    String sqlUpdate = "UPDATE Patient SET";
                    sqlUpdate += " pName = " + "'" + pName + "', ";
                    sqlUpdate += "DOB = " + "'" + dob + "', ";
                    sqlUpdate += "gender = " + "'" + gender + "', ";
                    sqlUpdate += "patientAge = " + patientAge + ", ";
                    sqlUpdate += "patientPhone = " + "'" + patientPhone + "', ";
                    sqlUpdate += "patientAddress = " + "'" + patientAddress + "', ";
                    sqlUpdate += "status = " + "'" + status + "'";
                    sqlUpdate += " WHERE pID = " + pId;
                    stmt.executeUpdate(sqlUpdate);
                    System.out.println("Patient with ID " + pId + " successfully updates.");

                } else {
                    String sqlUpdate = "UPDATE Patient SET";
                    sqlUpdate += " pName = " + "'" + pName + "', ";
                    sqlUpdate += " SSN = " + "'" + ssn + "', ";
                    sqlUpdate += "DOB = " + "'" + dob + "', ";
                    sqlUpdate += "gender = " + "'" + gender + "', ";
                    sqlUpdate += "patientAge = " + patientAge + ", ";
                    sqlUpdate += "patientPhone = " + "'" + patientPhone + "', ";
                    sqlUpdate += "patientAddress = " + "'" + patientAddress + "', ";
                    sqlUpdate += "status = " + "'" + status + "'";
                    sqlUpdate += " WHERE pID = " + pId;
                    stmt.executeUpdate(sqlUpdate);
                    System.out.println("Patient with ID " + pId + " successfully updates.");

                }

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Delete a Patient given its ID.
     * 
     * @param stmt the statement from the db connection
     * @param pId  the ID of patient to be deleted
     * 
     */
    static void deletePatient(Statement stmt, String pId) {
        try {
            ResultSet rs = null;

            try {

                String sqlDelete = "DELETE FROM Patient WHERE pID = ";
                sqlDelete += pId + ";";
                stmt.executeUpdate(sqlDelete);
                System.out.println("Patient with ID " + pId + " successfully deleted.");

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Enter a new Staff member record.
     * 
     * @param stmt          the statement from the db connection
     * @param staffID       ID of staff member
     * @param hID           hospital ID staff member belongs to
     * @param staffName     name of staff member
     * @param homeAddress   home address of staff member
     * @param officeAddress office address of staff member
     * @param sgender       staff member gender
     * @param age           staff member age
     * @param jobTitle      staff member job title
     * @param department    staff member department
     * @param specPosition  staff member professional title
     * @param staffPhone    staff member phone
     * @param email         staff member email
     * 
     */
    static void enterStaff(Statement stmt, String staffID, String hID, String staffName, String homeAddress,
            String officeAddress, String sgender, String age, String jobTitle, String department, String specPosition,
            String staffPhone, String email) {
        try {
            ResultSet rs = null;

            try {

                String sqlInsert = "insert into Staff(staffID, hID, staffName, homeAddress, officeAddress, sgender, age, jobTitle, department, "
                        + "specPosition, staffPhone, email) VALUES(";
                sqlInsert += staffID + ", ";
                sqlInsert += hID + ", ";
                sqlInsert += "'" + staffName + "', ";
                sqlInsert += "'" + homeAddress + "', ";
                sqlInsert += "'" + officeAddress + "', ";
                sqlInsert += "'" + sgender + "', ";
                sqlInsert += age + ", ";
                sqlInsert += "'" + jobTitle + "', ";
                sqlInsert += "'" + department + "', ";
                sqlInsert += "'" + specPosition + "', ";
                sqlInsert += "'" + staffPhone + "', ";
                sqlInsert += "'" + email + "'); ";

                rs = stmt.executeQuery(sqlInsert);
                System.out.println("Staff with ID " + staffID + " successfully created.");

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }

    }

    /**
     * Update Staff Member record with given ID with the provided values.
     * 
     * @param stmt          the statement from the db connection
     * @param staffID       ID of staff to be updated
     * @param staffName     name of staff member
     * @param homeAddress   home address of staff member
     * @param officeAddress office address of staff member
     * @param sgender       staff member gender
     * @param age           staff member age
     * @param jobTitle      staff member job title
     * @param department    staff member department
     * @param specPosition  staff member professional title
     * @param staffPhone    staff member phone
     * @param email         staff member email
     * 
     */
    static void updateStaff(Statement stmt, String staffID, String staffName, String homeAddress, String officeAddress,
            String sgender, String age, String jobTitle, String department, String specPosition, String staffPhone,
            String email) {

        try {
            ResultSet rs = null;

            try {

                String sqlUpdate = "UPDATE Staff SET ";
                sqlUpdate += "staffName = " + "'" + staffName + "', ";
                sqlUpdate += "homeAddress = " + "'" + homeAddress + "', ";
                sqlUpdate += "officeAddress = " + "'" + officeAddress + "', ";
                sqlUpdate += "sgender = " + "'" + sgender + "', ";
                sqlUpdate += "age = " + age + ", ";
                sqlUpdate += "jobTitle = " + "'" + jobTitle + "', ";
                sqlUpdate += "department = " + "'" + department + "', ";
                sqlUpdate += "specPosition = " + "'" + specPosition + "', ";
                sqlUpdate += "staffPhone = " + "'" + staffPhone + "', ";
                sqlUpdate += "email = " + "'" + email + "'";
                sqlUpdate += " WHERE staffID = " + staffID;

                stmt.executeQuery(sqlUpdate);
                System.out.println("Staff with ID " + staffID + " successfully Updated.");

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }

    }

    /**
     * Delete Staff member with given ID.
     * 
     * @param stmt    the statement from the db connection
     * @param staffID the ID of staff to be deleted
     * 
     */
    static void deleteStaff(Statement stmt, String staffID) {
        try {
            ResultSet rs = null;

            try {

                String sqlDelete = "DELETE FROM Staff WHERE staffID = ";
                sqlDelete += staffID + ";";
                stmt.executeUpdate(sqlDelete);
                System.out.println("Staff with ID " + staffID + " successfully deleted.");

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function shows the patient table similar to sql show query
     *
     * @param stmt the statement from the db connection
     **/
    static void showPatient(Statement stmt) {
        try {
            String sql = "select * from Patient;";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);

                DBTablePrinter.printResultSet(rs);

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function shows the hospital table similar to sql show query
     *
     * @param stmt the statement from the db connection
     **/
    static void showHospital(Statement stmt) {
        try {
            String sql = "select * from Hospital;";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);

                DBTablePrinter.printResultSet(rs);

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function shows the Staff table similar to sql show query
     *
     * @param stmt the statement from the db connection
     **/
    static void showStaff(Statement stmt) {
        try {
            String sql = "select * from Staff;";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);

                DBTablePrinter.printResultSet(rs);

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    // #2 cwng
    /**
     * This fucntion show bed table and return list of bed table similar to sql
     * select view
     *
     * @param stmt the statement from the db connection
     **/
    static void showBeds(Statement stmt) {
        try {
            String sql = "select * from Beds;";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);
                // while (rs.next()) {
                // // String b = rs.getString("bID");
                // // String h = rs.getString("hID");
                // // String s = rs.getString("spec");
                // // String p = rs.getString("pID");
                // // String r = rs.getString("reserved");
                // String r = rs.getString(1);
                // String s = rs.getString(2);
                // System.out.println(r);
                // System.out.println(s);
                // // System.out.println(b + " " + h + " " + s + " " + p + " " +
                // r);
                // }
                DBTablePrinter.printResultSet(rs);

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }

    }

    // #2 cwng
    /**
     * This checkbeds will check if the specific bed is reserved or NOT
     *
     * @param stmt the statement from the db connection
     * @param bID  is the number of bed number
     **/
    static void checkBeds(Statement stmt, String bID) {
        try {
            String sql = "select reserved from Beds where bID=";
            sql += bID + ";";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);
                ResultSet temp = null;
                temp = stmt.executeQuery(sql);
                if (!temp.next()) {
                    System.out.println("The bed number doesn't exist!");
                }
                while (rs.next()) {
                    String s = rs.getString("reserved");
                    if (s.equals("0")) {
                        System.out.println("The bed number " + bID + " is available.");
                    } else {
                        System.out.println("The bed number " + bID + " is not available.");
                    }
                }

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }

    }

    /**
     * This checkbeds will check if the hospital has available specialized bed.
     *
     * @param stmt the statement from the db connection
     * @param hID  is the hospital id that selected
     * @param spec is the specialty of the selected
     **/
    static void checkBedsBySpeciality(Statement stmt, String hID, String spec) {
        try {
            String sql = "select bID from Beds where hID=";
            sql += hID + " AND spec= '";
            sql += spec;
            sql += "' AND reserved = 0;";
            ResultSet rs = null;
            ResultSet temp = null;
            try {
                rs = stmt.executeQuery(sql);
                temp = stmt.executeQuery(sql);
                if (!temp.next()) {
                    System.out.println("There is no available bed for specialty of " + spec + " in Hospital " + hID);
                } else {
                    DBTablePrinter.printResultSet(rs);
                }

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }

    }

    /**
     * This function assign the patient to the selected bed
     *
     * @param stmt the statement from the db connection
     * @param pID  is patient id
     * @param bID  is the number of bed number
     **/
    static void assignPatientToBed(Statement stmt, String pID, String bID) {
        // MAKE SURE TO CHANGE BEDS TO RESERVE

        try {
            String sqlcheck = "select reserved from Beds where bID=";
            sqlcheck += bID + ";";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sqlcheck);

                while (rs.next()) {
                    String s = rs.getString("reserved");
                    if (s.equals("1")) {
                        System.out.println("The bed number " + bID + " is not available.");
                    } else {
                        String sql = "UPDATE Beds SET reserved = 1, pID= ";
                        sql += pID;
                        sql += " WHERE bID = ";
                        sql += bID + ";";
                        stmt.executeUpdate(sql);
                        System.out.println("Successfully assigned patient to the bed!");
                    }
                }

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function will release bed and set it to open / available
     *
     * @param stmt the statement from the db connection
     * @param bID  is the number of bed number
     **/
    static void releaseBed(Statement stmt, String bID) {
        try {
            String sqlcheck = "select reserved from Beds where bID=";
            sqlcheck += bID + ";";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sqlcheck);

                while (rs.next()) {
                    String s = rs.getString("reserved");
                    if (s.equals("1")) {
                        String sql = "UPDATE Beds SET reserved = 0, pID= ";
                        String pID = "NULL";
                        sql += pID;
                        sql += " WHERE bID = ";
                        sql += bID + ";";
                        stmt.executeUpdate(sql);
                        System.out.println("Successfully updated bed number " + bID);
                    } else {
                        System.out.println("Error! There is no patient assigned to this bed.");
                    }
                }

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function shows table of Check In similar to sql query display
     *
     * @param stmt the statement from the db connection
     **/
    static void showCheckInOut(Statement stmt) {
        try {
            String sql = "select * from CheckIn;";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);

                DBTablePrinter.printResultSet(rs);

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function will create check in / out for the patient
     *
     * @param stmt             the statement from the db connection
     * @param pID              is patient id
     * @param hID              is the hospital id
     * @param bID              is the number of bed number
     * @param startDate        is the start date of check in to the hospital
     * @param respDoctor       is the reponsible Doctor
     * @param currentDiagnosis is the current diagnosis information
     * @param registrationFee  is the fee of registration
     **/
    static void createCheckIn(Statement stmt, String pID, String hID, String bID, String startDate, String respDoctor,
            String currentDiagnosis, String registrationFee) {
        try {

            ResultSet rs = null;
            try {

                String sqlInsert = "insert into CheckIn(pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (";

                sqlInsert += pID + ", ";
                sqlInsert += hID + ", ";
                sqlInsert += bID + ", ";
                sqlInsert += "'" + startDate + "', ";
                sqlInsert += respDoctor + ", ";
                sqlInsert += "'" + currentDiagnosis + "', ";
                sqlInsert += registrationFee + ");";

                stmt.executeUpdate(sqlInsert);

                System.out.println("CheckIn/Out for patient " + pID + " successfully created!");

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function update the checkin/out which is checking out the patient from
     * the hospital
     *
     * @param stmt    the statement from the db connection
     * @param cID     is check in/out id
     * @param endDate is date of checking out or discharge to the hospital
     **/
    static void checkOut(Statement stmt, String cID, String endDate) {
        try {
            // Update the checkIns
            String sqlUpdate = "UPDATE CheckIn SET endDate=";
            sqlUpdate += "'" + endDate;
            sqlUpdate += "' WHERE cID =";
            sqlUpdate += cID + ";";

            ResultSet rs = null;
            try {
                stmt.executeUpdate(sqlUpdate);
                String sqlshow = "select pID from CheckIn where cID=";
                sqlshow += cID + ";";
                rs = stmt.executeQuery(sqlshow);
                String patientCheckOut = null;
                while (rs.next()) {
                    patientCheckOut = rs.getString("pID");
                }
                System.out.println("Successfully check-out patient " + patientCheckOut);

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * CONTAINS TRANSACTION
     * 
     * This function will transfer patient to the selected hospital
     *
     * @param stmt             the statement from the db connection
     * @param cID              is check ins
     * @param pID              is patient id
     * @param hID              is the hospital to be transferred
     * @param bID              is the number of bed number
     * @param endDate          is date of checking out or discharge to the hospital
     * @param respDoctor       is the reponsible Doctor
     * @param currentDiagnosis is the current diagnosis information
     * @param registrationFee  is the fee of registration
     **/
    static void transferPatient(Connection conn, String cID, String pID, String hID, String bID, String endDate,
            String respDoctor, String currentDiagnosis, String registrationFee) throws SQLException {

        PreparedStatement updateCheckIn = null; // For updating checkins
        PreparedStatement showhid = null; // for showing the hospital ID
        PreparedStatement createNewCheckIn = null; // for creating a new chech in

        /** SQL Queries to be executed */
        String sqlUpdate = "UPDATE CheckIn SET endDate= ? WHERE cID= ?";
        String sqlshow = "select hID from CheckIn WHERE cID= ?";
        String sqlInsert = "insert into CheckIn(pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (?, ?, ?, ?, ?, ?, ?) ";

        ResultSet rs = null; // ResultSet object to store query result

        try {
            // Update the checkIns
            conn.setAutoCommit(false); // set auto commit to false to initiate transaction

            /** Initialize PreparedStatements */
            updateCheckIn = conn.prepareStatement(sqlUpdate);
            showhid = conn.prepareStatement(sqlshow);
            createNewCheckIn = conn.prepareStatement(sqlInsert);

            /** Set the corresponding attributes */
            updateCheckIn.setDate(1, java.sql.Date.valueOf(endDate)); // parameter of endDate
            updateCheckIn.setInt(2, Integer.parseInt(cID)); // parameter of cID

            updateCheckIn.executeUpdate(); // execute update query

            showhid.setInt(1, Integer.parseInt(cID)); // parameter of cID

            rs = showhid.executeQuery(); // execute select query
            // To store the previous hospital
            String prevHopital = null;
            while (rs.next()) {
                prevHopital = rs.getString("hID");
            }
            // Create new CheckIn
            // Note: The startdate for new checkIns records is endDate of
            // previous checkIns records
            createNewCheckIn.setInt(1, Integer.parseInt(pID)); // parameter of pId
            createNewCheckIn.setInt(2, Integer.parseInt(hID)); // parameter of hId
            createNewCheckIn.setInt(3, Integer.parseInt(bID)); // parameter of bID
            createNewCheckIn.setDate(4, java.sql.Date.valueOf(endDate)); // parameter of Start Date
            createNewCheckIn.setInt(5, Integer.parseInt(respDoctor)); // parameter of Responsible doctor
            createNewCheckIn.setString(6, currentDiagnosis); // parameter of currentDiagnosis
            createNewCheckIn.setInt(7, Integer.parseInt(registrationFee)); // parameter of registrationFee

            createNewCheckIn.executeUpdate(); // execute insert query
            conn.commit(); // commit to database
            System.out.println("Successfully transferred patient " + pID + " from Hospital " + prevHopital
                    + " to Hospital " + hID);

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.out.println("Cannot transfer patient.");
                    System.err.println("Transaction is being rolled back");
                    conn.rollback(); // if patient cannot be transferred, rollback
                } catch (SQLException excep) {
                    excep.printStackTrace();
                }
            }

        } finally {
            close(rs);
            conn.setAutoCommit(true); // set auto commit back to true
        }

    }

    /**
     * This function shows the medical record similar to sql show query
     *
     * @param stmt the statement from the db connection
     **/
    static void showMedicalRecords(Statement stmt) {
        try {
            String sql = "select * from MedicalRecords;";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);

                DBTablePrinter.printResultSet(rs);

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function create medical record for the patient
     *
     * @param stmt             the statement from the db connection
     * @param mID              medical record id
     * @param cID              is checkIns id
     * @param prescriptions    is prescriptions info
     * @param diagnosisDetails is diagnosis details
     * @param treatment        is treatment info
     * @param test             is the test info
     * @param result           is the result
     * @param consultationfee  is the fee of consultion
     * @param testfee          is the fee of test
     * @param treatmentfee     is the fee of treatment
     **/
    static void enterMedicalRecords(Statement stmt, String mID, String cID, String prescriptions,
            String diagnosisDetails, String treatment, String test, String result, String consultationfee,
            String testfee, String treatmentfee) {
        try {

            ResultSet rs = null;
            try {

                String sqlInsert = "insert into MedicalRecords(mID ,cID, prescriptions, diagnosisDetails, treatment, test, result, consultationfee, testfee, treatmentfee) VALUES(";
                sqlInsert += mID + ", ";
                sqlInsert += cID + ", ";
                sqlInsert += "'" + prescriptions + "', ";
                sqlInsert += "'" + diagnosisDetails + "', ";
                sqlInsert += "'" + treatment + "', ";
                sqlInsert += "'" + test + "', ";
                sqlInsert += "'" + result + "', ";
                sqlInsert += consultationfee + ", ";
                sqlInsert += testfee + ", ";
                sqlInsert += treatmentfee + ");";

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
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function update medical record for the patient
     *
     * @param stmt             the statement from the db connection
     * @param mID              medical record id
     * @param prescriptions    is prescriptions info
     * @param diagnosisDetails is diagnosis details
     * @param treatment        is treatment info
     * @param test             is the test info
     * @param result           is the result
     * @param consultationfee  is the fee of consultion
     * @param testfee          is the fee of test
     * @param treatmentfee     is the fee of treatment
     **/
    static void updateMedicalRecords(Statement stmt, String mID, String prescriptions, String diagnosisDetails,
            String treatment, String test, String result, String consultationfee, String testfee, String treatmentfee) {
        try {

            ResultSet rs = null;
            ResultSet rs2 = null;
            try {

                String sqlUpdate = "UPDATE MedicalRecords SET ";
                sqlUpdate += "prescriptions= " + "'" + prescriptions + "', ";
                sqlUpdate += "diagnosisDetails= " + "'" + diagnosisDetails + "', ";
                sqlUpdate += "treatment= " + "'" + treatment + "', ";
                sqlUpdate += "test= " + "'" + test + "', ";
                sqlUpdate += "result= " + "'" + result + "', ";
                sqlUpdate += "consultationfee= " + consultationfee + ", ";
                sqlUpdate += "testfee= " + testfee + ", ";
                sqlUpdate += "treatmentfee= " + treatmentfee;
                sqlUpdate += " WHERE mID =";
                sqlUpdate += mID + ";";

                stmt.executeUpdate(sqlUpdate);

                // Get cID from existing MedicalRecord
                String sqlshow1 = "select cID from MedicalRecords where mID=";
                sqlshow1 += mID + ";";
                rs = stmt.executeQuery(sqlshow1);

                String cID = null;
                while (rs.next()) {
                    cID = rs.getString("cID");
                }

                String sqlshow2 = "select pID from CheckIn where cID=";
                sqlshow2 += cID + ";";
                rs2 = stmt.executeQuery(sqlshow2);

                String patientmedicalRecord = null;
                while (rs2.next()) {
                    patientmedicalRecord = rs2.getString("pID");
                }

                System.out.println("MedicalRecord for patient " + patientmedicalRecord + " successfully Updated!");

            } finally {
                close(rs);
                close(rs2);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    // #3 jasalina arthur
    /**
     * This function will show available beds in selected Hospital
     *
     * @param stmt the statement from the db connection
     * @param hID  is the hospital to display
     **/
    static void showAvailableBedInHospital(Statement stmt, String hID) {
        try {
            String sql = "select bID from Beds where reserved = 0 AND hID=";
            sql += hID + ";";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);

                DBTablePrinter.printResultSet(rs);

            } finally {
                close(rs);

            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * CONTAINS TRANSACTION
     * 
     * This function create billing account for the patient. It will prompt user to
     * enter hospital id so that it validates if the hospital has open beds for the
     * patient. If the bed is available, it will create a billing account for the
     * patient.
     * 
     * @param stmt             the statement from the db connection
     * @param hID              is the hospital id that selected
     * @param pID              is patient to be admit to the hospital
     * @param payerSSN         is prescriptions info
     * @param billingAddress   is the billing address of the patient
     * @param paymentInfo      is the payment info for the patient
     * @param registrationFee  is the registration fee
     * @param accommodationFee is the fee of accommodation
     * @param consultationFee  is the fee for consulation
     * @param testFee          is the fee of the test carried out on the patient
     * @param treatmentFee     is the fee of the treatement to be carried out on the
     *                         patient.
     * @param specDailyFee     is the daily fee for specializations
     * @param visitDate        is the date of visit of the patient
     **/
    static void createBillingAccount(Connection conn, String hID, String pID, String payerSSN, String billingAddress,
            String paymentInfo, String registrationFee, String accommodationFee, String consultationFee, String testFee,
            String treatmentFee, String specDailyFee, String visitDate) throws SQLException {

        PreparedStatement checkIfAvailableBeds = null; // for checking if there are available beds
        PreparedStatement createNewBillingAccount1 = null; // for creating a billing account with payerSSN = NULL
        PreparedStatement createNewBillingAccount2 = null; // for creating a billing account with a payerSSN

        /** SQL Queries to be executed. */
        String sqlCheck = "SELECT bID from Beds WHERE reserved = ? AND hID= ?";
        String sqlInsert = "insert into BillingAccounts(pID, billingAddress, paymentInfo, registrationFee, accommodationFee, consultationFee, testFee, treatmentFee, specDailyFee, visitDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String sqlInsert2 = "insert into BillingAccounts(pID, payerSSN, billingAddress, paymentInfo, registrationFee, accommodationFee, consultationFee, testFee, treatmentFee, specDailyFee, visitDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        ResultSet rs = null; // ResultSet to store the result of the queries

        try {
            // check beds first before you create account
            conn.setAutoCommit(false); // set auto commit to false to initiate transaction

            /** Initialize PreparedStatements */
            checkIfAvailableBeds = conn.prepareStatement(sqlCheck);
            createNewBillingAccount1 = conn.prepareStatement(sqlInsert);
            createNewBillingAccount2 = conn.prepareStatement(sqlInsert2);

            /** Set the parameter values. */
            checkIfAvailableBeds.setBoolean(1, false); // parameter of reserved
            checkIfAvailableBeds.setInt(2, Integer.parseInt(hID)); // parameter of hID

            ResultSet temp = null; // temporary variable to store ResultSet
            temp = checkIfAvailableBeds.executeQuery(); // execute select query

            // Check if there are beds in the hospital. If there are none, rollback.
            if (!temp.next()) {
                System.out.println("This hospital does not have an open bed.");
                System.err.println("Transaction is being rolled back");
                conn.rollback(); // rollback
            } else {
                if (payerSSN == null) { // if no payerSSN provided
                    createNewBillingAccount1.setInt(1, Integer.parseInt(pID));
                    createNewBillingAccount1.setString(2, billingAddress);
                    createNewBillingAccount1.setString(3, paymentInfo);
                    createNewBillingAccount1.setInt(4, Integer.parseInt(registrationFee));
                    createNewBillingAccount1.setInt(5, Integer.parseInt(accommodationFee));
                    createNewBillingAccount1.setInt(6, Integer.parseInt(consultationFee));
                    createNewBillingAccount1.setInt(7, Integer.parseInt(testFee));
                    createNewBillingAccount1.setInt(8, Integer.parseInt(treatmentFee));
                    createNewBillingAccount1.setInt(9, Integer.parseInt(specDailyFee));
                    createNewBillingAccount1.setDate(10, java.sql.Date.valueOf(visitDate));

                    createNewBillingAccount1.executeUpdate();
                    conn.commit(); // commit

                    System.out.println("Billing account for patient " + pID + " successfully created!");
                } else { // if payerSSN provided
                    createNewBillingAccount2.setInt(1, Integer.parseInt(pID));
                    createNewBillingAccount2.setString(2, payerSSN);
                    createNewBillingAccount2.setString(3, billingAddress);
                    createNewBillingAccount2.setString(4, paymentInfo);
                    createNewBillingAccount2.setInt(5, Integer.parseInt(registrationFee));
                    createNewBillingAccount2.setInt(6, Integer.parseInt(accommodationFee));
                    createNewBillingAccount2.setInt(7, Integer.parseInt(consultationFee));
                    createNewBillingAccount2.setInt(8, Integer.parseInt(testFee));
                    createNewBillingAccount2.setInt(9, Integer.parseInt(treatmentFee));
                    createNewBillingAccount2.setInt(10, Integer.parseInt(specDailyFee));
                    createNewBillingAccount2.setDate(11, java.sql.Date.valueOf(visitDate));

                    createNewBillingAccount2.executeUpdate();
                    conn.commit(); // commit

                    System.out.println("Billing account for patient " + pID + " successfully created!");
                }
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        } finally {
            close(rs);
            conn.setAutoCommit(true); // set auto commit back to true
        }
    }

    /**
     * This method is used to update billingAccounts given a billingAccountID and
     * the parameters to update with.
     * 
     * @param stmt             is the Statement object needed to execute mysql
     *                         statements.
     * @param pID              is patient in the hospital
     * @param payerSSN         is social security of whoever is paying
     * @param billingAddress   is the billing address of the patient
     * @param paymentInfo      is the payment info for the patient
     * @param test             is the test of the patient
     * @param result           is the result of the patient
     * @param registrationfee  is the registration fee
     * @param accommodationfee is the fee of accommodation
     * @param visitDate        is the date of visit of the patient
     */
    static void updateBillingAccount(Statement stmt, String baID, String pID, String payerSSN, String billingAddress,
            String paymentInfo, String registrationFee, String accommodationFee, String consultationFee, String testFee,
            String treatmentFee, String specDailyFee, String visitDate) {
        try {
            ResultSet rs = null;
            String updateQuery = "UPDATE BillingAccounts SET ";
            updateQuery += "pID = " + pID + ", ";
            updateQuery += "payerSSN = '" + payerSSN + "', ";
            updateQuery += "billingAddress = '" + billingAddress + "', ";
            updateQuery += "paymentInfo = '" + paymentInfo + "', ";
            updateQuery += "registrationFee = " + registrationFee + ", ";
            updateQuery += "accommodationFee = " + accommodationFee + ", ";
            updateQuery += "consultationFee = " + consultationFee + ", ";
            updateQuery += "testFee = " + testFee + ", ";
            updateQuery += "treatmentFee = " + treatmentFee + ", ";
            updateQuery += "specDailyFee = " + specDailyFee + ", ";
            updateQuery += "visitDate = '" + visitDate + "' ";
            updateQuery += "WHERE baID = ";
            updateQuery += baID + ";";

            stmt.executeUpdate(updateQuery);

            System.out.println("Billing Account for patient " + pID + " successfully Updated!");
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function shows the billing account table similar to sql show query
     *
     * @param stmt the statement from the db connection
     **/
    static void showBillingAccounts(Statement stmt) {
        try {
            String sql = "select * from BillingAccounts;";
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);

                DBTablePrinter.printResultSet(rs);

            } finally {
                close(rs);
                // close(stmt);
                // close(conn);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    // Report

    /**
     * This method returns a list of all of the billing accounts that a patient has
     * for a particular set of time.
     * 
     * @param startDate is the starting date to display the billing accounts for.
     * @param endDate   is the ending date range to display the billing accounts
     *                  for.
     * @param pID       is the id of the patient to look for the history from.
     */
    static void reportBillingHistory(Statement stmt, String startDate, String endDate, String pID) {
        try {
            ResultSet rs = null;
            String billQuery = "SELECT b.pID, b.payerSSN, b.billingAddress, b.paymentInfo, b.registrationFee, b.accommodationFee, ";
            billQuery += "b.consultationFee,  b.testFee, b.treatmentFee, b.specDailyFee, b.visitDate FROM BillingAccounts b WHERE b.pID = ";
            billQuery += pID + " AND ";
            billQuery += "b.visitDate >= '";
            billQuery += startDate + "' AND b.visitDate <= '";
            billQuery += endDate + "';";

            rs = stmt.executeQuery(billQuery);

            DBTablePrinter.printResultSet(rs);

        } catch (Throwable oops) {
            oops.printStackTrace();
        }

    }

    /**
     * This is the function to report the Usage Status of the Hospitals. It will
     * print the number of hospital beds currently occupied in each hospital. The
     * hospitals are defined by their hospital ids.
     * 
     * @param stmt statement object used to execute queries.
     */
    static void reportUsageStatus(Statement stmt) {
        ResultSet rs = null;
        try {
            System.out.println("\nBeds currently in use per Hospital (Sorted by Hospital ID): ");
            System.out.println("+-------------+----------------+");
            rs = stmt.executeQuery(
                    "select h.hID, count(*) from Beds b, Hospital h WHERE b.hID = h.hID AND b.reserved = true group by h.hID;");
            System.out.println("| Hospital ID | Beds Reserved  |");
            System.out.println("+-------------+----------------+");
            while (rs.next()) {
                // Print one row

                System.out.print("| ");
                System.out.print(rs.getString(1) + "         | "); // Print each col element
                System.out.print(rs.getString(2) + "              | ");

                System.out.println();
                System.out.println("+-------------+----------------+");
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function takes in a month, year, and hospital id and returns the number
     * of patients that checked in that month.
     * 
     * @param hID   is the ID of the hospital to check in.
     * @param month is the month to get the number of patients for.
     * @param year  is the year for the corresponding month.
     */
    static void reportPatientsPerMonth(Statement stmt, String hID, String month, String year) {
        ResultSet rs = null;
        try {
            System.out.println("\nNumber of Patients in Hospital " + hID + " During the Month " + month + ", " + year);
            String monthQuery = "select count(distinct(pID)) as Total_Patients FROM CheckIn WHERE MONTH(startDate) = ";
            monthQuery += month;
            monthQuery += " AND YEAR(startDate) = ";
            monthQuery += year;
            monthQuery += " AND hID = ";
            monthQuery += hID;
            rs = stmt.executeQuery(monthQuery);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            DBTablePrinter.printResultSet(rs);

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Report the Hospital Usage Percentages which means the number of beds
     * currently in use per hospital as a percentage.
     * 
     * @param stmt is the Statment object used to execute queries in mysql.
     * @param hID  is a string for the ID of a hospital to get the usage of.
     */
    static void reportHospitalPercentage(Statement stmt, String hID) {
        ResultSet rs = null;
        try {
            System.out.println("\nThe number of Beds Currently in Use Per Hospital:");
            String usageQuery = "SELECT h.hID, h.capacity, (SUM(case WHEN b.reserved = 1 THEN 1 ELSE 0 END) / ";
            usageQuery += "h.capacity) as Hospital_Usage FROM Beds b, Hospital h WHERE h.hID = ";
            usageQuery += hID;
            usageQuery += " AND b.hID = h.hID group by h.hID;";

            rs = stmt.executeQuery(usageQuery);
            ResultSet temp = null;
            temp = stmt.executeQuery(usageQuery);
            if (!temp.next()) {
                System.out.println("\nNO BEDS WERE SET UP FOR THIS HOSPITAL!\n");
            } else {

                DBTablePrinter.printResultSet(rs);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function reports all of the doctors that a particular patient is
     * currently seeing. It features the doctor's ID, their name, and the date from
     * which the patient has seen the doctor.
     * 
     * @param stmt is the statement Object used to execute mysql queries.
     * @param pID  is the string containing the ID of the patient to get the Doctors
     *             for.
     */
    static void reportDoctorByPatient(Statement stmt, String pID) {
        ResultSet rs = null;
        try {
            System.out.println("\nThe Doctors that the Patient is currently seeing:");
            String doctorQuery = "SELECT p.pID, p.pName as Patient_Name, c.RespDoctor as DoctorID, s.StaffName as Doctor, ";
            doctorQuery += "c.startDate as Seeing_From FROM ";
            doctorQuery += "CheckIn c, Patient p, Staff s WHERE c.pID = p.pID AND c.endDate is NULL AND p.pID = ";
            doctorQuery += pID;
            doctorQuery += " AND c.RespDoctor = s.StaffID;";

            rs = stmt.executeQuery(doctorQuery);
            ResultSet temp = null;
            temp = stmt.executeQuery(doctorQuery);
            if (!temp.next()) {
                System.out.println("\nNo Doctors assigned to this patient\n");
            } else {
                DBTablePrinter.printResultSet(rs);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This function reports all hospital information grouped by their specialities
     * 
     * @param stmt is the Statment object used to execute queries.
     */
    static void reportAllHospitalSpeciality(Statement stmt) {
        ResultSet rs = null;
        try {
            String hQuery = "SELECT h.spec1 as Specialization_1, h.spec2 as Specialization_2, h.hID as Hospital_ID, ";
            hQuery += "h.aID as Admin_ID, h.hAddress as Address, h.hPhone as Phone, h.capacity as Capacity ";
            hQuery += "FROM Hospital h order by h.spec1, h.spec2";

            rs = stmt.executeQuery(hQuery);
            ResultSet temp = null;
            temp = stmt.executeQuery(hQuery);
            if (!temp.next()) {
                System.out.println("\nHospitals not set up in system!\n");
            } else {
                DBTablePrinter.printResultSet(rs);
            }

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * Create the initial tables to be used in the database.
     *
     * @param stmt is the statement object used to execute MariaDB statements.
     */
    static void createInitialTables(Statement stmt) {
        try {

            // Drop Tables
            stmt.executeUpdate("DROP TABLE IF EXISTS BillingAccounts");
            stmt.executeUpdate("DROP TABLE IF EXISTS MedicalRecords");
            stmt.executeUpdate("DROP TABLE IF EXISTS CheckIn");
            stmt.executeUpdate("DROP TABLE IF EXISTS Staff");
            stmt.executeUpdate("DROP TABLE IF EXISTS Beds");
            stmt.executeUpdate("DROP TABLE IF EXISTS Hospital");
            stmt.executeUpdate("DROP TABLE IF EXISTS Patient");
            stmt.executeUpdate("DROP TABLE IF EXISTS Admin");
            // Create the table for Admin

            stmt.executeUpdate(
                    "CREATE TABLE Admin(aID integer UNIQUE NOT NULL, aName varchar(50) NOT NULL, PRIMARY KEY(aID))");

            // Create the table for hospital

            stmt.executeUpdate(
                    "create table Hospital(hID integer NOT NULL UNIQUE, aID integer NOT NULL, hAddress varchar(150) NOT NULL, "
                            + "hPhone varchar(15) NOT NULL, spec1 varchar(100), s1CostPerDay integer, spec2 varchar(100), s2CostPerDay integer, capacity integer NOT NULL, PRIMARY KEY(hID), FOREIGN KEY(aID) REFERENCES Admin(aID))");

            // Patient
            stmt.executeUpdate(
                    "create table Patient(pID integer NOT NULL UNIQUE PRIMARY KEY, pName varchar(50) NOT NULL, SSN varchar(11), "
                            + "DOB DATE NOT NULL, gender varchar(10) NOT NULL, patientAge integer NOT NULL, "
                            + "patientPhone varchar(15) NOT NULL, patientAddress varchar(150) NOT NULL, status varchar(150))");

            // Beds
            stmt.executeUpdate(
                    "create table Beds(bID int NOT NULL UNIQUE PRIMARY KEY, hID integer NOT NULL, spec varchar(100) NOT NULL, sChargePerDay integer, staffID integer, pID integer, reserved boolean, "
                            + "FOREIGN KEY (hID) REFERENCES Hospital(hID))");

            // Staff
            stmt.executeUpdate(
                    "create table Staff(staffID integer NOT NULL UNIQUE PRIMARY KEY, hID integer NOT NULL, staffName varchar(50) NOT NULL, "
                            + "homeAddress varchar(150) NOT NULL, officeAddress varchar(150), sgender varchar(10), age integer NOT NULL, jobTitle varchar(50) NOT NULL, department varchar(30), "
                            + "specPosition varchar(50), staffPhone varchar(15) NOT NULL, email varchar(30) NOT NULL, FOREIGN KEY(hID) REFERENCES Hospital(hID))");

            // Check-in #when creating check in ensure that patient is assigned
            // to a bed by updating bed (perhaps?)
            stmt.executeUpdate(
                    "create table CheckIn(cID integer NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, pID integer NOT NULL, hID integer NOT NULL, bID integer, "
                            + "startDate DATE NOT NULL, endDate DATE, respDoctor varchar(50) NOT NULL, currentDiagnosis varchar(500), registrationFee INTEGER NOT NULL, "
                            + "FOREIGN KEY(hID) REFERENCES Hospital(hID))");

            // MedicalRecords
            stmt.executeUpdate(
                    "create table MedicalRecords(mID integer NOT NULL UNIQUE PRIMARY KEY,cID integer NOT NULL, "
                            + "respNurse varchar(50), prescriptions varchar(500), diagnosisDetails varchar(500), treatment varchar(500), test varchar(500), "
                            + "result varchar(500), consultationfee integer NOT NULL, testfee integer NOT NULL, treatmentfee integer NOT NULL, FOREIGN KEY(cID) REFERENCES CheckIn(cID))");

            // BillingAccounts
            stmt.executeUpdate(
                    "create table BillingAccounts(baID integer NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT, pID integer NOT NULL, payerSSN varchar(11),"
                            + " billingAddress varchar(150) NOT NULL, paymentInfo varchar(100), registrationFee integer, accommodationFee integer, consultationFee integer, testFee integer, treatmentFee integer, specDailyFee integer, visitDate DATE NOT NULL);");

        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This is the function that populates the data values for all of the tables.
     *
     * @param stmt is the Statement object used to execute MariaDB statements
     */
    static void populateDemoTables(Statement stmt) {
        // populate data
        try {
            // Admin
            stmt.executeUpdate("insert into Admin(aID, aName) VALUES (11101, 'amagant'), (22201, 'jasalina')");

            // Patient #1
            stmt.executeUpdate(
                    "insert into Patient(pID, pName, DOB, gender, patientAge, patientPhone, patientAddress, status) VALUES(3001, 'John', '1980-02-22', 'M', 39, 513, '81 ABC St, NC 27', 'Treatment complete')");

            // Patient #2
            stmt.executeUpdate(
                    "insert into Patient(pID, pName, DOB, gender, patientAge, patientPhone, patientAddress, status) VALUES(3002, 'Jason', '1999-04-22', 'M', 20, 418, '82 ABC St, NC 27', 'In Treatment')");

            // hospital #1
            stmt.executeUpdate(
                    "insert into Hospital(hID, aID, hAddress, hPhone, spec1, s1CostPerDay, spec2, s2CostPerDay, capacity) VALUES (111, 11101, '111 St NC, 111', '101', 'pediatrics', 10, 'neurology', 15, 100)");

            // hospital #2
            stmt.executeUpdate(
                    "insert into Hospital(hID, aID, hAddress, hPhone, spec1, s1CostPerDay, spec2, s2CostPerDay, capacity) VALUES (222, 22201, '222 St NC, 222', '202', 'cardiology', 20, 'oncology', 25, 200)");

            // Staff Operator (Registration office)
            stmt.executeUpdate(
                    "insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, "
                            + "specPosition, staffPhone, email) VALUES (1001, 111, 'Simpson', '21 ABC St, NC 27', 'F', 36, 'Biller', 'Billing', 'Account Supervisor', '919', '1001@gmail.com')");

            // Nurse #1
            stmt.executeUpdate(
                    "insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, "
                            + "specPosition, staffPhone, email) VALUES (1002, 111, 'David', '22 ABC St, NC 27', 'M', 45, 'Nurse', 'Neurology', 'Senior Nurse', '123', '1002@gmail.com')");

            // Nurse #2
            stmt.executeUpdate(
                    "insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, "
                            + "specPosition, staffPhone, email) VALUES (1005, 222, 'Ruth', '23 ABC St, NC 27', 'F', 35, 'Nurse', 'pediatrics', 'Assistant Nurse', '456', '1005@gmail.com')");

            // Doctor #1
            stmt.executeUpdate(
                    "insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, "
                            + "specPosition, staffPhone, email) VALUES (1003, 111, 'Lucy', '42 ABC St, NC 27', 'F', 40, 'Doctor', 'pediatrics', 'Senior Surgeon', '631', '1003@gmail.com')");

            // Doctor #2
            stmt.executeUpdate(
                    "insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, "
                            + "specPosition, staffPhone, email) VALUES (1010, 111, 'Steven', '48 ABC St, NC 27', 'M', 65, 'Doctor', 'pediatrics', 'Senior Surgeon', '632', '1010@gmail.com')");

            // Doctor #3
            stmt.executeUpdate(
                    "insert into Staff(staffID, hID, staffName, homeAddress, sgender, age, jobTitle, department, "
                            + "specPosition, staffPhone, email) VALUES (1004, 222, 'Joseph', '51 ABC St, NC 27', 'M', 41, 'Doctor', 'cardiology', 'cardiologist', '327', '1004@gmail.com')");

            // Beds #1
            stmt.executeUpdate(
                    "insert into Beds(bID, hID, spec, sChargePerDay, staffID, pID, reserved) VALUES (5001, 111, 'neurology', 15, 1002, 3001, 1)");

            // Beds #2
            stmt.executeUpdate(
                    "insert into Beds(bID, hID, spec, sChargePerDay, staffID, pID, reserved) VALUES (5002, 111, 'neurology', 15, 1002, 3002, 1)");

            // Check-in/out #1
            stmt.executeUpdate(
                    "insert into CheckIn(pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3001, 111, 5001, '2019-08-05', 1003, 'abc', 20)");

            // Check-in/out #2
            stmt.executeUpdate(
                    "insert into CheckIn(pID, hID, bID, startDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3002, 111, 5002, '2019-10-15', 1003, 'def', 20)");

            // Possibly revise for startDate and endDate for MedicalRecords
            // instead of creating new checkins

            // Check-in/out # 3 for MR #1
            stmt.executeUpdate(
                    "insert into CheckIn(pID, hID, bID, startDate, endDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3001, 111, 5001, '2019-08-05', '2019-08-31', 1003, 'abc', 20)");

            // MedicalRecords #1
            stmt.executeUpdate(
                    "insert into MedicalRecords(mID ,cID, prescriptions, diagnosisDetails, treatment, test, result, consultationfee, testfee, treatmentfee) VALUES(2001, 3, 'antibiotics', 'Testing for TB', 'TB treatment', 'TB blood test', 'positive', 50, 75, 200 )");

            // Check-in/out # 4 FOR MR #2
            stmt.executeUpdate(
                    "insert into CheckIn(pID, hID, bID, startDate, endDate, respDoctor, currentDiagnosis, registrationFee) VALUES (3001, 111, 5001, '2019-09-01', '2019-09-16', 1003, 'abc', 20)");

            // MedicalRecords #2
            stmt.executeUpdate(
                    "insert into MedicalRecords(mID ,cID, prescriptions, diagnosisDetails, treatment, test, result, consultationfee, testfee, treatmentfee) "
                            + "VALUES(2002, 4, 'continue antibiotics', 'Testing for TB', 'Not required', 'X-ray chest (TB) Advanced', 'negative', 0, 125, 0 )");
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    /**
     * This method is used to test all of the currently implemented functions
     * 
     * @param conn is a working connection to the SQL database.
     * @param stmt is the Statement object used for executing SQL queries.
     */
    static void tests(Connection conn, Statement stmt) {
        System.out.println("TESTS FOR INFORMATION PROCESSING: ");
        System.out.println("\n\nTesting adding a hospital: ");
        enterHospital(stmt, "999", "22201", "111 Hospital Plaza", "704-919-3388", "dental", "500", "dermatology", "20",
                "85");
        showHospital(stmt);
        System.out.println("\n\nTesting updating a hospital: ");

        updateHospital(stmt, "999", "111 Hospital Blvd", "704-919-3388", "dental", "500", "Proctology", "20", "100");

        showHospital(stmt);
        System.out.println("\n\nTesting deleting a hospital: ");
        deleteHospital(stmt, "999");
        showHospital(stmt);
        
        System.out.println("\n\nTesting adding a Patient: ");
        enterPatient(stmt, "440", "90-36-2352", "Ellie Warren", "1999-10-01", "F", "21", "555-555-5555", "NYC", "RECOVERY");
        showPatient(stmt);
        System.out.println("\n\nTesting updating a Patient: ");
        updatePatient(stmt, "440", "90-36-2352", "Ellizabeth Warren", "1989-10-01", "F", "31", "555-555-5555", "NYC", "RECOVERED");
        showPatient(stmt);
        System.out.println("\n\nTesting deleting a Patient: ");
        deletePatient(stmt, "440");
        showPatient(stmt);
        
        System.out.println("\n\nTesting adding Staff: ");
        enterStaff(stmt, "440", "222", "Dr Jekyll", "somewhere", "Hospital 2 Basement", "M", "45", "doctor", "psych_ward", "psychology", "919-333-3333", "hyde@yahoo.com");
        showStaff(stmt);
        System.out.println("\n\nTesting updating Staff: ");
        updateStaff(stmt, "440", "Dr Hyde", "elsewhere", "Hospital 2 Basement", "M", "45", "doctor", "er", "surgery", "919-333-3333", "hyde@yahoo.com");
        showStaff(stmt);
        System.out.println("\n\nTesting deleting Staff: ");
        deleteStaff(stmt, "440");
        showStaff(stmt);
        
        System.out.println("\n\nTesting Bed Checking: ");
        System.out.println("Checking bed 5001");
        checkBeds(stmt, "5001");
        showBeds(stmt);
        System.out.println("\n\nTesting Releasing a Bed: ");
        releaseBed(stmt, "5001");
        showBeds(stmt);
        System.out.println("\n\nTesting Bed Checking Again: ");
        System.out.println("Checking bed 5001");
        checkBeds(stmt, "5001");
        System.out.println("\n\nTesting Check Beds by Speciality: ");
        System.out.println("Checking neurology: ");
        checkBedsBySpeciality(stmt, "111", "neurology");
        System.out.println("\n\nTesting Reserving/Assigning Beds: ");
        System.out.println("Assiging Bed 5001: ");
        assignPatientToBed(stmt, "3001", "5001");
        showBeds(stmt);
        System.out.println("\n\nTesting Bed Checking YET Again: ");
        System.out.println("Checking bed 5001");
        checkBeds(stmt, "5001");
        
        System.out.println("\n\nTesting Creating a CheckIn: ");
        createCheckIn(stmt, "3001", "222", "5005", "2019-11-17", "1004", "CHECKINITIS", "42");
        showCheckInOut(stmt);
        System.out.println("\n\nTesting Checking Out: ");
        checkOut(stmt, "5", "2019-11-18"); 
        showCheckInOut(stmt); 
        
        System.out.println("\n\nTransferring a Patient: ");
        System.out.println("First Create a new CheckIn");
        createCheckIn(stmt, "3001", "222", "5008", "2019-11-19", "1004", "TRANSFERITIS", "888");
        showCheckInOut(stmt);
        System.out.println("\nThen Conduct the Transfer: ");
        try{
          transferPatient(conn, "6", "3001", "111", "5002", "2019-11-20", "1010", "TRANSFERSUCCESS", "21");
        }catch (Exception e){
          e.printStackTrace();
        }
        showCheckInOut(stmt);
        
        System.out.println("\n\nTESTING MEDICAL RECORD MANAGEMENT");
        System.out.println("\n\nTesting Entering a Medical Record: ");
        enterMedicalRecords(stmt, "2003", "6", "prescription", "critical", "toolate", "Donttest", "TheEnd", "1000", "50000", "6000"); 
        showMedicalRecords(stmt);
        System.out.println("\n\nTesting Updating a Medical Record: ");
        updateMedicalRecords(stmt, "2003", "prescription2", "StillAlive", "Hope", "Try", "Chance", "99", "555", "300");
        showMedicalRecords(stmt);
        
        System.out.println("\n\nTESTING BILLING ACCOUNT MANAGEMENT");
        System.out.println("\n\nCreating a Billing Account: ");
        System.out.println("First Release Bed");
        releaseBed(stmt, "5001");
        try{
            createBillingAccount(conn, "111", "3001", "", "abcd street", "check", "1", "2", "3", "4", "5", "6", "2020-12-04");
            createBillingAccount(conn, "111", "3002", "999-22-9999", "abcd blvd", "credit", "11", "22", "33", "44", "55", "66", "2020-10-04");
        } catch (Exception e){
            e.printStackTrace();
        }
        showBillingAccounts(stmt);
        
        System.out.println("\n\nUpdating BillingAccount 1: ");
        updateBillingAccount(stmt, "1", "3001", "6SSN", "Chee", "cash", "20", "5", "50", "75", "200", "15", "2020-11-04");
        showBillingAccounts(stmt);
        
        
        System.out.println("\n\nTESTING REPORTS GENERATION");
        System.out.println("\n\nReporting Billing History of Only a Single Patient: ");
        reportBillingHistory(stmt, "2020-01-20", "2020-12-01", "3001");
        
        System.out.println("\n\nReporting Usage Status of All Hospitals: ");
        reportUsageStatus(stmt); 
        
        System.out.println("\n\nReporting Number of Patients in Specified Month at a Hospital: ");
        reportPatientsPerMonth(stmt, "111", "8", "2019");
        
        System.out.println("\n\nReporting Usage Percentage of a Specific Hospital (Beds used /Total Capacity): ");
        reportHospitalPercentage(stmt, "111"); 
        
        System.out.println("\n\nReporting The Doctors that a Specific Patient is Seeing: ");
        reportDoctorByPatient(stmt, "3001");
        reportDoctorByPatient(stmt, "3002");
        
        System.out.println("\n\nReporting Hospitals Grouped By Their Speciality: ");
        reportAllHospitalSpeciality(stmt);

    }

}
