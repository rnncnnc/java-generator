<script setup>
import { getTemplateList, getTemplateContent, resetTemplate, addTemplateType, addTemplateFile, deleteTemplateType, updateTemplateType, deleteTemplateFile, updateTemplateName, updateTemplateContent } from '@/api/template'
import { ref, onMounted, onUnmounted, nextTick } from 'vue'

import CodeDisplay from './CodeDisplay.vue'
import yaml from 'js-yaml'

import { options } from '@/assets/common'


import {
  Switch,
  RefreshLeft,
  Plus,
  Edit
} from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'


const menuItem = ref([])


onMounted(() => {
    getList()
})


// 获取模板列表
const getList = () => {
    getTemplateList().then(res => {
        menuItem.value = res.data

        menuItem.value.map(item => {
            item.edit = false
            item.children.map(child => {
                child.edit = false
                child.clearName = child.name.split('-')[0]
            })
        })


        ElMessage({
            message: '获取模板列表成功',
            type: 'success'
        })

    }).catch(err => {
        ElMessage({
            message: '获取模板列表失败',
            type: 'error'
        })
    })
}


// 模板内容
const template = ref('')


const changeData = ref('')

const handleContentUpdate = (newVal) => {
    changeData.value = newVal
    
}

const currentTemplateType = ref('')
const currentTemplateFile = ref('')

// 获取对应的模板内容
const handleClick = async (type, file) => {

    // TODO 判断文件是否更改
    if (changeData.value.trim() !== '') {
        await ElMessageBox.confirm('当前页面未保存，是否保存内容？')
        .then(() => {
        // 确认保存
            handleSave()
        })
        .catch(() => {
            changeData.value = ''
        })
    }

    getTemplateContent(type, file).then(res => {
        template.value = JSON.stringify(res.data)
    })

    currentTemplateType.value = type
    currentTemplateFile.value = file
}

// 保存内容
const handleSave = () => {
    if (changeData.value.trim() === '') {
        return
    }


    updateTemplateContent(currentTemplateType.value, currentTemplateFile.value, changeData.value).then(res => {
        ElMessage({
            message: '保存成功',
            type: 'success'
        })
    }).catch(err => {
        ElMessage({
            message: '保存失败',
            type: 'error'
        })
    })

    changeData.value = ''
}

// 快捷键处理函数
const handleKeydown = (e) => {
  // 检查是否按下 Ctrl+S (Windows/Linux) 或 Cmd+S (Mac)
  if ((e.ctrlKey || e.metaKey) && e.key === 's') {
    e.preventDefault();

    handleSave()
  }
};

// 组件挂载时注册事件监听
onMounted(() => {
  // 监听整个文档的keydown事件，但仅在组件活跃时生效
  document.addEventListener('keydown', handleKeydown);
});

// 组件销毁时移除事件监听（关键：避免影响其他组件）
onUnmounted(() => {
  document.removeEventListener('keydown', handleKeydown);
});

const lang = ref('yml')

const codeDisplay = ref()
// 切换语言
const handleChangeLang = async () => {
    if (changeData.value.trim() !== '') {
        await ElMessageBox.confirm('当前页面未保存，是否保存内容？')
        .then(() => {
        // 确认保存
            handleSave()
        })
        .catch(() => {
            changeData.value = ''
        })
    }

    lang.value = lang.value === 'yml' ? 'java' : 'yml'
    codeDisplay.value.changeLanguage(lang.value)

    ElMessage({
        message: '切换成功,当前语言为:' + lang.value,
        type: 'success'
    })

}

// 重置模板
const handleReset = () => {
    resetTemplate().then(res => {
        ElMessage({
            message: '重置成功',
            type: 'success'
        })
    }).catch(err => {
        ElMessage({
            message: '重置失败',
            type: 'error'
        })
    })
}


// 激活的菜单项
const activeIndex = ref('')


