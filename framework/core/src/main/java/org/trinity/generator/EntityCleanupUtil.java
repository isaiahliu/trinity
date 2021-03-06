package org.trinity.generator;

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
import java.util.List;
import java.util.Map;

import org.trinity.common.util.Tuple2;

public final class EntityCleanupUtil {
    public static void cleanUp(final File folder, final String packageName, final Map<String, List<Tuple2<String, String>>> userTypeMapping)
            throws FileNotFoundException, IOException {
        final List<String> allEntities = new ArrayList<>();
        for (final File subFolder : folder.listFiles()) {
            if (subFolder.isFile()) {
                continue;
            }
            final List<String> entities = new ArrayList<>();

            for (final File entityFolder : subFolder.listFiles()) {
                if (entityFolder.isDirectory() && entityFolder.getName().equals("entity")) {
                    entities.addAll(process(entityFolder, userTypeMapping));

                    System.out.println("Entity for " + subFolder.getName() + " has been processed.");
                }
            }

            if (!entities.isEmpty()) {
                entities.forEach(item -> allEntities.add(item));

                final File dataaccessFolder = new File(subFolder.getAbsolutePath() + "/dataaccess");
                if (!dataaccessFolder.exists()) {
                    dataaccessFolder.mkdir();
                }

                for (final File accessFile : dataaccessFolder.listFiles()) {
                    final String entityClass = accessFile.getName().replace("Repository.java", "").substring(1);
                    if (entities.contains(entityClass)) {
                        entities.remove(entityClass);
                    }
                }
            }
        }
    }

    private static List<String> process(final File folder, final Map<String, List<Tuple2<String, String>>> userTypeMapping)
            throws IOException, FileNotFoundException {
        final List<String> result = new ArrayList<>();
        for (final File file : folder.listFiles()) {
            if (file.getName().endsWith("PK.java") || file.getName().equals("package-info.java")) {
                continue;
            }

            List<Tuple2<String, String>> userTypes = userTypeMapping.get(file.getName().replace(".java", ""));

            if (userTypes == null) {
                userTypes = new ArrayList<>();
            }

            if (!userTypes.stream().filter(item -> item.getItem1().equals("status")).findAny().isPresent()) {
                userTypes.add(new Tuple2<>("status", "RecordStatus"));
            }

            final String className = file.getName().replace(".java", "");

            result.add(className);

            final StringBuilder contentBuilder = new StringBuilder("//Cleaned\r\n");
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

                String line = reader.readLine();
                if (line.startsWith("//Cleaned")) {
                    continue;
                }

                while (line != null) {
                    contentBuilder.append(line + "\r\n");

                    line = reader.readLine();
                }
            }
            String content = contentBuilder.toString();

            content = content.replaceAll("extends org.trinity.repository.entity.AbstractAuditableEntity", "extends AbstractAuditableEntity")
                    .replaceAll("@Column\\(name=\"created_by\"\\)\\s+private String createdBy;", "")
                    .replaceAll("@Temporal\\(TemporalType.TIMESTAMP\\)\\s+@Column\\(name=\"created_date\"\\)\\s+private Date createdDate;",
                            "")
                    .replaceAll("@Column\\(name=\"last_modified_by\"\\)\\s+private String lastModifiedBy;", "")
                    .replaceAll(
                            "@Temporal\\(TemporalType.TIMESTAMP\\)\\s+@Column\\(name=\"last_modified_date\"\\)\\s+private Date lastModifiedDate;",
                            "")
                    .replaceAll("private BigInteger version;", "")
                    .replaceAll("public String getCreatedBy\\(\\) \\{\\s+return this.createdBy;\\s+\\}", "")
                    .replaceAll("public void setCreatedBy\\(String createdBy\\) \\{\\s+this.createdBy = createdBy;\\s+\\}", "")
                    .replaceAll("public Date getCreatedDate\\(\\) \\{\\s+return this.createdDate;\\s+\\}", "")
                    .replaceAll("public void setCreatedDate\\(Date createdDate\\) \\{\\s+this.createdDate = createdDate;\\s+\\}", "")
                    .replaceAll("public String getLastModifiedBy\\(\\) \\{\\s+return this.lastModifiedBy;\\s+\\}", "")
                    .replaceAll(
                            "public void setLastModifiedBy\\(String lastModifiedBy\\) \\{\\s+this.lastModifiedBy = lastModifiedBy;\\s+\\}",
                            "")
                    .replaceAll("public Date getLastModifiedDate\\(\\) \\{\\s+return this.lastModifiedDate;\\s+\\}", "")
                    .replaceAll(
                            "public void setLastModifiedDate\\(Date lastModifiedDate\\) \\{\\s+this.lastModifiedDate = lastModifiedDate;\\s+\\}",
                            "")
                    .replaceAll("public BigInteger getVersion\\(\\) \\{\\s+return this.version;\\s+\\}", "")
                    .replaceAll("public void setVersion\\(BigInteger version\\) \\{\\s+this.version = version;\\s+\\}", "")
                    .replaceAll("Object", "String").replaceAll("public String getId", "public Long getId")
                    .replaceAll("public void setId\\(String id\\)", "public void setId(Long id)")
                    .replaceAll("@GeneratedValue\\(strategy=GenerationType.TABLE\\)",
                            "@GeneratedValue(strategy = GenerationType.TABLE, generator = \"" + className
                                    + "_PK_IdGenerator\") @TableGenerator(name = \"" + className
                                    + "_PK_IdGenerator\", table = \"id_table\", pkColumnName = \"type\", pkColumnValue = \"" + className
                                    + "_PK\", valueColumnName = \"value\", initialValue = 1, allocationSize = 1)")
                    .replaceAll("private String id", "private Long id").replaceAll("BigInteger", "Long");

            if (userTypes != null) {
                for (final Tuple2<String, String> userType : userTypes) {
                    content = content.replaceAll("String " + userType.getItem1(), "" + userType.getItem2() + " " + userType.getItem1())
                            .replaceAll(
                                    "String get" + userType.getItem1().substring(0, 1).toUpperCase() + userType.getItem1().substring(1)
                                            + "\\(\\)",
                                    userType.getItem2() + " get" + userType.getItem1().substring(0, 1).toUpperCase()
                                            + userType.getItem1().substring(1) + "()");
                }
            }

            try (final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                writer.write(content);
            }

            System.out.println("Processed: " + className);
        }
        return result;
    }
}
