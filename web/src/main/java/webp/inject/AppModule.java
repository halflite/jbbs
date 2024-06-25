package webp.inject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import jakarta.inject.Singleton;

public class AppModule extends AbstractModule {

  /** 設定ファイルの値ををDIに入れる */
  @Provides
  @Singleton
  public void config() {
    final Map<String, String> props = new HashMap<>();
    StreamSupport.stream(ConfigProvider.getConfig().getConfigSources().spliterator(), false)
        .map(ConfigSource::getProperties)
        .forEach(props::putAll);
    Names.bindProperties(binder(), props);
  }
}
