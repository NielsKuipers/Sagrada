-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO pattern_card (pattern, difficulty) VALUES ('040y6r020000rp1by000', 3);
INSERT INTO pattern_card (pattern, difficulty) VALUES ('0br000450bb20r56r310', 5);
INSERT INTO pattern_card (pattern, difficulty) VALUES ('r0b0y4p3g20105000600', 4);
INSERT INTO pattern_card (pattern, difficulty) VALUES ('001001g3b2b546g0b5g0', 6);
INSERT INTO pattern_card (pattern, difficulty) VALUES ('00g002y5b10r3p010604', 4);
INSERT INTO pattern_card (pattern, difficulty) VALUES ('1r30654r20005r10003r', 5);
INSERT INTO pattern_card (pattern, difficulty) VALUES ('341500620y000yr50yr6', 5);
INSERT INTO pattern_card (pattern, difficulty) VALUES ('00r50p40g3600b00y200', 3);
INSERT INTO pattern_card (pattern, difficulty) VALUES ('4025g006g203g405g100', 5);
INSERT INTO game (die, state) VALUES ('R4Y2B3B6', 'NEW');
INSERT INTO game (die, state) VALUES ('G2P5R2P3', 'ONGOING');
INSERT INTO player (name) VALUES ('Maurits');
INSERT INTO player (name) VALUES ('Frank');
INSERT INTO objective_card (name, description, type, config) VALUES ('Blue tints', 'Sum of values on blue die', 'Private', 'B');
INSERT INTO objective_card (name, description, type, config) VALUES ('Yellow tints', 'Sum of values on yellow die', 'Private', 'Y');
INSERT INTO objective_card (name, description, type, config) VALUES ('Green tints', 'Sum of values on green die', 'Private', 'G');
INSERT INTO objective_card (name, description, type, config) VALUES ('Purple tints', 'Sum of values on purple die', 'Private', 'P');
INSERT INTO objective_card (name, description, type, config) VALUES ('Red tints', 'Sum of values on red die', 'Private', 'R');
INSERT INTO game_player (game_id, player_id, player_score, pattern_card_id, tokens, objective_card_id) VALUES (1, 1, 250, 1, 3, 1);
INSERT INTO game_player (game_id, player_id, player_score, pattern_card_id, tokens, objective_card_id) VALUES (1, 2, 100, 5, 4, 1);
