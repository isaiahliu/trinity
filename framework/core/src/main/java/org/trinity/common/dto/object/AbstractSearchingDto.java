package org.trinity.common.dto.object;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractSearchingDto extends PagingDto implements ISearchingDto {
    private boolean searchAll = false;
    private Long id;
    private List<String> status;

    private String rsexp;

    private RelationshipNode relationshipNode;

    private final String SPLITTER = ",";
    private final String START_TAG = "[";
    private final String END_TAG = "]";

    private final Pattern namePattern = Pattern.compile("^([A-Za-z0-9_]+)(.*)$");
    private final Pattern splitterPattern = Pattern.compile("^(\\" + SPLITTER + ")(.*)$");
    private final Pattern startPattern = Pattern.compile("^(\\" + START_TAG + ")(.*)$");;
    private final Pattern endPattern = Pattern.compile("^(\\" + END_TAG + ")(.*)$");

    @Override
    public RelationshipNode generateRelationshipNode() {
        if (relationshipNode != null) {
            return relationshipNode;
        }

        final RelationshipNode node = new RelationshipNode();
        if (rsexp != null) {
            generate(node, rsexp);
        }

        relationshipNode = node;

        return relationshipNode;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getRsexp() {
        return rsexp;
    }

    @Override
    public List<String> getStatus() {
        if (status == null) {
            status = new ArrayList<>();
        }
        return status;
    }

    @Override
    public boolean isSearchAll() {
        return searchAll;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public void setRsexp(final String rsexp) {
        this.rsexp = rsexp;
        relationshipNode = null;
    }

    @Override
    public void setSearchAll(final boolean searchAll) {
        this.searchAll = searchAll;
    }

    @Override
    public void setStatus(final List<String> status) {
        this.status = status;
    }

    private String generate(final RelationshipNode node, String expression) {
        while (true) {
            Matcher matcher = namePattern.matcher(expression);
            if (!matcher.find()) {
                return "";
            }
            final RelationshipNode child = new RelationshipNode();
            child.setName(matcher.group(1));
            node.getChildren().add(child);

            expression = matcher.group(2);

            matcher = startPattern.matcher(expression);
            if (matcher.find()) {
                expression = generate(child, matcher.group(2));
            }

            matcher = splitterPattern.matcher(expression);
            if (matcher.find()) {
                expression = matcher.group(2);
                continue;
            }
            matcher = endPattern.matcher(expression);
            if (matcher.find()) {
                return matcher.group(2);
            }
        }
    }
}
