package cn.frank.foundation.equalTest;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Employee {

	private Long id = 0L;
	private String name = "";
	private String title = "";

	public Employee() {
		super();
	}
	
	public Employee(Long id, String name) {
		this(id,name,"");
	}

	public Employee(Long id, String name, String title) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		
		/**
		 * if want all field join hashcode calculation
		 * public int hashCode() {
         *     return HashCodeBuilder.reflectionHashCode(this);
         * }
		 */
		
		return new HashCodeBuilder(17,37)
		   .append(this.getId())
		   .append(this.getName())
		   .append(this.getTitle())
		   .build();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (this.getClass() !=  obj.getClass()) {
			return false;
		}
		Employee e = (Employee)obj;
		/**
		 * if want all fields join equal calculation
		 * return EqualsBuilder.reflectionEquals(this, obj);
		 */
		return new EqualsBuilder().append(this.getId(), e.getId())
				                  .append(this.getName(), e.getName())
				                  .append(this.getTitle(), e.getTitle())
				                  .isEquals();
	}
	
	
	

}
