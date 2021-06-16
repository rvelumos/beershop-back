--
-- PostgreSQL database dump
--


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.address (id, address_type, street, street_add, number, postal_code, city, province, country, username) VALUES (9, 'B', 'arsenaal', '', '8', '3831rs', 'stad', 'provincie', 'land', 'customer2');
INSERT INTO public.address (id, address_type, street, street_add, number, postal_code, city, province, country, username) VALUES (1, NULL, 'straat', 'block', '5', '1234dd', 'Amersfoortjet', 'frrf', 'rfrf', 'customer');


--
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.authorities (authority, username) VALUES ('ROLE_ADMIN', 'administrator');
INSERT INTO public.authorities (authority, username) VALUES ('ROLE_MANUFACTURER', 'administrator');
INSERT INTO public.authorities (authority, username) VALUES ('ROLE_CUSTOMER', 'administrator');
INSERT INTO public.authorities (authority, username) VALUES ('ROLE_MANUFACTURER', 'manufacturer');
INSERT INTO public.authorities (authority, username) VALUES ('ROLE_MANUFACTURER', 'grimbergen');
INSERT INTO public.authorities (authority, username) VALUES ('ROLE_MANUFACTURER', 'leffe');
INSERT INTO public.authorities (authority, username) VALUES ('ROLE_CUSTOMER', 'customer');
INSERT INTO public.authorities (authority, username) VALUES ('ROLE_CUSTOMER', 'customer2');

--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category (id, name) VALUES (1, 'Pale ale');
INSERT INTO public.category (id, name) VALUES (2, 'Blond');
INSERT INTO public.category (id, name) VALUES (3, 'Tripel');
INSERT INTO public.category (id, name) VALUES (4, 'Abdij/trappist');
INSERT INTO public.category (id, name) VALUES (5, 'Amber');
INSERT INTO public.category (id, name) VALUES (6, 'Bruin');
INSERT INTO public.category (id, name) VALUES (7, 'IPA');
INSERT INTO public.category (id, name) VALUES (8, 'Lager');
INSERT INTO public.category (id, name) VALUES (9, 'Pilsener');
INSERT INTO public.category (id, name) VALUES (10, 'Quadrupel');
INSERT INTO public.category (id, name) VALUES (11, 'Stout');
INSERT INTO public.category (id, name) VALUES (12, 'Winterbier');
INSERT INTO public.category (id, name) VALUES (13, 'Wit');
INSERT INTO public.category (id, name) VALUES (14, 'Herfstbock');
INSERT INTO public.category (id, name) VALUES (999, 'Cadeaubon');


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customer (id, email, firstname, lastname, birth_date, phone, newsletter, sex, username, message, address_id, company) VALUES (116, 'asddsa@sdadas.nl', 'ronald', 'van eijsden', '11-11-2001', '02334763476', false, 'M', 'customer2', NULL, NULL, NULL);
INSERT INTO public.customer (id, email, firstname, lastname, birth_date, phone, newsletter, sex, username, message, address_id, company) VALUES (1, 'customer@test.nl', 'ronaldo', 'van eijsden', '05-10-1985', '03312345678', false, NULL, 'customer', NULL, 1, NULL);


