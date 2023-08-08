drop table if exists member;

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