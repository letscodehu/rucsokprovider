DROP TABLE IF EXISTS rucsok;
CREATE TABLE rucsok (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  image_url varchar(255) DEFAULT NULL,
  link varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  video_url varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  email varchar(255) DEFAULT NULL
) DEFAULT CHARSET=utf8;
