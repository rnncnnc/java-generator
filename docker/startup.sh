#!/bin/bash

# 设置错误处理
set -e

# 日志函数
log() {
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] $1"
}

# 清理函数
cleanup() {
    log "收到停止信号，正在关闭服务..."
    
    # 先停止Java进程(避免Nginx关闭后仍有请求)
    if [ -n "$JAVA_PID" ] && kill -0 "$JAVA_PID" 2>/dev/null; then
        log "停止Java进程($JAVA_PID)..."
        kill -TERM "$JAVA_PID"
        wait "$JAVA_PID" || true
    fi
    
    # 再停止Nginx进程
    if [ -n "$NGINX_PID" ] && kill -0 "$NGINX_PID" 2>/dev/null; then
        log "停止Nginx进程($NGINX_PID)..."
        kill -TERM "$NGINX_PID"
        wait "$NGINX_PID" || true
    fi
    
    log "所有服务已关闭"
    exit 0
}

# 注册信号处理
trap cleanup SIGINT SIGTERM SIGQUIT

# 启动Nginx作为前台进程
log "启动Nginx服务..."
nginx -g 'daemon off;' &
NGINX_PID=$!
log "Nginx已启动, PID: $NGINX_PID"

# 启动Java应用
log "启动Java服务..."
java -jar backend.jar &
JAVA_PID=$!
log "Java已启动, PID: $JAVA_PID"

# 等待所有子进程
wait -n $NGINX_PID $JAVA_PID
EXIT_STATUS=$?

# 记录退出状态
log "一个服务已退出，状态码: $EXIT_STATUS"

# 触发清理
cleanup
