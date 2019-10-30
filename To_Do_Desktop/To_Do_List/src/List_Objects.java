import java.io.Serializable;

public class List_Objects implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4540154712508232059L;
	private String content;
	private boolean done;
	
	public List_Objects(String content, boolean done) {
		this.content=content;
		this.done=done;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	
	
	

}
