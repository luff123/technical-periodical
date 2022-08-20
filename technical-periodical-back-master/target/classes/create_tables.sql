create schema `tp`;

use `tp`;

create table `user` (
    `id` char(11),
    `name` varchar(50),
    `password` varchar(50),

    primary key (`id`)
);

create table `paper` (
    `id` int,
    `title` varchar(50),
    `author_id` char(11),
    `date` datetime,
    `periodical_id` int,
    `summary` varchar(200),
    `cover_path` varchar(200),
    `content` varchar(200),
    `classify` varchar(10),

    primary key (`id`)
);

create table `periodical` (
    `id` int,
    `name` varchar(50),
    `author_id` char(11),
    `date` datetime,
    `summary` varchar(200),
    `cover_path` varchar(200),
    `classify` varchar(10),

    primary key (`id`)
);

create table `star` (
    `user_id` char(11),
    `paper_id` int,

    primary key (`user_id`, `paper_id`)
);

create table `pub` (
    `user_id` char(11),
    `periodical_id` int,

    primary key (`user_id`, `periodical_id`)
);

create table `download` (
    `user_id` char(11),
    `paper_id` int,
    `local_path` varchar(200),

    primary key (`user_id`, `paper_id`)
);

create table `comment` (
    `id` int,
    `user_id` char(11),
    `paper_id` int,
    `date` datetime,
    `content` varchar(1000),

    primary key (`id`)
);

create table `reply` (
    `id` int,
    `user_id` char(11),
    `comment_id` int,
    `date` datetime,
    `content` varchar(1000),

    primary key (`id`)
);