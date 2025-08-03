import { defineStore } from 'pinia'

export const useIndexStore = defineStore('index', {
  state: () => {
    return {
      basePackage: ''
    }
  },
  getters: {
    getBasePackage: (state) => {
      return state.basePackage
    }
  },
  actions: {
    setBasePackage(basePackage) {
      this.basePackage = basePackage
    }
  }
})