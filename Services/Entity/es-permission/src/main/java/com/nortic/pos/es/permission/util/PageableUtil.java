package com.nortic.pos.es.permission.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

public class PageableUtil {

    public static PageRequest buildPageable(Integer page, Integer size, List<String> sort) {
        return PageRequest.of(page - 1, size, Sort.by(buildSort(sort)));
    }

    private static List<Sort.Order> buildSort(List<String> sort) {

        if (sort == null || sort.isEmpty()) {
            return Collections.emptyList();
        }

        if (sort.size() == 2
                && isDirection(sort.get(1))) {

            return List.of(
                    new Sort.Order(
                            Sort.Direction.fromString(sort.get(1)),
                            sort.get(0)
                    )
            );
        }

        return sort.stream().map(value -> {

            var values = value.split(",");

            var property = values[0];

            var direction = values.length > 1
                    ? Sort.Direction.fromString(values[1])
                    : Sort.Direction.ASC;

            return new Sort.Order(direction, property);

        }).toList();
    }

    private static boolean isDirection(String value) {

        return value.equalsIgnoreCase("ASC")
                || value.equalsIgnoreCase("DESC");
    }

}
