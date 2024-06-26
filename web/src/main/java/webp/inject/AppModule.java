package webp.inject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.apache.catalina.startup.Tomcat;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

public class AppModule extends AbstractModule {
  private static Logger LOG = LoggerFactory.getLogger(AppModule.class);

  @Override
  protected void configure() {
    // 設定をDIする
    final Map<String, String> props = new HashMap<>();
    StreamSupport.stream(ConfigProvider.getConfig().getConfigSources().spliterator(), false)
        .map(ConfigSource::getProperties)
        .forEach(props::putAll);
    Names.bindProperties(binder(), props);
  }

  /** サーバのインスタンスを作って返す */
  @Provides
  @Singleton
  public Tomcat tomcat(@Named("server.port") Integer port) {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(port);
    return tomcat;
  }
}