const typePopVisible = ref(false)
const templateType = ref('')
// 添加模板分类
const handleAddTemplateType = () => {
    if (!templateType.value.trim()) {
        ElMessage({
            message: '请输入模板分类',
            type: 'error'
        })

        typePopVisible.value = false
        return
    }

    addTemplateType(templateType.value.trim()).then(res => {
        getList()

        ElMessage({
            message: '添加成功',
            type: 'success'
        })

        templateType.value = ''

    }).catch(err => {
        ElMessage({
            message: '添加失败',
            type: 'error'
        })

        templateType.value = ''

    })

    typePopVisible.value = false
}

// 删除模板分类
const handleDeleteTemplateType = (item) => {
    deleteTemplateType(item.name).then(res => {
        getList()

        ElMessage({
            message: '删除成功',
            type: 'success'
        })
    }).catch(err => {
        ElMessage({
            message: '删除失败',
            type: 'error'
        })
    })

    activeIndex.value = ''

}


const drawVisiable = ref(false)

const fileTypes = ref(options)
const classTypes = ref([])

const filename = ref('')
const fileType = ref('')
const classType = ref('')

const currentType = ref('')

// 打开抽屉
const handleOpenDrawer = (item, index) => {
    currentType.value = item.name
    drawVisiable.value = true
    // 加载模板内容

}

// 关闭抽屉
const handleCloseDrawer = () => {
    drawVisiable.value = false
    filename.value = ''
    fileType.value = ''
    classType.value = ''
}


// 处理添加模板文件
const handleAddTemplate = async () => {

    if (!filename.value.trim()) {
        ElMessage({
            message: '请输入模板文件名',
            type: 'error'
        })
        return
    }

    if (!fileType.value) {
        ElMessage({
            message: '请选择模板类型',
            type: 'error'
        })
        return
    }

    if (!classType.value) {
        ElMessage({
            message: '请选择文件类型',
            type: 'error'
        })
        return
    }

    const content = await loadTemplate(fileType.value, classType.value)

    const params = {
        type: currentType.value,
        file: filename.value.trim() + '-' + fileType.value + '.yml',
        content: content
    }

    addTemplateFile(params).then(res => {

        getList()

        ElMessage({
            message: '添加成功',
            type: 'success'
        })

    }).catch(err => {
        ElMessage({
            message: '添加失败',
            type: 'error'
        })

    })

    handleCloseDrawer()
}


// 处理选择器改变
const handleSelectorChange = (item) => {

    for (let index = 0; index < fileTypes.value.length; index++) {
        const element = fileTypes.value[index];
        
        if (element.fileType === item) {
            classTypes.value = element.classType
        }
    }

    

}

// 加载模板
const loadTemplate = async (fileType, classType) => {
    const res = await fetch(`/template/${fileType}/template.yml`)

    const content = await res.text()
    
    const jsonObject = covertYamlToJson(content)

    console.log(typeof jsonObject);
    

    jsonObject['fileType'] = fileType
    jsonObject['classType'] = classType
    

    const ymlCode = convertJsonToYaml(JSON.stringify(jsonObject, null, 2))
    return ymlCode
}

// 将yaml转换为json
const covertYamlToJson = (content) => {

    if (!content) {
        return;
    }
        
    const yamlObject = yaml.load(content)

    
    // const jsonObject = JSON.stringify(yamlObject, null, 2)

    return yamlObject
}


// 将JSON转换为YAML
const convertJsonToYaml = (content) => {
    if (!content) {
        return;
    }
    
    // 先解析JSON字符串为JavaScript对象
    const jsonObject = JSON.parse(content);
    // 再将对象转换为YAML字符串
    return yaml.dump(jsonObject, {
    indent: 2,       // 缩进2个空格
    skipInvalid: true // 跳过无效值
    });
};


const typeInput = ref([])
const changeType = ref('')

// 处理修改模板类型的事件
const handleEditType = async (item) => {

    item.edit = true
    changeType.value = item.name

    await nextTick()
    
    // 3. 安全访问对应的输入框
    const inputRef = typeInput.value[0]
    
    // 4. 检查引用有效性
    if (inputRef?.input?.focus) {
        inputRef.input.focus()
    } else {
        console.error('无法聚焦输入框', inputRef)
        // 调试信息
        // console.log('当前索引:', index)
        console.log('所有输入框引用:', typeInput.value)
    }
}

