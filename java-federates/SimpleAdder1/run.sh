#!/usr/bin/env bash

cd target
java -jar \
-Djava.library.path=nar/processid-0.3.0-SNAPSHOT-amd64-Linux-gpp-jni/lib/amd64-Linux-gpp/jni/ \
-Djava.net.preferIPv4Stack=true \
SimpleAdder1-0.0.1-SNAPSHOT.jar \
SimpleAdder \
InputSource \
NORMAL
cd ..
