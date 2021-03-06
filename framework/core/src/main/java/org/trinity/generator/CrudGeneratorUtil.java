package org.trinity.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CrudGeneratorUtil {
    private static abstract class AbstractGenerator implements IGenerator {
        private final File projectFolder;
        private final List<String> entities;
        private final String targetFolderPath;
        private final String entitySchema;

        public AbstractGenerator(final File projectFolder, final String entitySchema, final List<String> entities,
                final String targetFolderPath) {
            super();
            this.projectFolder = projectFolder;
            this.entities = entities;
            this.targetFolderPath = targetFolderPath;
            this.entitySchema = entitySchema;
        }

        @Override
        public void generate() {
            final File folder = new File(projectFolder + "/" + targetFolderPath);

            final List<String> existingFiles = Arrays.stream(folder.listFiles()).filter(item -> {

                try (final FileInputStream reader = new FileInputStream(item)) {
                    return reader.available() > 0;
                } catch (final Throwable e) {
                    return false;
                }

            }).map(item -> item.getName()).collect(Collectors.toList());

            entities.removeIf(item -> existingFiles.contains(getFileNameForEntity(item)));

            for (final String entity : entities) {
                try (final PrintWriter writer = new PrintWriter(
                        new OutputStreamWriter(new FileOutputStream(folder.getCanonicalPath() + "/" + getFileNameForEntity(entity))))) {
                    write(writer, projectFolder.getName(), entitySchema, entity);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        protected abstract String getFileNameForEntity(String entity);

        protected abstract void write(PrintWriter writer, String projectName, String entitySchema, String entity);
    }

    private static class ConverterGenerator extends AbstractGenerator {
        public ConverterGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "process/src/main/java/org/trinity/" + projectFolder.getName() + "/process/converter");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return entity + "Converter.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {

            writer.println(String.format("package org.trinity.%1$s.process.converter;", projectName));
            writer.println(String.format(""));
            writer.println(String.format("import org.springframework.beans.factory.annotation.Autowired;"));
            writer.println(String.format("import org.springframework.stereotype.Component;"));
            writer.println(String.format("import org.trinity.common.dto.object.LookupDto;"));
            writer.println(String.format("import org.trinity.message.ILookupMessage;"));
            writer.println(String.format("import org.trinity.common.util.Tuple2;"));
            writer.println(String.format("import org.trinity.common.dto.object.RelationshipExpression;"));
            writer.println(String.format("import org.trinity.process.converter.AbstractLookupSupportObjectConverter;"));
            writer.println(String.format("import org.trinity.process.converter.IObjectConverter;"));
            writer.println(String.format("import org.trinity.%1$s.common.message.dto.domain.%2$sDto;", projectName, entity));
            writer.println(String.format("import org.trinity.%1$s.repository.%2$s.entity.%3$s;", projectName, entitySchema, entity));
            writer.println(String.format(""));
            writer.println(String.format("@Component"));
            writer.println(
                    String.format("public class %1$sConverter extends AbstractLookupSupportObjectConverter<%1$s, %1$sDto> {", entity));
            writer.println(String.format("private static enum %1$sRelationship {", entity));
            writer.println(String.format("NA"));
            writer.println(String.format("}"));
            writer.println(String.format(""));
            writer.println(String.format("@Autowired"));
            writer.println(String.format(
                    "public %1$sConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {",
                    entity));
            writer.println(String.format("super(lookupConverter);"));
            writer.println(String.format("}"));
            writer.println(String.format(""));
            writer.println(String.format("@Override"));
            writer.println(String.format(
                    "protected void convertBackInternal(final %1$sDto source, final %1$s target, final CopyPolicy copyPolicy) {", entity));
            writer.println(String.format("copyObject(source::getId, target::getId, target::setId, copyPolicy);"));
            writer.println(String.format("}"));
            writer.println(String.format(""));
            writer.println(String.format("@Override"));
            writer.println(String.format(
                    "protected void convertInternal(final %1$s source, final %1$sDto target, final CopyPolicy copyPolicy) {", entity));
            writer.println(String.format("copyObject(source::getId, target::getId, target::setId, copyPolicy);"));
            writer.println(String.format("}"));
            writer.println(String.format(""));
            writer.println(String.format("@Override"));
            writer.println(String.format("protected %1$s createFromInstance() {", entity));
            writer.println(String.format("return new %1$s();", entity));
            writer.println(String.format("}"));
            writer.println(String.format(""));
            writer.println(String.format("@Override"));
            writer.println(String.format("protected %1$sDto createToInstance() {", entity));
            writer.println(String.format("return new %1$sDto();", entity));
            writer.println(String.format("}", entity));
            writer.println(String.format("@Override", entity));
            writer.println(String.format(
                    "protected void convertRelationshipInternal(final %1$s source, final %1$sDto target, final RelationshipExpression relationshipExpression) {",
                    entity));
            writer.println(String.format("switch (relationshipExpression.getName(%1$sRelationship.class)) {", entity));
            writer.println(String.format("case NA:", entity));
            writer.println(String.format("default:", entity));
            writer.println(String.format("break;", entity));
            writer.println(String.format("}", entity));
            writer.println(String.format("}", entity));

            writer.println(String.format("}", entity));
        }
    }

    private static class DataPermissionGenerator extends AbstractGenerator {
        public DataPermissionGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "process/src/main/java/org/trinity/" + projectFolder.getName() + "/process/datapermission");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return entity + "DataPermissionValidator.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.process.datapermission;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("import org.springframework.stereotype.Component;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.common.exception.IException;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.repository.%3$s.entity.%1$s;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("@Component", entity, projectName, entitySchema));
            writer.println(String.format("public class %1$sDataPermissionValidator", entity, projectName, entitySchema));
            writer.println(String.format("        extends AbstractDataPermissionValidator<%1$s> {", entity, projectName, entitySchema));
            writer.println(String.format("    @Override", entity, projectName, entitySchema));
            writer.println(
                    String.format("    public void checkSpecialPermission() throws IException {", entity, projectName, entitySchema));
            writer.println(String.format("    super.checkSpecialPermission();", entity, projectName, entitySchema));
            writer.println(String.format("    }", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("    @Override", entity, projectName, entitySchema));
            writer.println(String.format("    protected void validateData(final String username, final Long id) throws IException {",
                    entity, projectName, entitySchema));
            writer.println(String.format("    }", entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
        }

    }

    private static class DtoGenerator extends AbstractGenerator {

        public DtoGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "common/src/main/java/org/trinity/" + projectFolder.getName() + "/common/message/dto/domain");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return entity + "Dto.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.common.message.dto.domain;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.common.dto.domain.AbstractBusinessDto;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("public class %1$sDto extends AbstractBusinessDto {", entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
        }
    }

    private static interface IGenerator {
        void generate();
    }

    private static class ProcessControllerGenerator extends AbstractGenerator {

        public ProcessControllerGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "process/src/main/java/org/trinity/" + projectFolder.getName() + "/process/controller");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return entity + "ProcessController.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.process.controller;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("import org.springframework.stereotype.Service;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sDto;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sSearchingDto;", entity, projectName,
                    entitySchema));
            writer.println(String.format("import org.trinity.%2$s.process.controller.base.AbstractAutowiredCrudProcessController;", entity,
                    projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.process.controller.base.I%1$sProcessController;", entity, projectName,
                    entitySchema));
            writer.println(String.format("import org.trinity.%2$s.repository.%3$s.dataaccess.I%1$sRepository;", entity, projectName,
                    entitySchema));

            writer.println(String.format("import org.trinity.%2$s.repository.%3$s.entity.%1$s;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("@Service", entity, projectName, entitySchema));
            writer.println(String.format("public class %1$sProcessController", entity, projectName, entitySchema));
            writer.println(
                    String.format("      extends AbstractAutowiredCrudProcessController<%1$s, %1$sDto, %1$sSearchingDto, I%1$sRepository>",
                            entity, projectName, entitySchema));
            writer.println(String.format("      implements I%1$sProcessController {", entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
        }

    }

    private static class ProcessControllerInterfaceGenerator extends AbstractGenerator {

        public ProcessControllerInterfaceGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "process/src/main/java/org/trinity/" + projectFolder.getName() + "/process/controller/base");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return "I" + entity + "ProcessController.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.process.controller.base;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(
                    String.format("import org.trinity.process.controller.ICrudProcessController;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sDto;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sSearchingDto;", entity, projectName,
                    entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(
                    String.format("public interface I%1$sProcessController extends ICrudProcessController<%1$sDto, %1$sSearchingDto> {",
                            entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
        }

    }

    private static class RepositoryGenerator extends AbstractGenerator {
        public RepositoryGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "repository/src/main/java/org/trinity/" + projectFolder.getName() + "/repository/" + entitySchema + "/dataaccess");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return "I" + entity + "Repository.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.repository.business.dataaccess;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("import java.util.ArrayList;", entity, projectName, entitySchema));
            writer.println(String.format("import java.util.List;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("import javax.persistence.criteria.Predicate;", entity, projectName, entitySchema));
            writer.println(String.format("import org.springframework.data.domain.Page;", entity, projectName, entitySchema));
            writer.println(String.format("import org.springframework.data.domain.Pageable;", entity, projectName, entitySchema));
            writer.println(String.format("import org.springframework.data.jpa.domain.Specification;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.repository.repository.IJpaRepository;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sSearchingDto;", entity, projectName,
                    entitySchema));
            writer.println(String.format("import org.trinity.%2$s.repository.business.entity.%1$s;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.repository.business.entity.%1$s_;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("public interface I%1$sRepository extends IJpaRepository<%1$s, %1$sSearchingDto> {", entity,
                    projectName, entitySchema));
            writer.println(String.format("    @Override", entity, projectName, entitySchema));
            writer.println(String.format("    default Page<%1$s> query(final %1$sSearchingDto searchingDto, final Pageable pagable) {",
                    entity, projectName, entitySchema));
            writer.println(String.format("        final Specification<%1$s> specification = (root, query, cb) -> {", entity, projectName,
                    entitySchema));
            writer.println(
                    String.format("            final List<Predicate> predicates = new ArrayList<>();", entity, projectName, entitySchema));
            writer.println(String.format("            if (!searchingDto.isSearchAll()) {", entity, projectName, entitySchema));
            writer.println(String.format("            }", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("            if (searchingDto.getId() != null) {", entity, projectName, entitySchema));
            writer.println(String.format("                predicates.add(cb.equal(root.get(%1$s_.id), searchingDto.getId()));", entity,
                    projectName, entitySchema));
            writer.println(String.format("            }", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("            if (searchingDto.getStatus().isEmpty()) {", entity, projectName, entitySchema));
            writer.println(String.format("                if (!searchingDto.isSearchAllStatus()) {", entity, projectName, entitySchema));
            writer.println(String.format("                    predicates.add(cb.equal(root.get(%1$s_.status), RecordStatus.ACTIVE));",
                    entity, projectName, entitySchema));
            writer.println(String.format("                }", entity, projectName, entitySchema));
            writer.println(String.format("            } else {", entity, projectName, entitySchema));
            writer.println(String.format("                final In<RecordStatus> in = cb.in(root.get(%1$s_.status));", entity, projectName,
                    entitySchema));
            writer.println(String.format(
                    "                searchingDto.getStatus().stream().map(item -> LookupParser.parse(RecordStatus.class, item))", entity,
                    projectName, entitySchema));
            writer.println(String.format("                        .forEach(item -> in.value(item));", entity, projectName, entitySchema));
            writer.println(String.format("                predicates.add(in);", entity, projectName, entitySchema));
            writer.println(String.format("            }", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(
                    String.format("            return cb.and(predicates.toArray(new Predicate[0]));", entity, projectName, entitySchema));
            writer.println(String.format("        };", entity, projectName, entitySchema));
            writer.println(String.format("        return findAll(specification, pagable);", entity, projectName, entitySchema));
            writer.println(String.format("    }", entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
        }
    }

    private static class RequestGenerator extends AbstractGenerator {

        public RequestGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "common/src/main/java/org/trinity/" + projectFolder.getName() + "/common/message/dto/request");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return entity + "Request.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.common.message.dto.request;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.common.dto.request.AbstractDataRequest;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sDto;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(
                    String.format("public class %1$sRequest extends AbstractDataRequest<%1$sDto> {", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
        }

    }

    private static class ResponseGenerator extends AbstractGenerator {

        public ResponseGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "common/src/main/java/org/trinity/" + projectFolder.getName() + "/common/message/dto/response");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return entity + "Response.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.common.message.dto.response;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.common.dto.response.AbstractResponse;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sDto;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(
                    String.format("public class %1$sResponse extends AbstractResponse<%1$sDto> {", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
        }
    }

    private static class RestControllerGenerator extends AbstractGenerator {
        public RestControllerGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities, "rest/src/main/java/org/trinity/" + projectFolder.getName() + "/rest/controller");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return entity + "RestController.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.rest.controller;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(
                    String.format("import org.springframework.web.bind.annotation.RequestMapping;", entity, projectName, entitySchema));
            writer.println(
                    String.format("import org.springframework.web.bind.annotation.RestController;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sDto;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.common.message.dto.domain.%1$sSearchingDto;", entity, projectName,
                    entitySchema));
            writer.println(
                    String.format("import org.trinity.%2$s.common.message.dto.request.%1$sRequest;", entity, projectName, entitySchema));
            writer.println(
                    String.format("import org.trinity.%2$s.common.message.dto.response.%1$sResponse;", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.%2$s.process.controller.base.I%1$sProcessController;", entity, projectName,
                    entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("@RestController", entity, projectName, entitySchema));
            writer.println(String.format("@RequestMapping(\"/*\")", entity, projectName, entitySchema));
            writer.println(String.format("public class %1$sRestController extends", entity, projectName, entitySchema));
            writer.println(String.format(
                    "      AbstractApplicationAwareCrudRestController<%1$sDto, %1$sSearchingDto, I%1$sProcessController, %1$sRequest, %1$sResponse> {",
                    entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("  @Override", entity, projectName, entitySchema));
            writer.println(String.format("  protected %1$sResponse createResponseInstance() {", entity, projectName, entitySchema));
            writer.println(String.format("      return new %1$sResponse();", entity, projectName, entitySchema));
            writer.println(String.format("  }", entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
        }

    }

    private static class SearchRequestGenerator extends AbstractGenerator {
        public SearchRequestGenerator(final File projectFolder, final String entitySchema, final List<String> entities) {
            super(projectFolder, entitySchema, entities,
                    "common/src/main/java/org/trinity/" + projectFolder.getName() + "/common/message/dto/domain");
        }

        @Override
        protected String getFileNameForEntity(final String entity) {
            return entity + "SearchingDto.java";
        }

        @Override
        protected void write(final PrintWriter writer, final String projectName, final String entitySchema, final String entity) {
            writer.println(String.format("package org.trinity.%2$s.common.message.dto.domain;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(String.format("import org.trinity.common.dto.object.AbstractSearchingDto;", entity, projectName, entitySchema));
            writer.println(String.format("", entity, projectName, entitySchema));
            writer.println(
                    String.format("public class %1$sSearchingDto extends AbstractSearchingDto {", entity, projectName, entitySchema));
            writer.println(String.format("}", entity, projectName, entitySchema));
        }
    }

    public static void generate(File projectFolder, final String entitySchema) throws IOException {
        projectFolder = projectFolder.getCanonicalFile();

        final File entityFolder = new File(projectFolder + "/repository/src/main/java/org/trinity/" + projectFolder.getName()
                + "/repository/" + entitySchema + "/entity");

        if (!entityFolder.isDirectory()) {
            return;
        }
        final List<String> entities = new ArrayList<>();
        for (final File entityFile : entityFolder.listFiles()) {
            if (entityFile.getName().equals("package-info.java")) {
                continue;
            }

            entities.add(entityFile.getName().replace(".java", ""));
        }

        final List<IGenerator> generators = new ArrayList<>();
        generators.add(new ConverterGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new DataPermissionGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new DtoGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new ProcessControllerGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new ProcessControllerInterfaceGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new RequestGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new SearchRequestGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new ResponseGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new RepositoryGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));
        generators.add(new RestControllerGenerator(projectFolder, entitySchema, new ArrayList<>(entities)));

        generators.forEach(item -> item.generate());
    }
}
