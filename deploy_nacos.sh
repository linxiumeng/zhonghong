#!/usr/bin/env bash

## 启动docker运行nacos
docker run --name nacos-standalone -e MODE=standalone -p 8848:8848 nacos/nacos-server:latest

## 配置nacos的config

## 打开localhost:8848/nacos -> 用户名密码都是nacos -> 配置管理 -> 配置列表 -> 点击'+'新增配置
## 第一个
## Data ID: blade-dev.yaml
## 配置格式: YAML
## 配置内容:
## #spring配置
#spring:
#  redis:
#    ##redis 单机环境配置
#    host: 127.0.0.1
#    port: 6379
#    password:
#    database: 0
#    ssl: false
#    ##redis 集群环境配置
#    #cluster:
#    #  nodes: 127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003
#    #  commandTimeout: 5000
#
##项目模块集中配置
#blade:
#  #通用开发生产环境数据库地址(特殊情况可在对应的子工程里配置覆盖)
#  datasource:
#    dev:
#      url: jdbc:mysql://139.196.95.27:3306/bladex?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&allowMultiQueries=true&serverTimezone=GMT%2B8
#      username: root
#      password: 123456
#
#finepetro:
#  #通用开发生产环境数据库地址(特殊情况可在对应的子工程里配置覆盖)
#  datasource:
#    dev:
#      url: jdbc:mysql://139.196.95.27:3306/finepetro?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&allowMultiQueries=true&serverTimezone=GMT%2B8

## 去掉注释符号'#'


## 第二个
## Data ID: blade.yaml
## 配置格式: YAML
## 配置内容:
##服务器配置
#server:
#  undertow:
#    # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程
#    io-threads: 4
#    # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载
#    worker-threads: 20
#    # 以下的配置会影响buffer,这些buffer会用于服务器连接的IO操作,有点类似netty的池化内存管理
#    buffer-size: 1024
#    # 是否分配的直接内存
#    direct-buffers: true
#
##spring配置
#spring:
#  devtools:
#    restart:
#      log-condition-evaluation-delta: false
#    livereload:
#      port: 23333
#
##feign配置
#feign:
#  hystrix:
#    enabled: true
#    #sentinel:
#    #enabled: true
#  okhttp:
#    enabled: true
#  httpclient:
#    enabled: true
#
##hystrix配置
#hystrix:
#  threadpool:
#    default:
#      coreSize: 300
#      maxQueueSize: 1000
#      queueSizeRejectionThreshold: 800
#  command:
#    default:
#      execution:
#        isolation:
#          thread:
#            timeoutInMilliseconds: 10000
#
#
##ribbon配置
#ribbon:
#  #对当前实例的重试次数
#  MaxAutoRetries: 1
#  #切换实例的重试次数
#  MaxAutoRetriesNextServer: 2
#  #请求处理的超时时间
#  ReadTimeout: 60000
#  #请求连接的超时时间
#  ConnectTimeout: 60000
#  #对所有操作请求都进行重试
#  OkToRetryOnAllOperations: true
#
##对外暴露端口
#management:
#  endpoints:
#    web:
#      exposure:
#        include: "*"
#  endpoint:
#    health:
#      show-details: always
#
##blade配置
#blade:
#  secure:
#    url:
#      exclude-patterns:
#        - /test/**
#    client:
#      - client-id: sword
#        path-patterns:
#          - /sword/**
#      - client-id: saber
#        path-patterns:
#          - /saber/**
#  tenant:
#    column: tenant_code
#    tables:
#      - blade_notice

## 去掉注释符号'#'
