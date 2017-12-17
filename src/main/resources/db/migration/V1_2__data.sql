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



-- CLEAR ALL
DELETE  FROM user_roles;
DELETE  FROM role;
DELETE  FROM user_detail;
--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: dbuser
--

INSERT INTO role (id, name) VALUES (22, 'USER');
INSERT INTO role (id, name) VALUES (23, 'ADMIN');


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbuser
--

SELECT pg_catalog.setval('role_id_seq', 23, true);


--
-- Data for Name: user_detail; Type: TABLE DATA; Schema: public; Owner: dbuser
--

INSERT INTO user_detail (id, is_enabled, password, username) VALUES (71, true, '$2a$10$/EGG1uSsR3CoDH5rnVPL0.3cqH2rTaV2z8r6JFI75wBhppuDCkn52', 'admin@gym.com');
INSERT INTO user_detail (id, is_enabled, password, username) VALUES (72, true, '$2a$10$VL9IzVZ8HNQhkf7A4nC3cu.OtH6Y5h7h8hifDrm.DELHPzI4U3fAq', 'admin1@gym.com');
INSERT INTO user_detail (id, is_enabled, password, username) VALUES (73, true, '$2a$10$VHc/gwKGuOTu1XDdNYikYekD9.GyUBCS5J61lUgd.weXgIV6eRvma', 'admin2@gym.com');
INSERT INTO user_detail (id, is_enabled, password, username) VALUES (74, true, '$2a$10$7ZgQhTZgyDmSSMJuxGn5u.4QUmUbFUOkhtPUqky7ioD.EiLBUXqHW', 'admin3@gym.com');


--
-- Name: user_detail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dbuser
--

SELECT pg_catalog.setval('user_detail_id_seq', 74, true);


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: dbuser
--

INSERT INTO user_roles (user_id, role_id) VALUES (71, 22);
INSERT INTO user_roles (user_id, role_id) VALUES (71, 23);
INSERT INTO user_roles (user_id, role_id) VALUES (72, 22);
INSERT INTO user_roles (user_id, role_id) VALUES (72, 23);
INSERT INTO user_roles (user_id, role_id) VALUES (73, 22);
INSERT INTO user_roles (user_id, role_id) VALUES (73, 23);
INSERT INTO user_roles (user_id, role_id) VALUES (74, 23);
INSERT INTO user_roles (user_id, role_id) VALUES (74, 22);


--
-- PostgreSQL database dump complete
--

