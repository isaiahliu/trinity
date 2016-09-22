package org.trinity.yqyl.rest.generator;

import java.io.File;
import java.io.IOException;

import org.trinity.generator.CrudRestGenerator;

public final class CrudRestControllerGenerator {
	public static void main(final String[] args) throws IOException {
		CrudRestGenerator.generate(new File("../"), "business");
	}
}
