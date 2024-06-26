package webp;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

import webp.inject.AppModule;

/** Main App */
public class App {
  private static Logger LOG = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    LOG.info("start.");
    Injector injector = Guice.createInjector(new AppModule());
    try {
      Tomcat tomcat = injector.getInstance(Tomcat.class);
      tomcat.start();
      tomcat.getServer().await();
      LOG.info("completed.");
    } catch (Exception e) {
      LOG.info("error.");
      LOG.error(e.getMessage(), e);
    }
  }
}
