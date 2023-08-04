CREATE TABLE IF not exists BOOK (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        TITLE TEXT NOT NULL,
                                        PUBLISHER_NAME TEXT NOT NULL,
                                        YEAR_OF_PUBLICATION INTEGER NOT NULL,
                                        SUBJECT TEXT NOT NULL,
                                        AUTHOR_ID INTEGER,
                                        FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(id)


);

CREATE TABLE IF NOT exists AUTHOR (
id INTEGER PRIMARY KEY AUTOINCREMENT,
FIRST_NAME TEXT NOT NULL,
LAST_NAME TEXT NOT NULL

);

