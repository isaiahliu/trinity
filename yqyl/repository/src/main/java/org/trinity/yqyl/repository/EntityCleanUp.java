package org.trinity.yqyl.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.trinity.common.util.Tuple2;
import org.trinity.generator.EntityCleanupUtil;

public class EntityCleanUp {
	private static Map<String, List<Tuple2<String, String>>> userTypeMapping = new HashMap<>();
	static {

		List<Tuple2<String, String>> userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "AccountStatus"));
		userTypeMapping.put("Account", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "AccountBalanceStatus"));
		userTypes.add(new Tuple2<String, String>("category", "AccountBalanceCategory"));
		userTypes.add(new Tuple2<String, String>("currency", "Currency"));
		userTypeMapping.put("AccountBalance", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "AccountPostingStatus"));
		userTypes.add(new Tuple2<String, String>("currency", "Currency"));
		userTypeMapping.put("AccountPosting", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "RecordStatus"));
		userTypes.add(new Tuple2<String, String>("category", "TransactionCategory"));
		userTypeMapping.put("AccountTransaction", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "ServiceReceiverClientStatus"));
		userTypes.add(new Tuple2<String, String>("type", "PersonalType"));
		userTypes.add(new Tuple2<String, String>("gender", "Gender"));
		userTypeMapping.put("ServiceReceiverClient", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "ServiceSupplierClientStatus"));
		userTypes.add(new Tuple2<String, String>("type", "PersonalType"));
		userTypeMapping.put("ServiceSupplierClient", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "ClientStatus"));
		userTypes.add(new Tuple2<String, String>("type", "PersonalType"));
		userTypeMapping.put("AllowanceSupplierClient", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "ClientStatus"));
		userTypeMapping.put("OperatorClient", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "AnnouncementStatus"));
		userTypes.add(new Tuple2<String, String>("clientType", "ClientType"));
		userTypeMapping.put("Announcement", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("category", "LookupType"));
		userTypes.add(new Tuple2<String, String>("language", "Language"));
		userTypes.add(new Tuple2<String, String>("status", "RecordStatus"));
		userTypeMapping.put("Lookup", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "MessageStatus"));
		userTypes.add(new Tuple2<String, String>("type", "MessageType"));
		userTypeMapping.put("Message", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "OrderStatus"));
		userTypeMapping.put("ServiceOrder", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "RecordStatus"));
		userTypes.add(new Tuple2<String, String>("name", "RoleName"));
		userTypeMapping.put("Role", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "ServiceStatus"));
		userTypeMapping.put("Service", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "RecordStatus"));
		userTypeMapping.put("UserGroup", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "UserStatus"));
		userTypeMapping.put("User", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("name", "AccessRight"));
		userTypes.add(new Tuple2<String, String>("status", "RecordStatus"));
		userTypeMapping.put("Accessright", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "TokenStatus"));
		userTypeMapping.put("Token", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("category", "FavoriteCategory"));
		userTypes.add(new Tuple2<String, String>("status", "RecordStatus"));
		userTypeMapping.put("Favorite", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("status", "RecordStatus"));
		userTypeMapping.put("ServiceCategory", userTypes);

		userTypes = new ArrayList<>();
		userTypes.add(new Tuple2<String, String>("key", "SystemAttributeKey"));
		userTypes.add(new Tuple2<String, String>("type", "ValueType"));
		userTypes.add(new Tuple2<String, String>("status", "RecordStatus"));
		userTypeMapping.put("SystemAttribute", userTypes);

	}

	public static void main(final String[] args) throws IOException {
		final File folder = new File("./src/main/java/org/trinity/yqyl/repository");
		EntityCleanupUtil.cleanUp(folder, "org.trinity.yqyl.repository", userTypeMapping);
	}
}
