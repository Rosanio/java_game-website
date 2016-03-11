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
    shown boolean,
    match boolean
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
-- Name: tamagotchis; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE tamagotchis (
    id integer NOT NULL,
    name character varying,
    age integer,
    gender character varying,
    sleep_level integer,
    hunger_level integer,
    happy_level integer,
    alive boolean
);


ALTER TABLE tamagotchis OWNER TO "Guest";

--
-- Name: tamagotchis_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE tamagotchis_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tamagotchis_id_seq OWNER TO "Guest";

--
-- Name: tamagotchis_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE tamagotchis_id_seq OWNED BY tamagotchis.id;


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
    profilepic character varying,
    tamagotchi_id integer,
    memory_high_score integer,
    points integer,
    memory_wins integer,
    memory_losses integer,
    tamagotchi_food integer
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

ALTER TABLE ONLY tamagotchis ALTER COLUMN id SET DEFAULT nextval('tamagotchis_id_seq'::regclass);


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

COPY cards (id, symbol, shown, match) FROM stdin;
6501	⌛️	f	\N
6503	🌈	f	\N
6505	🎾	f	\N
6507	🐤	f	\N
6509	👍	f	\N
6511	✊	f	\N
6513	👻	f	\N
6515	💚	f	\N
6517	💰	f	\N
6519	🚴	f	\N
6521	🖕	f	\N
6523	🐼	f	\N
6525	🦄	f	\N
6527	🎎	f	\N
6529	🙌	f	\N
6531	🐠	f	\N
6533	🍷	f	\N
6535	🐈	f	\N
6537	🐷	f	\N
6539	😈	f	\N
6541	👯	f	\N
6543	💃	f	\N
6545	🐮	f	\N
6547	🌟	f	\N
6549	🍡	f	\N
6551	🎀	f	\N
6502	⌛️	f	\N
6504	🌈	f	\N
6506	🎾	f	\N
6508	🐤	f	\N
6510	👍	f	\N
6512	✊	f	\N
6514	👻	f	\N
6516	💚	f	\N
6518	💰	f	\N
6520	🚴	f	\N
6522	🖕	f	\N
6524	🐼	f	\N
6526	🦄	f	\N
6528	🎎	f	\N
6530	🙌	f	\N
6532	🐠	f	\N
6534	🍷	f	\N
6536	🐈	f	\N
6538	🐷	f	\N
6540	😈	f	\N
6542	👯	f	\N
6544	💃	f	\N
6546	🐮	f	\N
6548	🌟	f	\N
6550	🍡	f	\N
6552	🎀	f	\N
\.


--
-- Name: cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cards_id_seq', 6552, true);


--
-- Data for Name: tamagotchis; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY tamagotchis (id, name, age, gender, sleep_level, hunger_level, happy_level, alive) FROM stdin;
\.


--
-- Name: tamagotchis_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('tamagotchis_id_seq', 86, true);


--
-- Data for Name: turns; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY turns (id, comp_turn, user_turn, shown) FROM stdin;
\.


--
-- Name: turns_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('turns_id_seq', 476, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, name, password, permissions, passwordhint, simon_high_score, profilepic, tamagotchi_id, memory_high_score, points, memory_wins, memory_losses, tamagotchi_food) FROM stdin;
18	anna	1234	admin	\N	1	http://s3.amazonaws.com/rapgenius/cats-animals-kittens-background.jpg	0	120	\N	10	3	\N
16	izzy	12345	user	\N	330	http://image.syracuse.com/home/syr-media/width620/img/zoo/photo/2015/10/05/18906340-mmmain.jpg	0	0	132	3	1	1
19	matt	123	user	\N	36	https://i.ytimg.com/vi/u5wU0xt3e54/maxresdefault.jpg	0	625	234	13	5	0
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 22, true);


--
-- Name: cards_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY cards
    ADD CONSTRAINT cards_pkey PRIMARY KEY (id);


--
-- Name: tamagotchis_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY tamagotchis
    ADD CONSTRAINT tamagotchis_pkey PRIMARY KEY (id);


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

