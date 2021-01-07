package com.techlab.contact;

import java.sql.*;
import java.util.Scanner;

public class ContactTest {
	static Connection conn = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/swabhav techlabs?user=root&password=root";
            conn = DriverManager.getConnection(url);
            System.out.println("connected successfully");
        }catch(Exception e){
           System.out.println("Unsuccessfull Connection");   
        }
		
		int choice;
		do {
			System.out.println();
			System.out.println("1. Display Contact List\n2. Add new Contact\n3. Edit existing Contact\n4. Delete existing Contact\n5. Exit");
			System.out.println("Make a choice from above");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				displayContact();
				break;
			case 2:
				addContact();
				break;
			case 3:
				editContact();
				break;
			case 4:
				deleteContact();
				break;
			}
		}while(choice!=5);

	}

	private static void deleteContact() throws SQLException {
		// TODO Auto-generated method stub
		int id;
		Scanner sc = new Scanner(System.in);
		displayContact();
		System.out.println("Enter which id to delete? ");
		id=sc.nextInt();
		String sql = "delete from contactapp where id=?";
		try {
			pst= conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pst.close();
			
		}
	}

	private static void editContact() throws SQLException {
		
		int id;
		String fname;
		displayContact();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter which id to Edit? ");
		id=sc.nextInt();
		System.out.println("Enter First name: ");
		fname = sc.next();	
		String sql = "update contactapp set fname=? where id=?";
		try {
			pst= conn.prepareStatement(sql);
			pst.setString(1, fname);
			pst.setInt(2,id);
			pst.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pst.close();
		}
		
	}

	private static void addContact() throws SQLException {
		// TODO Auto-generated method stub
		String fname,lname,number,emailid;
		Scanner scs = new Scanner(System.in);
		System.out.println("Enter First name: ");
		fname = scs.nextLine();
		System.out.println("Enter Last name: ");
		lname=scs.nextLine();
		System.out.println("Enter phone number: ");
		number = scs.nextLine();
		System.out.println("Enter Email id: ");
		emailid=scs.nextLine();
		String sql = "insert into contactapp(fname,lname,number,emailid)values(?,?,?,?)";
		try {
			pst= conn.prepareStatement(sql);
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setString(3, number);
			pst.setString(4, emailid);
			pst.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pst.close();
		}
		
	}

	private static void displayContact() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from contactapp";
		try {
			pst= conn.prepareStatement(sql);
			rst = pst.executeQuery();
			while(rst.next())
				System.out.println(rst.getInt(1)+" | "+ rst.getString(2)+" | "+ rst.getString(3)+" | "+rst.getString(4)+" | "+rst.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pst.close();
			rst.close();
		}
		
	}

}
