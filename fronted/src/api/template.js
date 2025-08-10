import request from "../utils/request";

// 获取模板类型 及 子目录(模板) 列表
export function getTemplateList() {
    return request({
        url: '/api/template/getTemplateList',
        method: 'get',
    })
}

// 获取模板内容
export function getTemplateContent(type, file) {
    return request({
        url: `/api/template/getTemplate?type=${type}&file=${file}`,
        method: 'get',
    })    
}

// 重置模板
export function resetTemplate() {
    return request({
        url: '/api/template/resetTemplate',
        method: 'get'
    })
}

// 添加模板分类
export function addTemplateType(type) {
    return request({
        url: `/api/template/addTemplateType/${type}`,
        method: 'post'
    })

}

// 添加模板
export function addTemplateFile(data) {
    return request({
        url: `/api/template/addTemplateFile`,
        method: 'post',
        data
    })
}

// 删除模板分类
export function deleteTemplateType(type) {
    return request({
        url: `/api/template/deleteTemplateType/${type}`,
        method: 'delete'
    })
}

// 修改模板类型名
export function updateTemplateType(oldType, newType) {
    return request({
        url: `/api/template/updateTemplateType/${oldType}/${newType}`,
        method: 'put'
    })
}

// 删除模板文件
export function deleteTemplateFile(type, file) {
    return request({
        url: `/api/template/deleteTemplateFile/${type}/${file}`,
        method: 'delete'
    })
}

// 更新模板文件名
export function updateTemplateName(type, file, newFile) {
    return request({
        url: `/api/template/updateTemplateFile/${type}/${file}`,
        method: 'put',
        data: {
            file: newFile
        }
    })
}

// 更新模板内容
export function updateTemplateContent(type, file, content) {
    return request({
        url: `/api/template/updateTemplateFile/${type}/${file}`,
        method: 'put',
        data: {
            content
        }
    })
}
