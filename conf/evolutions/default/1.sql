# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bug (
  bug_id                    bigint not null,
  device_id                 bigint,
  tester_id                 bigint,
  constraint pk_bug primary key (bug_id))
;

create table device (
  device_id                 bigint not null,
  description               varchar(255),
  constraint pk_device primary key (device_id))
;

create table tester (
  tester_id                 bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  country                   varchar(255),
  last_login                timestamp,
  constraint pk_tester primary key (tester_id))
;

create table testers (
  tester_id                 bigint,
  first_name                varchar(255),
  last_name                 varchar(255),
  country                   varchar(255),
  last_login                timestamp)
;


create table tester_device (
  tester_tester_id               bigint not null,
  device_device_id               bigint not null,
  constraint pk_tester_device primary key (tester_tester_id, device_device_id))
;
create sequence bug_seq;

create sequence device_seq;

create sequence tester_seq;

alter table bug add constraint fk_bug_device_1 foreign key (device_id) references device (device_id) on delete restrict on update restrict;
create index ix_bug_device_1 on bug (device_id);
alter table bug add constraint fk_bug_tester_2 foreign key (tester_id) references tester (tester_id) on delete restrict on update restrict;
create index ix_bug_tester_2 on bug (tester_id);



alter table tester_device add constraint fk_tester_device_tester_01 foreign key (tester_tester_id) references tester (tester_id) on delete restrict on update restrict;

alter table tester_device add constraint fk_tester_device_device_02 foreign key (device_device_id) references device (device_id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bug;

drop table if exists device;

drop table if exists tester_device;

drop table if exists tester;

drop table if exists testers;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists bug_seq;

drop sequence if exists device_seq;

drop sequence if exists tester_seq;

