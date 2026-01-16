import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { i18n } from './i18n'
import '@fortawesome/fontawesome-free/css/all.min.css'
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(i18n)
app.use(ElementPlus)
app.use(createPinia())
app.use(router)
app.mount('#app')
