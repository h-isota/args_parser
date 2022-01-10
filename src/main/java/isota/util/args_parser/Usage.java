package isota.util.args_parser;

import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class Usage {
	private static class Key {
		String desc;
		boolean isRequired;
		int size() {
			return 0;
		}
	}
	private static class KeyValue extends Key {
		String value;
		@Override
		int size() {
			return super.size() + value.length();
		}
	}
	private List<String> keyList = new Vector<>();
	private Hashtable<String, Key> keyTable = new Hashtable<>();
	private String command;
	public Usage(String command) {
		this.command = command;
	}
	/**
	 * 値を持つ引数を指定します。
	 * @param key 引数のキー
	 * @param value 値
	 * @param desc 説明
	 * @param isRequired 必須か否か
	 */
	public void add(String key, String value, String desc, boolean isRequired) {
		if (keyTable.containsKey(key)) {
			throw new Error("duplicate key: " + key);
		}
		keyList.add(key);
		KeyValue kv = new KeyValue();
		kv.value = value;
		kv.desc = desc;
		kv.isRequired = isRequired;
		keyTable.put(key, kv);
	}
	/**
	 * 値を持たない引数を指定します。
	 * @param key 引数のキー
	 * @param desc 説明
	 * @param isRequired 必須か否か
	 */
	public void add(String key, String desc, boolean isRequired) {
		if (keyTable.containsKey(key)) {
			throw new Error("duplicate key: " + key);
		}
		keyList.add(key);
		Key k = new Key();
		k.desc = desc;
		k.isRequired = isRequired;
		keyTable.put(key, k);
	}
	public boolean isKey(String key) {
		return keyTable.containsKey(key) && !(keyTable.get(key) instanceof KeyValue);
	}
	public boolean isKeyValue(String key) {
		return keyTable.containsKey(key) && keyTable.get(key) instanceof KeyValue;
	}
	public boolean isRequired(String key) {
		return keyTable.containsKey(key) && keyTable.get(key).isRequired;
	}
	public List<String> getKeyList() {
		return keyList;
	}
	@Override
	public String toString() {
		String result = command;
		Optional<String> maxKey = keyList.stream().max((o1, o2) -> o1.length() + keyTable.get(o1).size() - o2.length() - keyTable.get(o2).size());
		int maxLength = maxKey.isPresent() ? maxKey.get().length() + keyTable.get(maxKey.get()).size() : 0;
		maxLength += 2;
		for (String key : keyList) {
			result += System.getProperty("line.separator");
			Key k = keyTable.get(key);
			if (k instanceof KeyValue) {
				KeyValue kv = (KeyValue)k;
				result += String.format("  %-" + maxLength + "s: %s", key + " " + kv.value, kv.desc);
			} else {
				result += String.format("  %-" + maxLength + "s: %s", key, k.desc);
			}
		}
		return result;
	}
}
