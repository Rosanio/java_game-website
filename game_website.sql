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
781	⌛️	f	\N
783	🌈	f	\N
785	🎾	f	\N
787	🐤	f	\N
789	👍	f	\N
791	✊	f	\N
793	👻	f	\N
795	💚	f	\N
797	💰	f	\N
799	🚴	f	\N
801	🖕	f	\N
803	🐼	f	\N
805	🦄	f	\N
807	🎎	f	\N
809		f	\N
811	🐠	f	\N
813	🍷	f	\N
815	🐈	f	\N
817	🐷	f	\N
819	😈	f	\N
821	👯	f	\N
823	💃	f	\N
825	🐮	f	\N
827	🌟	f	\N
829	🍡	f	\N
831	🎀	f	\N
782	⌛️	f	\N
784	🌈	f	\N
786	🎾	f	\N
788	🐤	f	\N
790	👍	f	\N
792	✊	f	\N
794	👻	f	\N
796	💚	f	\N
798	💰	f	\N
800	🚴	f	\N
802	🖕	f	\N
804	🐼	f	\N
806	🦄	f	\N
808	🎎	f	\N
810		f	\N
812	🐠	f	\N
814	🍷	f	\N
816	🐈	f	\N
818	🐷	f	\N
820	😈	f	\N
822	👯	f	\N
824	💃	f	\N
826	🐮	f	\N
828	🌟	f	\N
830	🍡	f	\N
832	🎀	f	\N
\.


--
-- Name: cards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cards_id_seq', 832, true);


--
-- Data for Name: tamagotchis; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY tamagotchis (id, name, age, gender, sleep_level, hunger_level, happy_level, alive) FROM stdin;
49	safdaf	99	Male	12	0	11	f
\.


--
-- Name: tamagotchis_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('tamagotchis_id_seq', 49, true);


--
-- Data for Name: turns; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY turns (id, comp_turn, user_turn, shown) FROM stdin;
\.


--
-- Name: turns_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('turns_id_seq', 322, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, name, password, permissions, passwordhint, simon_high_score, profilepic, tamagotchi_id, memory_high_score, points) FROM stdin;
16	izzy	12345	user	\N	0	\N	49	\N	\N
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 16, true);


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

