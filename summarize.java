///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 21+
//PREVIEW
//JAVAC_OPTIONS -parameters
//JAVA_OPTIONS --enable-preview --enable-native-access=ALL-UNNAMED --add-modules jdk.incubator.vector
//MODULES jdk.incubator.vector
//DEPS io.quarkus.platform:quarkus-bom:3.17.2@pom
//DEPS io.quarkus:quarkus-rest
//DEPS io.quarkus:quarkus-arc
//DEPS io.quarkus:quarkus-smallrye-openapi
//DEPS io.quarkiverse.langchain4j:quarkus-langchain4j-jlama:0.22.0
//DEPS com.github.tjake:jlama-core:0.8.2
//DEPS com.github.tjake:jlama-native:0.8.2:linux-x86_64
//DEPS org.jsoup:jsoup:1.18.1

//FILES src/main/resources/application.properties
//SOURCES src/main/java/org/mfusco/sitesummarizer/SiteSummarizer.java
//SOURCES src/main/java/org/mfusco/sitesummarizer/SiteSummarizerMain.java
//SOURCES src/main/java/org/mfusco/sitesummarizer/SiteType.java
//SOURCES src/main/java/org/mfusco/sitesummarizer/TextExtractor.java
//SOURCES src/main/java/org/mfusco/sitesummarizer/SummarizerAiService.java
//SOURCES src/main/java/org/mfusco/sitesummarizer/crawlers/SiteCrawler.java
//SOURCES src/main/java/org/mfusco/sitesummarizer/crawlers/BlogCrawler.java
//SOURCES src/main/java/org/mfusco/sitesummarizer/crawlers/WikipediaCrawler.java

public class summarize {
    
} 