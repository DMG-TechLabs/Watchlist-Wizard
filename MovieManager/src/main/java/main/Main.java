package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Database;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.databridge.helpers.ResultProcessor;

public class Main {
	public static void main(String[] args) throws SQLException {

		ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select().from("Images").build());

		while(rs.next()){
			System.out.println(rs.getString(2));
		}
	}
}
