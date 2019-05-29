### 首先安装docker（本地和服务器都要安装），harbor（docker包的管理系统），maven（依赖管理）用于部署操作，详细过程请百度，这里就不说了

#### 最外层的pom.xml配置harbor仓库地址
````
        <!-- 推荐使用Harbor -->
        
        harbor的域名地址
        <docker.registry.url>docker-registry.verasti.com</docker.registry.url>
        本地的docker进程，mac如下，windows自查
        <docker.registry.host>unix:///var/run/docker.sock</docker.registry.host>
        版本号
        <docker.plugin.version>1.2.0</docker.plugin.version>
````

#### 修改maven的settings.xml文件
````
            这里需配置maven的配置文件 直接通过harbor的用户名密码上传到harbor管理中心
            <server>
	            <id>docker-registry.verasti.com</id>
	            <username>blade</username>
	            <password>Hb123456</password>
				<configuration>
				      <email>smallchill@163.com</email>
				</configuration>
	        </server>
````

#### 这里注意一下 docker login 的时候 如果harbor域名使用的不是https的话 需要将docker的配置文件改点
````
linux: /etc/docker/daemon.json 添加
"insecure-registries" : [
    "docker-registry.verasti.com"
  ]


mac: docker 小海豚 -> preference -> Daemon -> Advance -> 填入下面的地址
"insecure-registries" : [
    "docker-registry.verasti.com"
  ]


windows自便
````

#### 全部改好之后 调用 deploy.sh 脚本进行部署




