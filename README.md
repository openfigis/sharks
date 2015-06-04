The Sharks application visualizes *measures* and *PoAs* about sharks species and grouped stored in a *Microsoft Access database*.

#Architecture

The application is composed by a client side, a web application running in the browser, and a server side, 
a server application exposing the database data combined with external services data through a *REST api*.

The web application is built using Javascript and the [AngularJS](https://angularjs.org/) framework. 

The server side is layered as follow:
 - **storage**: is responsible to access the Access database and to expose the relational data in an object model. 
 The storage module uses JPA as persistence layer.
 - **service**: is responsible to combine and to expose data coming from the application storage 
 and from external services like RefPub and Monikers.
 - **web**: is responsible to expose the services through a REST API using [Jersey](https://jersey.java.net/) technology.
 
A common **config** module is shared between the layers for the application configuration handling.

## Technology stack
Here a list of technology/libraries used for the project.

Server:
- [Maven](https://maven.apache.org/), as project management tool.
- [CDI](http://cdi-spec.org/) as dependency injection framework.
- [Weld](http://weld.cdi-spec.org/), as CDI implementation.
- [JUnit](http://junit.org/), as unit and integration test framework.
- [Mockito](https://code.google.com/p/mockito/), as mock framework.
- [CDI-Unit](http://jglue.org/cdi-unit/), as CDI support in unit and integration tests.
- [Project Lombok](https://projectlombok.org/), as support for fast development.
- [UCanAccess](https://sourceforge.net/projects/ucanaccess/), a JDBC driver for accessing Access databases.
- [JPA](http://en.wikipedia.org/wiki/Java_Persistence_API), for mapping between ER to Java Object model.
- [Hibernate](http://hibernate.org/), as JPA implementation.
- [JAXB](http://en.wikipedia.org/wiki/Java_Architecture_for_XML_Binding), as XML to Java Object binding framework.
- [MOXy](https://wiki.eclipse.org/EclipseLink/Examples/MOXy), as XML binding extension in order to support XPath in object binding.
- [EhCache](http://ehcache.org/), as cache framework.
- [Solr](http://lucene.apache.org/solr/), as indexing service.
- [Jersey](https://jersey.java.net/), as Java REST framework.
- [Swagger](http://swagger.io/), as REST API documentation framework.
 


# Installation
The client side can be deployed in a web server as static resource. 
The server side is deployed in a Tomcat instance through the built war.

## Configuration file
The application is configured using a property-value file.
The configuration file location is passed to the application through the *system* or *env* property _**SHARKS_CONFIG**_.
 
The available configuration properties:
 - **storage.dbfile**: the access db location as absolute path
 - **cache.config**: the cache configuration file location as absolute path
 - **cache.warmup**: the type of cache warmup. The available types are:
  - _**none**_: no cache warmup is made. Note: this will slowdown the application performance.
  - _**sequential**_: the cache is warmed up calling all the external services sequentially.
  - _**parallel**_: the cache is warmed up calling the external services in parallel.
 - **cache.cleanPassphrase**: the passprhase used to clean the cache from the cache console. Omit it in order to not require a passphrase.
 - **service.sharks**: the application REST API base url, used by Swagger.
 - **service.refpub**: the base url of the RefPub service.
 - **service.monikers**: the base url of the Monikers service.
 - **service.geoserver.specieslist**: the url of the species list published by the GeoServer.
 - **service.solr**: the Solr instance url.
 
## Cache configuration
The cache is configured through an EhCache xml file.
	
The application uses the following caches:
 - **country**: cache for the Country concepts coming from RefPub service.
 - **species**: cache for the Species concepts coming from RefPub service.
 - **rfb4iso**: cache for the rfb4iso3 moniker.
 - **rfb**: cache for the rfb moniker.
 - **figisdoc**: cache for the figisdoc moniker.
 - **specieslist**: cache for the specieslist from GeoServer.

#Instance documentation

## REST API documentation
The REST API documentation is provided through Swagger. 
The Swagger interface lists and documents all the available REST operations. 
The Swagger console let also to call the single operation with the parameters if present/required.

## Cache Console
The cache console lets the user manage the application cache. The console offers these operations:
 - **cache cleaning**: clean the application cache and warms it up if the warms options is active.

