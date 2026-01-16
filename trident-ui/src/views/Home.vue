<template>
  <Header :list="breadcrumbList" />
  <div class="home"></div>
</template>
<script lang="ts" setup>
import Header from '@/components/Header.vue'
import { useI18n } from 'vue-i18n'
import type { IBreadcrumbItem } from '@/vo/Layout'
import { ref } from 'vue'
const { t } = useI18n()
const breadcrumbList = ref<IBreadcrumbItem[]>([
  {
    name: t('M_BREAD_CRUMB_0001'),
    path: '/',
  },
])
</script>
<style lang="scss" scoped>
.home {
  height: 100%;
  margin-top: 40%;
  text-align: center;
}
</style>
