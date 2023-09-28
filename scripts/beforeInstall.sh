#!/bin/bash
REPOSITORY=/home/ubuntu/tago
CONTAINER_NAME=tago-spring
IMAGE_NAME=121284569119.dkr.ecr.ap-northeast-2.amazonaws.com/tago-dev:latest

cd $REPOSITORY

CONTAINER_PID=$(docker container ls --all | grep -w $CONTAINER_NAME | awk '{print $1}')
CONTAINER_IMAGE_ID=$(docker images -q $IMAGE_NAME)

if [ -n ${CONTAINER_PID} ];
then
  echo "> 🔵 STOP $CONTAINER_PID CONTAINER"
  docker-compose down
fi

if [[ -n ${CONTAINER_IMAGE_ID} ]];
then
  docker rmi -f $(docker images --filter=reference=$IMAGE_NAME -q)
fi