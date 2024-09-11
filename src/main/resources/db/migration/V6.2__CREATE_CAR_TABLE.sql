CREATE TABLE entity_cars (id BIGINT GENERATED BY DEFAULT AS IDENTITY,
        company VARCHAR(255) CHECK (company IN ('FORD','TOYOTA','FERRARI','FIAT','VOLKSWAGEN','TESLA')),
        model VARCHAR(255), price FLOAT(53) NOT NULL,
        colour VARCHAR(255) CHECK (colour IN ('RED','DARKRED','BLUE',
                                              'LIGHTBLUE','DARKBLUE','WHITE',
                                              'YELLOW','BLACK','BROWN','GRAY','DARKGRAY')),
        doors INTEGER NOT NULL,
        traction4x4 BOOLEAN NOT NULL,
        PRIMARY KEY (id, company)) PARTITION BY LIST (company);

--Partitioning by company--

CREATE TABLE part_cars_company_ford PARTITION OF entity_cars FOR VALUES IN ('FORD');
CREATE TABLE part_cars_company_toyota PARTITION OF entity_cars FOR VALUES IN ('TOYOTA');
CREATE TABLE part_cars_company_ferrari PARTITION OF entity_cars FOR VALUES IN ('FERRARI');
CREATE TABLE part_cars_company_fiat PARTITION OF entity_cars FOR VALUES IN ('FIAT');
CREATE TABLE part_cars_company_volkswagen PARTITION OF entity_cars FOR VALUES IN ('VOLKSWAGEN');
CREATE TABLE part_cars_company_tesla PARTITION OF entity_cars FOR VALUES IN ('TESLA');