--
-- Data for Name: discount; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.discount (id, name, code, amount, expiration_date, uses, allowed_usages, product_id, username) VALUES (5, 'Cadeaubon', 'HSCDD-DXASG-HVSGB-AJHVS', '10', '2022-01-01', 0, '1', 13, 'customer');
INSERT INTO public.discount (id, name, code, amount, expiration_date, uses, allowed_usages, product_id, username) VALUES (1, 'Cadeaubon', 'HSADD-HXASG-HJSGB-AJHDS', '50', '2022-01-01', 1, '1', 15, 'customer');
INSERT INTO public.discount (id, name, code, amount, expiration_date, uses, allowed_usages, product_id, username) VALUES (2, 'Cadeaubon', 'HSADD-HXASG-HVSGB-AJHDS', '25', '2022-01-01', 0, '1', 14, 'customer');


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (expiration_date, password, username, email, apikey) VALUES ( '2021-10-10 02:00:00', '$2a$10$sLj8yvATK/CdFD3LLIOwQOdR8MJwctwBBH6qywnY9KWBRqklS0M/m', 'administrator', NULL, NULL);
INSERT INTO public.users ( expiration_date, password, username, email, apikey) VALUES ( '2021-10-01 02:00:00', '$2a$10$GAqvBWRwI4IiRcnf1WK9dOs9hY2M8jI39Nwr5hzp17OXll276K.9q', 'customer', NULL, NULL);
INSERT INTO public.users ( expiration_date, password, username, email, apikey) VALUES ( '2021-10-10 02:00:00', '$2a$10$pSAhtUT7gz18GZgIfrcFjOXSqvIXmx7NuXd.ysrUM7fdlMN.1mC3W', 'manufacturer', NULL, NULL);
INSERT INTO public.users ( expiration_date, password, username, email, apikey) VALUES ( '2021-10-10 02:00:00', '$2a$10$pSAhtUT7gz18GZgIfrcFjOXSqvIXmx7NuXd.ysrUM7fdlMN.1mC3W', 'grimbergen', NULL, NULL);
INSERT INTO public.users ( expiration_date, password, username, email, apikey) VALUES ( '2021-10-10 02:00:00', '$2a$10$pSAhtUT7gz18GZgIfrcFjOXSqvIXmx7NuXd.ysrUM7fdlMN.1mC3W', 'leffe', NULL, NULL);
INSERT INTO public.users ( expiration_date, password, username, email, apikey) VALUES ( '2021-10-10 02:00:00', '$2a$10$bSrPAFajZDjf1ugJ71JmYeJ/7LSgiBRJDj/d7g4EHtuJfNIsScrVi', 'test@test.nl', 'customer@test.nl', NULL);
INSERT INTO public.users ( expiration_date, password, username, email, apikey) VALUES ( '2021-10-10 02:00:00', '$2a$10$02nddlaMj17Re0dqJ.HL6OJPIBeEmjLEGA.YMqr3aFaQNZAgY03Pm', 'customer2', 'asddsa@sdadas.nl', NULL);


--
-- Data for Name: manufacturer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.manufacturer (id, name, email, user_id, phone) VALUES (1, 'Grimbergen', 'grimbergen@test.nl', 2, '0612345678');
INSERT INTO public.manufacturer (id, name, email, user_id, phone) VALUES (2, 'Leffe', 'grimbergen@test.nl', 2, '0612345678');
INSERT INTO public.manufacturer (id, name, email, user_id, phone) VALUES (999, 'Administrator', NULL, 1, NULL);


