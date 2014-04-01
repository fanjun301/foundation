package cn.frank.foundation.annotationTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * RetentionPolicy.SOURCE 只会保留在源码  @SuppressWarnings
 * RetentionPolicy.CLASS 也会保留在.class文件，不会加载到JVM中  @Deprecated
 * RetentionPolicy.RUNTIME 加载到JVM @Test
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Test {
	String value();
	long timeout() default 3000;
}
