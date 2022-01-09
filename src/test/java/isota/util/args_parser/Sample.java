package isota.util.args_parser;

public class Sample {

    public static void main(String[] args) {
    	// コマンドライン引数の解析方法を、Usage オブジェクトに設定します。
		Usage usage = new Usage("java isota.util.args_parser.Sample [-h] [-d dir] -f file");
		usage.add("-h", "show help", false);
		usage.add("-d", "dir", "set directory", false);
		usage.add("-f", "file", "set file", true);
		
		// コマンドライン引数を解析します。
		ArgsParser ap = new ArgsParser(args, usage);
		
		// 引数 -h があれば、ヘルプを表示して終了
		if (ap.sets("-h")) {
			System.err.println(usage);
			return;
		}
		
		// Usage オブジェクトを参照して、解析エラーがあればメッセージを表示して終了
		if (ap.hasError()) {
			System.err.println(ap.getErrorMsg());
			return;
		}
		
		// 引数 -d が指定されていれば、値を取得します。無ければ null
		String dir = ap.sets("-d") ? ap.get("-d") : null;
		
		// 引数 -f は必須だから値を持つはず。
		String file = ap.get("-f");
		
		// 指定された引数を使って、アプリケーションを実施
		System.out.println("dir: " + dir + ", file: " + file);
    }

}
