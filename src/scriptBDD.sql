USE sae_secouriste;

create user admin identified by 'admin';
GRANT ALL PRIVILEGES ON *.* TO admin WITH GRANT OPTION;

DROP TABLE IF EXISTS Affectation;
DROP TABLE IF EXISTS Necessite;
DROP TABLE IF EXISTS Possede;
DROP TABLE IF EXISTS Besoin;
DROP TABLE IF EXISTS Disponibilite;
DROP TABLE IF EXISTS DPS;
DROP TABLE IF EXISTS Competence;
DROP TABLE IF EXISTS Journee;
DROP TABLE IF EXISTS Secouriste;
DROP TABLE IF EXISTS Site;
DROP TABLE IF EXISTS Sport;

-- Table Sport
CREATE TABLE Sport (
    code VARCHAR(20),
    nom VARCHAR(20) NOT NULL,
    PRIMARY KEY (code)
);

-- Table Site
CREATE TABLE Site (
    code VARCHAR(20),
    nom VARCHAR(50) NOT NULL,
    longitude FLOAT NOT NULL,
    latitude FLOAT NOT NULL,
    PRIMARY KEY (code)
);

-- Table Secouriste
CREATE TABLE Secouriste (
    id INT,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    dateNaissance VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    tel VARCHAR(20) NOT NULL,
    adresse VARCHAR(100) NOT NULL,
    username VARCHAR(30) NOT NULL,
    mdp VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

-- Table Journee
CREATE TABLE Journee (
    id BIGINT,
    jour INT NOT NULL,
    mois INT NOT NULL,
    annee INT NOT NULL,
    PRIMARY KEY (id)
);

-- Table Competence
CREATE TABLE Competence (
    intitule VARCHAR(100),
    PRIMARY KEY (intitule)
);

-- Table DPS
CREATE TABLE DPS (
    id INT,
    horaire_depart INT,
    horaire_fin INT,
    laJournee BIGINT,
    leSport VARCHAR(20),
    leSite VARCHAR(20),
    PRIMARY KEY (id),
    FOREIGN KEY (laJournee) REFERENCES Journee(id),
    FOREIGN KEY (leSite) REFERENCES Site(code),
    FOREIGN KEY (leSport) REFERENCES Sport(code)
);

-- Table Disponibilite
CREATE TABLE Disponibilite (
    leSecouriste INT,
    laJournee BIGINT,
    PRIMARY KEY (leSecouriste, laJournee),
    FOREIGN KEY (laJournee) REFERENCES Journee(id),
    FOREIGN KEY (leSecouriste) REFERENCES Secouriste(id)
);

-- Table Besoin
CREATE TABLE Besoin (
    nombre INT,
    leDPS INT,
    laCompetence VARCHAR(100),
    PRIMARY KEY (leDPS, laCompetence),
    FOREIGN KEY (leDPS) REFERENCES DPS(id),
    FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
);

-- Table Possede
CREATE TABLE Possede (
    leSecouriste INT,
    laCompetence VARCHAR(100),
    PRIMARY KEY (leSecouriste, laCompetence),
    FOREIGN KEY (leSecouriste) REFERENCES Secouriste(id),
    FOREIGN KEY (laCompetence) REFERENCES Competence(intitule)
);

-- Table Necessite
CREATE TABLE Necessite (
    competence1 VARCHAR(100),
    competence2 VARCHAR(100),
    PRIMARY KEY (competence1, competence2),
    FOREIGN KEY (competence1) REFERENCES Competence(intitule),
    FOREIGN KEY (competence2) REFERENCES Competence(intitule)
);

-- Table Affectation
CREATE TABLE Affectation (
    leSecouriste INT,
    laCompetence VARCHAR(100),
    leDPS INT,
    PRIMARY KEY (leSecouriste, laCompetence, leDPS),
    FOREIGN KEY (leSecouriste) REFERENCES Secouriste(id),
    FOREIGN KEY (laCompetence) REFERENCES Competence(intitule),
    FOREIGN KEY (leDPS) REFERENCES DPS(id)
);

INSERT INTO Competence(intitule) VALUES ("PSE1");
INSERT INTO Competence(intitule) VALUES ("PSE2");
INSERT INTO Competence(intitule) VALUES ("CE");
INSERT INTO Competence(intitule) VALUES ("CP");
INSERT INTO Competence(intitule) VALUES ("CO");
INSERT INTO Competence(intitule) VALUES ("SSA");
INSERT INTO Competence(intitule) VALUES ("VPSP");
INSERT INTO Competence(intitule) VALUES ("PBC");
INSERT INTO Competence(intitule) VALUES ("PBF");