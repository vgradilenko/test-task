package config;

import org.aeonbits.owner.ConfigFactory;

import java.util.Objects;

public class ConfigProvider {
    private static final String CONFIG_DIR = "src/test/resources";
    public static final ConfigProps CONFIG_PROPS;

    static {
        Environment env = Environment.getByCommandLineProp();
        String s = Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResource(env.name().toLowerCase() + ".properties")).toString();
        System.out.println("TEST PATH " + s);

//        switch (env) {
//            case LOCAL, REMOTE ->
//                    ConfigFactory.setProperty("remote.config.path", s);
//            default -> throw new RuntimeException("Provide implementation for '" + env + "' environment");
//        }
        ConfigFactory.setProperty("common.config.path", CONFIG_DIR + "/common.properties");
        ConfigFactory.setProperty("remote.config.path", CONFIG_DIR + "/remote.properties");
        CONFIG_PROPS = ConfigFactory.create(ConfigProps.class, System.getProperties());
    }
}
