drop table if exists member;
create table member (
    id bigint auto_increment,
    phone_number varchar(255) not null unique,
    `name` varchar(255) not null,
    img_url varchar(255) not null,
    role varchar(255) not null,
    age_range int,
    gender varchar(255),
    mbti varchar(255),
    trip_types varchar(255),
    primary key (id)
);

drop table if exists place;
create table place (
    id bigint,
    type_id bigint NOT NULL,
    title varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    created_time varchar(255),
    modified_time varchar(255),
    img_url varchar(255) NOT NULL,
    mapx double NOT NULL,
    mapy double NOT NULL,
    visit double NOT NULL,
    overview longtext NOT NULL,
    homepage varchar(1000),
    telephone varchar(255),
    rest_date varchar(255),
    open_time varchar(1000),
    parking varchar(255),
    primary key (id)
);

drop table if exists course;
create table course (
    id bigint auto_increment,
    primary key (id)
);

drop table if exists course_place;
create table course_place (
    id bigint auto_increment,
    `order` int not null,
    course_id bigint not null,
    place_id bigint not null,
    primary key (id)
);

drop table if exists trip;
create table trip (
    id bigint auto_increment,
    name varchar(255),
    date_time timestamp,
    meet_place varchar(255),
    total_time int,
    max_cnt int,
    current_cnt int,
    same_gender boolean,
    same_age boolean,
    is_pet boolean,
    member_id bigint,
    primary key (id)
);

drop table if exists trip_place;
create table trip_place (
    id bigint auto_increment,
    `order` int,
    trip_id bigint,
    place_id bigint,
    primary key (id)
);

drop table if exists trip_member;
create table trip_member (
    id bigint auto_increment,
    trip_id bigint,
    member_id bigint,
    primary key (id)
);

drop table if exists tag;
create table tag (
    id bigint auto_increment,
    type varchar(255) not null,
    primary key (id)
);

drop table if exists course_tag;
create table course_tag (
    id bigint auto_increment,
    course_id bigint not null,
    tag_id bigint not null,
    primary key (id)
);

drop table if exists trip_tag;
create table trip_tag (
   id bigint auto_increment,
   trip_id bigint not null,
   tag_id bigint not null,
   primary key (id)
);

drop table if exists place_tag;
create table place_tag (
    id bigint auto_increment,
    place_id bigint not null,
    tag_id bigint not null,
    primary key (id)
);

drop table if exists member_tag;
create table member_tag (
    id bigint auto_increment,
    member_id bigint not null,
    tag_id bigint not null,
    primary key (id)
);

drop table if exists issue;
create table issue(
      id bigint auto_increment,
      member_id bigint,
      type varchar(255),
      detail varchar(255),
      primary key(id)
);

drop table if exists driver;
create table driver (
    id bigint auto_increment,
    code varchar(255) not null unique,
    phone_number varchar(255),
    name varchar(255),
    license varchar(255),
    comment longtext,
    img_url varchar(255),
    primary key (id)
);

drop table if exists dispatch;
create table dispatch (
    id bigint auto_increment,
    trip_id bigint not null,
    driver_id bigint not null,
    primary key (id)
);

drop table if exists car;
create table car (
     id bigint auto_increment,
     number varchar(255) not null unique,
     seater int not null,
     driver_id bigint not null,
     primary key (id)
);

