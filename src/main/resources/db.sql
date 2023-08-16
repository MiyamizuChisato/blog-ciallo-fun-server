create table identity_permission
(
    id            int auto_increment
        primary key,
    identity_id   int                                not null,
    permission_id int                                not null,
    create_time   datetime default CURRENT_TIMESTAMP not null,
    update_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted    tinyint  default 0                 not null
);

create table identity_profile
(
    id             int auto_increment
        primary key,
    identity_level tinyint  default 1                 not null,
    remark         varchar(255)                       not null,
    create_time    datetime default CURRENT_TIMESTAMP not null,
    update_time    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted     tinyint  default 0                 not null
);

create table permission_profile
(
    id              int auto_increment
        primary key,
    permission_name varchar(255)                       not null,
    remark          varchar(255)                       not null,
    create_time     datetime default CURRENT_TIMESTAMP not null,
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted      tinyint  default 0                 not null
);

create table user_auth
(
    user_profile_id int          not null
        primary key,
    email           varchar(255) not null,
    password        varchar(255) not null
);

create table user_identity
(
    id          int auto_increment
        primary key,
    user_id     int                                not null,
    identity_id int      default 1                 not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted  tinyint  default 0                 not null
);

create table user_oauth
(
    user_profile_id int          not null
        primary key,
    oauth_type      varchar(255) not null,
    oauth_id        varchar(255) not null,
    access_token    varchar(255) not null
);

create table user_profile
(
    id          int auto_increment
        primary key,
    nickname    varchar(255)                       not null,
    site        varchar(255) null,
    motto       varchar(255) null,
    avatar      varchar(255)                       not null,
    gender      tinyint null,
    location    varchar(255) null,
    birthday    datetime null,
    status      tinyint  default 1                 not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted  tinyint  default 0                 not null
);

