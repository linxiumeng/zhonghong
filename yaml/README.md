# 中鸿4.0

#### 介绍
{**以下是码云平台说明，您可以替换此简介**
码云是开源中国推出的基于 Git 的代码托管平台（同时支持 SVN）。专为开发者提供稳定、高效、安全的云端软件开发协作平台
无论是个人、团队、或是企业，都能够用码云实现代码托管、项目管理、协作开发。企业项目请看 [https://gitee.com/enterprises](https://gitee.com/enterprises)}

#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)


#### 项目说明
1. blade-common : 提取的公共类 其中包括工具类（util），实体类（entity），通用配置（config），表单（form），注解（annotation），拦截器（Interceptor）等等
2. blade-gateway : 网关模块
3. blade-service : 业务service模块 其中包括跳转类（controller），数据库底层操作类（dao），业务类（service），业务实现类（service.impl）
4. blade-service-api : 抽取出blade-service的一些东西 比如Feign的service

##### 针对于blade-service再细分一下
1. blade-foreward-user : 前台用户服务
2. blade-information : 信息相关服务 首页展示图（banner），新闻文章（news），公告（announcement），商品信息（goods）
3. blade-pay : 支付相关服务（未来要不断的接入）
4. blade-order : 需求单（demand），报价单（quotation），采购单（purchaseOrder）