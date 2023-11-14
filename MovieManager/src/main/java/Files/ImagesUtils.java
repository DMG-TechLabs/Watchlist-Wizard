package Files;

import Database.Database;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.databridge.connections.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import main.Movie;

/**
 *
 * @author kosta
 */
public class ImagesUtils {

	public static String SaveImg(String path, String url) throws IOException {
		// url = url.replaceAll(Pattern.quote("\\"), "");
		// url = url.replaceAll(Pattern.quote("300"), "700");
		// String ImageName = FilesList.GetOnlyPath(path, name)+name+".jpg";
		String ImageName = generateRandomString(10) + ".jpg";
		System.out.println(ImageName);
		if (url.contains("media-amazon")) {
			url = url.replaceAll(Pattern.quote("\\"), "");
			url = url.replaceAll(Pattern.quote("300"), "229");
		} else {
			url = "https://image.tmdb.org/t/p/original/" + url;
		}

		try (InputStream in = new URL(url).openStream()) {
			Files.copy(in, Paths.get(path + "\\" + ImageName));
		}

		return ImageName;
	}

	public static String SaveImg(String path, String url, String rescale) throws IOException {
		// url = url.replaceAll(Pattern.quote("\\"), "");
		// url = url.replaceAll(Pattern.quote("300"), "700");
		// String ImageName = FilesList.GetOnlyPath(path, name)+name+".jpg";
		String ImageName = generateRandomString(10) + ".jpg";
		System.out.println(ImageName);
		if (url.contains("media-amazon")) {
			url = url.replaceAll(Pattern.quote("\\"), "");
			url = url.replaceAll(Pattern.quote("300"), "229");
		} else {
			url = "https://image.tmdb.org/t/p/" + rescale + "/" + url;
		}

		try (InputStream in = new URL(url).openStream()) {
			String os = System.getProperty("os.name");
			if (os.toLowerCase().contains("windows")) {
				Files.copy(in, Paths.get(path + "\\" + ImageName));
			} else if (os.toLowerCase().contains("linux")) {
				Files.copy(in, Paths.get(path + "/" + ImageName));
			}
		}

		return ImageName;
	}

	public static void imageToDatabase(Movie movie) throws SQLException {
		String imageName = "ERROR";
		try {
			imageName = SaveImg(movie.getDirectory(), movie.getImagePath(), "w342");
		} catch (IOException ex) {
			Logger.getLogger(ImagesUtils.class.getName()).log(Level.SEVERE, null, ex);
		}

		System.out.println(imageName + " saved");
	}

	public static File delete(String dir) {
		File file = new File(dir);
		if (file.delete()) {
			System.out.println("Deleted the file: " + file.getName());
			return file;
		} else {
			System.out.println("Failed to delete the file.");
		}
		return null;
	}

	private static String generateRandomString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s = "";
		for (int i = 0; i < length; i++) {
			s = s + characters.charAt(new Random().nextInt(characters.length()));
		}

		return s;
	}
}
