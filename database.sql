CREATE TABLE tbl_model
(
    id character varying(255) COLLATE pg_catalog."default",
    nama_model character varying(255) COLLATE pg_catalog."default"
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

INSERT INTO tbl_model values ('1A', 'Test 1');
INSERT INTO tbl_model values ('2B', 'Test 2a');
