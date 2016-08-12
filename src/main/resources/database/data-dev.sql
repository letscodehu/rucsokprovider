truncate table rucsok;
insert into rucsok(id, title,  link, image_url, video_url) 
values (1, 'rucsok01', 'http://letscode.hu','http://localhost:8080/images/test/01.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (2, 'rucsok02', 'http://letscode.hu','http://localhost:8080/images/test/02.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (3, 'rucsok03', 'http://letscode.hu','http://localhost:8080/images/test/03.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (4, 'rucsok04', 'http://letscode.hu','http://localhost:8080/images/test/04.jpg', 'https://www.youtube.com/watch?v=2fccc1AAqco');
insert into rucsok(id, title,  link, image_url, video_url) 
values (5, 'rucsok05', 'http://letscode.hu','http://localhost:8080/images/test/05.jpg', 'http://localhost:8080/images/test/rucsokteszt.mp4');
insert into rucsok(id, title,  link, image_url, video_url) 
values (6, 'rucsok06', 'http://letscode.hu','http://localhost:8080/images/test/06.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (7, 'rucsok07', 'http://letscode.hu','http://localhost:8080/images/test/07.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (8, 'rucsok08', 'http://letscode.hu','http://localhost:8080/images/test/08.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (9, 'rucsok09', 'http://letscode.hu','http://localhost:8080/images/test/09.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (10, 'rucsok10', 'http://letscode.hu','http://localhost:8080/images/test/10.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (11, 'rucsok11', 'http://letscode.hu','http://localhost:8080/images/test/11.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (12, 'rucsok12', 'http://letscode.hu','http://localhost:8080/images/test/12.jpg', null);
insert into rucsok(id, title,  link, image_url, video_url) 
values (13, 'rucsok13', 'http://imgur.com/gallery/QelEIx3','http://localhost:8080/images/rucsi.png', 'http://localhost:8080/images/test/lYPDaKO.mp4');

truncate table user;
insert into user(id, email,  password, name, failedLogin)
values (1, 'rucsok', '$2a$06$0RA1mDUlDAvOh5V9a0R01.POVFn1/Dvc5ggJ9xhXLiDAjG3o6NiNG', 'rucsok', 0);
