DROP TABLE IF EXISTS Categorie_TVA CASCADE;
DROP TABLE IF EXISTS Taux_Tva CASCADE;
DROP TABLE IF EXISTS Client CASCADE;
DROP TABLE IF EXISTS Produit CASCADE;
DROP TABLE IF EXISTS Facture CASCADE;
DROP TABLE IF EXISTS Detail_Facture CASCADE;

CREATE TABLE IF NOT EXISTS Categorie_TVA (
    code_categorie VARCHAR(50) PRIMARY KEY,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Taux_Tva (
    id_tva UUID PRIMARY KEY, -- UUID
    code_categorie VARCHAR(50) NOT NULL,
    taux NUMERIC(5, 4) NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE,

    FOREIGN KEY (code_categorie) REFERENCES Categorie_TVA(code_categorie)
);

CREATE TABLE IF NOT EXISTS Client (
    id_client SERIAL PRIMARY KEY,
    code_client VARCHAR(100) UNIQUE,
    nom VARCHAR(100) NOT NULL,
    adresse VARCHAR(500),
    code_postal VARCHAR(50),
    ville VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Produit (
    id_produit UUID PRIMARY KEY, -- UUID
    designation VARCHAR(100) NOT NULL,
    prix_ht NUMERIC(10, 2) NOT NULL,
    code_categorie_tva VARCHAR(50) NOT NULL,

    FOREIGN KEY (code_categorie_tva) REFERENCES Categorie_TVA(code_categorie)
);

CREATE TABLE IF NOT EXISTS Facture (
    id_facture SERIAL PRIMARY KEY,
    reference_facture VARCHAR(100) UNIQUE NOT NULL,
    id_client INTEGER NOT NULL,
    date_emission DATE NOT NULL,
    date_echeance DATE,
    statut VARCHAR(10) CHECK(statut IN ('BROUILLON', 'VALIDEE', 'PAYEE')) DEFAULT 'BROUILLON',
    total_ht NUMERIC(10, 2) DEFAULT 0,
    total_ttc_globale NUMERIC(10, 2) DEFAULT 0,

    FOREIGN KEY (id_client) REFERENCES Client(id_client)
);

CREATE TABLE IF NOT EXISTS Detail_Facture (
    id_detail SERIAL PRIMARY KEY,
    id_facture INTEGER NOT NULL,
    id_produit UUID,
    id_tva UUID NOT NULL,

    designation_produit VARCHAR(100) NOT NULL,
    prix_unitaire NUMERIC(10, 2) NOT NULL,
    quantite INTEGER NOT NULL,

    FOREIGN KEY (id_facture) REFERENCES Facture(id_facture) ON DELETE CASCADE,
    FOREIGN KEY (id_produit) REFERENCES Produit(id_produit) ON DELETE SET NULL,
    FOREIGN KEY (id_tva) REFERENCES Taux_Tva(id_tva)
);