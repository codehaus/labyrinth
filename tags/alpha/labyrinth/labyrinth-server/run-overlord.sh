#!/bin/bash
REPO_HOME=$MAVEN_HOME/repository

$JAVA_HOME/bin/java \
    -classpath $REPO_HOME/classworlds/jars/classworlds-1.0-beta-5.jar \
    -Dmaven.repo.local=$REPO_HOME \
    -Dclassworlds.conf=overlord.conf \
    com.werken.classworlds.Launcher \
    $*
    