--
-- Data for Name: newsletter; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.newsletter (id, name, content, author, sent_date) VALUES (1, 'Test', 'Inhoud', 'administrator', '2021-06-01');
INSERT INTO public.newsletter (id, name, content, author, sent_date) VALUES (2, 'Tweede test', '<p>Test</p>

<p>Werkt dit</p>', 'administrator', NULL);


--
-- Data for Name: newsletter_subscriber; Type: TABLE DATA; Schema: public; Owner: beershop
--

INSERT INTO public.newsletter_subscriber (id, email) VALUES (30, 'test@test.nl');
INSERT INTO public.newsletter_subscriber (id, email) VALUES (31, 'ronald@mailadres.nl');
INSERT INTO public.newsletter_subscriber (id, email) VALUES (32, 'ba@aasd.nl');


--
-- Data for Name: shipping; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (41, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'klant');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (1, 1, NULL, 1, 'amersfoort
', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (4, 4, NULL, 1, 'amersfoort
', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (5, 5, NULL, 1, 'amersfoort
', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (6, 6, NULL, 1, 'amersfoort

', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (7, 7, NULL, 1, 'amersfoort
', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (8, 12, NULL, 2, 'utrecht', 'Nederland', '12', '4321aa', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (9, 13, NULL, 2, 'utrecht', 'Nederland', '12', '4321aa', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (10, 14, NULL, 1, 'amersfoort
', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (11, 15, NULL, 1, 'amersfoort
', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (14, 18, NULL, 1, 'amersfoort
', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');
INSERT INTO public.shipping (id, order_id, note, shipping_carrier, city, country, number, postal_code, province, street, street_add, username) VALUES (15, 19, NULL, 1, 'amersfoort
', 'Nederland', '45', '1234aa ', 'provincie', 'straat', NULL, 'customer');


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (1, 1, 1, '2021-03-08', '2021-03-11', 28, 'PROCESSING', 'UNPAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (5, 1, 5, '2021-04-29', '2002-12-11', 253, 'PROCESSING', 'UNPAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (6, 1, 6, '2021-04-29', '2002-12-11', 250, 'PROCESSING', 'PAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (7, 1, 7, '2021-04-29', '2021-04-11', 24, 'SENT', 'PAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (12, 1, 8, '2021-04-30', '1980-10-10', 2, 'NEW_ADDED', 'UNPAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (13, 1, 9, '2021-04-30', '1980-10-10', 2, 'NEW_ADDED', 'UNPAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (14, 1, 10, '2021-04-29', '2001-10-09', 123, 'NEW_ADDED', 'UNPAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (15, 1, 11, '2021-04-29', '2001-10-09', 126, 'SENT', 'UNPAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (18, 1, 14, '2021-04-30', '2002-01-01', 111, 'NEW_ADDED', 'UNPAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (19, 1, 15, '2021-04-30', '2002-01-01', 111, 'NEW_ADDED', 'UNPAID', 'customer', NULL);
INSERT INTO public.orders (id, customer_id, shipping_id, order_date, order_sent, price_total, order_status, invoice_status, username, customer) VALUES (4, 1, 4, '2021-03-28', '2021-03-31', 37.55, 'NEW_ADDED', 'PAID', 'customer', NULL);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (2, 3, 1, 'Leffe Tripel', 1.7, 'bitter, fresh', 25, 'Leffe Tripel is een authentiek blond abdijbier met karakter, dat nagist in de fles dankzij het hoge gehalte aan gist. Een bier met een rijkgeschakeerd aroma. ', 1, NULL, 'product_2.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (3, 7, 1, 'Kasteelbier Donker', 1.5, 'zwaar, vol', 75, 'Kasteel Donker behoort tot de familie van sterke donkerbruine bieren waarin gebrande mouten de boventoon aangeven. Het allereerste Kasteelbier is dan ook een volmondig bier met duidelijke toetsen van karamel, koffie en chocolade. De zoete aanzet maakt dit bier verrassend complex en zeer toegankelijk. ', 1, '15', 'product_3.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (4, 2, 1, 'Pakket 1: 16 Belgische bieren', 20.75, 'Zwaar', 15, NULL, 2, NULL, 'product_4.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (5, 1, 1, 'Affligem', 1.45, 'Fris', 364, 'Affligem Tripel is een goudkleurige tripel met een alcoholpercentage van 9,5%. Affligem Tripel ruikt fris en fruitig en het bier heeft een zoetige bittere smaak.', 1, NULL, 'product_5.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (6, 1, 1, 'Gulden Draak 9000', 1.8, 'Sterk, bitter', 324, 'Gulden Draak Quadrupel dankt zijn naam aan het vergulde beeld boven het Gentse Belfort. Dit is een zwaar en complex bier met overwegend zoete aroma''s. Complex, gebrande mouten, karamel en een flinke vleug met fruitigheid maken dit amberkleurige bier ongeëvenaard. ', 1, NULL, 'product_6.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (7, 1, 1, 'La  Trappe Quadruppel', 1.5, 'Zwaar', 142, 'La Trappe Quadrupel zag het levenslicht in augustus 1991. We kenden al de Dubbel en de zwaardere Tripel. La Trappe kwam toen met de nog zwaardere Quadrupel. Een nieuwe bierstijl was geboren. Dit zware bier heeft 10% alcohol en zit vol complexiteit. In deze eerste Quadrupel ter wereld proef je kruidnagel, noten, rozijnen, gedroogd fruit, karamel en zelf rijpe banaan. Dit is echt genieten na een dag Ora et Labora. ', 1, NULL, 'product_7.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (8, 1, 1, 'Straffe Hendrik Quadruppel', 1.6, 'Fresh', 275, 'Straffe Hendrik Quadrupel, gelanceerd in 2010, kan omschreven worden als een rijk bier, intens donker van kleur en volmondig qua smaak.

Het aroma is kruidig en elegant met licht geroosterde toetsen. ', 1, '15', 'product_8.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (9, 1, 1, 'Karmeliet Tripel', 1.7, 'Fruitig, fris', 12, 'Karmeliet is een complex goud- tot bronskleurig bier met een romige schuimkraag. Dit komt onder meer door de gebruikte granen, maar ook door een beperkt gebruik van Stiermarkse hop, het overvloedig kruiden en het fruitig karakter (banaan en vanille) van de huisgist. De zoete aanzet evolueert verder in een uitgesproken sinaasappelsmaak om dan mildbitter uit te vloeien. De aanbevolen schenkingstemperatuur van Tripel Karmeliet ligt rond de 6-9 graden Celsius. ', 1, NULL, 'product_9.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (10, 1, 1, 'Gulden Draak Quadrupel', 1.58, 'Kruidig, bitter', 188, 'Gulden Draak Quadrupel dankt zijn naam aan het vergulde beeld boven het Gentse Belfort. Dit is een zwaar en complex bier met overwegend zoete aroma''s. Complex, gebrande mouten, karamel en een flinke vleug met fruitigheid maken dit amberkleurige bier ongeëvenaard. ', 1, '25', 'product_10.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (1, 1, 1, 'Grimbergen Tripel', 1.75, 'Zomer, fresh', 663, 'Grimbergen Tripel is een amberblond adbijbier met een bitterzoete, pittige, uiterst volronde smaak en een warme afdronk. De typisch pittige Tripel-smaak ontstaat door de blonde kandijsuiker en de mengeling van zachte en bittere hopsoorten. ', 1, NULL, 'product_1.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (14, NULL, NULL, 'Cadeaubon 25 euro', 25, NULL, 9999, 'Een jaar geldig op het hele assortiment', 4, NULL, NULL);
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (30, 6, 1, 'Pakket 2: 32 Duitse bier', 32.4, 'Fruitig', 36, 'Topper', 2, '35', 'product_30.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (31, 3, 1, 'Pakket 3: 10 Zweeds bier', 17.5, 'Test', 74, 'Omschrijving', 2, NULL, 'product_31.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (32, 7, 1, 'Pakket 4: 20 Ierse IPA', 22.5, 'Kruidig', 28, 'Omschrijving', 2, '40', 'product_32.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (33, 6, 1, 'Pakket 5: 25 Donkere bieren', 25, 'Fris', 33, 'Omschrijving', 2, NULL, 'product_33.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (100, 2, 1, 'Brugse Zot', 1.4, 'lekker', 232, 'Brugse Zot', 1, NULL, 'brugse-zot-blonde-440823.jpg');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (40, 999, NULL, 'Cadeaubon 5 euro', 5, NULL, 9999, 'Een jaar geldig op het hele assortiment', 4, NULL, NULL);
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (11, 3, 1, 'La Chouffe', 1.93, 'Fris', 0, 'La Chouffe is een Belgisch blond bier dat voor het eerst in 1982 door de zwagers Pierre Gobron en Chris Bauweraerts werd gebrouwen. Het begon als een hobby, die geleidelijk uitgroeide tot de Brouwerij van Achouffe (met stokerij en taverne). Sedert 2006 is deze volledig in handen van de Brouwerij Duvel Moortgat uit Breendonk. La Chouffe was behalve op vat lange tijd alleen in grote flessen van 75 centiliter en 1,5 liter verkrijgbaar, maar sinds mei 2009 wordt het bier ook in flesjes van 33 centiliter gebotteld. De brouwer beveelt aan La Chouffe bij een temperatuur van 4 tot 10°C te drinken.', 1, NULL, 'product_11.png');
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (17, 999, NULL, 'Cadeaubon 12.5 euro', 12.5, NULL, 9999, 'Een jaar geldig op het hele assortiment', 4, NULL, NULL);
INSERT INTO public.product (id, category_id, manufacturer_id, name, price, taste, stock, description, type, discount, image) VALUES (15, 999, NULL, 'Cadeaubon 50 euro', 50, NULL, 9999, 'Een jaar geldig op het hele assortiment', 4, NULL, NULL);


--
-- Data for Name: order_product; Type: TABLE DATA; Schema: public; Owner: beershop
--

INSERT INTO public.order_product (order_id, product_id, quantity) VALUES (1, 1, 5);


--
-- Data for Name: orders_product; Type: TABLE DATA; Schema: public; Owner: beershop
--


--
-- Data for Name: search_terms; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.search_terms (id, keyword, amount) VALUES (68, 'Grimbergen', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (69, 'kasteelbier', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (70, 'grimbergen', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (71, 'bier', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (72, 'wijn', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (73, 'bier', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (74, 'bierpakket', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (75, 'grimbergen', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (76, 'bier', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (77, 'chouffe', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (78, 'tripel', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (79, 'bier', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (80, 'tripel', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (81, 'dubbel', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (82, 'ipa', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (83, 'pakket', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (84, 'duits', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (85, 'bier', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (86, 'bier', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (87, 'bier', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (88, 'grimbergen', 1);
INSERT INTO public.search_terms (id, keyword, amount) VALUES (89, 'bier', 1);



