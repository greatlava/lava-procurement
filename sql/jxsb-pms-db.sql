/*
 Navicat Premium Data Transfer

 Source Server         : 鸿鹄
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Schema         : jxsb-pms-db

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 05/01/2024 17:33:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bid_applications
-- ----------------------------
DROP TABLE IF EXISTS `bid_applications`;
CREATE TABLE `bid_applications`  (
  `xid` int(11) NOT NULL AUTO_INCREMENT COMMENT '抽取申请ID',
  `pb_id` int(11) NULL DEFAULT NULL COMMENT '评标ID',
  `x_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '申请编号',
  `x_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '招标项目名称',
  `x_way` int(11) NULL DEFAULT NULL COMMENT '专家确定方式',
  `x_type` int(11) NULL DEFAULT NULL COMMENT '资格审查类型',
  `x_start_time` datetime(0) NULL DEFAULT NULL COMMENT '评标开始时间',
  `x_end_time` datetime(0) NULL DEFAULT NULL COMMENT '评标结束时间',
  `x_count` int(11) NULL DEFAULT NULL COMMENT '专家人数',
  `x_dai_count` int(11) NULL DEFAULT NULL COMMENT '采购方代表人数',
  `x_area` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评标地点',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  PRIMARY KEY (`xid`) USING BTREE,
  INDEX `FK_Relationship_34`(`pb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抽取申请表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_applications
-- ----------------------------
INSERT INTO `bid_applications` VALUES (41, NULL, 'CQ20231229041', NULL, 1, 1, '2023-12-29 09:00:00', '2023-12-30 09:00:00', 5, 3, '湖南省永州市豪华酒店2020', 18);
INSERT INTO `bid_applications` VALUES (42, NULL, 'CQ202412042', NULL, 1, 1, '2024-01-01 09:00:00', '2024-01-02 09:00:00', 5, 2, '长沙市楼', 22);
INSERT INTO `bid_applications` VALUES (43, NULL, 'CQ202412043', NULL, 2, 1, '2024-01-25 09:00:00', '2024-01-31 09:00:00', 5, 2, 'asghjfdhdfh', 21);
INSERT INTO `bid_applications` VALUES (44, NULL, 'CQ202412044', NULL, 1, 1, '2024-01-02 20:37:17', '2024-01-03 20:37:21', 5, 2, 'css', 20);
INSERT INTO `bid_applications` VALUES (45, NULL, 'CQ202413045', NULL, 2, 1, '2024-01-03 14:47:47', '2024-01-03 09:00:00', 5, 2, '.长沙.', 28);
INSERT INTO `bid_applications` VALUES (46, NULL, 'CQ202413046', NULL, 1, 1, '2024-01-03 17:06:50', '2024-01-04 09:00:00', 5, 2, '长沙实力', 29);
INSERT INTO `bid_applications` VALUES (47, NULL, 'CQ202415047', NULL, 2, 2, '2024-01-05 10:40:34', '2024-01-05 10:40:36', 5, 2, '长沙', 27);

-- ----------------------------
-- Table structure for bid_candidate
-- ----------------------------
DROP TABLE IF EXISTS `bid_candidate`;
CREATE TABLE `bid_candidate`  (
  `zid` int(11) NOT NULL AUTO_INCREMENT COMMENT '候选人ID',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `hid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商ID',
  `z_final` decimal(10, 0) NULL DEFAULT NULL COMMENT '最终报价',
  `z_fraction` decimal(10, 0) NULL DEFAULT NULL COMMENT '最终得分',
  `z_recommend` int(11) NULL DEFAULT 0 COMMENT '是否推荐（是：0  否：1）',
  `z_ranking` int(11) NULL DEFAULT NULL COMMENT '排名',
  `z_sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专家签到表',
  `z_censor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审查表',
  `z_review` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评审表',
  `z_summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最终汇总表',
  `z_bidder` int(11) NULL DEFAULT 1 COMMENT '是否中标（是：0  否：1）',
  `z_send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `hName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  PRIMARY KEY (`zid`) USING BTREE,
  INDEX `FK_Relationship_20`(`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '中标候选人' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_candidate
-- ----------------------------
INSERT INTO `bid_candidate` VALUES (24, 18, '82', 12000, 9, 0, 1, NULL, NULL, NULL, NULL, 0, '2024-01-01 09:00:00', '花火科技有限公司');
INSERT INTO `bid_candidate` VALUES (25, 22, '2', 100, 100, 0, 1, '[{\"name\":\"01-软件开发Java设计规范——简洁版.docx\",\"size\":13805,\"uid\":1704162266705,\"url\":\"http://localhost:9610/static/2024/01/02/01-软件开发Java设计规范——简洁版_20240102102436A014.docx\"}]', '[{\"name\":\"02-Spring Cloud周测简单版.docx\",\"size\":41459,\"uid\":1704162269642,\"url\":\"http://localhost:9610/static/2024/01/02/02-Spring Cloud周测简单版_20240102102436A015.docx\"}]', '[{\"name\":\"03-入职经验谈-问答题.docx\",\"size\":12559,\"uid\":1704162273033,\"url\":\"http://localhost:9610/static/2024/01/02/03-入职经验谈-问答题_20240102102437A016.docx\"}]', '[{\"name\":\"bat.txt\",\"size\":192,\"uid\":1704162275660,\"url\":\"http://localhost:9610/static/2024/01/02/bat_20240102102437A017.txt\"}]', 0, '2024-01-02 09:00:00', '腾腾科技（深圳）有限公司');
INSERT INTO `bid_candidate` VALUES (26, 20, '2', 100, 100, 0, 1, '[{\"name\":\"01-软件开发Java设计规范——简洁版.docx\",\"size\":13805,\"uid\":1704199078915,\"url\":\"http://localhost:9610/static/2024/01/02/01-软件开发Java设计规范——简洁版_20240102203811A026.docx\"}]', '[{\"name\":\"02-Spring Cloud周测简单版.docx\",\"size\":41459,\"uid\":1704199083413,\"url\":\"http://localhost:9610/static/2024/01/02/02-Spring Cloud周测简单版_20240102203812A027.docx\"}]', '[{\"name\":\"03-入职经验谈-问答题.docx\",\"size\":12559,\"uid\":1704199086780,\"url\":\"http://localhost:9610/static/2024/01/02/03-入职经验谈-问答题_20240102203812A028.docx\"}]', '[{\"name\":\"2021年10月BCSP-S3机试试卷.doc\",\"size\":507530,\"uid\":1704199090252,\"url\":\"http://localhost:9610/static/2024/01/02/2021年10月BCSP-S3机试试卷_20240102203812A029.doc\"}]', 0, '2024-01-02 08:37:55', '腾腾科技（深圳）有限公司');
INSERT INTO `bid_candidate` VALUES (28, 28, '2', 100, 100, 0, 1, '[{\"name\":\"专家签到表.docx\",\"size\":758683,\"uid\":1704264908553,\"url\":\"http://localhost:9610/static/2024/01/03/专家签到表_20240103145521A021.docx\"}]', '[{\"name\":\"审查表.docx\",\"size\":367140,\"uid\":1704264912017,\"url\":\"http://localhost:9610/static/2024/01/03/审查表_20240103145521A022.docx\"}]', '[{\"name\":\"评审表.docx\",\"size\":380555,\"uid\":1704264915082,\"url\":\"http://localhost:9610/static/2024/01/03/评审表_20240103145521A023.docx\"}]', '[{\"name\":\"最终汇总表.docx\",\"size\":352256,\"uid\":1704264919860,\"url\":\"http://localhost:9610/static/2024/01/03/最终汇总表_20240103145521A024.docx\"}]', 0, '2024-01-03 14:55:00', '腾腾科技（深圳）有限公司');
INSERT INTO `bid_candidate` VALUES (29, 28, '82', 100, 98, 0, 2, '[{\"name\":\"专家签到表.docx\",\"size\":758683,\"uid\":1704264940582,\"url\":\"http://localhost:9610/static/2024/01/03/专家签到表_20240103145551A025.docx\"}]', '[{\"name\":\"审查表.docx\",\"size\":367140,\"uid\":1704264943365,\"url\":\"http://localhost:9610/static/2024/01/03/审查表_20240103145551A026.docx\"}]', '[{\"name\":\"评审表.docx\",\"size\":380555,\"uid\":1704264946121,\"url\":\"http://localhost:9610/static/2024/01/03/评审表_20240103145551A027.docx\"}]', '[{\"name\":\"最终汇总表.docx\",\"size\":352256,\"uid\":1704264950069,\"url\":\"http://localhost:9610/static/2024/01/03/最终汇总表_20240103145551A028.docx\"}]', 1, '2024-01-03 14:55:33', '得力集团');
INSERT INTO `bid_candidate` VALUES (30, 28, '83', 98, 98, 0, 3, '[{\"name\":\"专家签到表.docx\",\"size\":758683,\"uid\":1704264968780,\"url\":\"http://localhost:9610/static/2024/01/03/专家签到表_20240103145623A029.docx\"}]', '[{\"name\":\"审查表.docx\",\"size\":367140,\"uid\":1704264975108,\"url\":\"http://localhost:9610/static/2024/01/03/审查表_20240103145623A030.docx\"}]', '[{\"name\":\"评审表.docx\",\"size\":380555,\"uid\":1704264977899,\"url\":\"http://localhost:9610/static/2024/01/03/评审表_20240103145623A031.docx\"}]', '[{\"name\":\"最终汇总表.docx\",\"size\":352256,\"uid\":1704264981568,\"url\":\"http://localhost:9610/static/2024/01/03/最终汇总表_20240103145623A032.docx\"}]', 1, '2024-01-03 14:56:05', '上海晨光文具股份有限公司');
INSERT INTO `bid_candidate` VALUES (31, 29, '2', 1, 100, 0, 1, '[{\"name\":\"专家签到表.docx\",\"size\":758683,\"uid\":1704272950764,\"url\":\"http://localhost:9610/static/2024/01/03/专家签到表_20240103170921A050.docx\"}]', '[{\"name\":\"审查表.docx\",\"size\":367140,\"uid\":1704272953931,\"url\":\"http://localhost:9610/static/2024/01/03/审查表_20240103170921A051.docx\"}]', '[{\"name\":\"评审表.docx\",\"size\":380555,\"uid\":1704272956759,\"url\":\"http://localhost:9610/static/2024/01/03/评审表_20240103170921A052.docx\"}]', '[{\"name\":\"最终汇总表.docx\",\"size\":352256,\"uid\":1704272960041,\"url\":\"http://localhost:9610/static/2024/01/03/最终汇总表_20240103170922A053.docx\"}]', 0, '2024-01-03 17:09:05', '腾腾科技（深圳）有限公司');
INSERT INTO `bid_candidate` VALUES (32, 29, '82', 1, 98, 0, 2, '[{\"name\":\"专家签到表.docx\",\"size\":758683,\"uid\":1704272978290,\"url\":\"http://localhost:9610/static/2024/01/03/专家签到表_20240103170948A054.docx\"}]', '[{\"name\":\"审查表.docx\",\"size\":367140,\"uid\":1704272981446,\"url\":\"http://localhost:9610/static/2024/01/03/审查表_20240103170948A055.docx\"}]', '[{\"name\":\"评审表.docx\",\"size\":380555,\"uid\":1704272984650,\"url\":\"http://localhost:9610/static/2024/01/03/评审表_20240103170949A056.docx\"}]', '[{\"name\":\"最终汇总表.docx\",\"size\":352256,\"uid\":1704272987520,\"url\":\"http://localhost:9610/static/2024/01/03/最终汇总表_20240103170949A057.docx\"}]', 1, '2024-01-03 17:09:32', '得力集团');
INSERT INTO `bid_candidate` VALUES (33, 27, '2', 1, 100, 0, 1, '[{\"name\":\"02-数据库设计规范——简洁版.docx\",\"size\":39847,\"uid\":1704423407350,\"url\":\"http://localhost:9610/static/2024/01/05/02-数据库设计规范——简洁版_20240105105700A006.docx\"}]', '[{\"name\":\"03—数据库设计规范.doc\",\"size\":352256,\"uid\":1704423411435,\"url\":\"http://localhost:9610/static/2024/01/05/03—数据库设计规范_20240105105700A007.doc\"}]', '[{\"name\":\"02-Spring Cloud周测简单版.docx\",\"size\":41459,\"uid\":1704423414497,\"url\":\"http://localhost:9610/static/2024/01/05/02-Spring Cloud周测简单版_20240105105700A008.docx\"}]', '[{\"name\":\"鸿鹄双功能显示.docx\",\"size\":12982,\"uid\":1704423418588,\"url\":\"http://localhost:9610/static/2024/01/05/鸿鹄双功能显示_20240105105701A009.docx\"}]', 0, '2024-01-05 10:56:43', '腾腾科技（深圳）有限公司');

-- ----------------------------
-- Table structure for bid_committee
-- ----------------------------
DROP TABLE IF EXISTS `bid_committee`;
CREATE TABLE `bid_committee`  (
  `pb_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评标ID',
  `xid` int(11) NULL DEFAULT NULL COMMENT '抽取申请ID',
  `pb_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `pb_sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `pb_idcard` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `pb_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `pb_genre` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专家类型',
  `pb_types` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专家类别',
  `jid` int(11) NULL DEFAULT NULL COMMENT '专家ID',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目Id',
  PRIMARY KEY (`pb_id`) USING BTREE,
  INDEX `FK_Relationship_35`(`xid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评标委员会表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_committee
-- ----------------------------
INSERT INTO `bid_committee` VALUES (48, 41, NULL, NULL, NULL, NULL, NULL, NULL, 11, 18);
INSERT INTO `bid_committee` VALUES (49, 41, NULL, NULL, NULL, NULL, NULL, NULL, 6, 18);
INSERT INTO `bid_committee` VALUES (50, 41, NULL, NULL, NULL, NULL, NULL, NULL, 2, 18);
INSERT INTO `bid_committee` VALUES (51, 41, NULL, NULL, NULL, NULL, NULL, NULL, 12, 18);
INSERT INTO `bid_committee` VALUES (52, 41, NULL, NULL, NULL, NULL, NULL, NULL, 3, 18);
INSERT INTO `bid_committee` VALUES (53, 42, NULL, NULL, NULL, NULL, NULL, NULL, 7, 22);
INSERT INTO `bid_committee` VALUES (54, 42, NULL, NULL, NULL, NULL, NULL, NULL, 6, 22);
INSERT INTO `bid_committee` VALUES (55, 42, NULL, NULL, NULL, NULL, NULL, NULL, 12, 22);
INSERT INTO `bid_committee` VALUES (56, 42, NULL, NULL, NULL, NULL, NULL, NULL, 3, 22);
INSERT INTO `bid_committee` VALUES (57, 42, NULL, NULL, NULL, NULL, NULL, NULL, 11, 22);
INSERT INTO `bid_committee` VALUES (63, 44, NULL, NULL, NULL, NULL, NULL, NULL, 11, 20);
INSERT INTO `bid_committee` VALUES (64, 44, NULL, NULL, NULL, NULL, NULL, NULL, 1, 20);
INSERT INTO `bid_committee` VALUES (65, 44, NULL, NULL, NULL, NULL, NULL, NULL, 12, 20);
INSERT INTO `bid_committee` VALUES (66, 44, NULL, NULL, NULL, NULL, NULL, NULL, 2, 20);
INSERT INTO `bid_committee` VALUES (67, 44, NULL, NULL, NULL, NULL, NULL, NULL, 3, 20);
INSERT INTO `bid_committee` VALUES (68, 45, NULL, NULL, NULL, NULL, NULL, NULL, 3, 28);
INSERT INTO `bid_committee` VALUES (69, 45, NULL, NULL, NULL, NULL, NULL, NULL, 4, 28);
INSERT INTO `bid_committee` VALUES (70, 45, NULL, NULL, NULL, NULL, NULL, NULL, 6, 28);
INSERT INTO `bid_committee` VALUES (71, 45, NULL, NULL, NULL, NULL, NULL, NULL, 7, 28);
INSERT INTO `bid_committee` VALUES (72, 45, NULL, NULL, NULL, NULL, NULL, NULL, 1, 28);
INSERT INTO `bid_committee` VALUES (73, 46, NULL, NULL, NULL, NULL, NULL, NULL, 4, 29);
INSERT INTO `bid_committee` VALUES (74, 46, NULL, NULL, NULL, NULL, NULL, NULL, 2, 29);
INSERT INTO `bid_committee` VALUES (75, 46, NULL, NULL, NULL, NULL, NULL, NULL, 7, 29);
INSERT INTO `bid_committee` VALUES (76, 46, NULL, NULL, NULL, NULL, NULL, NULL, 8, 29);
INSERT INTO `bid_committee` VALUES (77, 46, NULL, NULL, NULL, NULL, NULL, NULL, 6, 29);
INSERT INTO `bid_committee` VALUES (83, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 27);
INSERT INTO `bid_committee` VALUES (84, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, 27);
INSERT INTO `bid_committee` VALUES (85, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, 27);
INSERT INTO `bid_committee` VALUES (86, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 27);
INSERT INTO `bid_committee` VALUES (87, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11, 27);

-- ----------------------------
-- Table structure for bid_documents
-- ----------------------------
DROP TABLE IF EXISTS `bid_documents`;
CREATE TABLE `bid_documents`  (
  `wid` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `w_title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件标题',
  `w_size` int(11) NULL DEFAULT NULL COMMENT '文件大小',
  `w_upload_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `fileName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传路径',
  PRIMARY KEY (`wid`) USING BTREE,
  INDEX `FK_Relationship_17`(`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '招标文件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_documents
-- ----------------------------
INSERT INTO `bid_documents` VALUES (23, 18, '初始附件-01-软件开发Java设计规范——简洁版.docx', 13, '2023-12-29 16:31:56', '01-软件开发Java设计规范——简洁版.docx', 'http://localhost:9610/static/2023/12/29/01-软件开发Java设计规范——简洁版_20231229163156A004.docx');
INSERT INTO `bid_documents` VALUES (24, 18, '初始附件-02-Spring Cloud周测简单版.docx', 40, '2023-12-29 16:31:56', '02-Spring Cloud周测简单版.docx', 'http://localhost:9610/static/2023/12/29/02-Spring Cloud周测简单版_20231229163156A005.docx');
INSERT INTO `bid_documents` VALUES (25, 18, '初始附件-02-数据库设计规范——简洁版.docx', 38, '2023-12-29 16:31:57', '02-数据库设计规范——简洁版.docx', 'http://localhost:9610/static/2023/12/29/02-数据库设计规范——简洁版_20231229163156A003.docx');
INSERT INTO `bid_documents` VALUES (26, 21, '初始附件-111.txt', 0, '2024-01-01 08:10:28', '111.txt', 'http://localhost:9610/static/2024/01/01/111_20240101201029A021.txt');
INSERT INTO `bid_documents` VALUES (28, 22, '初始附件-02-仓库管理系统需求规格说明书-评审版.docx', 1004, '2024-01-02 10:10:09', '02-仓库管理系统需求规格说明书-评审版.docx', 'http://localhost:9610/static/2024/01/02/02-仓库管理系统需求规格说明书-评审版_20240102101009A005.docx');
INSERT INTO `bid_documents` VALUES (29, 22, '初始附件-02-Gitee——项目管理工具的使用.docx', 2471, '2024-01-02 10:10:10', '02-Gitee——项目管理工具的使用.docx', 'http://localhost:9610/static/2024/01/02/02-Gitee——项目管理工具的使用_20240102101009A006.docx');
INSERT INTO `bid_documents` VALUES (30, 20, '初始附件-02-仓库管理系统需求规格说明书-评审版.docx', 1004, '2024-01-02 19:51:49', '02-仓库管理系统需求规格说明书-评审版.docx', 'http://localhost:9610/static/2024/01/02/02-仓库管理系统需求规格说明书-评审版_20240102195149A018.docx');
INSERT INTO `bid_documents` VALUES (31, 20, '初始附件-02-Gitee——项目管理工具的使用.docx', 2471, '2024-01-02 19:51:49', '02-Gitee——项目管理工具的使用.docx', 'http://localhost:9610/static/2024/01/02/02-Gitee——项目管理工具的使用_20240102195149A019.docx');
INSERT INTO `bid_documents` VALUES (33, 28, '技术标准与要求', 2500, '2024-01-03 14:07:08', '技术标准与要求.docx', 'http://localhost:9610/static/2024/01/03/技术标准与要求_20240103140708A012.docx');
INSERT INTO `bid_documents` VALUES (34, 29, '技术标准与要求', 1000, '2024-01-03 17:02:13', '技术标准与要求.docx', 'http://localhost:9610/static/2024/01/03/技术标准与要求_20240103170213A047.docx');
INSERT INTO `bid_documents` VALUES (35, 27, '初始附件-bat.txt', 0, '2024-01-05 09:50:24', 'bat.txt', 'http://localhost:9610/static/2024/01/05/bat_20240105095023A001.txt');
INSERT INTO `bid_documents` VALUES (36, 27, '初始附件-CRM.txt', 1, '2024-01-05 09:50:25', 'CRM.txt', 'http://localhost:9610/static/2024/01/05/CRM_20240105095023A002.txt');

-- ----------------------------
-- Table structure for bid_get_tender
-- ----------------------------
DROP TABLE IF EXISTS `bid_get_tender`;
CREATE TABLE `bid_get_tender`  (
  `yid` int(11) NOT NULL AUTO_INCREMENT COMMENT '获取标书ID',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `y_download_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下载时间',
  PRIMARY KEY (`yid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '获取标书表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_get_tender
-- ----------------------------
INSERT INTO `bid_get_tender` VALUES (10, 18, 2, '2023-12-29 16:56:04');
INSERT INTO `bid_get_tender` VALUES (11, 18, 82, '2023-12-29 17:00:18');
INSERT INTO `bid_get_tender` VALUES (12, 18, 83, '2023-12-29 17:04:52');
INSERT INTO `bid_get_tender` VALUES (13, 22, 2, '2024-01-02 10:17:22');
INSERT INTO `bid_get_tender` VALUES (14, 20, 2, '2024-01-02 20:35:28');
INSERT INTO `bid_get_tender` VALUES (15, 29, 2, '2024-01-03 17:04:02');
INSERT INTO `bid_get_tender` VALUES (16, 27, 2, '2024-01-05 10:01:08');

-- ----------------------------
-- Table structure for bid_notice
-- ----------------------------
DROP TABLE IF EXISTS `bid_notice`;
CREATE TABLE `bid_notice`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '招标公告ID',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `u_title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `u_project` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联项目',
  `u_money` decimal(10, 0) NULL DEFAULT NULL COMMENT '项目资金',
  `u_get_time` datetime(0) NULL DEFAULT NULL COMMENT '标注获取时间',
  `u_accept_time` datetime(0) NULL DEFAULT NULL COMMENT '接受答疑时间',
  `u_end_time` datetime(0) NULL DEFAULT NULL COMMENT '投标截止时间/获取标书截止时间',
  `u_kai_time` datetime(0) NULL DEFAULT NULL COMMENT '开标时间',
  `fj_annex` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件集',
  `fj_status` int(11) NULL DEFAULT 1 COMMENT '招标公告审批状态',
  `fj_remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `u_updateTime` datetime(0) NULL DEFAULT NULL COMMENT '状态为已发布  公告公示时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '招标公告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_notice
-- ----------------------------
INSERT INTO `bid_notice` VALUES (52, 18, '大海采购有限公司年度采购计划招标第一次公告', '大海采购有限公司年度采购计划招标', 1000000, '2023-12-29 04:32:53', '2023-12-30 09:00:00', '2023-12-31 09:00:00', '2024-01-02 03:00:00', '[{\"id\":1,\"name\":\"03-入职经验谈-问答题.docx\",\"size\":12559,\"uid\":1703838821914,\"url\":\"http://localhost:9610/static/2023/12/29/03-入职经验谈-问答题_20231229163442A006.docx\"},{\"id\":2,\"name\":\"03—数据库设计规范.doc\",\"size\":352256,\"uid\":1703838821915,\"url\":\"http://localhost:9610/static/2023/12/29/03—数据库设计规范_20231229163442A007.doc\"}]', 3, '<h1 class=\"ql-align-center\">湖北六国2024年度编织袋采购招标公告</h1><p>发布时间 :&nbsp;2023-12-29</p><p><br></p><p>	一、项目概况</p><p>	项目名称：湖北六国2024年度编织袋采购。</p><p>	招标编号：湖北六国-2023-编织袋</p><p>	1．招标方式：公开招标，采用电子招标投标方式</p><p>	2．资格审查方式：资格后审</p><p>	3．招标机构：铜陵嘉事德管理咨询有限责任公司</p><p>	4．报名时间：即日起至2024年1月9日17时</p><p>	5．报名地点：*****&nbsp;：http://www.youzhicai.com/）</p><p>	6．招标人单位：湖北六国化工股份有限公司</p><p>	7．资金来源：自筹</p><p>	8．交货地点：*****&nbsp;。</p><p>	9．交货期：招标方提供编织袋计划给中标单位，外编内塑袋和内粘膜袋供货周期为计划下达日起7天内到货（含新版），双面彩色腹膜编织袋供货周期为计划下达日起7天内到货，新版面10天内到货。</p><p>	计划数量：全年总量约464万条，其中：</p><p>	一标段：双面彩色覆膜编织袋（内套袋）：约100万条；</p><p>	二标段：内粘膜编织袋50KG（含黄色、白色、紫罗兰色）：约200万条；</p><p>	三标段：白色外编内塑编织袋25KG（内套袋）；约144万条；</p><p>	黄色外编内塑编织袋50KG（内套袋）：约20万条；</p><p>	10．合同类型：可调单价合同</p><p>	11．标段：本次招标按编织袋规格划分为3个标段(具体规格及要求见采购清单)。</p><p>	12．合同期限：项目总实施时间为3年。采取“1+1+1”模式，即首签合同期限为一年，首签期满后，依据公司相关规定和合同约定，经考核合格的，方可签订下一年合同。</p><p>	二、投标人资质要求</p><p>	（1）投标人应具有独立法人资格，提供有效的营业执照；</p><p>	（2）投标人在资金、人员、技术及设备等方面具有自身承担招标项目的能力和条件，有圆满完成类似项目服务的成功经验。</p><p>	（3）业绩要求：具有2020年1月1日以后（以合同签订时间为准），供货业绩1个及以上（业绩证明资料：合同或中标通知书复印件或者加盖用户章的反馈意见书，业绩规模至少是年供货量100万条）；</p><p>	（4）投标人应为专业编织袋生产制造商，不接受贸易商投标；</p><p>	三、获取招标文件方式</p><p>	1、获取招标文件时间:&nbsp;即日起至2024年1月9日17时</p><p>	2、获取地点：*****&nbsp;：http://www.youzhicai.com/）报名、并下载招标文件。</p><p>	四、招标文件费用及技术服务费</p><p>	1、文件费：招标文件每套售价200&nbsp;元，售后不退（按整个项目收取文件费）。</p><p>	招标文件费用缴纳至铜陵嘉事德管理咨询有限责任公司。接受招标文件费用的银行及账号：</p><p>	建行账号名：铜陵嘉事德管理咨询有限责任公司</p><p>	开户银行：中国建设银行铜陵市开发区支行</p><p>	账号：34001668408053006201</p><p>	投标人支付后，应在优质采系统内上传缴费回执，铜陵嘉事德管理咨询有限责任公司审核通过后可下载招标文件。</p><p>	特别说明：缴费及投标保证金按整个项目收取（不按标段收取），但投标单位投标哪个标段就须上传所投标段的招标文件缴费回执（即同一张缴费回执）；电子版投标文件也是如此，即投标哪个标段，就须上传哪个标段的电子版投标文件。</p><p>	2、本次招标收取平台技术服务费500元/年或260元/年。投标人登录投标工具客户端缴纳平台技术服务费，如需开具发票请联系*****。</p><p>	五、投标截止时间及地点、开标时间及地点</p><p>	1、投标文件递交的截止时间（投标截止时间，下同）为2024年1月18日9时00分，投标人应在截止时间前通过优质采投标工具客户端递交电子投标文件。</p><p>	2、逾期递交的电子投标文件，优质采云采购平台将予以拒收。</p><p>	六、发布媒体</p><p>	中国招标投标公共服务平台（www.cebpubservice.com）、“优质采云采购平台”（网址：&nbsp;<a href=\"http://www.youzhicai.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://www.youzhicai.com/</a>）、安徽省招标投标信息网（www.ahtba.org.cn）、中国采购与招标网（www.chinabidding.com.cn）。</p><p>	七、联系方式</p><p>	招标机构：铜陵嘉事德管理咨询有限责任公司</p><p>	地址：&nbsp;*****&nbsp;2758号铜化集团公司院内</p><p>	联系电话：*****</p><p>	联系人：*****&nbsp;<span style=\"color: rgb(229, 48, 30);\">登录查看更多</span></p><p>	招标人名称：湖北六国化工股份有限公司</p><p>	地址：*****</p><p>	联系人：*****&nbsp;&nbsp;&nbsp;电话：*****</p><p>	八、重要说明</p><p>	1.购买招标资料须登陆“优质采云采购平台”，请未注册的投标人及时注册审核手续，注册请登录优质采首页中“我要注册”（http://www.youzhicai.com/）。注册咨询电话：*****。因未及时办理注册审核手续影响报名及投标的，责任自负；</p><p>	2.本项目将采用网上支付文件、资料费用；网上下载招标文件；网上发布澄清、修改文件；网上发布招标控制价。招标人不另行书面发布上述内容，投标人应及时关注、查阅会员系统发布的上述相关内容，否则责任自负；</p><p>	3.会员报名成功后直接采用网上支付系统支付招标文件费用，直接下载招标文件及其它资料（含澄清、修改文件等）。网上支付采用银联快捷支付方式，只要个人银行卡上有银联标志，并登记了手机号码，即可通知手机验证码实现快捷支付，无需开通网上银行。</p><p>	4.提醒事项：用户注册成功后如因相关注册信息发生变更者（注：与初始注册信息登记不一致均属），会员应及时申请变更（咨询电话：*****），如因会员自身原因未及时变更导致不利后果者，会员责任自负。</p><p>	5.电子投标文件必须使用投标文件制作工具制作生成并上传。投标文件制作工具下载地址：&nbsp;http://file.youzhicai.com/files/BidderTools.rar，</p><p>	投标文件制作工具使用说明书及视频教程下载地址:&nbsp;<a href=\"http://file.youzhicai.com/files/BidderHelp.rar\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://file.youzhicai.com/files/BidderHelp.rar</a>。</p><p>	投标工具下载地址：http://toolcdn.youzhicai.com/tools/BidderTools.zip</p><p>	帮助文档地址：https://file.youzhicai.com/files/BidderHelp.rar</p><p>	6.电子投标文件制作及上传咨询热线：400-0099-555&nbsp;&nbsp;*****。</p><p>	7.本招标项目采用全流程电子化招投标方式，投标人须办理CA数字证书，CA数字证书用于电子投标文件的签章及加密上传；</p><p>	8.CA数字证书办理详见《关于优质采平台数字证书办理的须知》</p><p>	（<a href=\"http://www.youzhicai.com/nd/a_8f80a7ec-911f-4c4d-a123-f8849880f045.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://www.youzhicai.com/nd/a_8f80a7ec-911f-4c4d-a123-f8849880f045.html</a>）；</p><p>	CA办理须知：https://www.youzhicai.com/ActivityTopic/AdviceDetail/8f80a7ec-911f-4c4d-a123-f8849880f045</p><p>	咨询热线：400-0099-555，*****。</p><p>	投标单位开标时须带CA，或者远程解密。解密时长40分钟(非优质采平台或招标人原因导致解密不成功，视为自动放弃本次投标资格)。</p><p>		备注：</p><p>	1&nbsp;、本项目收取平台基础服务费500元/年，招标文件费200元，其余平台相关增值服务费用（如：投标通3600元/年等）为自愿购买项目，非强制收取，请投标人按需选择。</p><p>	2、 若发现投标单位存在陪标围标串标、弄虚作假等违法违规情况，将按照相关法律法规处理，并列入铜化集团公司黑名单，五年内不得参与铜化集团公司所有招投标项目（投标文件所附各项证明材料必须真实有效，评委及招标机构视情况，调取原件查验）。</p><p>	3、报名投标单位如确定不能正常参加投标，请在开标前一天上传弃标函，以免在优质采平台留下不良记录，影响后续报名参标。</p>', NULL);
INSERT INTO `bid_notice` VALUES (53, 18, '大海采购有限公司年度采购计划招标第二次公告', '大海采购有限公司年度采购计划招标', 1000000, '2023-12-29 09:00:00', '2023-12-30 09:00:00', '2023-12-31 09:00:00', '2024-01-01 09:00:00', '[{\"id\":1,\"name\":\"02-数据库设计规范——简洁版.docx\",\"size\":39847,\"uid\":1703838958735,\"url\":\"http://localhost:9610/static/2023/12/29/02-数据库设计规范——简洁版_20231229163636A008.docx\"},{\"id\":2,\"name\":\"03-入职经验谈-问答题.docx\",\"size\":12559,\"uid\":1703838958736,\"url\":\"http://localhost:9610/static/2023/12/29/03-入职经验谈-问答题_20231229163636A009.docx\"}]', 4, '<h1 class=\"ql-align-center\">湖北六国2024年度编织袋采购招标公告</h1><p>发布时间 :&nbsp;2023-12-29</p><p><br></p><p>	一、项目概况</p><p>	项目名称：湖北六国2024年度编织袋采购。</p><p>	招标编号：湖北六国-2023-编织袋</p><p>	1．招标方式：公开招标，采用电子招标投标方式</p><p>	2．资格审查方式：资格后审</p><p>	3．招标机构：铜陵嘉事德管理咨询有限责任公司</p><p>	4．报名时间：即日起至2024年1月9日17时</p><p>	5．报名地点：*****&nbsp;：http://www.youzhicai.com/）</p><p>	6．招标人单位：湖北六国化工股份有限公司</p><p>	7．资金来源：自筹</p><p>	8．交货地点：*****&nbsp;。</p><p>	9．交货期：招标方提供编织袋计划给中标单位，外编内塑袋和内粘膜袋供货周期为计划下达日起7天内到货（含新版），双面彩色腹膜编织袋供货周期为计划下达日起7天内到货，新版面10天内到货。</p><p>	计划数量：全年总量约464万条，其中：</p><p>	一标段：双面彩色覆膜编织袋（内套袋）：约100万条；</p><p>	二标段：内粘膜编织袋50KG（含黄色、白色、紫罗兰色）：约200万条；</p><p>	三标段：白色外编内塑编织袋25KG（内套袋）；约144万条；</p><p>	黄色外编内塑编织袋50KG（内套袋）：约20万条；</p><p>	10．合同类型：可调单价合同</p><p>	11．标段：本次招标按编织袋规格划分为3个标段(具体规格及要求见采购清单)。</p><p>	12．合同期限：项目总实施时间为3年。采取“1+1+1”模式，即首签合同期限为一年，首签期满后，依据公司相关规定和合同约定，经考核合格的，方可签订下一年合同。</p><p>	二、投标人资质要求</p><p>	（1）投标人应具有独立法人资格，提供有效的营业执照；</p><p>	（2）投标人在资金、人员、技术及设备等方面具有自身承担招标项目的能力和条件，有圆满完成类似项目服务的成功经验。</p><p>	（3）业绩要求：具有2020年1月1日以后（以合同签订时间为准），供货业绩1个及以上（业绩证明资料：合同或中标通知书复印件或者加盖用户章的反馈意见书，业绩规模至少是年供货量100万条）；</p><p>	（4）投标人应为专业编织袋生产制造商，不接受贸易商投标；</p><p>	三、获取招标文件方式</p><p>	1、获取招标文件时间:&nbsp;即日起至2024年1月9日17时</p><p>	2、获取地点：*****&nbsp;：http://www.youzhicai.com/）报名、并下载招标文件。</p><p>	四、招标文件费用及技术服务费</p><p>	1、文件费：招标文件每套售价200&nbsp;元，售后不退（按整个项目收取文件费）。</p><p>	招标文件费用缴纳至铜陵嘉事德管理咨询有限责任公司。接受招标文件费用的银行及账号：</p><p>	建行账号名：铜陵嘉事德管理咨询有限责任公司</p><p>	开户银行：中国建设银行铜陵市开发区支行</p><p>	账号：34001668408053006201</p><p>	投标人支付后，应在优质采系统内上传缴费回执，铜陵嘉事德管理咨询有限责任公司审核通过后可下载招标文件。</p><p>	特别说明：缴费及投标保证金按整个项目收取（不按标段收取），但投标单位投标哪个标段就须上传所投标段的招标文件缴费回执（即同一张缴费回执）；电子版投标文件也是如此，即投标哪个标段，就须上传哪个标段的电子版投标文件。</p><p>	2、本次招标收取平台技术服务费500元/年或260元/年。投标人登录投标工具客户端缴纳平台技术服务费，如需开具发票请联系*****。</p><p>	五、投标截止时间及地点、开标时间及地点</p><p>	1、投标文件递交的截止时间（投标截止时间，下同）为2024年1月18日9时00分，投标人应在截止时间前通过优质采投标工具客户端递交电子投标文件。</p><p>	2、逾期递交的电子投标文件，优质采云采购平台将予以拒收。</p><p>	六、发布媒体</p><p>	中国招标投标公共服务平台（www.cebpubservice.com）、“优质采云采购平台”（网址：&nbsp;<a href=\"http://www.youzhicai.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://www.youzhicai.com/</a>）、安徽省招标投标信息网（www.ahtba.org.cn）、中国采购与招标网（www.chinabidding.com.cn）。</p><p>	七、联系方式</p><p>	招标机构：铜陵嘉事德管理咨询有限责任公司</p><p>	地址：&nbsp;*****&nbsp;2758号铜化集团公司院内</p><p>	联系电话：*****</p><p>	联系人：*****&nbsp;<span style=\"color: rgb(229, 48, 30);\">登录查看更多</span></p><p>	招标人名称：湖北六国化工股份有限公司</p><p>	地址：*****</p><p>	联系人：*****&nbsp;&nbsp;&nbsp;电话：*****</p><p>	八、重要说明</p><p>	1.购买招标资料须登陆“优质采云采购平台”，请未注册的投标人及时注册审核手续，注册请登录优质采首页中“我要注册”（http://www.youzhicai.com/）。注册咨询电话：*****。因未及时办理注册审核手续影响报名及投标的，责任自负；</p><p>	2.本项目将采用网上支付文件、资料费用；网上下载招标文件；网上发布澄清、修改文件；网上发布招标控制价。招标人不另行书面发布上述内容，投标人应及时关注、查阅会员系统发布的上述相关内容，否则责任自负；</p><p>	3.会员报名成功后直接采用网上支付系统支付招标文件费用，直接下载招标文件及其它资料（含澄清、修改文件等）。网上支付采用银联快捷支付方式，只要个人银行卡上有银联标志，并登记了手机号码，即可通知手机验证码实现快捷支付，无需开通网上银行。</p><p>	4.提醒事项：用户注册成功后如因相关注册信息发生变更者（注：与初始注册信息登记不一致均属），会员应及时申请变更（咨询电话：*****），如因会员自身原因未及时变更导致不利后果者，会员责任自负。</p><p>	5.电子投标文件必须使用投标文件制作工具制作生成并上传。投标文件制作工具下载地址：&nbsp;http://file.youzhicai.com/files/BidderTools.rar，</p><p>	投标文件制作工具使用说明书及视频教程下载地址:&nbsp;<a href=\"http://file.youzhicai.com/files/BidderHelp.rar\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://file.youzhicai.com/files/BidderHelp.rar</a>。</p><p>	投标工具下载地址：http://toolcdn.youzhicai.com/tools/BidderTools.zip</p><p>	帮助文档地址：https://file.youzhicai.com/files/BidderHelp.rar</p><p>	6.电子投标文件制作及上传咨询热线：400-0099-555&nbsp;&nbsp;*****。</p><p>	7.本招标项目采用全流程电子化招投标方式，投标人须办理CA数字证书，CA数字证书用于电子投标文件的签章及加密上传；</p><p>	8.CA数字证书办理详见《关于优质采平台数字证书办理的须知》</p><p>	（<a href=\"http://www.youzhicai.com/nd/a_8f80a7ec-911f-4c4d-a123-f8849880f045.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://www.youzhicai.com/nd/a_8f80a7ec-911f-4c4d-a123-f8849880f045.html</a>）；</p><p>	CA办理须知：https://www.youzhicai.com/ActivityTopic/AdviceDetail/8f80a7ec-911f-4c4d-a123-f8849880f045</p><p>	咨询热线：400-0099-555，*****。</p><p>	投标单位开标时须带CA，或者远程解密。解密时长40分钟(非优质采平台或招标人原因导致解密不成功，视为自动放弃本次投标资格)。</p><p>		备注：</p><p>	1&nbsp;、本项目收取平台基础服务费500元/年，招标文件费200元，其余平台相关增值服务费用（如：投标通3600元/年等）为自愿购买项目，非强制收取，请投标人按需选择。</p><p>	2、 若发现投标单位存在陪标围标串标、弄虚作假等违法违规情况，将按照相关法律法规处理，并列入铜化集团公司黑名单，五年内不得参与铜化集团公司所有招投标项目（投标文件所附各项证明材料必须真实有效，评委及招标机构视情况，调取原件查验）。</p><p>	3、报名投标单位如确定不能正常参加投标，请在开标前一天上传弃标函，以免在优质采平台留下不良记录，影响后续报名参标。</p>', NULL);
INSERT INTO `bid_notice` VALUES (54, 18, '大海采购有限公司年度采购计划招标第三次公告', '大海采购有限公司年度采购计划招标', 1000000, '2023-12-28 09:00:00', '2023-12-29 09:00:00', '2023-12-30 09:00:00', '2023-12-31 09:00:00', '[{\"id\":1,\"name\":\"02-数据库设计规范——简洁版.docx\",\"size\":39847,\"uid\":1703838958735,\"url\":\"http://localhost:9610/static/2023/12/29/02-数据库设计规范——简洁版_20231229163636A008.docx\",\"status\":\"success\"},{\"id\":2,\"name\":\"03-入职经验谈-问答题.docx\",\"size\":12559,\"uid\":1703838958736,\"url\":\"http://localhost:9610/static/2023/12/29/03-入职经验谈-问答题_20231229163636A009.docx\",\"status\":\"success\"}]', 5, '<h1 class=\"ql-align-center\">大海采购公告年度采购计划第四次招标公告</h1><p>发布时间 :&nbsp;2023-12-29</p><p><br></p><p>	一、项目概况</p><p>	项目名称：大海采购有限公司年度采购计划招标。</p><p>	招标编号：湖北六国-2023-编织袋</p><p>	1．招标方式：公开招标，采用电子招标投标方式</p><p>	2．资格审查方式：资格后审</p><p>	3．招标机构：铜陵嘉事德管理咨询有限责任公司</p><p>	4．报名时间：即日起至2024年1月9日17时</p><p>	5．报名地点：*****&nbsp;：http://www.youzhicai.com/）</p><p>	6．招标人单位：湖北六国化工股份有限公司</p><p>	7．资金来源：自筹</p><p>	8．交货地点：*****&nbsp;。</p><p>	9．交货期：招标方提供编织袋计划给中标单位，外编内塑袋和内粘膜袋供货周期为计划下达日起7天内到货（含新版），双面彩色腹膜编织袋供货周期为计划下达日起7天内到货，新版面10天内到货。</p><p>	计划数量：全年总量约464万条，其中：</p><p>	一标段：双面彩色覆膜编织袋（内套袋）：约100万条；</p><p>	二标段：内粘膜编织袋50KG（含黄色、白色、紫罗兰色）：约200万条；</p><p>	三标段：白色外编内塑编织袋25KG（内套袋）；约144万条；</p><p>	黄色外编内塑编织袋50KG（内套袋）：约20万条；</p><p>	10．合同类型：可调单价合同</p><p>	11．标段：本次招标按编织袋规格划分为3个标段(具体规格及要求见采购清单)。</p><p>	12．合同期限：项目总实施时间为3年。采取“1+1+1”模式，即首签合同期限为一年，首签期满后，依据公司相关规定和合同约定，经考核合格的，方可签订下一年合同。</p><p>	二、投标人资质要求</p><p>	（1）投标人应具有独立法人资格，提供有效的营业执照；</p><p>	（2）投标人在资金、人员、技术及设备等方面具有自身承担招标项目的能力和条件，有圆满完成类似项目服务的成功经验。</p><p>	（3）业绩要求：具有2020年1月1日以后（以合同签订时间为准），供货业绩1个及以上（业绩证明资料：合同或中标通知书复印件或者加盖用户章的反馈意见书，业绩规模至少是年供货量100万条）；</p><p>	（4）投标人应为专业编织袋生产制造商，不接受贸易商投标；</p><p>	三、获取招标文件方式</p><p>	1、获取招标文件时间:&nbsp;即日起至2024年1月9日17时</p><p>	2、获取地点：*****&nbsp;：http://www.youzhicai.com/）报名、并下载招标文件。</p><p>	四、招标文件费用及技术服务费</p><p>	1、文件费：招标文件每套售价200&nbsp;元，售后不退（按整个项目收取文件费）。</p><p>	招标文件费用缴纳至铜陵嘉事德管理咨询有限责任公司。接受招标文件费用的银行及账号：</p><p>	建行账号名：铜陵嘉事德管理咨询有限责任公司</p><p>	开户银行：中国建设银行铜陵市开发区支行</p><p>	账号：34001668408053006201</p><p>	投标人支付后，应在优质采系统内上传缴费回执，铜陵嘉事德管理咨询有限责任公司审核通过后可下载招标文件。</p><p>	特别说明：缴费及投标保证金按整个项目收取（不按标段收取），但投标单位投标哪个标段就须上传所投标段的招标文件缴费回执（即同一张缴费回执）；电子版投标文件也是如此，即投标哪个标段，就须上传哪个标段的电子版投标文件。</p><p>	2、本次招标收取平台技术服务费500元/年或260元/年。投标人登录投标工具客户端缴纳平台技术服务费，如需开具发票请联系*****。</p><p>	五、投标截止时间及地点、开标时间及地点</p><p>	1、投标文件递交的截止时间（投标截止时间，下同）为2024年1月18日9时00分，投标人应在截止时间前通过优质采投标工具客户端递交电子投标文件。</p><p>	2、逾期递交的电子投标文件，优质采云采购平台将予以拒收。</p><p>	六、发布媒体</p><p>	中国招标投标公共服务平台（www.cebpubservice.com）、“优质采云采购平台”（网址：&nbsp;<a href=\"http://www.youzhicai.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://www.youzhicai.com/</a>）、安徽省招标投标信息网（www.ahtba.org.cn）、中国采购与招标网（www.chinabidding.com.cn）。</p><p>	七、联系方式</p><p>	招标机构：铜陵嘉事德管理咨询有限责任公司</p><p>	地址：&nbsp;*****&nbsp;2758号铜化集团公司院内</p><p>	联系电话：*****</p><p>	联系人：*****&nbsp;<span style=\"color: rgb(229, 48, 30);\">登录查看更多</span></p><p>	招标人名称：湖北六国化工股份有限公司</p><p>	地址：*****</p><p>	联系人：*****&nbsp;&nbsp;&nbsp;电话：*****</p><p>	八、重要说明</p><p>	1.购买招标资料须登陆“优质采云采购平台”，请未注册的投标人及时注册审核手续，注册请登录优质采首页中“我要注册”（http://www.youzhicai.com/）。注册咨询电话：*****。因未及时办理注册审核手续影响报名及投标的，责任自负；</p><p>	2.本项目将采用网上支付文件、资料费用；网上下载招标文件；网上发布澄清、修改文件；网上发布招标控制价。招标人不另行书面发布上述内容，投标人应及时关注、查阅会员系统发布的上述相关内容，否则责任自负；</p><p>	3.会员报名成功后直接采用网上支付系统支付招标文件费用，直接下载招标文件及其它资料（含澄清、修改文件等）。网上支付采用银联快捷支付方式，只要个人银行卡上有银联标志，并登记了手机号码，即可通知手机验证码实现快捷支付，无需开通网上银行。</p><p>	4.提醒事项：用户注册成功后如因相关注册信息发生变更者（注：与初始注册信息登记不一致均属），会员应及时申请变更（咨询电话：*****），如因会员自身原因未及时变更导致不利后果者，会员责任自负。</p><p>	5.电子投标文件必须使用投标文件制作工具制作生成并上传。投标文件制作工具下载地址：&nbsp;http://file.youzhicai.com/files/BidderTools.rar，</p><p>	投标文件制作工具使用说明书及视频教程下载地址:&nbsp;<a href=\"http://file.youzhicai.com/files/BidderHelp.rar\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://file.youzhicai.com/files/BidderHelp.rar</a>。</p><p>	投标工具下载地址：http://toolcdn.youzhicai.com/tools/BidderTools.zip</p><p>	帮助文档地址：https://file.youzhicai.com/files/BidderHelp.rar</p><p>	6.电子投标文件制作及上传咨询热线：400-0099-555&nbsp;&nbsp;*****。</p><p>	7.本招标项目采用全流程电子化招投标方式，投标人须办理CA数字证书，CA数字证书用于电子投标文件的签章及加密上传；</p><p>	8.CA数字证书办理详见《关于优质采平台数字证书办理的须知》</p><p>	（<a href=\"http://www.youzhicai.com/nd/a_8f80a7ec-911f-4c4d-a123-f8849880f045.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://www.youzhicai.com/nd/a_8f80a7ec-911f-4c4d-a123-f8849880f045.html</a>）；</p><p>	CA办理须知：https://www.youzhicai.com/ActivityTopic/AdviceDetail/8f80a7ec-911f-4c4d-a123-f8849880f045</p><p>	咨询热线：400-0099-555，*****。</p><p>	投标单位开标时须带CA，或者远程解密。解密时长40分钟(非优质采平台或招标人原因导致解密不成功，视为自动放弃本次投标资格)。</p><p>		备注：</p><p>	1&nbsp;、本项目收取平台基础服务费500元/年，招标文件费200元，其余平台相关增值服务费用（如：投标通3600元/年等）为自愿购买项目，非强制收取，请投标人按需选择。</p><p>	2、 若发现投标单位存在陪标围标串标、弄虚作假等违法违规情况，将按照相关法律法规处理，并列入铜化集团公司黑名单，五年内不得参与铜化集团公司所有招投标项目（投标文件所附各项证明材料必须真实有效，评委及招标机构视情况，调取原件查验）。</p><p>	3、报名投标单位如确定不能正常参加投标，请在开标前一天上传弃标函，以免在优质采平台留下不良记录，影响后续报名参标。</p>', '2023-12-29 16:45:38');
INSERT INTO `bid_notice` VALUES (55, 18, '大海采购有限公司年度采购计划招标第四次公告', '大海采购有限公司年度采购计划招标', 1000000, '2023-12-28 09:00:00', '2023-12-29 09:00:00', '2023-12-30 09:00:00', '2023-12-31 09:00:00', '[{\"id\":1,\"name\":\"02-数据库设计规范——简洁版.docx\",\"size\":39847,\"uid\":1703839231883,\"url\":\"http://localhost:9610/static/2023/12/29/02-数据库设计规范——简洁版_20231229164034A012.docx\"},{\"id\":2,\"name\":\"03-入职经验谈-问答题.docx\",\"size\":12559,\"uid\":1703839231884,\"url\":\"http://localhost:9610/static/2023/12/29/03-入职经验谈-问答题_20231229164034A013.docx\"}]', 1, '<h1 class=\"ql-align-center\">大海采购公告年度采购计划第四次招标公告</h1><p>发布时间 :&nbsp;2023-12-29</p><p><br></p><p>	一、项目概况</p><p>	项目名称：大海采购有限公司年度采购计划招标。</p><p>	招标编号：湖北六国-2023-编织袋</p><p>	1．招标方式：公开招标，采用电子招标投标方式</p><p>	2．资格审查方式：资格后审</p><p>	3．招标机构：铜陵嘉事德管理咨询有限责任公司</p><p>	4．报名时间：即日起至2024年1月9日17时</p><p>	5．报名地点：*****&nbsp;：http://www.youzhicai.com/）</p><p>	6．招标人单位：湖北六国化工股份有限公司</p><p>	7．资金来源：自筹</p><p>	8．交货地点：*****&nbsp;。</p><p>	9．交货期：招标方提供编织袋计划给中标单位，外编内塑袋和内粘膜袋供货周期为计划下达日起7天内到货（含新版），双面彩色腹膜编织袋供货周期为计划下达日起7天内到货，新版面10天内到货。</p><p>	计划数量：全年总量约464万条，其中：</p><p>	一标段：双面彩色覆膜编织袋（内套袋）：约100万条；</p><p>	二标段：内粘膜编织袋50KG（含黄色、白色、紫罗兰色）：约200万条；</p><p>	三标段：白色外编内塑编织袋25KG（内套袋）；约144万条；</p><p>	黄色外编内塑编织袋50KG（内套袋）：约20万条；</p><p>	10．合同类型：可调单价合同</p><p>	11．标段：本次招标按编织袋规格划分为3个标段(具体规格及要求见采购清单)。</p><p>	12．合同期限：项目总实施时间为3年。采取“1+1+1”模式，即首签合同期限为一年，首签期满后，依据公司相关规定和合同约定，经考核合格的，方可签订下一年合同。</p><p>	二、投标人资质要求</p><p>	（1）投标人应具有独立法人资格，提供有效的营业执照；</p><p>	（2）投标人在资金、人员、技术及设备等方面具有自身承担招标项目的能力和条件，有圆满完成类似项目服务的成功经验。</p><p>	（3）业绩要求：具有2020年1月1日以后（以合同签订时间为准），供货业绩1个及以上（业绩证明资料：合同或中标通知书复印件或者加盖用户章的反馈意见书，业绩规模至少是年供货量100万条）；</p><p>	（4）投标人应为专业编织袋生产制造商，不接受贸易商投标；</p><p>	三、获取招标文件方式</p><p>	1、获取招标文件时间:&nbsp;即日起至2024年1月9日17时</p><p>	2、获取地点：*****&nbsp;：http://www.youzhicai.com/）报名、并下载招标文件。</p><p>	四、招标文件费用及技术服务费</p><p>	1、文件费：招标文件每套售价200&nbsp;元，售后不退（按整个项目收取文件费）。</p><p>	招标文件费用缴纳至铜陵嘉事德管理咨询有限责任公司。接受招标文件费用的银行及账号：</p><p>	建行账号名：铜陵嘉事德管理咨询有限责任公司</p><p>	开户银行：中国建设银行铜陵市开发区支行</p><p>	账号：34001668408053006201</p><p>	投标人支付后，应在优质采系统内上传缴费回执，铜陵嘉事德管理咨询有限责任公司审核通过后可下载招标文件。</p><p>	特别说明：缴费及投标保证金按整个项目收取（不按标段收取），但投标单位投标哪个标段就须上传所投标段的招标文件缴费回执（即同一张缴费回执）；电子版投标文件也是如此，即投标哪个标段，就须上传哪个标段的电子版投标文件。</p><p>	2、本次招标收取平台技术服务费500元/年或260元/年。投标人登录投标工具客户端缴纳平台技术服务费，如需开具发票请联系*****。</p><p>	五、投标截止时间及地点、开标时间及地点</p><p>	1、投标文件递交的截止时间（投标截止时间，下同）为2024年1月18日9时00分，投标人应在截止时间前通过优质采投标工具客户端递交电子投标文件。</p><p>	2、逾期递交的电子投标文件，优质采云采购平台将予以拒收。</p><p>	六、发布媒体</p><p>	中国招标投标公共服务平台（www.cebpubservice.com）、“优质采云采购平台”（网址：&nbsp;<a href=\"http://www.youzhicai.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://www.youzhicai.com/</a>）、安徽省招标投标信息网（www.ahtba.org.cn）、中国采购与招标网（www.chinabidding.com.cn）。</p><p>	七、联系方式</p><p>	招标机构：铜陵嘉事德管理咨询有限责任公司</p><p>	地址：&nbsp;*****&nbsp;2758号铜化集团公司院内</p><p>	联系电话：*****</p><p>	联系人：*****&nbsp;<span style=\"color: rgb(229, 48, 30);\">登录查看更多</span></p><p>	招标人名称：湖北六国化工股份有限公司</p><p>	地址：*****</p><p>	联系人：*****&nbsp;&nbsp;&nbsp;电话：*****</p><p>	八、重要说明</p><p>	1.购买招标资料须登陆“优质采云采购平台”，请未注册的投标人及时注册审核手续，注册请登录优质采首页中“我要注册”（http://www.youzhicai.com/）。注册咨询电话：*****。因未及时办理注册审核手续影响报名及投标的，责任自负；</p><p>	2.本项目将采用网上支付文件、资料费用；网上下载招标文件；网上发布澄清、修改文件；网上发布招标控制价。招标人不另行书面发布上述内容，投标人应及时关注、查阅会员系统发布的上述相关内容，否则责任自负；</p><p>	3.会员报名成功后直接采用网上支付系统支付招标文件费用，直接下载招标文件及其它资料（含澄清、修改文件等）。网上支付采用银联快捷支付方式，只要个人银行卡上有银联标志，并登记了手机号码，即可通知手机验证码实现快捷支付，无需开通网上银行。</p><p>	4.提醒事项：用户注册成功后如因相关注册信息发生变更者（注：与初始注册信息登记不一致均属），会员应及时申请变更（咨询电话：*****），如因会员自身原因未及时变更导致不利后果者，会员责任自负。</p><p>	5.电子投标文件必须使用投标文件制作工具制作生成并上传。投标文件制作工具下载地址：&nbsp;http://file.youzhicai.com/files/BidderTools.rar，</p><p>	投标文件制作工具使用说明书及视频教程下载地址:&nbsp;<a href=\"http://file.youzhicai.com/files/BidderHelp.rar\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://file.youzhicai.com/files/BidderHelp.rar</a>。</p><p>	投标工具下载地址：http://toolcdn.youzhicai.com/tools/BidderTools.zip</p><p>	帮助文档地址：https://file.youzhicai.com/files/BidderHelp.rar</p><p>	6.电子投标文件制作及上传咨询热线：400-0099-555&nbsp;&nbsp;*****。</p><p>	7.本招标项目采用全流程电子化招投标方式，投标人须办理CA数字证书，CA数字证书用于电子投标文件的签章及加密上传；</p><p>	8.CA数字证书办理详见《关于优质采平台数字证书办理的须知》</p><p>	（<a href=\"http://www.youzhicai.com/nd/a_8f80a7ec-911f-4c4d-a123-f8849880f045.html\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: black;\">http://www.youzhicai.com/nd/a_8f80a7ec-911f-4c4d-a123-f8849880f045.html</a>）；</p><p>	CA办理须知：https://www.youzhicai.com/ActivityTopic/AdviceDetail/8f80a7ec-911f-4c4d-a123-f8849880f045</p><p>	咨询热线：400-0099-555，*****。</p><p>	投标单位开标时须带CA，或者远程解密。解密时长40分钟(非优质采平台或招标人原因导致解密不成功，视为自动放弃本次投标资格)。</p><p>		备注：</p><p>	1&nbsp;、本项目收取平台基础服务费500元/年，招标文件费200元，其余平台相关增值服务费用（如：投标通3600元/年等）为自愿购买项目，非强制收取，请投标人按需选择。</p><p>	2、 若发现投标单位存在陪标围标串标、弄虚作假等违法违规情况，将按照相关法律法规处理，并列入铜化集团公司黑名单，五年内不得参与铜化集团公司所有招投标项目（投标文件所附各项证明材料必须真实有效，评委及招标机构视情况，调取原件查验）。</p><p>	3、报名投标单位如确定不能正常参加投标，请在开标前一天上传弃标函，以免在优质采平台留下不良记录，影响后续报名参标。</p>', NULL);
INSERT INTO `bid_notice` VALUES (58, 22, '年度采购计划第一次公告', '年度采购计划', 1000000, '2023-11-29 09:00:00', '2024-01-30 09:00:00', '2024-01-01 09:00:00', '2024-01-01 09:00:00', '[{\"id\":1,\"name\":\"02-Spring Cloud周测简单版.docx\",\"size\":41459,\"uid\":1704161504702,\"url\":\"http://localhost:9610/static/2024/01/02/02-Spring Cloud周测简单版_20240102101212A007.docx\",\"status\":\"success\"},{\"id\":2,\"name\":\"02-Gitee——项目管理工具的使用.docx\",\"size\":2530870,\"uid\":1704161504701,\"url\":\"http://localhost:9610/static/2024/01/02/02-Gitee——项目管理工具的使用_20240102101212A008.docx\",\"status\":\"success\"}]', 5, '<h1 class=\"ql-align-center\">济南黄河路桥建设集团有限公司冷水沟村等四村城中村改造项目（新东站安置二区）配套24班小学项目装饰装修工程采购公告</h1><p><span style=\"background-color: initial; color: black;\">打印</span></p><p><span style=\"background-color: initial; color: black;\">导出PDF</span></p><p>发布时间 :&nbsp;2023-12-30</p><p class=\"ql-align-justify\">	冷水沟村等四村城中村改造项目（新东站安置二区）配套24班小学项目装饰装修工程,采购人为济南黄河路桥建设集团有限公司。项目已具备采购条件，现对该项目公开采购。</p><p>一、项目概况与采购范围</p><p class=\"ql-align-justify\">	1、项目名称：冷水沟村等四村城中村改造项目（新东站安置二区）配套24班小学项目装饰装修工程</p><p class=\"ql-align-justify\">	2、项目编号：HHZB-2024(XB)-SG-004</p><p class=\"ql-align-justify\">	3、采购方式：询比采购</p><p class=\"ql-align-justify\">	4、供货地点：济南市</p><p class=\"ql-align-justify\">	5、项目概况：本次招标为冷水沟小学项目装饰装修工程，概算：20496957.03元。</p><p>	6、工期：60日历天，具体时间以甲方通知为准。</p><p class=\"ql-align-justify\">	7、采购范围：本次图纸范围内的装修及保修等。本次招标施工范围主要包含教学楼、门卫、换热站、看台装饰装修工程（含门窗、保温、防水工程）。</p><p class=\"ql-align-justify\">	8、标段划分：不分标段。</p><p>二、供应商资格要求</p><p>	1.在中国境内合法注册，具有独立法人资格的企业，具有合法、有效的营业执照，并在人员、设备、资金等方面具有相应的能力；</p><p>	2.供应商具有建筑装修装饰工程专业承包壹级资质，且具备建设行政主管部门核发的有效的安全生产许可证，并在人员、设备、资金等方面具有相应的施工能力。</p><p>	3.拟派项目经理应持有建筑工程专业一级建造师注册证书，具备有效的安全生产考核合格证书（B证），有类似项目施工经验，且不得担任其他在建工程项目的项目经理。</p><p class=\"ql-align-justify\">	4.近三年（2021年1月1日至今）单项合同额1400万元及以上的类似合同业绩（合同以签订时间为准）</p><p>	5.信誉要求：</p><p>	（1）供应商自2021年1月1日至今社会信誉自查承诺（格式详见附件，加盖公司公章）；</p><p>	（2）供应商须遵守国家有关的法律、法规和政策，有良好的商业信誉，未被列入失信被执行人、重大税收违法案件当事人名单、政府采购严重违法失信行为记录名单，提供“中国执行信息公开网”网站（http://zxgk.court.gov.cn/）截图；</p><p class=\"ql-align-justify\">	6.本次采购不接受联合体投标。</p><p>三、采购文件的获取</p><p class=\"ql-align-justify\">	1、获取时间：2023年12月31日至2024年1月4日，每日上午09：00至11：30，下午13：30至16：30（北京时间）。</p><p>	2、网上获取，登录优质采云招标平台（网址：www.youzhicai.com，首次登录需要注册，无需办理CA锁）找到对应项目上传相应资料资料，联系代理机构审核后下载采购文件（操作说明详见公告附件）</p><p class=\"ql-align-justify\">	①营业执照；②资质证书；③安全生产许可证；④项目经理注册建造师证书及有效的安全生产考核合格证书（B类）；⑤法人授权委托书及授权代理人身份证；⑥业绩合同；⑦社会信誉自查承诺及\"中国执行信息公开网\"网站（http://zxgk.court.gov.cn/）截图。</p><p>	备注：获取采购文件时的资料查验，不代表资格审查的最终通过或合格，供应商最终资格的确认以评审委员会组织的资格审查为准。</p><p>	文件售价：300元/份，文件售后不退。</p><p>	支付方式：公对公转账（备注：冷水沟小学项目装饰装修标书费）</p><p>	单位名称：济南海河建设项目管理有限公司</p><p>	开户行：齐鲁银行济南南郊支行</p><p>	行号：313451007029</p><p>	账号：000000705003200000238</p><p>	注：标书费用如需开具发票，将汇款凭证及填写好的发票申请表（公告附件下载），*****。</p><p>四、响应文件的提交</p><p>	1、提交响应文件的截止时间：详见采购文件。</p><p>	2、提交响应文件的截止地点：网上递交+线下递交纸质响应文件。登录济南海河建设项目管理有限公司电子招标采购平台（https://jnhhjsxm.youzhicai.com/）加密上传响应文件，并在规定的开标解密时间内完成解密。</p><p>五、发布公告的媒介</p><p>	本次公告同时在济南海河建设项目管理有限公司（http://www.jnhhjsxm.com/）、济南海河建设项目管理有限公司电子招标采购平台（https://jnhhjsxm.youzhicai.com/）网站上发布。</p><p>六、联系方式</p><p>	采购人：济南黄河路桥建设集团有限公司</p><p>	采购代理机构：济南海河建设项目管理有限公司</p><p>	地址：*****&nbsp;。</p><p>	联系人：*****&nbsp;<span style=\"color: rgb(229, 48, 30);\">登录查看更多</span></p><p>	联系方式：*****</p><p>	</p><p>	平台联系人:*****</p><p>	平台联系方式:*****</p>', '2024-01-02 10:13:27');
INSERT INTO `bid_notice` VALUES (63, 20, 'IT设备采购计划第二次公告', 'IT设备采购计划', 100, '2024-01-01 01:00:00', '2024-01-01 01:00:00', '2024-01-02 12:40:00', '2024-01-03 12:45:00', '[{\"id\":1,\"name\":\"02-Gitee——项目管理工具的使用.docx\",\"size\":2530870,\"uid\":1704198839142,\"url\":\"http://localhost:9610/static/2024/01/02/02-Gitee——项目管理工具的使用_20240102203401A023.docx\"},{\"id\":2,\"name\":\"3第三课  面向对象建模（UML常用图讲解）.docx\",\"size\":5436940,\"uid\":1704198839143,\"url\":\"http://localhost:9610/static/2024/01/02/3第三课  面向对象建模（UML常用图讲解）_20240102203401A024.docx\"}]', 5, '<h1 class=\"ql-align-center\">IT设备采购计划第二次公告</h1><p>发布时间 :&nbsp;2023-12-30</p><p class=\"ql-align-justify\">	冷水沟村等四村城中村改造项目（新东站安置二区）配套24班小学项目装饰装修工程,采购人为济南黄河路桥建设集团有限公司。项目已具备采购条件，现对该项目公开采购。</p><p>一、项目概况与采购范围</p><p class=\"ql-align-justify\">	1、项目名称：冷水沟村等四村城中村改造项目（新东站安置二区）配套24班小学项目装饰装修工程</p><p class=\"ql-align-justify\">	2、项目编号：HHZB-2024(XB)-SG-004</p><p class=\"ql-align-justify\">	3、采购方式：询比采购</p><p class=\"ql-align-justify\">	4、供货地点：济南市</p><p class=\"ql-align-justify\">	5、项目概况：本次招标为冷水沟小学项目装饰装修工程，概算：20496957.03元。</p><p>	6、工期：60日历天，具体时间以甲方通知为准。</p><p class=\"ql-align-justify\">	7、采购范围：本次图纸范围内的装修及保修等。本次招标施工范围主要包含教学楼、门卫、换热站、看台装饰装修工程（含门窗、保温、防水工程）。</p><p class=\"ql-align-justify\">	8、标段划分：不分标段。</p><p>二、供应商资格要求</p><p>	1.在中国境内合法注册，具有独立法人资格的企业，具有合法、有效的营业执照，并在人员、设备、资金等方面具有相应的能力；</p><p>	2.供应商具有建筑装修装饰工程专业承包壹级资质，且具备建设行政主管部门核发的有效的安全生产许可证，并在人员、设备、资金等方面具有相应的施工能力。</p><p>	3.拟派项目经理应持有建筑工程专业一级建造师注册证书，具备有效的安全生产考核合格证书（B证），有类似项目施工经验，且不得担任其他在建工程项目的项目经理。</p><p class=\"ql-align-justify\">	4.近三年（2021年1月1日至今）单项合同额1400万元及以上的类似合同业绩（合同以签订时间为准）</p><p>	5.信誉要求：</p><p>	（1）供应商自2021年1月1日至今社会信誉自查承诺（格式详见附件，加盖公司公章）；</p><p>	（2）供应商须遵守国家有关的法律、法规和政策，有良好的商业信誉，未被列入失信被执行人、重大税收违法案件当事人名单、政府采购严重违法失信行为记录名单，提供“中国执行信息公开网”网站（http://zxgk.court.gov.cn/）截图；</p><p class=\"ql-align-justify\">	6.本次采购不接受联合体投标。</p><p>三、采购文件的获取</p><p class=\"ql-align-justify\">	1、获取时间：2023年12月31日至2024年1月4日，每日上午09：00至11：30，下午13：30至16：30（北京时间）。</p><p>	2、网上获取，登录优质采云招标平台（网址：www.youzhicai.com，首次登录需要注册，无需办理CA锁）找到对应项目上传相应资料资料，联系代理机构审核后下载采购文件（操作说明详见公告附件）</p><p class=\"ql-align-justify\">	①营业执照；②资质证书；③安全生产许可证；④项目经理注册建造师证书及有效的安全生产考核合格证书（B类）；⑤法人授权委托书及授权代理人身份证；⑥业绩合同；⑦社会信誉自查承诺及\"中国执行信息公开网\"网站（http://zxgk.court.gov.cn/）截图。</p><p>	备注：获取采购文件时的资料查验，不代表资格审查的最终通过或合格，供应商最终资格的确认以评审委员会组织的资格审查为准。</p><p>	文件售价：300元/份，文件售后不退。</p><p>	支付方式：公对公转账（备注：冷水沟小学项目装饰装修标书费）</p><p>	单位名称：济南海河建设项目管理有限公司</p><p>	开户行：齐鲁银行济南南郊支行</p><p>	行号：313451007029</p><p>	账号：000000705003200000238</p><p>	注：标书费用如需开具发票，将汇款凭证及填写好的发票申请表（公告附件下载），*****。</p><p>四、响应文件的提交</p><p>	1、提交响应文件的截止时间：详见采购文件。</p><p>	2、提交响应文件的截止地点：网上递交+线下递交纸质响应文件。登录济南海河建设项目管理有限公司电子招标采购平台（https://jnhhjsxm.youzhicai.com/）加密上传响应文件，并在规定的开标解密时间内完成解密。</p><p>五、发布公告的媒介</p><p>	本次公告同时在济南海河建设项目管理有限公司（http://www.jnhhjsxm.com/）、济南海河建设项目管理有限公司电子招标采购平台（https://jnhhjsxm.youzhicai.com/）网站上发布。</p><p>六、联系方式</p><p>	采购人：济南黄河路桥建设集团有限公司</p><p>	采购代理机构：济南海河建设项目管理有限公司</p>', '2024-01-02 20:39:01');
INSERT INTO `bid_notice` VALUES (68, 28, '办公用品第一次公告', '办公用品', 11, '2024-01-03 09:00:00', '2024-01-03 09:00:00', '2024-01-03 14:45:00', '2024-01-03 11:10:00', '[{\"id\":1,\"name\":\"招标书 2.docx\",\"size\":1953695,\"uid\":1704261357531,\"url\":\"http://localhost:9610/static/2024/01/03/招标书 2_20240103135649A010.docx\"},{\"id\":2,\"name\":\"招标书1.docx\",\"size\":2530870,\"uid\":1704261357533,\"url\":\"http://localhost:9610/static/2024/01/03/招标书1_20240103135649A011.docx\"}]', 5, '<h1 class=\"ql-align-center\">让偷换概念大V回复的话不发达刚发打撒更大萨法你发的更好百年奋斗很发达</h1><p class=\"ql-align-center\">第三方广东省公司大V噶说法武器</p>', '2024-01-03 15:03:57');
INSERT INTO `bid_notice` VALUES (69, 29, '办公桌第一次公告', '办公桌', 1, '2024-01-02 09:00:00', '2024-01-02 09:00:00', '2024-01-03 17:05:00', '2024-01-03 17:10:00', '[{\"id\":1,\"name\":\"招标书 3.docx\",\"size\":1953695,\"uid\":1704272299172,\"url\":\"http://localhost:9610/static/2024/01/03/招标书 3_20240103170037A045.docx\"},{\"id\":2,\"name\":\"招标书 2.docx\",\"size\":1953695,\"uid\":1704272294124,\"url\":\"http://localhost:9610/static/2024/01/03/招标书 2_20240103170037A046.docx\"}]', 5, '<p>现代牧业（集团）有限公司成立于2005年9月，总部位于安徽省马鞍山市。2010年11月26日，现代牧业在香港联交所成功上市。秉持“天生要强，与自己较劲”的企业精神，现代牧业在做强核心业务的基础上，做大饲料板块、发展肉牛产业、培育育种业务、成立奶牛研究院、打通交易平台、整合地草资源、发展数智云养牛，加速发展步伐。目前在全国运营规模牧场50个以上，可控牛群数超44万头，日产鲜奶突破7000吨，市场占有率8%。  </p><p><br></p><p>内蒙古华晟工程项目管理有限公司受现代牧业（集团）有限公司委托,现就现代牧业2024年度蚌埠、合肥、马鞍山牧场日常维修工程项目进行竞争性谈判，欢迎符合资格条件的竞谈人参加。</p><p><br></p><p>一、项目编号：HSZB-GC-2023137</p><p><br></p><p>二、项目名称：现代牧业2024年度蚌埠、合肥、马鞍山牧场日常维修工程项目</p><p><br></p><p>三、项目概况：</p><p><br></p><p>本次竞谈为现代牧业合肥牧场、现代牧业蚌埠牧场、现代牧业马鞍山牧场，共三个牧场分三个标段进行竞谈。</p><p><br></p><p>标段一：现代牧业合肥牧场日常维修工程。</p><p><br></p><p>标段二：现代牧业蚌埠牧场日常维修工程。</p><p><br></p><p>标段三：现代牧业马鞍山牧场日常维修工程。</p><p><br></p><p>现针对以下内容计划选取优质竞谈人：</p><p><br></p><p>（一）、项目地点：</p><p><br></p><p>标段一：合肥市肥东县白龙镇长王村合肥牧场。</p><p><br></p><p>标段二：安徽省蚌埠市五河县朱顶镇现代牧业园区蚌埠牧场。</p><p><br></p><p>标段三：安徽省马鞍山市丹阳镇马鞍山牧场。</p><p><br></p><p>（二）、采购范围：土建日常维修工程，具体工程项目由甲方根据场区实际维修需求予以指定。</p><p><br></p>', '2024-01-03 17:10:51');
INSERT INTO `bid_notice` VALUES (70, 27, '家具用品第一次公告', '家具用品', 1, '2024-01-05 09:54:37', '2024-01-05 09:54:38', '2024-01-05 10:45:00', '2024-01-05 08:00:00', '[{\"id\":1,\"name\":\"02-仓库管理系统需求规格说明书-评审版.docx\",\"size\":1028277,\"uid\":1704419790735,\"url\":\"http://localhost:9610/static/2024/01/05/02-仓库管理系统需求规格说明书-评审版_20240105095647A003.docx\"},{\"id\":2,\"name\":\"02-Gitee——项目管理工具的使用.docx\",\"size\":2530870,\"uid\":1704419790734,\"url\":\"http://localhost:9610/static/2024/01/05/02-Gitee——项目管理工具的使用_20240105095647A004.docx\"}]', 5, '<h1 class=\"ql-align-center\"><span class=\"ql-size-huge\">啊十大高手的规范化</span></h1>', '2024-01-05 10:58:01');

-- ----------------------------
-- Table structure for bid_submission
-- ----------------------------
DROP TABLE IF EXISTS `bid_submission`;
CREATE TABLE `bid_submission`  (
  `td_id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `td_status` int(11) NULL DEFAULT 0 COMMENT '投标状态',
  `td_success_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '成功递交时间',
  `td_fail_time` datetime(0) NULL DEFAULT NULL COMMENT '文件撤回时间',
  `fj_files` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传标书',
  PRIMARY KEY (`td_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '投递标书表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_submission
-- ----------------------------
INSERT INTO `bid_submission` VALUES (24, 18, 2, 0, '2023-12-29 16:58:07', NULL, '[{\"id\":1,\"name\":\"interesting_shop.rar\",\"size\":27377309,\"uid\":1703840284518,\"url\":\"http://localhost:9610/static/2023/12/29/interesting_shop_20231229165806A014.rar\"}]');
INSERT INTO `bid_submission` VALUES (25, 18, 82, 0, '2023-12-29 17:01:55', NULL, '[{\"id\":1,\"name\":\"02-Gitee——项目管理工具的使用.docx\",\"size\":2530870,\"uid\":1703840513885,\"url\":\"http://localhost:9610/static/2023/12/29/02-Gitee——项目管理工具的使用_20231229170155A016.docx\"}]');
INSERT INTO `bid_submission` VALUES (26, 18, 83, 0, '2023-12-29 17:05:06', NULL, '[{\"id\":1,\"name\":\"02-Spring Cloud周测简单版.docx\",\"size\":41459,\"uid\":1703840704662,\"url\":\"http://localhost:9610/static/2023/12/29/02-Spring Cloud周测简单版_20231229170505A017.docx\"}]');
INSERT INTO `bid_submission` VALUES (27, 22, 2, 0, '2024-01-02 10:18:25', NULL, '[{\"id\":1,\"name\":\"interesting_shop.rar\",\"size\":27377309,\"uid\":1704161902802,\"url\":\"http://localhost:9610/static/2024/01/02/interesting_shop_20240102101824A009.rar\"}]');
INSERT INTO `bid_submission` VALUES (29, 21, 2, 0, '2024-01-02 20:58:18', NULL, '[{\"id\":1,\"name\":\"02-Gitee——项目管理工具的使用.docx\",\"size\":2530870,\"uid\":1704200297266,\"url\":\"http://localhost:9610/static/2024/01/02/02-Gitee——项目管理工具的使用_20240102205818A034.docx\"}]');
INSERT INTO `bid_submission` VALUES (31, 20, 2, 0, '2024-01-02 21:18:59', NULL, '[{\"id\":1,\"name\":\"01-软件开发Java设计规范——简洁版.docx\",\"size\":13805,\"uid\":1704201537765,\"url\":\"http://localhost:9610/static/2024/01/02/01-软件开发Java设计规范——简洁版_20240102211859A040.docx\"}]');
INSERT INTO `bid_submission` VALUES (32, 28, 2, 0, '2024-01-03 14:42:31', NULL, '[{\"id\":1,\"name\":\"投标书.zip\",\"size\":30842758,\"uid\":1704264146773,\"url\":\"http://localhost:9610/static/2024/01/03/投标书_20240103144231A018.zip\"}]');
INSERT INTO `bid_submission` VALUES (33, 28, 82, 0, '2024-01-03 14:43:46', NULL, '[{\"id\":1,\"name\":\"投标书.zip\",\"size\":30842758,\"uid\":1704264224426,\"url\":\"http://localhost:9610/static/2024/01/03/投标书_20240103144346A019.zip\"}]');
INSERT INTO `bid_submission` VALUES (34, 28, 83, 0, '2024-01-03 14:44:29', NULL, '[{\"id\":1,\"name\":\"投标书.zip\",\"size\":30842758,\"uid\":1704264267866,\"url\":\"http://localhost:9610/static/2024/01/03/投标书_20240103144429A020.zip\"}]');
INSERT INTO `bid_submission` VALUES (35, 29, 2, 0, '2024-01-03 17:04:49', NULL, '[{\"id\":1,\"name\":\"投标书.zip\",\"size\":30842758,\"uid\":1704272686836,\"url\":\"http://localhost:9610/static/2024/01/03/投标书_20240103170448A048.zip\"}]');
INSERT INTO `bid_submission` VALUES (36, 29, 82, 0, '2024-01-03 17:05:32', NULL, '[{\"id\":1,\"name\":\"投标书.zip\",\"size\":30842758,\"uid\":1704272730253,\"url\":\"http://localhost:9610/static/2024/01/03/投标书_20240103170532A049.zip\"}]');
INSERT INTO `bid_submission` VALUES (37, 27, 2, 0, '2024-01-05 10:39:35', NULL, '[{\"id\":1,\"name\":\"interesting_shop.rar\",\"size\":27377309,\"uid\":1704422371796,\"url\":\"http://localhost:9610/static/2024/01/05/interesting_shop_20240105103935A005.rar\"}]');

-- ----------------------------
-- Table structure for bid_tender
-- ----------------------------
DROP TABLE IF EXISTS `bid_tender`;
CREATE TABLE `bid_tender`  (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '招标项目ID',
  `xy_id` int(11) NULL DEFAULT NULL COMMENT '采购计划序号',
  `s_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '招标项目编号',
  `s_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '招标项目名称',
  `s_leader` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目负责人',
  `s_way` int(11) NULL DEFAULT NULL COMMENT '招标方式',
  `s_must` int(11) NULL DEFAULT NULL COMMENT '是否必招',
  `s_sway` int(11) NULL DEFAULT NULL COMMENT '资格审查方式',
  `s_type` int(11) NULL DEFAULT NULL COMMENT '业务类型',
  `s_budget` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目预算',
  `s_unit` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '招标单位',
  `s_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `s_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `s_address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `s_start_time` datetime(0) NULL DEFAULT NULL COMMENT '公示开始时间',
  `s_end_time` datetime(0) NULL DEFAULT NULL COMMENT '公示结束时间',
  `s_project_state` int(11) NULL DEFAULT NULL COMMENT '项目状态   1：招标公告   2：立项中\r\n',
  `s_deadline` datetime(0) NULL DEFAULT NULL COMMENT '报价截止时间',
  `eid` int(11) NULL DEFAULT NULL COMMENT '合同ID',
  `fj_files` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '招标项目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_tender
-- ----------------------------
INSERT INTO `bid_tender` VALUES (18, 57, 'ZB20231229161548015', '大海采购有限公司年度采购计划招标', '王海', 1, 2, 1, 1, 1000000.00, '湖南省大海采购有限公司', '王大海', '15575212935', 'wdh@qq.com', '湖南省永州市道县珊瑚海街道', '超级管理员', '2023-12-29 16:15:43', NULL, '2024-01-01 20:24:07', NULL, NULL, 7, NULL, NULL, '[{\"id\":1,\"name\":\"01-软件开发Java设计规范——简洁版.docx\",\"size\":13805,\"uid\":1703838712694,\"url\":\"http://localhost:9610/static/2023/12/29/01-软件开发Java设计规范——简洁版_20231229163156A004.docx\"},{\"id\":2,\"name\":\"02-数据库设计规范——简洁版.docx\",\"size\":39847,\"uid\":1703838712697,\"url\":\"http://localhost:9610/static/2023/12/29/02-数据库设计规范——简洁版_20231229163156A003.docx\"},{\"id\":3,\"name\":\"02-Spring Cloud周测简单版.docx\",\"size\":41459,\"uid\":1703838712696,\"url\":\"http://localhost:9610/static/2023/12/29/02-Spring Cloud周测简单版_20231229163156A005.docx\"}]');
INSERT INTO `bid_tender` VALUES (19, 60, 'ZB2023122917947016', '劳保用品采购计划', NULL, 1, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, '刘波', '2023-12-29 17:09:48', NULL, '2023-12-29 17:09:48', NULL, NULL, 1, NULL, NULL, NULL);
INSERT INTO `bid_tender` VALUES (20, 59, 'ZB20231229171151017', 'IT设备采购计划', '曾琳', 1, 2, 1, 2, 100.00, '长沙市第一采购公司', '曾林', '15565436796', 'zlin@qq.com', '长沙市天心区司马里', '王梓', '2023-12-29 17:11:51', NULL, '2024-01-02 20:39:02', NULL, NULL, 7, NULL, NULL, '[{\"id\":1,\"name\":\"02-仓库管理系统需求规格说明书-评审版.docx\",\"size\":1028277,\"uid\":1704196284047,\"url\":\"http://localhost:9610/static/2024/01/02/02-仓库管理系统需求规格说明书-评审版_20240102195149A018.docx\"},{\"id\":2,\"name\":\"02-Gitee——项目管理工具的使用.docx\",\"size\":2530870,\"uid\":1704196284046,\"url\":\"http://localhost:9610/static/2024/01/02/02-Gitee——项目管理工具的使用_20240102195149A019.docx\"}]');
INSERT INTO `bid_tender` VALUES (22, 57, 'ZB20241295537019', '年度采购计划', '雷健', 1, 1, 1, 1, 1000000.00, '实力信息科技有限公司', '雷健', '15575345356', 'dfh@qq.com', '湖南省永州市珊瑚海', '超级管理员', '2024-01-02 09:55:16', NULL, '2024-01-02 10:29:07', NULL, NULL, 7, NULL, 53, '[{\"id\":1,\"name\":\"02-仓库管理系统需求规格说明书-评审版.docx\",\"size\":1028277,\"uid\":1704161405306,\"url\":\"http://localhost:9610/static/2024/01/02/02-仓库管理系统需求规格说明书-评审版_20240102101009A005.docx\"},{\"id\":2,\"name\":\"02-Gitee——项目管理工具的使用.docx\",\"size\":2530870,\"uid\":1704161405304,\"url\":\"http://localhost:9610/static/2024/01/02/02-Gitee——项目管理工具的使用_20240102101009A006.docx\"}]');
INSERT INTO `bid_tender` VALUES (23, 61, 'ZB20241220828020', '办公家具采购计划', NULL, 1, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, '陈思', '2024-01-02 20:08:28', NULL, '2024-01-02 20:08:28', NULL, NULL, 1, NULL, NULL, NULL);
INSERT INTO `bid_tender` VALUES (24, 63, 'ZB202413115940021', '办公用纸采购计划', NULL, 1, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, '吴花花', '2024-01-03 11:59:37', NULL, '2024-01-03 11:59:37', NULL, NULL, 1, NULL, NULL, NULL);
INSERT INTO `bid_tender` VALUES (25, 59, 'ZB20241312110022', 'IT设备采购计划', NULL, 2, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, '王梓', '2024-01-03 12:01:07', NULL, '2024-01-03 12:01:07', NULL, NULL, 1, NULL, NULL, NULL);
INSERT INTO `bid_tender` VALUES (26, 67, 'ZB20241312119023', '办公装修采购计划', NULL, 1, 0, 0, 2, NULL, NULL, NULL, NULL, NULL, NULL, '覃敏', '2024-01-03 12:01:16', NULL, '2024-01-05 17:19:52', NULL, NULL, 2, NULL, NULL, NULL);
INSERT INTO `bid_tender` VALUES (27, 65, 'ZB20241312144024', '家具用品', '曾林', 1, 2, 2, 2, 1.00, '长沙实力科技有限公司', '曾鑫', '15543657834', 'zengn@qq.com', '长沙市开福区司马里', '张烁', '2024-01-03 12:01:41', NULL, '2024-01-05 10:58:01', NULL, NULL, 7, NULL, NULL, '[{\"id\":1,\"name\":\"bat.txt\",\"size\":192,\"uid\":1704419381047,\"url\":\"http://localhost:9610/static/2024/01/05/bat_20240105095023A001.txt\"},{\"id\":2,\"name\":\"CRM.txt\",\"size\":1564,\"uid\":1704419381048,\"url\":\"http://localhost:9610/static/2024/01/05/CRM_20240105095023A002.txt\"}]');
INSERT INTO `bid_tender` VALUES (28, 83, 'ZB202413133640025', '办公用品', '花满楼', 1, 2, 1, 1, 11.00, '长沙实力科技有限公司', '画满歌', '15576546785', 'hua,amge@qq.com', '长沙市开福区司马里', '雷经理', '2024-01-03 13:36:40', NULL, '2024-01-03 15:03:58', NULL, NULL, 7, NULL, NULL, '[{\"id\":1,\"name\":\"技术标准与要求.docx\",\"size\":1953695,\"uid\":1704260941148,\"url\":\"http://localhost:9610/static/2024/01/03/技术标准与要求_20240103134924A009.docx\"}]');
INSERT INTO `bid_tender` VALUES (29, 87, 'ZB202413164938026', '办公桌', '曾琳', 1, 2, 1, 1, 1000.00, '长沙实力信息科技有限公司', '曾琳', '19967107662', '2387300579@qq.com', '长沙市开福区司马里', '雷经理', '2024-01-03 16:49:38', NULL, '2024-01-03 17:10:51', NULL, NULL, 7, NULL, NULL, '[{\"id\":1,\"name\":\"技术标准与要求.docx\",\"size\":1953695,\"uid\":1704272102819,\"url\":\"http://localhost:9610/static/2024/01/03/技术标准与要求_20240103165508A044.docx\"}]');

-- ----------------------------
-- Table structure for bid_winning_results
-- ----------------------------
DROP TABLE IF EXISTS `bid_winning_results`;
CREATE TABLE `bid_winning_results`  (
  `gs_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公示ID',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `h_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `gs_state` int(11) NULL DEFAULT 0 COMMENT '公示状态',
  `gs_res_date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '公示发布时间',
  `result_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `result_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`gs_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '中标结果公示表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bid_winning_results
-- ----------------------------
INSERT INTO `bid_winning_results` VALUES (75, 18, 82, '花火科技有限公司', 0, '2024-01-01 20:24:05', '合格', '大海采购有限公司年度采购计划招标公告');
INSERT INTO `bid_winning_results` VALUES (76, 22, 2, '腾腾科技（深圳）有限公司', 0, '2024-01-02 10:29:05', '<p class=\"ql-align-justify\">	冷水沟村等四村城中村改造项目（新东站安置二区）配套24班小学项目装饰装修工程,采购人为济南黄河路桥建设集团有限公司。项目已具备采购条件，现对该项目公开采购。</p><p>一、项目概况与采购范围</p><p class=\"ql-align-justify\">	1、项目名称：冷水沟村等四村城中村改造项目（新东站安置二区）配套24班小学项目装饰装修工程</p><p class=\"ql-align-justify\">	2、项目编号：HHZB-2024(XB)-SG-004</p><p class=\"ql-align-justify\">	3、采购方式：询比采购</p><p class=\"ql-align-justify\">	4、供货地点：济南市</p><p class=\"ql-align-justify\">	5、项目概况：本次招标为冷水沟小学项目装饰装修工程，概算：20496957.03元。</p><p>	6、工期：60日历天，具体时间以甲方通知为准。</p><p class=\"ql-align-justify\">	7、采购范围：本次图纸范围内的装修及保修等。本次招标施工范围主要包含教学楼、门卫、换热站、看台装饰装修工程（含门窗、保温、防水工程）。</p><p class=\"ql-align-justify\">	8、标段划分：不分标段。</p><p>二、供应商资格要求</p><p>	1.在中国境内合法注册，具有独立法人资格的企业，具有合法、有效的营业执照，并在人员、设备、资金等方面具有相应的能力；</p><p>	2.供应商具有建筑装修装饰工程专业承包壹级资质，且具备建设行政主管部门核发的有效的安全生产许可证，并在人员、设备、资金等方面具有相应的施工能力。</p><p>	3.拟派项目经理应持有建筑工程专业一级建造师注册证书，具备有效的安全生产考核合格证书（B证），有类似项目施工经验，且不得担任其他在建工程项目的项目经理。</p><p class=\"ql-align-justify\">	4.近三年（2021年1月1日至今）单项合同额1400万元及以上的类似合同业绩（合同以签订时间为准）</p><p>	5.信誉要求：</p><p>	（1）供应商自2021年1月1日至今社会信誉自查承诺（格式详见附件，加盖公司公章）；</p><p>	（2）供应商须遵守国家有关的法律、法规和政策，有良好的商业信誉，未被列入失信被执行人、重大税收违法案件当事人名单、政府采购严重违法失信行为记录名单，提供“中国执行信息公开网”网站（http://zxgk.court.gov.cn/）截图；</p><p class=\"ql-align-justify\">	6.本次采购不接受联合体投标。</p><p>三、采购文件的获取</p><p class=\"ql-align-justify\">	1、获取时间：2023年12月31日至2024年1月4日，每日上午09：00至11：30，下午13：30至16：30（北京时间）。</p><p>	2、网上获取，登录优质采云招标平台（网址：www.youzhicai.com，首次登录需要注册，无需办理CA锁）找到对应项目上传相应资料资料，联系代理机构审核后下载采购文件（操作说明详见公告附件）</p><p class=\"ql-align-justify\">	①营业执照；②资质证书；③安全生产许可证；④项目经理注册建造师证书及有效的安全生产考核合格证书（B类）；⑤法人授权委托书及授权代理人身份证；⑥业绩合同；⑦社会信誉自查承诺及\"中国执行信息公开网\"网站（http://zxgk.court.gov.cn/）截图。</p><p>	备注：获取采购文件时的资料查验，不代表资格审查的最终通过或合格，供应商最终资格的确认以评审委员会组织的资格审查为准。</p><p>	文件售价：300元/份，文件售后不退。</p><p>	支付方式：公对公转账（备注：冷水沟小学项目装饰装修标书费）</p><p>	单位名称：济南海河建设项目管理有限公司</p><p>	开户行：齐鲁银行济南南郊支行</p><p>	行号：313451007029</p><p>	账号：000000705003200000238</p><p>	注：标书费用如需开具发票，将汇款凭证及填写好的发票申请表（公告附件下载），*****。</p><p>四、响应文件的提交</p><p>	1、提交响应文件的截止时间：详见采购文件。</p><p>	2、提交响应文件的截止地点：网上递交+线下递交纸质响应文件。登录济南海河建设项目管理有限公司电子招标采购平台（https://jnhhjsxm.youzhicai.com/）加密上传响应文件，并在规定的开标解密时间内完成解密。</p><p>五、发布公告的媒介</p><p>	本次公告同时在济南海河建设项目管理有限公司（http://www.jnhhjsxm.com/）、济南海河建设项目管理有限公司电子招标采购平台（https://jnhhjsxm.youzhicai.com/）网站上发布。</p><p>六、联系方式</p><p>	采购人：济南黄河路桥建设集团有限公司</p><p>	采购代理机构：济南海河建设项目管理有限公司</p><p>	地址：*****&nbsp;。</p><p>	联系人：*****&nbsp;<span style=\"color: rgb(229, 48, 30);\">登录查看更多</span></p><p>	联系方式：*****</p><p>	</p><p>	平台联系人:*****</p><p>	平台联系方式:*****</p>', '中标通知');
INSERT INTO `bid_winning_results` VALUES (77, 20, 2, '腾腾科技（深圳）有限公司', 0, '2024-01-02 20:39:00', '<h1 class=\"ql-align-center\">IT设备采购计划第二次公告</h1><p>23456烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦</p><p>34567890</p><p>犯得上广泛的换个房间好看</p><p>43567</p>', '中标通知123');
INSERT INTO `bid_winning_results` VALUES (78, 28, 2, '腾腾科技（深圳）有限公司', 0, '2024-01-03 15:03:56', '<h1 class=\"ql-align-center\">恭喜恭喜恭喜高跟鞋</h1>', '中标通知');
INSERT INTO `bid_winning_results` VALUES (79, 29, 2, '腾腾科技（深圳）有限公司', 0, '2024-01-03 17:10:50', '<p>现代牧业（集团）有限公司成立于2005年9月，总部位于安徽省马鞍山市。2010年11月26日，现代牧业在香港联交所成功上市。秉持“天生要强，与自己较劲”的企业精神，现代牧业在做强核心业务的基础上，做大饲料板块、发展肉牛产业、培育育种业务、成立奶牛研究院、打通交易平台、整合地草资源、发展数智云养牛，加速发展步伐。目前在全国运营规模牧场50个以上，可控牛群数超44万头，日产鲜奶突破7000吨，市场占有率8%。  </p><p><br></p><p>内蒙古华晟工程项目管理有限公司受现代牧业（集团）有限公司委托,现就现代牧业2024年度蚌埠、合肥、马鞍山牧场日常维修工程项目进行竞争性谈判，欢迎符合资格条件的竞谈人参加。</p><p><br></p><p>一、项目编号：HSZB-GC-2023137</p><p><br></p><p>二、项目名称：现代牧业2024年度蚌埠、合肥、马鞍山牧场日常维修工程项目</p><p><br></p><p>三、项目概况：</p><p><br></p><p>本次竞谈为现代牧业合肥牧场、现代牧业蚌埠牧场、现代牧业马鞍山牧场，共三个牧场分三个标段进行竞谈。</p><p><br></p><p>标段一：现代牧业合肥牧场日常维修工程。</p><p><br></p><p>标段二：现代牧业蚌埠牧场日常维修工程。</p><p><br></p><p>标段三：现代牧业马鞍山牧场日常维修工程。</p><p><br></p><p>现针对以下内容计划选取优质竞谈人：</p><p><br></p><p>（一）、项目地点：</p><p><br></p><p>标段一：合肥市肥东县白龙镇长王村合肥牧场。</p><p><br></p><p>标段二：安徽省蚌埠市五河县朱顶镇现代牧业园区蚌埠牧场。</p><p><br></p><p>标段三：安徽省马鞍山市丹阳镇马鞍山牧场。</p><p><br></p><p>（二）、采购范围：土建日常维修工程，具体工程项目由甲方根据场区实际维修需求予以指定。</p><p><br></p>', '中标通知');
INSERT INTO `bid_winning_results` VALUES (80, 27, 2, '腾腾科技（深圳）有限公司', 0, '2024-01-05 10:58:00', '<h1 class=\"ql-align-center\">的方法也会让她的返还甲方发给客服给高呼口号</h1>', '中标通知');

-- ----------------------------
-- Table structure for bs_access
-- ----------------------------
DROP TABLE IF EXISTS `bs_access`;
CREATE TABLE `bs_access`  (
  `zr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '准入ID',
  `zr_bnumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务编号',
  `zr_promoter` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发起人',
  `zr_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`zr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商准入' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_access
-- ----------------------------
INSERT INTO `bs_access` VALUES (12, 'ZR2024120016', '李浩', '2024-01-02 09:13:03');
INSERT INTO `bs_access` VALUES (13, 'ZR2024120017', '马芸', '2024-01-02 09:19:08');
INSERT INTO `bs_access` VALUES (15, 'ZR2024120019', '李珊', '2024-01-02 09:28:53');
INSERT INTO `bs_access` VALUES (16, 'ZR2024120020', '陈达', '2024-01-02 09:32:14');
INSERT INTO `bs_access` VALUES (17, 'ZR2024120021', '刘花', '2024-01-02 09:38:01');
INSERT INTO `bs_access` VALUES (18, 'ZR2024120022', '李建华', '2024-01-02 09:42:17');
INSERT INTO `bs_access` VALUES (19, 'ZR2024120023', '王涵', '2024-01-02 09:46:28');
INSERT INTO `bs_access` VALUES (20, 'ZR2024120024', '吴桐', '2024-01-02 09:56:44');
INSERT INTO `bs_access` VALUES (21, 'ZR2024120025', '吴安', '2024-01-02 09:59:44');
INSERT INTO `bs_access` VALUES (22, 'ZR2024120026', '李龙威', '2024-01-02 10:02:50');

-- ----------------------------
-- Table structure for bs_accessories
-- ----------------------------
DROP TABLE IF EXISTS `bs_accessories`;
CREATE TABLE `bs_accessories`  (
  `fj_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `fj_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `fj_annex` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  PRIMARY KEY (`fj_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '相关附件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_accessories
-- ----------------------------
INSERT INTO `bs_accessories` VALUES (1, '附件1', '[{\"id\":1,\"name\":\"招标书1.docx\",\"size\":2530870,\"uid\":1704253194005,\"url\":\"http://localhost:9610/static/2024/01/03/招标书1_20240103113957A003.docx\"},{\"id\":2,\"name\":\"招标书 2.docx\",\"size\":1953695,\"uid\":1704253179109,\"url\":\"http://localhost:9610/static/2024/01/03/招标书 2_20240103113942A001.docx\",\"status\":\"success\"}]', 2);

-- ----------------------------
-- Table structure for bs_achievement
-- ----------------------------
DROP TABLE IF EXISTS `bs_achievement`;
CREATE TABLE `bs_achievement`  (
  `yj_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业绩ID',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `yj_unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购单位',
  `yj_partner` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合作方',
  `yj_date` date NULL DEFAULT NULL COMMENT '签订日期',
  `yj_rmb` decimal(10, 0) NULL DEFAULT NULL COMMENT '合同金额',
  `yj_bbr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买方业务代表',
  `yj_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买方业务代表联系电话',
  `yj_scan_contract` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同扫描件',
  `yj_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '合同内容',
  PRIMARY KEY (`yj_id`) USING BTREE,
  INDEX `FK_Relationship_43`(`hid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业绩表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_achievement
-- ----------------------------
INSERT INTO `bs_achievement` VALUES (1, 2, '采购部', '恒力集团有限公司', '2003-10-10', 300, '黄花', '18712345678', NULL, NULL);

-- ----------------------------
-- Table structure for bs_contract
-- ----------------------------
DROP TABLE IF EXISTS `bs_contract`;
CREATE TABLE `bs_contract`  (
  `eid` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同ID',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `gid` int(11) NULL DEFAULT NULL COMMENT '非招标项目ID',
  `e_hcode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `e_hname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同名称',
  `e_status` int(11) NULL DEFAULT NULL COMMENT '合同状态',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `e_delivery_time` date NULL DEFAULT NULL COMMENT '交付日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `o_hstatus` int(11) NULL DEFAULT NULL COMMENT '合同管理状态',
  `e_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同类型',
  `e_con` int(11) NULL DEFAULT NULL COMMENT '密级',
  `e_startDate` date NULL DEFAULT NULL COMMENT '开始时间',
  `e_endDate` date NULL DEFAULT NULL COMMENT '结束时间',
  `e_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '合同金额',
  `e_description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同情况说明',
  `e_opinion` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '意见',
  `e_image` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同影像',
  `e_documents` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同文件',
  `e_cancel` int(11) NULL DEFAULT 0 COMMENT '是否作废',
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '合同表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_contract
-- ----------------------------
INSERT INTO `bs_contract` VALUES (53, 2, 22, NULL, 'HT2024130043', '年度采购合同', 1, '欧', '2024-01-03 10:22:01', '2024-01-17', NULL, '2024-01-03 11:31:51', 3, '一般采购合同', 1, '2024-01-07', '2025-01-07', 154810.00, '年度采购', '通过', 'http://localhost:9610/static/2024/01/03/合同影像_20240103102159A074.docx', 'http://localhost:9610/static/2024/01/03/合同文件_20240103102200A076.docx', 0);
INSERT INTO `bs_contract` VALUES (54, 82, 18, NULL, 'HT2024130044', '大海采购有限公司年度采购计划合同', 2, '欧', '2024-01-03 15:08:03', '2024-01-19', NULL, '2024-01-03 15:08:02', 2, '一般采购合同', 1, '2024-01-09', '2025-01-19', 154810.00, '大海采购有限公司年度采购计划', NULL, 'http://localhost:9610/static/2024/01/03/合同影像 1_20240103150700A033.docx', 'http://localhost:9610/static/2024/01/03/合同文件_20240103150701A035.docx', 0);
INSERT INTO `bs_contract` VALUES (55, 82, 18, NULL, 'HT2024130045', '大海采购有限公司年度采购计划合同', 1, '欧', '2024-01-03 15:08:09', '2024-01-17', NULL, '2024-01-03 15:09:22', 3, '一般采购合同', 1, '2024-01-07', '2025-01-17', 154810.00, '大海采购有限公司年度采购计划', '通过', 'http://localhost:9610/static/2024/01/03/合同影像_20240103150840A036.docx', 'http://localhost:9610/static/2024/01/03/合同文件_20240103150701A035.docx', 1);
INSERT INTO `bs_contract` VALUES (56, 2, NULL, 61, 'HT2024130046', '软件许可采购合同', 1, '欧', '2024-01-03 15:20:26', '2024-01-17', NULL, '2024-01-03 15:21:05', 3, '一般采购合同', 1, '2024-01-07', '2025-01-07', 40680.00, '软件许可采购', '通过', 'http://localhost:9610/static/2024/01/03/合同文件_20240103152024A038.docx', 'http://localhost:9610/static/2024/01/03/合同文件_20240103152025A040.docx', 0);
INSERT INTO `bs_contract` VALUES (57, 2, 29, NULL, 'HT2024130047', '办公桌采购合同', 1, '欧', '2024-01-03 17:13:51', '2024-01-17', NULL, '2024-01-03 17:16:03', 3, '一般采购合同', 1, '2024-01-07', '2025-01-07', 40680.00, '办公桌采购', '通过', 'http://localhost:9610/static/2024/01/03/合同影像 1_20240103171349A058.docx', 'http://localhost:9610/static/2024/01/03/合同文件_20240103171349A060.docx', 1);
INSERT INTO `bs_contract` VALUES (58, 83, NULL, 63, 'HT2024130048', '办公用具采购合同', 2, '欧', '2024-01-03 17:25:02', '2024-01-19', NULL, '2024-01-03 17:25:01', 2, '一般采购合同', 1, '2024-01-09', '2025-01-19', 3390.00, '办公用具采购', NULL, 'http://localhost:9610/static/2024/01/03/合同影像 1_20240103172500A062.docx', 'http://localhost:9610/static/2024/01/03/合同文件1_20240103172500A064.docx', 0);

-- ----------------------------
-- Table structure for bs_educate
-- ----------------------------
DROP TABLE IF EXISTS `bs_educate`;
CREATE TABLE `bs_educate`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '教育ID',
  `jid` int(11) NULL DEFAULT NULL COMMENT '专家ID',
  `r_school` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `specialized` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
  `r_degree` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
  `r_enrollment_time` date NULL DEFAULT NULL COMMENT '入学时间',
  `r_graduation_time` date NULL DEFAULT NULL COMMENT '毕业时间',
  PRIMARY KEY (`rid`) USING BTREE,
  INDEX `FK_Relationship_28`(`jid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教育表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_educate
-- ----------------------------
INSERT INTO `bs_educate` VALUES (1, 2, '工业大学', '语言学', '本科', '2014-09-07', '2018-09-07');
INSERT INTO `bs_educate` VALUES (2, 2, '清花大学', '语言学', '研究生', '2019-09-07', '2022-09-07');
INSERT INTO `bs_educate` VALUES (3, 1, '农业大学', '养殖', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (4, 3, '塔里木大学', '机械自动化', '本科', '2015-09-07', '2019-09-07');
INSERT INTO `bs_educate` VALUES (5, 3, '上海交大', '机械自动化', '研究生', '2019-09-07', '2023-09-07');
INSERT INTO `bs_educate` VALUES (6, 4, '复旦大学', '商务英语', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (7, 5, '武汉大学', '工程管理', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (8, 6, '工业大学', '工程管理', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (9, 7, '工业大学', '工程管理', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (10, 8, '工业大学', '工程管理', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (11, 9, '农业大学', '工程管理', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (12, 10, '工业大学', '工程管理', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (13, 11, '西安交通大学', '工程管理', '本科', '2013-09-07', '2017-09-07');
INSERT INTO `bs_educate` VALUES (14, 12, '浙江大学', '工程管理', '本科', '2013-09-07', '2017-09-07');

-- ----------------------------
-- Table structure for bs_employee
-- ----------------------------
DROP TABLE IF EXISTS `bs_employee`;
CREATE TABLE `bs_employee`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  `p_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `p_password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `p_sex` int(11) NULL DEFAULT NULL COMMENT '员工性别',
  `p_born` date NULL DEFAULT NULL COMMENT '员工出生日期',
  `p_nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工昵称',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bs_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `bs_enterprise`;
CREATE TABLE `bs_enterprise`  (
  `zz_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资质ID',
  `zz_cert_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证书名称',
  `zz_cert_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证书编号',
  `zz_unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发证单位',
  `zz_date` date NULL DEFAULT NULL COMMENT '发证日期',
  `zz_expiration_date` date NULL DEFAULT NULL COMMENT '有效期至',
  `zz_scan` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扫描件',
  `zz_cert_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '证书内容',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  PRIMARY KEY (`zz_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '企业资质表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_enterprise
-- ----------------------------
INSERT INTO `bs_enterprise` VALUES (1, 'AAA级信用企业等级证书', '88921239', '地方省政府', '2023-10-01', '2024-10-10', NULL, NULL, 2);
INSERT INTO `bs_enterprise` VALUES (2, 'ISO体系认证', '77792341', '地方省政府', '2022-10-10', '2024-10-01', '', NULL, 2);

-- ----------------------------
-- Table structure for bs_experience
-- ----------------------------
DROP TABLE IF EXISTS `bs_experience`;
CREATE TABLE `bs_experience`  (
  `qid` int(11) NOT NULL AUTO_INCREMENT COMMENT '任职经历ID',
  `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `time` date NULL DEFAULT NULL COMMENT '入职时间',
  `q_office` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务',
  `specialized` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业',
  `jid` int(11) NULL DEFAULT NULL COMMENT '专家id',
  PRIMARY KEY (`qid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任职经历表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_experience
-- ----------------------------
INSERT INTO `bs_experience` VALUES (1, '教育单位', '2023-12-01', '养殖', '养殖管理', 1);
INSERT INTO `bs_experience` VALUES (3, '教育单位', '2023-12-01', '语言教授', '语言文化', 2);
INSERT INTO `bs_experience` VALUES (4, '教育单位', '2023-11-30', '技术教授', '技术指导', 3);
INSERT INTO `bs_experience` VALUES (5, '政府单位', '2022-11-20', '质检', '质量验收', 4);
INSERT INTO `bs_experience` VALUES (6, '教育单位', '2022-11-20', '工程教授', '工程管理', 5);
INSERT INTO `bs_experience` VALUES (7, '教育单位', '2022-11-20', '工程教授', '工程管理', 6);
INSERT INTO `bs_experience` VALUES (8, '教育单位', '2022-11-20', '工程教授', '工程管理', 7);
INSERT INTO `bs_experience` VALUES (9, '教育单位', '2022-11-20', '工程教授', '工程管理', 8);
INSERT INTO `bs_experience` VALUES (10, '教育单位', '2022-11-20', '工程教授', '工程管理', 9);
INSERT INTO `bs_experience` VALUES (11, '教育单位', '2022-11-20', '工程教授', '工程管理', 10);
INSERT INTO `bs_experience` VALUES (12, '教育单位', '2022-11-20', '工程教授', '工程管理', 11);
INSERT INTO `bs_experience` VALUES (13, '教育单位', '2022-11-20', '工程教授', '工程管理', 12);

-- ----------------------------
-- Table structure for bs_expert
-- ----------------------------
DROP TABLE IF EXISTS `bs_expert`;
CREATE TABLE `bs_expert`  (
  `jid` int(11) NOT NULL AUTO_INCREMENT COMMENT '专家ID',
  `j_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `j_sex` int(11) NULL DEFAULT NULL COMMENT '性别',
  `j_identity` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `j_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `j_state` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '健康状况',
  `j_unit` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作单位',
  `j_retire` int(11) NULL DEFAULT NULL COMMENT '是否退休',
  `j_address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通讯地址',
  `j_card` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行卡号',
  `j_identity_photo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证照',
  `j_documents_photo` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件照',
  `j_bank` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `j_sh_state` int(11) NULL DEFAULT NULL COMMENT '专家审核状态',
  `j_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '意见',
  PRIMARY KEY (`jid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '专家表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_expert
-- ----------------------------
INSERT INTO `bs_expert` VALUES (1, '张十三', 0, '995731289128546', '14711421235', 'shisan@163.com', '健康', '教育单位', 0, '湖南长沙', '25353234125212', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '长沙银行', 1, 'pass');
INSERT INTO `bs_expert` VALUES (2, '李碧花', 1, '343479358238532', '18712345775', 'li@163.com', '健康', '教育单位', 0, '湖北武汉', '32432579832354', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '武汉银行', 1, '无');
INSERT INTO `bs_expert` VALUES (3, '杨文胜', 0, '235325635388432', '19912345082', 'wensheng@163.com', '健康', '教育单位', 0, '湖南武冈', '12345327537852', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '长沙银行', 1, '通过');
INSERT INTO `bs_expert` VALUES (4, '郑海磊', 0, '235325635213785', '18212347865', 'hailei@163.com', '健康', '政府单位', 0, '湖南株洲', '12343243253851', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '工农银行', 1, 'pass');
INSERT INTO `bs_expert` VALUES (5, '曾令坤', 0, '235322342213731', '15512367890', 'lingkun@163.com', '健康', '教育单位', 0, '广东深圳', '34324938223957', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '广东银行', 1, '通过');
INSERT INTO `bs_expert` VALUES (6, '李奕坤', 0, '123912741238583', '17681834921', 'yikun@163.com', '健康', '教育单位', 0, '江西萍乡', '12938124721123', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '江西银行', 1, 'pass');
INSERT INTO `bs_expert` VALUES (7, '吴碧勇', 0, '123129429132138', '18812349432', 'biyong@163.com', '健康', '教育单位', 0, '贵州铜仁', '12903128490721', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '贵州银行', 1, 'good');
INSERT INTO `bs_expert` VALUES (8, '肖睿', 0, '129312741290472', '19967543872', 'rui@163.com', '健康', '教育单位', 0, '山东济南', '12301294124123', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '山东银行', 1, 'pass');
INSERT INTO `bs_expert` VALUES (9, '邱厦凯', 0, '123124724232192', '18812949831', 'kai@163.com', '健康', '教育单位', 0, '江西南昌', '12039129481292', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '江西银行', 0, NULL);
INSERT INTO `bs_expert` VALUES (10, '龚奕琦', 0, '123912412849122', '19812329482', 'yiqi@163.com', '健康', '教育单位', 0, '广东广州', '12931247128412', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '广东银行', 2, 'e');
INSERT INTO `bs_expert` VALUES (11, '杜青霞', 1, '123124812749123', '18712134210', 'xia@163.com', '健康', '教育单位', 0, '黑龙江哈尔滨', '23124124791283', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '黑龙江银行', 1, 'pass!!');
INSERT INTO `bs_expert` VALUES (12, '刘朝霞', 1, '238124982714912', '18712939281', 'zhao@163.com', '健康', '教育单位', 0, '湖南益阳', '21391241249122', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '[{\"size\":347232,\"url\":\"http://localhost:9610/static/2023/12/24/daxiong_20231224165825A001.jpg\",\"name\":\"daxiong_20231224165825A001.jpg\"}]', '湖南银行', 1, 'pass');

-- ----------------------------
-- Table structure for bs_financial_status
-- ----------------------------
DROP TABLE IF EXISTS `bs_financial_status`;
CREATE TABLE `bs_financial_status`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '财务状态ID',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `c_annual` int(11) NULL DEFAULT NULL COMMENT '年度',
  `c_net_profit` int(11) NULL DEFAULT NULL COMMENT '净利润（万元）',
  `c_lev` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资产负债率（%）',
  `c_scan_far` int(11) NULL DEFAULT NULL COMMENT '财务审计报告扫描件',
  `c_scan_aar` int(11) NULL DEFAULT NULL COMMENT '附件审计报告',
  `c_scan_al` int(11) NULL DEFAULT NULL COMMENT '资产负债扫描件',
  `c_scan_is` int(11) NULL DEFAULT NULL COMMENT '利润表扫描件',
  `c_scan_cfs` int(11) NULL DEFAULT NULL COMMENT '现金流量表扫描件',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '财务状态表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_financial_status
-- ----------------------------
INSERT INTO `bs_financial_status` VALUES (1, 2, 1, 100, '20', 1, 1, 1, 1, 1);

-- ----------------------------
-- Table structure for bs_frame_management
-- ----------------------------
DROP TABLE IF EXISTS `bs_frame_management`;
CREATE TABLE `bs_frame_management`  (
  `oid` int(11) NOT NULL AUTO_INCREMENT COMMENT '框架协议ID',
  `jh_id` int(11) NULL DEFAULT NULL COMMENT '框架计划ID',
  `o_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '框架协议编号',
  `o_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '框架协议名称',
  `o_subject` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签署主体',
  `hid` int(11) NULL DEFAULT NULL COMMENT '相对方ID',
  `h_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相对方名称',
  `o_startDate` date NULL DEFAULT NULL COMMENT '签署时间',
  `o_endDate` date NULL DEFAULT NULL COMMENT '失效时间',
  `o_file` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '协议文件',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `o_hstatus` int(11) NULL DEFAULT NULL COMMENT '协议状态',
  `o_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '协议类型',
  `o_describe` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `o_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  `o_totalPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `o_cancel` int(11) NULL DEFAULT 0 COMMENT '是否作废',
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '框架协议管理表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_frame_management
-- ----------------------------
INSERT INTO `bs_frame_management` VALUES (44, 59, 'FM2024130043', '办公用品框架计划协议', '鸿鹄科技有限公司', 96, '恒力集团有限公司', '2024-01-08', '2026-01-07', 'http://localhost:9610/static/2024/01/03/框架协议_20240103102421A077.docx', '欧', '2024-01-03 10:24:22', 3, '采购框架协议', '办公用品框架计划', '审核通过', 40680.00, 0);
INSERT INTO `bs_frame_management` VALUES (45, 53, 'FM2024130044', '采购办公用品协议', '鸿鹄科技有限公司', 82, '得力集团', '2024-01-09', '2025-01-19', 'http://localhost:9610/static/2024/01/03/附件_20240103153449A042.docx', '欧', '2024-01-03 15:34:50', 1, '采购框架协议', '采购办公用品', NULL, 1356000.00, 0);
INSERT INTO `bs_frame_management` VALUES (46, 54, 'FM2024130045', '办公用品协议', '鸿鹄科技有限公司', 2, '腾腾科技（深圳）有限公司', '2024-01-09', '2025-01-09', 'http://localhost:9610/static/2024/01/03/附件_20240103172954A066.docx', '欧', '2024-01-03 17:29:56', 1, '采购框架协议', '办公用品', NULL, 30510.00, 0);

-- ----------------------------
-- Table structure for bs_inventory
-- ----------------------------
DROP TABLE IF EXISTS `bs_inventory`;
CREATE TABLE `bs_inventory`  (
  `in_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标的ID',
  `tid` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编码',
  `eid` int(11) NULL DEFAULT NULL COMMENT '合同ID',
  `gid` int(11) NULL DEFAULT NULL COMMENT '非招标ID',
  `oid` int(11) NULL DEFAULT NULL COMMENT '框架协议ID',
  `in_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `in_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格型号',
  `in_unit` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  `in_vat` decimal(10, 2) NULL DEFAULT NULL COMMENT '含税单价',
  `in_count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `in_subtotal` decimal(10, 2) NULL DEFAULT NULL COMMENT '小计',
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_inventory
-- ----------------------------
INSERT INTO `bs_inventory` VALUES (184, '1002', 53, NULL, NULL, '空调', '型号AC-200', '台', 2825.00, 50, 141250.00);
INSERT INTO `bs_inventory` VALUES (185, '1008', 53, NULL, NULL, '打印机', '型号Printer-800', '台', 678.00, 20, 13560.00);
INSERT INTO `bs_inventory` VALUES (188, '1009', NULL, NULL, 44, '办公桌', '型号Desk-900', '张', 1017.00, 30, 30510.00);
INSERT INTO `bs_inventory` VALUES (189, '1010', NULL, NULL, 44, '办公椅', '型号Chair-1000', '把', 339.00, 30, 10170.00);
INSERT INTO `bs_inventory` VALUES (190, '1002', 54, NULL, NULL, '空调', '型号AC-200', '台', 2825.00, 50, 141250.00);
INSERT INTO `bs_inventory` VALUES (191, '1008', 54, NULL, NULL, '打印机', '型号Printer-800', '台', 678.00, 20, 13560.00);
INSERT INTO `bs_inventory` VALUES (194, '1002', 55, NULL, NULL, '空调', '型号AC-200', '台', 2825.00, 50, 141250.00);
INSERT INTO `bs_inventory` VALUES (195, '1008', 55, NULL, NULL, '打印机', '型号Printer-800', '台', 678.00, 20, 13560.00);
INSERT INTO `bs_inventory` VALUES (197, '1008', 56, NULL, NULL, '打印机', '型号Printer-800', '台', 678.00, 60, 40680.00);
INSERT INTO `bs_inventory` VALUES (198, '1003', NULL, NULL, 45, '洗衣机', '型号WM-300', '台', 1356.00, 1000, 1356000.00);
INSERT INTO `bs_inventory` VALUES (200, '1009', 57, NULL, NULL, '办公桌', '型号Desk-900', '张', 1017.00, 40, 40680.00);
INSERT INTO `bs_inventory` VALUES (201, '1001', 58, NULL, NULL, '冰箱', '型号BX-100', '个', 1695.00, 2, 3390.00);
INSERT INTO `bs_inventory` VALUES (202, '1009', NULL, NULL, 46, '办公桌', '型号Desk-900', '张', 1017.00, 30, 30510.00);

-- ----------------------------
-- Table structure for bs_job
-- ----------------------------
DROP TABLE IF EXISTS `bs_job`;
CREATE TABLE `bs_job`  (
  `mid` int(11) NOT NULL AUTO_INCREMENT COMMENT '职称ID',
  `jid` int(11) NULL DEFAULT NULL COMMENT '专家ID',
  `m_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证书名称',
  `m_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证书编号',
  `m_job` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职称',
  `m_institution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '颁发机构',
  `m_get_date` date NULL DEFAULT NULL COMMENT '获得时间',
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '职称表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_job
-- ----------------------------
INSERT INTO `bs_job` VALUES (1, 2, '教师资格证', '93481290', '高级', '机构1', '2020-10-10');
INSERT INTO `bs_job` VALUES (2, 2, '国际双语教师资格证', '23945093', '高级', '机构2', '2022-10-02');
INSERT INTO `bs_job` VALUES (3, 1, '教师资格证', '43743234', '高级', '机构3', '2020-10-10');
INSERT INTO `bs_job` VALUES (4, 3, '教师资格证', '43274332', '高级', '机构4', '2020-10-10');
INSERT INTO `bs_job` VALUES (5, 4, '教师资格证', '32407322', '高级', '机构5', '2020-10-10');
INSERT INTO `bs_job` VALUES (6, 5, '教师资格证', '34234523', '高级', '机构6', '2020-10-10');
INSERT INTO `bs_job` VALUES (7, 6, '教师资格证', '23943843', '高级', '机构7', '2020-10-10');
INSERT INTO `bs_job` VALUES (8, 7, '教师资格证', '23940324', '高级', '机构8', '2020-10-10');
INSERT INTO `bs_job` VALUES (9, 8, '教师资格证', '34242300', '高级', '机构9', '2020-10-10');
INSERT INTO `bs_job` VALUES (10, 9, '教师资格证', '23493341', '高级', '机构10', '2020-10-10');
INSERT INTO `bs_job` VALUES (11, 10, '教师资格证', '43945324', '高级', '机构11', '2020-10-10');
INSERT INTO `bs_job` VALUES (12, 11, '教师资格证', '23438433', '高级', '机构12', '2020-10-10');
INSERT INTO `bs_job` VALUES (13, 12, '教师资格证', '19329422', '高级', '机构13', '2020-10-10');

-- ----------------------------
-- Table structure for bs_occupation
-- ----------------------------
DROP TABLE IF EXISTS `bs_occupation`;
CREATE TABLE `bs_occupation`  (
  `nid` int(11) NOT NULL AUTO_INCREMENT COMMENT '职业ID',
  `jid` int(11) NULL DEFAULT NULL COMMENT '专家ID',
  `n_certificate` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职业资格证书',
  `n_acquire_date` date NULL DEFAULT NULL COMMENT '取得时间',
  `n_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职业证书编号',
  `n_institution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职业颁发机构',
  `n_expiration_date` date NULL DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`nid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '职业表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_occupation
-- ----------------------------
INSERT INTO `bs_occupation` VALUES (1, 2, '法语A1', '2022-01-10', '98762345', '机构1', '2099-01-10');
INSERT INTO `bs_occupation` VALUES (2, 2, '德语A1', '2022-10-10', '38470123', '机构2', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (3, 1, '计算机二级', '2022-10-10', '12312322', '机构3', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (4, 3, '计算机二级', '2022-10-10', '43252353', '机构4', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (5, 4, '计算机二级', '2022-10-10', '42357923', '机构5', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (6, 5, '计算机二级', '2022-10-10', '23498236', '机构6', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (7, 6, '计算机二级', '2022-10-10', '32452375', '机构7', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (8, 7, '计算机二级', '2022-10-10', '24234782', '机构8', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (9, 8, '计算机二级', '2022-10-10', '34823594', '机构9', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (10, 9, '计算机二级', '2022-10-10', '34238945', '机构10', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (11, 10, '计算机二级', '2022-10-10', '34233431', '机构11', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (12, 11, '计算机二级', '2022-10-10', '32489237', '机构12', '2030-10-10');
INSERT INTO `bs_occupation` VALUES (13, 12, '计算机二级', '2022-10-10', '34124323', '机构13', '2030-10-10');

-- ----------------------------
-- Table structure for bs_operator
-- ----------------------------
DROP TABLE IF EXISTS `bs_operator`;
CREATE TABLE `bs_operator`  (
  `yw_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '经办人ID',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `yw_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `yw_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `yw_idcrad` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `yw_mailbox` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `yw_scan_idcard` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证扫描件',
  `yw_scan_empower` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权书扫描件',
  `yw_Landline` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座机',
  `yw_empower_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '授权书内容',
  PRIMARY KEY (`yw_id`) USING BTREE,
  INDEX `FK_Relationship_40`(`hid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '业务经办人信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_operator
-- ----------------------------
INSERT INTO `bs_operator` VALUES (1, 2, '郑国光', '19012345678', '238572358236907322', 'guoguang@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2023/12/19/li_20231219112338A004.jpg\",\"name\":\"li_20231219112338A004.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2023/12/19/li_bei_20231219112338A005.jpg\",\"name\":\"li_bei_20231219112338A005.jpg\"}]', NULL, '0738-1010', NULL);
INSERT INTO `bs_operator` VALUES (15, 82, '李四', '19012345345', '431382198607028742', 'li@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2023/12/29/li_20231229164937A004.jpg\",\"name\":\"li_20231229164937A004.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2023/12/29/li_bei_20231229164938A005.jpg\",\"name\":\"li_bei_20231229164938A005.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (16, 83, '李小花', '19012344580', '431382945309343319', 'xiaohua@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2023/12/29/li_20231229170034A009.jpg\",\"name\":\"li_20231229170034A009.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2023/12/29/li_bei_20231229170034A010.jpg\",\"name\":\"li_bei_20231229170034A010.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (17, 84, '吴艳华', '13512342267', '431382198607022367', 'yanhua@163.com', '[{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102090354A004.jpg\",\"name\":\"li_bei_20240102090354A004.jpg\"},{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102090354A005.jpg\",\"name\":\"li_20240102090354A005.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (18, 85, '李杉', '19012225678', '431382224507023459', 'shan@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102091304A009.jpg\",\"name\":\"li_20240102091304A009.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102091304A010.jpg\",\"name\":\"li_bei_20240102091304A010.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (19, 86, '曾梨', '19012322436', '431382198234573459', 'zengli@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102091908A015.jpg\",\"name\":\"li_20240102091908A015.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102091908A014.jpg\",\"name\":\"li_bei_20240102091908A014.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (20, 87, '李雨琪', '19010095678', '431382198622389459', 'yuqi@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102092345A020.jpg\",\"name\":\"li_20240102092345A020.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102092345A019.jpg\",\"name\":\"li_bei_20240102092345A019.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (21, 88, '张大海', '19012223478', '431383386070355459', 'dahai@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102092853A024.jpg\",\"name\":\"li_20240102092853A024.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102092853A025.jpg\",\"name\":\"li_bei_20240102092853A025.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (22, 89, '曹海', '19012334578', '431382223567023459', 'caohai@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102093215A029.jpg\",\"name\":\"li_20240102093215A029.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102093215A030.jpg\",\"name\":\"li_bei_20240102093215A030.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (23, 90, '骆达华', '19012223348', '431382133297023459', 'dahua@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102093802A035.jpg\",\"name\":\"li_20240102093802A035.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102093802A034.jpg\",\"name\":\"li_bei_20240102093802A034.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (24, 91, '李思思', '19012234678', '431382198232323459', 'sisi@163.com', '[{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102094217A039.jpg\",\"name\":\"li_bei_20240102094217A039.jpg\"},{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102094217A040.jpg\",\"name\":\"li_20240102094217A040.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (25, 92, '陈开立', '19013321678', '431382192232123359', 'kaili@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102094629A044.jpg\",\"name\":\"li_20240102094629A044.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102094629A045.jpg\",\"name\":\"li_bei_20240102094629A045.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (26, 93, '张小宝', '19012223478', '431382198223903459', 'xiaobao@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102095644A050.jpg\",\"name\":\"li_20240102095644A050.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102095644A049.jpg\",\"name\":\"li_bei_20240102095644A049.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (27, 94, '李华彩', '19012332878', '431382198607122239', 'huacai@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102095944A055.jpg\",\"name\":\"li_20240102095944A055.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102095944A054.jpg\",\"name\":\"li_bei_20240102095944A054.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (28, 95, '吴碧梨', '19012223678', '431382198633222459', 'bili@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102100251A059.jpg\",\"name\":\"li_20240102100251A059.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102100251A060.jpg\",\"name\":\"li_bei_20240102100251A060.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (29, 96, '黄花', '19012342238', '431382122348023459', 'huanghua@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102111854A065.jpg\",\"name\":\"li_20240102111854A065.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102111854A064.jpg\",\"name\":\"li_bei_20240102111854A064.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (30, 97, '李姗姗', '19012222438', '431382122347023459', 'shanshan@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102112304A069.jpg\",\"name\":\"li_20240102112304A069.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102112305A070.jpg\",\"name\":\"li_bei_20240102112305A070.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (31, 98, 'fiona', '19012223528', '431382122307023459', 'fi@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102112946A074.jpg\",\"name\":\"li_20240102112946A074.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102112946A075.jpg\",\"name\":\"li_bei_20240102112946A075.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (32, 99, '李依依', '19012223423', '431382198991242259', 'yiyi@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/02/li_20240102113414A079.jpg\",\"name\":\"li_20240102113414A079.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/02/li_bei_20240102113414A080.jpg\",\"name\":\"li_bei_20240102113414A080.jpg\"}]', NULL, NULL, NULL);
INSERT INTO `bs_operator` VALUES (33, 100, '刘德', '19012237678', '431382198622363459', 'liude@163.com', '[{\"size\":208772,\"url\":\"http://localhost:9610/static/2024/01/03/li_20240103142914A016.jpg\",\"name\":\"li_20240103142914A016.jpg\"},{\"size\":80384,\"url\":\"http://localhost:9610/static/2024/01/03/li_bei_20240103142914A017.jpg\",\"name\":\"li_bei_20240103142914A017.jpg\"}]', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bs_payment
-- ----------------------------
DROP TABLE IF EXISTS `bs_payment`;
CREATE TABLE `bs_payment`  (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付约定ID',
  `eid` int(11) NULL DEFAULT NULL COMMENT '合同ID',
  `payContent` int(11) NULL DEFAULT NULL COMMENT '款项内容',
  `payDate` date NULL DEFAULT NULL COMMENT '付款日期',
  `payer` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款单位',
  `hid` int(11) NULL DEFAULT NULL COMMENT '收款合同方ID',
  `h_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收款合同方',
  `payTerms` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款条件',
  `payAmount` decimal(10, 2) NULL DEFAULT NULL COMMENT '付款金额',
  `debty` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '违约责任',
  PRIMARY KEY (`pay_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_payment
-- ----------------------------
INSERT INTO `bs_payment` VALUES (84, 53, 1, '2024-01-13', '鸿鹄科技有限公司', 2, '腾腾科技（深圳）有限公司', '承兑汇票', 50000.00, '由违约方承担');
INSERT INTO `bs_payment` VALUES (85, 53, 3, '2024-01-17', '鸿鹄科技有限公司', NULL, '腾腾科技（深圳）有限公司', '电汇', 104810.00, '由违约方承担');
INSERT INTO `bs_payment` VALUES (86, 54, 1, '2024-01-09', '鸿鹄科技有限公司', 82, '得力集团', '电汇', 154810.00, '违约方承担');
INSERT INTO `bs_payment` VALUES (88, 55, 1, '2024-01-08', '鸿鹄科技有限公司', 82, '得力集团', '电汇', 154810.00, '违约方承担');
INSERT INTO `bs_payment` VALUES (90, 56, 1, '2024-01-08', '鸿鹄科技有限公司', 2, '腾腾科技（深圳）有限公司', '电汇', 40680.00, '违约方承担');
INSERT INTO `bs_payment` VALUES (92, 57, 1, '2024-01-13', '鸿鹄科技有限公司', 2, '腾腾科技（深圳）有限公司', '电汇', 13000.00, '违约方承担');
INSERT INTO `bs_payment` VALUES (93, 57, 3, '2024-01-24', '鸿鹄科技有限公司', NULL, '腾腾科技（深圳）有限公司', '电汇', 27680.00, '违约方承担');
INSERT INTO `bs_payment` VALUES (94, 58, 3, '2024-01-09', '鸿鹄科技有限公司', 83, '上海晨光文具股份有限公司', '电汇', 3390.00, '违约方承担');

-- ----------------------------
-- Table structure for bs_personnel
-- ----------------------------
DROP TABLE IF EXISTS `bs_personnel`;
CREATE TABLE `bs_personnel`  (
  `js_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '技术人员ID',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `js_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `js_position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
  `js_education` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
  `js_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职称',
  PRIMARY KEY (`js_id`) USING BTREE,
  INDEX `FK_Relationship_41`(`hid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '核心技术人员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_personnel
-- ----------------------------
INSERT INTO `bs_personnel` VALUES (1, 2, '张贻三', '工程管理', '本科', '高级工程师');
INSERT INTO `bs_personnel` VALUES (2, 2, '肖雪红', '工程管理', '研究生', '高级工程师');

-- ----------------------------
-- Table structure for bs_risk_monitor
-- ----------------------------
DROP TABLE IF EXISTS `bs_risk_monitor`;
CREATE TABLE `bs_risk_monitor`  (
  `rm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '风险监控ID',
  `eid` int(11) NULL DEFAULT NULL COMMENT '合同ID',
  `rm_illustrate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '风险说明',
  `rm_happen_date` date NULL DEFAULT NULL COMMENT '发生日期',
  `rm_item` int(11) NULL DEFAULT NULL COMMENT '风险项',
  `rm_status` int(11) NULL DEFAULT NULL COMMENT '风险处理状态',
  `rm_handing_date` date NULL DEFAULT NULL COMMENT '处理日期',
  `rm_handing_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `rm_handing_result` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理结果',
  PRIMARY KEY (`rm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商风险监控' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_risk_monitor
-- ----------------------------
INSERT INTO `bs_risk_monitor` VALUES (3, 53, '合同变更', '2024-01-03', 3, 2, '2024-01-03', 'admin', '正在整改中，，，');
INSERT INTO `bs_risk_monitor` VALUES (4, 56, '合同条款变更', '2024-01-03', 4, 2, '2024-01-03', 'admin', '正在整改中');

-- ----------------------------
-- Table structure for bs_sign
-- ----------------------------
DROP TABLE IF EXISTS `bs_sign`;
CREATE TABLE `bs_sign`  (
  `gn_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同签署ID',
  `eid` int(11) NULL DEFAULT NULL COMMENT '合同ID',
  `gn_signatoryCount` int(11) NULL DEFAULT NULL COMMENT '签署方数',
  `gn_sub` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '我方主体',
  `gn_pbId` int(11) NULL DEFAULT NULL COMMENT '乙方供应商ID',
  `gn_pbName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乙方名称',
  `gn_pbAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乙方地址',
  `gn_pbContact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乙方联系人',
  `gn_pbCif` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乙方联系方式',
  `gn_pbBank` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乙方开户行',
  `gn_pbAccount` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '乙方开户账号',
  `gn_pbAmount` decimal(10, 2) NULL DEFAULT NULL COMMENT '合同方金额',
  `gn_pbCurrency` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '币别',
  `gn_pbPayment` decimal(10, 2) NULL DEFAULT NULL COMMENT '已支付金额',
  `gn_pbFixedprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '锁定金额',
  `gn_pbBalance` decimal(10, 2) NULL DEFAULT NULL COMMENT '剩余金额',
  `gn_pcName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '丙方名称',
  `gn_pcAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '丙方地址',
  `gn_pcContact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '丙方联系人',
  `gn_pcCif` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '丙方联系方式',
  `gn_pcBank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '丙方开户行',
  `gn_pcAccount` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '丙方开户账号',
  `gn_pcAmount` decimal(10, 2) NULL DEFAULT NULL COMMENT '合同方金额',
  `gn_pcCurrency` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '币别',
  `gn_pcPayment` decimal(10, 2) NULL DEFAULT NULL COMMENT '已支付金额',
  `gn_pcFixedprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '锁定金额',
  `gn_pcBalance` decimal(10, 2) NULL DEFAULT NULL COMMENT '剩余金额',
  PRIMARY KEY (`gn_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_sign
-- ----------------------------
INSERT INTO `bs_sign` VALUES (38, 53, 0, '鸿鹄科技有限公司', 2, '腾腾科技（深圳）有限公司', '广东省深圳市南山区海天二路33号腾讯滨海大厦', '郑国光', '19012345678', '中国农业银行长沙麓山支行', '6227002960270112911', 154810.00, '人民币', 50000.00, NULL, 104810.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `bs_sign` VALUES (39, 54, 0, '鸿鹄科技有限公司', 82, '得力集团', NULL, '李四', '19012345345', NULL, NULL, 154810.00, '人民币', 154810.00, NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `bs_sign` VALUES (40, 55, 0, '鸿鹄科技有限公司', 82, '得力集团', NULL, '李四', '19012345345', NULL, NULL, 154810.00, '人民币', 154810.00, NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `bs_sign` VALUES (41, 56, 0, '鸿鹄科技有限公司', 2, '腾腾科技（深圳）有限公司', '广东省深圳市南山区海天二路33号腾讯滨海大厦', '郑国光', '19012345678', '中国农业银行长沙麓山支行', '6227002960270112911', 40680.00, '人民币', 40680.00, NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `bs_sign` VALUES (42, 57, 0, '鸿鹄科技有限公司', 2, '腾腾科技（深圳）有限公司', '广东省深圳市南山区海天二路33号腾讯滨海大厦', '郑国光', '19012345678', '中国农业银行长沙麓山支行', '6227002960270112911', 40680.00, '人民币', 13000.00, NULL, 27680.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `bs_sign` VALUES (43, 58, 0, '鸿鹄科技有限公司', 83, '上海晨光文具股份有限公司', NULL, '李小花', '19012344580', NULL, NULL, 3390.00, '人民币', 3390.00, NULL, 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bs_supplier
-- ----------------------------
DROP TABLE IF EXISTS `bs_supplier`;
CREATE TABLE `bs_supplier`  (
  `hid` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `zr_id` int(11) NULL DEFAULT NULL COMMENT '准入ID',
  `h_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `h_credit_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `h_incorporation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位注册地',
  `h_institution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构类型',
  `h_quality` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业性质',
  `h_start_time` date NULL DEFAULT NULL COMMENT '成立日期',
  `h_juridical` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法人/负责人',
  `h_juridical_identity` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法人/负责人身份证号',
  `h_address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位联系地址',
  `h_range` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '经营范围',
  `h_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司简介',
  `h_copies` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '营业执照扫描件',
  `h_juridical_copies` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法人/负责人身份证扫描件',
  `h_expiration` datetime(0) NULL DEFAULT NULL COMMENT '营业执照有效期',
  `h_bank` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `h_account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `h_bank_address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户行地址',
  `h_sign_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位注册电话',
  `h_sign_address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位注册地址',
  `h_capital` decimal(10, 0) NULL DEFAULT NULL COMMENT '注册资本',
  `h_actual_capital` decimal(10, 0) NULL DEFAULT NULL COMMENT '实缴资本',
  `h_prove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户许可证/基本户存款信息证明',
  `f_status` int(11) NULL DEFAULT 0 COMMENT '供应商审核状态',
  `f_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商审核意见',
  `f_state` int(11) NULL DEFAULT 1 COMMENT '供应商状态',
  `f_classify` int(11) NULL DEFAULT 1 COMMENT '供应商分类',
  `h_loginaccount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商账号',
  `h_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '供应商密码',
  PRIMARY KEY (`hid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_supplier
-- ----------------------------
INSERT INTO `bs_supplier` VALUES (2, 0, '腾腾科技（深圳）有限公司', '913101175887740588', '开曼群岛', '服务业', '股份制企业', '1998-10-08', '马花腾', '431382945309348821', '广东省深圳市南山区海天二路33号腾讯滨海大厦', '计算机软、硬件的设计、技术开发、销售（不含专营、专控、专卖商品及限制项目）', '腾讯是一家世界领先的互联网科技公司，用创新的产品和服务提升全球各地人们的生活品质', '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2023/12/19/220466577_20231219112337A001.jpg\",\"name\":\"220466577_20231219112337A001.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/19/bei_20231219112337A002.jpg\",\"name\":\"bei_20231219112337A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/19/zheng_20231219112337A003.jpg\",\"name\":\"zheng_20231219112337A003.jpg\"}]', '2025-10-01 00:00:00', '农业银行', '6227002960270112911', '中国农业银行长沙麓山支行', '13512345678', '深圳市南山区高新区科技中一路腾讯大厦35层', 211, 212, NULL, 1, '已通过', 1, 2, 'tengxun', 'teng123');
INSERT INTO `bs_supplier` VALUES (82, 0, '得力集团', '913101175887743287', '开曼群岛', '零售业', '股份制企业', '1988-03-03', '张三', '431382945309348234', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2023/12/29/R-C1_20231229164937A001.jpg\",\"name\":\"R-C1_20231229164937A001.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/29/bei_20231229164937A002.jpg\",\"name\":\"bei_20231229164937A002.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/29/zheng_20231229164937A003.jpg\",\"name\":\"zheng_20231229164937A003.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'pass', 1, 1, 'huahuo', '123456');
INSERT INTO `bs_supplier` VALUES (83, 0, '上海晨光文具股份有限公司', '913101175887740551', '开曼群岛', '零售业', '股份制企业', '2018-07-04', '王五', '433342198607023459', NULL, NULL, NULL, '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2023/12/29/220466577_20231229170034A006.jpg\",\"name\":\"220466577_20231229170034A006.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2023/12/29/bei_20231229170034A007.jpg\",\"name\":\"bei_20231229170034A007.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2023/12/29/zheng_20231229170034A008.jpg\",\"name\":\"zheng_20231229170034A008.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '通过', 1, 1, 'gaohua', '123456');
INSERT INTO `bs_supplier` VALUES (84, 0, '鸿海科技有限公司', '913101175833770588', '台湾', '服务业', '股份制企业', '2010-06-02', '李华', '431382945309322678', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102090353A001.jpg\",\"name\":\"R-C1_20240102090353A001.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102090353A003.jpg\",\"name\":\"bei_20240102090353A003.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102090353A002.jpg\",\"name\":\"zheng_20240102090353A002.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'pass', 2, 1, 'honghai', '123456');
INSERT INTO `bs_supplier` VALUES (85, 12, '思科科技有限公司', '913101175887742245', '开曼群岛', NULL, NULL, '2001-02-01', '李浩', '431382198607022278', NULL, NULL, NULL, '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2024/01/02/220466577_20240102091303A006.jpg\",\"name\":\"220466577_20240102091303A006.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102091303A008.jpg\",\"name\":\"bei_20240102091303A008.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102091303A007.jpg\",\"name\":\"zheng_20240102091303A007.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'sike', '123456');
INSERT INTO `bs_supplier` VALUES (86, 13, '阿里芭芭集团控股有限公司', '913101175887741278', '开曼群岛', NULL, NULL, '2001-02-01', '马芸', '431382945223948821', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102091907A011.jpg\",\"name\":\"R-C1_20240102091907A011.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102091907A012.jpg\",\"name\":\"bei_20240102091907A012.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102091907A013.jpg\",\"name\":\"zheng_20240102091907A013.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'alibaba', '123456');
INSERT INTO `bs_supplier` VALUES (87, 0, '花旗集团', '913123459887740588', '开曼群岛', '服务业', '私营企业', '2001-03-03', '陈哲', '431382945223848821', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102092344A016.jpg\",\"name\":\"R-C1_20240102092344A016.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102092345A018.jpg\",\"name\":\"bei_20240102092345A018.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102092345A017.jpg\",\"name\":\"zheng_20240102092345A017.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'pass', 3, 1, 'huaqi', '123456');
INSERT INTO `bs_supplier` VALUES (88, 15, '象屿集团有限公司', '913101172234740588', '开曼群岛', NULL, NULL, '2000-01-05', '李珊', '431382945234598821', NULL, NULL, NULL, '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2024/01/02/220466577_20240102092852A021.jpg\",\"name\":\"220466577_20240102092852A021.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102092853A023.jpg\",\"name\":\"bei_20240102092853A023.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102092853A022.jpg\",\"name\":\"zheng_20240102092853A022.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'xiangyu', '123456');
INSERT INTO `bs_supplier` VALUES (89, 16, '中国航空工业集团有限公司', '913101175223840588', '开曼群岛', NULL, NULL, '1983-06-08', '陈达', '431382922376348821', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102093214A026.jpg\",\"name\":\"R-C1_20240102093214A026.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102093214A027.jpg\",\"name\":\"bei_20240102093214A027.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102093214A028.jpg\",\"name\":\"zheng_20240102093214A028.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'chinesehang', '123456');
INSERT INTO `bs_supplier` VALUES (90, 17, '上海建工集团股份有限公司', '913101133217740588', '上海', NULL, NULL, '2016-02-02', '刘花', '431382945309348821', NULL, NULL, NULL, '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2024/01/02/220466577_20240102093801A031.jpg\",\"name\":\"220466577_20240102093801A031.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102093801A032.jpg\",\"name\":\"bei_20240102093801A032.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102093801A033.jpg\",\"name\":\"zheng_20240102093801A033.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'jiangong', '123456');
INSERT INTO `bs_supplier` VALUES (91, 18, '新疆广汇实业投资（集团）有限责任公司', '913101132234022588', '新疆', NULL, NULL, '2002-07-12', '李建华', '431322345309348821', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102094216A036.jpg\",\"name\":\"R-C1_20240102094216A036.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102094216A037.jpg\",\"name\":\"bei_20240102094216A037.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102094216A038.jpg\",\"name\":\"zheng_20240102094216A038.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'guanghui', '123456');
INSERT INTO `bs_supplier` VALUES (92, 19, '成都兴城投资集团有限公司', '913101117582234588', '成都', NULL, NULL, '2001-07-10', '王涵', '431382943321348821', NULL, NULL, NULL, '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2024/01/02/220466577_20240102094628A041.jpg\",\"name\":\"220466577_20240102094628A041.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102094629A043.jpg\",\"name\":\"bei_20240102094629A043.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102094629A042.jpg\",\"name\":\"zheng_20240102094629A042.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'xingcheng', '123456');
INSERT INTO `bs_supplier` VALUES (93, 20, '通威集团有限公司', '913101172210740588', '北京', NULL, NULL, '2000-06-21', '吴桐', '431382945322398821', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102095643A046.jpg\",\"name\":\"R-C1_20240102095643A046.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102095644A048.jpg\",\"name\":\"bei_20240102095644A048.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102095644A047.jpg\",\"name\":\"zheng_20240102095644A047.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'tongwei', '123456');
INSERT INTO `bs_supplier` VALUES (94, 21, '安徽海螺集团有限责任公司', '913101175882235788', '安徽', NULL, NULL, '1999-07-08', '吴安', '431382922345438821', NULL, NULL, NULL, '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2024/01/02/220466577_20240102095944A051.jpg\",\"name\":\"220466577_20240102095944A051.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102095944A052.jpg\",\"name\":\"bei_20240102095944A052.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102095944A053.jpg\",\"name\":\"zheng_20240102095944A053.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'hailuo', '123456');
INSERT INTO `bs_supplier` VALUES (95, 22, '上海德龙钢铁集团有限公司', '913101175822170588', '上海', NULL, NULL, '1999-03-09', '李龙威', '431382945223748821', NULL, NULL, NULL, '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2024/01/02/220466577_20240102100250A056.jpg\",\"name\":\"220466577_20240102100250A056.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102100250A057.jpg\",\"name\":\"bei_20240102100250A057.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102100250A058.jpg\",\"name\":\"zheng_20240102100250A058.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, 1, 1, 'denglong', '123456');
INSERT INTO `bs_supplier` VALUES (96, 0, '恒力集团有限公司', '913101175220042388', '山东', NULL, NULL, '2000-02-01', '李明伟', '431382945302236821', NULL, NULL, NULL, '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2024/01/02/220466577_20240102111854A061.jpg\",\"name\":\"220466577_20240102111854A061.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102111854A062.jpg\",\"name\":\"bei_20240102111854A062.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102111854A063.jpg\",\"name\":\"zheng_20240102111854A063.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'pass', 1, 1, 'hengli', '123456');
INSERT INTO `bs_supplier` VALUES (97, 0, '正威国际集团有限公司', '913101133014740588', '上海', NULL, NULL, '2002-03-02', '吴孙戈', '431382945302238821', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102112304A066.jpg\",\"name\":\"R-C1_20240102112304A066.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102112304A068.jpg\",\"name\":\"bei_20240102112304A068.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102112304A067.jpg\",\"name\":\"zheng_20240102112304A067.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'pass', 1, 1, 'zhengwei', '123456');
INSERT INTO `bs_supplier` VALUES (98, 0, '法国巴黎银行', '913101175222310588', '开曼群岛', NULL, NULL, '1990-02-05', 'lip', '431382945322308821', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102112945A071.jpg\",\"name\":\"R-C1_20240102112945A071.jpg\"}]', '[{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102112946A072.jpg\",\"name\":\"zheng_20240102112946A072.jpg\"},{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102112946A073.jpg\",\"name\":\"bei_20240102112946A073.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 'great', 1, 1, 'frech', '123456');
INSERT INTO `bs_supplier` VALUES (99, 0, '万科企业股份有限公司', '913101175229040331', '杭州', NULL, NULL, '2000-11-07', '王曼达', '431382945223948821', NULL, NULL, NULL, '[{\"size\":537680,\"url\":\"http://localhost:9610/static/2024/01/02/R-C1_20240102113413A076.jpg\",\"name\":\"R-C1_20240102113413A076.jpg\"}]', '[{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/02/zheng_20240102113413A077.jpg\",\"name\":\"zheng_20240102113413A077.jpg\"},{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/02/bei_20240102113413A078.jpg\",\"name\":\"bei_20240102113413A078.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '通过', 1, 1, 'wanke', '123456');
INSERT INTO `bs_supplier` VALUES (100, 0, '深圳花海集团股份有限公司', '913101175882201588', '开曼群岛', '服务业', '股份制企业', '2000-03-02', '张华', '431382945322358821', '湖南长沙', NULL, '', '[{\"size\":358080,\"url\":\"http://localhost:9610/static/2024/01/03/220466577_20240103142913A013.jpg\",\"name\":\"220466577_20240103142913A013.jpg\"}]', '[{\"size\":26342,\"url\":\"http://localhost:9610/static/2024/01/03/bei_20240103142913A015.jpg\",\"name\":\"bei_20240103142913A015.jpg\"},{\"size\":30088,\"url\":\"http://localhost:9610/static/2024/01/03/zheng_20240103142913A014.jpg\",\"name\":\"zheng_20240103142913A014.jpg\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '通过', 1, 1, 'huahai', '123456');

-- ----------------------------
-- Table structure for bs_training
-- ----------------------------
DROP TABLE IF EXISTS `bs_training`;
CREATE TABLE `bs_training`  (
  `lid` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训ID',
  `jid` int(11) NULL DEFAULT NULL COMMENT '专家ID',
  `l_training` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '培训机构',
  `l_specialized` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '培训专业',
  `l_start_date` date NULL DEFAULT NULL COMMENT '培训开始时间',
  `l_end_date` date NULL DEFAULT NULL COMMENT '培训结束时间',
  PRIMARY KEY (`lid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '培训表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bs_training
-- ----------------------------
INSERT INTO `bs_training` VALUES (1, 2, '机构1', '语言学', '2022-10-01', '2022-11-01');
INSERT INTO `bs_training` VALUES (2, 2, '机构2', '语言学', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (3, 1, '机构3', '养殖', '2022-10-01', '2022-11-01');
INSERT INTO `bs_training` VALUES (4, 1, '机构4', '养殖', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (5, 3, '机构5', '机械自动化', '2022-10-01', '2022-11-01');
INSERT INTO `bs_training` VALUES (6, 3, '机构6', '机械自动化', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (7, 4, '机构7', '计算机信息', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (8, 5, '机构8', '计算机信息', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (9, 6, '机构9', '计算机信息', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (10, 7, '机构10', '计算机信息', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (11, 8, '机构11', '计算机信息', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (12, 9, '机构12', '计算机信息', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (13, 10, '机构13', '计算机信息', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (14, 11, '机构14', '计算机信息', '2022-12-01', '2023-01-01');
INSERT INTO `bs_training` VALUES (15, 12, '机构15', '计算机信息', '2022-12-01', '2023-01-01');

-- ----------------------------
-- Table structure for com_code_rules
-- ----------------------------
DROP TABLE IF EXISTS `com_code_rules`;
CREATE TABLE `com_code_rules`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target_form` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标表单',
  `prefix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号前缀',
  `serial_number` int(10) NULL DEFAULT NULL COMMENT '流水号',
  `step` int(10) NULL DEFAULT NULL COMMENT '步长',
  `code_rules` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号规则',
  `operator` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `year` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年',
  `mouth` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '月',
  `day` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日',
  `hour` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时',
  `minute` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分',
  `second` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '秒',
  `max_mantissa` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最大尾数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '编码规则表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of com_code_rules
-- ----------------------------
INSERT INTO `com_code_rules` VALUES (2, '采购计划', 'CGJH', 3, 2, 'CGJHYYYYMMDDHHmm001', 'admin', '2024-01-03 16:44:29', 'YYYY', 'MM', 'DD', 'HH', 'mm', '', '243');
INSERT INTO `com_code_rules` VALUES (3, '招标项目', 'ZB', 3, 1, 'ZBYYYYMMDDHHMMSS001', '雷建', '2024-01-03 16:49:39', 'YYYY', 'MM', 'DD', 'HH', 'mm', 'SS', '026');
INSERT INTO `com_code_rules` VALUES (4, '投标', 'TB', 3, 1, 'TBYYYYMMDD001', '雷健', '2023-11-23 14:07:05', 'YYYY', 'MM', 'DD', '', '', '', '001');
INSERT INTO `com_code_rules` VALUES (5, '合同', 'HT', 4, 1, 'HTYYYYMMDD0001', '12', '2024-01-03 17:25:01', 'YYYY', 'MM', 'DD', '', '', '', '0048');
INSERT INTO `com_code_rules` VALUES (7, '预算', 'YS', 4, 2, 'YSYYYYMMDD0001', '小欧领导', '2024-01-03 16:46:16', 'YYYY', 'MM', 'DD', '', '', '', '0035');
INSERT INTO `com_code_rules` VALUES (8, '物料', 'WL', 2, 1, 'WLYYYYMMDDHH01', '张三', '2023-11-28 10:38:25', 'YYYY', 'MM', 'DD', 'HH', '', '', '002');
INSERT INTO `com_code_rules` VALUES (9, '行项目', 'HXM', 3, 2, 'HXMYYYYMMDD001', '王五', '2024-01-03 16:52:24', 'YYYY', 'MM', 'DD', NULL, NULL, NULL, '120');
INSERT INTO `com_code_rules` VALUES (10, '框架计划', 'KJJH', 3, 1, 'KJJHYYYYMMDD001', '王五', '2024-01-03 16:52:24', 'YYYY', 'MM', 'DD', '', '', '', '026');
INSERT INTO `com_code_rules` VALUES (13, '非招标项目', 'FZB', 3, 2, 'FZBYYYYMMDD001', '六六', '2024-01-05 13:24:15', 'YYYY', 'MM', 'DD', '', '', '', '054');
INSERT INTO `com_code_rules` VALUES (14, '框架协议', 'FM', 4, 1, 'FMYYYYMMDD0001', '12', '2024-01-03 17:29:55', 'YYYY', 'MM', 'DD', '', '', '', '0045');
INSERT INTO `com_code_rules` VALUES (15, '供应商准入', 'ZR', 4, 1, 'ZRYYYYMMDD0001', 'vocal', '2024-01-03 14:29:14', 'YYYY', 'MM', 'DD', '', '', '', '0031');
INSERT INTO `com_code_rules` VALUES (16, '抽取编号', 'CQ', 3, 1, 'CQYYYYMMDD001', '雷健', '2024-01-05 10:40:46', 'YYYY', 'MM', 'DD', '', '', '', '047');

-- ----------------------------
-- Table structure for com_pub_attachments
-- ----------------------------
DROP TABLE IF EXISTS `com_pub_attachments`;
CREATE TABLE `com_pub_attachments`  (
  `an_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `aid` int(11) NULL DEFAULT NULL COMMENT '采购计划id',
  `jhid` int(11) NULL DEFAULT NULL COMMENT '框架计划id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '合同ID',
  `an_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '附件名称',
  `an_type` int(11) NULL DEFAULT NULL COMMENT '业务类型',
  `an_dan` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务单据号',
  `an_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'URL',
  `an_size` decimal(10, 0) NULL DEFAULT NULL COMMENT '文件大小',
  `an_upload_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`an_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公共附件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of com_pub_attachments
-- ----------------------------
INSERT INTO `com_pub_attachments` VALUES (74, 76, NULL, NULL, 'csc_20240103085454A073.txt', NULL, NULL, 'http://localhost:9610/static/2024/01/03/csc_20240103085454A073.txt', NULL, '2024-01-03 08:54:55');
INSERT INTO `com_pub_attachments` VALUES (75, NULL, NULL, 53, '附件_20240103102200A075.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103102200A075.docx', 11697, '2024-01-03 10:22:02');
INSERT INTO `com_pub_attachments` VALUES (76, 82, NULL, NULL, '附件_20240103104522A078.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103104522A078.docx', NULL, '2024-01-03 10:45:23');
INSERT INTO `com_pub_attachments` VALUES (77, NULL, NULL, 54, '附件_20240103150701A034.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103150701A034.docx', 11697, '2024-01-03 15:08:03');
INSERT INTO `com_pub_attachments` VALUES (78, NULL, NULL, 55, '附件_20240103150701A034.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103150701A034.docx', 11697, '2024-01-03 15:08:09');
INSERT INTO `com_pub_attachments` VALUES (79, 66, NULL, NULL, '附件_20240103151403A037.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103151403A037.docx', NULL, '2024-01-03 15:14:03');
INSERT INTO `com_pub_attachments` VALUES (80, NULL, NULL, 56, '附件_20240103152025A039.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103152025A039.docx', 11697, '2024-01-03 15:20:26');
INSERT INTO `com_pub_attachments` VALUES (81, 79, NULL, NULL, '附件_20240103152215A041.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103152215A041.docx', NULL, '2024-01-03 15:22:15');
INSERT INTO `com_pub_attachments` VALUES (82, 87, NULL, NULL, '附件1_20240103164652A043.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件1_20240103164652A043.docx', NULL, '2024-01-03 16:46:55');
INSERT INTO `com_pub_attachments` VALUES (83, NULL, NULL, 57, '附件_20240103171349A059.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103171349A059.docx', 11697, '2024-01-03 17:13:51');
INSERT INTO `com_pub_attachments` VALUES (84, 85, NULL, NULL, '附件_20240103171804A061.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件_20240103171804A061.docx', NULL, '2024-01-03 17:18:05');
INSERT INTO `com_pub_attachments` VALUES (85, NULL, NULL, 58, '附件1_20240103172500A063.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件1_20240103172500A063.docx', 11697, '2024-01-03 17:25:02');
INSERT INTO `com_pub_attachments` VALUES (86, 86, NULL, NULL, '附件1_20240103172847A065.docx', NULL, NULL, 'http://localhost:9610/static/2024/01/03/附件1_20240103172847A065.docx', NULL, '2024-01-03 17:28:47');
INSERT INTO `com_pub_attachments` VALUES (87, 84, NULL, NULL, 'csc_20240105131447A002.txt', NULL, NULL, 'http://localhost:9610/static/2024/01/05/csc_20240105131447A002.txt', NULL, '2024-01-05 13:14:48');
INSERT INTO `com_pub_attachments` VALUES (88, 60, NULL, NULL, 'csc_20240105132546A003.txt', NULL, NULL, 'http://localhost:9610/static/2024/01/05/csc_20240105132546A003.txt', NULL, '2024-01-05 13:25:46');
INSERT INTO `com_pub_attachments` VALUES (89, 80, NULL, NULL, '123_20240105154419A004.txt', NULL, NULL, 'http://localhost:9610/static/2024/01/05/123_20240105154419A004.txt', NULL, '2024-01-05 15:44:20');

-- ----------------------------
-- Table structure for com_quotation
-- ----------------------------
DROP TABLE IF EXISTS `com_quotation`;
CREATE TABLE `com_quotation`  (
  `bj_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报价单号',
  `gf_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购项目编号',
  `bj_second` int(11) NULL DEFAULT NULL COMMENT '报价次数',
  `bj_total` decimal(10, 2) NULL DEFAULT NULL COMMENT '报价金额',
  `bj_hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报价时间',
  PRIMARY KEY (`bj_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '报价单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of com_quotation
-- ----------------------------
INSERT INTO `com_quotation` VALUES (66, 'FZB202413050', 1, 3000.00, 2, 1, '2024-01-03 15:17:30');
INSERT INTO `com_quotation` VALUES (67, 'FZB202413050', 1, 3112.00, 2, 0, '2024-01-03 15:17:49');
INSERT INTO `com_quotation` VALUES (68, 'FZB202413052', 1, 2900.00, 2, 1, '2024-01-03 17:19:40');
INSERT INTO `com_quotation` VALUES (69, 'FZB202413052', 1, 2999.00, 2, 0, '2024-01-03 17:19:58');
INSERT INTO `com_quotation` VALUES (70, 'FZB202413052', 1, 2800.00, 83, 0, '2024-01-03 17:21:54');
INSERT INTO `com_quotation` VALUES (71, 'FZB202413050', 1, 4200.00, 83, 0, '2024-01-05 11:21:45');
INSERT INTO `com_quotation` VALUES (72, 'FZB202413049', 1, 114000.00, 84, 0, '2024-01-05 11:25:56');
INSERT INTO `com_quotation` VALUES (73, 'FZB202415053', 1, 226000.00, 84, 0, '2024-01-05 11:26:35');
INSERT INTO `com_quotation` VALUES (74, 'FZB202415053', 1, 225500.00, 2, 0, '2024-01-05 13:15:18');
INSERT INTO `com_quotation` VALUES (75, 'FZB202415054', 1, 40600.00, 83, 0, '2024-01-05 13:27:10');
INSERT INTO `com_quotation` VALUES (76, 'FZB202415054', 1, 39000.00, 2, 0, '2024-01-05 13:27:21');
INSERT INTO `com_quotation` VALUES (77, 'FZB202413048', 1, 3880.00, 2, 0, '2024-01-05 15:47:02');
INSERT INTO `com_quotation` VALUES (78, 'FZB202413048', 1, 3600.00, 85, 0, '2024-01-05 15:49:16');

-- ----------------------------
-- Table structure for com_sourcing
-- ----------------------------
DROP TABLE IF EXISTS `com_sourcing`;
CREATE TABLE `com_sourcing`  (
  `xy_id` int(11) NOT NULL COMMENT '采购计划序号',
  `gid` int(11) NULL DEFAULT NULL COMMENT '非招标项目ID',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `xy_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购计划编码',
  `xy_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购计划名称',
  `xy_type` int(11) NULL DEFAULT NULL COMMENT '业务类型',
  `dept` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建部门',
  `xy_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购人',
  `td_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `xy_count` int(11) NULL DEFAULT NULL COMMENT '行项目数量',
  `xy_pcode` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品编码',
  `xy_pname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `xy_number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `xy_ji_unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计量单位',
  `xy_shui` decimal(10, 0) NULL DEFAULT NULL COMMENT '税率',
  `xy_price` decimal(10, 0) NULL DEFAULT NULL COMMENT '预算单价',
  `xy_money` decimal(10, 0) NULL DEFAULT NULL COMMENT '预算金额',
  `xy_delivery_time` datetime(0) NULL DEFAULT NULL COMMENT '交付时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `area` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交货地点',
  `fj_annex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`xy_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购寻源' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 155 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码生成业务表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (98, 'bid_candidate', '中标候选人', NULL, NULL, 'BidCandidate', 'crud', 'com.ruoyi.system', 'system', 'candidate', '中标候选人', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:03:41', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (99, 'bid_documents', '招标文件表', NULL, NULL, 'BidDocuments', 'crud', 'com.ruoyi.system', 'system', 'documents', '招标文件', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:03:44', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (100, 'bid_get_tender', '获取标书表', NULL, NULL, 'BidGetTender', 'crud', 'com.ruoyi.system', 'system', 'tender', '获取标书', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:03:45', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (101, 'bid_notice', '招标公告表', NULL, NULL, 'BidNotice', 'crud', 'com.ruoyi.system', 'system', 'notice', '招标公告', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:03:47', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (102, 'bid_submission', '投递标书表', NULL, NULL, 'BidSubmission', 'crud', 'com.ruoyi.system', 'system', 'submission', '投递标书', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:03:50', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (103, 'bid_tender', '招标项目表', NULL, NULL, 'BidTender', 'crud', 'com.ruoyi.system', 'system', 'tender', '招标项目', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:03:52', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (104, 'bid_winning_results', '中标结果公示表', NULL, NULL, 'BidWinningResults', 'crud', 'com.ruoyi.system', 'system', 'results', '中标结果公示', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:03:57', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (105, 'bs_access', '供应商准入', NULL, NULL, 'BsAccess', 'crud', 'com.ruoyi.system', 'system', 'access', '供应商准入', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:03:58', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (106, 'bs_accessories', '相关附件表', NULL, NULL, 'BsAccessories', 'crud', 'com.ruoyi.system', 'system', 'accessories', '相关附件', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:00', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (107, 'bs_achievement', '业绩表', NULL, NULL, 'BsAchievement', 'crud', 'com.ruoyi.system', 'system', 'achievement', '业绩', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:00', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (110, 'bs_contract', '合同表', NULL, NULL, 'BsContract', 'crud', 'com.ruoyi.system', 'system', 'contract', '合同', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:09', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (111, 'bs_educate', '教育表', NULL, NULL, 'BsEducate', 'crud', 'com.ruoyi.system', 'system', 'educate', '教育', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:14', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (112, 'bs_employee', '员工表', NULL, NULL, 'BsEmployee', 'crud', 'com.ruoyi.system', 'system', 'employee', '员工', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:17', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (113, 'bs_enterprise', '企业资质表', NULL, NULL, 'BsEnterprise', 'crud', 'com.ruoyi.system', 'system', 'enterprise', '企业资质', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:20', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (114, 'bs_experience', '任职经历表', NULL, NULL, 'BsExperience', 'crud', 'com.ruoyi.system', 'system', 'experience', '任职经历', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:23', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (115, 'bs_expert', '专家表', NULL, NULL, 'BsExpert', 'crud', 'com.ruoyi.system', 'system', 'expert', '专家', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:25', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (116, 'bs_financial_status', '财务状态表', NULL, NULL, 'BsFinancialStatus', 'crud', 'com.ruoyi.system', 'system', 'status', '财务状态', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:31', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (117, 'bs_job', '职称表', NULL, NULL, 'BsJob', 'crud', 'com.ruoyi.system', 'system', 'job', '职称', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:34', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (118, 'bs_occupation', '职业表', NULL, NULL, 'BsOccupation', 'crud', 'com.ruoyi.system', 'system', 'occupation', '职业', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:36', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (119, 'bs_operator', '业务经办人信息表', NULL, NULL, 'BsOperator', 'crud', 'com.ruoyi.system', 'system', 'operator', '业务经办人信息', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:37', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (120, 'bs_personnel', '核心技术人员表', NULL, NULL, 'BsPersonnel', 'crud', 'com.ruoyi.system', 'system', 'personnel', '核心技术人员', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:40', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (121, 'bs_supplier', '供应商表', NULL, NULL, 'BsSupplier', 'crud', 'com.ruoyi.system', 'system', 'supplier', '供应商', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:42', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (122, 'bs_training', '培训表', NULL, NULL, 'BsTraining', 'crud', 'com.ruoyi.system', 'system', 'training', '培训', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:49', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (124, 'com_quotation', '报价单', NULL, NULL, 'ComQuotation', 'crud', 'com.ruoyi.system', 'system', 'quotation', '报价单', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:53', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (125, 'com_sourcing', '采购寻源', NULL, NULL, 'ComSourcing', 'crud', 'com.ruoyi.system', 'system', 'sourcing', '采购寻源', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:04:54', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (126, 'nobid_non_pro', '非招标项目表', NULL, NULL, 'NobidNonPro', 'crud', 'com.ruoyi.system', 'system', 'pro', '非招标项目', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:05:00', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (127, 'nobid_sup_non_pro', '供应商非招标项目表', NULL, NULL, 'NobidSupNonPro', 'crud', 'com.ruoyi.system', 'system', 'pro', '供应商非招标项目', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:05:02', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (130, 'bs_frame_management', '框架协议管理表', NULL, NULL, 'BSFrameManagement', 'crud', 'com.hh.pms.cm', 'system', 'management', '框架协议管理', 'ruoyi', '0', '/', '{}', 'admin', '2023-11-17 16:05:15', '', '2023-12-18 17:19:39', NULL);
INSERT INTO `gen_table` VALUES (131, 'ppm_frame_plan', '框架计划表', NULL, NULL, 'PpmFramePlan', 'crud', 'com.ruoyi.system', 'system', 'plan', '框架计划', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-17 16:05:18', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (139, 'ppm_approval_record', '审批记录', NULL, NULL, 'PpmApprovalRecord', 'crud', 'com.ruoyi.system', 'system', 'record', '审批记录', 'ruoyi', '0', '/', '{}', 'admin', '2023-11-24 12:42:29', '', '2023-11-24 13:03:29', NULL);
INSERT INTO `gen_table` VALUES (142, 'com_code_rules', '编码规则表', NULL, NULL, 'ComCodeRules', 'crud', 'com.ruoyi.system', 'system', 'rules', '编码规则', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-27 15:33:23', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (144, 'ppm_device', '设备信息表', NULL, NULL, 'PpmDevice', 'crud', 'com.ruoyi.system', 'system', 'device', '设备信息', 'ruoyi', '0', '/', '{}', 'admin', '2023-11-28 11:40:26', '', '2023-11-28 11:49:51', NULL);
INSERT INTO `gen_table` VALUES (145, 'ppm_line_items', '行项目表', NULL, NULL, 'PpmLineItems', 'crud', 'com.ruoyi.system', 'system', 'items', '行项目', 'ruoyi', '0', '/', '{}', 'admin', '2023-11-28 11:40:29', '', '2023-11-28 11:53:47', NULL);
INSERT INTO `gen_table` VALUES (146, 'ppm_procurement_plan', '采购计划表', NULL, NULL, 'PpmProcurementPlan', 'crud', 'com.hh.nobidding', 'system', 'plan', '采购计划', 'ruoyi', '0', '/', '{}', 'admin', '2023-11-28 11:40:34', '', '2023-12-26 00:15:51', NULL);
INSERT INTO `gen_table` VALUES (147, 'ppm_budget', '预算表', NULL, NULL, 'PpmBudget', 'crud', 'com.ruoyi.system', 'system', 'budget', '预算', 'ruoyi', '0', '/', '{}', 'admin', '2023-11-28 11:43:16', '', '2023-11-28 11:44:19', NULL);
INSERT INTO `gen_table` VALUES (148, 'bs_risk_monitor', '供应商风险监控', NULL, NULL, 'BsRiskMonitor', 'crud', 'com.ruoyi.system', 'system', 'monitor', '供应商风险监控', 'ruoyi', '0', '/', NULL, 'admin', '2023-11-28 17:47:49', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (149, 'bs_payment', '支付约定', NULL, NULL, 'BsPayment', 'crud', 'com.ruoyi.system', 'system', 'payment', '支付约定', 'ruoyi', '0', '/', '{}', 'admin', '2023-11-30 14:45:31', '', '2023-11-30 15:13:25', NULL);
INSERT INTO `gen_table` VALUES (150, 'bs_sign', '签署执行状态', NULL, NULL, 'BsSign', 'crud', 'com.ruoyi.system', 'system', 'sign', '签署执行状态', 'ruoyi', '0', '/', '{}', 'admin', '2023-11-30 14:45:33', '', '2023-11-30 15:14:24', NULL);
INSERT INTO `gen_table` VALUES (151, 'bid_applications', '抽取申请表', NULL, NULL, 'BidApplications', 'crud', 'com.ruoyi.system', 'system', 'applications', '抽取申请', 'ruoyi', '0', '/', NULL, 'admin', '2023-12-04 11:18:48', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (152, 'bid_committee', '评标委员会表', NULL, NULL, 'BidCommittee', 'crud', 'com.ruoyi.system', 'system', 'committee', '评标委员会', 'ruoyi', '0', '/', NULL, 'admin', '2023-12-04 11:18:50', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (153, 'bs_inventory', '产品表', NULL, NULL, 'BsInventory', 'crud', 'com.hh.nobidding', 'system', 'inventory', '产品表', 'ruoyi', '0', '/', '{}', 'admin', '2023-12-05 16:00:36', '', '2023-12-25 17:34:51', NULL);
INSERT INTO `gen_table` VALUES (154, 'com_pub_attachments', '公共附件表', NULL, NULL, 'ComPubAttachments', 'crud', 'com.hh.pms', 'system', 'attachments', '公共附件', 'ruoyi', '0', '/', '{}', 'admin', '2023-12-13 10:40:33', '', '2023-12-13 10:45:33', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint(20) NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3078 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '代码生成业务表字段' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (2399, 98, 'zid', '候选人ID', 'int(11)', 'Long', 'zid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:03:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (2400, 98, 'sid', '招标项目ID', 'int(11)', 'Long', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:03:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (2401, 98, 'z_bname', '供应商名称', 'varchar(50)', 'String', 'zBname', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-17 16:03:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2402, 98, 'z_final', '最终报价', 'decimal(10,0)', 'Long', 'zFinal', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:03:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2403, 98, 'z_fraction', '最终得分', 'decimal(10,0)', 'Long', 'zFraction', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:03:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2404, 98, 'z_recommend', '是否推荐', 'int(11)', 'Long', 'zRecommend', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:03:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2405, 98, 'z_ranking', '排名', 'int(11)', 'Long', 'zRanking', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:03:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2406, 98, 'z_sign', '专家签到表', 'varchar(30)', 'String', 'zSign', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:03:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2407, 98, 'z_censor', '审查表', 'varchar(30)', 'String', 'zCensor', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:03:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2408, 98, 'z_review', '评审表', 'varchar(30)', 'String', 'zReview', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-17 16:03:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2409, 98, 'z_summary', '最终汇总表', 'varchar(30)', 'String', 'zSummary', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-17 16:03:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2410, 98, 'z_bidder', '是否中标', 'int(11)', 'Long', 'zBidder', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-17 16:03:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2411, 98, 'z_send_time', '发送时间', 'datetime', 'Date', 'zSendTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 13, 'admin', '2023-11-17 16:03:44', '', NULL);
INSERT INTO `gen_table_column` VALUES (2412, 99, 'wid', '文件ID', 'int(11)', 'Long', 'wid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:03:44', '', NULL);
INSERT INTO `gen_table_column` VALUES (2413, 99, 'sid', '招标项目ID', 'int(11)', 'Long', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:03:44', '', NULL);
INSERT INTO `gen_table_column` VALUES (2414, 99, 'w_title', '文件标题', 'varchar(256)', 'String', 'wTitle', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:03:44', '', NULL);
INSERT INTO `gen_table_column` VALUES (2415, 99, 'w_size', '文件大小', 'int(11)', 'Long', 'wSize', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:03:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2416, 99, 'w_upload_time', '上传时间', 'datetime', 'Date', 'wUploadTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2023-11-17 16:03:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2417, 100, 'yid', '获取标书ID', 'int(11)', 'Long', 'yid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:03:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2418, 100, 'sid', '招标项目ID', 'int(11)', 'Long', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:03:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2419, 100, 'td_name', '投标人名称', 'varchar(50)', 'String', 'tdName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-17 16:03:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (2420, 100, 'td_person', '联系人', 'varchar(50)', 'String', 'tdPerson', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:03:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (2421, 100, 'td_phone', '联系方式', 'varchar(20)', 'String', 'tdPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:03:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (2422, 100, 'email', '邮箱', 'varchar(30)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:03:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (2423, 100, 'y_download_time', '下载时间', 'datetime', 'Date', 'yDownloadTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2023-11-17 16:03:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (2424, 100, 'td_status', '投标状态', 'int(11)', 'Long', 'tdStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 8, 'admin', '2023-11-17 16:03:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (2425, 101, 'uid', '招标公告ID', 'int(11)', 'Long', 'uid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:03:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (2426, 101, 'sid', '招标项目ID', 'int(11)', 'Long', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:03:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2427, 101, 'u_title', '公告标题', 'varchar(256)', 'String', 'uTitle', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:03:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2428, 101, 'u_project', '关联项目', 'int(11)', 'Long', 'uProject', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:03:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2429, 101, 'u_money', '项目资金', 'decimal(10,0)', 'Long', 'uMoney', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:03:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2430, 101, 'u_get_time', '标注获取时间', 'datetime', 'Date', 'uGetTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-11-17 16:03:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2431, 101, 'u_accept_time', '接受答疑时间', 'datetime', 'Date', 'uAcceptTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2023-11-17 16:03:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2432, 101, 'u_end_time', '投标截止时间', 'datetime', 'Date', 'uEndTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-11-17 16:03:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (2433, 101, 'u_kai_time', '开标时间', 'datetime', 'Date', 'uKaiTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 9, 'admin', '2023-11-17 16:03:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (2434, 101, 'fj_annex', '附件', 'int(11)', 'Long', 'fjAnnex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-17 16:03:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (2435, 101, 'fj_status', '招标公告审批状态', 'int(11)', 'Long', 'fjStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2023-11-17 16:03:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (2436, 101, 'fj_remark', '备注', 'varchar(500)', 'String', 'fjRemark', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 12, 'admin', '2023-11-17 16:03:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (2437, 102, 'td_id', NULL, 'int(11)', 'Long', 'tdId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:03:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (2438, 102, 'sid', '招标项目ID', 'int(11)', 'Long', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:03:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (2439, 102, 'td_name', '投标人名称', 'varchar(50)', 'String', 'tdName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-17 16:03:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (2440, 102, 'td_person', '联系人', 'varchar(50)', 'String', 'tdPerson', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:03:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (2441, 102, 'td_phone', '联系方式', 'varchar(20)', 'String', 'tdPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:03:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2442, 102, 'td_status', '投标状态', 'int(11)', 'Long', 'tdStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 6, 'admin', '2023-11-17 16:03:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2443, 102, 'td_success_time', '成功递交时间', 'datetime', 'Date', 'tdSuccessTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2023-11-17 16:03:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2444, 102, 'td_fail_time', '文件撤回时间', 'datetime', 'Date', 'tdFailTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-11-17 16:03:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2445, 102, 'td_IP', 'IP地址', 'varchar(20)', 'String', 'tdIp', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:03:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2446, 103, 'sid', '招标项目ID', 'int(11)', 'Long', 'sid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:03:52', '', '2023-12-04 14:33:28');
INSERT INTO `gen_table_column` VALUES (2447, 103, 'xy_id', '采购计划序号', 'int(11)', 'Long', 'xyId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:03:52', '', '2023-12-04 14:33:28');
INSERT INTO `gen_table_column` VALUES (2448, 103, 's_code', '招标项目编号', 'varchar(10)', 'String', 'sCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:03:52', '', '2023-12-04 14:33:28');
INSERT INTO `gen_table_column` VALUES (2449, 103, 's_name', '招标项目名称', 'varchar(50)', 'String', 'sName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-11-17 16:03:53', '', '2023-12-04 14:33:29');
INSERT INTO `gen_table_column` VALUES (2450, 103, 's_way', '招标方式', 'int(11)', 'Long', 'sWay', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:03:53', '', '2023-12-04 14:33:29');
INSERT INTO `gen_table_column` VALUES (2451, 103, 's_must', '是否必招', 'int(11)', 'Long', 'sMust', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:03:53', '', '2023-12-04 14:33:29');
INSERT INTO `gen_table_column` VALUES (2452, 103, 's_sway', '资格审查方式', 'int(11)', 'Long', 'sSway', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:03:53', '', '2023-12-04 14:33:30');
INSERT INTO `gen_table_column` VALUES (2453, 103, 's_type', '业务类型', 'int(11)', 'Long', 'sType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 9, 'admin', '2023-11-17 16:03:53', '', '2023-12-04 14:33:30');
INSERT INTO `gen_table_column` VALUES (2454, 103, 's_budget', '项目预算', 'decimal(10,0)', 'Long', 'sBudget', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-17 16:03:54', '', '2023-12-04 14:33:30');
INSERT INTO `gen_table_column` VALUES (2455, 103, 's_unit', '招标单位', 'varchar(256)', 'String', 'sUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-17 16:03:54', '', '2023-12-04 14:33:30');
INSERT INTO `gen_table_column` VALUES (2456, 103, 's_person', '联系人', 'varchar(50)', 'String', 'sPerson', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-17 16:03:54', '', '2023-12-04 14:33:30');
INSERT INTO `gen_table_column` VALUES (2457, 103, 's_phone', '电话', 'varchar(20)', 'String', 'sPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-11-17 16:03:54', '', '2023-12-04 14:33:31');
INSERT INTO `gen_table_column` VALUES (2458, 103, 'email', '邮箱', 'varchar(30)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-11-17 16:03:54', '', '2023-12-04 14:33:31');
INSERT INTO `gen_table_column` VALUES (2459, 103, 's_address', '地址', 'varchar(256)', 'String', 'sAddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-17 16:03:55', '', '2023-12-04 14:33:31');
INSERT INTO `gen_table_column` VALUES (2460, 103, 'create_by', '创建人', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2023-11-17 16:03:55', '', '2023-12-04 14:33:31');
INSERT INTO `gen_table_column` VALUES (2461, 103, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2023-11-17 16:03:55', '', '2023-12-04 14:33:32');
INSERT INTO `gen_table_column` VALUES (2462, 103, 'update_by', '修改人', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 18, 'admin', '2023-11-17 16:03:55', '', '2023-12-04 14:33:32');
INSERT INTO `gen_table_column` VALUES (2463, 103, 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 19, 'admin', '2023-11-17 16:03:56', '', '2023-12-04 14:33:32');
INSERT INTO `gen_table_column` VALUES (2464, 103, 's_start_time', '公示开始时间', 'datetime', 'Date', 'sStartTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 20, 'admin', '2023-11-17 16:03:56', '', '2023-12-04 14:33:32');
INSERT INTO `gen_table_column` VALUES (2465, 103, 's_end_time', '公示结束时间', 'datetime', 'Date', 'sEndTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 21, 'admin', '2023-11-17 16:03:56', '', '2023-12-04 14:33:32');
INSERT INTO `gen_table_column` VALUES (2466, 103, 's_project_state', '项目状态   1：招标公告   2：立项中\r\n', 'int(11)', 'Long', 'sProjectState', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, 'admin', '2023-11-17 16:03:56', '', '2023-12-04 14:33:33');
INSERT INTO `gen_table_column` VALUES (2467, 103, 's_deadline', '报价截止时间', 'datetime', 'Date', 'sDeadline', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 23, 'admin', '2023-11-17 16:03:56', '', '2023-12-04 14:33:33');
INSERT INTO `gen_table_column` VALUES (2468, 104, 'gs_id', '公示ID', 'int(11)', 'Long', 'gsId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:03:57', '', '2023-12-19 14:51:50');
INSERT INTO `gen_table_column` VALUES (2469, 104, 'sid', '招标项目ID', 'int(11)', 'Long', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:03:57', '', '2023-12-19 14:51:50');
INSERT INTO `gen_table_column` VALUES (2471, 104, 'gs_state', '公示状态', 'int(11)', 'Long', 'gsState', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:03:57', '', '2023-12-19 14:51:51');
INSERT INTO `gen_table_column` VALUES (2473, 105, 'zr_id', '准入ID', 'int(11)', 'Long', 'zrId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:03:58', '', NULL);
INSERT INTO `gen_table_column` VALUES (2474, 105, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:03:59', '', NULL);
INSERT INTO `gen_table_column` VALUES (2475, 105, 'zr_bnumber', '业务编号', 'varchar(10)', 'String', 'zrBnumber', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:03:59', '', NULL);
INSERT INTO `gen_table_column` VALUES (2476, 105, 'zr_promoter', '发起人', 'varchar(50)', 'String', 'zrPromoter', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:03:59', '', NULL);
INSERT INTO `gen_table_column` VALUES (2477, 105, 'zr_time', '提交时间', 'datetime', 'Date', 'zrTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2023-11-17 16:03:59', '', NULL);
INSERT INTO `gen_table_column` VALUES (2478, 106, 'fj_id', '附件ID', 'int(11)', 'Long', 'fjId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:00', '', NULL);
INSERT INTO `gen_table_column` VALUES (2479, 106, 'fj_name', '名称', 'varchar(50)', 'String', 'fjName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-11-17 16:04:00', '', NULL);
INSERT INTO `gen_table_column` VALUES (2480, 106, 'fj_annex', '附件', 'varchar(50)', 'String', 'fjAnnex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:00', '', NULL);
INSERT INTO `gen_table_column` VALUES (2481, 107, 'yj_id', '业绩ID', 'int(11)', 'Long', 'yjId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (2482, 107, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (2483, 107, 'yj_unit', '采购单位', 'varchar(50)', 'String', 'yjUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (2484, 107, 'yj_partner', '合作方', 'varchar(50)', 'String', 'yjPartner', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (2485, 107, 'yj_date', '签订日期', 'date', 'Date', 'yjDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2023-11-17 16:04:02', '', NULL);
INSERT INTO `gen_table_column` VALUES (2486, 107, 'yj_rmb', '合同金额', 'decimal(10,0)', 'Long', 'yjRmb', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:02', '', NULL);
INSERT INTO `gen_table_column` VALUES (2487, 107, 'yj_bbr', '买方业务代表', 'varchar(50)', 'String', 'yjBbr', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:04:02', '', NULL);
INSERT INTO `gen_table_column` VALUES (2488, 107, 'yj_phone', '买方业务代表联系电话', 'varchar(20)', 'String', 'yjPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:04:02', '', NULL);
INSERT INTO `gen_table_column` VALUES (2489, 107, 'yj_scan_contract', '合同扫描件', 'varchar(50)', 'String', 'yjScanContract', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:04:02', '', NULL);
INSERT INTO `gen_table_column` VALUES (2490, 107, 'yj_content', '合同内容', 'text', 'String', 'yjContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'editor', '', 10, 'admin', '2023-11-17 16:04:02', '', NULL);
INSERT INTO `gen_table_column` VALUES (2510, 110, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:10', '', '2023-12-24 19:43:57');
INSERT INTO `gen_table_column` VALUES (2511, 110, 'eid', '合同ID', 'int(11)', 'Long', 'eid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:10', '', '2023-12-24 19:43:57');
INSERT INTO `gen_table_column` VALUES (2515, 110, 'e_hcode', '合同编号', 'varchar(10)', 'String', 'eHcode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:11', '', '2023-12-24 19:43:57');
INSERT INTO `gen_table_column` VALUES (2516, 110, 'e_hname', '合同名称', 'varchar(50)', 'String', 'eHname', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-11-17 16:04:12', '', '2023-12-24 19:43:57');
INSERT INTO `gen_table_column` VALUES (2517, 110, 'e_status', '合同状态', 'int(11)', 'Long', 'eStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 5, 'admin', '2023-11-17 16:04:12', '', '2023-12-24 19:43:58');
INSERT INTO `gen_table_column` VALUES (2518, 110, 'create_by', '创建人', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:12', '', '2023-12-24 19:43:58');
INSERT INTO `gen_table_column` VALUES (2519, 110, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-11-17 16:04:13', '', '2023-12-24 19:43:58');
INSERT INTO `gen_table_column` VALUES (2520, 110, 'e_delivery_time', '交付日期', 'datetime', 'Date', 'eDeliveryTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-11-17 16:04:13', '', '2023-12-24 19:43:58');
INSERT INTO `gen_table_column` VALUES (2521, 110, 'update_by', '修改人', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:04:13', '', '2023-12-24 19:43:58');
INSERT INTO `gen_table_column` VALUES (2522, 110, 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-11-17 16:04:14', '', '2023-12-24 19:43:59');
INSERT INTO `gen_table_column` VALUES (2523, 110, 'o_hstatus', '合同管理状态', 'int(11)', 'Long', 'oHstatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2023-11-17 16:04:14', '', '2023-12-24 19:43:59');
INSERT INTO `gen_table_column` VALUES (2524, 111, 'rid', '教育ID', 'int(11)', 'Long', 'rid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:15', '', NULL);
INSERT INTO `gen_table_column` VALUES (2525, 111, 'jid', '专家ID', 'int(11)', 'Long', 'jid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:15', '', NULL);
INSERT INTO `gen_table_column` VALUES (2526, 111, 'r_school', '毕业院校', 'varchar(50)', 'String', 'rSchool', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:15', '', NULL);
INSERT INTO `gen_table_column` VALUES (2527, 111, 'specialized', '专业', 'varchar(50)', 'String', 'specialized', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:16', '', NULL);
INSERT INTO `gen_table_column` VALUES (2528, 111, 'r_degree', '学历', 'varchar(50)', 'String', 'rDegree', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:04:16', '', NULL);
INSERT INTO `gen_table_column` VALUES (2529, 111, 'r_enrollment_time', '入学时间', 'date', 'Date', 'rEnrollmentTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-11-17 16:04:16', '', NULL);
INSERT INTO `gen_table_column` VALUES (2530, 111, 'r_graduation_time', '毕业时间', 'date', 'Date', 'rGraduationTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2023-11-17 16:04:17', '', NULL);
INSERT INTO `gen_table_column` VALUES (2531, 112, 'pid', '员工ID', 'int(11)', 'Long', 'pid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:18', '', NULL);
INSERT INTO `gen_table_column` VALUES (2532, 112, 'p_name', '用户名', 'varchar(30)', 'String', 'pName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-11-17 16:04:18', '', NULL);
INSERT INTO `gen_table_column` VALUES (2533, 112, 'p_password', '密码', 'varchar(30)', 'String', 'pPassword', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:18', '', NULL);
INSERT INTO `gen_table_column` VALUES (2534, 112, 'p_sex', '员工性别', 'int(11)', 'Long', 'pSex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2023-11-17 16:04:19', '', NULL);
INSERT INTO `gen_table_column` VALUES (2535, 112, 'p_born', '员工出生日期', 'date', 'Date', 'pBorn', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2023-11-17 16:04:19', '', NULL);
INSERT INTO `gen_table_column` VALUES (2536, 112, 'p_nick', '员工昵称', 'varchar(50)', 'String', 'pNick', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:19', '', NULL);
INSERT INTO `gen_table_column` VALUES (2537, 113, 'zz_id', '资质ID', 'int(11)', 'Long', 'zzId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (2538, 113, 'zz_cert_name', '证书名称', 'varchar(50)', 'String', 'zzCertName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-11-17 16:04:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (2539, 113, 'zz_cert_no', '证书编号', 'varchar(10)', 'String', 'zzCertNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (2540, 113, 'zz_unit', '发证单位', 'varchar(100)', 'String', 'zzUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (2541, 113, 'zz_date', '发证日期', 'date', 'Date', 'zzDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2023-11-17 16:04:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (2542, 113, 'zz_expiration_date', '有效期至', 'date', 'Date', 'zzExpirationDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-11-17 16:04:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (2543, 113, 'zz_scan', '扫描件', 'varchar(50)', 'String', 'zzScan', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:04:22', '', NULL);
INSERT INTO `gen_table_column` VALUES (2544, 113, 'zz_cert_content', '证书内容', 'text', 'String', 'zzCertContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'editor', '', 8, 'admin', '2023-11-17 16:04:23', '', NULL);
INSERT INTO `gen_table_column` VALUES (2545, 113, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:04:23', '', NULL);
INSERT INTO `gen_table_column` VALUES (2546, 114, 'qid', '任职经历ID', 'int(11)', 'Long', 'qid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:24', '', NULL);
INSERT INTO `gen_table_column` VALUES (2547, 114, 'unit', '单位名称', 'varchar(50)', 'String', 'unit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:24', '', NULL);
INSERT INTO `gen_table_column` VALUES (2548, 114, 'time', '入职时间', 'date', 'Date', 'time', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 3, 'admin', '2023-11-17 16:04:24', '', NULL);
INSERT INTO `gen_table_column` VALUES (2549, 114, 'q_office', '职务', 'varchar(50)', 'String', 'qOffice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:25', '', NULL);
INSERT INTO `gen_table_column` VALUES (2550, 114, 'specialized', '专业', 'varchar(50)', 'String', 'specialized', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:04:25', '', NULL);
INSERT INTO `gen_table_column` VALUES (2551, 115, 'jid', '专家ID', 'int(11)', 'Long', 'jid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:26', '', NULL);
INSERT INTO `gen_table_column` VALUES (2552, 115, 'qid', '任职经历ID', 'int(11)', 'Long', 'qid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:26', '', NULL);
INSERT INTO `gen_table_column` VALUES (2553, 115, 'pb_id', '评标ID', 'int(11)', 'Long', 'pbId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:26', '', NULL);
INSERT INTO `gen_table_column` VALUES (2554, 115, 'j_name', '姓名', 'varchar(50)', 'String', 'jName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-11-17 16:04:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (2555, 115, 'j_sex', '性别', 'int(11)', 'Long', 'jSex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2023-11-17 16:04:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (2556, 115, 'j_identity', '身份证号', 'varchar(30)', 'String', 'jIdentity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (2557, 115, 'j_phone', '手机号', 'varchar(20)', 'String', 'jPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:04:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (2558, 115, 'email', '邮箱', 'varchar(30)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:04:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (2559, 115, 'j_state', '健康状况', 'varchar(50)', 'String', 'jState', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:04:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (2560, 115, 'j_unit', '工作单位', 'varchar(256)', 'String', 'jUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-17 16:04:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (2561, 115, 'j_retire', '是否退休', 'int(11)', 'Long', 'jRetire', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-17 16:04:29', '', NULL);
INSERT INTO `gen_table_column` VALUES (2562, 115, 'j_address', '通讯地址', 'varchar(256)', 'String', 'jAddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-17 16:04:29', '', NULL);
INSERT INTO `gen_table_column` VALUES (2563, 115, 'j_card', '银行卡号', 'varchar(30)', 'String', 'jCard', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-11-17 16:04:29', '', NULL);
INSERT INTO `gen_table_column` VALUES (2564, 115, 'j_identity_photo', '身份证照', 'varchar(50)', 'String', 'jIdentityPhoto', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-11-17 16:04:30', '', NULL);
INSERT INTO `gen_table_column` VALUES (2565, 115, 'j_documents_photo', '证件照', 'varchar(50)', 'String', 'jDocumentsPhoto', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-17 16:04:30', '', NULL);
INSERT INTO `gen_table_column` VALUES (2566, 115, 'j_bank', '开户行', 'varchar(256)', 'String', 'jBank', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-11-17 16:04:30', '', NULL);
INSERT INTO `gen_table_column` VALUES (2567, 115, 'j_sh_state', '专家审核状态', 'int(11)', 'Long', 'jShState', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2023-11-17 16:04:30', '', NULL);
INSERT INTO `gen_table_column` VALUES (2568, 115, 'j_opinion', '意见', 'varchar(500)', 'String', 'jOpinion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 18, 'admin', '2023-11-17 16:04:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (2569, 116, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:31', '', NULL);
INSERT INTO `gen_table_column` VALUES (2570, 116, 'c_id', '财务状态ID', 'int(11)', 'Long', 'cId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (2571, 116, 'c_annual', '年度', 'int(11)', 'Long', 'cAnnual', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (2572, 116, 'c_net_profit', '净利润（万元）', 'int(11)', 'Long', 'cNetProfit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (2573, 116, 'c_lev', '资产负债率（%）', 'varchar(20)', 'String', 'cLev', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:04:32', '', NULL);
INSERT INTO `gen_table_column` VALUES (2574, 116, 'c_scan_far', '财务审计报告扫描件', 'int(11)', 'Long', 'cScanFar', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:33', '', NULL);
INSERT INTO `gen_table_column` VALUES (2575, 116, 'c_scan_aar', '附件审计报告', 'int(11)', 'Long', 'cScanAar', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:04:33', '', NULL);
INSERT INTO `gen_table_column` VALUES (2576, 116, 'c_scan_al', '资产负债扫描件', 'int(11)', 'Long', 'cScanAl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:04:33', '', NULL);
INSERT INTO `gen_table_column` VALUES (2577, 116, 'c_scan_is', '利润表扫描件', 'int(11)', 'Long', 'cScanIs', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:04:33', '', NULL);
INSERT INTO `gen_table_column` VALUES (2578, 116, 'c_scan_cfs', '现金流量表扫描件', 'int(11)', 'Long', 'cScanCfs', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-17 16:04:33', '', NULL);
INSERT INTO `gen_table_column` VALUES (2579, 117, 'mid', '职称ID', 'int(11)', 'Long', 'mid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:34', '', NULL);
INSERT INTO `gen_table_column` VALUES (2580, 117, 'jid', '专家ID', 'int(11)', 'Long', 'jid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:34', '', NULL);
INSERT INTO `gen_table_column` VALUES (2581, 117, 'm_name', '证书名称', 'varchar(50)', 'String', 'mName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-17 16:04:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (2582, 117, 'm_code', '证书编号', 'varchar(50)', 'String', 'mCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (2583, 117, 'm_job', '职称', 'varchar(50)', 'String', 'mJob', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:04:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (2584, 117, 'm_institution', '颁发机构', 'varchar(50)', 'String', 'mInstitution', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (2585, 117, 'm_get_date', '获得时间', 'date', 'Date', 'mGetDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2023-11-17 16:04:35', '', NULL);
INSERT INTO `gen_table_column` VALUES (2586, 118, 'nid', '职业ID', 'int(11)', 'Long', 'nid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:36', '', NULL);
INSERT INTO `gen_table_column` VALUES (2587, 118, 'jid', '专家ID', 'int(11)', 'Long', 'jid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:36', '', NULL);
INSERT INTO `gen_table_column` VALUES (2588, 118, 'n_certificate', '职业资格证书', 'varchar(256)', 'String', 'nCertificate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:36', '', NULL);
INSERT INTO `gen_table_column` VALUES (2589, 118, 'n_acquire_date', '取得时间', 'date', 'Date', 'nAcquireDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2023-11-17 16:04:36', '', NULL);
INSERT INTO `gen_table_column` VALUES (2590, 118, 'n_code', '职业证书编号', 'varchar(50)', 'String', 'nCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:04:37', '', NULL);
INSERT INTO `gen_table_column` VALUES (2591, 118, 'n_institution', '职业颁发机构', 'varchar(50)', 'String', 'nInstitution', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:37', '', NULL);
INSERT INTO `gen_table_column` VALUES (2592, 118, 'n_expiration_date', '有效期', 'date', 'Date', 'nExpirationDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2023-11-17 16:04:37', '', NULL);
INSERT INTO `gen_table_column` VALUES (2593, 119, 'yw_id', '经办人ID', 'int(11)', 'Long', 'ywId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:38', '', NULL);
INSERT INTO `gen_table_column` VALUES (2594, 119, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:38', '', NULL);
INSERT INTO `gen_table_column` VALUES (2595, 119, 'yw_name', '姓名', 'varchar(50)', 'String', 'ywName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-17 16:04:38', '', NULL);
INSERT INTO `gen_table_column` VALUES (2596, 119, 'yw_phone', '手机号', 'varchar(20)', 'String', 'ywPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:38', '', NULL);
INSERT INTO `gen_table_column` VALUES (2597, 119, 'yw_idcrad', '身份证号', 'varchar(30)', 'String', 'ywIdcrad', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:04:38', '', NULL);
INSERT INTO `gen_table_column` VALUES (2598, 119, 'yw_mailbox', '邮箱', 'varchar(30)', 'String', 'ywMailbox', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (2599, 119, 'yw_scan_idcard', '身份证扫描件', 'varchar(50)', 'String', 'ywScanIdcard', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:04:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (2600, 119, 'yw_scan_empower', '授权书扫描件', 'varchar(50)', 'String', 'ywScanEmpower', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:04:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (2601, 119, 'yw_Landline', '座机', 'varchar(20)', 'String', 'ywLandline', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:04:39', '', NULL);
INSERT INTO `gen_table_column` VALUES (2602, 119, 'yw_empower_content', '授权书内容', 'text', 'String', 'ywEmpowerContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'editor', '', 10, 'admin', '2023-11-17 16:04:40', '', NULL);
INSERT INTO `gen_table_column` VALUES (2603, 120, 'js_id', '技术人员ID', 'int(11)', 'Long', 'jsId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:40', '', NULL);
INSERT INTO `gen_table_column` VALUES (2604, 120, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (2605, 120, 'js_name', '姓名', 'varchar(50)', 'String', 'jsName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-17 16:04:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (2606, 120, 'js_position', '职位', 'varchar(50)', 'String', 'jsPosition', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (2607, 120, 'js_education', '学历', 'varchar(50)', 'String', 'jsEducation', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:04:41', '', NULL);
INSERT INTO `gen_table_column` VALUES (2608, 120, 'js_title', '职称', 'varchar(50)', 'String', 'jsTitle', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2609, 121, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2610, 121, 'zr_id', '准入ID', 'int(11)', 'Long', 'zrId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2611, 121, 'h_name', '供应商名称', 'varchar(50)', 'String', 'hName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-17 16:04:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2612, 121, 'h_credit_code', '统一社会信用代码', 'varchar(50)', 'String', 'hCreditCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2613, 121, 'h_incorporation', '单位注册地', 'varchar(50)', 'String', 'hIncorporation', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:04:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2614, 121, 'h_institution', '机构类型', 'varchar(50)', 'String', 'hInstitution', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:04:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2615, 121, 'h_quality', '企业性质', 'varchar(50)', 'String', 'hQuality', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:04:43', '', NULL);
INSERT INTO `gen_table_column` VALUES (2616, 121, 'h_start_time', '成立日期', 'datetime', 'Date', 'hStartTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-11-17 16:04:44', '', NULL);
INSERT INTO `gen_table_column` VALUES (2617, 121, 'h_juridical', '法人/负责人', 'varchar(50)', 'String', 'hJuridical', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:04:44', '', NULL);
INSERT INTO `gen_table_column` VALUES (2618, 121, 'h_juridical_identity', '法人/负责人身份证号', 'varchar(30)', 'String', 'hJuridicalIdentity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-17 16:04:44', '', NULL);
INSERT INTO `gen_table_column` VALUES (2619, 121, 'h_address', '单位联系地址', 'varchar(256)', 'String', 'hAddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-17 16:04:44', '', NULL);
INSERT INTO `gen_table_column` VALUES (2620, 121, 'h_range', '经营范围', 'varchar(256)', 'String', 'hRange', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-17 16:04:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2621, 121, 'h_desc', '公司简介', 'varchar(500)', 'String', 'hDesc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 13, 'admin', '2023-11-17 16:04:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2622, 121, 'h_copies', '营业执照扫描件', 'varchar(30)', 'String', 'hCopies', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-11-17 16:04:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2623, 121, 'h_juridical_copies', '法人/负责人身份证扫描件', 'varchar(30)', 'String', 'hJuridicalCopies', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-17 16:04:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2624, 121, 'h_expiration', '营业执照有效期', 'datetime', 'Date', 'hExpiration', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 16, 'admin', '2023-11-17 16:04:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (2625, 121, 'h_bank', '开户行', 'varchar(50)', 'String', 'hBank', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2023-11-17 16:04:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (2626, 121, 'h_account', '银行账号', 'varchar(30)', 'String', 'hAccount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2023-11-17 16:04:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (2627, 121, 'h_bank_address', '开户行地址', 'varchar(256)', 'String', 'hBankAddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-11-17 16:04:46', '', NULL);
INSERT INTO `gen_table_column` VALUES (2628, 121, 'h_sign_phone', '单位注册电话', 'varchar(20)', 'String', 'hSignPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2023-11-17 16:04:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (2629, 121, 'h_sign_address', '单位注册地址', 'varchar(256)', 'String', 'hSignAddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2023-11-17 16:04:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (2630, 121, 'h_capital', '注册资本', 'decimal(10,0)', 'Long', 'hCapital', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, 'admin', '2023-11-17 16:04:47', '', NULL);
INSERT INTO `gen_table_column` VALUES (2631, 121, 'h_actual_capital', '实缴资本', 'decimal(10,0)', 'Long', 'hActualCapital', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 23, 'admin', '2023-11-17 16:04:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2632, 121, 'h_prove', '开户许可证/基本户存款信息证明', 'varchar(30)', 'String', 'hProve', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 24, 'admin', '2023-11-17 16:04:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2633, 121, 'f_status', '供应商审核状态', 'int(11)', 'Long', 'fStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 25, 'admin', '2023-11-17 16:04:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2634, 121, 'f_opinion', '供应商审核意见', 'varchar(500)', 'String', 'fOpinion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 26, 'admin', '2023-11-17 16:04:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (2635, 121, 'f_state', '供应商状态', 'int(11)', 'Long', 'fState', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 27, 'admin', '2023-11-17 16:04:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (2636, 121, 'f_classify', '供应商分类', 'int(11)', 'Long', 'fClassify', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 28, 'admin', '2023-11-17 16:04:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (2637, 122, 'lid', '培训ID', 'int(11)', 'Long', 'lid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (2638, 122, 'jid', '专家ID', 'int(11)', 'Long', 'jid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (2639, 122, 'l_training', '培训机构', 'varchar(50)', 'String', 'lTraining', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (2640, 122, 'l_specialized', '培训专业', 'varchar(50)', 'String', 'lSpecialized', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (2641, 122, 'l_start_date', '培训开始时间', 'date', 'Date', 'lStartDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2023-11-17 16:04:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2642, 122, 'l_end_date', '培训结束时间', 'date', 'Date', 'lEndDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-11-17 16:04:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2650, 124, 'bj_id', '报价单号', 'int(11)', 'Long', 'bjId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (2651, 124, 'gf_id', '采购项目编号', 'varchar(20)', 'String', 'gfId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (2652, 124, 'bj_second', NULL, 'int(11)', 'Long', 'bjSecond', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (2653, 124, 'bj_total', NULL, 'decimal(10,0)', 'Long', 'bjTotal', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (2654, 125, 'xy_id', '采购计划序号', 'int(11)', 'Long', 'xyId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:04:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (2655, 125, 'gid', '非招标项目ID', 'int(11)', 'Long', 'gid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:04:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (2656, 125, 'sid', '招标项目ID', 'int(11)', 'Long', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:04:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (2657, 125, 'xy_code', '采购计划编码', 'varchar(10)', 'String', 'xyCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:04:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (2658, 125, 'xy_name', '采购计划名称', 'varchar(50)', 'String', 'xyName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2023-11-17 16:04:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (2659, 125, 'xy_type', '业务类型', 'int(11)', 'Long', 'xyType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 6, 'admin', '2023-11-17 16:04:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (2660, 125, 'dept', '创建部门', 'varchar(30)', 'String', 'dept', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:04:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (2661, 125, 'xy_person', '采购人', 'varchar(50)', 'String', 'xyPerson', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:04:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (2662, 125, 'td_phone', '联系方式', 'varchar(20)', 'String', 'tdPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:04:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (2663, 125, 'xy_count', '行项目数量', 'int(11)', 'Long', 'xyCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-17 16:04:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (2664, 125, 'xy_pcode', '产品编码', 'varchar(10)', 'String', 'xyPcode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-17 16:04:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (2665, 125, 'xy_pname', '产品名称', 'varchar(50)', 'String', 'xyPname', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 12, 'admin', '2023-11-17 16:04:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (2666, 125, 'xy_number', '数量', 'int(11)', 'Long', 'xyNumber', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-11-17 16:04:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (2667, 125, 'xy_ji_unit', '计量单位', 'varchar(10)', 'String', 'xyJiUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-11-17 16:04:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (2668, 125, 'xy_shui', '税率', 'decimal(10,0)', 'Long', 'xyShui', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-17 16:04:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (2669, 125, 'xy_price', '预算单价', 'decimal(10,0)', 'Long', 'xyPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-11-17 16:04:57', '', NULL);
INSERT INTO `gen_table_column` VALUES (2670, 125, 'xy_money', '预算金额', 'decimal(10,0)', 'Long', 'xyMoney', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2023-11-17 16:04:58', '', NULL);
INSERT INTO `gen_table_column` VALUES (2671, 125, 'xy_delivery_time', '交付时间', 'datetime', 'Date', 'xyDeliveryTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 18, 'admin', '2023-11-17 16:04:58', '', NULL);
INSERT INTO `gen_table_column` VALUES (2672, 125, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 19, 'admin', '2023-11-17 16:04:58', '', NULL);
INSERT INTO `gen_table_column` VALUES (2673, 125, 'create_by', '创建人', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 20, 'admin', '2023-11-17 16:04:58', '', NULL);
INSERT INTO `gen_table_column` VALUES (2674, 125, 'area', '交货地点', 'varchar(256)', 'String', 'area', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2023-11-17 16:04:59', '', NULL);
INSERT INTO `gen_table_column` VALUES (2675, 125, 'fj_annex', '附件', 'varchar(50)', 'String', 'fjAnnex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, 'admin', '2023-11-17 16:04:59', '', NULL);
INSERT INTO `gen_table_column` VALUES (2676, 125, 'update_by', '修改人', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 23, 'admin', '2023-11-17 16:04:59', '', NULL);
INSERT INTO `gen_table_column` VALUES (2677, 125, 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 24, 'admin', '2023-11-17 16:04:59', '', NULL);
INSERT INTO `gen_table_column` VALUES (2678, 126, 'gid', '非招标项目ID', 'int(11)', 'Long', 'gid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:05:00', '', '2023-12-28 05:05:03');
INSERT INTO `gen_table_column` VALUES (2679, 126, 'xy_id', '采购计划序号', 'int(11)', 'Long', 'xyId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:05:00', '', '2023-12-28 05:05:03');
INSERT INTO `gen_table_column` VALUES (2680, 126, 'g_code', '非招标项目编号', 'varchar(10)', 'String', 'gCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:05:00', '', '2023-12-28 05:05:03');
INSERT INTO `gen_table_column` VALUES (2681, 126, 'g_name', '非招标项目名称', 'varchar(50)', 'String', 'gName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-11-17 16:05:01', '', '2023-12-28 05:05:03');
INSERT INTO `gen_table_column` VALUES (2682, 126, 'g_is_public', '公开/邀请', 'int(11)', 'Long', 'gIsPublic', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:05:01', '', '2023-12-28 05:05:04');
INSERT INTO `gen_table_column` VALUES (2683, 126, 'g_spawn_time', '生成时间', 'datetime', 'Date', 'gSpawnTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-11-17 16:05:01', '', '2023-12-28 05:05:04');
INSERT INTO `gen_table_column` VALUES (2684, 126, 'g_deadline', '报价截止时间', 'datetime', 'Date', 'gDeadline', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-11-17 16:05:01', '', '2023-12-28 05:05:04');
INSERT INTO `gen_table_column` VALUES (2686, 126, 'g_count', '报价数量', 'int(11)', 'Long', 'gCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:05:02', '', '2023-12-28 05:05:04');
INSERT INTO `gen_table_column` VALUES (2687, 126, 'g_unit', '委托单位', 'varchar(50)', 'String', 'gUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-17 16:05:02', '', '2023-12-28 05:05:05');
INSERT INTO `gen_table_column` VALUES (2688, 126, 'g_rounds', '报价轮次', 'int(11)', 'Long', 'gRounds', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-17 16:05:02', '', '2023-12-28 05:05:05');
INSERT INTO `gen_table_column` VALUES (2689, 127, 'gf_id', '采购项目编号', 'varchar(20)', 'String', 'gfId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:05:02', '', NULL);
INSERT INTO `gen_table_column` VALUES (2690, 127, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:05:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (2691, 127, 'gf_name', '采购项目名称', 'varchar(30)', 'String', 'gfName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-17 16:05:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (2692, 127, 'gf_unit', '采购单位', 'varchar(20)', 'String', 'gfUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-17 16:05:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (2693, 127, 'gf_way', '采购方式', 'int(11)', 'Long', 'gfWay', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:05:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (2694, 127, 'gf_ln', '是否流标', 'int(11)', 'Long', 'gfLn', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:05:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (2695, 127, 'gf_tp', '终止采购', 'int(11)', 'Long', 'gfTp', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-17 16:05:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (2696, 127, 'gf_eba', '过期未接受', 'int(11)', 'Long', 'gfEba', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:05:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (2697, 127, 'gf_sb', '是否中标', 'int(11)', 'Long', 'gfSb', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:05:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (2698, 127, 'gf_qtime', '报价截至时间', 'date', 'Date', 'gfQtime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 10, 'admin', '2023-11-17 16:05:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (2699, 127, 'gf_qstate', NULL, 'int(11)', 'Long', 'gfQstate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-17 16:05:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (2725, 130, 'oid', '框架协议ID', 'int(11)', 'Long', 'oid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:05:15', '', '2023-12-21 20:36:54');
INSERT INTO `gen_table_column` VALUES (2726, 130, 'jh_id', '框架计划ID', 'int(11)', 'Long', 'jhId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:05:15', '', '2023-12-21 20:36:54');
INSERT INTO `gen_table_column` VALUES (2727, 130, 'o_code', '框架协议编号', 'varchar(10)', 'String', 'oCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:05:16', '', '2023-12-21 20:36:54');
INSERT INTO `gen_table_column` VALUES (2728, 130, 'o_name', '框架协议名称', 'varchar(50)', 'String', 'oName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-11-17 16:05:16', '', '2023-12-21 20:36:54');
INSERT INTO `gen_table_column` VALUES (2731, 130, 'create_by', '创建人', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2023-11-17 16:05:17', '', '2023-12-21 20:36:56');
INSERT INTO `gen_table_column` VALUES (2732, 130, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2023-11-17 16:05:17', '', '2023-12-21 20:36:56');
INSERT INTO `gen_table_column` VALUES (2733, 130, 'o_hstatus', '协议状态', 'int(11)', 'Long', 'oHstatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 13, 'admin', '2023-11-17 16:05:18', '', '2023-12-21 20:36:56');
INSERT INTO `gen_table_column` VALUES (2734, 131, 'jh_id', '框架计划ID', 'int(11)', 'Long', 'jhId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 16:05:18', '', '2023-12-26 00:13:48');
INSERT INTO `gen_table_column` VALUES (2735, 131, 'oid', '框架协议ID', 'int(11)', 'Long', 'oid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-17 16:05:19', '', '2023-12-26 00:13:48');
INSERT INTO `gen_table_column` VALUES (2736, 131, 'jh_code', '框架计划编码', 'varchar(10)', 'String', 'jhCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 16:05:19', '', '2023-12-26 00:13:48');
INSERT INTO `gen_table_column` VALUES (2737, 131, 'jh_name', '计划名称', 'varchar(50)', 'String', 'jhName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-11-17 16:05:19', '', '2023-12-26 00:13:49');
INSERT INTO `gen_table_column` VALUES (2738, 131, 'jh_yu', '计划预算', 'decimal(10,0)', 'Long', 'jhYu', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-17 16:05:19', '', '2023-12-26 00:13:49');
INSERT INTO `gen_table_column` VALUES (2739, 131, 'dept', '创建部门', 'varchar(30)', 'String', 'dept', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 16:05:20', '', '2023-12-26 00:13:49');
INSERT INTO `gen_table_column` VALUES (2740, 131, 'jh_status', '计划状态', 'int(11)', 'Long', 'jhStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 7, 'admin', '2023-11-17 16:05:20', '', '2023-12-26 00:13:49');
INSERT INTO `gen_table_column` VALUES (2741, 131, 'jh_person', '审批人', 'varchar(50)', 'String', 'jhPerson', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-17 16:05:20', '', '2023-12-26 00:13:49');
INSERT INTO `gen_table_column` VALUES (2742, 131, 'jh_pmethod', '采购方式', 'varchar(50)', 'String', 'jhPmethod', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 16:05:20', '', '2023-12-26 00:13:50');
INSERT INTO `gen_table_column` VALUES (2821, 103, 's_leader', '项目负责人', 'varchar(11)', 'String', 'sLeader', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, '', '2023-11-22 19:46:56', '', '2023-12-04 14:33:29');
INSERT INTO `gen_table_column` VALUES (2850, 139, 'rid', 'id', 'int(10)', 'Integer', 'rid', '1', '1', NULL, '1', NULL, '1', '1', 'EQ', 'input', '', 1, 'admin', '2023-11-24 12:42:30', '', '2023-11-24 13:03:30');
INSERT INTO `gen_table_column` VALUES (2851, 139, 'aid', '采购计划id', 'int(11)', 'Integer', 'aid', '0', '0', NULL, '1', '0', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-24 12:42:30', '', '2023-11-24 13:03:30');
INSERT INTO `gen_table_column` VALUES (2852, 139, 'node', '流程节点', 'varchar(10)', 'String', 'node', '0', '0', NULL, '1', '0', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-24 12:42:30', '', '2023-11-24 13:03:30');
INSERT INTO `gen_table_column` VALUES (2853, 139, 'processed_by', '处理人', 'varchar(11)', 'String', 'processedBy', '0', '0', NULL, '1', '0', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-24 12:42:31', '', '2023-11-24 13:03:31');
INSERT INTO `gen_table_column` VALUES (2854, 139, 'depnt', '所属部门', 'varchar(10)', 'String', 'depnt', '0', '0', NULL, '1', '0', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-24 12:42:31', '', '2023-11-24 13:03:31');
INSERT INTO `gen_table_column` VALUES (2855, 139, 'update_time', '处理时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '0', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-11-24 12:42:31', '', '2023-11-24 13:03:31');
INSERT INTO `gen_table_column` VALUES (2856, 139, 'opinion', '处理意见', 'varchar(10)', 'String', 'opinion', '0', '0', NULL, '1', '0', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-24 12:42:32', '', '2023-11-24 13:03:31');
INSERT INTO `gen_table_column` VALUES (2857, 139, 'opinion_details', '意见详情', 'varchar(30)', 'String', 'opinionDetails', '0', '0', NULL, '1', '0', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-24 12:42:32', '', '2023-11-24 13:03:32');
INSERT INTO `gen_table_column` VALUES (2887, 142, 'id', NULL, 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-27 15:33:23', '', NULL);
INSERT INTO `gen_table_column` VALUES (2888, 142, 'target_form', '目标表单', 'varchar(10)', 'String', 'targetForm', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-27 15:33:24', '', NULL);
INSERT INTO `gen_table_column` VALUES (2889, 142, 'prefix', '编号前缀', 'varchar(10)', 'String', 'prefix', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-27 15:33:24', '', NULL);
INSERT INTO `gen_table_column` VALUES (2890, 142, 'serial_number', '流水号', 'int(10)', 'Integer', 'serialNumber', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-27 15:33:24', '', NULL);
INSERT INTO `gen_table_column` VALUES (2891, 142, 'step', '步长', 'int(10)', 'Integer', 'step', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-27 15:33:25', '', NULL);
INSERT INTO `gen_table_column` VALUES (2892, 142, 'code_rules', '编号规则', 'varchar(30)', 'String', 'codeRules', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-27 15:33:25', '', NULL);
INSERT INTO `gen_table_column` VALUES (2893, 142, 'operator', '操作人', 'varchar(10)', 'String', 'operator', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-27 15:33:25', '', NULL);
INSERT INTO `gen_table_column` VALUES (2894, 142, 'update_time', '操作时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-11-27 15:33:26', '', NULL);
INSERT INTO `gen_table_column` VALUES (2895, 142, 'year', '年', 'varchar(10)', 'String', 'year', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-27 15:33:26', '', NULL);
INSERT INTO `gen_table_column` VALUES (2896, 142, 'mouth', '月', 'varchar(10)', 'String', 'mouth', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-27 15:33:26', '', NULL);
INSERT INTO `gen_table_column` VALUES (2897, 142, 'day', '日', 'varchar(10)', 'String', 'day', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-27 15:33:26', '', NULL);
INSERT INTO `gen_table_column` VALUES (2898, 142, 'hour', '时', 'varchar(10)', 'String', 'hour', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-27 15:33:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (2899, 142, 'minute', '分', 'varchar(10)', 'String', 'minute', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-11-27 15:33:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (2900, 142, 'second', '秒', 'varchar(10)', 'String', 'second', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-11-27 15:33:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (2901, 142, 'max_mantissa', '最大尾数', 'varchar(10)', 'String', 'maxMantissa', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-27 15:33:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (2910, 144, 'tid', '产品编码', 'varchar(30)', 'String', 'tid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-28 11:40:26', '', '2023-12-21 09:37:18');
INSERT INTO `gen_table_column` VALUES (2911, 144, 't_name', '产品名称', 'varchar(20)', 'String', 'tName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-11-28 11:40:27', '', '2023-12-21 09:37:19');
INSERT INTO `gen_table_column` VALUES (2912, 144, 'category', '类别', 'varchar(15)', 'String', 'category', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-28 11:40:27', '', '2023-12-21 09:37:19');
INSERT INTO `gen_table_column` VALUES (2913, 144, 't_unit', '计量单位', 'varchar(20)', 'String', 'tUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-28 11:40:27', '', '2023-12-21 09:37:19');
INSERT INTO `gen_table_column` VALUES (2914, 144, 'shui', '税率', 'decimal(10,0)', 'Long', 'shui', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-28 11:40:28', '', '2023-12-21 09:37:19');
INSERT INTO `gen_table_column` VALUES (2915, 144, 't_illustrate', '需求说明', 'varchar(500)', 'String', 'tIllustrate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2023-11-28 11:40:28', '', '2023-12-21 09:37:20');
INSERT INTO `gen_table_column` VALUES (2916, 144, 'du_code', '预算科目编号', 'varchar(10)', 'String', 'duCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-28 11:40:28', '', '2023-12-21 09:37:20');
INSERT INTO `gen_table_column` VALUES (2917, 144, 't_notes', '备注', 'varchar(500)', 'String', 'tNotes', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 8, 'admin', '2023-11-28 11:40:29', '', '2023-12-21 09:37:20');
INSERT INTO `gen_table_column` VALUES (2918, 144, 't_model', '规格型号', 'varchar(20)', 'String', 'tModel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-28 11:40:29', '', '2023-12-21 09:37:20');
INSERT INTO `gen_table_column` VALUES (2919, 145, 'vid', '行项目ID', 'int(11)', 'Integer', 'vid', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-28 11:40:30', '', '2023-11-28 11:53:48');
INSERT INTO `gen_table_column` VALUES (2920, 145, 'v_code', '行项目编号', 'varchar(10)', 'String', 'vCode', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 2, 'admin', '2023-11-28 11:40:30', '', '2023-11-28 11:53:48');
INSERT INTO `gen_table_column` VALUES (2921, 145, 'sid', '招标项目ID', 'int(11)', 'Integer', 'sid', '0', '0', NULL, '1', '0', '0', '0', 'EQ', 'input', '', 3, 'admin', '2023-11-28 11:40:30', '', '2023-11-28 11:53:48');
INSERT INTO `gen_table_column` VALUES (2922, 145, 'jh_id', '框架计划ID', 'int(11)', 'Integer', 'jhId', '0', '0', NULL, '1', '0', '0', '0', 'EQ', 'input', '', 4, 'admin', '2023-11-28 11:40:31', '', '2023-11-28 11:53:49');
INSERT INTO `gen_table_column` VALUES (2923, 145, 'aid', '采购计划ID', 'int(11)', 'Integer', 'aid', '0', '0', NULL, '1', '0', '0', '0', 'EQ', 'input', '', 5, 'admin', '2023-11-28 11:40:31', '', '2023-11-28 11:53:49');
INSERT INTO `gen_table_column` VALUES (2924, 145, 'tid', '设备编号', 'int(11)', 'Integer', 'tid', '0', '0', NULL, '1', '0', '0', '0', 'EQ', 'input', '', 6, 'admin', '2023-11-28 11:40:31', '', '2023-11-28 11:53:50');
INSERT INTO `gen_table_column` VALUES (2925, 145, 'gid', '非招标项目ID', 'int(11)', 'Integer', 'gid', '0', '0', NULL, '1', '0', '0', '0', 'EQ', 'input', '', 7, 'admin', '2023-11-28 11:40:32', '', '2023-11-28 11:53:50');
INSERT INTO `gen_table_column` VALUES (2926, 145, 'v_count', '数量', 'int(11)', 'Integer', 'vCount', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 8, 'admin', '2023-11-28 11:40:32', '', '2023-11-28 11:53:50');
INSERT INTO `gen_table_column` VALUES (2927, 145, 'v_person', '采购人', 'varchar(50)', 'String', 'vPerson', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 9, 'admin', '2023-11-28 11:40:32', '', '2023-11-28 11:53:51');
INSERT INTO `gen_table_column` VALUES (2928, 145, 'v_delivery_time', '交付时间', 'datetime', 'BigDecimal', 'vDeliveryTime', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'datetime', '', 10, 'admin', '2023-11-28 11:40:33', '', '2023-11-28 11:53:51');
INSERT INTO `gen_table_column` VALUES (2929, 145, 'v_delivery_area', '交付地点', 'varchar(256)', 'String', 'vDeliveryArea', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 11, 'admin', '2023-11-28 11:40:33', '', '2023-11-28 11:53:52');
INSERT INTO `gen_table_column` VALUES (2930, 145, 'v_illustrate', '需求说明', 'varchar(500)', 'String', 'vIllustrate', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'textarea', '', 12, 'admin', '2023-11-28 11:40:33', '', '2023-11-28 11:53:52');
INSERT INTO `gen_table_column` VALUES (2931, 145, 'procurement_method', '采购方式', 'int(10)', 'Integer', 'procurementMethod', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 13, 'admin', '2023-11-28 11:40:34', '', '2023-11-28 11:53:53');
INSERT INTO `gen_table_column` VALUES (2932, 146, 'aid', '采购计划ID', 'int(11)', 'Long', 'aid', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-28 11:40:34', '', '2023-12-26 00:15:51');
INSERT INTO `gen_table_column` VALUES (2933, 146, 'a_code', '采购计划编号', 'varchar(30)', 'String', 'aCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-28 11:40:35', '', '2023-12-26 00:15:52');
INSERT INTO `gen_table_column` VALUES (2934, 146, 'a_name', '计划名称', 'varchar(50)', 'String', 'aName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-28 11:40:35', '', '2023-12-26 00:15:52');
INSERT INTO `gen_table_column` VALUES (2935, 146, 'create_by', '创建人', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-11-28 11:40:35', '', '2023-12-26 00:15:52');
INSERT INTO `gen_table_column` VALUES (2936, 146, 'a_create_dept', '创建部门', 'varchar(30)', 'String', 'aCreateDept', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-28 11:40:36', '', '2023-12-26 00:15:52');
INSERT INTO `gen_table_column` VALUES (2937, 146, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-11-28 11:40:36', '', '2023-12-26 00:15:52');
INSERT INTO `gen_table_column` VALUES (2938, 146, 'update_by', '修改人', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-11-28 11:40:36', '', '2023-12-26 00:15:53');
INSERT INTO `gen_table_column` VALUES (2939, 146, 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-11-28 11:40:37', '', '2023-12-26 00:15:53');
INSERT INTO `gen_table_column` VALUES (2940, 146, 'fj_annex', '附件路径', 'varchar(50)', 'String', 'fjAnnex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 9, 'admin', '2023-11-28 11:40:37', '', '2023-12-26 00:15:53');
INSERT INTO `gen_table_column` VALUES (2941, 146, 'a_opinion', '采购计划审核意见', 'varchar(500)', 'String', 'aOpinion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 10, 'admin', '2023-11-28 11:40:37', '', '2023-12-26 00:15:53');
INSERT INTO `gen_table_column` VALUES (2942, 146, 'a_project_count', '行项目数量', 'int(11)', 'Long', 'aProjectCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-28 11:40:38', '', '2023-12-26 00:15:53');
INSERT INTO `gen_table_column` VALUES (2943, 146, 'a_btype', '采购业务类型名称', 'varchar(50)', 'Long', 'aBtype', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 12, 'admin', '2023-11-28 11:40:38', '', '2023-12-26 00:15:54');
INSERT INTO `gen_table_column` VALUES (2944, 146, 'a_astate', '采购审批状态', 'int(11)', 'Long', 'aAstate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-11-28 11:40:38', '', '2023-12-26 00:15:54');
INSERT INTO `gen_table_column` VALUES (2945, 147, 'du_id', '预算ID', 'varchar(11)', 'String', 'duId', '1', '0', NULL, '1', NULL, '0', NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-28 11:43:17', '', '2023-11-28 11:44:19');
INSERT INTO `gen_table_column` VALUES (2946, 147, 'vid', '行项目id', 'int(11)', 'Integer', 'vid', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 2, 'admin', '2023-11-28 11:43:17', '', '2023-11-28 11:44:19');
INSERT INTO `gen_table_column` VALUES (2947, 147, 'aid', '采购计划ID', 'int(11)', 'Integer', 'aid', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 3, 'admin', '2023-11-28 11:43:17', '', '2023-11-28 11:44:20');
INSERT INTO `gen_table_column` VALUES (2948, 147, 'du_dept', '部门名称', 'varchar(50)', 'String', 'duDept', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 4, 'admin', '2023-11-28 11:43:18', '', '2023-11-28 11:44:20');
INSERT INTO `gen_table_column` VALUES (2949, 147, 'du_code', '预算科目编号', 'varchar(10)', 'String', 'duCode', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 5, 'admin', '2023-11-28 11:43:18', '', '2023-11-28 11:44:20');
INSERT INTO `gen_table_column` VALUES (2950, 147, 'du_name', '预算科目名称', 'varchar(50)', 'String', 'duName', '0', '0', NULL, '1', '0', '1', '0', 'LIKE', 'input', '', 6, 'admin', '2023-11-28 11:43:18', '', '2023-11-28 11:44:21');
INSERT INTO `gen_table_column` VALUES (2951, 147, 'du_total', '总金额', 'decimal(10,0)', 'BigDecimal', 'duTotal', '0', '0', NULL, '1', '0', '1', '0', 'EQ', 'input', '', 7, 'admin', '2023-11-28 11:43:19', '', '2023-11-28 11:44:21');
INSERT INTO `gen_table_column` VALUES (2952, 148, 'rm_id', '风险监控ID', 'int(11)', 'Long', 'rmId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-28 17:47:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2953, 148, 'eid', '合同ID', 'int(11)', 'Long', 'eid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-28 17:47:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (2954, 148, 'rm_illustrate', '风险说明', 'varchar(255)', 'String', 'rmIllustrate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-28 17:47:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (2955, 148, 'rm_happen_date', '发生日期', 'date', 'Date', 'rmHappenDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2023-11-28 17:47:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (2956, 148, 'rm_item', '风险项', 'int(11)', 'Long', 'rmItem', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-28 17:47:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (2957, 148, 'rm_status', '风险处理状态', 'int(11)', 'Long', 'rmStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 6, 'admin', '2023-11-28 17:47:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (2958, 148, 'rm_handing_date', '处理日期', 'date', 'Date', 'rmHandingDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2023-11-28 17:47:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (2959, 148, 'rm_handing_person', '处理人', 'varchar(50)', 'String', 'rmHandingPerson', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-28 17:47:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (2960, 148, 'rm_handing_result', '处理结果', 'varchar(500)', 'String', 'rmHandingResult', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 9, 'admin', '2023-11-28 17:47:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (2962, 110, 'e_type', '合同类型', 'int(11)', 'String', 'eType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 12, '', '2023-11-30 14:08:54', '', '2023-12-24 19:43:59');
INSERT INTO `gen_table_column` VALUES (2963, 110, 'e_con', '密级', 'int(11)', 'Long', 'eCon', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, '', '2023-11-30 14:08:54', '', '2023-12-24 19:43:59');
INSERT INTO `gen_table_column` VALUES (2964, 110, 'e_startDate', '开始时间', 'date', 'Date', 'eStartdate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 14, '', '2023-11-30 14:08:54', '', '2023-12-24 19:43:59');
INSERT INTO `gen_table_column` VALUES (2965, 110, 'e_endDate', '结束时间', 'date', 'Date', 'eEnddate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 15, '', '2023-11-30 14:08:54', '', '2023-12-24 19:44:00');
INSERT INTO `gen_table_column` VALUES (2966, 110, 'e_amount', '合同金额', 'decimal(10,0)', 'BigDecimal', 'eAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, '', '2023-11-30 14:08:54', '', '2023-12-24 19:44:00');
INSERT INTO `gen_table_column` VALUES (2967, 110, 'e_description', '合同情况说明', 'varchar(256)', 'String', 'eDescription', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, '', '2023-11-30 14:08:55', '', '2023-12-24 19:44:00');
INSERT INTO `gen_table_column` VALUES (2968, 110, 'e_opinion', '意见', 'varchar(256)', 'String', 'eOpinion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, '', '2023-11-30 14:08:55', '', '2023-12-24 19:44:00');
INSERT INTO `gen_table_column` VALUES (2970, 149, 'pay_id', '支付约定ID', 'int(11)', 'Long', 'payId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-30 14:45:31', '', '2023-11-30 15:13:26');
INSERT INTO `gen_table_column` VALUES (2971, 149, 'eid', '合同ID', 'int(11)', 'Long', 'eid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-30 14:45:31', '', '2023-11-30 15:13:26');
INSERT INTO `gen_table_column` VALUES (2972, 149, 'payContent', '款项内容', 'varchar(256)', 'String', 'payContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'editor', '', 3, 'admin', '2023-11-30 14:45:31', '', '2023-11-30 15:13:26');
INSERT INTO `gen_table_column` VALUES (2973, 149, 'payDate', '付款日期', 'date', 'Date', 'payDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2023-11-30 14:45:32', '', '2023-11-30 15:13:26');
INSERT INTO `gen_table_column` VALUES (2974, 149, 'payer', '付款单位', 'varchar(100)', 'String', 'payer', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-30 14:45:32', '', '2023-11-30 15:13:26');
INSERT INTO `gen_table_column` VALUES (2975, 149, 'hid', '收款合同方ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-30 14:45:32', '', '2023-11-30 15:13:27');
INSERT INTO `gen_table_column` VALUES (2976, 149, 'h_name', '收款合同方', 'varchar(100)', 'String', 'hName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 7, 'admin', '2023-11-30 14:45:32', '', '2023-11-30 15:13:27');
INSERT INTO `gen_table_column` VALUES (2977, 149, 'payTerms', '付款条件', 'varchar(256)', 'String', 'payTerms', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-30 14:45:32', '', '2023-11-30 15:13:27');
INSERT INTO `gen_table_column` VALUES (2978, 149, 'payAmount', '付款金额', 'decimal(10,0)', 'Long', 'payAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-30 14:45:33', '', '2023-11-30 15:13:27');
INSERT INTO `gen_table_column` VALUES (2979, 149, 'debty', '违约责任', 'varchar(256)', 'String', 'debty', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-30 14:45:33', '', '2023-11-30 15:13:27');
INSERT INTO `gen_table_column` VALUES (2980, 150, 'gn_id', '合同签署ID', 'int(11)', 'Long', 'gnId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 1, 'admin', '2023-11-30 14:45:33', '', '2023-12-20 17:38:12');
INSERT INTO `gen_table_column` VALUES (2981, 150, 'gn_signatoryCount', '签署方数', 'int(11)', 'Long', 'gnSignatorycount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-30 14:45:34', '', '2023-12-20 17:38:12');
INSERT INTO `gen_table_column` VALUES (2982, 150, 'gn_sub', '我方主体', 'varchar(100)', 'String', 'gnSub', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-30 14:45:34', '', '2023-12-20 17:38:12');
INSERT INTO `gen_table_column` VALUES (2983, 150, 'gn_pbId', '乙方供应商ID', 'int(11)', 'Long', 'gnPbid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-30 14:45:34', '', '2023-12-20 17:38:13');
INSERT INTO `gen_table_column` VALUES (2984, 150, 'gn_pbName', '乙方名称', 'varchar(100)', 'String', 'gnPbname', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2023-11-30 14:45:34', '', '2023-12-20 17:38:13');
INSERT INTO `gen_table_column` VALUES (2985, 150, 'gn_pbAddress', '乙方地址', 'varchar(255)', 'String', 'gnPbaddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-30 14:45:34', '', '2023-12-20 17:38:13');
INSERT INTO `gen_table_column` VALUES (2986, 150, 'gn_pbContact', '乙方联系人', 'varchar(50)', 'String', 'gnPbcontact', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-30 14:45:35', '', '2023-12-20 17:38:13');
INSERT INTO `gen_table_column` VALUES (2987, 150, 'gn_pbCif', '乙方联系方式', 'varchar(20)', 'String', 'gnPbcif', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-30 14:45:35', '', '2023-12-20 17:38:14');
INSERT INTO `gen_table_column` VALUES (2988, 150, 'gn_pbBank', '乙方开户行', 'varchar(100)', 'String', 'gnPbbank', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-30 14:45:35', '', '2023-12-20 17:38:14');
INSERT INTO `gen_table_column` VALUES (2989, 150, 'gn_pbAccount', '乙方开户账号', 'varchar(20)', 'String', 'gnPbaccount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-30 14:45:35', '', '2023-12-20 17:38:14');
INSERT INTO `gen_table_column` VALUES (2990, 150, 'gn_pbAmount', '合同方金额', 'decimal(10,0)', 'Long', 'gnPbamount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-30 14:45:35', '', '2023-12-20 17:38:14');
INSERT INTO `gen_table_column` VALUES (2991, 150, 'gn_pbCurrency', '币别', 'varchar(20)', 'String', 'gnPbcurrency', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-11-30 14:45:36', '', '2023-12-20 17:38:14');
INSERT INTO `gen_table_column` VALUES (2992, 150, 'gn_pbPayment', '已支付金额', 'decimal(10,0)', 'Long', 'gnPbpayment', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-11-30 14:45:36', '', '2023-12-20 17:38:15');
INSERT INTO `gen_table_column` VALUES (2993, 150, 'gn_pbFixedprice', '锁定金额', 'decimal(10,0)', 'Long', 'gnPbfixedprice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-30 14:45:36', '', '2023-12-20 17:38:15');
INSERT INTO `gen_table_column` VALUES (2994, 150, 'gn_pbBalance', '剩余金额', 'decimal(10,0)', 'Long', 'gnPbbalance', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-11-30 14:45:36', '', '2023-12-20 17:38:15');
INSERT INTO `gen_table_column` VALUES (2996, 150, 'gn_pcName', '丙方名称', 'varchar(100)', 'String', 'gnPcname', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 17, 'admin', '2023-11-30 14:45:37', '', '2023-12-20 17:38:15');
INSERT INTO `gen_table_column` VALUES (2997, 150, 'gn_pcAddress', '丙方地址', 'varchar(255)', 'String', 'gnPcaddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2023-11-30 14:45:37', '', '2023-12-20 17:38:15');
INSERT INTO `gen_table_column` VALUES (2998, 150, 'gn_pcContact', '丙方联系人', 'varchar(50)', 'String', 'gnPccontact', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-11-30 14:45:37', '', '2023-12-20 17:38:16');
INSERT INTO `gen_table_column` VALUES (2999, 150, 'gn_pcCif', '丙方联系方式', 'varchar(20)', 'String', 'gnPccif', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2023-11-30 14:45:37', '', '2023-12-20 17:38:16');
INSERT INTO `gen_table_column` VALUES (3000, 150, 'gn_pcBank', '丙方开户行', 'varchar(255)', 'String', 'gnPcbank', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2023-11-30 14:45:38', '', '2023-12-20 17:38:16');
INSERT INTO `gen_table_column` VALUES (3001, 150, 'gn_pcAccount', '丙方开户账号', 'varchar(20)', 'String', 'gnPcaccount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, 'admin', '2023-11-30 14:45:38', '', '2023-12-20 17:38:16');
INSERT INTO `gen_table_column` VALUES (3002, 150, 'gn_pcAmount', '合同方金额', 'decimal(10,0)', 'Long', 'gnPcamount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 23, 'admin', '2023-11-30 14:45:38', '', '2023-12-20 17:38:17');
INSERT INTO `gen_table_column` VALUES (3003, 150, 'gn_pcCurrency', '币别', 'varchar(20)', 'String', 'gnPccurrency', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 24, 'admin', '2023-11-30 14:45:38', '', '2023-12-20 17:38:17');
INSERT INTO `gen_table_column` VALUES (3004, 150, 'gn_pcPayment', '已支付金额', 'decimal(10,0)', 'Long', 'gnPcpayment', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 25, 'admin', '2023-11-30 14:45:39', '', '2023-12-20 17:38:17');
INSERT INTO `gen_table_column` VALUES (3005, 150, 'gn_pcFixedprice', '锁定金额', 'decimal(10,0)', 'Long', 'gnPcfixedprice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 26, 'admin', '2023-11-30 14:45:39', '', '2023-12-20 17:38:17');
INSERT INTO `gen_table_column` VALUES (3006, 150, 'gn_pcBalance', '剩余金额', 'decimal(10,0)', 'Long', 'gnPcbalance', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 27, 'admin', '2023-11-30 14:45:39', '', '2023-12-20 17:38:17');
INSERT INTO `gen_table_column` VALUES (3007, 110, 'e_image', '合同影像', 'varchar(256)', 'String', 'eImage', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'imageUpload', '', 19, '', '2023-11-30 15:18:04', '', '2023-12-24 19:44:00');
INSERT INTO `gen_table_column` VALUES (3008, 110, 'e_documents', '合同文件', 'varchar(255)', 'String', 'eDocuments', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, '', '2023-11-30 15:18:04', '', '2023-12-24 19:44:01');
INSERT INTO `gen_table_column` VALUES (3009, 151, 'xid', '抽取申请ID', 'int(11)', 'Long', 'xid', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-12-04 11:18:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (3010, 151, 'pb_id', '评标ID', 'int(11)', 'Long', 'pbId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-12-04 11:18:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (3011, 151, 'x_code', '申请编号', 'varchar(10)', 'String', 'xCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-12-04 11:18:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (3012, 151, 'x_name', '招标项目名称', 'varchar(50)', 'String', 'xName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-12-04 11:18:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (3013, 151, 'x_way', '专家确定方式', 'int(11)', 'Long', 'xWay', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-12-04 11:18:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (3014, 151, 'x_type', '资格审查类型', 'int(11)', 'Long', 'xType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 6, 'admin', '2023-12-04 11:18:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (3015, 151, 'x_start_time', '评标开始时间', 'datetime', 'Date', 'xStartTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2023-12-04 11:18:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (3016, 151, 'x_end_time', '评标结束时间', 'datetime', 'Date', 'xEndTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-12-04 11:18:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (3017, 151, 'x_count', '专家人数', 'int(11)', 'Long', 'xCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-12-04 11:18:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (3018, 151, 'x_dai_count', '采购方代表人数', 'int(11)', 'Long', 'xDaiCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-12-04 11:18:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (3019, 151, 'x_area', '评标地点', 'varchar(256)', 'String', 'xArea', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-12-04 11:18:50', '', NULL);
INSERT INTO `gen_table_column` VALUES (3020, 152, 'pb_id', '评标ID', 'int(11)', 'Long', 'pbId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-12-04 11:18:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (3021, 152, 'xid', '抽取申请ID', 'int(11)', 'Long', 'xid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-12-04 11:18:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (3022, 152, 'pb_name', '姓名', 'varchar(50)', 'String', 'pbName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-12-04 11:18:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (3023, 152, 'pb_sex', '性别', 'char(1)', 'String', 'pbSex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2023-12-04 11:18:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (3024, 152, 'pb_idcard', '身份证号', 'varchar(30)', 'String', 'pbIdcard', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-12-04 11:18:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (3025, 152, 'pb_phone', '联系方式', 'varchar(20)', 'String', 'pbPhone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-12-04 11:18:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (3026, 152, 'pb_genre', '专家类型', 'varchar(30)', 'String', 'pbGenre', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-12-04 11:18:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (3027, 152, 'pb_types', '专家类别', 'varchar(30)', 'String', 'pbTypes', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-12-04 11:18:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (3028, 152, 'jid', '专家ID', 'int(11)', 'Long', 'jid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-12-04 11:18:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (3029, 103, 'eid', '合同ID', 'int(11)', 'Long', 'eid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 24, '', '2023-12-04 11:39:30', '', '2023-12-04 14:33:33');
INSERT INTO `gen_table_column` VALUES (3030, 153, 'in_id', '标的ID', 'int(11)', 'Long', 'inId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-12-05 16:00:37', '', '2023-12-25 17:34:51');
INSERT INTO `gen_table_column` VALUES (3031, 153, 'tid', '产品编码', 'int(60)', 'String', 'tid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-12-05 16:00:37', '', '2023-12-25 17:34:51');
INSERT INTO `gen_table_column` VALUES (3032, 153, 'eid', '合同ID', 'decimal(10,0)', 'Long', 'eid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-12-05 16:00:37', '', '2023-12-25 17:34:51');
INSERT INTO `gen_table_column` VALUES (3033, 153, 'in_name', '产品名称', 'varchar(60)', 'String', 'inName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2023-12-05 16:00:37', '', '2023-12-25 17:34:52');
INSERT INTO `gen_table_column` VALUES (3035, 153, 'in_model', '规格型号', 'varchar(20)', 'String', 'inModel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-12-05 16:00:38', '', '2023-12-25 17:34:52');
INSERT INTO `gen_table_column` VALUES (3036, 153, 'in_unit', '单位', 'char(5)', 'String', 'inUnit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-12-05 16:00:38', '', '2023-12-25 17:34:52');
INSERT INTO `gen_table_column` VALUES (3037, 153, 'in_vat', '含税单价', 'decimal(10,2)', 'BigDecimal', 'inVat', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-12-05 16:00:39', '', '2023-12-25 17:34:53');
INSERT INTO `gen_table_column` VALUES (3038, 153, 'in_count', '数量', 'int(11)', 'Long', 'inCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-12-05 16:00:39', '', '2023-12-25 17:34:53');
INSERT INTO `gen_table_column` VALUES (3039, 153, 'in_subtotal', '小计', 'decimal(10,2)', 'BigDecimal', 'inSubtotal', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-12-05 16:00:39', '', '2023-12-25 17:34:53');
INSERT INTO `gen_table_column` VALUES (3041, 144, 't_price', '单价', 'decimal(10,2)', 'BigDecimal', 'tPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, '', '2023-12-05 20:22:07', '', '2023-12-21 09:37:20');
INSERT INTO `gen_table_column` VALUES (3042, 126, 'g_tenderType', '业务类型', 'int(11)', 'Long', 'gTendertype', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 12, '', '2023-12-11 20:17:57', '', '2023-12-28 05:05:05');
INSERT INTO `gen_table_column` VALUES (3043, 131, 'jh_founder', '创建人', 'varchar(255)', 'String', 'jhFounder', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, '', '2023-12-13 09:51:13', '', '2023-12-26 00:13:50');
INSERT INTO `gen_table_column` VALUES (3044, 131, 'hid', '供应商id', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, '', '2023-12-13 09:51:13', '', '2023-12-26 00:13:50');
INSERT INTO `gen_table_column` VALUES (3045, 131, 'business_type', '业务类型', 'int(11)', 'Long', 'businessType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 12, '', '2023-12-13 09:51:14', '', '2023-12-26 00:13:50');
INSERT INTO `gen_table_column` VALUES (3046, 154, 'an_id', '附件ID', 'int(11)', 'Integer', 'anId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-12-13 10:40:34', '', '2023-12-13 10:45:33');
INSERT INTO `gen_table_column` VALUES (3047, 154, 'aid', '采购计划id', 'int(11)', 'Integer', 'aid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-12-13 10:40:34', '', '2023-12-13 10:45:33');
INSERT INTO `gen_table_column` VALUES (3048, 154, 'jhid', '框架计划id', 'int(11)', 'Integer', 'jhid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-12-13 10:40:34', '', '2023-12-13 10:45:33');
INSERT INTO `gen_table_column` VALUES (3049, 154, 'eid', '合同ID', 'int(11)', 'Integer', 'eid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-12-13 10:40:34', '', '2023-12-13 10:45:34');
INSERT INTO `gen_table_column` VALUES (3050, 154, 'an_name', '附件名称', 'varchar(50)', 'String', 'anName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2023-12-13 10:40:34', '', '2023-12-13 10:45:34');
INSERT INTO `gen_table_column` VALUES (3051, 154, 'an_type', '业务类型', 'int(11)', 'Integer', 'anType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 6, 'admin', '2023-12-13 10:40:35', '', '2023-12-13 10:45:34');
INSERT INTO `gen_table_column` VALUES (3052, 154, 'an_dan', '业务单据号', 'varchar(20)', 'String', 'anDan', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-12-13 10:40:35', '', '2023-12-13 10:45:34');
INSERT INTO `gen_table_column` VALUES (3053, 154, 'an_url', 'URL', 'varchar(80)', 'String', 'anUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-12-13 10:40:35', '', '2023-12-13 10:45:34');
INSERT INTO `gen_table_column` VALUES (3054, 154, 'an_size', '文件大小', 'decimal(10,0)', 'BigDecimal', 'anSize', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-12-13 10:40:35', '', '2023-12-13 10:45:35');
INSERT INTO `gen_table_column` VALUES (3055, 154, 'an_upload_time', '上传时间', 'datetime', 'Date', 'anUploadTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 10, 'admin', '2023-12-13 10:40:36', '', '2023-12-13 10:45:35');
INSERT INTO `gen_table_column` VALUES (3056, 130, 'o_subject', '签署主体', 'varchar(50)', 'String', 'oSubject', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, '', '2023-12-15 14:42:10', '', '2023-12-21 20:36:54');
INSERT INTO `gen_table_column` VALUES (3057, 130, 'hid', '相对方ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, '', '2023-12-15 14:42:10', '', '2023-12-21 20:36:55');
INSERT INTO `gen_table_column` VALUES (3058, 130, 'h_name', '相对方名称', 'varchar(50)', 'String', 'hName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 7, '', '2023-12-15 14:42:10', '', '2023-12-21 20:36:55');
INSERT INTO `gen_table_column` VALUES (3059, 130, 'o_startDate', '签署时间', 'date', 'Date', 'oStartdate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, '', '2023-12-15 14:42:11', '', '2023-12-21 20:36:55');
INSERT INTO `gen_table_column` VALUES (3060, 130, 'o_endDate', '失效时间', 'date', 'Date', 'oEnddate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 9, '', '2023-12-15 14:42:11', '', '2023-12-21 20:36:55');
INSERT INTO `gen_table_column` VALUES (3061, 130, 'o_file', '协议文件', 'varchar(500)', 'String', 'oFile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'fileUpload', '', 10, '', '2023-12-15 14:42:11', '', '2023-12-21 20:36:55');
INSERT INTO `gen_table_column` VALUES (3062, 153, 'oid', '框架协议ID', 'int(11)', 'Long', 'oid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, '', '2023-12-15 14:45:43', '', '2023-12-25 17:34:52');
INSERT INTO `gen_table_column` VALUES (3063, 130, 'o_type', '协议类型', 'varchar(50)', 'String', 'oType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 14, '', '2023-12-18 03:33:37', '', '2023-12-21 20:36:56');
INSERT INTO `gen_table_column` VALUES (3064, 130, 'o_describe', '说明', 'varchar(500)', 'String', 'oDescribe', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 15, '', '2023-12-18 03:45:33', '', '2023-12-21 20:36:56');
INSERT INTO `gen_table_column` VALUES (3065, 130, 'o_opinion', '审核意见', 'varchar(500)', 'String', 'oOpinion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 16, '', '2023-12-18 03:45:33', '', '2023-12-21 20:36:57');
INSERT INTO `gen_table_column` VALUES (3066, 130, 'o_totalPrice', '总价', 'decimal(10,2)', 'BigDecimal', 'oTotalprice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, '', '2023-12-18 15:03:02', '', '2023-12-21 20:36:57');
INSERT INTO `gen_table_column` VALUES (3067, 104, 'hid', '供应商ID', 'int(11)', 'Long', 'hid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, '', '2023-12-19 14:51:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (3068, 104, 'h_name', '供应商名称', 'varchar(80)', 'String', 'hName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, '', '2023-12-19 14:51:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (3069, 104, 'gs_res_date', '公示发布时间', 'date', 'Date', 'gsResDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, '', '2023-12-19 14:51:51', '', NULL);
INSERT INTO `gen_table_column` VALUES (3070, 150, 'eid', '合同ID', 'int(11)', 'Long', 'eid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, '', '2023-12-20 17:38:12', '', NULL);
INSERT INTO `gen_table_column` VALUES (3071, 110, 'e_cancel', '是否作废', 'int(11)', 'Long', 'eCancel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, '', '2023-12-24 19:44:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (3072, 153, 'gid', '非招标ID', 'int(11)', 'Long', 'gid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, '', '2023-12-25 17:30:58', '', '2023-12-25 17:34:51');
INSERT INTO `gen_table_column` VALUES (3073, 146, 'fj_file_name', '附件名称', 'varchar(255)', 'String', 'fjFileName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 14, '', '2023-12-26 00:15:06', '', '2023-12-26 00:15:54');
INSERT INTO `gen_table_column` VALUES (3074, 126, 'g_timeon', '报价开始时间', 'datetime', 'Date', 'gTimeon', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, '', '2023-12-28 05:05:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (3075, 126, 'g_company', '公司', 'varchar(50)', 'String', 'gCompany', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, '', '2023-12-28 05:05:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (3076, 126, 'g_notes', '备注', 'varchar(255)', 'String', 'gNotes', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, '', '2023-12-28 05:05:05', '', NULL);
INSERT INTO `gen_table_column` VALUES (3077, 126, 'g_release', '是否发布', 'int(11)', 'Long', 'gRelease', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, '', '2023-12-28 05:05:05', '', NULL);

-- ----------------------------
-- Table structure for nobid_non_pro
-- ----------------------------
DROP TABLE IF EXISTS `nobid_non_pro`;
CREATE TABLE `nobid_non_pro`  (
  `gid` int(11) NOT NULL AUTO_INCREMENT COMMENT '非招标项目ID',
  `eid` int(11) NULL DEFAULT NULL COMMENT '合同ID',
  `xy_id` int(11) NULL DEFAULT NULL COMMENT '采购计划序号',
  `g_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '非招标项目编号',
  `g_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '非招标项目名称',
  `g_is_public` int(11) NULL DEFAULT NULL COMMENT '公开/邀请',
  `g_spawn_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `g_timeon` datetime(0) NULL DEFAULT NULL COMMENT '报价开始时间',
  `g_deadline` datetime(0) NULL DEFAULT NULL COMMENT '报价截止时间',
  `g_count` int(11) NULL DEFAULT NULL COMMENT '报价数量',
  `g_unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '委托单位',
  `g_rounds` int(11) NULL DEFAULT NULL COMMENT '报价轮次',
  `g_tenderType` int(11) NULL DEFAULT NULL COMMENT '业务类型',
  `g_company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司',
  `g_notes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `g_release` int(11) NULL DEFAULT 0 COMMENT '是否发布 0:未发布 1:已发布',
  `hid` int(11) NULL DEFAULT NULL COMMENT '单一来源供应商',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '非招标项目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of nobid_non_pro
-- ----------------------------
INSERT INTO `nobid_non_pro` VALUES (52, NULL, 62, 'FZB202412041', '电脑配件采购计划', 1, '2024-01-02 15:34:19', '2024-01-05 15:35:00', '2024-01-10 15:35:00', NULL, NULL, NULL, 3, NULL, NULL, 2, 2);
INSERT INTO `nobid_non_pro` VALUES (54, NULL, 76, 'FZB202413043', '办公用具采购计划', 1, '2024-01-03 08:53:47', NULL, NULL, NULL, '腾腾科技（深圳）有限公司', NULL, 4, NULL, NULL, 1, NULL);
INSERT INTO `nobid_non_pro` VALUES (55, NULL, 77, 'FZB202413044', '办公设备采购计划', 1, '2024-01-03 08:58:53', NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, 0, NULL);
INSERT INTO `nobid_non_pro` VALUES (56, NULL, 78, 'FZB202413045', '办公室用具采购计划', 1, '2024-01-03 09:02:11', NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, 0, NULL);
INSERT INTO `nobid_non_pro` VALUES (57, NULL, 79, 'FZB202413046', '办公设备采购计划', 1, '2024-01-03 10:13:04', NULL, NULL, NULL, '腾腾科技（深圳）有限公司', NULL, 4, NULL, NULL, 1, NULL);
INSERT INTO `nobid_non_pro` VALUES (58, NULL, 81, 'FZB202413047', '办公电脑采购计划', 1, '2024-01-03 10:40:45', NULL, NULL, NULL, NULL, NULL, 6, NULL, NULL, 0, NULL);
INSERT INTO `nobid_non_pro` VALUES (59, NULL, 80, 'FZB202413048', '办公设备', 1, '2024-01-03 10:41:20', '2024-01-05 15:47:00', '2024-01-20 12:00:00', NULL, NULL, NULL, 5, NULL, NULL, 2, 85);
INSERT INTO `nobid_non_pro` VALUES (60, NULL, 82, 'FZB202413049', '医疗设备采购计划', 1, '2024-01-03 10:44:48', '2024-01-05 12:00:00', '2024-01-10 12:00:00', NULL, NULL, NULL, 3, NULL, NULL, 1, NULL);
INSERT INTO `nobid_non_pro` VALUES (61, 56, 66, 'FZB202413050', '软件许可采购计划', 1, '2024-01-03 15:12:41', '2024-01-03 15:17:16', '2024-01-10 12:00:00', NULL, NULL, NULL, 3, NULL, NULL, 2, 2);
INSERT INTO `nobid_non_pro` VALUES (62, NULL, 86, 'FZB202413051', '通讯设备采购计划', 1, '2024-01-03 15:40:57', NULL, NULL, NULL, '腾腾科技（深圳）有限公司', NULL, 4, NULL, NULL, 1, NULL);
INSERT INTO `nobid_non_pro` VALUES (63, 58, 85, 'FZB202413052', '办公用具采购计划', 1, '2024-01-03 15:41:02', '2024-01-03 17:18:41', '2024-01-13 12:00:00', NULL, NULL, NULL, 3, NULL, NULL, 2, 83);
INSERT INTO `nobid_non_pro` VALUES (64, NULL, 84, 'FZB202415053', '吸氧机采购计划', 1, '2024-01-05 13:12:30', '2024-01-05 13:15:00', '2024-01-17 12:00:00', NULL, NULL, NULL, 3, NULL, NULL, 2, 2);
INSERT INTO `nobid_non_pro` VALUES (65, NULL, 60, 'FZB202415054', '劳保用品采购计划', 1, '2024-01-05 13:24:14', '2024-01-05 13:28:00', '2024-01-10 12:00:00', NULL, NULL, NULL, 3, NULL, NULL, 2, 83);

-- ----------------------------
-- Table structure for nobid_sup_non_pro
-- ----------------------------
DROP TABLE IF EXISTS `nobid_sup_non_pro`;
CREATE TABLE `nobid_sup_non_pro`  (
  `gf_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '采购项目编号',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商ID',
  `gf_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购项目名称',
  `gf_unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购单位',
  `gf_way` int(11) NULL DEFAULT NULL COMMENT '采购方式',
  `gf_ln` int(11) NULL DEFAULT NULL COMMENT '是否流标',
  `gf_tp` int(11) NULL DEFAULT NULL COMMENT '终止采购',
  `gf_eba` int(11) NULL DEFAULT NULL COMMENT '过期未接受',
  `gf_sb` int(11) NULL DEFAULT NULL COMMENT '是否中标',
  `gf_qtime` datetime(0) NULL DEFAULT NULL COMMENT '报价截至时间',
  `gf_qstate` int(11) NULL DEFAULT NULL COMMENT '报价状态'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商非招标项目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of nobid_sup_non_pro
-- ----------------------------
INSERT INTO `nobid_sup_non_pro` VALUES ('FZB202413049', 2, '医疗设备采购计划', NULL, NULL, NULL, NULL, NULL, NULL, '2024-01-10 12:00:00', 0);
INSERT INTO `nobid_sup_non_pro` VALUES ('FZB202413050', 2, '软件许可采购计划', NULL, NULL, NULL, NULL, NULL, NULL, '2024-01-10 12:00:00', 1);
INSERT INTO `nobid_sup_non_pro` VALUES ('FZB202413052', 2, '办公用具采购计划', NULL, NULL, NULL, NULL, NULL, NULL, '2024-01-13 12:00:00', 1);
INSERT INTO `nobid_sup_non_pro` VALUES ('FZB202413052', 83, '办公用具采购计划', NULL, NULL, NULL, NULL, NULL, NULL, '2024-01-13 12:00:00', 1);
INSERT INTO `nobid_sup_non_pro` VALUES ('FZB202413048', 2, '办公设备', NULL, NULL, NULL, NULL, NULL, NULL, '2024-01-20 12:00:00', 1);

-- ----------------------------
-- Table structure for ppm_approval_record
-- ----------------------------
DROP TABLE IF EXISTS `ppm_approval_record`;
CREATE TABLE `ppm_approval_record`  (
  `rid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `aid` int(11) NULL DEFAULT NULL COMMENT '采购计划id',
  `node` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程节点',
  `processed_by` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `depnt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '处理时间',
  `opinion` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理意见',
  `opinion_details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '意见详情',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 160 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '审批记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ppm_approval_record
-- ----------------------------
INSERT INTO `ppm_approval_record` VALUES (110, 57, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 09:54:36', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (111, 57, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-02 09:55:17', '通过', '<p>采购计划合格通过</p>');
INSERT INTO `ppm_approval_record` VALUES (112, 62, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 09:56:13', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (113, 62, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-02 09:56:29', '通过', '<p>合格</p>');
INSERT INTO `ppm_approval_record` VALUES (114, 68, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 11:56:14', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (115, 68, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-02 12:31:18', '通过', '通过');
INSERT INTO `ppm_approval_record` VALUES (116, 65, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 19:16:03', '发起申请', '<p>采购50台洗衣机，请求审核！！</p>');
INSERT INTO `ppm_approval_record` VALUES (117, 61, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 19:16:44', '发起申请', '<p>采购30台办公桌，请求审核！！！</p>');
INSERT INTO `ppm_approval_record` VALUES (118, 59, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 19:18:28', '发起申请', '<p>学校需要采购30台笔记本电脑，请求审核</p>');
INSERT INTO `ppm_approval_record` VALUES (119, 65, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-02 19:19:47', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (120, 57, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 19:21:34', '发起申请', '<p>采购计划编辑完成，请求审核</p>');
INSERT INTO `ppm_approval_record` VALUES (121, 61, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-02 19:23:58', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (122, 63, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 20:07:19', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (123, 63, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-02 20:07:47', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (124, 74, '提交采购计划', '超级管理员', '研发部门', '2024-01-02 20:18:23', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (125, 74, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-02 20:18:31', '通过', 'asd');
INSERT INTO `ppm_approval_record` VALUES (126, 76, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 08:52:55', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (127, 76, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 08:53:20', '通过', '通过');
INSERT INTO `ppm_approval_record` VALUES (128, 77, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 08:58:21', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (129, 77, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 08:58:32', '通过', '通过');
INSERT INTO `ppm_approval_record` VALUES (130, 78, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 09:01:50', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (131, 78, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 09:01:58', '通过', '通过');
INSERT INTO `ppm_approval_record` VALUES (132, 79, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 10:12:33', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (133, 79, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 10:12:45', '通过', '通过');
INSERT INTO `ppm_approval_record` VALUES (134, 80, '提交采购计划', '雷建国', '深圳总公司', '2024-01-03 10:16:22', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (135, 80, '采购计划通过审核', '雷经理', '深圳总公司', '2024-01-03 10:17:15', '通过', '<p>pass</p>');
INSERT INTO `ppm_approval_record` VALUES (136, 81, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 10:39:39', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (137, 81, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 10:39:47', '通过', '通过');
INSERT INTO `ppm_approval_record` VALUES (138, 82, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 10:44:19', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (139, 82, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 10:44:38', '通过', '通过');
INSERT INTO `ppm_approval_record` VALUES (140, 64, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 11:58:41', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (141, 67, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 11:59:14', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (142, 64, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 11:59:31', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (143, 59, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 11:59:56', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (144, 66, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 12:00:02', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (145, 66, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 12:00:14', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (146, 75, '提交采购计划', '超级管理员', '研发部门', '2024-01-03 12:00:44', '发起申请', '<p><br></p>');
INSERT INTO `ppm_approval_record` VALUES (147, 67, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-03 12:00:56', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (148, 83, '提交采购计划', '雷经理', '深圳总公司', '2024-01-03 13:33:29', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (149, 83, '采购计划通过审核', '雷经理', '深圳总公司', '2024-01-03 13:33:58', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (150, 84, '提交采购计划', '雷经理', '深圳总公司', '2024-01-03 15:37:45', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (151, 84, '采购计划通过审核', '雷经理', '深圳总公司', '2024-01-03 15:37:56', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (152, 85, '提交采购计划', '雷经理', '深圳总公司', '2024-01-03 15:39:33', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (153, 85, '采购计划通过审核', '雷经理', '深圳总公司', '2024-01-03 15:39:44', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (154, 86, '提交采购计划', '雷经理', '深圳总公司', '2024-01-03 15:40:37', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (155, 86, '采购计划通过审核', '雷经理', '深圳总公司', '2024-01-03 15:40:47', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (156, 87, '提交采购计划', '雷经理', '深圳总公司', '2024-01-03 16:47:32', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (157, 87, '采购计划通过审核', '雷经理', '深圳总公司', '2024-01-03 16:47:58', '通过', '<p>通过</p>');
INSERT INTO `ppm_approval_record` VALUES (158, 60, '提交采购计划', '超级管理员', '研发部门', '2024-01-05 13:23:40', '发起申请', NULL);
INSERT INTO `ppm_approval_record` VALUES (159, 60, '采购计划通过审核', '超级管理员', '研发部门', '2024-01-05 13:24:01', '通过', '<p>通过</p>');

-- ----------------------------
-- Table structure for ppm_budget
-- ----------------------------
DROP TABLE IF EXISTS `ppm_budget`;
CREATE TABLE `ppm_budget`  (
  `du_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '预算ID',
  `aid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购计划ID',
  `du_dept` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `du_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预算科目名称',
  `du_total` decimal(10, 0) NULL DEFAULT NULL COMMENT '总金额',
  PRIMARY KEY (`du_id`) USING BTREE,
  INDEX `FK_Relationship_24`(`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '预算表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ppm_budget
-- ----------------------------
INSERT INTO `ppm_budget` VALUES ('YS202312290031', 'CGJH20231229163208', '采购部', '采购冰箱', 10000);
INSERT INTO `ppm_budget` VALUES ('YS202312290032', '60', '采购部', '电视机预算', 6000);
INSERT INTO `ppm_budget` VALUES ('YS2024120033', '61', '采购部', '办公桌预算', 2247);
INSERT INTO `ppm_budget` VALUES ('YS2024120034', 'CGJH202412948216', '采购部', '办公桌预算', 4083);
INSERT INTO `ppm_budget` VALUES ('YS2024130035', 'CGJH2024131644243', '采购部', '办公预算', 405573);

-- ----------------------------
-- Table structure for ppm_device
-- ----------------------------
DROP TABLE IF EXISTS `ppm_device`;
CREATE TABLE `ppm_device`  (
  `tid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品编码',
  `t_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `category` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别',
  `t_unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计量单位',
  `shui` decimal(10, 0) NULL DEFAULT NULL COMMENT '税率',
  `t_illustrate` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '需求说明',
  `du_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预算科目编号',
  `t_notes` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `t_model` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格型号',
  `t_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ppm_device
-- ----------------------------
INSERT INTO `ppm_device` VALUES ('1001', '冰箱', '家电', '个', 0, '家用冰箱，能够制冷', NULL, '现代款式，能效优良', '型号BX-100', 1500.00);
INSERT INTO `ppm_device` VALUES ('1002', '空调', '家电', '台', 0, '1.5匹挂式空调，冷暖两用', NULL, '适用于中小型房间', '型号AC-200', 2500.00);
INSERT INTO `ppm_device` VALUES ('1003', '洗衣机', '家电', '台', 0, '全自动洗衣机，大容量', NULL, '省水省电，一键操作', '型号WM-300', 1200.00);
INSERT INTO `ppm_device` VALUES ('1004', '电视', '家电', '台', 0, '高清平板电视，55寸', NULL, '超薄设计，视觉盛宴', '型号TV-400', 1800.00);
INSERT INTO `ppm_device` VALUES ('1005', '微波炉', '家电', '台', 0, '家用微波炉，多功能', NULL, '快速加热，食物更美味', '型号MW-500', 800.00);
INSERT INTO `ppm_device` VALUES ('1006', '笔记本电脑', '电子产品', '台', 0, '轻薄本，高性能', NULL, '适用于办公和娱乐', '型号Laptop-600', 3500.00);
INSERT INTO `ppm_device` VALUES ('1007', '智能手机', '电子产品', '台', 0, '6.5寸智能手机，高像素', NULL, '全面屏设计，畅快体验', '型号Phone-700', 1200.00);
INSERT INTO `ppm_device` VALUES ('1008', '打印机', '办公设备', '台', 0, '激光打印机，多功能', NULL, '高速打印，节省办公时间', '型号Printer-800', 600.00);
INSERT INTO `ppm_device` VALUES ('1009', '办公桌', '办公家具', '张', 0, '现代简约办公桌，实木', NULL, '宽敞设计，提高工作效率', '型号Desk-900', 900.00);
INSERT INTO `ppm_device` VALUES ('1010', '办公椅', '办公家具', '把', 0, '人体工学设计办公椅，可调节', NULL, '舒适座椅，保护腰椎', '型号Chair-1000', 300.00);
INSERT INTO `ppm_device` VALUES ('1030', '投影仪', '办公设备', '台', 0, '高清投影，适用于大型会议', NULL, '便携式设计，易于携带', '型号PJ-300', 3500.00);
INSERT INTO `ppm_device` VALUES ('SB20240706002', '笔记本', '办公用品', '台', 0, '拯救者', NULL, NULL, 'BJ-002', 8013.00);
INSERT INTO `ppm_device` VALUES ('T1090809', '显示器', '计算器配件', '台', 0, '联想显示器', NULL, NULL, 'PJ-100', 1000.00);
INSERT INTO `ppm_device` VALUES ('XYG20240101001', '吸氧机', '医疗', '台', 0, '需要液氧吸氧机', NULL, '0.5-5升/分钟可调', 'XYG-456', 20000.00);

-- ----------------------------
-- Table structure for ppm_frame_plan
-- ----------------------------
DROP TABLE IF EXISTS `ppm_frame_plan`;
CREATE TABLE `ppm_frame_plan`  (
  `jh_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '框架计划ID',
  `oid` int(11) NULL DEFAULT NULL COMMENT '框架协议ID',
  `jh_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '框架计划编码',
  `jh_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计划名称',
  `jh_yu` decimal(10, 0) NULL DEFAULT NULL COMMENT '计划预算',
  `dept` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建部门',
  `jh_status` int(11) NULL DEFAULT 0 COMMENT '计划状态',
  `jh_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批人',
  `jh_pmethod` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购方式',
  `jh_founder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `hid` int(11) NULL DEFAULT NULL COMMENT '供应商id',
  `business_type` int(11) NULL DEFAULT NULL COMMENT '业务类型',
  PRIMARY KEY (`jh_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '框架计划表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ppm_frame_plan
-- ----------------------------
INSERT INTO `ppm_frame_plan` VALUES (53, 45, 'KJJH20231229016', '采购办公用品', 2000, '采购部', 2, '超级管理员', '3', '超级管理员', 82, 1);
INSERT INTO `ppm_frame_plan` VALUES (54, 46, 'KJJH20231229017', '办公用品', 30000, '研发部门', 2, '超级管理员', '2', '超级管理员', 2, 1);
INSERT INTO `ppm_frame_plan` VALUES (55, NULL, 'KJJH202412018', '学习使用电脑', 45670, '研发部门', 2, '雷经理', '4', '超级管理员', 2, 1);
INSERT INTO `ppm_frame_plan` VALUES (56, NULL, 'KJJH202412019', '办公用品', 45102, '研发部门', 2, '雷经理', '5', '超级管理员', 82, 1);
INSERT INTO `ppm_frame_plan` VALUES (57, NULL, 'KJJH202412020', '采购笔记本', 20356, '研发部门', 1, NULL, '5', '超级管理员', 83, 1);
INSERT INTO `ppm_frame_plan` VALUES (58, NULL, 'KJJH202412021', '办公用品', 40359, '研发部门', 1, NULL, '5', '超级管理员', 2, 1);
INSERT INTO `ppm_frame_plan` VALUES (59, 44, 'KJJH202412022', '办公用品框架计划', 34961, '研发部门', 2, '超级管理员', '5', '超级管理员', 96, 1);
INSERT INTO `ppm_frame_plan` VALUES (60, NULL, 'KJJH202412023', '家具用品框架计划', 34568, '研发部门', 0, NULL, NULL, '超级管理员', 96, 2);
INSERT INTO `ppm_frame_plan` VALUES (61, NULL, 'KJJH202412024', '医疗设备框架计划', 213456, '研发部门', 1, NULL, NULL, '超级管理员', 2, 2);
INSERT INTO `ppm_frame_plan` VALUES (62, NULL, 'KJJH202413025', '办公用品', 30875, '深圳总公司', 0, NULL, NULL, '雷经理', 82, 1);
INSERT INTO `ppm_frame_plan` VALUES (63, NULL, 'KJJH202413026', '医疗设备', 409867, '深圳总公司', 0, NULL, NULL, '雷经理', 97, 1);

-- ----------------------------
-- Table structure for ppm_line_items
-- ----------------------------
DROP TABLE IF EXISTS `ppm_line_items`;
CREATE TABLE `ppm_line_items`  (
  `vid` int(11) NOT NULL AUTO_INCREMENT COMMENT '行项目ID',
  `v_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '行项目编号',
  `sid` int(11) NULL DEFAULT NULL COMMENT '招标项目ID',
  `jh_id` int(11) NULL DEFAULT NULL COMMENT '框架计划ID`',
  `aid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购计划ID',
  `tid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `gid` int(11) NULL DEFAULT NULL COMMENT '非招标项目ID',
  `v_count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `v_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购人',
  `v_delivery_time` datetime(0) NULL DEFAULT NULL COMMENT '交付时间',
  `v_delivery_area` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交付地点',
  `v_illustrate` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '需求说明',
  `procurement_method` int(10) NULL DEFAULT NULL COMMENT '采购方式',
  `did` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预算编号',
  PRIMARY KEY (`vid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 256 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '行项目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ppm_line_items
-- ----------------------------
INSERT INTO `ppm_line_items` VALUES (194, 'HXM20231229071', NULL, NULL, '58', '1009', NULL, 100, '陈冰', '2023-12-11 00:00:00', '湖南省长沙市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (195, 'HXM20231229072', NULL, NULL, '60', '1004', NULL, 20, '刘美', '2023-12-27 00:00:00', '湖南省浏阳市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (197, 'HXM20231229074', NULL, NULL, '59', '1006', NULL, 30, '胡清', '2023-12-28 00:00:00', '广东省广州市龙岗区', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (199, 'HXM20231229076', NULL, NULL, '66', '1008', NULL, 60, '圆圆', '2023-12-12 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (200, 'HXM20231229077', NULL, NULL, '67', '1007', NULL, 80, '欧吉', '2023-12-27 00:00:00', '浙江省温州市', '华为手机', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (201, 'HXM20231229078', NULL, NULL, '65', '1003', NULL, 50, '谭流', '2023-12-28 00:00:00', ' 四川省成都市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (202, 'HXM20231229079', NULL, NULL, '63', '1008', NULL, 100, '张芸芸', '2023-12-20 00:00:00', '武汉市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (208, 'HXM20231229075', NULL, NULL, '62', 'T1090809', NULL, 100, '颜云云', '2023-12-19 00:00:00', '北京市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (211, 'HXM20231229080', NULL, 53, NULL, '1003', NULL, 1000, '覃青青', '2023-09-13 00:00:00', '武汉市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (214, 'HXM20231229081', NULL, 54, NULL, '1009', NULL, 30, '王五', '2023-12-12 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (215, 'HXM202412085', NULL, 55, NULL, 'SB20240706002', NULL, 40, '王五', '2023-12-29 00:00:00', '北京市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (216, 'HXM202412086', NULL, 55, NULL, 'SB20240706002', NULL, 10, '彭于飞', '2024-01-01 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (217, 'HXM202412087', NULL, 56, NULL, '1008', NULL, 20, '段德', '2024-01-10 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (218, 'HXM202412088', NULL, NULL, '64', '1010', NULL, 20, '刘兵', '2024-01-01 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (219, 'HXM202412089', NULL, 57, NULL, '1009', NULL, 30, '刘熙', '2024-01-01 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (220, 'HXM20231229073', NULL, NULL, '61', '1009', NULL, 30, '王伟', '2023-12-18 00:00:00', '广东省深圳市', '无', NULL, 'YS2024120033');
INSERT INTO `ppm_line_items` VALUES (221, 'HXM202412090', NULL, NULL, '69', '1009', NULL, 30, '刘斌', '2024-01-01 00:00:00', '上海市', '无', NULL, 'YS2024120034');
INSERT INTO `ppm_line_items` VALUES (222, 'HXM20231229070', NULL, NULL, '57', '1002', NULL, 50, '张丽丽', '2023-12-21 00:00:00', '湖南省长沙市', '无', NULL, 'YS202312290031');
INSERT INTO `ppm_line_items` VALUES (223, 'HXM202412091', NULL, NULL, '57', '1008', NULL, 20, '刘兵', '2024-01-01 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (224, 'HXM202412092', NULL, 58, NULL, '1009', NULL, 40, '王五', '2024-01-31 00:00:00', '北京市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (229, 'HXM202411082', NULL, NULL, '68', '1006', NULL, 20, 'iu', '2024-01-10 00:00:00', '河北', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (230, 'HXM202411083', NULL, NULL, '68', '1030', NULL, 10, 'iu', '2024-01-10 00:00:00', '河北', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (231, 'HXM202411084', NULL, NULL, '68', 'T1090809', NULL, 20, 'iu', '2024-01-10 00:00:00', '河北', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (232, 'HXM202412097', NULL, 59, NULL, '1009', NULL, 30, '黄星', '2024-01-31 00:00:00', '永州市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (233, 'HXM202412098', NULL, 59, NULL, '1010', NULL, 30, '黄星', '2024-01-31 00:00:00', '邵阳市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (234, 'HXM202412099', NULL, 60, NULL, '1003', NULL, 40, '王洛', '2024-01-31 00:00:00', '成都市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (235, 'HXM202412100', NULL, 61, NULL, 'XYG20240101001', NULL, 40, '李落落', '2024-01-31 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (236, 'HXM202412101', NULL, NULL, '74', '1002', NULL, 20, '23', '2024-01-09 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (237, 'HXM202413102', NULL, NULL, '75', '1009', NULL, 10, '李华', '2024-01-31 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (238, 'HXM202413103', NULL, NULL, '76', '1008', NULL, 15, '欧', '2024-01-20 00:00:00', '北京', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (239, 'HXM202413104', NULL, NULL, '77', '1006', NULL, 20, '欧', '2024-01-20 00:00:00', '北京', NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (240, 'HXM202413105', NULL, NULL, '77', 'T1090809', NULL, 10, '欧', '2024-01-20 00:00:00', '北京', NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (241, 'HXM202413106', NULL, NULL, '78', '1010', NULL, 20, '欧', '2024-01-15 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (242, 'HXM202413107', NULL, NULL, '78', '1009', NULL, 20, '欧', '2024-01-15 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (243, 'HXM202413108', NULL, NULL, '78', '1008', NULL, 5, '欧', '2024-01-15 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (244, 'HXM202413109', NULL, NULL, '79', '1009', NULL, 10, '欧', '2024-01-10 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (245, 'HXM202413110', NULL, NULL, '79', '1006', NULL, 5, '欧', '2024-01-10 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (246, 'HXM202413111', NULL, NULL, '80', '1010', NULL, 10, '张四', '2024-02-08 00:00:00', '上海', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (247, 'HXM202413112', NULL, NULL, '81', '1006', NULL, 50, '欧明鑫', '2024-01-20 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (248, 'HXM202413113', NULL, NULL, '82', 'XYG20240101001', NULL, 5, '欧明鑫', '2024-01-30 00:00:00', '北京', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (249, 'HXM202413114', NULL, NULL, '83', '1009', NULL, 40, '刘冰', '2024-01-31 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (250, 'HXM202413115', NULL, 62, NULL, '1008', NULL, 30, '王洛洛', '2024-01-31 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (251, 'HXM202413116', NULL, NULL, '84', 'XYG20240101001', NULL, 10, '欧', '2024-01-10 00:00:00', '北京', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (252, 'HXM202413117', NULL, NULL, '85', '1001', NULL, 2, '欧', '2024-01-20 00:00:00', '北京', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (253, 'HXM202413118', NULL, NULL, '86', '1007', NULL, 10, '李江', '2024-01-15 00:00:00', '北京', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (254, 'HXM202413119', NULL, NULL, '87', '1009', NULL, 40, '刘兵', '2024-01-31 00:00:00', '上海市', '无', NULL, NULL);
INSERT INTO `ppm_line_items` VALUES (255, 'HXM202413120', NULL, 63, NULL, 'XYG20240101001', NULL, 40, '王落落', '2024-01-31 00:00:00', '北京市', '无', NULL, NULL);

-- ----------------------------
-- Table structure for ppm_procurement_plan
-- ----------------------------
DROP TABLE IF EXISTS `ppm_procurement_plan`;
CREATE TABLE `ppm_procurement_plan`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购计划ID',
  `a_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购计划编号',
  `a_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计划名称',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `a_create_dept` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `fj_annex` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '附件路径',
  `a_opinion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购计划审核意见',
  `a_project_count` int(11) NULL DEFAULT NULL COMMENT '行项目数量',
  `a_btype` int(11) NULL DEFAULT NULL COMMENT '采购业务类型名称',
  `a_astate` int(11) NULL DEFAULT 0 COMMENT '采购审批状态',
  `fj_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购计划表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ppm_procurement_plan
-- ----------------------------
INSERT INTO `ppm_procurement_plan` VALUES (57, 'CGJH20231229163208', '年度采购计划', '超级管理员', '研发部门', '2023-12-29 16:14:31', NULL, '2024-01-02 19:21:34', NULL, NULL, NULL, 1, 1, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (58, 'CP20231001', '办公用品采购计划', '雷经理', '行政部', '2023-12-29 16:30:00', NULL, '2024-01-02 09:33:59', NULL, NULL, NULL, 1, 0, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (59, 'CP20231002', 'IT设备采购计划', '王梓', 'IT部门', '2023-12-29 17:00:00', NULL, '2024-01-03 12:01:10', NULL, NULL, NULL, 2, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (60, 'CP20231003', '劳保用品采购计划', '刘波', '人力资源部', '2023-12-29 17:30:00', NULL, '2024-01-05 13:24:14', NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (61, 'CP20231004', '办公家具采购计划', '陈思', '行政部', '2023-12-29 18:00:00', NULL, '2024-01-02 20:08:28', NULL, NULL, NULL, 2, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (62, 'CP20231005', '电脑配件采购计划', '刘冰', 'IT部门', '2023-12-29 18:30:00', NULL, '2024-01-02 15:34:19', NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (63, 'CP20231006', '办公用纸采购计划', '吴花花', '行政部', '2023-12-29 19:00:00', NULL, '2024-01-03 11:59:40', NULL, NULL, NULL, 2, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (64, 'CP20231007', '办公电器采购计划', '雷梅美', '行政部', '2023-12-29 19:30:00', NULL, '2024-01-03 11:59:32', NULL, NULL, NULL, 1, 2, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (65, 'CP20231008', '家具用品', '张烁', '行政部', '2023-12-29 20:00:00', NULL, '2024-01-03 12:01:44', NULL, NULL, NULL, 2, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (66, 'CP20231009', '软件许可采购计划', '李飒', 'IT部门', '2023-12-29 20:30:00', NULL, '2024-01-03 15:12:41', NULL, NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (67, 'CP20231010', '办公装修采购计划', '覃敏', '行政部', '2023-12-29 21:00:00', NULL, '2024-01-03 12:01:19', NULL, NULL, NULL, 2, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (68, 'CGJH2024112025211', '多媒体采购计划', '超级管理员', '行政部', '2024-01-01 20:28:00', NULL, '2024-01-02 12:31:30', '', NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (69, 'CGJH202412948216', '办公用品', '超级管理员', '研发部门', '2024-01-02 09:50:00', NULL, NULL, '', NULL, NULL, 1, 0, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (75, 'CGJH202413848228', '办公用品', '雷经理', '深圳总公司', '2024-01-03 08:51:03', NULL, '2024-01-03 12:00:44', '', NULL, NULL, 1, 1, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (76, 'CGJH202413851229', '办公用具采购计划', '超级管理员', '研发部门', '2024-01-03 08:52:41', NULL, '2024-01-03 08:53:47', '', NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (77, 'CGJH202413855230', '办公设备采购计划', '超级管理员', '研发部门', '2024-01-03 08:58:02', NULL, '2024-01-03 08:58:54', '', NULL, NULL, 4, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (78, 'CGJH202413859231', '办公室用具采购计划', '超级管理员', '研发部门', '2024-01-03 09:01:38', NULL, '2024-01-03 09:02:12', '', NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (79, 'CGJH2024131011233', '办公设备采购计划', '超级管理员', '研发部门', '2024-01-03 10:12:26', NULL, '2024-01-03 10:13:04', '', NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (80, 'CGJH2024131015234', '办公设备', '雷建国', '深圳总公司', '2024-01-03 10:15:50', NULL, '2024-01-03 10:41:20', '', NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (81, 'CGJH2024131038236', '办公电脑采购计划', '超级管理员', '研发部门', '2024-01-03 10:39:07', NULL, '2024-01-03 10:40:45', '', NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (82, 'CGJH2024131043237', '医疗设备采购计划', '超级管理员', '研发部门', '2024-01-03 10:44:09', NULL, '2024-01-03 10:44:47', '', NULL, NULL, 4, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (83, 'CGJH2024131331239', '办公用品', '雷经理', '深圳总公司', '2024-01-03 13:32:51', NULL, '2024-01-03 13:36:40', '', NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (84, 'CGJH2024131536240', '吸氧机采购计划', '雷经理', '深圳总公司', '2024-01-03 15:37:35', NULL, '2024-01-05 13:12:30', '', NULL, NULL, 4, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (85, 'CGJH2024131538241', '办公用具采购计划', '雷经理', '深圳总公司', '2024-01-03 15:39:08', NULL, '2024-01-03 15:41:03', '', NULL, NULL, 1, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (86, 'CGJH2024131539242', '通讯设备采购计划', '雷经理', '深圳总公司', '2024-01-03 15:40:26', NULL, '2024-01-03 15:40:58', '', NULL, NULL, 4, 3, NULL);
INSERT INTO `ppm_procurement_plan` VALUES (87, 'CGJH2024131644243', '办公桌', '雷经理', '深圳总公司', '2024-01-03 16:46:55', NULL, '2024-01-03 16:49:39', '', NULL, NULL, 1, 3, NULL);

SET FOREIGN_KEY_CHECKS = 1;
