#!/bin/sh

JAVA_OPTS="-Xmx6G -Xms6G -XX:NewSize=256m -XX:MaxNewSize=256m -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=2 -XX:TargetSurvivorRatio=50 -XX:+CMSClassUnloadingEnabled -XX:+HeapDumpOnOutOfMemoryError -XX:+UseG1GC"

hoaPID=0

getHoaPID(){
    javaps=`ps -ef | grep hoa-*.jar | grep -v "$0" | grep -v "grep"`
    if [ -n "$javaps" ]; then
        hoaPID=`echo $javaps | awk '{print $2}'`
    else
        hoaPID=0
    fi
}

startup(){
    getHoaPID
    echo "================================================================================================================"
    if [ $hoaPID -ne 0 ]; then
        echo "HOA already started(PID=$hoaPID)!"
        echo "================================================================================================================"
    else
        echo "Starting HOA..."
        set CLASSPATH=.
        nohup java $JAVA_OPTS -jar hoa-*.jar >logs/server.out 2>&1 & 
       getHoaPID
      if [ $hoaPID -ne 0 ]; then
            echo "HOA started successful(PID=$hoaPID)!"
            echo "================================================================================================================"
        else
            echo "HOA started  failed!"
            echo "================================================================================================================"
        fi
    fi
}

startup

