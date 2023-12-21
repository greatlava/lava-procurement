package com.hh.pms.sae.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hh.pms.sae.mapper.NobidSupNonProMapper;
import com.hh.pms.sae.domain.NobidSupNonPro;
import com.hh.pms.sae.service.INobidSupNonProService;

/**
 * 供应商非招标项目Service业务层处理
 *
 * @author ruoyi
 * @date 2023-12-20
 */
@Service
public class NobidSupNonProServiceImpl implements INobidSupNonProService {
    @Autowired
    private NobidSupNonProMapper nobidSupNonProMapper;

    /**
     * 查询供应商非招标项目
     *
     * @param gfId 供应商非招标项目主键
     * @return 供应商非招标项目
     */
    @Override
    public NobidSupNonPro selectNobidSupNonProByGfId(String gfId) {
        return nobidSupNonProMapper.selectNobidSupNonProByGfId(gfId);
    }

    /**
     * 查询供应商非招标项目列表
     *
     * @param nobidSupNonPro 供应商非招标项目
     * @return 供应商非招标项目
     */
    @Override
    public List<NobidSupNonPro> selectNobidSupNonProList(NobidSupNonPro nobidSupNonPro) {
        return nobidSupNonProMapper.selectNobidSupNonProList(nobidSupNonPro);
    }

    /**
     * 新增供应商非招标项目
     *
     * @param nobidSupNonPro 供应商非招标项目
     * @return 结果
     */
    @Override
    public int insertNobidSupNonPro(NobidSupNonPro nobidSupNonPro) {
        return nobidSupNonProMapper.insertNobidSupNonPro(nobidSupNonPro);
    }

    /**
     * 修改供应商非招标项目
     *
     * @param nobidSupNonPro 供应商非招标项目
     * @return 结果
     */
    @Override
    public int updateNobidSupNonPro(NobidSupNonPro nobidSupNonPro) {
        return nobidSupNonProMapper.updateNobidSupNonPro(nobidSupNonPro);
    }

    /**
     * 批量删除供应商非招标项目
     *
     * @param gfIds 需要删除的供应商非招标项目主键
     * @return 结果
     */
    @Override
    public int deleteNobidSupNonProByGfIds(String[] gfIds) {
        return nobidSupNonProMapper.deleteNobidSupNonProByGfIds(gfIds);
    }

    /**
     * 删除供应商非招标项目信息
     *
     * @param gfId 供应商非招标项目主键
     * @return 结果
     */
    @Override
    public int deleteNobidSupNonProByGfId(String gfId) {
        return nobidSupNonProMapper.deleteNobidSupNonProByGfId(gfId);
    }

    @Override
    public NobidSupNonPro checkIsJoin(Long hid, String gf_id) {
        return nobidSupNonProMapper.checkIsJoin(hid, gf_id);
    }
}
