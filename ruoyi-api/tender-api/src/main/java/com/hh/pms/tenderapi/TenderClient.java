package com.hh.pms.tenderapi;

import com.hh.pms.domain.BidTender;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "hh-bidding",fallback = TenderClientImpl.class)
public interface TenderClient {
    @PostMapping("addTender")
    public AjaxResult add(@RequestBody BidTender bidTender);
}
