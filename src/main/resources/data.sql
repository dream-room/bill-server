-- add user for test
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (1,'2018-1-1 00:00:00','admin','admin','admin','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (2,'2018-1-1 00:00:00','admin2','admin2','admin2','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (3,'2018-1-1 00:00:00','admin3','admin3','admin3','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (4,'2018-1-1 00:00:00','admin4','admin4','admin4','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (5,'2018-1-1 00:00:00','admin5','admin5','admin5','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (6,'2018-1-1 00:00:00','admin6','admin6','admin6','123456','2018-1-1 00:00:00');
INSERT INTO user (id, create_time, description, name, no, password, update_time) VALUES (7,'2018-1-1 00:00:00','admin7','admin7','admin7','123456','2018-1-1 00:00:00');

-- add bill and details
INSERT INTO `bill` (`id`, `create_time`, `name`, `no`, `update_time`, `company`) VALUES ('1', '2018-02-05 13:15:17', 'first', '1', '2018-02-05 13:15:27', 'Test Company');
INSERT INTO `bill` (`id`, `create_time`, `name`, `no`, `update_time`, `company`) VALUES ('2', '2018-02-05 13:15:17', 'two', '2', '2018-02-05 13:15:27', 'Test Company');
INSERT INTO `bill_detail` (`id`, `amount`, `bill_no`, `create_time`, `goods_version`, `goods_name`, `goods_id`, `total`, `unit_price`, `update_time`) VALUES ('1', '100', '1', '2018-02-05 13:17:02', '1', 'name1', '1', '12.00', '0.12', '2018-02-05 13:18:12');
INSERT INTO `bill_detail` (`id`, `amount`, `bill_no`, `create_time`, `goods_version`, `goods_name`, `goods_id`, `total`, `unit_price`, `update_time`) VALUES ('2', '100', '1', '2018-02-05 13:17:02', '1', 'name2', '2', '13.00', '0.13', '2018-02-05 13:18:12');

-- add component
INSERT INTO `component` (`id`, `create_time`, `name`, `num`, `price`, `update_time`, `type`) VALUES ('1', '2018-02-28 14:59:32', 'a1', '100', '2.00', '2018-02-28 14:59:34', '1');
INSERT INTO `component` (`id`, `create_time`, `name`, `num`, `price`, `update_time`, `type`) VALUES ('2', '2018-02-28 14:59:48', 'b2', '200', '3.00', '2018-02-28 14:59:46', '2');

-- add goods
INSERT INTO `goods` (`id`, `create_time`, `name`, `status`, `update_time`, `price`) VALUES ('1', '2018-02-28 15:17:18', 'cc', '1', '2018-02-28 15:17:20', '1.50');

-- add goodsComponent
INSERT INTO `goods_component` (`id`, `component_id`, `component_name`, `create_time`, `goods_id`, `num`, `price`, `update_time`) VALUES ('1', '1', 'a1', '2018-02-28 15:17:56', '1', '2', '1.50', '2018-02-28 15:17:59');
-- INSERT INTO `goods_component` (`id`, `component_id`, `create_time`, `goods_id`, `num`, `update_time`) VALUES ('1', '1', '2018-02-28 15:17:56', '1', '2', '2018-02-28 15:17:59');

