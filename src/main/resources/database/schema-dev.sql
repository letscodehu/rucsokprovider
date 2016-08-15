DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  failedLogin int(20) DEFAULT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS rucsok;
CREATE TABLE rucsok (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  image_url varchar(255) DEFAULT NULL,
  link varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  video_url varchar(255) DEFAULT NULL,
  user_id bigint(20) NOT NULL, 
  created_at datetime NOT NULL, 
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id)
) AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
