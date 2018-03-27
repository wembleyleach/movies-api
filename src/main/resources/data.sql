INSERT INTO movies(id, title, synopsis, duration) VALUES ('1', 'Kill Bill, Vol. 1', 'The lead character, called ''The Bride,'' was a member of the Deadly Viper Assassination Squad, led by her lover ''Bill.'' Upon realizing she was pregnant with Bill''s child, ''The Bride'' decided to escape her life as a killer. She fled to Texas, met a young man, who, on the day of their wedding rehearsal was gunned down by an angry and jealous Bill (with the assistance of the Deadly Viper Assassination Squad). Four years later, ''The Bride'' wakes from a coma, and discovers her baby is gone. She, then, decides to seek revenge upon the five people who destroyed her life and killed her baby. The saga of Kill Bill Volume I begins.', '1h11m');
INSERT INTO movies(id, title, synopsis, duration) VALUES ('2', 'Kill Bill, Vol. 2', 'The murderous Bride is back and she is still continuing her vengeance quest against her ex-boss, Bill, and taking aim at Bill''s younger brother Budd and Elle Driver, the only survivors from the squad of assassins who betrayed her four years earlier. It''s all leading up to the ultimate confrontation with Bill, the Bride''s former master and the man who ordered her execution!', '1h37m');

INSERT INTO actors(id, movie_id, name, role)
VALUES ('1', '1', 'Uma Thurman', 'The Bride');

INSERT INTO actors(id, movie_id, name, role)
VALUES ('2', '1', 'Lucy Liu', 'O-Ren Ishii');

INSERT INTO genres(id, name, movie_id)
VALUES ('1', 'action', '1');

INSERT INTO genres(id, name, movie_id)
VALUES ('2', 'crime', '1');

INSERT INTO genres(id, name, movie_id)
VALUES ('3', 'thriller', '1');

INSERT INTO genres(id, name, movie_id)
VALUES ('4', 'crime', '2');

INSERT INTO genres(id, name, movie_id)
VALUES ('5', 'thriller', '2');