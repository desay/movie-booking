CREATE TABLE public.customer
(
  id bigint NOT NULL,
  email character varying[],
  first_name character varying[] NOT NULL,
  last_name character varying[],
  telephone character varying[] NOT NULL,
  CONSTRAINT customer_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.customer
  OWNER TO postgres;

CREATE TABLE public.movie
(
  id bigint NOT NULL,
  code character varying[] NOT NULL,
  end_time time without time zone NOT NULL,
  name character varying[],
  start_time time without time zone NOT NULL,
  CONSTRAINT movie_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.movie
  OWNER TO postgres;
  
  
  CREATE TABLE public.reservation
(
  id bigint NOT NULL,
  duration time without time zone NOT NULL,
  end_date timestamp without time zone,
  start_date timestamp without time zone NOT NULL,
  submission_date timestamp without time zone,
  customer_id bigint NOT NULL,
  movie_id bigint NOT NULL,
  CONSTRAINT reservation_pkey PRIMARY KEY (id),
  CONSTRAINT fkm8xumi0g23038cw32oiva2ymw FOREIGN KEY (movie_id)
      REFERENCES public.movie (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkoq2iacdgt8val8v26jn0iw83q FOREIGN KEY (customer_id)
      REFERENCES public.customer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.reservation
  OWNER TO postgres;

-- Index: public.fkm8xumi0g23038cw32oiva2ymw

-- DROP INDEX public.fkm8xumi0g23038cw32oiva2ymw;

CREATE INDEX fkm8xumi0g23038cw32oiva2ymw
  ON public.reservation
  USING btree
  (movie_id);

-- Index: public.fkoq2iacdgt8val8v26jn0iw83q

-- DROP INDEX public.fkoq2iacdgt8val8v26jn0iw83q;

CREATE INDEX fkoq2iacdgt8val8v26jn0iw83q
  ON public.reservation
  USING btree
  (customer_id);
