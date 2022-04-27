-- clear table data
SET foreign_key_checks = 0;
DELETE FROM `cart_food`;
DELETE FROM `cart`;
DELETE FROM `user`;
DELETE FROM `role`;
DELETE FROM `producer`;
DELETE FROM `food`;
DELETE FROM `food_category`;

ALTER TABLE `cart_food` AUTO_INCREMENT = 1;
ALTER TABLE `cart` AUTO_INCREMENT = 1;
ALTER TABLE `user` AUTO_INCREMENT = 1;
ALTER TABLE `role` AUTO_INCREMENT = 1;
ALTER TABLE `producer` AUTO_INCREMENT = 1;
ALTER TABLE `food` AUTO_INCREMENT = 1;
ALTER TABLE `food_category` AUTO_INCREMENT = 1;

-- table role
INSERT INTO `role` (`role`) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

-- table food category
INSERT INTO `food_category` VALUES ('Juice'), ('Pizza'), ('Soup');

-- table producer
INSERT INTO `producer` (contact_no, name) VALUES ('(+94) 764567890', 'Pizza Hut'), ('(+94) 710001234', 'Cafe LA');

-- table food
INSERT INTO `food` (image_url, name, price, category_name, producer_id)
VALUES ('https://tb-merchant.apptizer.io/apptizer/app-images/54_15496775/PRD_3cx4dd1k501_0.jpg', 'Passion Fruit Juice'
, 350.00, 'Juice', 2),
       ('https://tb-merchant.apptizer.io/apptizer/app-images/58_18628785/PRD_6im5b96k500_0.jpg', 'Garlic Prawn Pizza'
       , 2000.00, 'Pizza', 1),
       ('https://tb-merchant.apptizer.io/apptizer/app-images/58_18628785/PRD_5gu5aadn500_0.jpg', 'Beef Pizza'
       , 3500.00, 'Pizza', 1),
       ('https://tb-merchant.apptizer.io/apptizer/app-images/52_23503015/PRD_58k794aj501_0.jpg', 'Vegetable soup'
       , 200.00, 'Soup', 2),
       ('https://tb-merchant.apptizer.io/apptizer/app-images/54_15496775/PRD_43h51c7s501_0.jpg', 'Black Grape Smoothie'
       , 500.00, 'Juice', 2),
       ('https://tb-merchant.apptizer.io/apptizer/app-images/58_18628785/PRD_3ol592as500_0.jpg', 'Hot and Spicy Chicken'
       , 1800.00, 'Pizza', 1),
       ('https://tb-merchant.apptizer.io/apptizer/app-images/54_15496775/PRD_40v50b8y501_0.jpg', 'Coffee Milkshake'
       , 600.00, 'Juice', 2),
       ('https://tb-merchant.apptizer.io/apptizer/app-images/52_23503015/PRD_55t77d7x501_0.jpg', 'Shrimp Soup'
       , 460.00, 'Soup', 2);
