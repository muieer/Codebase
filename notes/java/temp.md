**Q：什么是 uber/fat JAR?**<br>
**A：** uber/fat JAR 不仅包含开发者自己写的代码，也包含用到的第三方依赖库的代码，这种类型的 JAR 可以独立执行程序。<wbr>
[What is a Java Uber-JAR and Why Is It Useful?](https://blog.payara.fish/what-is-a-java-uber-jar) 这边博客有详细的介绍。<wbr>
这些 org.apache.maven.plugins:maven-assembly-plugin 、org.apache.maven.plugins:maven-shade-plugin Maven 插件可以创建 <wbr>
uber/fat JAR。

**Q: 什么是 Manifest File？**<br>
**A:** Manifest File 是一个文件，存储有关 JAR 包的关键信息，还有一些其他用途，比如指定主类，使 JAR 可执行。<wbr>
这些是相关的技术文档：[Working with Manifest Files](https://docs.oracle.com/javase/tutorial/deployment/jar/manifestindex.html)<wbr>
、[Understanding the JAR Manifest File](https://www.baeldung.com/java-jar-manifest)。