// 处理修改类型名
const handleTemplateTypeChange = (item) => {
    if (changeType.value.trim() === item.name.trim()) {
        item.edit = false
        return
    }

    

    updateTemplateType(item.name, changeType.value).then(res => {
        getList()

        ElMessage({
            message: '修改成功',
            type: 'success'
        })
    }).catch(err => {
        ElMessage({
            message: '修改失败',
            type: 'error'
        })
    })

    item.edit = false
}

// 删除模板文件
const handleDeleteTemplate = (item, subItem) => {
    deleteTemplateFile(item.name, subItem.name).then(res => {
        getList()

        ElMessage({
            message: '删除成功',
            type: 'success'
        })
    }).catch(err => {
        ElMessage({
            message: '删除失败',
            type: 'error'
        })
    })

}


const templateInput = ref()
const changeTemplate = ref('')

const handleEditTemplate = async (item) => {
    item.edit = true
    changeTemplate.value = item.clearName

    await nextTick()
    
    // 3. 安全访问对应的输入框
    const inputRef = templateInput.value[0]
    
    // 4. 检查引用有效性
    if (inputRef?.input?.focus) {
        inputRef.input.focus()
    } else {
        console.error('无法聚焦输入框', inputRef)
        // 调试信息
        // console.log('当前索引:', index)
        console.log('所有输入框引用:', templateInput.value)
    }
}


// 处理修改模板文件名
const handleTemplateChange = (item, subItem) => {
    if (changeTemplate.value.trim() === subItem.clearName.trim()) {
        subItem.edit = false
        return
    }

    


    updateTemplateName(item.name, subItem.name, changeTemplate.value + '-' + subItem.name.split('-')[1]).then(res => {
        getList()

        ElMessage({
            message: '修改成功',
            type: 'success'
        })
    }).catch(err => {
        ElMessage({
            message: '修改失败',
            type: 'error'
        })
    })

    item.edit = false
}

</script>

