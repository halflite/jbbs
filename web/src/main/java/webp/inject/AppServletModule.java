package webp.inject;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppServletModule extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    // TODO 後で
    return null;
  }
}
