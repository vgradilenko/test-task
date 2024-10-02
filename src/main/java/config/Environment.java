package config;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Environment {
    REMOTE, LOCAL;

    public static Environment getByCommandLineProp() {
        String env = Optional.ofNullable(System.getenv("env")).orElse(REMOTE.name());
        Objects.requireNonNull(env, "Environment is not defined. Specify it by '-Denv=<env>'");
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(env))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Couldn't find '" + env + "' environment. Values: "
                                + Arrays.toString(values())));
    }
}
