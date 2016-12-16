#!/bin/sh
hoaPID=0

getHoaPID(){
    javaps=`ps -ef | grep hoa-*.jar | grep -v "$0" | grep -v "grep"`
    if [ -n "$javaps" ]; then
        hoaPID=`echo $javaps | awk '{print $2}'`
    else
        hoaPID=0
    fi
}

shutdown(){
    getHoaPID
    echo "================================================================================================================"
    if [ $hoaPID -ne 0 ]; then
        echo "Stopping HOA(PID=$hoaPID)..."
        kill -9 $hoaPID
        if [ $? -eq 0 ]; then
            echo "HOA stopped successful!"
            echo "================================================================================================================"
        else
            echo "HOA stopped failed!"
            echo "================================================================================================================"
        fi
        getHoaPID
        if [ $hoaPID -ne 0 ]; then
            shutdown
        fi
    else
        echo "HOA is not running"
        echo "================================================================================================================"
    fi
}

shutdown

