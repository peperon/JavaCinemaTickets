ALTER TABLE hall_layout
ADD CONSTRAINT unique_row_column UNIQUE("row", "column");

ALTER TABLE users
ADD COLUMN user_type_id integer;

ALTER TABLE users
ADD CONSTRAINT user_type_id_fk FOREIGN KEY(user_type_id) REFERENCES user_type(id);

INSERT INTO user_type
VALUES
(1, 'User'),
(2, 'Power user'),
(3, 'Administrator');