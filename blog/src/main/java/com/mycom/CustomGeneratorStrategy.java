package com.mycom;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

/**
 * @author jason, Moon (jason.moon.kr@gmail.com)
 * @since 2018-08-30
 */
public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {
	@Override
	public String getJavaClassName(Definition definition, Mode mode) {
		return 'J' + super.getJavaClassName(definition, mode);
	}
}