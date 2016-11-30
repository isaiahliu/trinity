package org.trinity.common.dto.washer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Put this method on the setters to tell the washer to keep this value.
 * 
 * @author Isaiah Liu
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KeepAfterWashed {

	/**
	 * This defined the scenario where the annotation works. If there's no value
	 * here, the data will always be kept.
	 * 
	 * @return
	 */
	Class<?>[] value() default {};
}
