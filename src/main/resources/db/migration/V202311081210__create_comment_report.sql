create table if not exists comment_report
(
    comment_report_id  bigint auto_increment
        primary key,
    created_time       datetime null,
    last_modified_time datetime null,
    comment_id         bigint   null,
    user_id            bigint   null
);

