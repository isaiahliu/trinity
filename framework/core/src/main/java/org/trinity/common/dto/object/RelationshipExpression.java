package org.trinity.common.dto.object;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class RelationshipExpression {
    private final String name;
    private List<RelationshipExpression> children;

    private final Pattern pattern = Pattern.compile("([A-Z])");

    public RelationshipExpression() {
        this("");
    }

    public RelationshipExpression(final String name) {
        this.name = pattern.matcher(name).replaceAll("_$1").toUpperCase();
    }

    public <T extends Enum<T>> RelationshipExpression(final T t) {
        this.name = t.name();
    }

    public List<RelationshipExpression> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public String getName() {
        return name;
    }

    public <T extends Enum<T>> T getName(final Class<T> clazz) {
        try {
            return Enum.valueOf(clazz, name.toUpperCase());
        } catch (final IllegalArgumentException e) {
            return Enum.valueOf(clazz, "NA");
        }
    }

    public void setChildren(final List<RelationshipExpression> children) {
        this.children = children;
    }
}
