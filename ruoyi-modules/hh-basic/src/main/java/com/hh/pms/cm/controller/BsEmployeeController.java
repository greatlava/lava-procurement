package com.hh.pms.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hh.pms.cm.domain.BsEmployee;
import com.ruoyi.auth.service.SysRecordLogService;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.IpUtils;
import com.ruoyi.common.core.utils.uuid.IdUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.hh.pms.cm.service.IBsEmployeeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 员工Controller
 *
 * @author ruoyi
 * @date 2023-11-19
 */
@RestController
@RequestMapping("/employee")
public class BsEmployeeController extends BaseController {
    @Autowired
    private IBsEmployeeService bsEmployeeService;

    @Autowired(required = false)
    private SysRecordLogService recordLogService;

    @Autowired
    private RedisService redisService;
    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static long expireTime = CacheConstants.EXPIRATION;

    /**
     * 查询员工列表
     */
    @RequiresPermissions("system:employee:list")
    @GetMapping("/list")
    public TableDataInfo list(BsEmployee bsEmployee) {
        startPage();
        List<BsEmployee> list = bsEmployeeService.selectBsEmployeeList(bsEmployee);
        return getDataTable(list);
    }

    /**
     * 导出员工列表
     */
    @RequiresPermissions("system:employee:export")
    @Log(title = "员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BsEmployee bsEmployee) {
        List<BsEmployee> list = bsEmployeeService.selectBsEmployeeList(bsEmployee);
        ExcelUtil<BsEmployee> util = new ExcelUtil<BsEmployee>(BsEmployee.class);
        util.exportExcel(response, list, "员工数据");
    }

    /**
     * 获取员工详细信息
     */
    @RequiresPermissions("system:employee:query")
    @GetMapping(value = "/{pid}")
    public AjaxResult getInfo(@PathVariable("pid") Long pid) {
        return success(bsEmployeeService.selectBsEmployeeByPid(pid));
    }

    /**
     * 新增员工
     */
    @RequiresPermissions("system:employee:add")
    @Log(title = "员工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BsEmployee bsEmployee) {
        return toAjax(bsEmployeeService.insertBsEmployee(bsEmployee));
    }

    /**
     * 修改员工
     */
    @RequiresPermissions("system:employee:edit")
    @Log(title = "员工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BsEmployee bsEmployee) {
        return toAjax(bsEmployeeService.updateBsEmployee(bsEmployee));
    }

    /**
     * 删除员工
     */
    @RequiresPermissions("system:employee:remove")
    @Log(title = "员工", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pids}")
    public AjaxResult remove(@PathVariable Long[] pids) {
        return toAjax(bsEmployeeService.deleteBsEmployeeByPids(pids));
    }


    @PostMapping("/login")
    public R<?> login(String username, String password) {
        // 用户登录
        BsEmployee employee = loginEmp(username, password);
        // 获取登录token
        return R.ok(createToken(employee));
    }

    public BsEmployee loginEmp(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
            throw new ServiceException("用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围");
            throw new ServiceException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围");
            throw new ServiceException("用户名不在指定范围");
        }
        // IP黑名单校验
        String blackStr = Convert.toStr(redisService.getCacheObject(CacheConstants.SYS_LOGIN_BLACKIPLIST));
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "很遗憾，访问IP已被列入系统黑名单");
            throw new ServiceException("很遗憾，访问IP已被列入系统黑名单");
        }
        // 查询用户信息
        R<BsEmployee> userResult = bsEmployeeService.loginEmp(username, password);

        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在");
            throw new ServiceException("登录用户：" + username + " 不存在");
        }

        if (R.FAIL == userResult.getCode()) {
            throw new ServiceException(userResult.getMsg());
        }

        BsEmployee userInfo = userResult.getData();
        recordLogService.recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        return userInfo;
    }

    public Map<String, Object> createToken(BsEmployee loginUser) {
        String token = IdUtils.fastUUID();
        Long userId = loginUser.getPid();
        String userName = loginUser.getpName();
        loginUser.setToken(token);
        loginUser.setPid(userId);
        loginUser.setpName(userName);
        refreshToken(loginUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, token);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("access_token", JwtUtils.createToken(claimsMap));
        rspMap.put("expires_in", expireTime);
        return rspMap;
    }

    public void refreshToken(BsEmployee loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }
}
