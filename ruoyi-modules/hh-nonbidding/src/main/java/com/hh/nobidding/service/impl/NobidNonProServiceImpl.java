package com.hh.nobidding.service.impl;

import com.hh.nobidding.domain.ComPubAttachments;
import com.hh.nobidding.domain.ComQuotation;
import com.hh.nobidding.domain.NobidNonPro;
import com.hh.nobidding.mapper.NobidNonProMapper;
import com.hh.nobidding.service.INobidNonProService;
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
public class NobidNonProServiceImpl implements INobidNonProService {
    @Resource
    private NobidNonProMapper nobidNonProMapper;

    /**
     * 查询非招标项目
     *
     * @param gid 非招标项目主键
     * @return 非招标项目
     */
    @Override
    public NobidNonPro selectNobidNonProByGid(Long gid) {
        return nobidNonProMapper.selectNobidNonProByGid(gid);
    }

    /**
     * 查询非招标项目列表
     *
     * @param nobidNonPro 非招标项目
     * @return 非招标项目
     */
    @Override
    public List<NobidNonPro> selectNobidNonProList(NobidNonPro nobidNonPro) {
        return nobidNonProMapper.selectNobidNonProList(nobidNonPro);
    }

    /**
     * 新增非招标项目
     *
     * @param nobidNonPro 非招标项目
     * @return 结果
     */
    @Override
    public int insertNobidNonPro(NobidNonPro nobidNonPro) {
        return nobidNonProMapper.insertNobidNonPro(nobidNonPro);
    }

    /**
     * 修改非招标项目
     *
     * @param nobidNonPro 非招标项目
     * @return 结果
     */
    @Override
    public int updateNobidNonPro(NobidNonPro nobidNonPro) {
        return nobidNonProMapper.updateNobidNonPro(nobidNonPro);
    }

    /**
     * 批量删除非招标项目
     *
     * @param gids 需要删除的非招标项目主键
     * @return 结果
     */
    @Override
    public int deleteNobidNonProByGids(Long[] gids) {
        return nobidNonProMapper.deleteNobidNonProByGids(gids);
    }

    /**
     * 删除非招标项目信息
     *
     * @param gid 非招标项目主键
     * @return 结果
     */
    @Override
    public int deleteNobidNonProByGid(Long gid) {
        return nobidNonProMapper.deleteNobidNonProByGid(gid);
    }

    //查询项目报价
    @Override
    public List<ComQuotation> selectQuotation(String gfId) {
        return nobidNonProMapper.selectQuotation(gfId);
    }

    //查询公共附件
    @Override
    public ComPubAttachments selectComPubAttachmentsByAid(Integer aid) {
        return nobidNonProMapper.selectComPubAttachmentsByAid(aid);
    }

    //修改公共附件
    @Override
    public int updateComPubAttachments(ComPubAttachments comPubAttachments) {
        return nobidNonProMapper.updateComPubAttachments(comPubAttachments);
    }

    //查询可创建合同的非招标项目表
    @Override
    public List<NobidNonPro> selectNobidNonProList1(NobidNonPro nobidNonPro) {
        return nobidNonProMapper.selectNobidNonProList1(nobidNonPro);
    }

    //新增公共附件
    @Override
    public int insertComPubAttachments(ComPubAttachments comPubAttachments) {
        return nobidNonProMapper.insertComPubAttachments(comPubAttachments);
    }

    //新增报价单
    @Override
    public int insertComQuotation(ComQuotation comQuotation) {
        return nobidNonProMapper.insertComQuotation(comQuotation);
    }

    //删除报价单
    @Override
    public int deleteComQuotationByGfIds(String gfId) {
        return nobidNonProMapper.deleteComQuotationByGfIds(gfId);
    }

    //查询是否存在
    @Override
    public List<ComQuotation> selectComPubAttachmentsByGfId(String gfId) {
        return nobidNonProMapper.selectComPubAttachmentsByGfId(gfId);
    }

    //修改非招标项目eid
    @Override
    public int updateNobidNonProSid(NobidNonPro nobidNonPro) {
        return nobidNonProMapper.updateNobidNonPro(nobidNonPro);
    }
}