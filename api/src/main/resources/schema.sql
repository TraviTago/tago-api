drop table if exists member;
drop table if exists trip_member;

create table member (
    id bigint auto_increment,
    email varchar(255) not null,
    `name` varchar(255) not null,
    authority varchar(255) not null,
    oauth_provider varchar(255) not null,
    age_range int,
    gender varchar(255),
    mbti varchar(255),
    favorites varchar(255),
    trip_types varchar(255),
    primary key (id),
    constraint uniqueAccount unique (email, oauth_provider)
);

drop table if exists trip;


create table trip (
      id bigint auto_increment,
      name varchar(255),
      meetTime timestamp,
      meetPlace varchar(255),
      max_member int,
      current_member int,
      same_gender boolean,
      same_age boolean,
      is_pet boolean,
      favorites varchar(255),
      user_id bigint,
      course_id bigint,
      primary key (id)
);

create table trip_member (
    id bigint auto_increment,
    trip_id bigint,
    member_id bigint,
    primary key (id)
);
