# boot-base
集成spring-boot的一个rest服务基础框架，使用了spring data jpa redis mybatis


# security分支
这个分支将token管理使用security实现，并且同样通过redis管理，授权方式发生变更

获取token
~~~
curl -X POST \
  http://localhost:9090/oauth/token \
  -H 'authorization: Basic Y2xpZW50OnNlY3JldA==' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'grant_type=password&username=&password=password'
~~~

使用token
~~~
curl -X GET \
  http://localhost:9090/api \
  -H 'authorization: bearer 586438d3-9062-4e22-af86-1184d8124fe2' \
~~~
