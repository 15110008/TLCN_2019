-- -- ----------------------------
-- -- 1. Table structure for roles
-- -- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -- ----------------------------
-- -- 2. Table structure for accounts
----------------------------
DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id` BIGINT(20) NOT NULL,
  `enabled` BIT(1) NOT NULL,
  `first_name` VARCHAR(255) DEFAULT '',
  `last_name` VARCHAR(255) DEFAULT '',
  `password` VARCHAR(255) DEFAULT NULL,
  `user_name` VARCHAR(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -- ----------------------------
-- -- 3. Table structure for accounts_roles
-- -- ----------------------------
DROP TABLE IF EXISTS `accounts_roles`;

CREATE TABLE `accounts_roles` (
  `account_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  KEY `FKpwest19ib22ux5gk54esw9qve` (`role_id`),
  KEY `FKt44duw96d6v8xrapfo4ff2up6` (`account_id`),
  CONSTRAINT `FKpwest19ib22ux5gk54esw9qve` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKt44duw96d6v8xrapfo4ff2up6` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -- ----------------------------
-- -- 4. Table structure for privileges
-- -- ----------------------------
DROP TABLE IF EXISTS `privileges`;

CREATE TABLE `privileges` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -- ----------------------------
-- -- 5. Table structure for roles_privileges
-- -- ----------------------------
DROP TABLE IF EXISTS `roles_privileges`;

CREATE TABLE `roles_privileges` (
  `role_id` BIGINT(20) NOT NULL,
  `privilege_id` BIGINT(20) NOT NULL,
  KEY `FK5duhoc7rwt8h06avv41o41cfy` (`privilege_id`),
  KEY `FK629oqwrudgp5u7tewl07ayugj` (`role_id`),
  CONSTRAINT `FK5duhoc7rwt8h06avv41o41cfy` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`),
  CONSTRAINT `FK629oqwrudgp5u7tewl07ayugj` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- -- ----------------------------
-- -- 6. Table structure for tour_types
-- -- ----------------------------
DROP TABLE IF EXISTS `tour_types`;
CREATE TABLE `tour_types` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -- ----------------------------
-- -- 7. Table structure for tours
-- -- ----------------------------
DROP TABLE IF EXISTS `tours`;
CREATE TABLE `tours` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `enable` bit(1) NOT NULL,
  `file_content_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `number_of_date` int(11) NOT NULL,
  `number_of_night` int(11) NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tour_type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkmd8j2x2ob2x4ui0ootug4au2` (`tour_type`),
  CONSTRAINT `FKkmd8j2x2ob2x4ui0ootug4au2` FOREIGN KEY (`tour_type`) REFERENCES `tour_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -- ----------------------------
-- -- 8. Table structure for plans
-- -- ----------------------------
DROP TABLE IF EXISTS `plans`;
CREATE TABLE `plans` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `adult_price` double NOT NULL,
  `child_price` double NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `number_of_reserved_slot` int(11) NOT NULL,
  `number_of_slot` int(11) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tour_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl2w1i8m75l8ju87arsbgclcim` (`tour_id`),
  CONSTRAINT `FKl2w1i8m75l8ju87arsbgclcim` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -- ----------------------------
-- -- 9. Table structure for places
-- -- ----------------------------
DROP TABLE IF EXISTS `places`;
CREATE TABLE `places` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -- ----------------------------
-- -- 10. Table structure for plans_places
-- -- ----------------------------
DROP TABLE IF EXISTS `plans_places`;
CREATE TABLE `plans_places` (
  `plan_id` bigint(20) NOT NULL,
  `place_id` bigint(20) NOT NULL,
  PRIMARY KEY (`plan_id`,`place_id`),
  KEY `FKfcciok440nqu5qwdn67gcyrsw` (`place_id`),
  CONSTRAINT `FKfcciok440nqu5qwdn67gcyrsw` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`),
  CONSTRAINT `FKnq2cs2xgf0pyapatqeq8uokv1` FOREIGN KEY (`plan_id`) REFERENCES `plans` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -- ----------------------------
-- -- 11. Table structure for bookings
-- -- ----------------------------
DROP TABLE IF EXISTS `bookings`;
CREATE TABLE `bookings` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `bank_account` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `booking_time` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grand_total` double NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_of_ticket` int(11) NOT NULL,
  `plan_id` bigint(20) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjebrw9c5tynujow8xcb09n3n8` (`plan_id`),
  KEY `FKeyog2oic85xg7hsu2je2lx3s6` (`account_id`),
  CONSTRAINT `FKeyog2oic85xg7hsu2je2lx3s6` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FKjebrw9c5tynujow8xcb09n3n8` FOREIGN KEY (`plan_id`) REFERENCES `plans` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -- ----------------------------
-- -- 12. Table structure for booking_details
-- -- ----------------------------
DROP TABLE IF EXISTS `booking_details`;
CREATE TABLE `booking_details` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `identification` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `booking_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkbcan6ybv86uappnh0qtdmvas` (`booking_id`),
  CONSTRAINT `FKkbcan6ybv86uappnh0qtdmvas` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- -- ----------------------------
-- -- 13. Table structure for hibernate_sequence
-- -- ----------------------------

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
