<template>
  <el-sub-menu index="item.id" v-if="item.isDir">
    <template #title>
      <i :class="item.iconClass"></i>
      <span>{{ item.name }}</span>
    </template>
    <slide-item v-for="(child, index) in item.children" :key="index" :item="child"> </slide-item>
  </el-sub-menu>
  <el-menu-item index="item.link" v-else>
    <i :class="item.iconClass"></i>
    <span>{{ item.name }}</span>
  </el-menu-item>
</template>
<script lang="ts" setup>
defineProps({
  item: {
    type: Object,
    default: () => {},
  },
})
</script>
<style lang="scss" scoped>
@use 'scss/_index.scss' as *;
.navigator {
  height: 100%;
  .menu {
    height: calc(100% - 60px);
    overflow: auto;
    .el-menu {
      border-right: none;
      i {
        font-size: 16px;
        width: 24px;
        margin-right: 5px;
        text-align: center;
      }
      .el-menu-item {
        line-height: 48px;
        height: 48px;
      }
      .el-submenu__title {
        line-height: 48px;
        height: 48px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
    }
  }
}
</style>
