insert into ab_userinfo (id,nick,name,email,password,active,verified,created)values (nextval('seq_ab_userinfo'),'vk','vk','vk@gmail.com','$2a$10$rmMGjl87j/0AywUePFwJBef/CUMM6TUzuCCqU1eof0swWCF2N5a5q',true,true,(SELECT EXTRACT(EPOCH FROM TIMESTAMP  '2017-10-25')));
