-- RUCSOK SCHEMA

drop table if exists rucsok;
create table rucsok (
      id bigint generated by default as identity (start with 1)
    , title varchar(255)
    , link varchar(255)
    , imageUrl varchar(500)
    , videoUrl varchar(500)
    , user_id int
    , createdAt timestamp
    , primary key (id)
);

drop table if exists user;
create table user (
      id bigint generated by default as identity (start with 1)
    , email varchar(255) not null
    , password varchar(255) not null
    , name varchar(255) not null
    , failedLogin int
    , primary key (id)
);

drop table if exists vote;
create table vote (
      user_id bigint not null
    , rucsok_id bigint not null
    , vote varchar(255) not null   
    , foreign key (user_id)  references user(id)
    , foreign key (rucsok_id)  references rucsok(id)
    , primary key (user_id, rucsok_id)
);

drop table if exists pun;
create table pun (
      id bigint not null
    , user_id bigint not null
    , text varchar(500) not null   
    , foreign key (user_id)  references user(id)
    , primary key (id)
);

-- users

insert into user(id, email,  password, name, failedLogin)
values (1, 'rucsok', '$2a$06$0RA1mDUlDAvOh5V9a0R01.POVFn1/Dvc5ggJ9xhXLiDAjG3o6NiNG', 'rucsok', 0);

insert into user(id, email,  password, name, failedLogin)
values (2, 'asd', '$2a$06$0RA1mDUlDAvOh5V9a0R01.POVFn1/Dvc5ggJ9xhXLiDAjG3o6NiNG', 'kecske', 0);

-- rucsoks

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (1, 'rucsok01', 'http://rucsok.com/01.gif','img01', null, 1, '2016-08-08 11:11:11');

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (2, 'rucsok02', 'http://rucsok.com/02.gif','img02', 'https://www.youtube.com/watch?v=buXwBr9H3VY', 1, '2016-08-08 11:11:11');

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (3, 'rucsok03', 'http://rucsok.com/03.gif','img03', 'http://rucsok.com/rucsok.mp4', 2, '2016-08-08 11:11:11');

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (4, 'rucsok04', 'http://rucsok.com/04.gif','img04', null, 2, '2016-08-08 11:11:11');

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (5, 'justforpagination1', 'http://rucsok.com/05.gif','img05', null, 2, '2016-08-30 11:11:11');

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (6, 'justforpagination2', 'http://rucsok.com/05.gif','img05', null, 2, '2016-08-30 11:11:12');

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (7, 'justforpagination3', 'http://rucsok.com/05.gif','img05', null, 2, '2016-08-30 11:11:13');

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (8, 'justforpagination4', 'http://rucsok.com/05.gif','img05', null, 2, '2016-08-30 11:11:14');

insert into rucsok(id, title,  link, imageUrl, videoUrl, user_id, createdAt) 
values (9, 'justforpagination5', 'http://rucsok.com/05.gif','img05', null, 2, '2016-08-30 11:11:15');

-- votes

insert into vote(user_id, rucsok_id, vote)
values (1,2, 'UP');
insert into vote(user_id, rucsok_id, vote)
values (1,3, 'UP');
insert into vote(user_id, rucsok_id, vote)
values (2,1, 'UP');

-- puns

INSERT INTO pun(id, text, user_id) 
VALUES 
(1, 'lol1', '1'), 
(2, 'lol2', '2');

-- OAUTH2 SCHEMA

-- used in tests that use HSQL
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(256), authentication LONGVARBINARY
);

drop table if exists oauth_approvals;
create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);

