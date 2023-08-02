create table if not exists member (
    id bigint auto_increment,
    email varchar(255) not null,
    nick_name varchar(255),
    oauth_provider varchar(255),
    primary key (id)
);