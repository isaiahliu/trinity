package org.trinity.yqyl.process.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.trinity.common.dto.object.PagingDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.process.converter.IObjectConverter;

@Component
public class PagingConverter implements IObjectConverter<PagingDto, Pageable> {
    private static final String SORTING_PATTERN = "(.+?)(_(asc|desc)){0,1}";
    private static final String SORTING_SPLITTER = ",";

    @Override
    public Pageable convert(PagingDto source) {
        if (source == null) {
            source = new PagingDto();
        }

        final String sortedBy = source.getSortedBy();
        final Pattern pattern = Pattern.compile(SORTING_PATTERN);

        final List<Order> orders = new ArrayList<>();
        if (!StringUtils.isEmpty(sortedBy)) {
            final String[] sortedByTokens = sortedBy.split(SORTING_SPLITTER);
            for (final String sortedByToken : sortedByTokens) {
                final Matcher matcher = pattern.matcher(sortedByToken);
                if (matcher.matches()) {
                    final String column = matcher.group(1);
                    final String direction = matcher.group(3);

                    if ("desc".equals(direction)) {
                        orders.add(new Order(Direction.DESC, column));
                    } else {
                        orders.add(new Order(column));
                    }
                }
            }
        }

        if (orders.isEmpty()) {
            return new PageRequest(source.getPageIndex(), source.getPageSize());
        } else {
            return new PageRequest(source.getPageIndex(), source.getPageSize(), new Sort(orders));
        }
    }

    @Override
    public Pageable convert(final PagingDto source, final Pageable target, final CopyPolicy copyPolicy) {
        return null;
    }

    @Override
    public Pageable convert(final PagingDto source, final Pageable target, final CopyPolicy copyPolicy,
            final RelationshipExpression relationshipExpression) {
        return null;
    }

    @Override
    public Pageable convert(final PagingDto source, final RelationshipExpression relationshipExpression) {
        return null;
    }

    @Override
    public PagingDto convertBack(final Pageable source) {
        return null;
    }

    @Override
    public PagingDto convertBack(final Pageable source, final PagingDto target, final CopyPolicy copyPolicy) {
        return null;
    }
}
