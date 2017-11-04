CREATE TABLE `albl_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` varchar(45) NOT NULL UNIQUE,
  `store_name` varchar(100) DEFAULT NULL,
  `store_address` varchar(100) DEFAULT NULL,
  `created_dtime` datetime DEFAULT NULL,
  `created_user` varchar(45) DEFAULT NULL,
  `last_modified_dtime` datetime DEFAULT NULL,
  `last_modified_user` varchar(45) DEFAULT NULL,
  `phone_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IX_albl_storephone_FK` (`phone_detail_id`),
  CONSTRAINT `IX_albl_storephone_FK` FOREIGN KEY (`phone_detail_id`) REFERENCES `albl_store_phonedetails` (`id`) ON DELETE CASCADE ON UPDATE CASCADE  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `albl_store_phonedetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT, 
  `phone_number` varchar(10) DEFAULT NULL,
  `cc` varchar(10) DEFAULT NULL,
   PRIMARY KEY (`id`)  
   
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `albl_userstore` (  
  `user_id` int(11) DEFAULT NULL,
  `store_id` varchar(45) DEFAULT NULL,
  `created_dtime` datetime DEFAULT NULL,    
  KEY `IX_userstore_userid_FK` (`user_id`),
  KEY `IX_userstore_storeid_FK` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
