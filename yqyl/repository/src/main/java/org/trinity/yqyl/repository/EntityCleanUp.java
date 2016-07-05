package org.trinity.yqyl.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.trinity.common.util.Tuple2;

public class EntityCleanUp {
	private static Map<String, List<Tuple2<String, String>>> userTypeMapping = new HashMap<>();
	static {

		List<Tuple2<String, String>> userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "UserStatus"));
		userTypeMapping.put("User", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("name", "AccessRight"));
		userTypeMapping.put("Accessright", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "TokenStatus"));
		userTypeMapping.put("Token", userTypes);

	}

	public static void main(final String[] args) throws IOException {
		final File folder = new File("./src/main/java/org/trinity/yqyl/repository");

		for (final File subFolder : folder.listFiles()) {
			if (subFolder.isFile()) {
				continue;
			}

			for (final File entityFolder : subFolder.listFiles()) {
				if (entityFolder.isDirectory() && entityFolder.getName().equals("entity")) {
					process(entityFolder);

					System.out.println("Entity for " + subFolder + " has been processed.");
				}
			}
		}
	}

	private static void process(final File folder) throws IOException, FileNotFoundException {
		for (final File file : folder.listFiles()) {
			if (file.getName().endsWith("PK.java")) {
				continue;
			}

			final List<Tuple2<String, String>> userTypes = userTypeMapping
					.get(file.getName().replace(".java", ""));

			final String className = file.getName().replace(".java", "");

			final StringBuilder contentBuilder = new StringBuilder();
			try (final BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)))) {

				String line = reader.readLine();
				while (line != null) {
					contentBuilder.append(line + "\r\n");

					line = reader.readLine();
				}
			}
			String content = contentBuilder.toString();

			content = content
					.replaceAll(
							"extends org.trinity.repository.entity.AbstractAuditableEntity",
							"extends AbstractAuditableEntity")
					.replaceAll(
							"@Column\\(name=\"created_by\"\\)\\s+private String createdBy;",
							"")
					.replaceAll(
							"@Temporal\\(TemporalType.TIMESTAMP\\)\\s+@Column\\(name=\"created_date\"\\)\\s+private Date createdDate;",
							"")
					.replaceAll(
							"@Column\\(name=\"last_modified_by\"\\)\\s+private String lastModifiedBy;",
							"")
					.replaceAll(
							"@Temporal\\(TemporalType.TIMESTAMP\\)\\s+@Column\\(name=\"last_modified_date\"\\)\\s+private Date lastModifiedDate;",
							"")
					.replaceAll("private BigInteger version;", "")
					.replaceAll(
							"public String getCreatedBy\\(\\) \\{\\s+return this.createdBy;\\s+\\}",
							"")
					.replaceAll(
							"public void setCreatedBy\\(String createdBy\\) \\{\\s+this.createdBy = createdBy;\\s+\\}",
							"")
					.replaceAll(
							"public Date getCreatedDate\\(\\) \\{\\s+return this.createdDate;\\s+\\}",
							"")
					.replaceAll(
							"public void setCreatedDate\\(Date createdDate\\) \\{\\s+this.createdDate = createdDate;\\s+\\}",
							"")
					.replaceAll(
							"public String getLastModifiedBy\\(\\) \\{\\s+return this.lastModifiedBy;\\s+\\}",
							"")
					.replaceAll(
							"public void setLastModifiedBy\\(String lastModifiedBy\\) \\{\\s+this.lastModifiedBy = lastModifiedBy;\\s+\\}",
							"")
					.replaceAll(
							"public Date getLastModifiedDate\\(\\) \\{\\s+return this.lastModifiedDate;\\s+\\}",
							"")
					.replaceAll(
							"public void setLastModifiedDate\\(Date lastModifiedDate\\) \\{\\s+this.lastModifiedDate = lastModifiedDate;\\s+\\}",
							"")
					.replaceAll(
							"public BigInteger getVersion\\(\\) \\{\\s+return this.version;\\s+\\}",
							"")
					.replaceAll(
							"public void setVersion\\(BigInteger version\\) \\{\\s+this.version = version;\\s+\\}",
							"")
					.replaceAll("Object", "String")
					.replaceAll("public String getId", "public Long getId")
					.replaceAll("public void setId\\(String id\\)",
							"public void setId(Long id)")
					.replaceAll("@GeneratedValue\\(strategy=GenerationType.TABLE\\)",
							"@GeneratedValue(strategy = GenerationType.TABLE, generator = \""
									+ className
									+ "_PK_IdGenerator\") @TableGenerator(name = \""
									+ className
									+ "_PK_IdGenerator\", table = \"id_table\", pkColumnName = \"type\", pkColumnValue = \""
									+ className
									+ "_PK\", valueColumnName = \"value\", initialValue = 1, allocationSize = 1)")
					.replaceAll("private String id", "private Long id");

			if (userTypes != null) {
				for (final Tuple2<String, String> userType : userTypes) {
					content = content
							.replaceAll("String " + userType.getItem1(),
									"" + userType.getItem2() + " " + userType.getItem1())
							.replaceAll(
									"String get"
											+ userType.getItem1().substring(0, 1).toUpperCase()
											+ userType.getItem1().substring(1) + "\\(\\)",
									userType.getItem2() + " get"
											+ userType.getItem1().substring(0, 1).toUpperCase()
											+ userType.getItem1().substring(1) + "()");
				}
			}

			try (final BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(file)))) {
				writer.write(content);
			}
		}
	}
}
