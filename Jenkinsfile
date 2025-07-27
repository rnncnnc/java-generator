pipeline {
    // 指定任务在哪个集群节点中执行
    agent any

    // 声明全局变量，方便后面使用
    environment {
        tag = ""
        harborUser = 'admin'
        harborPasswd = 'secet3157'
        harborAddress = '192.168.0.65:880'
        harborRepo = 'project'
    }

    stages {
        stage('解析 Tag') {
            steps {
                script {
                    echo "${env.git_ref}"
                    if (env.git_ref) {
                        // Webhook 触发：从 git_ref 解析（如 refs/tags/v1.0.0 → v1.0.0）
                        env.tag = env.git_ref.split('/')[2]
                    } else if (params.tag) {
                        // 手动参数触发：直接使用手动输入的 Tag
                        env.tag = params.tag
                    } else {
                        error '未检测到 Tag 来源（需 Webhook 触发或手动输入 MANUAL_TAG）'
                    }
                    echo "当前 Tag: ${env.tag}"
                }
            }
        }

        stage('拉取git仓库代码') {
            steps {
                checkout scmGit(branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[url: 'https://www.bakistrim.site:15700/rnncnnc/JavaGenerator.git']])
            }
        }
        
        stage('通过nodejs构建前端项目') {
            steps {
                sh '''cd fronted
                /var/jenkins_home/nodejs/bin/pnpm install
                /var/jenkins_home/nodejs/bin/pnpm build
                '''
            }
        }
        
        stage('通过maven构建后端项目') {
            steps {
                sh '''cd backend
                /var/jenkins_home/maven/bin/mvn clean package -DskipTests'''
            }
        }
        
        stage('通过sonarqube做前端代码质量检测') {
            steps {
                sh '''/var/jenkins_home/sonar-scanner/bin/sonar-scanner \\
                -Dsonar.sources=./fronted/src \\
                -Dsonar.projectName=${JOB_NAME}-fronted \\
                -Dsonar.projectKey=${JOB_NAME}-fronted \\
                -Dsonar.exclusions='**/node_modules/**,** /dist/**,**/*.test.js,**/*.spec.js,**/coverage/**' \\
                -Dsonar.vue.file.suffixes=.vue \\
				-Dsonar.sourceEncoding=UTF-8 \\
				-Dsonar.projectVersion=${tag} \\
                -Dsonar.token=squ_fa0b705d0cf3e1503eed143e9e8d36446ca25146'''
            }
        }
        
        stage('通过sonarqube做后端代码质量检测') {
            steps {
                sh '''/var/jenkins_home/sonar-scanner/bin/sonar-scanner \\
                -Dsonar.source=./backend/ \\
                -Dsonar.projectName=${JOB_NAME}-backend \\
                -Dsonar.projectKey=${JOB_NAME}-backend \\
                -Dsonar.java.binaries=./backend/target/ \\
				-Dsonar.projectVersion=${tag} \\
                -Dsonar.token=squ_fa0b705d0cf3e1503eed143e9e8d36446ca25146'''
            }
        }
        
        stage('通过docker制作自定义镜像') {
            steps {
                sh '''rm -rf ./docker/java-generator
                mkdir ./docker/java-generator
                mv ./backend/target/*.jar ./docker/java-generator/backend.jar
                mv ./fronted/dist ./docker/java-generator
                docker build ./docker -t ${JOB_NAME}:${tag}'''
            }
        }
        
        stage('将自定义镜像推送到harbor') {
            steps {
                sh '''docker login -u ${harborUser} -p ${harborPasswd} ${harborAddress}
                docker tag ${JOB_NAME}:${tag} ${harborAddress}/${harborRepo}/${JOB_NAME}:${tag}
                docker push ${harborAddress}/${harborRepo}/${JOB_NAME}:${tag}'''
            }
        }
        
        stage('通过 publish over ssh 通知目标服务器') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'Server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "deploy.sh $harborAddress $harborRepo $JOB_NAME $tag $host_port $container_port", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    
    }
    
    // 通知
    post {
        success {
            emailext body: '''部署成功：${JOB_NAME} \n
            时间：${date} \n
            访问地址：http://${server_ip}:${host_port} \n
            测试报告-前端：https://www.bakistrim.site:12700/dashboard?id=${JOB_NAME}-fronted \n
            测试报告-后端：https://www.bakistrim.site:12700/dashboard?id=${JOB_NAME}-backend''', subject: '${JOB_NAME}', to: '2769876032@qq.com'
        }

        failure {
            emailext body: '''部署失败：${JOB_NAME} \n
            时间：${date}''', subject: '${JOB_NAME}', to: '2769876032@qq.com'
        }
    }
}