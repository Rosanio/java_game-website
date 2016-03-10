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
3173	⌛️	f	\N
3175	🌈	f	\N
3177	🎾	f	\N
3179	🐤	f	\N
3181	👍	f	\N
3183	✊	f	\N
3185	👻	f	\N
3187	💚	f	\N
3189	💰	f	\N
3191	🚴	f	\N
3193	🖕	f	\N
3195	🐼	f	\N
3197	🦄	f	\N
3199	🎎	f	\N
3201	🙌	f	\N
3203	🐠	f	\N
3205	🍷	f	\N
3207	🐈	f	\N
3209	🐷	f	\N
3211	😈	f	\N
3213	👯	f	\N
3215	💃	f	\N
3217	🐮	f	\N
3219	🌟	f	\N
3221	🍡	f	\N
3223	🎀	f	\N
3174	⌛️	f	\N
3176	🌈	f	\N
3178	🎾	f	\N
3180	🐤	f	\N
3182	👍	f	\N
3184	✊	f	\N
3186	👻	f	\N
3188	💚	f	\N
3190	💰	f	\N
3192	🚴	f	\N
3194	🖕	f	\N
3196	🐼	f	\N
3198	🦄	f	\N
3200	🎎	f	\N
3202	🙌	f	\N
3204	🐠	f	\N
3206	🍷	f	\N
3208	🐈	f	\N
3210	🐷	f	\N
3212	😈	f	\N
3214	👯	f	\N
3216	💃	f	\N
3218	🐮	f	\N
3220	🌟	f	\N
3222	🍡	f	\N
3224	🎀	f	\N
\.


--
-- Name: cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cards_id_seq', 3224, true);


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

COPY users (id, name, password, permissions, passwordhint, simon_high_score, profilepic, tamagotchi_id, memory_high_score, points, memory_wins, memory_losses) FROM stdin;
17	aa	1234	user	\N	0	\N	0	0	\N	\N	\N
16	izzy	12345	user	\N	0	\N	0	0	\N	\N	\N
20	wer	123	user	\N	0	\N	\N	0	\N	\N	\N
18	anna	123	user	\N	1	\N	0	120	\N	\N	\N
19	matt	123	user	\N	0	\N	0	80	\N	\N	\N
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 20, true);


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

