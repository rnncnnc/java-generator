import request from "../utils/request";
import { download } from '@/utils/request'

// 获取表信息
export function getTableList(data) {
    return request({
        url: '/api/build/table/list',
        method: 'post',
        data
    })
}

// 下载生成文件
export function downloadFiles(data) {
    return download('/api/build/builder', data)
}