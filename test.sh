#!/usr/bin/env bash

#$echo "`scp -i ~/.ssh/id_rsa jar4.zip root@47.111.12.2:~`" &&
## 进入服务器
ssh root@47.111.12.2  "

unzip -o jar4.zip -d jar5/ &&

cd jar5/ &&

mkdir log


"
echo done!