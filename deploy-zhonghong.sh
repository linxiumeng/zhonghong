
#使用说明，用来提示输入参数
usage() {
	echo "Usage: sh 执行脚本.sh [base|modules|stop|rm|rmiNoneTag]"
	exit 1
}

#启动基础模块
base(){
	docker-compose up -d nacos blade-redis
}

#启动程序模块
modules(){
	docker-compose up -d blade-bg-admin blade-information blade-foreward-user blade-pay blade-order blade-log blade-gateway
}

#关闭所有模块
stop(){
	docker-compose stop
}

#删除所有模块
rm(){
	docker-compose rm
}

#删除Tag为空的镜像
rmiNoneTag(){
	docker images|grep none|awk '{print $3}'|xargs docker rmi -f
	docker images|grep blade|awk '{print $3}'|xargs docker rmi -f
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"port")
	port
;;
"mount")
	mount
;;
"base")
	base
;;
"modules")
	modules
;;
"stop")
	stop
;;
"rm")
	rm
;;
"rmiNoneTag")
	rmiNoneTag
;;
*)
	usage
;;
esac
