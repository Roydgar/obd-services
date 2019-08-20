package tk.roydgar.obdservices.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public class StreamUtils {

    public static <T> Stream<T> ofNullable(List<T> args) {
        return args == null ? Stream.empty() : args.stream();
    }
}
