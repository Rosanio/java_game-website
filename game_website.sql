--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cards; Type: TABLE; Schema: public; Owner: Guest; Tablespace:
--

CREATE TABLE cards (
    id integer NOT NULL,
    symbol character varying,
    shown boolean
);


ALTER TABLE cards OWNER TO "Guest";

--
-- Name: cards_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cards_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cards_id_seq OWNER TO "Guest";

--
-- Name: cards_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cards_id_seq OWNED BY cards.id;


--
-- Name: turns; Type: TABLE; Schema: public; Owner: Guest; Tablespace:
--

CREATE TABLE turns (
    id integer NOT NULL,
    comp_turn character varying,
    user_turn character varying,
    shown boolean
);


ALTER TABLE turns OWNER TO "Guest";

--
-- Name: turns_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE turns_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE turns_id_seq OWNER TO "Guest";

--
-- Name: turns_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE turns_id_seq OWNED BY turns.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: Guest; Tablespace:
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying,
    password character varying,
    permissions character varying,
    passwordhint character varying,
    simon_high_score integer,
    profilepic character varying
);


ALTER TABLE users OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cards ALTER COLUMN id SET DEFAULT nextval('cards_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY turns ALTER COLUMN id SET DEFAULT nextval('turns_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: cards; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cards (id, symbol, shown) FROM stdin;
313	⌛️	f
315	🌈	f
317	🎾	f
319	🐤	f
321	👍	f
323	✊	f
325	👻	f
327	💚	f
329	💰	f
331	🚴	f
333	🖕	f
335	🐼	f
337	🦄	f
339	🎎	f
341		f
343	🐠	f
345	🍷	f
347	🐈	f
349	🐷	f
351	😈	f
353	👯	f
355	💃	f
357	🐮	f
359	🌟	f
361	🍡	f
363	🎀	f
314	⌛️	f
316	🌈	f
318	🎾	f
320	🐤	f
322	👍	f
324	✊	f
326	👻	f
328	💚	f
330	💰	f
332	🚴	f
334	🖕	f
336	🐼	f
338	🦄	f
340	🎎	f
342		f
344	🐠	f
346	🍷	f
348	🐈	f
350	🐷	f
352	😈	f
354	👯	f
356	💃	f
358	🐮	f
360	🌟	f
362	🍡	f
364	🎀	f
\.


--
-- Name: cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cards_id_seq', 364, true);


--
-- Data for Name: turns; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY turns (id, comp_turn, user_turn, shown) FROM stdin;
271	blue	\N	t
272	red	\N	t
273	yellow	\N	t
270	yellow	green	t
263	green	\N	t
264	green	\N	t
265	yellow	\N	t
266	yellow	\N	t
267	green	\N	t
268	green	\N	t
269	yellow	\N	t
\.


--
-- Name: turns_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('turns_id_seq', 273, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, name, password, permissions, passwordhint, simon_high_score, profilepic) FROM stdin;
6	Anna	123	user	numbers	140	\N
7	matt2	123	user	\N	3	\N
10	matt5	123	user	\N	0	\N
11	matt	123	user	not123	360	\N
12	charlie	123	user	123	12	http://cps-static.rovicorp.com/3/JPG_400/MI0001/458/MI0001458042.jpg?partner=allrovi.com
13	mbrecoon	333	user	333	110	\N
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 13, true);


--
-- Name: cards_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace:
--

ALTER TABLE ONLY cards
    ADD CONSTRAINT cards_pkey PRIMARY KEY (id);


--
-- Name: turns_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace:
--

ALTER TABLE ONLY turns
    ADD CONSTRAINT turns_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace:
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
