CREATE SCHEMA IF NOT EXISTS VIDEOTHEQUE_SCHEMA AUTHORIZATION SA;
SET SCHEMA VIDEOTHEQUE_SCHEMA;

CREATE TABLE category (
                          id   INTEGER PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL
);

CREATE TABLE actor (
                       id   INTEGER PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(70) NOT NULL,
                       bio VARCHAR(550)
);

CREATE TABLE film (
                        id INTEGER PRIMARY KEY AUTO_INCREMENT,
                        title VARCHAR(50) NOT NULL,
                        prod_year INTEGER,
                        description VARCHAR(550),
                        release_date DATE,
                        duration INTEGER,
                        id_category INT,
                        foreign key (id_category) references category(id)
);


CREATE TABLE film_actor (
                                film_id   INT,
                                actor_id   INT,
                                primary key (film_id, actor_id),
                                foreign key (film_id) references film(id),
                                foreign key (actor_id) references actor(id)
);
