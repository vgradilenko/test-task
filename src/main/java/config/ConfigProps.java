package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:${remote.config.path}",
        "file:${common.config.path}"
})
public interface ConfigProps extends Config {
    @Key("base.url")
    String getBaseUrl();

    @Key("expected.response.time")
    int getExpectedResponseTime();

    @Key("connection.timeout")
    int getConnectionTimeout();
}