# hbv1

===================
SQL DATABASE SCRIPT
===================

-- Table: public."Filters"

-- DROP TABLE public."Filters";

CREATE TABLE public."Filters"
(
    id integer NOT NULL DEFAULT nextval('"Filters_id_seq"'::regclass),
    "itemId" integer,
    "userId" integer,
    name text COLLATE "default".pg_catalog,
    CONSTRAINT "Filters_pkey" PRIMARY KEY (id),
    CONSTRAINT itemfilter_fkey FOREIGN KEY ("itemId")
        REFERENCES public."ScheduleItem" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_fkey FOREIGN KEY ("userId")
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT userfilter_fkey FOREIGN KEY ("userId")
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Filters"
    OWNER to postgres;

-- Index: fki_itemfilter_fkey

-- DROP INDEX public.fki_itemfilter_fkey;

CREATE INDEX fki_itemfilter_fkey
    ON public."Filters" USING btree
    (itemId)
    TABLESPACE pg_default;

-- Index: fki_userfilter_fkey

-- DROP INDEX public.fki_userfilter_fkey;

CREATE INDEX fki_userfilter_fkey
    ON public."Filters" USING btree
    (userId)
    TABLESPACE pg_default;

-- Table: public."Friendship"

-- DROP TABLE public."Friendship";

CREATE TABLE public."Friendship"
(
    id integer NOT NULL DEFAULT nextval('"Friendship_id_seq"'::regclass),
    "userId1" integer,
    "userId2" integer,
    CONSTRAINT "Friendship_pkey" PRIMARY KEY (id),
    CONSTRAINT user1_fkey FOREIGN KEY ("userId1")
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user2_fkey FOREIGN KEY ("userId2")
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Friendship"
    OWNER to postgres;

-- Index: fki_user1_fkey

-- DROP INDEX public.fki_user1_fkey;

CREATE INDEX fki_user1_fkey
    ON public."Friendship" USING btree
    (userId1)
    TABLESPACE pg_default;

-- Index: fki_user2_fkey

-- DROP INDEX public.fki_user2_fkey;

CREATE INDEX fki_user2_fkey
    ON public."Friendship" USING btree
    (userId2)
    TABLESPACE pg_default;

-- Table: public."Group"

-- DROP TABLE public."Group";

CREATE TABLE public."Group"
(
    id integer NOT NULL DEFAULT nextval('"Group_id_seq"'::regclass),
    name text COLLATE "default".pg_catalog,
    CONSTRAINT "Group_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Group"
    OWNER to postgres;

-- Table: public."Members"

-- DROP TABLE public."Members";

CREATE TABLE public."Members"
(
    id integer NOT NULL DEFAULT nextval('"Members_id_seq"'::regclass),
    "groupId" integer,
    "userId" integer,
    CONSTRAINT "Members_pkey" PRIMARY KEY (id),
    CONSTRAINT group_fkey FOREIGN KEY ("groupId")
        REFERENCES public."Group" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT member_fkey FOREIGN KEY ("userId")
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Members"
    OWNER to postgres;

-- Index: fki_group_fkey

-- DROP INDEX public.fki_group_fkey;

CREATE INDEX fki_group_fkey
    ON public."Members" USING btree
    (groupId)
    TABLESPACE pg_default;

-- Index: fki_member_fkey

-- DROP INDEX public.fki_member_fkey;

CREATE INDEX fki_member_fkey
    ON public."Members" USING btree
    (userId)
    TABLESPACE pg_default;

-- Table: public."ScheduleItem"

-- DROP TABLE public."ScheduleItem";

CREATE TABLE public."ScheduleItem"
(
    color text COLLATE "default".pg_catalog,
    description text COLLATE "default".pg_catalog,
    "endTime" timestamp without time zone,
    id integer NOT NULL DEFAULT nextval('"ScheduleItem_id_seq"'::regclass),
    location text COLLATE "default".pg_catalog,
    "startTime" timestamp without time zone,
    title text COLLATE "default".pg_catalog,
    userid integer,
    "weekNo" integer,
    year integer,
    CONSTRAINT "ScheduleItem_pkey" PRIMARY KEY (id),
    CONSTRAINT userid FOREIGN KEY (userid)
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."ScheduleItem"
    OWNER to postgres;

-- Index: fki_userid

-- DROP INDEX public.fki_userid;

CREATE INDEX fki_userid
    ON public."ScheduleItem" USING btree
    (userid)
    TABLESPACE pg_default;

-- Table: public."User"

-- DROP TABLE public."User";

CREATE TABLE public."User"
(
    id integer NOT NULL DEFAULT nextval('"User_id_seq"'::regclass),
    password text COLLATE "default".pg_catalog,
    photo text COLLATE "default".pg_catalog,
    username text COLLATE "default".pg_catalog,
    CONSTRAINT "User_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."User"
    OWNER to postgres;

-- Table: public."Tags"

-- DROP TABLE public."Tags";

CREATE TABLE public."Tags"
(
    "userId" integer,
    "itemId" integer,
    id integer NOT NULL DEFAULT nextval('tags_id_seq'::regclass),
    CONSTRAINT tags_pkey PRIMARY KEY (id),
    CONSTRAINT item_fkey FOREIGN KEY ("itemId")
        REFERENCES public."ScheduleItem" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_fkey FOREIGN KEY ("userId")
        REFERENCES public."User" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Tags"
    OWNER to postgres;

-- Index: fki_item_fkey

-- DROP INDEX public.fki_item_fkey;

CREATE INDEX fki_item_fkey
    ON public."Tags" USING btree
    (itemId)
    TABLESPACE pg_default;

-- Index: fki_user_fkey

-- DROP INDEX public.fki_user_fkey;

CREATE INDEX fki_user_fkey
    ON public."Tags" USING btree
    (userId)
    TABLESPACE pg_default;