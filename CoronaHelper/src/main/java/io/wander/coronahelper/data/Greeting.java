package io.wander.coronahelper.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Greeting  {//extends RepresentationModel<Greeting> {

	@Id
	@GeneratedValue
	private long id;
	private String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public Greeting() {
		// required by JPA .. even the author does not know
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Greeting [id=" + id + ", content=" + content + "]";
	}

}