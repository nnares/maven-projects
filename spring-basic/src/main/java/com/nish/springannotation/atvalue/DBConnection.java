package com.nish.springannotation.atvalue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

/*
*	Default bean name for @Component
*   if(first two characters are uppercase) {
* 		class 	- 	com.xyz.FOoServiceImpl, com.nish.springannotation.atvalue.DBConnection
* 		name 	- 	FOoServiceImpl, DBConnection
* 	}else{
*		class  	- 	com.xyz.FooServiceImpl, com.nish.springannotation.atautowired.model.VehicleDriver
* 		name 	-	fooServiceImpl, vehicleDriver
*  }
* */

@Component
public class DBConnection {

	@Value("${mysql.db.driver}")
	private String driverClass;
	@Value("${mysql.db.jdbc.url}")
	private String dbURL;
	@Value("${mysql.db.username}")
	private String userName;
	@Value("${mysql.db.password}")
	private char[] password;

	public DBConnection() {
	}

	public void printDBConfig() {
		System.out.println("---------------------DB Config Details----------------------");
		System.out.println("Driver Class = " + driverClass);
		System.out.println("DB URL = " + dbURL);
		System.out.println("User Name = " + userName);
		// Never do below in production environment :D
		System.out.println("Password = " + String.valueOf(password));
	}

	public Connection getDBConnection() {

		Connection connection = null;
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(
					dbURL, userName, String.valueOf(password));

		} catch (Exception e) {
			System.out.println(e);
		}

		return connection;
	}

}