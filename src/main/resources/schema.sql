CREATE TABLE IF NOT EXISTS movies (
  id        VARCHAR(64) NOT NULL,
  title     TEXT,
  synopsis  TEXT,
  duration  TEXT,
  image_url TEXT,
  rating    INT DEFAULT 5,
  playback  TEXT
);

CREATE TABLE IF NOT EXISTS actors (
  id       VARCHAR(64) NOT NULL,
  name     TEXT,
  role     TEXT,
  movie_id VARCHAR(64),
  FOREIGN KEY (movie_id) REFERENCES movies (id)
);

CREATE TABLE IF NOT EXISTS genres (
  id       VARCHAR(64) NOT NULL,
  name     TEXT,
  movie_id VARCHAR(64),
  FOREIGN KEY (movie_id) REFERENCES movies (id)
);