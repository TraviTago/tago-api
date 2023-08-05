drop table if exists member;

create table member (
    id bigint auto_increment,
    email varchar(255),
    name varchar(255) not null,
    authority varchar(255) not null,
    oauth_provider varchar(255),
    primary key (id)
);