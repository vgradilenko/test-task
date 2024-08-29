package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:${remote.config.path}"})
public interface ConfigProps extends Config {
    @DefaultValue("http://127.0.0.1/")
    @Key("base.url")
    String baseUrl();
}