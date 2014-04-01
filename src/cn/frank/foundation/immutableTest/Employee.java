package cn.frank.foundation.immutableTest;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * create one immutable class
 * <p>使用private final定义属性
 * <p>提供带参数的构造器
 * <p>仅为该类的属性提供get方法，不提供set方法
 * <p>如有必要，重写hashcode 和 equal方法 --> 主要考虑是缓存不可变变量的
 * @author jfan
 *
 */
public class Employee {

	private final Long id;
	private final String name;
	private final String title;

	public Employee(Long id, String name, String title) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
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

	@Override
	public String toString() {
		return String.format("ID: %s NAME: %s TITLE:%s ", this.getId(), this.getName(), this.getTitle());
	}
	
}
