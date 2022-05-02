--------------------------------
2022-04-30
--------------------------------
1. package name은 소문자만 사용합니다.
2. JPA를 사용 하지 않은 이유가 있을까요? MemberRepository.java에 개발하신 쿼리는 JPA 기본제공 기능입니다.
3. controller/MemberForm.java는 dto로 보입니다.
   controller/dto/MemberFormDto.java 로 하는게 식별성이 높을것 같습니다.
4. @Setter 어노테이션을 기본적으로 사용 하지 않습니다.
   생성시 필요한 정보를 넣어 줄 수 있도록 구현 하여, 이후에 Set이 필요한 로직의 경우 용도를 알수 있는 method를 만들어 사용 합니다.
   ex)public void changePassword(String password);
5. Member 도메인에서 address 는 단순 VO로 보입니다.
   그리고 이미 member.domain 인데 굳이 member package가 한번 더 필요한지 모르겠습니다.
   member/domain/Member.java
   member/domain/valeobject/Address.java
6. MemberController.java의 패스는 별도로 선언하여 사용 합니다.
   member/controller/MemberWebApiUrl.java
   public class MemberWebUrl{
       public static final String HOME_URL = "/";
       ...
   }
7. 비지니스 로직은 controller에 넣지 않습니다.
   - 콘트롤러에서 하는일
      dto를 도메인에서 사용 하는 command로 바꾸는 프로세스 호출
      service 처리 결과를 return 하기 위해 dto 변환 처리
8. 비지니스 로직은 도메인에서 처리 합니다.
   - controller : dto to command
   - service : command 를 Member 도메인으로 넘김
   - member : command를 가지고 비지니스 처리를 함.

9. 일부 리팩토링 했습니다. TODO 찾아서 추가 작업 해주세요.

10.빌드가 혹시 안되는 경우 Settings > Build Tools > Gradle > Gradle projects > Build and run using : Gradle ==> IntelliJ IDEA, Run tests using: Gradle ==> Intellij IDEA 로 변경