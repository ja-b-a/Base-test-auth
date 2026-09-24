package learn.qa.config;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public enum LocalConfig implements Config {
  INSTANCE;

  @Nonnull
  @Override
  public String frontUrl() {
    return "<front-url>";
  }
}