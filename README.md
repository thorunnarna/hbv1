# hbv1

=====
TO DO
=====
Virkni
 - Validate-a scheduleItem
 - Add friend og bæta við í group á /search
 - Fletta milli vikna í schedule

View
 - Compare meira fínt
 - Gera vina/group listana flottari
 - Index meira fínt
 - Útlit almennt meira?
 - setja logout á fleiri síður, mögulega í banner
 - Group page? neh




===================
SQL DATABASE SCRIPT
===================

|------|
| USER |
|------|

-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public."user"
(
    id serial,
    password text,
    photo text,
    username text,
    school text,
    CONSTRAINT "User_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."user"
    OWNER to postgres;

|--------------|
| SCHEDULEITEM |
|--------------|

-- Table: public.scheduleitem

-- DROP TABLE public.scheduleitem;

CREATE TABLE public.scheduleitem
(
    color text,
    description,
    endtime timestamp without time zone,
    id serial,
    location text,
    starttime timestamp without time zone,
    title text,
    userid integer,
    weekno integer,
    year integer,
    CONSTRAINT "ScheduleItem_pkey" PRIMARY KEY (id),
    CONSTRAINT userid FOREIGN KEY (userid)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.scheduleitem
    OWNER to postgres;

-- Index: fki_userid

-- DROP INDEX public.fki_userid;

CREATE INDEX fki_userid
    ON public.scheduleitem USING btree
    (userid)
    TABLESPACE pg_default;

|-------|
| GROUP |
|-------|

-- Table: public."group"

-- DROP TABLE public."group";

CREATE TABLE public."group"
(
    id serial,
    name text,
    CONSTRAINT "Group_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."group"
    OWNER to postgres;



|---------|
| FILTERS |
|---------|

-- Table: public.filters

-- DROP TABLE public.filters;

CREATE TABLE public.filters
(
    id serial,
    itemid integer,
    userid integer,
    name text,
    CONSTRAINT "Filters_pkey" PRIMARY KEY (id),
    CONSTRAINT itemfilter_fkey FOREIGN KEY (itemid)
        REFERENCES public.scheduleitem (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_fkey FOREIGN KEY (userid)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT userfilter_fkey FOREIGN KEY (userid)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.filters
    OWNER to postgres;

-- Index: fki_itemfilter_fkey

-- DROP INDEX public.fki_itemfilter_fkey;

CREATE INDEX fki_itemfilter_fkey
    ON public.filters USING btree
    (itemid)
    TABLESPACE pg_default;

-- Index: fki_userfilter_fkey

-- DROP INDEX public.fki_userfilter_fkey;

CREATE INDEX fki_userfilter_fkey
    ON public.filters USING btree
    (userid)
    TABLESPACE pg_default;

|------------|
| FRIENDSHIP |
|------------|

-- Table: public.friendship

-- DROP TABLE public.friendship;

CREATE TABLE public.friendship
(
    id serial,
    userid1 integer,
    userid2 integer,
    CONSTRAINT "Friendship_pkey" PRIMARY KEY (id),
    CONSTRAINT user1_fkey FOREIGN KEY (userid1)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user2_fkey FOREIGN KEY (userid2)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.friendship
    OWNER to postgres;

-- Index: fki_user1_fkey

-- DROP INDEX public.fki_user1_fkey;

CREATE INDEX fki_user1_fkey
    ON public.friendship USING btree
    (userid1)
    TABLESPACE pg_default;

-- Index: fki_user2_fkey

-- DROP INDEX public.fki_user2_fkey;

CREATE INDEX fki_user2_fkey
    ON public.friendship USING btree
    (userid2)
    TABLESPACE pg_default;

|---------|
| MEMBERS |
|---------|

-- Table: public.members

-- DROP TABLE public.members;

CREATE TABLE public.members
(
    id serial,
    groupid integer,
    userid integer,
    CONSTRAINT "Members_pkey" PRIMARY KEY (id),
    CONSTRAINT group_fkey FOREIGN KEY (groupid)
        REFERENCES public."group" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT member_fkey FOREIGN KEY (userid)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.members
    OWNER to postgres;

-- Index: fki_group_fkey

-- DROP INDEX public.fki_group_fkey;

CREATE INDEX fki_group_fkey
    ON public.members USING btree
    (groupid)
    TABLESPACE pg_default;

-- Index: fki_member_fkey

-- DROP INDEX public.fki_member_fkey;

CREATE INDEX fki_member_fkey
    ON public.members USING btree
    (userid)
    TABLESPACE pg_default;

|------|
| TAGS |
|------|

-- Table: public.tags

-- DROP TABLE public.tags;

CREATE TABLE public.tags
(
    userid integer,
    itemid integer,
    id serial,
    CONSTRAINT tags_pkey PRIMARY KEY (id),
    CONSTRAINT item_fkey FOREIGN KEY (itemid)
        REFERENCES public.scheduleitem (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_fkey FOREIGN KEY (userid)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tags
    OWNER to postgres;

-- Index: fki_item_fkey

-- DROP INDEX public.fki_item_fkey;

CREATE INDEX fki_item_fkey
    ON public.tags USING btree
    (itemid)
    TABLESPACE pg_default;

-- Index: fki_user_fkey

-- DROP INDEX public.fki_user_fkey;

CREATE INDEX fki_user_fkey
    ON public.tags USING btree
    (userid)
    TABLESPACE pg_default;