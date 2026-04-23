-- =============================================
-- 业务设置权限标识修改SQL
-- 功能：
-- 1. 将业务设置下所有菜单的权限标识从 system:xxx 改为 business:xxx
-- 2. 调整业务设置菜单的显示顺序（放到系统管理后面，与系统管理同级）
-- 执行顺序：在业务数据库中执行此SQL
-- =============================================

-- =============================================
-- 第一步：调整业务设置菜单的显示顺序
-- 将业务设置(menu_id=2121)的order_num从10改为4，放到系统工具后面
-- 当前根目录菜单order_num：
-- 1:系统管理, 2:系统监控, 3:系统工具, 4:若依官网, 10:业务设置
-- 调整后：
-- 1:系统管理, 2:系统监控, 3:系统工具, 4:业务设置, 5:若依官网, ...后续菜单依次后移
-- =============================================

-- 先将若依官网及之后的菜单order_num加1，为业务设置腾出位置
UPDATE `sys_menu` SET `order_num` = `order_num` + 1 WHERE `parent_id` = 0 AND `order_num` >= 4;

-- 将业务设置的order_num改为4（放到系统工具后面）
UPDATE `sys_menu` SET `order_num` = 4 WHERE `menu_id` = 2121;

-- =============================================
-- 第二步：修改用户管理(menu_id=100)的权限标识
-- system:user:xxx -> business:user:xxx
-- =============================================

-- 主菜单权限
UPDATE `sys_menu` SET `perms` = 'business:user:list' WHERE `menu_id` = 100;

-- 按钮权限
UPDATE `sys_menu` SET `perms` = 'business:user:query' WHERE `menu_id` = 1000;
UPDATE `sys_menu` SET `perms` = 'business:user:add' WHERE `menu_id` = 1001;
UPDATE `sys_menu` SET `perms` = 'business:user:edit' WHERE `menu_id` = 1002;
UPDATE `sys_menu` SET `perms` = 'business:user:remove' WHERE `menu_id` = 1003;
UPDATE `sys_menu` SET `perms` = 'business:user:export' WHERE `menu_id` = 1004;
UPDATE `sys_menu` SET `perms` = 'business:user:import' WHERE `menu_id` = 1005;
UPDATE `sys_menu` SET `perms` = 'business:user:resetPwd' WHERE `menu_id` = 1006;

-- =============================================
-- 第三步：修改角色管理(menu_id=101)的权限标识
-- system:role:xxx -> business:role:xxx
-- =============================================

-- 主菜单权限
UPDATE `sys_menu` SET `perms` = 'business:role:list' WHERE `menu_id` = 101;

-- 按钮权限
UPDATE `sys_menu` SET `perms` = 'business:role:query' WHERE `menu_id` = 1007;
UPDATE `sys_menu` SET `perms` = 'business:role:add' WHERE `menu_id` = 1008;
UPDATE `sys_menu` SET `perms` = 'business:role:edit' WHERE `menu_id` = 1009;
UPDATE `sys_menu` SET `perms` = 'business:role:remove' WHERE `menu_id` = 1010;
UPDATE `sys_menu` SET `perms` = 'business:role:export' WHERE `menu_id` = 1011;

-- =============================================
-- 第四步：修改部门管理(menu_id=103)的权限标识
-- system:dept:xxx -> business:dept:xxx
-- =============================================

-- 主菜单权限
UPDATE `sys_menu` SET `perms` = 'business:dept:list' WHERE `menu_id` = 103;

-- 按钮权限
UPDATE `sys_menu` SET `perms` = 'business:dept:query' WHERE `menu_id` = 1016;
UPDATE `sys_menu` SET `perms` = 'business:dept:add' WHERE `menu_id` = 1017;
UPDATE `sys_menu` SET `perms` = 'business:dept:edit' WHERE `menu_id` = 1018;
UPDATE `sys_menu` SET `perms` = 'business:dept:remove' WHERE `menu_id` = 1019;

-- =============================================
-- 第五步：修改岗位管理(menu_id=104)的权限标识
-- system:post:xxx -> business:post:xxx
-- =============================================

-- 主菜单权限
UPDATE `sys_menu` SET `perms` = 'business:post:list' WHERE `menu_id` = 104;

