SET FOREIGN_KEY_CHECKS = 0; 
truncate table user;
SET FOREIGN_KEY_CHECKS = 1;
insert into user(id, email,  password, name, failedLogin)
values (1, 'rucsok', '$2a$06$0RA1mDUlDAvOh5V9a0R01.POVFn1/Dvc5ggJ9xhXLiDAjG3o6NiNG', 'rucsok', 0);
insert into user(id, email,  password, name, failedLogin)
values (2, 'mister@rucsok.com', '$2a$06$0RA1mDUlDAvOh5V9a0R01.POVFn1/Dvc5ggJ9xhXLiDAjG3o6NiNG', 'MisterRucsok', 0);
insert into user(id, email,  password, name, failedLogin)
values (3, 'rucs@master.com', '$2a$06$0RA1mDUlDAvOh5V9a0R01.POVFn1/Dvc5ggJ9xhXLiDAjG3o6NiNG', 'rucsokLikeNoTomorrow', 0);

SET FOREIGN_KEY_CHECKS = 0; 
truncate table rucsok;
SET FOREIGN_KEY_CHECKS = 1;
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (1, 'rucsok01', 'http://letscode.hu','http://localhost:8080/images/test/01.jpg', null, 1);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (2, 'rucsok02', 'http://letscode.hu','http://localhost:8080/images/test/02.jpg', null, 1);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (3, 'rucsok03', 'http://letscode.hu','http://localhost:8080/images/test/03.jpg', null, 1);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (4, 'rucsok04', 'http://letscode.hu','http://localhost:8080/images/test/04.jpg', 'https://www.youtube.com/watch?v=2fccc1AAqco', 1);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (5, 'rucsok05', 'http://letscode.hu','http://localhost:8080/images/test/05.jpg', 'http://localhost:8080/images/test/rucsokteszt.mp4', 1);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (6, 'rucsok06', 'http://letscode.hu','http://localhost:8080/images/test/06.jpg', null, 2);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (7, 'rucsok07', 'http://letscode.hu','http://localhost:8080/images/test/07.jpg', null, 2);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (8, 'rucsok08', 'http://letscode.hu','http://localhost:8080/images/test/08.jpg', null, 3);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (9, 'rucsok09', 'http://letscode.hu','http://localhost:8080/images/test/09.jpg', null, 3);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (10, 'rucsok10', 'http://letscode.hu','http://localhost:8080/images/test/10.jpg', null, 3);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (11, 'rucsok11', 'http://letscode.hu','http://localhost:8080/images/test/11.jpg', null, 1);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (12, 'rucsok12', 'http://letscode.hu','http://localhost:8080/images/test/12.jpg', null, 1);
insert into rucsok(id, title,  link, image_url, video_url, user_id) 
values (13, 'rucsok13', 'http://imgur.com/gallery/QelEIx3','http://localhost:8080/images/rucsi.png', 'http://localhost:8080/images/test/lYPDaKO.mp4', 1);
