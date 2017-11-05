
INSERT INTO bus_role ( role,created_dtime )  VALUES  ('OFFICE',CURDATE() );
   
INSERT INTO bus_role ( role,created_dtime )  VALUES  ('INSTITUTION',CURDATE() );  

INSERT INTO bus_resource ( resource,created_dtime )  VALUES  ('create_store',CURDATE() );  

INSERT INTO bus_resource ( resource,created_dtime )  VALUES  ('search_store',CURDATE() );  

INSERT INTO bus_resource ( resource,created_dtime )  VALUES  ('retrieve_store',CURDATE() );  

INSERT INTO bus_resource ( resource,created_dtime )  VALUES  ('create_userstore',CURDATE() );  

INSERT INTO bus_resource ( resource,created_dtime )  VALUES  ('retrive_userstore',CURDATE() );


INSERT INTO roles_resources ( role_id,resource_id,created_dtime )  VALUES  
((select role_id from bus_role where role = 'INSTITUTION'),
(select resource_id from bus_resource where resource = 'create_store'),CURDATE() );

INSERT INTO roles_resources ( role_id,resource_id,created_dtime )  VALUES  
((select role_id from bus_role where role = 'INSTITUTION'),
(select resource_id from bus_resource where resource = 'search_store'),CURDATE() );


INSERT INTO roles_resources ( role_id,resource_id,created_dtime )  VALUES  
((select role_id from bus_role where role = 'INSTITUTION'),
(select resource_id from bus_resource where resource = 'retrieve_store'),CURDATE() );

INSERT INTO roles_resources ( role_id,resource_id,created_dtime )  VALUES  
((select role_id from bus_role where role = 'INSTITUTION'),
(select resource_id from bus_resource where resource = 'create_userstore'),CURDATE() );

INSERT INTO roles_resources ( role_id,resource_id,created_dtime )  VALUES  
((select role_id from bus_role where role = 'INSTITUTION'),
(select resource_id from bus_resource where resource = 'retrive_userstore'),CURDATE() );

INSERT INTO roles_resources ( role_id,resource_id,created_dtime )  VALUES  
((select role_id from bus_role where role = 'OFFICE'),
(select resource_id from bus_resource where resource = 'retrive_userstore'),CURDATE() );