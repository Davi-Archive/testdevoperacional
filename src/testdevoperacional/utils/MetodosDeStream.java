package testdevoperacional.utils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MetodosDeStream {

    public static <T> T filtrarERetornarPrimeiro(List<T> listToFilter,
	    Predicate<T> mapper) {
	if (!listToFilter.isEmpty())
	    return listToFilter.stream().filter(mapper)
		    .collect(Collectors.toList()).get(0);
	return null;
    }

}
