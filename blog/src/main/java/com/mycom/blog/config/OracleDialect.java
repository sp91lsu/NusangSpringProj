package com.mycom.blog.config;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class OracleDialect extends Oracle10gDialect {

	public OracleDialect() {
		registerFunction("CALC_DISTANCE", new StandardSQLFunction("CALC_DISTANCE",StandardBasicTypes.DOUBLE));
	}
}
