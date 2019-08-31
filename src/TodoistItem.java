import java.util.ArrayList;

public class TodoistItem {

    private int day_order;
    private Long assigned_by_uid;
    private String due_date_utc;
    private int is_archived;
    private ArrayList<Integer> labels;
    private Long sync_id;
    private boolean all_day;
    private int in_history;
    private String date_added;
    private int checked;
    private String date_lang;
    private long id;
    private String content;
    private int indent;
    private long user_id;
    private int is_deleted;
    private int priority;
    private Long parent_id;
    private int item_order;
    private Long responsible_uid;
    private long project_id;
    private int collapsed;
    private String date_string;


    public TodoistItem() {

    }

    public TodoistItem(boolean all_day, long assigned_by_uid, int checked, int collapsed, String content, String date_added, String date_lang, String date_string, int day_order, String due_date_utc, long id, int in_history, int indent, int is_archived, int is_deleted, int item_order, ArrayList<Integer> labels, Long parent_id, int priority, long project_id, Long responsible_uid, long sync_id, long user_id) {

        this.all_day = all_day;
        this.assigned_by_uid = assigned_by_uid;
        this.checked = checked;
        this.collapsed = collapsed;
        this.content = content;
        this.date_added = date_added;
        this.date_lang = date_lang;
        this.date_string = date_string;
        this.day_order = day_order;
        this.due_date_utc = due_date_utc;
        this.id = id;
        this.in_history = in_history;
        this.indent = indent;
        this.is_archived = is_archived;
        this.is_deleted = is_deleted;
        this.item_order = item_order;
        this.labels = labels;
        this.parent_id = parent_id;
        this.priority = priority;
        this.project_id = project_id;
        this.responsible_uid = responsible_uid;
        this.sync_id = sync_id;
        this.user_id = user_id;
    }

    public boolean isAll_day() {
        return all_day;
    }

    public void setAll_day(boolean all_day) {
        this.all_day = all_day;
    }

    public Long getAssigned_by_uid() {
        return assigned_by_uid;
    }

    public void setAssigned_by_uid(Long assigned_by_uid) {
        this.assigned_by_uid = assigned_by_uid;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(int collapsed) {
        this.collapsed = collapsed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getDate_lang() {
        return date_lang;
    }

    public void setDate_lang(String date_lang) {
        this.date_lang = date_lang;
    }

    public String getDate_string() {
        return date_string;
    }

    public void setDate_string(String date_string) {
        this.date_string = date_string;
    }

    public int getDay_order() {
        return day_order;
    }

    public void setDay_order(int day_order) {
        this.day_order = day_order;
    }

    public String getDue_date_utc() {
        return due_date_utc;
    }

    public void setDue_date_utc(String due_date_utc) {
        this.due_date_utc = due_date_utc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIn_history() {
        return in_history;
    }

    public void setIn_history(int in_history) {
        this.in_history = in_history;
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

    public ArrayList<Integer> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<Integer> labels) {
        this.labels = labels;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public Long getResponsible_uid() {
        return responsible_uid;
    }

    public void setResponsible_uid(Long responsible_uid) {
        this.responsible_uid = responsible_uid;
    }

    public Long getSync_id() {
        return sync_id;
    }

    public void setSync_id(Long sync_id) {
        this.sync_id = sync_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