-- 按钮权限
UPDATE `sys_menu` SET `perms` = 'business:post:query' WHERE `menu_id` = 1020;
UPDATE `sys_menu` SET `perms` = 'business:post:add' WHERE `menu_id` = 1021;
UPDATE `sys_menu` SET `perms` = 'business:post:edit' WHERE `menu_id` = 1022;
UPDATE `sys_menu` SET `perms` = 'business:post:remove' WHERE `menu_id` = 1023;
UPDATE `sys_menu` SET `perms` = 'business:post:export' WHERE `menu_id` = 1024;

-- =============================================
-- 第六步：修改通知公告(menu_id=107)的权限标识
-- system:notice:xxx -> business:notice:xxx
-- =============================================

-- 主菜单权限
UPDATE `sys_menu` SET `perms` = 'business:notice:list' WHERE `menu_id` = 107;

-- 按钮权限
UPDATE `sys_menu` SET `perms` = 'business:notice:query' WHERE `menu_id` = 1035;
UPDATE `sys_menu` SET `perms` = 'business:notice:add' WHERE `menu_id` = 1036;
UPDATE `sys_menu` SET `perms` = 'business:notice:edit' WHERE `menu_id` = 1037;
UPDATE `sys_menu` SET `perms` = 'business:notice:remove' WHERE `menu_id` = 1038;

-- 其他地方引用的notice权限（如招标项目下的）
-- 注意：这些属于其他模块，不修改，保持system:notice:xxx
-- UPDATE `sys_menu` SET `perms` = 'business:notice:query' WHERE `menu_id` = 2088;
-- UPDATE `sys_menu` SET `perms` = 'business:notice:add' WHERE `menu_id` = 2089;
-- UPDATE `sys_menu` SET `perms` = 'business:notice:edit' WHERE `menu_id` = 2090;

-- =============================================
-- 第七步：修改编号规则(menu_id=2018)的权限标识
-- system:rules:xxx -> business:rules:xxx
-- =============================================

-- 按钮权限
UPDATE `sys_menu` SET `perms` = 'business:rules:list' WHERE `menu_id` = 2054;
UPDATE `sys_menu` SET `perms` = 'business:rules:add' WHERE `menu_id` = 2055;
UPDATE `sys_menu` SET `perms` = 'business:rules:export' WHERE `menu_id` = 2056;
UPDATE `sys_menu` SET `perms` = 'business:rules:edit' WHERE `menu_id` = 2057;
UPDATE `sys_menu` SET `perms` = 'business:rules:query' WHERE `menu_id` = 2058;

-- =============================================
-- 第八步：修改物料管理(menu_id=2122)的权限标识
-- system:device:xxx -> business:device:xxx
-- =============================================

-- 主菜单权限
UPDATE `sys_menu` SET `perms` = 'business:device:list' WHERE `menu_id` = 2122;

-- 按钮权限
UPDATE `sys_menu` SET `perms` = 'business:device:query' WHERE `menu_id` = 2123;
UPDATE `sys_menu` SET `perms` = 'business:device:add' WHERE `menu_id` = 2124;
UPDATE `sys_menu` SET `perms` = 'business:device:edit' WHERE `menu_id` = 2125;
UPDATE `sys_menu` SET `perms` = 'business:device:remove' WHERE `menu_id` = 2126;
UPDATE `sys_menu` SET `perms` = 'business:device:export' WHERE `menu_id` = 2127;

-- 其他地方引用的device权限（如新增采购计划下的）
-- 注意：这些属于其他模块，不修改，保持system:device:xxx
-- UPDATE `sys_menu` SET `perms` = 'business:device:list' WHERE `menu_id` = 2068;

-- =============================================
-- 第九步：验证修改
-- 执行以下查询验证修改结果
-- =============================================
-- 查看业务设置下的菜单及其权限：
-- SELECT menu_id, menu_name, parent_id, order_num, perms FROM sys_menu WHERE parent_id = 2121 ORDER BY order_num;

-- 查看修改后的权限标识：
-- SELECT menu_id, menu_name, perms FROM sys_menu WHERE perms LIKE 'business:%' ORDER BY menu_id;

-- 查看根目录菜单顺序：
-- SELECT menu_id, menu_name, parent_id, order_num FROM sys_menu WHERE parent_id = 0 ORDER BY order_num;
