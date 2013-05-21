--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.2
-- Started on 2013-05-21 10:13:50

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 182 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2001 (class 0 OID 0)
-- Dependencies: 182
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 177 (class 1259 OID 16446)
-- Name: hall; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hall (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE public.hall OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16444)
-- Name: hall_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hall_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hall_id_seq OWNER TO postgres;

--
-- TOC entry 2002 (class 0 OID 0)
-- Dependencies: 176
-- Name: hall_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hall_id_seq OWNED BY hall.id;


--
-- TOC entry 179 (class 1259 OID 16462)
-- Name: hall_layout; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE hall_layout (
    id integer NOT NULL,
    "row" integer,
    "column" integer
);


ALTER TABLE public.hall_layout OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16460)
-- Name: hall_layout_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hall_layout_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hall_layout_id_seq OWNER TO postgres;

--
-- TOC entry 2003 (class 0 OID 0)
-- Dependencies: 178
-- Name: hall_layout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hall_layout_id_seq OWNED BY hall_layout.id;


--
-- TOC entry 181 (class 1259 OID 16485)
-- Name: map; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE map (
    id integer NOT NULL,
    key character varying,
    value character varying
);


ALTER TABLE public.map OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16483)
-- Name: map_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE map_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.map_id_seq OWNER TO postgres;

--
-- TOC entry 2004 (class 0 OID 0)
-- Dependencies: 180
-- Name: map_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE map_id_seq OWNED BY map.id;


--
-- TOC entry 175 (class 1259 OID 16425)
-- Name: movie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE movie (
    id integer NOT NULL,
    title character varying(100),
    hall_id integer,
    year smallint,
    description character varying(1000),
    length smallint,
    projection timestamp with time zone
);


ALTER TABLE public.movie OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16423)
-- Name: movie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE movie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movie_id_seq OWNER TO postgres;

--
-- TOC entry 2005 (class 0 OID 0)
-- Dependencies: 174
-- Name: movie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE movie_id_seq OWNED BY movie.id;


--
-- TOC entry 173 (class 1259 OID 16410)
-- Name: ticket; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ticket (
    id integer NOT NULL,
    user_id integer,
    movie_id integer,
    seat_id integer,
    date timestamp with time zone
);


ALTER TABLE public.ticket OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 16408)
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ticket_id_seq OWNER TO postgres;

--
-- TOC entry 2006 (class 0 OID 0)
-- Dependencies: 172
-- Name: ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ticket_id_seq OWNED BY ticket.id;


--
-- TOC entry 171 (class 1259 OID 16404)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "user" (
    id integer NOT NULL,
    user_name character varying(50),
    first_name character varying(50),
    last_name character varying(50),
    password character varying(100),
    register_date date
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16402)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 2007 (class 0 OID 0)
-- Dependencies: 170
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- TOC entry 169 (class 1259 OID 16396)
-- Name: user_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_type (
    id integer NOT NULL,
    name character varying(50)
);


ALTER TABLE public.user_type OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 16394)
-- Name: user_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_type_id_seq OWNER TO postgres;

--
-- TOC entry 2008 (class 0 OID 0)
-- Dependencies: 168
-- Name: user_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_type_id_seq OWNED BY user_type.id;


--
-- TOC entry 1959 (class 2604 OID 16449)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hall ALTER COLUMN id SET DEFAULT nextval('hall_id_seq'::regclass);


--
-- TOC entry 1960 (class 2604 OID 16465)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hall_layout ALTER COLUMN id SET DEFAULT nextval('hall_layout_id_seq'::regclass);


--
-- TOC entry 1961 (class 2604 OID 16488)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY map ALTER COLUMN id SET DEFAULT nextval('map_id_seq'::regclass);


--
-- TOC entry 1958 (class 2604 OID 16428)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY movie ALTER COLUMN id SET DEFAULT nextval('movie_id_seq'::regclass);


--
-- TOC entry 1957 (class 2604 OID 16413)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ticket ALTER COLUMN id SET DEFAULT nextval('ticket_id_seq'::regclass);


