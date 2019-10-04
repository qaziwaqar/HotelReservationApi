create table users(
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled boolean not null default false
);

create table user_roles (
    user_role_id serial primary key,
    username varchar(50) not null,
    role varchar(50) not null,
    unique(username, role),
    constraint fk_user_roles_users foreign key (username) references users(username)
);

create unique index ix_user_roles_username on user_roles (username, role);

insert into users(username, password, enabled) values ('user1','user1',true);
insert into users(username, password, enabled) values ('user2','user2',true);

insert into user_roles (username, role) values ('user1','ROLE_ADMIN');
insert into user_roles (username, role) values ('user2','ROLE_USER');

