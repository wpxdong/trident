/* eslint-disable @typescript-eslint/no-explicit-any */
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import { createI18n } from 'vue-i18n'
import { getCookie, setCookie } from './utils'
const _default = (r: any) => r.default || r
const zh = 'zh-CN',
  en = 'en-US'
if (![zh, en].includes(getCookie('lang'))) {
  setCookie('lang', zh, window.location.hostname)
  window.localStorage.setItem('lang', zh)
}
const locales: string[] = []
const resources = import.meta.glob('@/locales/*.json')
Object.values(resources).forEach((locale) => {
  const matched = locale.name.match(/([A-Za-z0-9-_]+)\./i)
  if (matched && matched.length > 1) {
    const lang = matched[1] === undefined ? zh : matched[1]
    if (lang) locales.push(lang)
  }
})
async function loadLocaleMessages() {
  /*@vite-ignore */
  const messages: Record<string, any> = {}
  for (const locale of locales) {
    const resource = _default(await import(`@/locales/${locale}.json`))
    messages[locale] = resource
  }
  return messages
}
const messages = await loadLocaleMessages()
const i18n = createI18n({
  locale: getCookie('lang') || zh,
  fallbackLocale: getCookie('lang') || zh,
  messages: messages,
})
const app = createApp(App)
app.use(ElementPlus)
app.use(createPinia())
app.use(router)
app.use(i18n)
app.mount('#app')