--
-- TOC entry 1956 (class 2604 OID 16407)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 1955 (class 2604 OID 16399)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_type ALTER COLUMN id SET DEFAULT nextval('user_type_id_seq'::regclass);


--
-- TOC entry 1989 (class 0 OID 16446)
-- Dependencies: 177
-- Data for Name: hall; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hall (id, name) FROM stdin;
\.


--
-- TOC entry 2009 (class 0 OID 0)
-- Dependencies: 176
-- Name: hall_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hall_id_seq', 1, false);


--
-- TOC entry 1991 (class 0 OID 16462)
-- Dependencies: 179
-- Data for Name: hall_layout; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hall_layout (id, "row", "column") FROM stdin;
\.


--
-- TOC entry 2010 (class 0 OID 0)
-- Dependencies: 178
-- Name: hall_layout_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hall_layout_id_seq', 1, false);


--
-- TOC entry 1993 (class 0 OID 16485)
-- Dependencies: 181
-- Data for Name: map; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY map (id, key, value) FROM stdin;
\.


--
-- TOC entry 2011 (class 0 OID 0)
-- Dependencies: 180
-- Name: map_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('map_id_seq', 1, false);


--
-- TOC entry 1987 (class 0 OID 16425)
-- Dependencies: 175
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY movie (id, title, hall_id, year, description, length, projection) FROM stdin;
\.


--
-- TOC entry 2012 (class 0 OID 0)
-- Dependencies: 174
-- Name: movie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('movie_id_seq', 1, false);


--
-- TOC entry 1985 (class 0 OID 16410)
-- Dependencies: 173
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ticket (id, user_id, movie_id, seat_id, date) FROM stdin;
\.


--
-- TOC entry 2013 (class 0 OID 0)
-- Dependencies: 172
-- Name: ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ticket_id_seq', 1, false);


--
-- TOC entry 1983 (class 0 OID 16404)
-- Dependencies: 171
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "user" (id, user_name, first_name, last_name, password, register_date) FROM stdin;
\.


--
-- TOC entry 2014 (class 0 OID 0)
-- Dependencies: 170
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 1, false);


--
-- TOC entry 1981 (class 0 OID 16396)
-- Dependencies: 169
-- Data for Name: user_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_type (id, name) FROM stdin;
1	user
2	power_user
3	admin
\.


--
-- TOC entry 2015 (class 0 OID 0)
-- Dependencies: 168
-- Name: user_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_type_id_seq', 3, true);


--
-- TOC entry 1973 (class 2606 OID 16467)
-- Name: hall_layout_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hall_layout
    ADD CONSTRAINT hall_layout_pkey PRIMARY KEY (id);


--
-- TOC entry 1971 (class 2606 OID 16454)
-- Name: hall_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY hall
    ADD CONSTRAINT hall_pkey PRIMARY KEY (id);


--
-- TOC entry 1975 (class 2606 OID 16493)
-- Name: map_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY map
    ADD CONSTRAINT map_pkey PRIMARY KEY (id);


--
-- TOC entry 1969 (class 2606 OID 16433)
-- Name: movie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);


--
-- TOC entry 1967 (class 2606 OID 16415)
-- Name: ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (id);


--
-- TOC entry 1965 (class 2606 OID 16417)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 1963 (class 2606 OID 16401)
-- Name: user_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_type
    ADD CONSTRAINT user_type_pkey PRIMARY KEY (id);


--
-- TOC entry 1979 (class 2606 OID 16455)
-- Name: movie_hall_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY movie
    ADD CONSTRAINT movie_hall_id_fkey FOREIGN KEY (hall_id) REFERENCES hall(id);


--
-- TOC entry 1976 (class 2606 OID 16468)
-- Name: ticket_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES movie(id);


--
-- TOC entry 1978 (class 2606 OID 16478)
-- Name: ticket_seat_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_seat_id_fkey FOREIGN KEY (seat_id) REFERENCES hall_layout(id);


--
-- TOC entry 1977 (class 2606 OID 16473)
-- Name: ticket_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ticket
    ADD CONSTRAINT ticket_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- TOC entry 2000 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-05-21 10:13:50

--
-- PostgreSQL database dump complete
--

