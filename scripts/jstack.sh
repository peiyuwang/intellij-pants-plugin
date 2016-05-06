#!/usr/bin/env bash

# This script periodically wakes up and look for pants ConsoleRunner process,
# runs jstack against it if it finds one, to run on travis for debug purpose.

NUM_SAMPLES=30
SLEEP_INTERNAL_SECONDS=60


i="1"

while [ ${i} -le ${NUM_SAMPLES} ]
do
	if [ ! -z ${PID} ]; then
		echo "============================================================================="
		echo "Sampling ps for pants ConsoleRunner process (${i}/${NUM_SAMPLES})"
		echo "============================================================================="
		echo
		echo -n "Total NGServer: "
        ps -ef|grep com.martiansoftware.nailgun.NGServer |grep -v grep|wc -l
        echo
        ps -ef|grep com.martiansoftware.nailgun.NGServer |grep -v gre
	fi

	sleep ${SLEEP_INTERNAL_SECONDS}
	i=$[${i}+1]
done

