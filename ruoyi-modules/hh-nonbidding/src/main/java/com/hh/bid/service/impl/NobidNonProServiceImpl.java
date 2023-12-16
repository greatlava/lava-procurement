package com.hh.bid.service.impl;

import com.hh.bid.domain.NobidNonPro;
import com.hh.bid.mapper.NobidNonProMapper;
import com.hh.bid.service.INobidNonProService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 非招标项目Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-11
 */
@Service
@Transactional
public class NobidNonProServiceImpl implements INobidNonProService
{
    @Resource
    private NobidNonProMapper nobidNonProMapper;

    /**
     * 查询非招标项目
     *
     * @param gid 非招标项目主键
     * @return 非招标项目
     */
    @Override
    public NobidNonPro selectNobidNonProByGid(Long gid)
    {
        return nobidNonProMapper.selectNobidNonProByGid(gid);
    }

    /**
     * 查询非招标项目列表
     *
     * @param nobidNonPro 非招标项目
     * @return 非招标项目
     */
    @Override
    public List<NobidNonPro> selectNobidNonProList(NobidNonPro nobidNonPro)
    {
        return nobidNonProMapper.selectNobidNonProList(nobidNonPro);
    }

    /**
     * 新增非招标项目
     *
     * @param nobidNonPro 非招标项目
     * @return 结果
     */
    @Override
    public int insertNobidNonPro(NobidNonPro nobidNonPro)
    {
        return nobidNonProMapper.insertNobidNonPro(nobidNonPro);
    }

    /**
     * 修改非招标项目
     *
     * @param nobidNonPro 非招标项目
     * @return 结果
     */
    @Override
    public int updateNobidNonPro(NobidNonPro nobidNonPro)
    {
        return nobidNonProMapper.updateNobidNonPro(nobidNonPro);
    }

    /**
     * 批量删除非招标项目
     *
     * @param gids 需要删除的非招标项目主键
     * @return 结果
     */
    @Override
    public int deleteNobidNonProByGids(Long[] gids)
    {
        return nobidNonProMapper.deleteNobidNonProByGids(gids);
    }

    /**
     * 删除非招标项目信息
     *
     * @param gid 非招标项目主键
     * @return 结果
     */
    @Override
    public int deleteNobidNonProByGid(Long gid)
    {
        return nobidNonProMapper.deleteNobidNonProByGid(gid);
    }
}