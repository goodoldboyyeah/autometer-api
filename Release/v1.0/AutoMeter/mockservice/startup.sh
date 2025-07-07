#!/bin/sh
echo "start.................."
dir=$(cd "$(dirname "$0")";pwd)
jar=/mockservice.jar
config=/config/application.yml
jarpath=$dir$jar
configpath=$dir$config
echo $jarpath
echo $configpath
nohup java -Xms256m -Xmx256m -Xmn128m -XX:ReservedCodeCacheSize=120M -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -XX:+UseConcMarkSweepGC -XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -XX:-UseBiasedLocking -XX:-UseCounterDecay -XX:AutoBoxCacheMax=20000 -XX:+PerfDisableSharedMem -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -jar $jarpath --spring.config.location=$configpath &
echo "start mockservice ok..........."
