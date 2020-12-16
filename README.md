# zc-server

spring cloud for kubernetes example

tag
git tag -a 202009291705 -m '2020-09-29'
git push origin 202009291705

打包：
gradle -p zc-employee-service clean bootJar

docker打包
gradle -p zc-employee-service clean docker
docker run -d -p 11220:11220 zc/zc-employee-service

接口文档地址:/模块前缀/swagger-ui.html#/ eg: /zc-employee-service/swagger-ui.html#/
