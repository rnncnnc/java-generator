import axios from 'axios'
import { saveAs } from 'file-saver'
import { ElLoading } from 'element-plus'

const request = axios.create({
  timeout: 100000
})

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

// 加载界面
let loadingInstance

// 请求拦截器
request.interceptors.request.use(function(config) {
    
    // 显示加载界面
    loadingInstance = ElLoading.service({ fullscreen: true })

    return config;
}, function(error) {
    return Promise.reject(error)
})


// 响应拦截器
request.interceptors.response.use(function(res) {
    // 关闭加载界面
    loadingInstance && loadingInstance.close()

    if (res.data.code === 0) {
        return Promise.reject()
    }

    if (res.data instanceof Blob) {
        return res
    }
    return res.data;
}, function(error) {

    // 关闭加载界面
    loadingInstance && loadingInstance.close()

    return Promise.reject(error)
})

const errorCode = {
  '401': '认证失败，无法访问系统资源',
  '403': '当前操作没有权限',
  '404': '访问资源不存在',
  'default': '系统未知错误，请反馈给管理员'
}


// 通用下载方法
export function download(url , params, config) {
    return request.post(url, params, {
        transformRequest: [params => JSON.stringify(params)],
        headers: { 'Content-Type': 'application/json' },
        responseType: 'blob',
        ...config
    }).then(async (res) => {
        console.log(res);
        
        if (res.type !== 'application/json') {
            const blob = new Blob([res.data])
            saveAs(blob, res.headers.get('fName'))
        } else {
            const resText = await res.text()
            const rspObj = JSON.parse(resText)
            const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode['default']
            alert(`下载失败：${errMsg}`)
        }

        console.log('下载完成')
    }).catch(err => {
        console.error(err);
        alert('下载文件出现错误，请联系管理员！')
        console.log("下载终止");
    })
}


export default request

