#!/usr/bin/env bash

# user maven to build docker images and push result images onto harbor
#/Users/nuoee/software/apache-maven-3.6.1/bin/mvn clean package docker:build

## 调用此脚本生成 jar4文件夹和jar4.zip



echo "`/Users/nuoee/software/apache-maven-3.6.1/bin/mvn clean package`" &&

echo "`rm -rf jar4/`" &&

echo "`rm -rf jar4.zip`" &&

echo "`mkdir jar4/`" &&

echo "`cp blade-service/blade-bg-admin/target/blade-bg-admin.jar jar4/`" &&
echo "`cp blade-service/blade-foreward-user/target/blade-foreward-user.jar jar4/`"&&
echo "`cp blade-service/blade-infomation/target/blade-information.jar jar4/`"&&
echo "`cp blade-service/blade-log/target/blade-log.jar jar4/`"&&
echo "`cp blade-service/blade-order/target/blade-order.jar jar4/`"&&
echo "`cp blade-service/blade-pay/target/blade-pay.jar jar4/`"&&
echo "`cp blade-gateway/target/blade-gateway.jar jar4/`"&&

echo "`zip -r jar4.zip jar4/*`" &&


## 现将自己公钥拷贝到服务器的authorized_keys实现免密码登陆 只有免密码登陆才能用scp命令
echo "`scp -i ~/.ssh/id_rsa jar4.zip root@47.111.12.2:~`" &&

## 进入服务器 进行部署操作
ssh root@47.111.12.2  "

#echo `rm -rf jar4/` &&

unzip -o jar4.zip -d jar4 &&

cd jar4 &&

mkdir log &&

ps -aux | grep 'blade' | awk '{print $2}' | xargs kill ;

echo `nohup java -jar blade-foreward-user.jar >log/userlog.txt 2>&1 &` &&
echo `nohup java -jar blade-information.jar >log/infolog.txt 2>&1 &` &&
echo `nohup java -jar blade-log.jar >log/loglog.txt 2>&1 &` &&
echo `nohup java -jar blade-order.jar >log/orderlog.txt 2>&1 &` &&
echo `nohup java -jar blade-pay.jar >log/paylog.txt 2>&1 &` &&
echo `nohup java -jar blade-bg-admin.jar >log/bglog.txt 2>&1 &` &&
echo `nohup java -jar blade-gateway.jar >log/gwlog.txt 2>&1 &`

"
echo done!
