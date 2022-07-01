# host


---

### 개발 환경

- java 11
- JPA 
- querydsl
- spring-boot
- mariaDB 8.0.28
- gradle


---

### BaseEntity

- 스프링 프레임워크에서 제공하는 @CreatedDate 과 @LastModifiedDate를 이용하여 생성일자와 수정일자를 관리함


### API

- @RequestMapping("/v1/clients")을 기본으로 엔드포인트 설정

### 호스트 등록
- @PostMapping("/v1/clients)
- HttpServletRequest를 이용하여 호스트명과 IP를 받아서 DB에 저장 한다.


### 호스트 수정
- @PatchMapping("/{clientId}")
- IP 혹은 호스트명 변동 시에 실행하여 name과 ip 필드를 업데이트 한다.


### 단일 호스트 조회
- @GetMapping("/{clientId}")
- 등록된 호스트의 아이디를 통한 단건 조회이며 연결상태를 체크하지 않는다.
- Alive 상태는 기존 연결 상태를 유지
- lastAlivedAt은 마지막 Alive 시간이며 초기 생성 후 바로 조회하면 null값을 갖는다.


### 전체 호스트 리스트 조회
- @GetMapping("/list")
- 전체 조회이며 호스트의 연결상태를 체크하지 않는다.
- Alive 상태는 기존 연결 상태를 유지
- lastAlivedAt은 마지막 Alive 시간이며 초기 생성 후 바로 조회하면 null값을 갖는다.


### 전체 호스트 상태 리스트 조회
- @GetMapping("/state-list")
- 전체 조회이며 호스트의 연결상태를 체크
- lastAlivedAt은 마지막 Alive 시간
- 현재 연결 상태라면 lastAlivedAt은 조회시간과 동일하다.


### 호스트 삭제
- @DeleteMapping("/{clientId}")
- 등록된 호스트 중 일치하는 아이디와 관련 정보를 삭제한다.


### 단일 호스트 상태 조회
- @PatchMapping("/state/{clientId}")
- 단일 조회이며 호스트의 연결상태를 체크
- lastAlivedAt은 마지막 Alive 시간
- 현재 연결 상태라면 lastAlivedAt은 조회시간과 동일하다.