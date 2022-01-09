package isota.util.args_parser;

import java.util.Hashtable;

public class ArgsParser {
	private Hashtable<String, String> valueTable = new Hashtable<>();
	private boolean hasError = false;
	private String errorMsg = null;
	private boolean put(String key, String value) {
		if (valueTable.containsKey(key)) {
			errorMsg = "duplicate key: " + key;
			hasError = true;
			return false;
		}
		valueTable.put(key, value);
		return true;
	}
	public ArgsParser(String[] args, Usage usage) {
		for (int i = 0; i < args.length; i++) {
			if (usage.isKey(args[i])) {
				if (!put(args[i], "")) {
					return;
				}
			} else if (usage.isKeyValue(args[i])) {
				if (i + 1 >= args.length || usage.isKey(args[i + 1]) || usage.isKeyValue(args[i + 1])) {
					errorMsg = "missing value: " + args[i];
					hasError = true;
					return;
				}
				if (!put(args[i], args[++i])) {
					return;
				}
			} else {
				errorMsg = "unknown key: " + args[i];
				hasError = true;
				return;
			}
		}
		// 必須チェック
		usage.getKeyList().stream().anyMatch(key -> {
			if (usage.isRequired(key) && !sets(key)) {
				errorMsg = "missing key: " + key;
				hasError = true;
				return true;
			}
			return false;
		});
	}
	public String get(String key) {
		return valueTable.get(key);
	}
	public boolean hasError() {
		return hasError;
	}
	public boolean sets(String key) {
		return valueTable.containsKey(key);
	}
	public String getErrorMsg() {
		return errorMsg;
	}
}
