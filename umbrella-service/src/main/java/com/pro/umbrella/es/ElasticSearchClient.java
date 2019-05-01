package com.pro.umbrella.es;

import com.google.common.primitives.Ints;
import com.pro.umbrella.common.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ElasticSearchClient implements InitializingBean, DisposableBean {

    private TransportClient client = null;

    @Value("${es.hosts}")
    private String servers;

    @Value("${es.cluster.name}")
    private String clusterName;

    @Override
    public void afterPropertiesSet() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        List<InetSocketTransportAddress> addresses = CommonConstants.SPLITTER_COMMA.splitToList(servers).stream()
                .map(x -> {
                    List<String> address = CommonConstants.SPLITTER_COLON.splitToList(x);

                    try {
                        int port = 9200;
                        if (address.size() == 2 && StringUtils.isNotBlank(address.get(1))) {
                            Integer integer = Ints.tryParse(address.get(1));
                            if (integer != null) {
                                port = integer;
                            }
                        }
                        return new InetSocketTransportAddress(InetAddress.getByName(address.get(0)), port);
                    } catch (UnknownHostException e) {
                        System.out.println("[ElasticSearch-Client]无效的地址:[{}]" + x);
                        return null;
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());

        try {
            Settings settings = Settings.builder().put("cluster.name", clusterName)
                    .put("client.transport.ping_timeout", "5s").put("client.transport.sniff", false).build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddresses(addresses.toArray(new InetSocketTransportAddress[]{}));
            System.out.println("[ElasticSearch-Client]服务连接成功");
        } catch (Exception e) {
            System.out.println("[ElasticSearch-Client]服务连接失败"+ e);
        }
    }

    public TransportClient getClient() {
        return client;
    }


    @Override
    public void destroy() throws Exception {
        if (client != null) {
            client.close();
        }
    }
}
