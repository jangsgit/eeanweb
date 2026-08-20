package com.dae.eean.config;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServerConfig {
    // [2026-08-20 장애 조치] 운영 서버가 추가 커넥터(구 테스트용 포트)를 통해
    // 운영과 동일한 세션/DB커넥션을 그대로 공유해 테스트 트래픽이 운영에 영향을 주는
    // 문제가 확인되어, 운영 서버는 server.port(8600) 단일 포트만 사용하도록 비활성화함.
    // 테스트가 필요할 경우 반드시 별도 폴더/프로세스로 완전히 분리하여 실행할 것.

    // @Bean
    // public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
    //     return factory -> {
    //         factory.addAdditionalTomcatConnectors(createStandardConnector());
    //     };
    // }
    //
    // private Connector createStandardConnector() {
    //     Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    //     connector.setPort(8500); // HTTP 포트
    //     return connector;
    // }
}
