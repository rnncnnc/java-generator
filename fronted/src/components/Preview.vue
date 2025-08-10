<script setup>
import { getTemplateList, getTemplateContent, resetTemplate, addTemplateType, addTemplateFile, deleteTemplateType, updateTemplateType, deleteTemplateFile, updateTemplateName, updateTemplateContent } from '@/api/template'
import { ref, onMounted, onUnmounted, nextTick, watch } from 'vue'
import { downloadFiles } from "@/api/database"

import { preview } from '@/api/database'


import CodeDisplay from './CodeDisplay.vue'
import yaml from 'js-yaml'

import { options } from '@/assets/common'
import { useIndexStore } from "@/store/index"

import MonacoEditor from 'monaco-editor-vue3';
import * as monaco from 'monaco-editor';

import {
  Switch,
  RefreshLeft,
  Plus,
  Edit,
  Discount
} from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'


const props = defineProps({
    tableList: {
        type: Array,
        default: []
    }
})



const menuItem = ref([])


onMounted(() => {
    getList()
})


// 获取模板列表
const getList = async () => {
    await getTemplateList().then(res => {
        menuItem.value = res.data

        menuItem.value.map(item => {
            item.edit = false
            item.children.map(child => {
                child.edit = false
                child.clearName = child.name.split('-')[0]
                child.type = child.name.split('-')[1].split('.')[0]
            })
        })

        if (currentTemplateType.value.trim() === '') {
            currentTemplateType.value = menuItem.value[0].name
        }


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

// 模板修改后的内容
const changeData = ref('')

// 处理模板修改事件
const handleContentUpdate = (newVal) => {
    changeData.value = newVal
}

// 当前选择的类型
const currentTemplateType = ref('')
// 当前选择的文件
const currentTemplateFile = ref('')

// 自动保存
const autoSave = ref(false)

// 获取对应的模板内容
const handleClick = async (type, file) => {
    if (changeData.value.trim() !== '') {
        // 自动保存
        if (autoSave.value) {
            handleSave()
        } else {
            await ElMessageBox.confirm('当前页面未保存，是否保存内容？')
            .then(() => {
            // 确认保存
                handleSave()
            })
            .catch(() => {
                changeData.value = ''
            })
        }
    }

    currentTemplateType.value = type
    currentTemplateFile.value = file

    

    if (lang.value === 'java') {
        parseToJava()
    } else if (lang.value === 'yml') {
        getTemplateContent(type, file).then(res => {
            template.value = JSON.stringify(res.data)
        })
    }
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

// 当前显示的语言
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

    if (currentTemplateFile.value.trim() === '' || currentTemplateType.value.trim() === '') {
        ElMessage({
            message: '请先选择模板',
            type: 'error'
        })

        return
    }


    lang.value = lang.value === 'yml' ? 'java' : 'yml'


    if (lang.value === 'java') {
        parseToJava()
    }

    codeDisplay.value.changeLanguage(lang.value)

}

// 解析为java
const parseToJava = () => {
    const indexStore = useIndexStore()
    const basePackage = indexStore.getBasePackage

    let tempTableList = null

    if (props.tableList.length !== 0) {
        tempTableList = [props.tableList[0]]
    }

    const params = {
        tableList: tempTableList,
        template: {
            type: currentTemplateType.value,
            file: currentTemplateFile.value
        },
        basePackage: basePackage
    }

    // 获取预览文件
    preview(params).then(res => {
        template.value = res.data
    })
}

// 重置模板
const handleReset = () => {
    resetTemplate().then(res => {
        getList()

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

// 类型弹出框是否显示
const typePopVisible = ref(false)
// 类型input输入
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

// 模板字段弹窗是否显示
const templateFieldDialogVisible = ref(false)

// 选择框数组
const fileTypes = ref(options)
const classTypes = ref([])

// 表单选项
const filename = ref('')
const fileType = ref('')
const classType = ref('')

// 打开弹窗
const handleOpenTemplateFieldDialog = (item, index) => {
    currentTemplateType.value = item.name
    templateFieldDialogVisible.value = true
    // 加载模板内容

}

// 关闭弹窗
const handleCloseTemplateFieldDialog = () => {
    templateFieldDialogVisible.value = false
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
        type: currentTemplateType.value,
        file: filename.value.trim() + '-' + fileType.value + '.yml',
        content: content
    }

    addTemplateFile(params).then(async res => {

        await getList()

        // 刷新菜单索引
        updateMenuIndex(currentTemplateType.value, filename.value)

        ElMessage({
            message: '添加成功',
            type: 'success'
        })

        // 关闭弹窗并清空数据
        handleCloseTemplateFieldDialog()
    }).catch(err => {
        ElMessage({
            message: '添加失败',
            type: 'error'
        })

    })
}

// 更新当前的菜单索引
const updateMenuIndex = (type, file) => {
    let typeIndex = 0
    let fileIndex = 0

    let filename = ''

    // 遍历找出当前文件的索引
    for(let i = 0; i < menuItem.value.length; i++) {
        if (menuItem.value[i].name === type) {
            typeIndex = i

            for (let j = 0; j < menuItem.value[i].children.length; j++) {
                if (menuItem.value[i].children[j].clearName === file) {
                    fileIndex = j
                    filename = menuItem.value[i].children[j].name
                }
            }
        }
    }

    activeIndex.value = `${String(typeIndex)}-${String(fileIndex)}`

    // 获取模板内容
    handleClick(type, filename)
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

// 生成对话框是否显示
const generateDialogVisible = ref(false)

const emits = defineEmits(['generate'])

// 生成文件
const handleGenerate = () => {

    const indexStore = useIndexStore()
    const basePackage = indexStore.getBasePackage


    const params = {
        basePackage: basePackage,
        tableList: props.tableList,
        template: {
            type: currentTemplateType.value
        }
    }

    downloadFiles(params).then(res => {
        generateDialogVisible.value = false

        emits('generate')

        ElMessage.success('生成代码成功')
    })
}


// 抽屉显示
const drawVisiable = ref(false)

const parseData = ref([])

const codeContent = ref("1111")
const activeName = ref('')

const editorOptions = ref({
   automaticLayout: true, // 自动适应容器大小
    minimap: { enabled: false }, // 显示缩略图
    scrollBeyondLastLine: true,
    fontSize: 12,
    lineNumbers: 'on',
    folding: true,
    lineDecorationsWidth: 10,
    lineNumbersMinChars: 5,
    roundedSelection: false,
    scrollbar: {
        vertical: 'visible', // 始终显示垂直滚动条
        horizontal: 'visible', // 始终显示水平滚动条
        verticalScrollbarSize: 10, // 垂直滚动条宽度
        horizontalScrollbarSize: 10 // 水平滚动条高度
    },
    theme: 'vs', // 使用深色主题
    readOnly: true
});

// 处理抽屉打开事件,显示解析文件
const handleOpenDrawer = async () => {
    drawVisiable.value = true

    const specialInfo = await fetch(`/template/SpecialFieldExplain.exp`)
    const specialData = await specialInfo.text()
    parseData.value.push({
        fileType: 'SpecialFieldExplain',
        content: specialData
    })

    for (let i = 0; i < fileTypes.value.length; i++) {
        
        const res = await fetch(`/template/${fileTypes.value[i].fileType}/template.yml`)
        const data = await res.text()

        const obj = {
            fileType: fileTypes.value[i].fileType,
            content: data
        }
        
        parseData.value.push(obj)
    }

    activeName.value = 'SpecialFieldExplain'
    codeContent.value = specialData
}

// 编辑器实例对象
const editorRef = ref()

// 点击tab事件,更新codeContent内容
const handleDrawerTabClick = (tab) => {
    parseData.value.forEach(item => {
        if (item.fileType === tab.props.name) {
            codeContent.value = item.content
        }
    })

    editorRef.value.editor.setScrollPosition({ scrollTop: 0 })
}

</script>

<template>
    <div class="main-box">
        <div class="left-menu">
            <div class="preview">
                <div class="header">
                    <h2 class="title">预览模板</h2>
                    <el-checkbox v-model="autoSave" label="as" size="large" />
                    <el-button type="info" size="normal" @click="handleOpenDrawer" :icon="Discount" circle ></el-button>

                    <el-button type="primary" size="large" @click="generateDialogVisible = true">GENERATE</el-button>
                </div>
                
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
                                    <el-icon @click.stop.prevent="handleOpenTemplateFieldDialog(item, String(index))"><Plus /></el-icon>

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

                        <template v-for="(type, typeIndex) in fileTypes" :key="typeIndex">
                            <el-menu-item-group :title="type.fileType" >
                                <template v-for="(subItem, subIndex) in item.children" :key="subIndex">
                                    <el-menu-item :index="`${String(index)}-${String(subIndex)}`" @click="handleClick(item.name, subItem.name)" v-if="subItem.type === type.fileType">
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
                            </el-menu-item-group>
                        </template>
                        

                    </el-sub-menu>
                </template>
                
            </el-menu>
        </div>

        <div class="code-editor">
            <CodeDisplay ref="codeDisplay" :template="template" @update="handleContentUpdate" />
        </div>
        <el-drawer v-model="drawVisiable" title="文件解析" :with-header="false">
            <template #default>
            <div>
                <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleDrawerTabClick">
                    <el-tab-pane :label="item.fileType" :name="item.fileType" v-for="(item, index) in parseData" :key="index"></el-tab-pane>
                </el-tabs>
                <div class="editor-container">
                    <MonacoEditor
                    ref="editorRef"
                    v-model:value="codeContent"
                    :options="editorOptions"
                    class="monaco-editor"
                    />
                </div>
            </div>
            </template>
        </el-drawer>

        <!-- 添加模板弹窗 -->
        <el-dialog v-model="templateFieldDialogVisible" title="选择使用的模板类型" width="500" @close="handleCloseTemplateFieldDialog">

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
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="handleCloseTemplateFieldDialog">Cancel</el-button>
                    <el-button type="primary" @click="handleAddTemplate">
                    Confirm
                    </el-button>
                </div>
            </template>
        </el-dialog>

         <el-dialog v-model="generateDialogVisible" title="选择使用的模板类型" width="500">
            <el-select v-model="currentTemplateType">
                <el-option :label="item.name" :value="item.name" v-for="(item, index) in menuItem" :key="index" />
            </el-select>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="generateDialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="handleGenerate">
                    Confirm
                    </el-button>
                </div>
            </template>
        </el-dialog>

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

    .header {
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

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

.editor-container {
  width: 100%;
  height: 85vh;
  padding: 0 20px;
  box-sizing: border-box;
}

.monaco-editor {
  width: 100%;
  height: calc(100% - 80px);
  border: 1px solid #ccc;
  border-radius: 4px;
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