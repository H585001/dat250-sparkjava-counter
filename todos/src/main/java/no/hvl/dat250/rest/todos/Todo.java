package no.hvl.dat250.rest.todos;

import java.util.Objects;
import java.util.UUID;

import com.google.gson.Gson;

public class Todo {
    private Long id;
    private final String summary;
    private final String description;

    public Todo(Long id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public Todo(String summary, String description) {
        this(UUID.randomUUID().getMostSignificantBits(), summary, description);
    }

    /**
     * ID might be null!
     */
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }
    
	String toJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(this);
    	
    	return jsonInString;
    }

    // Do not change equals and hashcode!

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(summary, todo.summary) && Objects.equals(description, todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summary, description);
    }

	@Override
	public String toString() {
		return "Todo [id=" + id + ", summary=" + summary + ", description=" + description + "]";
	}
}
