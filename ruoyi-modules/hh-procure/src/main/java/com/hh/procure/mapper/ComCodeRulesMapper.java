package com.hh.procure.mapper;

import com.hh.procure.domain.ComCodeRules;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
public interface ComCodeRulesMapper 
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ComCodeRules selectComCodeRulesById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param comCodeRules 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ComCodeRules> selectComCodeRulesList(ComCodeRules comCodeRules);

    /**
     * 新增【请填写功能名称】
     *
     * @param comCodeRules 【请填写功能名称】
     * @return 结果
     */
    public int insertComCodeRules(ComCodeRules comCodeRules);

    /**
     * 修改【请填写功能名称】
     *
     * @param comCodeRules 【请填写功能名称】
     * @return 结果
     */
    public int updateComCodeRules(ComCodeRules comCodeRules);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteComCodeRulesById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteComCodeRulesByIds(Long[] ids);

    /**
     * 根据表名查询编号规则
     * @param str
     * @return
     */
    public ComCodeRules selectComCodeRulesByTargetForm(String str);
}
