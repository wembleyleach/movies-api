CREATE TABLE IF NOT EXISTS movies (
  id              VARCHAR(16) NOT NULL,
  title           TEXT,
  synopsis     TEXT,
  duration        TEXT,
  image_url TEXT
);

CREATE TABLE IF NOT EXISTS actors (
  id              VARCHAR(10) NOT NULL,
  name TEXT,
  role TEXT,
  movie_id VARCHAR(16),
  FOREIGN KEY (movie_id) REFERENCES movies(id)
);