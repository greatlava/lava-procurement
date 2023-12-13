package com.ruoyi.system.api;


import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.system.api.domain.BidTender;
import com.ruoyi.system.api.domain.BidWinningResults;
import com.ruoyi.system.api.factory.BidWinningResultFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId="RemotebidWinningResultsService",value = ServiceNameConstants.BIDDING_SERVICE,
        fallbackFactory = BidWinningResultFactory.class)
public interface RemoteBidWinningResultsService {

    @PostMapping("/tender/addTender")
    public AjaxResult insertBidTender(@RequestBody BidTender bidTender);
}
