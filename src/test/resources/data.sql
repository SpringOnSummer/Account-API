MERGE INTO `Members` values (100,'Member_One','one','mail.com','one_password');
MERGE INTO `Members` values (200,'Member_Two','two','mail.com','two_password');

MERGE INTO `Member_details` values (100,100,'010-1234-5678','nick_name',now());