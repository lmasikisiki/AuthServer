--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.6
-- Dumped by pg_dump version 9.5.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: role; Type: TABLE; Schema: public; Owner: dbuser
--

CREATE TABLE role (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE role OWNER TO dbuser;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_id_seq OWNER TO dbuser;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dbuser
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- Name: user_detail; Type: TABLE; Schema: public; Owner: dbuser
--

CREATE TABLE user_detail (
    id bigint NOT NULL,
    is_enabled boolean,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE user_detail OWNER TO dbuser;

--
-- Name: user_detail_id_seq; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE user_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_detail_id_seq OWNER TO dbuser;

--
-- Name: user_detail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dbuser
--

ALTER SEQUENCE user_detail_id_seq OWNED BY user_detail.id;


--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: dbuser
--

CREATE TABLE user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE user_roles OWNER TO dbuser;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY user_detail ALTER COLUMN id SET DEFAULT nextval('user_detail_id_seq'::regclass);

--
-- Name: role_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: user_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY user_detail
    ADD CONSTRAINT user_detail_pkey PRIMARY KEY (id);


--
-- Name: user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: fk411xjm896kl8bwxu1wpsay56j; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT fk411xjm896kl8bwxu1wpsay56j FOREIGN KEY (user_id) REFERENCES user_detail(id);


--
-- Name: fkrhfovtciq1l558cw6udg0h0d3; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT fkrhfovtciq1l558cw6udg0h0d3 FOREIGN KEY (role_id) REFERENCES role(id);


--
-- Name: role; Type: ACL; Schema: public; Owner: dbuser
--

REVOKE ALL ON TABLE role FROM PUBLIC;
REVOKE ALL ON TABLE role FROM dbuser;
GRANT ALL ON TABLE role TO dbuser;


--
-- Name: user_detail; Type: ACL; Schema: public; Owner: dbuser
--

REVOKE ALL ON TABLE user_detail FROM PUBLIC;
REVOKE ALL ON TABLE user_detail FROM dbuser;
GRANT ALL ON TABLE user_detail TO dbuser;


--
-- Name: user_roles; Type: ACL; Schema: public; Owner: dbuser
--

REVOKE ALL ON TABLE user_roles FROM PUBLIC;
REVOKE ALL ON TABLE user_roles FROM dbuser;
GRANT ALL ON TABLE user_roles TO dbuser;


--
-- PostgreSQL database dump complete
--

