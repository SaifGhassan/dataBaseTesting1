package dataBaseTesting.dataBaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	WebDriver driver = new ChromeDriver();
	Connection con;
	Statement stmt;
	ResultSet rs;
	String website = "https://smartbuy-me.com/account/register";
	int CustomerNumberInDatabase;
	String CustomerFirstNameInDatabase;
	String CustomerLastNameInDatabase;
	String email;
	String Password;

	@BeforeTest
	public void MySetup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "7654-SSS");
		driver.manage().window().maximize();
		driver.get(website);
	}

	@Test(priority = 1, enabled = true)
	public void addNewCustomer() throws SQLException {

		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'saif', 'ali', 'ahmad', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00);";

		stmt = con.createStatement();
		int RowInserted = stmt.executeUpdate(query);
		System.out.println(RowInserted);

	}

	@Test(priority = 2, enabled = true)
	public void updateCustomerInfo() throws SQLException {

		String query = "UPDATE customers SET ContactLastName = 'saif' WHERE customerNumber = 999;";
		stmt = con.createStatement();
		int RowInserted = stmt.executeUpdate(query);
		System.out.println(RowInserted);

	}

	@Test(priority = 3, enabled = true)
	public void readTheUpdatedData() throws SQLException {

		String query = "SELECT * FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);

		while (rs.next()) {

			CustomerNumberInDatabase = rs.getInt("customerNumber");
			CustomerFirstNameInDatabase = rs.getString("customerName").toString().trim();
			CustomerLastNameInDatabase = rs.getString("contactLastName");
			email = CustomerFirstNameInDatabase + CustomerLastNameInDatabase + "@gmail.com";
			Password = "11111111";
			System.out.println(CustomerNumberInDatabase);
			System.out.println(CustomerFirstNameInDatabase);
			System.out.println(CustomerLastNameInDatabase);
			System.out.println(email);

			driver.findElement(By.id("customer[first_name]")).sendKeys(CustomerFirstNameInDatabase);
			driver.findElement(By.id("customer[last_name]")).sendKeys(CustomerLastNameInDatabase);
			driver.findElement(By.id("customer[email]")).sendKeys(email);
			driver.findElement(By.id("customer[password]")).sendKeys(Password);

		}
	}

	@Test(priority = 4, enabled = true)
	public void DeleteCustomer() throws SQLException {
		String query = "DELETE FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		int RowInserted = stmt.executeUpdate(query);
		System.out.println(RowInserted);
	}

}
