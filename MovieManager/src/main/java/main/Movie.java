package main;

import Database.DBMethods;

public class Movie implements Comparable<Movie> {

	private String Title;
	private String Year;
	private String Rated;
	private String Released;
	private String Runtime;
	private String Genre;
	private String Director;
	private String Writer;
	private String Actors;
	private String Plot;
	private String Language;
	private String Country;
	private String Awards;
	private String Poster;
	private String Type;
	private String imdbRating;
	private String imdbID;

	private String[] info;
	private String directory;
	private String filename;

	public Movie() {
		info = new String[DBMethods.getDBFields().length];
	}

	Movie(String Title) {
		this.Title = Title;
	}

	public String[] getInfo() {

		info[0] = Title;
		info[1] = Year;
		info[2] = Rated;
		info[3] = Released;
		info[4] = Runtime;
		info[5] = Director;
		info[6] = Writer;
		info[7] = Actors;
		info[8] = Plot;
		info[9] = Language;
		info[10] = Country;
		info[11] = Awards;
		info[12] = Poster;
		info[13] = imdbRating;
		info[14] = imdbID;

		return info;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String Year) {
		this.Year = Year;
	}

	public String getRated() {
		return Rated;
	}

	public void setRated(String Rated) {
		this.Rated = Rated;
	}

	public String getReleased() {
		return Released;
	}

	public void setReleased(String Released) {
		this.Released = Released;
	}

	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String Runtime) {
		this.Runtime = Runtime;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String Genre) {
		this.Genre = Genre;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String Director) {
		this.Director = Director;
	}

	public String getWriter() {
		return Writer;
	}

	public void setWriter(String Writers) {
		this.Writer = Writers;
	}

	public String getActors() {
		return Actors;
	}

	public void setActors(String Actors) {
		this.Actors = Actors;
	}

	public String getPlot() {
		return Plot;
	}

	public void setPlot(String Plot) {
		this.Plot = Plot;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String Language) {
		this.Language = Language;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

	public String getAwards() {
		return Awards;
	}

	public void setAwards(String Awards) {
		this.Awards = Awards;
	}

	public String getPoster() {
		return Poster;
	}

	public void setPoster(String Poster) {
		this.Poster = Poster;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		if (imdbID == "") {
			this.imdbID = null;
		} else {
			this.imdbID = imdbID;
		}
	}

	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	public void setInfo(String[] info) {
		this.info = info;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void addInfo(String[] info) {
		setInfo(info);

		setTitle(info[0]);
		setYear(info[1]);
		setRated(info[2]);
		setReleased(info[3]);
		setRuntime(info[4]);
		setGenre(info[5]);
		setDirector(info[6]);
		setWriter(info[7]);
		setActors(info[8]);
		setPlot(info[9]);
		setLanguage(info[10]);
		setCountry(info[11]);
		setAwards(info[12]);
		setPoster(info[13]);
		//setType(info[14]);
		setImdbRating(info[14]);
		setImdbID(info[15]);
		setDirectory(info[16]);
		setFilename(info[17]);
	}

	@Override
	public String toString() {
		return "Title: " + Title + "\nYear: " + Year + "\nRated: " + Rated + "\nReleased: " + Released + "\nRuntime: " + Runtime + "\nGenre: " + Genre + "\nDirector: " + Director + "\nWriters: " + Writer + "\nActors: " + Actors + "\nPlot: " + Plot + "\nLanguage: " + Language + "\nCountry: " + Country + "\nAwards: " + Awards + "\nPoster URL: " + Poster + "\nType: " + Type + "\nIMDb Rating: " + imdbRating + "\nIMDb ID: " + imdbID + "\nDirectory: " + directory + "\nFilename: " + filename;
	}

	@Override
	public int compareTo(Movie m) {
		return this.Title.compareTo(m.Title);
	}

	public boolean equals(String title) {
		return this.Title.compareTo(title) == 0;
	}
}
