package team.pixel.schedule_builder_backend.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;




@Configuration
@EnableElasticsearchRepositories(basePackages = "team.pixel.schedule_builder_backend.repository")
@Slf4j
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.rest.uri}")
    private String elasticSearchUris;

    @Value("${spring.elasticsearch.rest.username}")
    private String elasticSearchUsername;

    @Value("${spring.elasticsearch.rest.password}")
    private String elasticSearchPassword;


    //Both these beans required for ElasticsearchRepository!! Do not modify
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(elasticSearchUsername, elasticSearchPassword));
        return new RestHighLevelClient(RestClient.builder(
                        HttpHost.create(elasticSearchUris))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(restHighLevelClient());
    }

}

