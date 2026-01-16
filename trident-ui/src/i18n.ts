import messages from '@intlify/unplugin-vue-i18n/messages'
import { getCookie, setCookie } from './utils'
import { createI18n } from 'vue-i18n'
const zh = 'zh-CN',
  en = 'en-US'
if (![zh, en].includes(getCookie('lang'))) {
  setCookie('lang', zh, window.location.hostname)
  window.localStorage.setItem('lang', zh)
}
//https://www.docs4.dev/posts/how-to-load-i18n-locales-asynchronously-in-vue-3-vite
export const i18n = createI18n({
  locale: getCookie('lang') || zh,
  fallbackLocale: getCookie('lang') || zh,
  messages: messages,
  legacy: false,
  globalInjection: true,
})
