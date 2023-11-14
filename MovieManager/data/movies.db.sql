BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Settings" (
	"theme"	TEXT,
	"player_path"	TEXT,
	"font"	TEXT
);
CREATE TABLE IF NOT EXISTS "Extensions" (
	"extension_id"	INTEGER NOT NULL UNIQUE,
	"extension"	TEXT UNIQUE,
	PRIMARY KEY("extension_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Directories" (
	"id"	INTEGER NOT NULL UNIQUE,
	"directory"	BLOB NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Actors" (
	"id"	INTEGER NOT NULL UNIQUE,
	"actor"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Writers" (
	"id"	INTEGER NOT NULL UNIQUE,
	"writer"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Directors" (
	"id"	INTEGER NOT NULL UNIQUE,
	"actor"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Countries" (
	"id"	INTEGER NOT NULL UNIQUE,
	"country"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Languages" (
	"id"	INTEGER NOT NULL UNIQUE,
	"language"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Scraped" (
	"movie_id"	INTEGER NOT NULL UNIQUE,
	"is_scraped"	INTEGER NOT NULL,
	PRIMARY KEY("movie_id")
);
CREATE TABLE IF NOT EXISTS "Categories" (
	"id"	INTEGER NOT NULL UNIQUE,
	"category"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Categories_Matching" (
	"category_id"	INTEGER NOT NULL,
	"movie_id"	INTEGER NOT NULL,
	PRIMARY KEY("category_id","movie_id")
);
CREATE TABLE IF NOT EXISTS "Actors_Matching" (
	"actor_id"	INTEGER NOT NULL,
	"movie_id"	INTEGER NOT NULL,
	PRIMARY KEY("actor_id","movie_id")
);
CREATE TABLE IF NOT EXISTS "Writers_Matching" (
	"writer_id"	INTEGER NOT NULL,
	"movie_id"	INTEGER NOT NULL,
	PRIMARY KEY("writer_id","movie_id")
);
CREATE TABLE IF NOT EXISTS "Directors_Matching" (
	"director_id"	INTEGER NOT NULL,
	"movie_id"	INTEGER NOT NULL,
	PRIMARY KEY("director_id","movie_id")
);
CREATE TABLE IF NOT EXISTS "Language_Matching" (
	"language_id"	INTEGER NOT NULL,
	"movie_id"	INTEGER NOT NULL,
	PRIMARY KEY("language_id","movie_id")
);
CREATE TABLE IF NOT EXISTS "Countries_Matching" (
	"country_id"	INTEGER NOT NULL,
	"movie_id"	INTEGER NOT NULL,
	PRIMARY KEY("country_id","movie_id")
);
CREATE TABLE IF NOT EXISTS "Rated_Matching" (
	"rated_id"	INTEGER NOT NULL,
	"movie_id"	INTEGER NOT NULL,
	PRIMARY KEY("rated_id","movie_id")
);
CREATE TABLE IF NOT EXISTS "Movies" (
	"movie_id"	INTEGER NOT NULL UNIQUE,
	"title"	TEXT NOT NULL,
	"year"	INTEGER,
	"runtime"	INTEGER,
	"plot"	TEXT,
	"image_url"	TEXT,
	"imdb_rating"	REAL,
	"imdb_id"	TEXT UNIQUE,
	"filepath"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("movie_id" AUTOINCREMENT)
);
COMMIT;
