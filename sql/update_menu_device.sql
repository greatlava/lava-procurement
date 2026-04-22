-- =============================================
-- 菜单结构修改SQL
-- 功能：
-- 1. 创建"业务设置"菜单目录
-- 2. 将原"系统管理"下的指定菜单移动到"业务设置"下
-- 3. 创建"物料管理"菜单和权限
-- 执行顺序：在业务数据库中执行此SQL
-- =============================================

-- =============================================
-- 第一步：创建"业务设置"菜单目录
-- =============================================
-- menu_id: 2121, parent_id: 0 (根目录), order_num: 10 (放在现有菜单后面)
-- 现有根目录菜单：系统管理(1),系统监控(2),系统工具(3),若依官网(4),招标项目(2000),采购需求管理(2006),供应商管理(2008),合同管理(2015),专家管理(2031),非招标项目(2036)
INSERT INTO `sys_menu` VALUES (2121, '业务设置', 0, 10, 'business', NULL, '', 1, 0, 'M', '0', '0', '', 'edit', 'admin', NOW(), '', NULL, '业务设置目录');

-- =============================================
-- 第二步：移动菜单项到"业务设置"下
-- 按顺序：部门管理、角色管理、岗位管理、用户管理、通知公告、编号规则、物料管理
-- =============================================

-- 1. 部门管理 (原menu_id=103, parent_id=1, order_num=4)
-- 新order_num: 1
UPDATE `sys_menu` SET `parent_id` = 2121, `order_num` = 1 WHERE `menu_id` = 103;

-- 2. 角色管理 (原menu_id=101, parent_id=1, order_num=2)
-- 新order_num: 2
UPDATE `sys_menu` SET `parent_id` = 2121, `order_num` = 2 WHERE `menu_id` = 101;

-- 3. 岗位管理 (原menu_id=104, parent_id=1, order_num=5)
-- 新order_num: 3
UPDATE `sys_menu` SET `parent_id` = 2121, `order_num` = 3 WHERE `menu_id` = 104;

-- 4. 用户管理 (原menu_id=100, parent_id=1, order_num=1)
-- 新order_num: 4
UPDATE `sys_menu` SET `parent_id` = 2121, `order_num` = 4 WHERE `menu_id` = 100;

-- 5. 通知公告 (原menu_id=107, parent_id=1, order_num=8)
-- 新order_num: 5
UPDATE `sys_menu` SET `parent_id` = 2121, `order_num` = 5 WHERE `menu_id` = 107;

-- 6. 编号规则 (原menu_id=2018, parent_id=1, order_num=9)
-- 新order_num: 6
UPDATE `sys_menu` SET `parent_id` = 2121, `order_num` = 6 WHERE `menu_id` = 2018;

-- =============================================
-- 第三步：创建"物料管理"菜单
-- =============================================

-- 物料管理主菜单 (C类型菜单)
-- menu_id: 2122, parent_id: 2121, order_num: 7
-- component: device/index (新创建的Vue页面)
INSERT INTO `sys_menu` VALUES (2122, '物料管理', 2121, 7, 'device', 'device/index', '', 1, 0, 'C', '0', '0', 'system:device:list', 'list', 'admin', NOW(), '', NULL, '物料管理菜单');

-- =============================================
-- 第四步：创建物料管理的权限按钮 (F类型)
-- 注意：后端PpmDeviceController中已有的权限标识：
-- - list: system:device:list
-- - query: system:device:query
-- - add: system:device:add
-- - edit: system:device:edit
-- - remove: system:device:remove
-- - export: system:device:export
-- =============================================

-- 1. 物料查询权限
INSERT INTO `sys_menu` VALUES (2123, '物料查询', 2122, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:device:query', '#', 'admin', NOW(), '', NULL, '');

-- 2. 物料新增权限
INSERT INTO `sys_menu` VALUES (2124, '物料新增', 2122, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:device:add', '#', 'admin', NOW(), '', NULL, '');

-- 3. 物料修改权限
INSERT INTO `sys_menu` VALUES (2125, '物料修改', 2122, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:device:edit', '#', 'admin', NOW(), '', NULL, '');

-- 4. 物料删除权限
INSERT INTO `sys_menu` VALUES (2126, '物料删除', 2122, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:device:remove', '#', 'admin', NOW(), '', NULL, '');

-- 5. 物料导出权限
INSERT INTO `sys_menu` VALUES (2127, '物料导出', 2122, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:device:export', '#', 'admin', NOW(), '', NULL, '');

-- =============================================
-- 第五步：调整"系统管理"下剩余菜单的order_num
-- 原系统管理(parent_id=1)下的菜单及其order_num:
-- 100: 用户管理 (order_num=1) - 已移动
-- 101: 角色管理 (order_num=2) - 已移动
-- 102: 菜单管理 (order_num=3) - 保留
-- 103: 部门管理 (order_num=4) - 已移动
-- 104: 岗位管理 (order_num=5) - 已移动
-- 105: 字典管理 (order_num=6) - 保留
-- 106: 参数设置 (order_num=7) - 保留
-- 107: 通知公告 (order_num=8) - 已移动
-- 108: 日志管理 (order_num=9) - 保留 (M类型，有子菜单)
-- 2018: 编号规则 (order_num=9) - 已移动
-- 
-- 保留的菜单需要重新排序：
-- 102: 菜单管理 -> order_num=1
-- 105: 字典管理 -> order_num=2
-- 106: 参数设置 -> order_num=3
-- 108: 日志管理 -> order_num=4
-- =============================================

UPDATE `sys_menu` SET `order_num` = 1 WHERE `menu_id` = 102;
UPDATE `sys_menu` SET `order_num` = 2 WHERE `menu_id` = 105;
UPDATE `sys_menu` SET `order_num` = 3 WHERE `menu_id` = 106;
UPDATE `sys_menu` SET `order_num` = 4 WHERE `menu_id` = 108;

-- =============================================
-- 第六步：验证修改
-- 执行以下查询验证修改结果
-- =============================================
-- 查看业务设置下的菜单：
-- SELECT * FROM sys_menu WHERE parent_id = 2121 ORDER BY order_num;

-- 查看系统管理下的菜单：
-- SELECT * FROM sys_menu WHERE parent_id = 1 ORDER BY order_num;

-- 查看物料管理菜单及其权限：
-- SELECT * FROM sys_menu WHERE menu_id = 2122 OR parent_id = 2122 ORDER BY order_num;
