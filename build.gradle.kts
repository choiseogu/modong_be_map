plugins {
    java
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.my"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

repositories {
    mavenCentral()
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper")

    //implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    //implementation ("org.springframework.boot:spring-boot-starter-security")

    //classGraph 관련 오류 해결 버전 업데이트
    implementation ("io.github.classgraph:classgraph:4.8.180")

    testImplementation("io.projectreactor:reactor-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //ai 관련 라이브러리
    implementation(platform("dev.langchain4j:langchain4j-bom:1.3.0"))
    implementation("dev.langchain4j:langchain4j-google-ai-gemini:1.3.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
