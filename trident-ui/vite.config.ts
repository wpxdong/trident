import { fileURLToPath, URL } from 'node:url'
// import vueI18n from '@intlify/unplugin-vue-i18n/vite'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'
// import path from 'node:path'
import VueI18nPlugin from '@intlify/unplugin-vue-i18n/vite'
import { resolve, dirname } from 'node:path'
// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx(),
    vueDevTools(),
    // vueI18n({
    //   include: path.resolve(__dirname, './src/locales/**'),
    // }),
    VueI18nPlugin({
      include: resolve(dirname(fileURLToPath(import.meta.url)), './src/locales/**'),
      strictMessage: false,
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
      scss: fileURLToPath(new URL('./src/assets/scss', import.meta.url)),
      image: fileURLToPath(new URL('./src/assets/image', import.meta.url)),
    },
  },
})
