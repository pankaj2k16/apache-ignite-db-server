package com.deloitte.client.apacheigniteclient.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataPageEvictionMode;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteConfiguration {
    @Bean
    public Ignite ignite() {
        org.apache.ignite.configuration.IgniteConfiguration cfg = new org.apache.ignite.configuration.IgniteConfiguration();
        cfg.setDataStorageConfiguration(getDataStorageConfiguration());
        cfg.setCacheConfiguration(getCacheConfiguration());

        Ignite ignite = Ignition.start(cfg);
        return ignite;
    }

    private CacheConfiguration getCacheConfiguration() {
        CacheConfiguration<String, String> cc = new CacheConfiguration<>();
        cc.setName("dummy");
        cc.setOnheapCacheEnabled(false);
        cc.setBackups(1);
        cc.setCacheMode(CacheMode.REPLICATED);
        cc.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        return cc;
    }

    private static @NotNull DataStorageConfiguration getDataStorageConfiguration() {
        DataRegionConfiguration drc = new DataRegionConfiguration();
        drc.setName("My DataRegion");
        drc.setInitialSize(10 * 1024 * 1024);
        drc.setMaxSize(40 * 1024 * 1024);
        drc.setPageEvictionMode(DataPageEvictionMode.RANDOM_2_LRU);

        DataStorageConfiguration dsc = new DataStorageConfiguration();
        dsc.setDefaultDataRegionConfiguration(drc);
        return dsc;
    }
}
