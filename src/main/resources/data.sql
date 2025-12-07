INSERT INTO Categorie_TVA (code_categorie, description) VALUES
('NORMAL', 'Taux Normal'),
('INTER', 'Taux Intermédiaire'),
('REDUIT', 'Taux Réduit');

INSERT INTO Taux_Tva (id_tva, code_categorie, taux, date_debut) VALUES
('019af8e5-93ef-7fa0-ad1c-0beff9ea2cd2', 'NORMAL', 0.20, '2014-01-01'),
('019af8e5-c274-7eab-bbc3-1905ec886c7c', 'INTER', 0.07, '2014-01-01'),
('019af8e5-e899-7782-a4e9-737a9c6df182','REDUIT', 0.055, '2014-01-01');

INSERT INTO Produit (id_produit, designation, prix_ht, code_categorie_tva) VALUES
('019af8e7-44c8-7f70-aa40-2c1106a95e32' ,'Mon produit C', 70000.00, 'NORMAL'),
('019af8e7-6912-7e34-88c8-cf50669a56e8' ,'Mon produit A', 1500.00, 'REDUIT'),
('019af8e7-88b4-7cfb-a656-932d6bed99e5' ,'Mon produit D', 3000.00, 'NORMAL'),
('019af8e7-a7b2-728c-abd9-74b7b5e54046' ,'Mon produit B', 4000.00, 'INTER');

INSERT INTO Client (id_client, code_client, nom, adresse, code_postal, ville) VALUES
(1, 'CU2203-0005', 'Mon client SAS', '45, rue du test', '75016', 'PARIS');

INSERT INTO Facture (id_facture, reference_facture, id_client, date_emission, date_echeance, statut, total_ht, total_ttc_globale) VALUES
(1, '2022-0025', 1, '2018-07-26', '2018-07-31', 'PAYEE', 84000.00, 99325.00);

INSERT INTO Detail_Facture(id_facture, id_produit, id_tva, designation_produit, prix_unitaire, quantite) VALUES
(1, '019af8e7-44c8-7f70-aa40-2c1106a95e32', '019af8e5-93ef-7fa0-ad1c-0beff9ea2cd2', 'Mon produit C', 70000.00, 1),
(1, '019af8e7-6912-7e34-88c8-cf50669a56e8', '019af8e5-e899-7782-a4e9-737a9c6df182', 'Mon produit A', 1500.00, 2),
(1, '019af8e7-88b4-7cfb-a656-932d6bed99e5', '019af8e5-93ef-7fa0-ad1c-0beff9ea2cd2', 'Mon produit D', 3000.00, 1),
(1, '019af8e7-a7b2-728c-abd9-74b7b5e54046', '019af8e5-c274-7eab-bbc3-1905ec886c7c', 'Mon produit B', 4000.00, 2);