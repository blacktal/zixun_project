drop table if exists user;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' unique,
  `password` varchar(128) NOT NULL DEFAULT '',
  `head_url` varchar(256) NOT NULL DEFAULT '',
  `salt` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists question;
create table if not exists question(
id int unsigned auto_increment,
title varchar(50) not null,
content text not null,
comment_num int unsigned not null default 0,
user_id int unsigned not null,
created_date datetime not null,
primary key(id),
index date_index(created_date asc)
)engine=InnoDB default charset=utf8;

DROP TABLE IF EXISTS `login_ticket`;
  CREATE TABLE `login_ticket` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `ticket` VARCHAR(45) NOT NULL,
    `expired` DATETIME NOT NULL,
    `status` INT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `ticket_UNIQUE` (`ticket` ASC)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comment`;
  CREATE TABLE `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `user_id` INT NOT NULL,
  `entity_id` INT NOT NULL,
  `entity_type` INT NOT NULL,
  `created_date` DATETIME NOT NULL,
  `status` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `entity_index` (`entity_id` ASC, `entity_type` ASC)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  
  DROP TABLE IF EXISTS `message`;
  CREATE TABLE `message` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `from_id` INT NULL,
    `to_id` INT NULL,
    `content` TEXT NULL,
    `created_date` DATETIME NULL,
    `has_read` INT NULL,
    `conversation_id` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `conversation_index` (`conversation_id` ASC),
    INDEX `created_date` (`created_date` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
  
