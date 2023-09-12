create table archive
(
    id          int auto_increment
        primary key,
    title       varchar(255)                       not null,
    image       varchar(255)                       not null,
    category    int                                not null,
    description varchar(255)                       not null,
    content     varchar(255)                       not null,
    watch_count int      default 0                 not null,
    like_count  int      default 0                 not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted  tinyint  default 0                 not null
)
    charset = utf8mb4;

create table category
(
    id          int auto_increment
        primary key,
    remark      varchar(255)                       null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted  tinyint  default 0                 not null
)
    charset = utf8mb4;

create table file_detail
(
    id                varchar(32)  not null comment '文件id'
        primary key,
    url               varchar(512) not null comment '文件访问地址',
    size              bigint       null comment '文件大小，单位字节',
    filename          varchar(256) null comment '文件名称',
    original_filename varchar(256) null comment '原始文件名',
    base_path         varchar(256) null comment '基础存储路径',
    path              varchar(256) null comment '存储路径',
    ext               varchar(32)  null comment '文件扩展名',
    content_type      varchar(128) null comment 'MIME类型',
    platform          varchar(32)  null comment '存储平台',
    th_url            varchar(512) null comment '缩略图访问路径',
    th_filename       varchar(256) null comment '缩略图名称',
    th_size           bigint       null comment '缩略图大小，单位字节',
    th_content_type   varchar(128) null comment '缩略图MIME类型',
    object_id         varchar(32)  null comment '文件所属对象id',
    object_type       varchar(32)  null comment '文件所属对象类型，例如用户头像，评价图片',
    attr              text         null comment '附加属性',
    file_acl          varchar(32)  null comment '文件ACL',
    th_file_acl       varchar(32)  null comment '缩略图文件ACL',
    create_time       datetime     null comment '创建时间'
)
    comment '文件记录表' charset = utf8mb3
                         row_format = DYNAMIC;

create table identity_permission
(
    id            int auto_increment
        primary key,
    identity_id   int                                not null,
    permission_id int                                not null,
    create_time   datetime default CURRENT_TIMESTAMP not null,
    update_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted    tinyint  default 0                 not null
)
    charset = utf8mb4;

create table identity_profile
(
    id          int auto_increment
        primary key,
    remark      varchar(255)                       not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted  tinyint  default 0                 not null
)
    charset = utf8mb4;

create table message
(
    id          int auto_increment
        primary key,
    parent      int      default 0                 not null,
    content     varchar(255)                       not null,
    create_user int                                not null,
    reply_user  int                                null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null,
    is_deleted  tinyint  default 0                 not null
);

create table permission_profile
(
    id              int auto_increment
        primary key,
    permission_name varchar(255)                       not null,
    remark          varchar(255)                       null,
    create_time     datetime default CURRENT_TIMESTAMP not null,
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted      tinyint  default 0                 not null
)
    charset = utf8mb4;

create table user_auth
(
    user_profile_id int          not null
        primary key,
    email           varchar(255) not null,
    password        varchar(255) not null
)
    charset = utf8mb4;

create table user_identity
(
    id          int auto_increment
        primary key,
    user_id     int                                not null,
    identity_id int      default 1                 not null,
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    is_deleted  tinyint  default 0                 not null
)
    charset = utf8mb4;

create table user_oauth
(
    user_profile_id int          not null
        primary key,
    oauth_type      varchar(255) not null,
    oauth_id        varchar(255) not null,
    access_token    varchar(255) not null
)
    charset = utf8mb4;

create table user_profile
(
    id          int auto_increment
        primary key,
    nickname    varchar(255)                                                              not null,
    site        varchar(255)                                                              null,
    motto       varchar(255)                                                              null,
    avatar      varchar(255) default 'https://cos.blog.ciallo.fun/blog/avatar/avatar.jpg' not null,
    gender      tinyint                                                                   null,
    location    varchar(255)                                                              null,
    birthday    date                                                                      null,
    status      tinyint      default 1                                                    not null,
    create_time datetime     default CURRENT_TIMESTAMP                                    not null,
    update_time datetime     default CURRENT_TIMESTAMP                                    not null on update CURRENT_TIMESTAMP,
    is_deleted  tinyint      default 0                                                    not null
)
    charset = utf8mb4;

