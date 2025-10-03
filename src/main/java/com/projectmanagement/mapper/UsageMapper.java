package com.projectmanagement.mapper;

import org.springframework.stereotype.Component;

import com.projectmanagement.entity.Usage;

@Component
public class UsageMapper {
    public Usage toUsage(UsageMapper usage) {
        Usage use = new Usage();
        return use ;
    }

    public UsageMapper toUsageMapper(Usage usage) {
        UsageMapper usageMapper = new UsageMapper();
        return usageMapper;
    }
}
