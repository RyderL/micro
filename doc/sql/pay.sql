DROP TABLE IF EXISTS `pay_channel`;
CREATE TABLE `pay_channel` (
  `id` char(32) NOT NULL DEFAULT '' COMMENT 'ID',
  `pay_channel` varchar(50) COMMENT '支付渠道编号',
  `pay_channel_name` VARCHAR(50) COMMENT '支付渠道名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付渠道';

INSERT INTO pay_channel VALUES ('0000000000', '0000000000', '银联');
INSERT INTO pay_channel VALUES ('0000000001', '0000000001', '支付宝');
INSERT INTO pay_channel VALUES ('0000000002', '0000000002', '微信');

DROP TABLE IF EXISTS `pay_info`;
CREATE TABLE `pay_info` (
  `id` char(32) NOT NULL DEFAULT '' COMMENT 'ID',
  `pay_order_no` varchar(50) COMMENT '支付订单编号',
  `order_no` varchar(50) COMMENT '业务订单编号',
  `pay_channel` varchar(50) COMMENT '支付渠道编号',
  `status` VARCHAR(50) COMMENT '订单状态：I：初始，P：处理中，S：成功，F：失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付信息';