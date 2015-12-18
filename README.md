Sample Spring Boot Project
====

Spring Boot 프로젝트를 생성하고 DB에 연결하는 방식에 대해서 설명한다.

## project 생성

- http://start.spring.io/에서 필요한 dependencies를 선택해서 만들었다.

    - Gradle
    - Spring Boot 1.3.0
    - WEB
    - JPA
    - AWS JDBC
    - MySQL
    - DevTools
    - Lombok


## `.gitignore` 생성

- https://www.gitignore.io/에서 *Java*, *Gradle*, *Intellij*, *Eclipse*를 선택해서 만들었다.


## git 초기화 및 remote 연결

1. project 생성에서 다운로드된 압축파일을 적당한 위치에 압축을 푼다.

    ```bash
    $ upzip demo.zip
    $ cd demo
    ```

1. local git 초기화를 한다.

    ```bash
    $ cd domo
    $ git init
    ```

2. remote 연결

    - 우선 remote로 사용할 서버에 repository를 만든다.
    - [github](https://github.com/) 또는 [bitbucket](https://bitbucket.org/)에서 repository를 생성 후 나오는 가이드를 따라서 진행한다.

    ```
    $ git add remote master origin <<remote repository>>
    ```


## MySQL DB를 AWS의 RDS 서비스를 사용하는 경우

### AWS SDK 설치 및 설정

1. AWS CLI 설치

    - https://aws.amazon.com/ko/cli/

1. AWS AIM에서 신규 사용자 추가

    - 현재는 admin을 추가했음.(API 호출용 계정 )
        - https://console.aws.amazon.com/iam/home
        - access-key/secret-key는 문의 바람

2. AWS SDK CLI *profile* 설정

    > 설정한 프로파일은 `build.gradle`에서 사용한다.

    - `~/.aws/config` 에 sooyoung-admin **profile**을 추가한다.

        ```bash
        $ vi ~/.asw/config
        ....
        [profile sooyoung-admin]
        output = json
        region = ap-northeast-1
        ```

    - `~/.aws/credentials`에 sooyoung-admin **profile** key를 추가한다.

        ```bash
        $ vi ~/.asw/credentials
        ....
        [sooyoung-admin]
        aws_access_key_id = <<aws access key>>
        aws_secret_access_key = <<aws secret key>>
        ```

### AWS MySQL DB 생성

`gradle aws plugin`을 사용해서 관리한다.

1. DB 생성

    ```bash
    $ ./gradlew migrateDBInstance
    ```

    - AWS CLI 확인

    ```bash
    $ aws rds describe-db-instances \
      --profile sooyoung-admin \
    ```

    - AWS 확인 [console](https://ap-northeast-1.console.aws.amazon.com/rds/home#dbinstances:)

2. DB 재시작

    ```bash
    $ ./gradlew rebootDBInstance
    ```

3. DB 삭제

    ```bash
    $ ./gradlew deleteDBInstance
    ```

#### AWS Security Group에 Inbound port 추가

DB 인스턴스를 만들고 접근하려고 하면 접속을 할 수 없는 상태이다.
외부에서 접속이 가능하게 3306 포트를 열어야 한다.

> AWS Security Group은 `build.gradle`에서 사용한다. (**sg-b9f1f1dc**)

1. inbound 포트 추가
    현재는 모든 IP(0.0.0.0/0)를 추가했으나, 가능하면 접속 IP 구간(106.240.74.171/32)을 좁게 잡으면 좋겠다.

    ```bash
    $ aws ec2 authorize-security-group-ingress \
      --profile sooyoung-admin \
      --group-id sg-b9f1f1dc --protocol tcp --port 3306 --cidr 0.0.0.0/0
    ```

2. inbound 포트 추가 확인

    ```bash
    $ aws ec2 describe-security-groups \
      --profile sooyoung-admin \
      --group-ids sg-b9f1f1dc
    ```

    - AWS 확인 [console](https://ap-northeast-1.console.aws.amazon.com/ec2/v2/home#SecurityGroups:search=default;sort=groupId)
        **Inbound** 탭에서 확인

#### MySQL DB에 Scheme 추가

편한 SQL Tool을 사용하여 프로젝트에 사용한 Scheme(**hack-korea**)를 추가한다.
기본 캐릭터셋은 `utf8_unicode_ci`로 설정한다.


## Reference

- [Spring boot starter](http://start.spring.io/)
- [.gitignore](https://www.gitignore.io/)
- [AWS CLI](https://aws.amazon.com/ko/cli/)
- [AWS Gradle plugin](https://github.com/classmethod-aws/gradle-aws-plugin)