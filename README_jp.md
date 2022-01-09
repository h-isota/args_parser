# args_parser

java のコマンドライン引数を解析するライブラリです。

## デモ

以下のように表示できます。

```sh
$ java -cp build/libs/args_parser.jar:bin/test/ isota.util.args_parser.Sample -h 
java isota.util.args_parser.Sample [-h] [-d dir] -f file
  -h      : show help
  -d dir  : set directory
  -f file : set file
```
 
## 使い方

[Sample.java](src/test/java/isota/util/args_parser/Sample.java) を参照ください。
 
## 開発者
 
* isota
 
## ライセンス

[MIT license](LICENSE).
