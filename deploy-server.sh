#!/usr/bin/env bash

echo `ps -aux | grep 'blade' | awk '{print $2}' | xargs kill `;

echo aaa;

echo `nohup java -jar blade-foreward-user.jar >log/userlog.txt 2>&1 &` &&
echo `nohup java -jar blade-information.jar >log/infolog.txt 2>&1 &` &&
echo `nohup java -jar blade-log.jar >log/loglog.txt 2>&1 &` &&
echo `nohup java -jar blade-order.jar >log/orderlog.txt 2>&1 &` &&
echo `nohup java -jar blade-pay.jar >log/paylog.txt 2>&1 &` &&
echo `nohup java -jar blade-bg-admin.jar >log/bglog.txt 2>&1 &` &&
echo `nohup java -jar blade-gateway.jar >log/gwlog.txt 2>&1 &`