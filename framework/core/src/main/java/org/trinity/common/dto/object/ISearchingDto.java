package org.trinity.common.dto.object;

import java.util.ArrayList;
import java.util.List;

public interface ISearchingDto extends IPagingDto {
    public static final class RelationshipNode {
        private String name;
        private List<RelationshipNode> children;

        public RelationshipNode() {
            this("");
        }

        public RelationshipNode(final String name) {
            this.name = name;
        }

        public List<RelationshipNode> getChildren() {
            if (children == null) {
                children = new ArrayList<>();
            }
            return children;
        }

        public String getName() {
            return name;
        }

        public void setChildren(final List<RelationshipNode> children) {
            this.children = children;
        }

        public void setName(final String name) {
            this.name = name;
        }
    }

    RelationshipNode generateRelationshipNode();

    Long getId();

    String getRelationshipExpression();

    List<String> getStatus();

    boolean isSearchAll();

    void setId(final Long id);

    void setRelationshipExpression(final String relationshipExpression);

    void setSearchAll(boolean searchAll);

    void setStatus(List<String> status);
}
