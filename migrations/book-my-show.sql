-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE users
(
    id         NUMBER(10)   NOT NULL,
    first_name VARCHAR2(45) NULL,
    last_name  VARCHAR2(45) NULL,
    email_id   VARCHAR2(45) NOT NULL,
    password   VARCHAR2(45) NOT NULL,
    created_at TIMESTAMP(0) NOT NULL,
    updated_at TIMESTAMP(0) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT email_id_UNIQUE UNIQUE (email_id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER users_seq_tr
    BEFORE INSERT
    ON users
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT users_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE timings
(
    id         number(10)   NOT NULL,
    name       varchar2(45) DEFAULT NULL,
    start_time timestamp(0) DEFAULT NULL,
    end_time   timestamp(0) DEFAULT NULL,
    created_at timestamp(0) NOT NULL,
    updated_at timestamp(0) NOT NULL,
    PRIMARY KEY (id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE timings_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER timings_seq_tr
    BEFORE INSERT
    ON timings
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT timings_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE regions
(
    id          number(10)   NOT NULL,
    name        varchar2(45) DEFAULT NULL,
    region_type number(10)   DEFAULT NULL,
    parent_id   number(10)   DEFAULT NULL,
    created_at  timestamp(0) NOT NULL,
    updated_at  timestamp(0) NOT NULL,
    PRIMARY KEY (id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE regions_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER regions_seq_tr
    BEFORE INSERT
    ON regions
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT regions_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

CREATE INDEX parent_id_idx ON regions (parent_id);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE theatres
(
    id         number(10)   NOT NULL,
    name       varchar2(45) NOT NULL,
    address    varchar2(45) NOT NULL,
    region_id  number(10)   NOT NULL,
    created_at timestamp(0) NOT NULL,
    updated_at timestamp(0) NOT NULL,
    PRIMARY KEY (id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE theatres_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER theatres_seq_tr
    BEFORE INSERT
    ON theatres
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT theatres_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

CREATE INDEX region_id_idx ON theatres (region_id);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE halls
(
    id         number(10)   NOT NULL,
    name       varchar2(45) NOT NULL,
    seats      number(10)   NOT NULL,
    theatre_id number(10)   NOT NULL,
    created_at timestamp(0) NOT NULL,
    updated_at timestamp(0) NOT NULL,
    PRIMARY KEY (id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE halls_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER halls_seq_tr
    BEFORE INSERT
    ON halls
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT halls_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

CREATE INDEX fk_theatre_id_idx ON halls (theatre_id);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE movies
(
    id            NUMBER(10)   NOT NULL,
    name          VARCHAR2(45) NOT NULL,
    director_name VARCHAR2(45) NOT NULL,
    release_date  DATE         NOT NULL,
    is_active     NUMBER(3)    NULL,
    created_at    TIMESTAMP(0) NOT NULL,
    updated_at    TIMESTAMP(0) NOT NULL,
    PRIMARY KEY (id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE movies_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER movies_seq_tr
    BEFORE INSERT
    ON movies
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT movies_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE shows
(
    id              number(10)    NOT NULL,
    movie_id        number(10)    NOT NULL,
    hall_id         number(10)    NOT NULL,
    show_date       timestamp(0)  NOT NULL,
    timing_id       number(10)    NOT NULL,
    seat_price      binary_double NOT NULL,
    available_seats number(10) DEFAULT NULL,
    created_at      timestamp(0)  NOT NULL,
    updated_at      timestamp(0)  NOT NULL,
    PRIMARY KEY (id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE shows_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER shows_seq_tr
    BEFORE INSERT
    ON shows
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT shows_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;

CREATE INDEX fk_movie_id_idx ON shows (movie_id);
CREATE INDEX fk_hall_id_idx ON shows (hall_id);
CREATE INDEX fk_timing_id_idx ON shows (timing_id);


CREATE TABLE bookings
(
    id         number(10)   NOT NULL,
    user_id    number(10)   NOT NULL,
    show_id    number(10)   NOT NULL,
    seats      number(10) DEFAULT NULL,
    created_at timestamp(0) NOT NULL,
    updated_at timestamp(0) NOT NULL,
    PRIMARY KEY (id)
);

-- Generate ID using sequence and trigger
CREATE SEQUENCE bookings_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER bookings_seq_tr
    BEFORE INSERT
    ON bookings
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT bookings_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

CREATE INDEX fk_user_id_idx ON bookings (user_id);
CREATE INDEX fk_show_id_idx ON bookings (show_id);

ALTER TABLE regions
    ADD CONSTRAINT parent_id FOREIGN KEY (parent_id) REFERENCES regions (id);

ALTER TABLE theatres
    ADD CONSTRAINT fk_region_id FOREIGN KEY (region_id) REFERENCES regions (id);

ALTER TABLE halls
    ADD CONSTRAINT fk_theatre_id FOREIGN KEY (theatre_id) REFERENCES theatres (id);

ALTER TABLE shows
    ADD CONSTRAINT fk_hall_id FOREIGN KEY (hall_id) REFERENCES halls (id);
ALTER TABLE shows
    ADD CONSTRAINT fk_movie_id FOREIGN KEY (movie_id) REFERENCES movies (id);
ALTER TABLE shows
    ADD CONSTRAINT fk_timing_id FOREIGN KEY (timing_id) REFERENCES timings (id);

ALTER TABLE bookings
    ADD CONSTRAINT fk_show_id FOREIGN KEY (show_id) REFERENCES shows (id);
ALTER TABLE bookings
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id);