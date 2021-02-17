# CargoTracker RegApp

[The original DDD sample regapp](https://github.com/citerus/dddsample-regapp) is written in Swing and Spring. 

This client app is rewritten for [eclipse-ee4j/cargotracker](https://github.com/hantsy/cargotracker).

* Rebuilding the whole UI with JavaFX 
* Using CDI(JBoss Weld) as IOC container
* The client and server communication is switched to Jakarta Restful Service(JAX-RS)

## Prerequites

This project is developed against the latest Java and JavaFX.
 
* Java 15+
* Apache Maven 3.6.x

## Build 

Build and run the application from source codes.

```bash
mvn clean compile exec:java
```

## References

* [JavaFX and CDI: How to Inject many Stages](https://stackoverflow.com/questions/47591527/javafx-and-cdi-how-to-inject-many-stages)
* [Bootstrap Javafx 2.0 with Weld](https://stackoverflow.com/questions/14654627/bootstrap-javafx-2-0-with-weld)
* [fx-guice](https://github.com/cathive/fx-guice)
* [Introducing FxWeaver - Dependency Injection Support for JavaFX and FXML](https://rgielen.net/posts/2019/introducing-fxweaver-dependency-injection-support-for-javafx-and-fxml/) and  [rgielen /  javafx-weaver](https://github.com/rgielen/javafx-weaver)
* [Getting Started with JavaFX](https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm)
* [Working With Layouts in JavaFX](https://docs.oracle.com/javafx/2/layout/jfxpub-layout.htm)
* [JavaFX Tutorial: FXML and SceneBuilder](https://www.vojtechruzicka.com/javafx-fxml-scene-builder/)
* [TestFX](https://github.com/TestFX/TestFX)
* [Test Driven Development In JavaFX With TestFX](https://medium.com/information-and-technology/test-driven-development-in-javafx-with-testfx-66a84cd561e0)
* [trishagee/jb-stock-client](https://github.com/trishagee/jb-stock-client)
* [Spring Tips: JavaFX ](https://spring.io/blog/2019/01/16/spring-tips-javafx)
