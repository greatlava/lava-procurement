package com.ruoyi.system.api.factory;


import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.system.api.RemoteBidWinningResultsService;
import com.ruoyi.system.api.domain.BidTender;
import com.ruoyi.system.api.domain.BidWinningResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class BidWinningResultFactory implements FallbackFactory<RemoteBidWinningResultsService> {

    private static final Logger log = LoggerFactory.getLogger(BidWinningResultFactory.class);

    @Override
    public RemoteBidWinningResultsService create(Throwable cause) {
        log.error("用户服务调用失败:{}", cause.getMessage());
        return new RemoteBidWinningResultsService() {
            @Override
            public AjaxResult insertBidTender(BidTender bidTender){
                return null;
            }
        };
    }
}
