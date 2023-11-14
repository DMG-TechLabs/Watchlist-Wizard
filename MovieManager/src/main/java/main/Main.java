package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Database;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.databridge.helpers.ResultProcessor;

public class Main {
	public static void main(String[] args) throws SQLException {


		String column = "Movie_ID";
		ResultSet rs = Database.connection().executeQuery(new QueryBuilder().select(column).from("Movies").build());

		while(rs.next()){
				System.out.println(rs.getString(column));
		}
	}
}
