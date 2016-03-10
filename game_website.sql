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
    points integer
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
1717	⌛️	f	\N
1719	🌈	f	\N
1721	🎾	f	\N
1723	🐤	f	\N
1725	👍	f	\N
1727	✊	f	\N
1729	👻	f	\N
1731	💚	f	\N
1733	💰	f	\N
1735	🚴	f	\N
1737	🖕	f	\N
1739	🐼	f	\N
1741	🦄	f	\N
1743	🎎	f	\N
1745	🙌	f	\N
1747	🐠	f	\N
1749	🍷	f	\N
1751	🐈	f	\N
1753	🐷	f	\N
1755	😈	f	\N
1757	👯	f	\N
1759	💃	f	\N
1761	🐮	f	\N
1763	🌟	f	\N
1765	🍡	f	\N
1767	🎀	f	\N
1718	⌛️	f	\N
1720	🌈	f	\N
1722	🎾	f	\N
1724	🐤	f	\N
1726	👍	f	\N
1728	✊	f	\N
1730	👻	f	\N
1732	💚	f	\N
1734	💰	f	\N
1736	🚴	f	\N
1738	🖕	f	\N
1740	🐼	f	\N
1742	🦄	f	\N
1744	🎎	f	\N
1746	🙌	f	\N
1748	🐠	f	\N
1750	🍷	f	\N
1752	🐈	f	\N
1754	🐷	f	\N
1756	😈	f	\N
1758	👯	f	\N
1760	💃	f	\N
1762	🐮	f	\N
1764	🌟	f	\N
1766	🍡	f	\N
1768	🎀	f	\N
\.


--
-- Name: cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cards_id_seq', 1768, true);


--
-- Data for Name: tamagotchis; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY tamagotchis (id, name, age, gender, sleep_level, hunger_level, happy_level, alive) FROM stdin;
71	asdawf	65	Female	12	0	11	f
60	qwe	65	Male	15	0	8	f
58	asdf	65	Female	0	0	15	f
59	t	65	Male	15	0	8	f
61	123	65	Male	15	0	8	f
62	123	65	Male	15	0	8	f
63	123	65	Male	15	0	8	f
64	123	65	Male	15	0	8	f
65	123	65	Female	15	0	8	f
66	toby	65	Female	14	0	11	f
67	asd	65	Male	15	0	8	f
68	1232	65	Female	15	0	8	f
69	asd	65	Female	8	0	15	f
70	asd	65	Female	4	0	15	f
\.


--
-- Name: tamagotchis_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('tamagotchis_id_seq', 71, true);


--
-- Data for Name: turns; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY turns (id, comp_turn, user_turn, shown) FROM stdin;
\.


--
-- Name: turns_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('turns_id_seq', 325, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, name, password, permissions, passwordhint, simon_high_score, profilepic, tamagotchi_id, memory_high_score, points) FROM stdin;
17	aa	1234	user	\N	0	\N	0	0	\N
18	anna	123	user	\N	1	\N	0	200	\N
19	matt	123	user	\N	0	\N	0	0	\N
16	izzy	12345	user	\N	0	\N	0	0	\N
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 19, true);


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

