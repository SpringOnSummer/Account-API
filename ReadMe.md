# Account Rest API Application

## Specifies

### Project Name
Account-API

### Package
org.project.personal.accountapi

### Spring boot version : 2.7.12

### Added dependencies
#### Developer Tools
1. Lombok
2. Spring Boot Devtools
3. Spring Configuration Processor

#### Web
1. Spring Web
2. Spring Session

#### SQL
1. Spring Data Jpa
2. H2 Database
3. MySQL Driver

#### NoSQL
1. Spring Data Redis

#### Testing
1. Spring Rest Docs

#### Spring Cloud Config
1. Config Client <-> Config Server

#### Spring Cloud Discovery
1. Eureka Discovery Client <-> Eureka Server

## 따라하기

### 1.Spring Cloud Config
위 설정에 따라 프로젝트를 설정하고 어플리케이션을 실행하면 아래의 오류 메시지가 출력될 것이다.
```console
org.springframework.cloud.commons.ConfigDataMissingEnvironmentPostProcessor$ImportException: No spring.config.import set
...
```

이는 Spring Cloud Config 설정과 관련된 것이므로 추가로 출력되는 해당 메시지를 그대로 수행하면 된다.
```console
Description:

No spring.config.import property has been defined

Action:

Add a spring.config.import=configserver: property to your configuration.
	If configuration is not required add spring.config.import=optional:configserver: instead.
	To disable this check, set spring.cloud.config.enabled=false or 
	spring.cloud.config.import-check.enabled=false.
```
아직 Config Server 를 아직 활성화하지 않았거나 사용할 예정이 없다면 관련 의존성을 제거하거나 다음 `application.yml` 에 다음을 추가한다.
```yaml
spring:
  cloud:
    config:
      enabled: false
```

### 2. Spring Data Redis
spring cloud config 관련 설정을 했다면, 다음의 오류메시지가 출력된다
```console
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'enableRedisKeyspaceNotificationsInitializer' defined in class path resource [org/springframework/boot/autoconfigure/session/RedisSessionConfiguration$SpringBootRedisHttpSessionConfiguration.class]: Invocation of init method failed; nested exception is org.springframework.data.redis.RedisConnectionFailureException: Unable to connect to Redis; nested exception is io.lettuce.core.RedisConnectionException: Unable to connect to localhost:6379
...
```
Spring Data Redis 에서 발생하는 에러이고 최하단으로 내려가면 다음의 메시지를 확인할 수 있다.
```console
Caused by: io.netty.channel.AbstractChannel$AnnotatedConnectException: Connection refused: localhost/127.0.0.1:6379
Caused by: java.net.ConnectException: Connection refused
	at java.base/sun.nio.ch.SocketChannelImpl.checkConnect(Native Method) ~[na:na]
	at java.base/sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:777) ~[na:na]
	...
```
default 설정이 localhost/127.0.0.1:6379 으로 되어있고 이는 레디스 서버를 실행했을 때 나오는 default 설정과 동일하다.  
Cloud Config 와 마찬가지로 아직 활성화하지 않았거나 사용할 예정이 없다면 관련 의존성을 제거하거나 다음 `application.yml` 에 다음을 추가한다.
```yaml
spring:
  autoconfigure:
    exclude: org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
```

### 3. Eureka Discovery
1번과 2번을 수행했다면 어플리케이션은 실행되지만 여전히 오류 메시지가 출력되는 것을 확인할 수 있는데, 이는 Eureka Discovery 가 설정되어있기 때문이다.  
또한, 반복적으로 http 요청을 보내기 때문에 로그가 지속적으로 출력된다.
따라서 해당 옵션을 아직 활성화하지 않았거나 사용할 예정이 없다면 관련 의존성을 제거하거나 다음 `application.yml` 에 다음을 추가한다.
```yaml
eureka:
  client:
    enabled: false
```
