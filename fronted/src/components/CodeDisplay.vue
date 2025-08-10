<template>
  <div class="editor-container">
    <MonacoEditor
      ref="editorRef"
      v-model:value="codeContent"
      :language="props.currentLanguage"
      :options="editorOptions"
      class="monaco-editor"
    />
  </div>
</template>

<script setup>
import { ref, onMounted, watch, nextTick, readonly } from 'vue';
import MonacoEditor from 'monaco-editor-vue3';
import * as monaco from 'monaco-editor';
import yaml from 'js-yaml';

const props = defineProps({
  template: {
    type: String,
    default: ''
  }
})

// 编辑器配置
const editorOptions = ref({
    automaticLayout: true, // 自动适应容器大小
    minimap: { enabled: true }, // 显示缩略图
    scrollBeyondLastLine: true,
    fontSize: 14,
    lineNumbers: 'on',
    folding: true,
    lineDecorationsWidth: 10,
    lineNumbersMinChars: 2,
    roundedSelection: false,
    scrollbar: {
        vertical: 'visible', // 始终显示垂直滚动条
        horizontal: 'visible', // 始终显示水平滚动条
        verticalScrollbarSize: 10, // 垂直滚动条宽度
        horizontalScrollbarSize: 10 // 水平滚动条高度
    },
    theme: 'vs', // 使用深色主题
    readOnly: false
});


// 编辑器内容
const codeContent = ref('');

// yml 代码
const ymlCode = ref('')

// java 代码
const javaCode = ref('')

const currentLang = ref('yml')

// 切换语言
const changeLanguage = (lang) => {
    currentLang.value = lang


    if (currentLang.value === 'java') {
        editorOptions.readOnly = true
        codeContent.value = javaCode.value
    } else if (currentLang.value === 'yml') {
        contentInit.value = true
        editorOptions.readOnly = false
        codeContent.value = ymlCode.value
    }

    scrollToTop()
};

const editorRef = ref()

// 滚动到顶部的方法
const scrollToTop = () => {
  if (editorRef.value && editorRef.value.editor) {

    editorRef.value.editor.setScrollPosition({ scrollTop: 0 })
    // const editorDom = editorRef.value.editor.getDomNode();
    // if (editorDom) {
    //   editorDom.scrollTop = 0;
    // }
  }
}


// 将JSON转换为YAML
const convertJsonToYaml = () => {
    if (!props.template) {
        ymlCode.value = '';
        return;
    }
    
    try {
        // 先解析JSON字符串为JavaScript对象
        const jsonObject = JSON.parse(props.template);
        // 再将对象转换为YAML字符串
        ymlCode.value = yaml.dump(jsonObject, {
        indent: 2,       // 缩进2个空格
        skipInvalid: true // 跳过无效值
        });
    } catch (error) {
        ymlCode.value = '';
    }
};

const contentInit = ref(false)

// 监听模板变化
watch(() => props.template, (newVal) => {
    nextTick(() => {
        if (currentLang.value === 'yml') {
          convertJsonToYaml()
          codeContent.value = ymlCode.value
          contentInit.value = true
        } else if (currentLang.value === 'java') {
          javaCode.value = newVal
          codeContent.value = javaCode.value
        }

        scrollToTop()
    })
}, {
    immediate: true
})

const emits = defineEmits(['update'])

// 监听内容变化
watch(() => codeContent.value, (newVal) => {
  if (contentInit.value) {
    contentInit.value = false
  } else {
    if (currentLang.value === 'yml') {
      ymlCode.value = newVal
      emits('update', newVal)
    } else if (currentLang.value === 'java') {
      javaCode.value = newVal
    }
  }
})


defineExpose({
    changeLanguage
})
</script>

<style scoped>
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
</style>
