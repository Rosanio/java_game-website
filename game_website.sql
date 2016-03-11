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
    memory_losses integer
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
5877	⌛️	f	\N
5879	🌈	f	\N
5881	🎾	f	\N
5883	🐤	f	\N
5885	👍	f	\N
5887	✊	f	\N
5889	👻	f	\N
5891	💚	f	\N
5893	💰	f	\N
5895	🚴	f	\N
5897	🖕	f	\N
5899	🐼	f	\N
5901	🦄	f	\N
5903	🎎	f	\N
5905	🙌	f	\N
5907	🐠	f	\N
5909	🍷	f	\N
5911	🐈	f	\N
5913	🐷	f	\N
5915	😈	f	\N
5917	👯	f	\N
5919	💃	f	\N
5921	🐮	f	\N
5923	🌟	f	\N
5925	🍡	f	\N
5927	🎀	f	\N
5878	⌛️	f	\N
5880	🌈	f	\N
5882	🎾	f	\N
5884	🐤	f	\N
5886	👍	f	\N
5888	✊	f	\N
5890	👻	f	\N
5892	💚	f	\N
5894	💰	f	\N
5896	🚴	f	\N
5898	🖕	f	\N
5900	🐼	f	\N
5902	🦄	f	\N
5904	🎎	f	\N
5906	🙌	f	\N
5908	🐠	f	\N
5910	🍷	f	\N
5912	🐈	f	\N
5914	🐷	f	\N
5916	😈	f	\N
5918	👯	f	\N
5920	💃	f	\N
5922	🐮	f	\N
5924	🌟	f	\N
5926	🍡	f	\N
5928	🎀	f	\N
\.


--
-- Name: cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cards_id_seq', 5928, true);


--
-- Data for Name: tamagotchis; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY tamagotchis (id, name, age, gender, sleep_level, hunger_level, happy_level, alive) FROM stdin;
\.


--
-- Name: tamagotchis_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('tamagotchis_id_seq', 72, true);


--
-- Data for Name: turns; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY turns (id, comp_turn, user_turn, shown) FROM stdin;
\.


--
-- Name: turns_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('turns_id_seq', 328, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, name, password, permissions, passwordhint, simon_high_score, profilepic, tamagotchi_id, memory_high_score, points, memory_wins, memory_losses) FROM stdin;
18	anna	1234	user	\N	1	http://s3.amazonaws.com/rapgenius/cats-animals-kittens-background.jpg	0	120	\N	9	1
19	matt	123	user	\N	0	https://i.ytimg.com/vi/u5wU0xt3e54/maxresdefault.jpg	72	235	\N	12	5
16	izzy	12345	user	\N	0	\N	0	0	\N	1	1
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 21, true);


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

