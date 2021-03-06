package com.suda.tree.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "secure.ignored")
@Data
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

}