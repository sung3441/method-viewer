# method-viewer

## 개요
메소드의 다양한 정보를 출력할 수 있는 어노테이션 모음

## 종류
|어노테이션명|기능 설명|
|:---|:---|
|**@SpeedViewer**|속도 측정|
|**@ParameterViewer**|파라미터 조회|
|**@ReturnViewer**|반환 값 조회|
|**@StackTraceViewer**|스택 목록 조회|
|**@MethodViewer**|위 모든 어노테이션 기능을 수행|

## 사용 방법
build.gradle
```build.gradle
// 방식 1 (file import)
// 1. 프로젝트 root 경로에 첨부된 method-viewer-0.0.1-SNAPSHOT.jar 파일 넣기
// 2. import문 추가
implementation files('method-viewer-0.0.1-SNAPSHOT.jar')

// 방식 2 (jitpack) -> 아직 지원 x
```

application.properties
```application.properties
methodviewer.basedir=fastcampus.innercircle.oss.methodviewer            // 글로벌 설정 시 영향 받을 패키지 입력
methodviewer.printer.type=logback  // sysout, logback, log4j            // 로그 남길 구현체
methodviewer.printer.loglevel=INFO // TRACE, DEBUG, INFO, WARN, ERROR   // 로그 레벨
```

java Bean 등록
```java
@Value("${methodviewer.printer.type:systemout}")
private String printerType;
@Value("${methodviewer.printer.loglevel:DEBUG}")
private String logLevel;
@Value("${methodviewer.basedir}")
private String baseDir;

@Bean
public Print print() {
  return PrintFactory.create(printerType, logLevel);
}

@Bean
public MethodViewerAspect methodViewerAspect() {
  return MethodViewerAspectFactory.create(baseDir, print());
}
```

java method
```java
// 메소드 위에 어노테이션 추가
@SpeedViewer
public void action() {
    // 작업 수행
}
```
