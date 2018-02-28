-- add user for test
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (1,'2018-1-1 00:00:00','admin','admin','admin','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (2,'2018-1-1 00:00:00','admin2','admin2','admin2','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (3,'2018-1-1 00:00:00','admin3','admin3','admin3','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (4,'2018-1-1 00:00:00','admin4','admin4','admin4','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (5,'2018-1-1 00:00:00','admin5','admin5','admin5','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (6,'2018-1-1 00:00:00','admin6','admin6','admin6','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (7,'2018-1-1 00:00:00','admin7','admin7','admin7','123456','2018-1-1 00:00:00');

-- add bill and details
INSERT INTO `bill` (`id`, `create_time`, `name`, `no`, `update_time`) VALUES ('1', '2018-02-05 13:15:17', 'first', '1', '2018-02-05 13:15:27');
INSERT INTO `bill` (`id`, `create_time`, `name`, `no`, `update_time`) VALUES ('2', '2018-02-05 13:15:17', 'two', '2', '2018-02-05 13:15:27');
INSERT INTO `bill_detail` (`id`, `amount`, `bill_no`, `create_time`, `product_model`, `product_name`, `product_no`, `total`, `unit_price`, `update_time`) VALUES ('1', '100', '1', '2018-02-05 13:17:02', 'model1', 'name1', 'no1', '12.00', '0.12', '2018-02-05 13:18:12');
INSERT INTO `bill_detail` (`id`, `amount`, `bill_no`, `create_time`, `product_model`, `product_name`, `product_no`, `total`, `unit_price`, `update_time`) VALUES ('2', '100', '1', '2018-02-05 13:17:02', 'model2', 'name2', 'no2', '13.00', '0.13', '2018-02-05 13:18:12');

-- add component
INSERT INTO `component` (`id`, `create_time`, `name`, `num`, `price`, `update_time`) VALUES ('1', '2018-02-28 14:59:32', 'a1', '100', '2.00', '2018-02-28 14:59:34');
INSERT INTO `component` (`id`, `create_time`, `name`, `num`, `price`, `update_time`) VALUES ('2', '2018-02-28 14:59:48', 'b2', '200', '3.00', '2018-02-28 14:59:46');

-- add goods
INSERT INTO `goods` (`id`, `create_time`, `name`, `status`, `update_time`) VALUES ('1', '2018-02-28 15:17:18', 'cc', '1', '2018-02-28 15:17:20');

-- add goodsComponent
INSERT INTO `goods_component` (`id`, `component_id`, `component_name`, `create_time`, `goods_id`, `num`, `price`, `update_time`) VALUES ('1', '1', 'a1', '2018-02-28 15:17:56', '1', '2', '1.50', '2018-02-28 15:17:59');

