package tk.roydgar.obdservices.config.google;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
@ConditionalOnProperty(value = "pub-sub.enabled", havingValue = "true")
@Slf4j
public class PubSubConfig {
    @Bean
    public MessageChannel pubsubInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            MessageChannel pubsubInputChannel,
            PubSubTemplate pubSubTemplate,
            @Value("${pub-sub.subscription-name}") String carLogSubscriptionName) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, carLogSubscriptionName);
        adapter.setOutputChannel(pubsubInputChannel);
        return adapter;
    }
}