<template>
    <div class="main-box">
        <div class="left-menu">
            <div class="preview">
                <h2 class="title">预览模板</h2>
                
                <div class="btn-group">
                    <el-button type="danger" :icon="RefreshLeft" size="small" @click="handleReset">重置</el-button>
                    <el-button type="primary" :icon="Switch" size="small" @click="handleChangeLang">切换</el-button>
                    <el-popover :visible="typePopVisible" placement="bottom" :width="300" trigger="click">
                        <div class="pop-content">
                            <el-input v-model="templateType" style="width: 260px" placeholder="请输入模板类名..." />
                            
                            <div style="text-align: right; marginTop: 10px; width: 100%; float: right">
                                <el-button size="small" type="danger" @click="typePopVisible = false; templateType = '';">取消</el-button>

                                <el-button size="small" type="primary" @click="handleAddTemplateType">
                                    确认
                                </el-button>
                            </div>
                        </div>
                        <template #reference>
                            <el-button type="warning" :icon="Plus" size="small" @click="typePopVisible = true">添加</el-button>
                        </template>
                    </el-popover>
                    
                    <el-button type="success" :icon="Edit" size="small" @click="handleSave">保存</el-button>
                </div>

            </div>

            <!-- default-active="1" -->
            <!-- 模板分类 -->
            <el-menu
                :default-active="activeIndex"
                :unique-opened="true"
                class="el-menu-vertical-demo"
            >
                <template v-for="(item, index) in menuItem" :key="index">
                    <el-sub-menu :index="String(index)">
                        <template #title>
                            <div class="sub-menu-item">
                                <el-input ref="typeInput" v-model="changeType" @click.stop.prevent @blur="handleTemplateTypeChange(item)" style="width: 100px" placeholder="请输入模板类名..." v-if="item.edit" />

                                <h3 class="type-name" @click.stop.prevent="handleEditType(item)" v-else>{{item.name}}</h3>



                                <div class="sub-menu-item-btn">
                                    <el-icon @click.stop.prevent="handleOpenDrawer(item, String(index))"><Plus /></el-icon>

                                    <el-popconfirm
                                        :width="200"
                                        class="box-item"
                                        :title="`确认删除${item.name}吗？`"
                                        placement="right"
                                        @confirm="handleDeleteTemplateType(item)"

                                    >
                                        <template #reference>
                                            <el-icon @click.stop.prevent><Delete /></el-icon>
                                        </template>
                                    </el-popconfirm>
                                </div>
                            </div>
                        </template>

                        <template v-for="(subItem, subIndex) in item.children" :key="subIndex">
                            <el-menu-item :index="`${String(index)}-${String(subIndex)}`" @click="handleClick(item.name, subItem.name)">
                                <template #title>
                                    <div class="menu-item">
                                        <el-input ref="templateInput" v-model="changeTemplate" @click.stop.prevent @blur="handleTemplateChange(item, subItem)" style="width: 100px" placeholder="请输入模板类名..." v-if="subItem.edit" />
                                        
                                        <p class="template-name" @click.stop.prevent="handleEditTemplate(subItem)" v-else>{{subItem.clearName}}</p>

                                        <el-popconfirm
                                            :width="200"
                                            class="box-item"
                                            :title="`确认删除${subItem.name}吗？`"
                                            placement="right"
                                            @confirm="handleDeleteTemplate(item, subItem)"

                                        >
                                            <template #reference>
                                                <el-icon @click.stop.prevent><Delete /></el-icon>
                                            </template>
                                        </el-popconfirm>
                                    </div>
                                </template>

                            </el-menu-item>

                        </template>

                    </el-sub-menu>
                </template>
                
            </el-menu>
        </div>

        <div class="code-editor">
            <CodeDisplay ref="codeDisplay" :template="template" @update="handleContentUpdate" />
        </div>
        <el-drawer v-model="drawVisiable" :direction="direction" @close="handleCloseDrawer">

            <template #header>
            <h4>添加模板文件</h4>
            </template>
            <template #default>
            <div>
                <el-form label-width="auto" style="max-width: 600px">
                    <el-form-item label="fileName">
                        <el-input v-model="filename" placeholder="请输入文件名"  />
                    </el-form-item>
                    <el-form-item label="fileType">
                        <el-select v-model="fileType" placeholder="请选择文件类型"  @change="handleSelectorChange">
                            <el-option
                            v-for="item in fileTypes"
                            :key="item.fileType"
                            :label="item.fileType"
                            :value="item.fileType"
                            />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="classType">
                        <el-select v-model="classType" placeholder="请选择文件类型" >
                            <el-option
                            v-for="item in classTypes"
                            :key="item.name"
                            :label="item.name"
                            :value="item.name"
                            />
                        </el-select>
                    </el-form-item>
                </el-form>
                
                
            </div>
            </template>
            <template #footer>
            <div style="flex: auto">
                <el-button type="danger" @click="handleCloseDrawer">取消</el-button>
                <el-button type="primary" @click="handleAddTemplate">确定</el-button>
            </div>
            </template>
        </el-drawer>

    </div>
</template>

<style lang="scss" scoped>

.main-box {
    width: 100%;
    height: calc(100% - 20px);

    display: flex;
    justify-content: space-around;
    align-items: flex-start;
}

.left-menu {
    // ... 其他保持不变的样式 ...
    width: 24%; // 补偿边框宽度
    height: 85vh; // 补偿边框高度
    margin: 0 auto; // 水平居中
    overflow: auto; // 添加滚动条控制
    border: 1px solid #ccc;
    border-radius: 10px;
    scrollbar-width: none;
}

.code-editor {
    width: 70%;
    height: 85vh;
}


.preview {
    box-sizing: border-box;
    width: 100%;
    padding: 10px 20px;

    .btn-group {
        display: flex;
        width: 100%;
        justify-content: space-around;
    }

}

.pop-content {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}


.title {
    font-size: 18px;
    margin-right: 30px;
}

.sub-menu-item {
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .sub-menu-item-btn {
        float: right;
    }

    .type-name {
        max-width: 100px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
}

.menu-item {
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .template-name {
        max-width: 100px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

}
</style>