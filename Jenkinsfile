pipeline {
	// 指定任务在哪个集群节点中执行
	agent any

	// 声明全局变量，方便后面使用
	environment {
		harborUser = 'admin'
		harborPasswd = 'secet3157'
		harborAddress = '192.168.0.65:880'
		harborRepo = 'project'
	}

	stages {
		stage('拉取git仓库代码') {
			steps {
				checkout scmGit(branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[url: 'https://www.bakistrim.site:15700/rnncnnc/JavaGenerator.git']])
			}
		}
		
	
	}
}