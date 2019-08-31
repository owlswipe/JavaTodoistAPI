public class TodoistProject {

    private String name;
    private int color;
    private int is_deleted;
    private int collapsed;
    private long id;
    private Long parent_id;

    public int getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(int collapsed) {
        this.collapsed = collapsed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }

    public int getIs_archived() {
        return is_archived;
    }

    public void setIs_archived(int is_archived) {
        this.is_archived = is_archived;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getItem_order() {
        return item_order;
    }

    public void setItem_order(int item_order) {
        this.item_order = item_order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    private int item_order;
    private int indent;
    private boolean shared;
    private int is_archived;

    public TodoistProject() {
    }

    public TodoistProject(int collapsed, int color, long id, int indent, int is_archived,
                          int is_deleted, int item_order, String name, Long parent_id, boolean shared) {
        this.collapsed = collapsed;
        this.color = color;
        this.id = id;
        this.indent = indent;
        this.is_archived = is_archived;
        this.is_deleted = is_deleted;
        this.item_order = item_order;
        this.name = name;
        this.parent_id = parent_id;
        this.shared = shared;
    }
}
